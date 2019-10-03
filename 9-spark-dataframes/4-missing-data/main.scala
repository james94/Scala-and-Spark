// Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Grab small dataset with some missing data
val df = spark.read.option("header","true").option("inferSchema","true").csv("../ContainsNull.csv")

// df.printSchema()

// df.show()

// // Drop rows with any null values
// df.na.drop().show()

// // Drop rows with 2 null values
// df.na.drop(2).show()

// look for all columns that match 100 data type, fill it in
// what columns have a data type that is a number? Int, Double
// Sales column has that data type, fills in 100 for missing values
// df.na.fill(100).show()

// fill in any missing values that are string
// df.na.fill("Missing Name").show()

// specify that I want to fill in Name column
// df.na.fill("New Name", Array("Name")).show()

// specify that I want to fill in Sales column with 200
// df.na.fill(200, Array("Sales")).show()

// get avg sales data
df.describe().show()

// fill in Sales col with avg sales data
// df.na.fill(400.5, Array("Sales")).show()
// df.na.fill("missing name", Array("Name")).show()

val df2 = df.na.fill(400.5, Array("Sales"))
df2.na.fill("missing name", Array("Name")).show()