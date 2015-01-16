import scala.util.Random

object ConsumerStreamExample {
  def main(args: Array[String]): Unit = {
    val topicNames = if(args.length == 0) {
      List("mali")
    } else {
      args.toList
    }

    val consumer = Consumer(topicNames)

    consumer.readStream(println(_))
  }
}