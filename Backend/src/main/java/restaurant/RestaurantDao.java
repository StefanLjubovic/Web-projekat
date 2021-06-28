package restaurant;

import user.User;
import util.ModelDao;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

public class RestaurantDao implements ModelDao<Restaurant> {
    private List<Restaurant> restaurants;
    private String filePath="src/main/java/restaurant/Restaurant.json";
    private RestaurantSerialization restaurantSerialization=new RestaurantSerialization();

    public List<Restaurant> GetAllRestaurants(){
        restaurants=getAll();
        Collections.sort(restaurants, (o1, o2) -> Boolean.compare(o1.getStatus(), o2.getStatus()));
        return restaurants;
    }
    public boolean SaveRestaurant(Restaurant restaurant){
        return create(restaurant);
    }

    @Override
    public Boolean create(Restaurant obj) {
        if(objectExists(obj))
            return false;
        restaurants=getAll();
        restaurants.add(obj);
        restaurantSerialization.save(filePath,restaurants);
        return true;
    }

    @Override
    public List<Restaurant> getAll() {
        restaurants=restaurantSerialization.load(filePath);
        return restaurants;
    }

    @Override
    public Restaurant getOne(String id) {
        restaurants=getAll();
        for (Restaurant restaurant: restaurants) {
            if(restaurant.getId().equals(id))
                return restaurant;
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        restaurants=getAll();
        int index = -1;
        for (int i = 0; i < restaurants.size(); i++){
            if(restaurants.get(i).getId().equals(id)){
                index = i;
                break;
            }
        }
        if(index != -1){
            restaurants.remove(index);
            restaurantSerialization.save(filePath,restaurants);
            return true;
        }
        return false;
    }

    @Override
    public Boolean objectExists(Restaurant object) {
        restaurants=getAll();
        for (Restaurant restaurant:restaurants) {
            if(restaurant.getId().equals(object.getId()))
                return true;
        }
        return false;
    }
}
