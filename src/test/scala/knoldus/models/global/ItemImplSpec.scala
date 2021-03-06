package knoldus.models.global

import akka.stream.alpakka.dynamodb.scaladsl.DynamoClient
import knoldus.models.ItemImpl
import knoldus.models.core.{Code, Item}
import org.scalatest.AsyncFlatSpec

class ItemImplSpec extends AsyncFlatSpec{

  val client: DynamoClient = DynamoDBClient.client
  val itemImpl = new ItemImpl(client)

  it should "put the item" in {
    val id = scala.util.Random.nextInt()
    val item = Item(id, "name", Code(List("code1")))
    val futureRes = itemImpl.put(item)
    futureRes.map(res => assert(res === None))
  }

  it should "put & get the item" in {
    val id = scala.util.Random.nextInt()
    val item = Item(id, "name", Code(List("code2")))
    val futureRes = for {
      _ <- itemImpl.put(item)
      res <- itemImpl.get(id)
    } yield res
    futureRes.map(res => assert(res === Some(Right(Item(id, "name", Code(List("code2")))))))
  }

  it should "delete the item" in {
    val id = scala.util.Random.nextInt()
    val item = Item(id, "name", Code(List("code3")))
    val futureRes = for {
      _ <- itemImpl.put(item)
      resBeforeDelete <- itemImpl.get(id)
      _ <- itemImpl.delete(id)
      resAfterDelete <- itemImpl.get(id)
    } yield (resBeforeDelete, resAfterDelete)

    futureRes.map {
      case (resBeforeDelete, resAfterDelete) => assert(resBeforeDelete === Some(Right(Item(id, "name", Code(List("code3")))))
        && resAfterDelete === None)
    }
  }

  it should "get all the items" in {
    val futureRes = itemImpl.readAll
    futureRes.map(list => assert(list.nonEmpty))
  }
}
