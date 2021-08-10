package net.therap.jdbc.domain;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class Enrollment {

    private Trainee trainee;

    private List<Course> courseList;

    public Enrollment(Trainee traineeValue, List<Course> courseListValue) {
        trainee = traineeValue;
        courseList = courseListValue;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee traineeValue) {
        trainee = traineeValue;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourse(List<Course> courseListValue) {
        courseList = courseListValue;
    }
}