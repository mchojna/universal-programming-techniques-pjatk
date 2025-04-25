package Training.Streams;

import java.util.*;

public class Student {
	private final String name;
	private final Map<Subject.Name,Subject> subjects;

	public Student(String name) {
		this.name = name;
		subjects = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public List<Subject> getSubjects() {
		return new ArrayList<>(subjects.values());	}

	public void addSubject(Subject subcject) { //TODO Same subject should not be added twice
		this.subjects.put(subcject.getName(), subcject);
	}

	public void addGrade(Subject.Name subjectName, Grade grade) { //TODO what happens if Student does not have this subject?
		subjects.get(subjectName).addGrade(grade);
	}

	@Override
	public String toString() {
		return name;
	}
}
