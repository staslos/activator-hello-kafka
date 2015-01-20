package example.consumer

import play.api.libs.json._

object JsonConsumerExample {
  def main(args: Array[String]): Unit = {
    val topicName =
      if(args.length == 0) "testTopicComplex"
      else args(0)

    val consumer = Consumer(List(topicName))

    implicit val reads = Json.reads[Message]

    consumer.readStream(println(_))
  }
}

case class Message(userId: Int, source: String)