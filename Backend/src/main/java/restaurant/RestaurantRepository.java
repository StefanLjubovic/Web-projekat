package restaurant;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import user.User;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private String filePath="src/main/java/restaurant/Restaurant.json";

    public void Serialize(List<Restaurant> restaurants){
        Gson gson=new Gson();
        try {
            FileWriter fileWriter=new FileWriter(filePath);
            String json=gson.toJson(restaurants);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Save(Restaurant restaurant){
        List<Restaurant> restaurants=GetAll();
        restaurants.add(restaurant);
        Serialize(restaurants);
    }

    public List<Restaurant> GetAll(){
        Gson gson=new Gson();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Type listType=new TypeToken<ArrayList<Restaurant>>(){}.getType();
            restaurants=gson.fromJson(json,listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public void Delete(String id){
        List<Restaurant> restaurants=GetAll();
        Restaurant restaurantsToBeRemoved = null;
        for(Restaurant restaurant:restaurants) {
            if(restaurant.getId().equals(id)){
                restaurantsToBeRemoved=restaurant;
                break;
            }
        }
        restaurants.remove(restaurantsToBeRemoved);
        Serialize(restaurants);
    }
    public Restaurant GetById(String id){
        List<Restaurant> restaurants=GetAll();
        for(Restaurant restaurant:restaurants) {
            if(restaurant.getId().equals(id)){
                return restaurant;
            }
        }
        return null;
    }
}
