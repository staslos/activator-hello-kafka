import scala.util.Random

object ProducerStreamExample {
  def main(args: Array[String]): Unit = {
    val strProducer = Producer[String]("mali")

    val messageStream = Stream.continually{
      Random.alphanumeric.take(5).mkString
    }.take(100)

    strProducer.sendStream(messageStream)
  }
}