package example
package consumer

import example.utils.KafkaConfig
import kafka.consumer.{ Consumer => KafkaConsumer }
import kafka.consumer._
import kafka.serializer._
import scala.collection.JavaConversions._
import kafka.api._

case class Consumer(topics: List[String]) {
  private val kafkaConfig = KafkaConfig()
  private val config = new ConsumerConfig(kafkaConfig)
  private val consumer = KafkaConsumer.create(config)

  //topics to listen
  private val filterSpec = new Whitelist(topics.mkString(","))

  private val stream = consumer.createMessageStreamsByFilter(filterSpec, 1, new DefaultDecoder(), new DefaultDecoder()).get(0)

  private val clientId = kafkaConfig.getCustomString("consumer.clientId")

  val simpleConsumer = new SimpleConsumer(
    kafkaConfig.getCustomString("consumer.host"),
    kafkaConfig.getCustomInt("consumer.port"),
    kafkaConfig.getCustomInt("consumer.timeOut"),
    kafkaConfig.getCustomInt("consumer.bufferSize"),
    clientId)

  def readStream(writer: String => Unit) = {
    for(messageAndTopic <- stream) {
      try{
        val message = new String(messageAndTopic.message, "UTF-8")
        writer(message)
      }
      catch {
        case e: Throwable => println(e)
      }
    }
  }

  def readChunk() = {
    val (partition, offset, fetchSize) = (0, 0L, 100)

    var fetchRequest = new FetchRequestBuilder().clientId(clientId)
    for(topic <- topics) {
      fetchRequest.addFetch(topic, partition, offset, fetchSize)
    }

    val fetchResponse = simpleConsumer.fetch(fetchRequest.build())

    fetchResponse.data.values.flatMap{ topic =>
      topic.messages.toList.map(mao => new String(mao.message.payload.array(), "UTF-8"))
    }
  }

}
