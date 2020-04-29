package entity;

import java.util.Objects;

public class Training extends Entity {

    private String typeTraining;

    public String getTypeTraining() {
        return typeTraining;
    }

    public void setTypeTraining(String typeTraining) {
        this.typeTraining = typeTraining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Training)) return false;
        Training training = (Training) o;
        return getId() == training.getId() &&
                Objects.equals(getTypeTraining(), training.getTypeTraining());
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", typeTraining='" + typeTraining + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTypeTraining());
    }


}