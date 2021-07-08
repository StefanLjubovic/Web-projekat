package grade;

import com.google.gson.Gson;
import restaurant.Restaurant;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

import static server.Server.gradeDao;
import static server.Server.restaurantDAO;

public class GradeController {

    private static Gson gson=new Gson();

    public static Route getRestuarantGrades = (Request req, Response res) -> {
        String restaurantIdJson = req.body();
        String restaurantId = gson.fromJson(restaurantIdJson,String.class);
        List<Grade> grades= gradeDao.getGradesForRestaurant(restaurantId);
        res.type("application/json");
        return gson.toJson(grades);
    };
}
