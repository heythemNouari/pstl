import java.io.{File, PrintWriter}

import parametric.schemaInference
import org.apache.spark.sql.SparkSession
import play.api.libs.json.{JsValue, Json}


object runInference {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[8]")
      .appName("JSON Schema Inference")
      .getOrCreate()

    /*TODO set the path and equiv variables*/
    val path = "hdfs://localhost:9000/Json/"
    val file = "nytimes.1k.json"
    val equiv = "k" // "l"
    val result = new schemaInference(path+file,equiv,spark).infer()
    println(result)
    spark.stop()

  }
}