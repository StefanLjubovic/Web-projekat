package user;

import buyerType.BuyerType;
import order.Order;

import java.util.Date;
import java.util.List;


public class User{
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String gender;
    private double dateOfBirth;
    private UserRoles role;
    private Integer restaurantId;
    private String cartId;
    private boolean status=true;
    private Double collectedPoints=0.0;

    public User(String id, String firstName, String lastName, String username, String password, String gender, Integer dateOfBirth, UserRoles role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public User(String firstName, String lastName, String username, String password, String gender, Integer dateOfBirth, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        if(role.equals("Customer"))
            this.role = UserRoles.Customer;
        else if(role.equals("Manager")){
            this.role = UserRoles.Manager;
        }else{
            this.role = UserRoles.Deliverer;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private BuyerType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(double dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public BuyerType getType() {
        return type;
    }

    public void setType(BuyerType type) {
        this.type = type;
    }
}
