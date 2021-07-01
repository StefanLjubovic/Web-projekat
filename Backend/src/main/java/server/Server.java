package server;

import restaurant.RestaurantController;
import restaurant.RestaurantDao;
import spark.Filter;
import spark.Request;
import spark.Response;
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


//        post(Path.Web.LOGIN, UserController.Login); //Login
       // post("/login", (req, resp) -> {
       //     return "Ide gas";
       // });

        get(Path.Web.RESTAURANT, RestaurantController.getAllRestaurants);
        get(Path.Web.GET_AVAILABLE_MANAGERS, UserController.getAvailableManagers);
        post(Path.Web.CREATE_USER, UserController.createUser);

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST ,DELETE");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
            response.header("Content-Encoding", "gzip");
        });
//        after((request, response) -> response.type("application/json"));
    }

}
