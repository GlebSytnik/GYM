package entity;

import java.util.Objects;

public class Coach extends Entity  {

    private String qualification;
    private int userDetailsId;
    private int timetablesId;


    public int getUserDetailsId() {
        return userDetailsId;
    }

    public void setuserdetailsId(int userdetailsId) {
        this.userDetailsId = userdetailsId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getTimetablesid() {
        return timetablesId;
    }

    public void setTimetables_id(int timetablesId) {
        this.timetablesId = timetablesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return getId() == coach.getId() &&
                userDetailsId == coach.userDetailsId &&
                timetablesId == coach.timetablesId &&
                Objects.equals(getQualification(), coach.getQualification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQualification(), userDetailsId, timetablesId);
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", qualification='" + qualification + '\'' +
                ", userdetailsId=" + userDetailsId +
                ", timetablesId=" + timetablesId +
                '}';
    }


}

