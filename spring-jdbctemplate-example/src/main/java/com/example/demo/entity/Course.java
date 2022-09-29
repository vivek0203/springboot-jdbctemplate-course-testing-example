package com.example.demo.entity;

public class Course {
    private int course_id;
    private String title;
    private String description;



    public Course() {}

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course(String title, String description) {
        this.course_id = course_id;
        this.title = title;
        this.description = description;


    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}




