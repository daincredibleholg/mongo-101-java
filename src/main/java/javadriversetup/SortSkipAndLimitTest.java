package javadriversetup;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import util.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

public class SortSkipAndLimitTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("sortSkipAndLimitTest");

		collection.drop();

		// insert 100 documents
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				collection.insertOne(new Document().append("i", i).append("j", j));
			}
		}

		Bson projection = fields(include("i", "j"), excludeId());
//		Bson sort = new Document("i", 1).append("j", -1);

		Bson sort = orderBy(ascending("i"), descending("j"));

		List<Document> all = collection.find()
				.projection(projection)
				.sort(sort)
				.skip(20)
				.limit(10)
				.into(new ArrayList<>());

		all.forEach(Helpers::printJson);

		System.out.println("No of docs: #" + all.size());
	}
}
