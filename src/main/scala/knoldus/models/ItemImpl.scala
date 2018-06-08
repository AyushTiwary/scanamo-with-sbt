package knoldus.models

import akka.stream.alpakka.dynamodb.scaladsl.DynamoClient
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import com.gu.scanamo.{ScanamoAlpakka, ScanamoAsync}
import com.gu.scanamo.error.DynamoReadError
import com.gu.scanamo.query.{KeyEquals, UniqueKey}
import knoldus.models.core.{Item, ItemApi}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ItemImpl(client: DynamoClient) extends ItemApi {

  val tableName = "asyncItems"

  override def put(item: Item): Future[Option[Either[DynamoReadError, Item]]] =
    ScanamoAlpakka.put(client)(tableName)(item)

  override def get(id: Long): Future[Option[Either[DynamoReadError, Item]]] =
    ScanamoAlpakka.get[Item](client)(tableName)(UniqueKey(KeyEquals('id, id)))

  override def delete(id: Long): Future[DeleteItemResult] =
    ScanamoAlpakka.delete[Item](client)(tableName)(UniqueKey(KeyEquals('id, id)))

  override def readAll: Future[List[Either[DynamoReadError, Item]]] =
    ScanamoAlpakka.scan[Item](client)(tableName)

}
