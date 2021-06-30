package restaurant;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import user.User;
import util.Path;
import util.Serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RestaurantSerialization extends Serialization<Restaurant> {
    public List<Restaurant> load(String filePath) {
        Gson gson=new Gson();
        String json = super.preLoad(filePath);
        List<Restaurant> restaurants = new ArrayList<>();
        if(json != null){
            Type listType = new TypeToken<ArrayList<Restaurant>>(){}.getType();
            restaurants = gson.fromJson(json,listType);
        }
        return  restaurants;
    }
}



