package user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static server.Server.userDao;

import java.util.UUID;
public class UserController  {


    private static Gson gson=new Gson();
    private static Map<String, String> loginTrack = new HashMap<>();

    public static Route getAvailableManagers = (Request req, Response res)-> {
        res.type("application/json");
        List<User> users=userDao.getAvailableManagers();
        return gson.toJson(users);
    };
    class LoginRequest {
        String username;
        String password;
    }

    public static Route Login = (Request req, Response res)-> {

        Map<String, Object> model = new HashMap<>();
        String yourObjectStr = "" + req.body();
        Gson gson = new GsonBuilder().create();
        LoginRequest requestData = gson.fromJson(yourObjectStr , LoginRequest.class);
        User user = authorize(requestData.username, requestData.password);
        if(user != null){
            String loginToken = UUID.randomUUID().toString();
            loginTrack.put(loginToken, user.getId());
            model.put("loginToken", loginToken);
            model.put("user", gson.toJson(user));
            res.body("Ide gas");
            res.status(200);
        }else{
            model.put("errorMessage", "User doesn't exist or email/password is not correct");
            res.body(gson.toJson(model));
            res.status(400);
        }
        return gson.toJson(model);
    };


    public static Route getUserByToken = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        String token = req.queryParams("token");
        System.out.println("Searching for user: "+token);
        if(loginTrack.containsKey(token)){
            String userId = loginTrack.get(token);
            User user = userDao.getOne(userId);
            String loginToken = UUID.randomUUID().toString();
            loginTrack.put(loginToken, user.getId());
            model.put("loginToken", loginToken);
            model.put("user", gson.toJson(user));
            res.status(200);
        }else{
            model.put("errorMessage", "Invalid user token");
            res.body(gson.toJson(model));
            res.status(400);
        }
        return gson.toJson(model);
    };

    public static Route updateUser=(Request req,Response res)->{
        String yourObjectStr = "" + req.body();
        System.out.println(yourObjectStr);
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(yourObjectStr , User.class);
        return userDao.update(user);
    };

    public static Route getAllUsers=(Request req,Response res)->{
        return gson.toJson(userDao.getAll());
    };

    public static User authorize(String username, String password){
        username = username.toLowerCase(Locale.ROOT);
        List<User> users = userDao.getAll();
        System.out.println(users.get(0).getClass());
        for (User user: users) {
            String _username = user.getUsername();
            if(_username.equals(username)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public static Route createUser=(Request req,Response res)->{
        String yourObjectStr = "" + req.body();
        System.out.println(yourObjectStr);
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(yourObjectStr , User.class);
        String uniqueID = UUID.randomUUID().toString();
        user.setId(uniqueID);
        if(authorize(user.getUsername(),user.getPassword())==null){
            userDao.create(user);
        }
        return gson.toJson(user);
    };

}
