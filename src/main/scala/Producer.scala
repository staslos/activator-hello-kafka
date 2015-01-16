import java.util.Properties

import kafka.api._
import kafka.producer.{ Producer => KafkaProducer }
import kafka.producer._
import kafka.consumer._

case class Producer[A](topic: String) {
  //TODO: will be replaced with typesafeCofig
  val config = new ProducerConfig(KafkaConfig())
  val producer = new KafkaProducer[A, A](config)

  def send(message: A) = sendMessage(producer, keyedMessage(topic, message))

  def sendStream(stream: Stream[A]) = {
    val iter = stream.iterator
    while(iter.hasNext) {
      send(iter.next())
    }
  }


  private def keyedMessage(topic: String, message: A): KeyedMessage[A, A] = new KeyedMessage[A, A](topic, message)
  private def sendMessage(producer: KafkaProducer[A, A], message: KeyedMessage[A, A]) = producer.send(message)
} 
