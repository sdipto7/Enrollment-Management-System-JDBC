package net.therap.jdbc.domain;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class Trainee {

    private int traineeId;

    private String traineeName;

    public Trainee(int traineeIdValue, String traineeNameValue) {
        traineeId = traineeIdValue;
        traineeName = traineeNameValue;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeIdValue) {
        traineeId = traineeIdValue;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeNameValue) {
        traineeName = traineeNameValue;
    }
}