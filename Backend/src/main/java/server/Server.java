package server;

import restaurant.RestaurantController;
import restaurant.RestaurantDao;
import spark.Filter;
import spark.Request;
import spark.Response;
import user.UserController;
import user.UserDao;
import util.Path;
import util.Upload;

import java.io.File;

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
//        uploadDir = new File("upload");
        File uploadDir =  new File("upload");
        uploadDir.mkdir();
        staticFiles.externalLocation(uploadDir.toString());

        before((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "PUT,GET,POST,DELETE");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
//            response.header("Content-Encoding", "gzip");
        });
        post(Path.Web.LOGIN, UserController.Login); //Login
        get(Path.Web.GET_USER_BY_TOKEN, UserController.getUserByToken);

        get(Path.Web.RESTAURANT, RestaurantController.getAllRestaurants);
        post(Path.Web.CREATE_RESTAURANT, RestaurantController.createRestaurant);
        get(Path.Web.GET_AVAILABLE_MANAGERS, UserController.getAvailableManagers);
        get(Path.Web.GET_SINGLE_RESTAURANT, RestaurantController.getSingleRestaurant);
        post(Path.Web.CREATE_USER, UserController.createUser);

        post(Path.Web.UPLOAD_IMAGE, Upload.uploadImage);
//        after((request, response) -> response.type("application/json"));
    }

}
