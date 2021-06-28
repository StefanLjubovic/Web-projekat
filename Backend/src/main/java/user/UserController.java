package user;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

import static server.Server.userDao;

public class UserController  {

    private static Gson gson=new Gson();

    public static Route getAvailableManagers = (Request req, Response res)-> {
        res.type("application/json");
        List<User> users=userDao.getAvailableManagers();
        return gson.toJson(users);
    };
}
