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