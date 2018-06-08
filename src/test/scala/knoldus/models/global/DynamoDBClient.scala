package knoldus.models.global

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.dynamodb.impl.DynamoSettings
import akka.stream.alpakka.dynamodb.scaladsl.DynamoClient

object DynamoDBClient {
  implicit val system = ActorSystem("scanamo-alpakka")
  implicit val materializer = ActorMaterializer()

  val settings = DynamoSettings(system)
  val client = DynamoClient(settings)
}
