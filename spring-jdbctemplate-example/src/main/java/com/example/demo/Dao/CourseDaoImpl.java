package com.example.demo.Dao;

import com.example.demo.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@SuppressWarnings("deprecation")
@Component
public class CourseDaoImpl  implements CourseDao<Course>{

    private static final Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<Course> rowMapper = (rs, rowNum) -> {
            Course course = new Course();
            course.setCourse_id(rs.getInt("course_id"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            return course;
        };
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> list() {
        String query = "Select * From course";
        return jdbcTemplate.query(query,rowMapper);
    }

     @Override
    public void create( Course course) {
        String query = "Insert Into course(title,description) Values(?,?)";
        int insert = jdbcTemplate.update(query,course.getTitle(),course.getDescription());
        if (insert==1){
            log.info("New course created  " +course.getTitle());
        }
    }
    @Override
    public Optional<Course> get(int id) {
        String query = "Select  *  From course Where course_id= ?";
        Course course= null;
        try {
            course = jdbcTemplate.queryForObject(query,new Object[]{id},rowMapper);
        }catch (DataAccessException e){
            log.info("Course not found:"+id);
        }
        return Optional.ofNullable(course);
    }
    @Override
    public void update(Course course, int id) {
        String query = "Update course SET title=? ,description=? where course_id = ?";
        int update = jdbcTemplate.update(query,course.getTitle(),course.getDescription(),id);
        if (update==1){
            log.info("Course updated :"+course.getTitle());
        }
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from course where course_id = ? ",id);

    }
}

