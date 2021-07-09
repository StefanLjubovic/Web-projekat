package grade;

import dto.GradeDTO;
import order.Order;
import restaurant.Restaurant;
import restaurant.RestaurantSerialization;
import util.ModelDao;
import static server.Server.userDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradeDao implements ModelDao<Grade> {
    private List<Grade> grades;
    private String filePath="src/main/java/grade/Grade.json";
    private GradeSerialization gradeSerialization=new GradeSerialization();
    public GradeDao(){
        grades = gradeSerialization.load(filePath);
    }
    @Override
    public Boolean create(Grade obj) {
        if(objectExists(obj))
            return false;
        grades.add(obj);
        gradeSerialization.save(filePath,grades);
        return true;
    }

    @Override
    public List<Grade> getAll() {
        return grades;
    }

    @Override
    public Grade getOne(String id) {
        for (Grade grade: grades) {
            if(grade.getId().equals(id))
                return grade;
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        for (int i = 0; i < grades.size(); i++){
            if(grades.get(i).getId().equals(id)){
                grades.remove(i);
                gradeSerialization.save(filePath,grades);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean objectExists(Grade object) {
        for (Grade grade:grades) {
            if(grade.getId().equals(object.getId()))
                return true;
        }
        return false;
    }

    public List<GradeDTO> getGradesForRestaurant(String restaurantId) {
        List<GradeDTO> retVal=new ArrayList<>();
        GradeDTO dto=new GradeDTO();
        for(Grade grade: grades){
            if(grade.getRestaurantId().equals(restaurantId)){
                dto.user=userDao.getOne(grade.getUserId());
                dto.grade=grade;
                retVal.add(dto);
            }
        }
        return retVal;
    }

    public void UpdateGrade(Grade grade) {
        for(int i = 0; i < grades.size(); i++){
            if(grades.get(i).getId().equals(grade.getId())){
                grades.set(i, grade);
                gradeSerialization.save(filePath,grades);
                return;
            }
        }
    }
}
