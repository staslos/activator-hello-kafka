
object ProducerExample {
  def main(args: Array[String]): Unit = {
    val strProducer = Producer[String]("mali")
    
    strProducer.send("hello");
  }
}