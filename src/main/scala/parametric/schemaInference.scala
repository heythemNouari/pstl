package parametric

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import typeReduction._
import typeDefinition._
import typeInference._
import play.api.libs.json.{JsArray, JsBoolean, JsNull, JsNumber, JsObject, JsString, JsValue, Json}

class schemaInference(path: String, equiv: String, spark: SparkSession){
  def infer() = {
    println(s"infer the $equiv schema from $path")
    val helper = new Helper()
    val order = helper.whichOrdering(equiv)

    import spark.implicits._
    /**load json lines*/
    val objects = spark.read.text(path).as[String].rdd //coalesce...

    /**infer the schema*/
    val reducer = new Reduction()
    val inferencer = new Inference()

    val parsed: RDD[JsValue] = objects.filter(x=>x.length()>0).map{ x=>
      try Json.parse(x.stripMargin)
      catch {
        case e  : Throwable=> e.printStackTrace() ; JsNull
        //a quick trick to avoid crashing the app TODO improve
      }
    }
    val types = parsed.map(x=>inferencer.inferStructType(x, reducer.Reduce,order).asInstanceOf[countingType])
    val result = types.reduce((t,u)=>reducer.Reduce(t,u,order))
  }
}