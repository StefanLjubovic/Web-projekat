package grade;

import java.util.Date;

public class Grade {
    private String id;
    private String userId;
    private String restaurantId;
    private String comment;
    private int grade;
    private Long date;
    private Status approved;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Grade(String userId, String restaurantId, String comment, int grade) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.comment = comment;
        this.grade = grade;
        approved=Status.WaitingForApproval;
    }

    public Grade(String id, String userId, String restaurantId, String comment, int grade, Long date, String approved) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.comment = comment;
        this.grade = grade;
        this.date = date;
        if(approved =="Approved")
            this.approved=Status.Approved;
        else if(approved=="Rejected")
            this.approved=Status.Rejected;
        else
            this.approved=Status.WaitingForApproval;
    }

    public Status isApproved() {
        return approved;
    }

    public void setApproved(Status approved) {
        this.approved = approved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
