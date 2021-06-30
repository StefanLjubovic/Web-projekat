package server;

import restaurant.RestaurantController;
import restaurant.RestaurantDao;
import spark.Filter;
import user.UserController;
import user.UserDao;
import util.Path;

import static spark.Spark.*;
import static spark.Spark.port;
import static util.Filters.correctSplashes;

public class Server {

    public static UserDao userDao;
    public static RestaurantDao restaurantDAO;

    public static void main(String[] args) {
        port(8080);
        restaurantDAO = new RestaurantDao();
        userDao = new UserDao();


        post(Path.Web.LOGIN+"/", UserController.Login); //Login
        post(Path.Web.REGISTER_RESTAURANT, RestaurantController.createRestaurant);
        get(Path.Web.GET_AVAILABLE_MANAGERS, UserController.getAvailableManagers);

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
//            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        });
    }

}
