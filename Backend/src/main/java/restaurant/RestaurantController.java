package restaurant;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import user.User;

import java.util.ArrayList;
import java.util.List;
import static server.Server.restaurantDAO;
import static server.Server.userDao;

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
      System.out.println(restaurant.getName());
      restaurant.setStatus(true);
      restaurant.setItems(new ArrayList<>());
      restaurant.setGrade(0.0);
      restaurant.setId(restaurantDAO.generateId());
      User user=userDao.getOne(restaurant.getManagerId());
      user.setRestaurantId(restaurant.getId());
      userDao.update(user);
      return restaurantDAO.SaveRestaurant(restaurant);
    };


    public static Route getSingleRestaurant = (Request req, Response res) -> {
        res.type("application/json");
        System.out.println(req.queryParams("id")+"aaaaa");
        Restaurant restaurant = restaurantDAO.getOne(req.queryParams("id"));
//        restaurant = restaurant.availableItems();
        res.status(200);
        if(restaurant == null){
            res.status(400);
        }
        System.out.println(gson.toJson(restaurant));
        return gson.toJson(restaurant);
    };
    public static Route saveItem = (Request req, Response res) -> {
        System.out.println("Saving item");
        String restaurantId = req.params(":restaurantId");
        System.out.println("Taking restaurant with id: "+ restaurantId);
        String itemJson = req.body();
        Item item = gson.fromJson(itemJson,Item.class);
        Restaurant restaurant = restaurantDAO.getOne(restaurantId);
        restaurant = restaurant.addItem(item);
        restaurantDAO.UpdateRestaurant(restaurant);
        res.status(200);
        return "Ok";
    };

    public static Route updateRestaurant = (Request req, Response res) -> {
        String restaurantJson = req.body();
        Restaurant restaurant = gson.fromJson(restaurantJson, Restaurant.class);
        restaurantDAO.UpdateRestaurant(restaurant);
        res.status(200);
        return "Ok";
    };

}
