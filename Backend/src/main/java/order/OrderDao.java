package order;

import grade.Grade;
import grade.GradeSerialization;
import restaurant.Restaurant;
import util.ModelDao;

import java.util.List;

public class OrderDao implements ModelDao<Order> {
    private List<Order> orders;
    private String filePath="src/main/java/order/Order.json";
    private OrderSerialization orderSerialization=new OrderSerialization();
    public OrderDao(){
        orders = orderSerialization.load(filePath);
    }
    @Override
    public Boolean create(Order obj) {
        if(objectExists(obj))
            return false;
        orders.add(obj);
        orderSerialization.save(filePath,orders);
        return true;
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public Order getOne(String id) {
        for (Order order: orders) {
            if(order.getId().equals(id))
                return order;
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        for (int i = 0; i < orders.size(); i++){
            if(orders.get(i).getId().equals(id)){
                orders.remove(i);
                orderSerialization.save(filePath,orders);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean objectExists(Order object) {
        for (Order order:orders) {
            if(order.getId().equals(object.getId()))
                return true;
        }
        return false;
    }

    public String generateId() {
        int nextId = 1;
        for(Order order: orders){
            int orderId = Integer.parseInt(order.getId());
            if(orderId > nextId){
                nextId = orderId + 1;
            }
        }
        return  "" + nextId;
    }
}
