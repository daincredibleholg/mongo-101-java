package setup;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarkersetup.HelloWorldFreemarkerStyle;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class HelloWorldFreemarkerSparkStyle {


	public static void main(String... args) throws IOException, TemplateException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

		Template helloTemplate = configuration.getTemplate("hello.ftl");
		StringWriter writer = new StringWriter();
		Map<String, Object> helloMap = new HashMap<>();
		helloMap.put("name", "Freemarker");

		helloTemplate.process(helloMap, writer);

		get("/", (req, resp) ->  writer.toString());
	}


}
