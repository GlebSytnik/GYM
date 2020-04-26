package entity;


import dao.exception.DAOException;

import java.util.Objects;

public class Customers extends Entity  {
    private  int id;
    private  int height;
    private  int weight;
    private  int subscriptionId;
    private  int userDetailsId;
    private  int coachId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserdetailsId(int userdetailsId) {
        this.userDetailsId = userdetailsId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", height=" + height +
                ", weight=" + weight +
                ", subscriptionId=" + subscriptionId +
                ", userdetailsId=" + userDetailsId +
                ", coachId=" + coachId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers)) return false;
        Customers customers = (Customers) o;
        return getId() == customers.getId() &&
                getHeight() == customers.getHeight() &&
                getWeight() == customers.getWeight() &&
                getSubscriptionId() == customers.getSubscriptionId() &&
                getUserDetailsId() == customers.getUserDetailsId() &&
                getCoachId() == customers.getCoachId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeight(), getWeight(), getSubscriptionId(), getUserDetailsId(), getCoachId());
    }


}

