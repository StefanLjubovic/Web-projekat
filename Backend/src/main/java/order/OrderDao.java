package order;

import dto.OrderDTO;
import grade.Grade;
import grade.GradeSerialization;
import restaurant.Restaurant;
import user.User;
import user.UserRoles;
import util.ModelDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static server.Server.restaurantDAO;
import static server.Server.userDao;

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
        return UUID.randomUUID().toString();
    }

    public List<OrderDTO> fillDTO(String id) {
        User user =userDao.getOne(id);
        List<OrderDTO> orderDTOS=new ArrayList<OrderDTO>();
        for(Order order: orders){
            if(user.getRole().equals(UserRoles.Customer) && order.getBuyerId().equals(user.getId()))
                addDTO(orderDTOS, order);
            else if(user.getRole().equals(UserRoles.Manager) && order.getRestaurantId().equals(user.getRestaurantId()))
                addDTO(orderDTOS, order);
            else if(user.getRole().equals(UserRoles.Deliverer) && order.getDelivererId().equals(user.getId())
            && order.getStatus().equals(OrderStatus.WaitingForDelivery))
                addDTO(orderDTOS, order);
            else if(user.getRole().equals(UserRoles.Admin))
                addDTO(orderDTOS, order);
        }
        return orderDTOS;
    }

    private void addDTO(List<OrderDTO> orderDTOS, Order order) {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.order= order;
        orderDTO.user=userDao.getOne(order.getBuyerId());
        orderDTO.restaurant=restaurantDAO.getOne(order.getRestaurantId());
        orderDTO.deliverer= userDao.getOne(order.getDelivererId());
        orderDTOS.add(orderDTO);
    }

    public void UpdateOrder(Order order) {
        for(int i = 0; i < orders.size(); i++){
            if(orders.get(i).getId().equals(order.getId())){
                orders.set(i, order);
                orderSerialization.save(filePath,orders);
                return;
            }
        }
    }
}
