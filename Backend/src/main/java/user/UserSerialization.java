package user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import util.Serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserSerialization extends Serialization<User>{

    public List<User> load(String location) {
        Gson gson=new Gson();
        String json = super.preLoad(location);
        List<User> users = new ArrayList<>();
        if(json != null){
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();

            users = gson.fromJson(json,listType);
        }
        return  users;
    }
}
