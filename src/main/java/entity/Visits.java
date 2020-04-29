package entity;

import java.util.Objects;

public class Visits extends Entity {

    private  int trainingId;
    private  int customersId;
    private int coachId;

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getCustomersId() {
        return customersId;
    }

    public void setCustomersId(int customersId) {
        this.customersId = customersId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    @Override
    public String toString() {
        return "Visits{" +
                "id=" + id +
                ", trainingId=" + trainingId +
                ", customersId=" + customersId +
                ", coachId=" + coachId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visits)) return false;
        Visits visits = (Visits) o;
        return getId() == visits.getId() &&
                getTrainingId() == visits.getTrainingId() &&
                getCustomersId() == visits.getCustomersId() &&
                getCoachId() == visits.getCoachId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTrainingId(), getCustomersId(), getCoachId());
    }
}

