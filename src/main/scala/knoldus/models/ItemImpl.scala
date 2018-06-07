package knoldus.models

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import com.gu.scanamo.ScanamoAsync
import com.gu.scanamo.error.DynamoReadError
import com.gu.scanamo.query.{KeyEquals, UniqueKey}
import knoldus.core.{Item, ItemApi}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ItemImpl(client: AmazonDynamoDBAsync) extends ItemApi {

  val tableName = "asyncItems"

  override def put(item: Item): Future[Option[Either[DynamoReadError, Item]]] =
    ScanamoAsync.put(client)(tableName)(item)

  override def get(id: Long): Future[Option[Either[DynamoReadError, Item]]] =
    ScanamoAsync.get[Item](client)(tableName)(UniqueKey(KeyEquals('id, id)))

  override def delete(id: Long): Future[DeleteItemResult] =
    ScanamoAsync.delete[Item](client)(tableName)(UniqueKey(KeyEquals('id, id)))

  override def readAll: Future[List[Either[DynamoReadError, Item]]] =
    ScanamoAsync.scan[Item](client)(tableName)

}
