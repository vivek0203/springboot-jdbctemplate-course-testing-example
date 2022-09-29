package com.example.demo.Dao;

import com.example.demo.CourseApplication;
import com.example.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest(classes = CourseApplication.class)
@ExtendWith(SpringExtension.class)

class CourseDaoTest {

    private CourseDaoImpl dao;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoTest(CourseDaoImpl dao, JdbcTemplate jdbcTemplate) {
        this.dao = dao;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Test
    void listCourses_ShouldReturnAllCourses() {
        List<Course> courseList = dao.list();

        assertEquals(5,courseList.size());
    }
    @Test
    void validCourse_ShouldBeCreated() {
        Course course = new Course("test title","test description");
        dao.create(course);

        List<Course> courseList =dao.list();
        assertEquals(6,courseList.size());
        assertEquals("test title",courseList.get(5).getTitle());
        assertEquals("test description",courseList.get(5).getDescription());
    }
    @Test
    void getCourseWithValidId_ShouldReturnEmptyOptional() {
        Optional<Course> courseOptional = dao.get(99);
        assertFalse(courseOptional.isPresent());
    }
    @Test
    void updateCourse_ShouldBeUpdated() {
        Course course = dao.get(1).get();
        course.setTitle("Java Complete Masterclass");
        dao.update(course,1);

        Course updatedCourse = dao.get(1).get();
        assertEquals("Java Complete Masterclass",updatedCourse.getTitle());
    }
    @Test
    void deleteCourse_ShouldRemoveCourse() {
        dao.delete(1);
        List<Course> courseList = dao.list();

        assertEquals(5,courseList.size());
    }
}