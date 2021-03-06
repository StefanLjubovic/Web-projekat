package order;

import com.google.gson.Gson;
import dto.OrderDTO;
import restaurant.Restaurant;
import spark.Request;
import spark.Response;
import spark.Route;
import user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static server.Server.*;

public class OrderController {

    private static Gson gson=new Gson();

    public static Route createOrder=(Request req, Response res)->{
        String orderJson = req.body();
        Order order = gson.fromJson(orderJson,Order.class);
        order.setStatus(OrderStatus.InPreparation);
        order.setId(orderDao.generateId());
        order.setDate(new Date());
        User user = userDao.getOne(order.getBuyerId());
        user.addScore(order.getPrice());
        userDao.update(user);
        res.status(200);
        orderDao.create(order);
        return gson.toJson(order);
    };

    public static Route getAllOrders=(Request req,Response res)->{
        System.out.println(req.queryParams("id")+"aaaaa");
        List<OrderDTO> dtos=orderDao.fillDTO(req.queryParams("id"));
        return gson.toJson(dtos);
    };

    public static Route updateOrderStatus=(Request req,Response res)->{
        String orderJson = req.body();
        OrderDTO orderDTO = gson.fromJson(orderJson,OrderDTO.class);
        orderDao.UpdateOrder(orderDTO.order);
        if(orderDTO.order.getStatus().equals(OrderStatus.Canceled)) {
            orderDTO.user.removeScore(orderDTO.order.getPrice());
            userDao.update(orderDTO.user);
        }
        if(orderDTO.order.delivererId !=null){
            orderDTO.deliverer=userDao.getOne(orderDTO.order.delivererId);
        }
        System.out.println(gson.toJson(orderDTO));
        return gson.toJson(orderDTO);
    };

}
