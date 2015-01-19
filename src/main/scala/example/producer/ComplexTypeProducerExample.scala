package example.producer

import java.nio.ByteBuffer

import example.utils.KafkaConfig
import kafka.serializer.{Decoder, Encoder}
import kafka.utils.VerifiableProperties


object ComplexTypeProducerExample {
  def main(args: Array[String]): Unit = {
    val topicName =
      if(args.length == 0) "complexTestTopic"
      else args(0)

    val config = KafkaConfig()
    config.put("serializer.class", "example.producer.MessageEncoder")

    val producer = Producer[Message](topicName, config)
    val message = Message(1, "mobile")

    producer.send(message)
  }
}

case class Message(userId: Int, source: String)

case class MessageEncoder(verifiableProperties: VerifiableProperties ) extends Encoder[Message] {
  override def toBytes(t: Message): Array[Byte] = {
    val intBuffer = ByteBuffer.allocate(4)
    intBuffer.putInt(t.userId)

    val sourceBuffer = ByteBuffer.wrap(t.source.getBytes())
    Array.concat(intBuffer.array(), sourceBuffer.array())
  }
}