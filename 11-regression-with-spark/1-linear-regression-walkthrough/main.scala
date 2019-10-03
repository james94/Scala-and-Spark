import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()

val data = spark.read.option("header","true").option("inferSchema","true").option("multiline","true").format("csv").load("../Regression/USA_Housing.csv")

data.printSchema

// make sure our formatting is correct
data.head(1)

// val colnames = data.columns
// val firstrow = data.head(1)(0)
// println("\n")
// println("Example Data Row")
// for(ind <- Range(1,colnames.length)){
//   println(colnames(ind))
//   println(firstrow(ind))
//   println("\n")
// }

// setup dataframe for ml
// ("labels","features")
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// grab label, predict price of house, rename price to label
val df = (data.select(data("Price").as("label"), 
         $"Avg Area Income", $"Avg Area House Age", 
         $"Avg Area Number of Rooms", $"Avg Area Number of Bedrooms", $"Area Population"))


// Assembler convert input to single vector
// single array is what ml needs to read to train the model
val assembler = (new VectorAssembler()
                .setInputCols(Array("Avg Area Income", "Avg Area House Age", 
                "Avg Area Number of Rooms", "Avg Area Number of Bedrooms", 
                "Area Population")).setOutputCol("features"))

val output = assembler.transform(df).select($"label",$"features")

// create linear regression model object, train it and explore results
val lr = new LinearRegression()

// fit our model to our training data
val lrModel = lr.fit(output)

val trainingSummary = lrModel.summary

// residual is difference between label and prediction value
trainingSummary.residuals.show()
