package user;

import order.Order;
import order.OrderDao;
import util.ModelDao;
import util.Serialization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDao extends Serialization<User> implements ModelDao<User> {
    private List<User> users;
    private String filePath="src/main/java/user/User.json";
    private UserSerialization userSerialization=new UserSerialization();
    public static List<Reward> rewards = new ArrayList<>();
    public UserDao(){
        Reward silver = new Reward("Silver", 0.03, 3000);
        Reward gold = new Reward("Gold", 0.04, 4000);
        Reward platinum = new Reward("Platinum", 0.1, 10000);
        rewards.add(silver);
        rewards.add(gold);
        rewards.add(platinum);
        users = userSerialization.load(filePath);
    }


    @Override
    public Boolean create(User obj) {
        if(objectExists(obj))
            return false;
        users.add(obj);
        userSerialization.save(filePath,users);
        return true;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getOne(String id) {
        for (User user: users) {
            if(user.getId().equals(id))
                return user;
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        int index = -1;
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(id)){
                index = i;
                break;
            }
        }
        if(index != -1){
            users.remove(index);
            userSerialization.save(filePath,users);
            return true;
        }
        return false;
    }

    @Override
    public Boolean objectExists(User object) {
        for (User user:users) {
            if(user.getId().equals(object.getId()))
                return true;
        }
        return false;
    }
    public List<User> getAvailableManagers(){
        users=getAll();
        List<User> retVal=new ArrayList<>();
        users.stream()
                .filter(u->u.getRole().equals(UserRoles.Manager) && u.getRestaurantId()==null)
                .forEach(u->retVal.add(u));
        return retVal;
    }
    public boolean update(User user){
        users=getAll();
        int index=-1;
        for(User u:users){
            if(user.getUsername().equals(u.getUsername())) {
                index=users.indexOf(u);
                break;
            }
        }
        users.set(index,user);
        userSerialization.save(filePath,users);
        return true;
    }

    public User getByUsername(String username) {
        for (User user: users) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public List<User> getAllSuspiciousUsers(){
        List<User> retval=new ArrayList<>();
        for (User user:users) {
            if (suspicious(user)) {
                retval.add(user);
            }
        }
        return retval;
    }

    private boolean suspicious(User user) {
        OrderDao orderDao=new OrderDao();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result = cal.getTime();
        List<Order> orders=orderDao.getAll();
        long count=orders.stream()
                .filter(o-> o.getBuyerId().equals(user.getUsername()) && compare(o.getDate(),result) )
                .count();
        if(count>=5)
            return true;
        return false;
    }

    private boolean compare(Date date, Date result) {
        if(date.after(result))
            return true;
        if(date.equals(result))
            return true;
        return false;
    }
}
