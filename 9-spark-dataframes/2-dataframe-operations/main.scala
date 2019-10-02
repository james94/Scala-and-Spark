import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header","true").option("inferSchema","true").csv("../CitiGroup2006_2008")

df.printSchema()

// filter off data based on some condition
// use scala syntax with dollar sign notation
import spark.implicits._

// scala notation where
// df.filter($"Close">480).show()

// sql notation: where Close > 480
// df.filter("Close > 480").show()

// scala notation where
// df.filter($"Close" < 480 && $"High" < 480).show()

// sql notation where
// df.filter("Close < 480 AND High < 480").show()

// collect dataframe as an array
// val CH_low = df.filter("Close < 480 AND High < 480").collect()

// know how many rows it returns
// val CH_low = df.filter("Close < 480 AND High < 480").count()

// === needs to be used with scala notation
// df.filter($"High" === 484.40).show()

// sql notation
// df.filter("High = 484.40").show()

// how correlated our 2 columns
df.select(corr("High", "Low")).show()


