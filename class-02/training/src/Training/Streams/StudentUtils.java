package Training.Streams;

import java.util.ArrayList;
import java.util.List;

public class StudentUtils {
	public static List<Student> getSomeStudents() {
		List<Student> studentList = new ArrayList<>();

		Student student1 = new Student("Franek");
		studentList.add(student1);
		student1.addSubject(new Subject(Subject.Name.UTP));
		student1.addSubject(new Subject(Subject.Name.SAD));
		student1.addSubject(new Subject(Subject.Name.ASD));
		student1.addSubject(new Subject(Subject.Name.SYC));

		student1.addGrade(Subject.Name.UTP, Grade.THREE);
		student1.addGrade(Subject.Name.UTP, Grade.THREE_PLUS);
		student1.addGrade(Subject.Name.UTP, Grade.FOUR);
		student1.addGrade(Subject.Name.UTP, Grade.FIVE);

		student1.addGrade(Subject.Name.SAD, Grade.THREE);
		student1.addGrade(Subject.Name.SAD, Grade.THREE_PLUS);
		student1.addGrade(Subject.Name.SAD, Grade.FOUR_PLUS);
		student1.addGrade(Subject.Name.SAD, Grade.FIVE);

		student1.addGrade(Subject.Name.ASD, Grade.FIVE);
		student1.addGrade(Subject.Name.ASD, Grade.FIVE);
		student1.addGrade(Subject.Name.ASD, Grade.FOUR_PLUS);
		student1.addGrade(Subject.Name.ASD, Grade.FIVE);

		Student student2 = new Student("Maria");
		studentList.add(student2);
		student2.addSubject(new Subject(Subject.Name.UTP));
		student2.addSubject(new Subject(Subject.Name.SAD));
		student2.addSubject(new Subject(Subject.Name.ASD));

		student2.addGrade(Subject.Name.UTP, Grade.FOUR);
		student2.addGrade(Subject.Name.UTP, Grade.FOUR);
		student2.addGrade(Subject.Name.UTP, Grade.FOUR);
		student2.addGrade(Subject.Name.UTP, Grade.FIVE);

		student2.addGrade(Subject.Name.SAD, Grade.THREE);
		student2.addGrade(Subject.Name.SAD, Grade.THREE_PLUS);
		student2.addGrade(Subject.Name.SAD, Grade.FOUR_PLUS);
		student2.addGrade(Subject.Name.SAD, Grade.FIVE);

		student2.addGrade(Subject.Name.ASD, Grade.FIVE);
		student2.addGrade(Subject.Name.ASD, Grade.FIVE);
		student2.addGrade(Subject.Name.ASD, Grade.FOUR_PLUS);
		student2.addGrade(Subject.Name.ASD, Grade.FIVE);

		Student student4 = new Student("Maria");
		studentList.add(student4);
		student4.addSubject(new Subject(Subject.Name.UTP));
		student4.addSubject(new Subject(Subject.Name.SAD));
		student4.addSubject(new Subject(Subject.Name.SBD));
		student4.addSubject(new Subject(Subject.Name.ASD));

		student4.addGrade(Subject.Name.UTP, Grade.FOUR);
		student4.addGrade(Subject.Name.UTP, Grade.FOUR);
		student4.addGrade(Subject.Name.UTP, Grade.FOUR);
		student4.addGrade(Subject.Name.UTP, Grade.FIVE);
		student4.addGrade(Subject.Name.UTP, Grade.FIVE);

		student4.addGrade(Subject.Name.SAD, Grade.THREE);
		student4.addGrade(Subject.Name.SAD, Grade.THREE_PLUS);
		student4.addGrade(Subject.Name.SAD, Grade.FOUR_PLUS);
		student4.addGrade(Subject.Name.SAD, Grade.FOUR_PLUS);
		student4.addGrade(Subject.Name.SAD, Grade.FIVE);

		student4.addGrade(Subject.Name.ASD, Grade.FIVE);
		student4.addGrade(Subject.Name.ASD, Grade.FIVE);
		student4.addGrade(Subject.Name.ASD, Grade.FOUR_PLUS);
		student4.addGrade(Subject.Name.ASD, Grade.FIVE);
		student4.addGrade(Subject.Name.ASD, Grade.FIVE);




		return studentList;

	}
}
