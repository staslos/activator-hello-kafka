package example.consumer

object ConsumerChunkExample {
  def main(args: Array[String]): Unit = {
    val topicNames = if(args.length == 0) {
      List("mali")
    } else {
      args.toList
    }

    val consumer = Consumer(topicNames)

    val readResponse = consumer.readChunk()
    println(readResponse)
  }
}
