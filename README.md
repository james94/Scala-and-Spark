# Scala-and-Spark
Use Scala and Spark to Analyze Big Data

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

[p3-logistic-regression/main.scala](projects/p3-logistic-regression/main.scala)

### P4. Clustering

The _situation_ was I was given portuguese wholesale customers data set from [UC Irvine's machine learning data set repository](http://archive.ics.uci.edu/ml/datasets/Wholesale+customers). This data set includes annual spending on specific product categories. My _task_ was to cluster the clients into at least 3 regions based off of the sales of some sort of product categories. My _actions_ included creating a **SparkSession**, **extracting** the Wholesale Customers csv data, **transforming** the feature data columns into a single column array, **loading** that data into the [kmeans model](https://spark.apache.org/docs/latest/ml-clustering.html#k-means) to train it and **evaluating** the model using Within Set Sum of Squared Errors. The _result_ with K = 3 clusters was the error = 8.0951. I noticed as I increased K, the error decreased by 1 until K = 6 clusters, then there was not a huge change in error. Thus, the lowest error = 4.8 when K = 6.

[p4-clustering/main.scala](projects/p4-clustering/main.scala)

### P5. PCA

Breast Cancer Wisconsin (Diagnostic) Database

569 instances of data

You are to transform a dataset with 30 features into a dataset with fewer principle components. Keep in mind there are some parts to this project that we haven't shown because part of this project is to discover how to do something on your own by reading through the documentation. 30 numeric attributes about cancer and tumors. Figure out how to use normalization to standardize the data.

Often it is a good idea to normalize each feature to have the unit standard deviation and or zero mean when using principle analysis. It is a prerequisite step. Look at ML feature documentation. Figure out how you can use StandardScalar to standardize the cancer dataset.

PCAs simple todo once you have understanding of ml.feature objects

set input col to be features, do some transformation with the output col, fit, transform, etc

[p5-pca/main.scala](projects/p5-pca/main.scala)

### P6. Recommendation System

