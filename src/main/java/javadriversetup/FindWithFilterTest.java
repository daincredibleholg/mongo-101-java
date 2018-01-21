package javadriversetup;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;

public class FindWithFilterTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

		collection.drop();

		// insert 10 documents
		for (int i = 0; i < 10; i++) {
			collection.insertOne(new Document()
					.append("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100)));
		}

//		Bson filter = new Document("x", 0)
//				.append("y", new Document("$gt", 10).append("$lt", 90));

		Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));
		List<Document> all = collection.find(filter).into(new ArrayList<>());

		all.forEach(doc -> System.out.println("x: " + doc.getInteger("x") + ", y: " + doc.getInteger("y")));

		long count = collection.count(filter);
		System.out.println();
		System.out.println("Count: " + count);
	}
}
