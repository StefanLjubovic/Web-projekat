package restaurant;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;
import static server.Server.restaurantDAO;

public class RestaurantController {

    private static Gson gson=new Gson();

    public static Route getAllRestaurants = (Request req,Response res)->{
        List<Restaurant> restaurants=restaurantDAO.GetAllRestaurants();
        res.type("application/json");
        return gson.toJson(restaurants);
    };

    public static Route createRestaurant=(Request req,Response res)->{
      String restaurantJson=req.body();
      Restaurant restaurant=gson.fromJson(restaurantJson,Restaurant.class);
      return restaurantDAO.SaveRestaurant(restaurant);
    };
}
