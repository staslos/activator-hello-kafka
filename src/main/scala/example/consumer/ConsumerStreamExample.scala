package example.consumer

object ConsumerStreamExample {
  def main(args: Array[String]): Unit = {
    val topicNames = if(args.length == 0) {
      List("mali")
    } else {
      args.toList
    }

    val consumer = StreamConsumer(topicNames)

    consumer.read().foreach(println)
  }
}
