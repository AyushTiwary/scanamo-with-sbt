package knoldus.models.global

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder

object DynamoDBClient {
  val client = AmazonDynamoDBAsyncClientBuilder.standard()
    .withRegion(Regions.US_EAST_1)
    .build()
}
