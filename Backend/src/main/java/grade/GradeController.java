package grade;

import com.google.gson.Gson;
import dto.GradeDTO;
import restaurant.Restaurant;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static server.Server.gradeDao;
import static server.Server.restaurantDAO;

public class GradeController {

    private static Gson gson=new Gson();

    public static Route getRestuarantGrades = (Request req, Response res) -> {
        List<GradeDTO> grades= gradeDao.getGradesForRestaurant(req.queryParams("id"));
        res.type("application/json");
        return gson.toJson(grades);
    };

    public static  Route saveReview=(Request req,Response res)->{
        String reviewJson = req.body();
        System.out.println(reviewJson);
        Grade grade=gson.fromJson(reviewJson,Grade.class);
        Date date=new Date();
        grade.setDate(date.getTime());
        grade.setApproved(Status.WaitingForApproval);
        String uniqueID = UUID.randomUUID().toString();
        grade.setId(uniqueID);
        return gradeDao.create(grade);
    };

    public static Route updateReview=(Request req,Response res)->{
        String reviewJson = req.body();
        System.out.println(reviewJson);
        GradeDTO gradeDTO=gson.fromJson(reviewJson,GradeDTO.class);
        System.out.println(gradeDTO.grade.isApproved());
        gradeDao.UpdateGrade(gradeDTO.grade);
        restaurantDAO.updateGrade(gradeDTO.grade.getRestaurantId());
        return true;
    };
}
