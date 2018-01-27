package hw.week3;

import com.mongodb.MongoClient;
import hw.week3.model.ScoreType;
import hw.week3.model.Scores;
import hw.week3.model.Student;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

import static hw.week3.model.ScoreType.homework;

public class Homework1 {

	public static void main(String[] args) {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("hw.week3.model");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), "school");
		datastore.ensureIndexes();

		final Query<Student> query = datastore.createQuery(Student.class);
		final List<Student> students = query.asList();

		students.forEach(Homework1::removeLowestHomeworkScore);

		datastore.save(students);
	}

	private static void removeLowestHomeworkScore(Student student) {
		student.getScores().removeIf(s ->
				s.getScore() == getLowestScoreFor(homework, student));

	}

	private static double getLowestScoreFor(ScoreType type, Student student) {
		return student.getScores().stream()
				.filter(score -> score.getType().equals(type))
				.map(Scores::getScore)
				.min(Double::compare)
				.orElse(0.0);
	}


}
