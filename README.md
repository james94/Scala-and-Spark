# Scala and Spark for Machine Learning

Use Scala and Spark to Analyze Big Data using MLib, SQL and DataFrames.

## Certificate of Udemy Course Completion

[Scala and Spark Course Certificate](medel-scala-and-spark-UC-4T63CYNN.jpg)

## Projects

### Run Scala Spark Apps via Terminal

For Windows:

~~~cmd
cd path\to\project-folder
REM ex: cd Scala-and-Spark\projects\p1-dataframe
spark-shell

REM spark shell loads
REM inside folder: p1-dataframe, run scala app
:load main.scala
~~~

For Mac or Linux:

~~~bash
cd path/to/project-folder
# ex: cd Scala-and-Spark/projects/p1-dataframe
spark-shell

# spark shell loads
# inside folder: p1-dataframe, run scala app
:load main.scala
~~~

### P1. DataFrames

Given Netflix stock data from 2011 - 2016 in csv format, I used Spark DataFrames to answer questions about the data set.

[p1-dataframe/main.scala](projects/p1-dataframe/main.scala)

### P2. Linear Regression

Given a data set containing customer information for an ecommerce company, I created a linear regression model to predict yearly amount expenditure of a customer.

[p2-linear-regression/main.scala](projects/p2-linear-regression/main.scala)

### P3. Logistic Regression

Given a data set containing fake advertising data set, indicating whether or not a particular internet user clicked on an Advertisement, I created a model that predicts whether or not a user will click on an ad based off the features of that user.

[p3-logistic-regression/main.scala](projects/p3-logistic-regression/main.scala)

### P4. Clustering

The _situation_ was I was given [Wholesale customers Data Set](http://archive.ics.uci.edu/ml/datasets/Wholesale+customers) from [UC Irvine's machine learning data set repository](https://archive.ics.uci.edu/ml/index.php). This data set includes annual spending on specific product categories. My _task_ was to cluster the clients into at least 3 regions based off of the sales of some sort of product categories. My _actions_ included **extracting** the Wholesale Customers csv data, **transforming** the feature data columns into a single column array, **loading** that data into the [kmeans model](https://spark.apache.org/docs/latest/ml-clustering.html#k-means) to train it and **evaluating** the model using Within Set Sum of Squared Errors. The _result_ with K = 3 clusters was the error = 8.0951. I noticed as I increased K, the error decreased by 1 until K = 6 clusters, then there was not a huge change in error. Thus, the lowest error = 4.8 when K = 6.

[p4-clustering/main.scala](projects/p4-clustering/main.scala)

### P5. PCA

The _situation_ was I was given a [Breast Cancer Wisconsin (Diagnostic) Data Set](https://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+(Diagnostic)) from [UC Irvine's machine learning data set repository](https://archive.ics.uci.edu/ml/index.php). My _task_ was to transform a dataset with 30 features into a dataset with fewer principle components. My _actions_ included **extracting** the Cancer_Data, **transforming** the 30 columns of feature data into a single column array with all those features, **normalizing** the data to have unit standard deviation as a pre-step for Principle Component Analysis (PCA) and **loading** that data into PCA object to transform the 30 scaled features array into 4 principle components. The _result_ was there was only 4 principle components.

[p5-pca/main.scala](projects/p5-pca/main.scala)

### P6. Recommendation System

Prerequisite: Have a Cluster Set Up on Databricks Platform or Cloudera Platform on a Cloud Service of your choice. Have uploaded the movie_ratings data to the cluster. Have created a Databricks notebook, Jupyter Notebook or Zeppelin notebook for writing the Spark code.

The _situation_, I was given a movie ratings data set from [GroupLens research at MovieLens](https://grouplens.org/datasets/movielens/) website. This dataset includes movie ratings by users. My _task_ was to implement a full recommendation system on a real cluster on the [databricks platform](https://databricks.com/try-databricks?utm_campaign=701610000008nLSAAY&gclid=Cj0KCQjwoebsBRCHARIsAC3JP0LFmdgFTNQi478tmPyhRe75tFyaRlJdMhnQ-d5Ap9celtB_SyIDUiIaAnGsEALw_wcB). My _actions_ included setting up the databricks community edition platform as a single node cluster, uploading the movie_ratings.csv small dataset to this cluster, creating a databricks notebook to write a Scala Spark collaborative filtering based recommendation system application. My Spark code included **extracting** the movie_rating csv data, **transforming** the movie data by splitting up 80% for training and 20% for testing the model, **loading** the training data into the [Alternating Least Squares (ALS) model](https://spark.apache.org/docs/latest/ml-collaborative-filtering.html) to train it and **evaluating** the model to see on average how far off our movie prediction rating is from our actual rating. The _result_ is on average, the ALS model's prediction is 84% off from the actual rating. So, the model is off by a little less than 1 star, which is decent.

[p6-recommendation-system/main.scala](projects/p6-recommendation-system/main.scala)