package user;

public class Reward {
    String name;
    Double discount;
    Double requiredScore;

    public Reward(String name, double discount, double requiredScore) {
        this.name = name;
        this.discount = discount;
        this.requiredScore = requiredScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getRequiredScore() {
        return requiredScore;
    }

    public void setRequiredScore(Double requiredScore) {
        this.requiredScore = requiredScore;
    }
}
