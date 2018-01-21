package hw.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import util.Helpers;

import java.util.ArrayList;
import java.util.Comparator;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;
import static java.util.stream.Collectors.groupingBy;

public class HomeWork3 {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("students");
		MongoCollection<Document> collection = db.getCollection("grades");

		Bson order = orderBy(ascending("student_id"), ascending("score"));
		Bson filter = eq("type", "homework");

		collection.find(filter).sort(order).into(new ArrayList<>())
				.stream().collect(
				groupingBy(doc -> doc.getInteger("student_id"))).values()
				.forEach(user -> user.stream()
						.min(Comparator.comparingDouble(a -> a.getDouble("score")))
						// RUN THIS ONLY ONCE!
//						.ifPresent(collection::deleteOne));
						.ifPresent(Helpers::printJson));
	}

}
