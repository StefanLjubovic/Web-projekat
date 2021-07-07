package user;

import util.ModelDao;
import util.Serialization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDao extends Serialization<User> implements ModelDao<User> {
    private List<User> users;
    private String filePath="src/main/java/user/User.json";
    private UserSerialization userSerialization=new UserSerialization();
    public UserDao(){
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
}
