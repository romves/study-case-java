package model.user;

public class Guest extends User{
    private String userName;
    public Guest (String id, double balance){
        super(id, balance);
        this.userName = "Guest";
    }



    @Override
    public void generateReceipt() {


    }
}
