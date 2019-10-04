import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder,TrainValidationSplit}

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

// READ data
val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("../../11-regression-with-spark/Regression/Clean-USA-Housing.csv")

data.printSchema

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val df = data.select(data("Price").as("label"),$"Avg Area Income", $"Avg Area House Age", $"Avg Area Number of Rooms", $"Avg Area Number of Bedrooms", $"Area Population")

// put all the features into a single column using vector assembler
val assembler = new VectorAssembler().setInputCols(Array("Avg Area Income", "Avg Area House Age", "Avg Area Number of Rooms", "Avg Area Number of Bedrooms", "Area Population")).setOutputCol("features")
// transform dataframe using that assembler
val output = assembler.transform(df).select($"label",$"features")

// TRAINING AND TEST data
// create array of training and test data
val Array(training,test) = output.select("label","features").randomSplit(Array(0.7,0.3),seed=12345)

// linear regression model
val lr = new LinearRegression()

// Parameter Grid Builder: needed for holdout grid
val paramGrid = new ParamGridBuilder().addGrid(lr.regParam, Array(1000, 1000000000, 0.001)).build()

// Train SPLIT (Holdout)
val trainvalsplit = (new TrainValidationSplit()
                    .setEstimator(lr)
                    .setEvaluator(new RegressionEvaluator().setMetricName("r2"))
                    .setEstimatorParamMaps(paramGrid)
                    .setTrainRatio(0.8))

val model = trainvalsplit.fit(training)

model.transform(test).select("features","label","prediction").show()

// calculate error from label and prediction
