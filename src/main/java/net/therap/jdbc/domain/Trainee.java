package net.therap.jdbc.domain;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class Trainee {

    private String traineeId, traineeName;

    public Trainee(String traineeIdValue, String traineeNameValue) {
        traineeId = traineeIdValue;
        traineeName = traineeNameValue;
    }

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeIdValue) {
        traineeId = traineeIdValue;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeNameValue) {
        traineeName = traineeNameValue;
    }
}