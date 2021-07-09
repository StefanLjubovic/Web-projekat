package grade;

import java.util.Date;

public class Grade {
    private String id;
    private String userId;
    private String restaurantId;
    private String comment;
    private int grade;
    private Long date;
    private boolean approved=false;

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
    }

    public Grade(String id, String userId, String restaurantId, String comment, int grade, Long date, boolean approved) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.comment = comment;
        this.grade = grade;
        this.date = date;
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
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
