package order;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import grade.Grade;
import util.Serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderSerialization extends Serialization<Order> {
    public List<Order> load(String filePath) {
        Gson gson=new Gson();
        String json = super.preLoad(filePath);
        List<Order> orders = new ArrayList<>();
        if(json != null){
            Type listType = new TypeToken<ArrayList<Order>>(){}.getType();
            orders = gson.fromJson(json,listType);
        }
        return  orders;
    }
}
