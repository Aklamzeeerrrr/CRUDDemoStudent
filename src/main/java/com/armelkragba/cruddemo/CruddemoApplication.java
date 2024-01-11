package com.armelkragba.cruddemo;

import com.armelkragba.cruddemo.dao.StudentDAO;
import com.armelkragba.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{
			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students");

		int numRowsDeleted = studentDAO.deleteAll();

		System.out.println("Delete row count: "+ numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 2;
		System.out.println("Deleting student id: " + studentId);

		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting steudent with id: "+ studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "Scooby"
		System.out.println("Updating student ...");
		myStudent.setFirstName("Scooby");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: "+ myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Dude");

		//display list of students
		theStudents.forEach(System.out::println);
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findAll();

		//display list of students
		theStudents.forEach(System.out::println);

	}

	private void readStudent(StudentDAO studentDAO) {

		//create a student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Daff", "Dude", "daff@gmail.com");

		//save the student

		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		//display id of the saved student

		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id : " + theId);

		//retrieve student based on the id : primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("The student with id : " + theId + " is: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create the student objet
		System.out.println("Creating 3 students object...");

		List<Student> students = Stream.of(
				new Student("Joe", "Doe", "joe@gmail.com"),
				new Student("Mel", "Pli", "mel@gmail.com"),
				new Student("mook", "Daak", "mook@gmail.com")
		).toList();

		//save the student object
		System.out.println("Saving the student...");
		students.forEach(studentDAO::save);
	}

	private void createStudent(StudentDAO studentDAO) {

		//create the student objet
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@gmail.com");

		//save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id : "+ tempStudent.getId());
	}

}
