package net.therap.jdbc.domain;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class EnrollmentDetails {

    private String traineeName, traineeId, courseCode, courseTitle;

    public EnrollmentDetails() {
        traineeId = "";
        traineeName = "";
        courseCode = "";
        courseTitle = "";
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeNameValue) {
        traineeName = traineeNameValue;
    }

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeIdValue) {
        traineeId = traineeIdValue;
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