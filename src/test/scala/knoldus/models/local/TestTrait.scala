package knoldus.models.local

import java.io.File

import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType.N
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

import scala.util.Try

trait TestTrait extends AsyncFlatSpec with BeforeAndAfterAll {

  var process: Process = _

  val processBuilder = new ProcessBuilder("java",
    "-Djava.library.path=./DynamoDBLocal_lib",
    "-jar",
    "DynamoDBLocal.jar",
    "-sharedDb",
    "-inMemory",
    "-port",
    "8042")
    .inheritIO()
    .directory(new File("/home/knoldus/Downloads/dynamodb_local_latest"))

  override def beforeAll(): Unit = {
    process = processBuilder.start()
    Try(LocalDynamoDB.createTable(LocalDynamoDB.client())("asyncItems")('id -> N))
  }

  override def afterAll(): Unit = process.destroy()
}
