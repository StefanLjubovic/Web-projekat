package cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant.Restaurant;
import util.Serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CartSerialization extends Serialization<Cart> {
    public List<Cart> load(String filePath) {
        Gson gson=new Gson();
        String json = super.preLoad(filePath);
        List<Cart> carts = new ArrayList<>();
        if(json != null){
            Type listType = new TypeToken<ArrayList<Cart>>(){}.getType();
            carts = gson.fromJson(json,listType);
        }
        return  carts;
    }
}

