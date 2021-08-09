package net.therap.jdbc.domain;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class EnrollmentDetailsOutput {

    private HashMap<String, HashSet<String>> enrollmentInfo;

    private HashMap<String, String> traineeInfo;

    private HashMap<String, String> courseInfo;

    public EnrollmentDetailsOutput() {
        enrollmentInfo = new HashMap<>();
        traineeInfo = new HashMap<>();
        courseInfo = new HashMap<>();
    }

    public HashMap<String, HashSet<String>> getEnrollmentInfo() {
        return enrollmentInfo;
    }

    public void setEnrollmentInfo(HashMap<String, HashSet<String>> enrollmentInfoValue) {
        enrollmentInfo = enrollmentInfoValue;
    }

    public HashMap<String, String> getTraineeInfo() {
        return traineeInfo;
    }

    public void setTraineeInfo(HashMap<String, String> traineeInfoValue) {
        traineeInfo = traineeInfoValue;
    }

    public HashMap<String, String> getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(HashMap<String, String> courseInfoValue) {
        courseInfo = courseInfoValue;
    }
}