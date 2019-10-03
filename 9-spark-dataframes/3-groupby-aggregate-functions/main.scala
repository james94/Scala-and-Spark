import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Create a DataFrame fromo Spark Session read csv
// Technically known as class Dataset
val df = spark.read.option("header", "true").option("inferSchema","true").csv("../Sales.csv")

// print schema and see what dataframe looks like
df.printSchema()

// see what dataframe looks like
df.show()

// // group by company columns
// df.groupBy("Company").mean().show()

// // group by company and count, 3 employees at google, 2 facebook,etc
// df.groupBy("Company").count().show()

// // print max values of these sales values
// df.groupBy("Company").max().show()

// // print min values of these sales values
// df.groupBy("Company").min().show()

// // print sum values of these sales values
// df.groupBy("Company").sum().show()

// take sum of all values in Sales column
// df.select(sum("Sales")).show()

// order your results
df.show()
// order by lowest to highest for Sales
df.orderBy("Sales").show()

// order by highest to lowest for Sales
df.orderBy($"Sales".desc).show()