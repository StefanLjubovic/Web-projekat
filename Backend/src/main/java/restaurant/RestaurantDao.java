package restaurant;

import user.User;
import util.ModelDao;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import static server.Server.restaurantDAO;

public class RestaurantDao implements ModelDao<Restaurant> {
    private List<Restaurant> restaurants;
    private String filePath="src/main/java/restaurant/Restaurant.json";
    private RestaurantSerialization restaurantSerialization=new RestaurantSerialization();
    public RestaurantDao(){
        restaurants = restaurantSerialization.load(filePath);
    }
    public List<Restaurant> GetAllRestaurants(){
        List<Restaurant> _restaurants = restaurants;
        Collections.sort(_restaurants, (o1, o2) -> Boolean.compare(o1.getStatus(), o2.getStatus()));
        return _restaurants;
    }
    public boolean SaveRestaurant(Restaurant restaurant){
        return create(restaurant);
    }

    @Override
    public Boolean create(Restaurant obj) {
        if(objectExists(obj))
            return false;
        restaurants.add(obj);
        restaurantSerialization.save(filePath,restaurants);
        return true;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurants;
    }

    @Override
    public Restaurant getOne(String id) {
        System.out.println("Search for "+id);
        for (Restaurant restaurant: restaurants) {
            if(restaurant.getId().equals(id))
                return restaurant;
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        for (int i = 0; i < restaurants.size(); i++){
            if(restaurants.get(i).getId().equals(id)){
                restaurants.remove(i);
                restaurantSerialization.save(filePath,restaurants);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean objectExists(Restaurant object) {
        for (Restaurant restaurant:restaurants) {
            if(restaurant.getId().equals(object.getId()))
                return true;
        }
        return false;
    }

    public String generateId() {
        int nextId = 1;
        for(Restaurant restaurant: restaurants){
            int restaurantId = Integer.parseInt(restaurant.getId());
            if(restaurantId > nextId){
                nextId = restaurantId + 1;
            }
        }
        return  "" + nextId;
    }
}
