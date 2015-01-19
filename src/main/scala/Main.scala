import java.util.Properties

import kafka.api.{OffsetRequest, FetchRequestBuilder, FetchRequest}
import kafka.producer.{KeyedMessage, ProducerConfig, Producer}
import kafka.consumer._

import scala.collection.immutable.Stream
import scala.collection.immutable.Stream

object Main extends App {

  val properties = new Properties();


  properties.put("metadata.broker.list", "188.166.37.75:49165")
  properties.put("serializer.class", "kafka.serializer.StringEncoder")


  val config = new ProducerConfig(properties)
  val producer = new Producer[String, String](config)

  val topic = "cihad4"
  val message = "hello"
  val messageStream = Stream.continually {
    new KeyedMessage[String, String](topic, message)
  }
  val keyedMessage = new KeyedMessage[String, String](topic, message)
  //println(messageStream.take(5).toList)
  producer.send(messageStream.take(5).toList :_*)
}



object ConsumerApp extends App {

  val properties = KafkaConfig()

  val config = new ConsumerConfig(properties)
  val (seed, port, timeout, bufferSize, clientId) = ("188.166.37.75", 49165, 10000, 64 * 1024, "testClient")
  val consumer = new SimpleConsumer(seed, port, timeout, bufferSize, clientId)

  val (topic, partition, offset, fetchSize) = ("cihad4", 0, 0L, 1000)

  val request = new FetchRequestBuilder().clientId(clientId).addFetch(topic, partition, offset, fetchSize).build()

  val response = consumer.fetch(request)

  println(response)
}
