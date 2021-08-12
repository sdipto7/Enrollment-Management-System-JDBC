package net.therap.jdbc.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Trainee trainee = (Trainee) object;

        return this.getTraineeId() == trainee.getTraineeId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getTraineeName());
    }
}