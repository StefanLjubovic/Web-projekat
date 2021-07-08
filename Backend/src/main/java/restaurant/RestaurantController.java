package restaurant;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;
import static server.Server.restaurantDAO;

public class RestaurantController {



    private static Gson gson=new Gson();

    public static Route getAllRestaurants = (Request req,Response res) -> {
        List<Restaurant> restaurants = restaurantDAO.GetAllRestaurants();
        res.type("application/json");
        return gson.toJson(restaurants);
    };

    public static Route createRestaurant=(Request req,Response res)->{
      String restaurantJson = req.body();
      System.out.println(restaurantJson);
      Restaurant restaurant = gson.fromJson(restaurantJson,Restaurant.class);
      restaurant.setStatus(true);
      restaurant.setItems(new ArrayList<>());
      restaurant.setGrade(0.0);
      restaurant.setId(restaurantDAO.generateId());
      return restaurantDAO.SaveRestaurant(restaurant);
    };


    public static Route getSingleRestaurant = (Request req, Response res) -> {
        res.type("application/json");
        Restaurant restaurant = restaurantDAO.getOne(req.queryParams("id"));
        res.status(200);
        if(restaurant == null){
            res.status(400);
        }
        return gson.toJson(restaurant);
    };

}
