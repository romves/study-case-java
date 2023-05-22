package model.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member extends User{
    private String memberName;
    private Date joinedDate;

    public Member(String id, String memberName, String joinedDate, double balance) {
        super(id, balance);
        this.memberName = memberName;
        this.joinedDate =  convertStringToDate(joinedDate);
    }

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter a date in yyyy/MM/dd format.");
            return null;
        }
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberAge() {
        return 0;
    }


}


