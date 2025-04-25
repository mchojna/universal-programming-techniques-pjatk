package Training.Streams;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	public enum Name {UTP, ASD, SKJ, SYC, SBD, SAD}

	private final Name name;
	private final List<Grade> grades;

	public Subject(Name name) {
		this.name = name;
		this.grades = new ArrayList<>();
	}

	public Name getName() {
		return name;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void addGrade(Grade grade){
		this.grades.add(grade);
	}

	@Override
	public String toString() {
		return "Subject{" +
				"name=" + name +
				'}';
	}
}