package com.example.demo;

import com.example.demo.Dao.CourseDao;
import com.example.demo.entity.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CourseApplication {

	private static CourseDao<Course> dao;

	public CourseApplication(CourseDao<Course>dao) {
		this.dao =dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);

		System.out.println("/n One Course --------------------------- ");
		Optional<Course> firstOne = dao.get(1);
		System.out.println(firstOne.get());

		dao.delete(4);

		System.out.println("/n Create courses------------------------  ");
		Course course1 = new Course("Spring Boot Jpa Introduction", "New course");
		dao.create(course1);

		course1.setDescription("Learn  how to build application using spring data jpa");
		dao.update(course1, 6);

		System.out.println("/n All courses--------------------------- /n");
		List<Course> courses2 = dao.list();
		courses2.forEach(System.out::println);
	}


}
