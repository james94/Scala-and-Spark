// Databricks notebook source
import org.apache.spark.ml.recommendation.ALS

// COMMAND ----------

// grab our data as our ratings
val ratings = spark.read.option("header","true").option("inferSchema","true").csv("/FileStore/tables/movie_ratings.csv")

// COMMAND ----------

ratings.head()

// COMMAND ----------

ratings.printSchema

// COMMAND ----------

// do the training, test split
val Array(training,test) = ratings.randomSplit(Array(0.8,0.2))

// COMMAND ----------

// Building Recommender Model
// Alternating Least Squares (ALS) algorithm
// Setup ALS dataset
// Max Iterations
// Regularization parameter
// User column, item column, user column, rating column
// Optional: mess with setMaxIter and setRegParam and see if they make a difference for our average error
// on that rating
val als = new ALS().setMaxIter(5).setRegParam(0.01).setUserCol("userId").setItemCol("movieId").setRatingCol("rating")

// COMMAND ----------

val model = als.fit(training)

// COMMAND ----------

// evaluate model by performing transformations on test data
val predictions = model.transform(test)

// COMMAND ----------

// shows userId, movieId, rating and prediction from test data
// prediction is based on userId and movieId
// I predicted user 534 when watching movie 463 would have a 3.9 rating when in reality, they had
// a 4.0 rating
predictions.show()

// COMMAND ----------

// How can we evaluate how well the model did?
// we can check how far off we are from the rating vs the prediction
// I create a new column, rating minus prediction, then take average of that column to see on average 
// how far off our prediction is from our rating
// I took the absolute value to deal with situations when the rating minus prediction was negative
import org.apache.spark.sql.functions._
val error = predictions.select(abs($"rating"-$"prediction"))

// COMMAND ----------

// gives me absolute value of rating minus prediction
// I can see how off I was for each of these predictions
// I want to know on average how far off am I?
error.show()

// COMMAND ----------

// to know how far off I am on average, I run the following code
// you'll notice we don't get that mean or avg immediately
// the mean and std dev have null or nan since when we did the split on training and testing
// data, there were some user ids that weren't able to be in the both training and testing,
// so we have null values in our output, which means the model had never seen the userId,movieId
// before and it wasn't able to do some output
error.describe().show()

// COMMAND ----------

// this will show the average for where we do have information for userId,movieId and drop
// those null values as we learned when we dealt with missing data in dataframes
// on average, we are off by 0.84
// so based on the split we did for training and test data, on average our model is off by alittle < 1 star
// it means that if you really liked that movie, our model is able to predict you more or less would like
// that movie within 1 star and it is the samething if you didn't like a movie
error.na.drop().describe().show()

// COMMAND ----------

// Summary
// We are able to implement our full recommendation system on a real cluster on the databricks platform
// Recommendation systems are ccomplex and deal with a whole bunch of problems that other ML algorithms
// don't have to deal with, such as the "Cold Start problem", which is when you have a user who is never
// initiated with the platform that you are trying to recommend
// For instance, if you are on Netflix, a very new user who just signed up for that service, what movies
// do you recommend to them if they have never seen any movies on your platform and you have no info about
// them
// Similarly, if it is a customers first time on a website like Amazon, what products do you recommend for
// this person to buy if you have no purchasing history or browsing history
// There are ways to solve this problem, one solution is to recommend the top 10 most popular items to
// people and seeing what the data says from there

// We were able to do a full recommendation system with Scala and Spark with just a few lines of code.
// It is fully parallelizable meaning you can grab the larger cluster off the databricks pro platform
// and deal with much larger datasets
