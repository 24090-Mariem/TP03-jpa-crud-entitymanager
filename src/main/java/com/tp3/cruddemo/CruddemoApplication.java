
package com.tp3.cruddemo;

import com.tp3.cruddemo.dao.StudentDAO;
import com.tp3.cruddemo.entity.Student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

        return args -> {

            String action = (args.length > 0) ? args[0] : "read";

            switch (action) {

                case "create" -> createStudent(studentDAO);

                case "read" -> readStudents(studentDAO);

                case "update" -> updateStudent(studentDAO);

                case "delete" -> deleteStudent(studentDAO);

                case "search" -> {
                    studentDAO.findByLastName("Doe")
                            .forEach(System.out::println);
                }

                case "deleteAll" -> {
                    int count = studentDAO.deleteAll();
                    System.out.println("Deleted rows: " + count);
                }

                default -> System.out.println("Unknown action");
            }
        };
    }

    // CREATE
    private void createStudent(StudentDAO dao) {

        Student student = new Student(
                "Paul",
                "Doe",
                "paul@test.com"
        );

        dao.save(student);

        System.out.println("Saved student. ID: " + student.getId());
    }

    // READ
    private void readStudents(StudentDAO dao) {

        System.out.println("All students:");

        for (Student student : dao.findAll()) {
            System.out.println(student);
        }
    }

    // UPDATE
    private void updateStudent(StudentDAO dao) {

        Student student = dao.findById(1);

        if (student != null) {

            student.setFirstName("John");

            dao.update(student);

            System.out.println("Updated: " + dao.findById(1));

        } else {
            System.out.println("Student not found");
        }
    }

    // DELETE
    private void deleteStudent(StudentDAO dao) {

        dao.delete(1);

        System.out.println("Student deleted. ID: 1");
    }
}
