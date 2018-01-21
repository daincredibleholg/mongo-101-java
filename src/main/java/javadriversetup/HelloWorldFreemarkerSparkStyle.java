package javadriversetup;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarkersetup.HelloWorldFreemarkerStyle;
import org.bson.Document;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class HelloWorldFreemarkerSparkStyle {


	public static void main(String... args) throws IOException, TemplateException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("updateTest");

		collection.drop();

		collection.insertOne(new Document("name", "MongoDB"));

		Template helloTemplate = configuration.getTemplate("hello.ftl");
		StringWriter writer = new StringWriter();
		helloTemplate.process(collection.find().first(), writer);

		get("/", (req, resp) ->  writer.toString());
	}


}
