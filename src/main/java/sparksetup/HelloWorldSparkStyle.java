package sparksetup;


import static spark.Spark.get;

public class HelloWorldSparkStyle {

	public static void main(String... args) {
		get("/", (req, resp) ->  "Hello World From Spark");
	}
}
