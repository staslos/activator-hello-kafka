import ZookeeperUtils._

object DeleteTopicExample {

  def main(args: Array[String]): Unit = {
    var topicName = ""

    if(args.length == 0) {
      println("topic name must be provided")
      System.exit(-1)
    } else {
      topicName = args(0).toString
    }

    println(s"Deleting topic: $topicName")
    val zookeeperClient = createClient()
    TopicAdmin(zookeeperClient).deleteTopic(topicName)
    println("Topic %s is marked for deletion.".format(topicName))
    println("Note: This will have no impact if delete.topic.enable is not set to true.")
  }
}
