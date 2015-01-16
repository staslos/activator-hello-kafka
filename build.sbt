libraryDependencies ++= Seq(
	"org.apache.kafka" % "kafka_2.10" % "0.8.1"
    	exclude("javax.jms", "jms")
    	exclude("com.sun.jdmk", "jmxtools")
    	exclude("com.sun.jmx", "jmxri"),
	"com.typesafe" % "config" % "1.2.1",
	"org.scalatest" % "scalatest_2.10" % "2.2.1" % "test"
)