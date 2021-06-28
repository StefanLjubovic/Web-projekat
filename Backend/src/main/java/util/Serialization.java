package util;

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

public class Serialization<T> {
    public void save(String location,List<T> entities){
        Gson gson=new Gson();
        try {
            FileWriter fileWriter=new FileWriter(location);
            String json=gson.toJson(entities);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public List<T> load(String location){
        Gson gson=new Gson();
        ArrayList<T> loadedEntities = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(location)));
            Type listType=new TypeToken<ArrayList<User>>(){}.getType();
            loadedEntities=gson.fromJson(json,listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedEntities;
    }
}
