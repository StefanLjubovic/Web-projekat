package server;

import restaurant.RestaurantController;
import restaurant.RestaurantDao;
import user.UserController;
import user.UserDao;
import util.Path;

import static spark.Spark.*;
import static spark.Spark.port;

public class Server {

    public static UserDao userDao;
    public static RestaurantDao restaurantDAO;

    public static void main(String[] args) {
        port(8080);
        restaurantDAO=new RestaurantDao();
        userDao=new UserDao();

        get(Path.Web.INDEX, RestaurantController.getAllRestaurants);
        post(Path.Web.REGISTER_RESTUARANT, RestaurantController.createRestaurant);
        get(Path.Web.GET_AVAILABLE_MANAGERS, UserController.getAvailableManagers);
    }
}
