package user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.*;

import static server.Server.userDao;
import static spark.Spark.halt;

public class UserController  {


    public static Gson gson=new Gson();

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
            if(!user.getStatus())
                halt(400,"User is banned!");
            res.status(200);
        }else{
            System.out.println("User doesn't exist or email/password is not correct");
            model.put("errorMessage", "User doesn't exist or email/password is not correct");
            res.body(gson.toJson(model));
            res.status(400);
            halt(400,"User doesn't exist or email/password is not correct");
        }
        return gson.toJson(model);
    };

    public static Route changePassword=(Request req,Response res)->{
        String yourObjectStr = "" + req.body();
        Gson gson = new GsonBuilder().create();
        LoginRequest requestData = gson.fromJson(yourObjectStr , LoginRequest.class);
        User user=userDao.getByUsername(requestData.username);
        user.setPassword(requestData.password);
        return userDao.update(user);
    };
    public static Route getSingleManager = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        String id = req.queryParams("id");
        User user = userDao.getOne(id);
        res.status(200);
        if(user == null){
            halt(400,"This user doesn't exist");
        }
        return gson.toJson(user);
    };
    public static Route getUserByToken = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        String token = req.queryParams("token");
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

        for (User user: users) {
            String _username = user.getUsername();
            if(_username.equals(username)){
                System.out.println("Username is valid! " + user.getPassword());
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
        user.setCollectedPoints(0.0);
        System.out.println(user.getRole());
        String uniqueID = UUID.randomUUID().toString();
        user.setId(uniqueID);
        if(authorize(user.getUsername(),user.getPassword())==null){
            userDao.create(user);
        }
        return gson.toJson(user);
    };

    public static Route getAllSuspiciousUsers=(Request req,Response res)->{
        List<User> users=userDao.getAllSuspiciousUsers();
        return gson.toJson(users);
    };

}
