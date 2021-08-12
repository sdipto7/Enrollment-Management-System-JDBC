package net.therap.jdbc.domain;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class Enrollment implements Comparable<Enrollment> {

    private Trainee trainee;

    private Course course;

    public Enrollment(Trainee traineeValue, Course courseValue) {
        trainee = traineeValue;
        course = courseValue;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee traineeValue) {
        trainee = traineeValue;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course courseValue) {
        course = courseValue;
    }

    @Override
    public int compareTo(Enrollment enrollment) {
        return this.getTrainee().getTraineeId() - enrollment.getTrainee().getTraineeId();
    }
}