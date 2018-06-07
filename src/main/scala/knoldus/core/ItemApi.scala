package knoldus.core

import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import com.gu.scanamo.error.DynamoReadError

import scala.concurrent.Future

case class Code(itemCodes: List[String])

case class Item(id: Long, name: String, code: Code)

trait ItemApi {

  def put(items: Item): Future[Option[Either[DynamoReadError, Item]]]

  def get(id: Long): Future[Option[Either[DynamoReadError, Item]]]

  def delete(id: Long): Future[DeleteItemResult]

  def readAll: Future[List[Either[DynamoReadError, Item]]]

}