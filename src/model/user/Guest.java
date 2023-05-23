package model.user;

public class Guest extends User{

    public Guest (String id, double balance){
        super(id, balance);
        this.userName = "Guest";
    }
}
