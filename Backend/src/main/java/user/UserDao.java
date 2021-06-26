package user;

import util.ModelDao;
import util.Serialization;

import java.util.List;

public class UserDao extends Serialization<User> implements ModelDao<User> {
    private List<User> users;

    @Override
    public Boolean create(User obj) {
        if(objectExists(obj))
            return false;
        users.add(obj);
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


}
