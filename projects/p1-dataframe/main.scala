// DATAFRAME PROJECT
// Use the Netflix_2011_2016.csv file to Answer and complete
// the commented tasks below!

// Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// For Scala/Spark $ Syntax
import spark.implicits._

// Load the Netflix Stock CSV File, have Spark infer the data types.
val df = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")

// What are the column names?
df.columns

// What does the Schema look like?
df.printSchema()

// Print out the first 5 rows.
df.head(5)
println("\n")
for(row <- df.head(5)){
    println(row)
}

// Use describe() to learn about the DataFrame.
df.describe().show()

// Create a new dataframe with a column called HV Ratio that
// is the ratio of the High Price versus volume of stock traded
// for a day.
val df2 = df.withColumn("HV Ratio", (df("High")/df("Volume")))
df2.columns
df2.printSchema()

df2.head(5)

// What day had the Peak High in Price?
df2.describe().show()
val dfPeakHigh = df2.filter($"High" > 716)
dfPeakHigh.select("Date", "High").show()

// What is the mean of the Close column?
df2.select(avg("Close")).show()

// What is the max and min of the Volume column?
df2.select(max("Volume"), min("Volume")).show()



// How many days was the Close lower than $ 600?
df2.filter($"Close" < 600).count()

// What percentage of the time was the High greater than $500 ?
(df2.filter($"High" > 500).count()*1.0 / df2.count())*100

// What is the Pearson correlation between High and Volume?
df2.select(corr("High", "Volume")).show()

// What is the max High per year?
val df3 = df2.withColumn("Year", year(df("Date")))
val dfMax = df3.groupBy("Year").max()
val resultMaxHigh = dfMax.select($"Year", $"max(High)")
resultMaxHigh.orderBy("Year").show()

// What is the average Close for each Calender Month?
val df4 = df3.withColumn("Month", month(df("Date")))
val dfAvg = df4.groupBy("Month").mean()
val resultAvgClose = dfAvg.select($"Month", $"avg(Close)")
resultAvgClose.orderBy("Month").show()