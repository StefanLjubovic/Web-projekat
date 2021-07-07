package grade;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant.Restaurant;
import util.Serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GradeSerialization extends Serialization<Grade> {
    public List<Grade> load(String filePath) {
        Gson gson=new Gson();
        String json = super.preLoad(filePath);
        List<Grade> grades = new ArrayList<>();
        if(json != null){
            Type listType = new TypeToken<ArrayList<Grade>>(){}.getType();
            grades = gson.fromJson(json,listType);
        }
        return  grades;
    }
}
