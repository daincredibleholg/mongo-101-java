package javadriversetup;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import util.Helpers;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

public class DeleteTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("deleteTest");

		collection.drop();

		for (int i = 0; i < 8; i++) {
			collection.insertOne(new Document().append("_id", i));
		}

		collection.deleteMany(gt("_id", 4));

		collection.deleteOne(eq("_id", 2));

		collection.find().into(new ArrayList<>()).forEach(Helpers::printJson);
	}
}
