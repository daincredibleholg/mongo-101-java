package javadriversetup;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

public class DocumentTest {

	public void main (String... args) {
		Document document = new Document()
				.append("str", "MongoDB, Hello")
				.append("int", 42)
				.append("l", 1L)
				.append("double", 1.1)
				.append("date", new Date())
				.append("objectId", new ObjectId())
				.append("null", null)
				.append("embeddedDoc", new Document("x", 0))
				.append("list", Arrays.asList(1, 2, 3));

	}
}
