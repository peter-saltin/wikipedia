

/**
 * @author peter


 */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd._
import org.apache.spark.mllib.recommendation.{ALS, Rating, MatrixFactorizationModel}
import scala.io.Source
import java.util.Random

/**
 * @author peter saltin 
 */
object wikipedia {

  def main(args: Array[String]) {
    val sparkHome = "/root/spark"
    val master = Source.fromFile("/root/spark-ec2/cluster-url").mkString.trim
    val masterHostname = Source.fromFile("/root/spark-ec2/masters").mkString.trim    
    val conf = new SparkConf().
      setAppName("Wikipedia Analysis").
      set("spark.executor.memory", "6g").
      setMaster(master).
      setSparkHome(sparkHome)
    val sc = new SparkContext(conf)
    val hadoopConf=sc.hadoopConfiguration
    hadoopConf.set("fs.s3.impl", "org.apache.hadoop.fs.s3native.NativeS3FileSystem")
    hadoopConf.set("fs.s3n.awsAccessKeyId",sys.env("AWS_ACCESS_KEY_ID"))
    hadoopConf.set("fs.s3n.awsSecretAccessKey",sys.env("AWS_SECRET_ACCESS_KEY"))
}