package hw.week3.model;

import lombok.Data;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

@Entity("students")
@Data
public class Student {

	@Id
	private long id;

	private String name;

	@Embedded
	private List<Scores> scores;
}
