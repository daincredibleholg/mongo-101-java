package javadriversetup;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class FindTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("insertTest");

		coll.drop();

		// insert 10 documents
		for (int i = 0; i < 10; i++) {
			coll.insertOne(new Document("x", i));
		}

		System.out.println("Find one: ");
		Document first = coll.find().first();
		System.out.println(first.getInteger("x"));

		System.out.println("Find all with into: ");
		ArrayList<Document> all = coll.find().into(new ArrayList<>());
		for (Document cur : all) {
			System.out.println(cur.getInteger("x"));
		}
		System.out.println("Find all with iteration:");
		try (MongoCursor<Document> cursor = coll.find().iterator()) {
			while (cursor.hasNext()) {
				Document cur = cursor.next();
				System.out.println(cur.getInteger("x"));
			}
		}

		System.out.println("Count: ");
		System.out.println(coll.count());
	}
}
