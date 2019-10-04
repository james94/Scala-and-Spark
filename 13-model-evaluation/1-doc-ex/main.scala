import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder,TrainValidationSplit}

val data = spark.read.format("libsvm").load("../Model_Validation/sample_linear_regression_data.txt")

// TRAIN TEST SPLIT
val Array(training,test) = data.randomSplit(Array(0.9,0.1),seed=12345)

val lr = new LinearRegression()
// set up parameter grid
// we cab yse paramgridbuilder to construct a grid of params to search over
// add grid of params for linear regression object
// train validation split will try all combination of values and find best model using evaluator we imported
val paramGrid = new ParamGridBuilder().addGrid(lr.regParam,Array(0.1,0.01)).addGrid(lr.fitIntercept).addGrid(lr.elasticNetParam,Array(0.0,0.5,1.0)).build()


// run train validation split
val trainValidationSplit = new TrainValidationSplit().setEstimator(lr).setEvaluator(new RegressionEvaluator()).setEstimatorParamMaps(paramGrid).setTrainRatio(0.8)

val model = trainValidationSplit.fit(training)

model.transform(test).select("features","label","prediction").show()