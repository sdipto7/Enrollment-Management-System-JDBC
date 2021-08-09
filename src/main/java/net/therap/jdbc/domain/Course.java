package net.therap.jdbc.domain;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class Course {

    private String courseCode, courseTitle;

    public Course(String courseCodeValue, String courseTitleValue) {
        courseCode = courseCodeValue;
        courseTitle = courseTitleValue;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCodeValue) {
        courseCode = courseCodeValue;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitleValue) {
        courseTitle = courseTitleValue;
    }
}