package user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private String filePath="src/main/java/user/User.json";

    public void Serialize(List<User> users){
        Gson gson=new Gson();
        Path currentRelativePath = Paths.get("");
        try {
            FileWriter fileWriter=new FileWriter(filePath);
            String json=gson.toJson(users);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Save(User user){
        List<User> users=GetAll();
        users.add(user);
        Serialize(users);
    }

    public List<User> GetAll(){
        Gson gson=new Gson();
        ArrayList<User> loadedUsers = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Type listType=new TypeToken<ArrayList<User>>(){}.getType();
            loadedUsers=gson.fromJson(json,listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedUsers;
    }

    public void Delete(String id){
        List<User> users=GetAll();
        User userToBeRemoved = null;
        for(User user:users) {
            if(user.getId().equals(id)){
                userToBeRemoved=user;
                break;
            }
        }
        users.remove(userToBeRemoved);
        Serialize(users);
    }
    public User GetById(String id){
        List<User> users=GetAll();
        for(User user:users) {
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

}
