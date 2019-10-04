import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()

val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("../Classification/titanic.csv")

data.printSchema()

val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example Data Row")
for(ind <- Range(1,colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}

val logregdataall = (data.select(data("Survived").as("label"),$"Pclass", $"Name",
                    $"Sex", $"Age", $"SibSp", $"Parch", $"Fare", $"Embarked"))

val logregdata = logregdataall.na.drop()

import org.apache.spark.ml.feature.{VectorAssembler,StringIndexer,VectorIndexer,OneHotEncoderEstimator}
import org.apache.spark.ml.linalg.Vectors

// Converting Strings into numerical values
val genderIndexer = new StringIndexer().setInputCol("Sex").setOutputCol("SexIndex")
val embarkedIndexer = new StringIndexer().setInputCol("Embarked").setOutputCol("EmbarkIndex")

// Convert numerical values into One Hot Encoding 0 or 1
val genderEncoder = new OneHotEncoderEstimator().setInputCols(Array("SexIndex")).setOutputCols(Array("SexVec"))
val embarkEncoder = new OneHotEncoderEstimator().setInputCols(Array("EmbarkIndex")).setOutputCols(Array("EmbarkVec"))

// (label,featurs)
val assembler = (new VectorAssembler()
                    .setInputCols(Array("Pclass","SexVec","Age","SibSp",
                    "Parch","Fare","EmbarkVec")).setOutputCol("features"))

val Array(training,test) = logregdata.randomSplit(Array(0.7,0.3),seed=12345)

import org.apache.spark.ml.Pipeline

val lr = new LogisticRegression()

val pipeline = new Pipeline().setStages(Array(genderIndexer, embarkedIndexer, genderEncoder, embarkEncoder,assembler,lr))

val model = pipeline.fit(training)

val results = model.transform(test)

//
//////////////////////////////
// MODEL EVALUATION
//////////////////////////////
import org.apache.spark.mllib.evaluation.MulticlassMetrics

val predictionAndLabels = results.select($"prediction",$"label").as[(Double,Double)].rdd

// multiclassmetrics is broken since it is under the old RDD
// and in the future won't be updated since it is under the old RDD
val metrics = new MulticlassMetrics(predictionAndLabels)

// confusion matrix still works, but when you are dealing with
// something on the edge of technology, you're going to have to be
// dealing with this stuff often, check docs to make sure stuff is 
// working
// metrics.accuracy: recall and precision are the same as accuracy
// if you want to calculate precision and recall you should do that
// off of the confusion matrix
println("Confusion matrix:")
println(metrics.confusionMatrix)