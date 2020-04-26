package entity;


import dao.exception.DAOException;

public class Subscription  extends Entity {

    private String typeSubscription;




    public String getTypesubscription() {
        return typeSubscription;
    }

    public void setTypesubscription(String typesubscription) {
        this.typeSubscription = typesubscription;
    }


}