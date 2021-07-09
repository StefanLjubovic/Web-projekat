package order;

import com.google.gson.Gson;
import restaurant.Restaurant;
import spark.Request;
import spark.Response;
import spark.Route;
import user.User;

import java.util.ArrayList;
import java.util.Date;

import static server.Server.*;

public class OrderController {

    private static Gson gson=new Gson();

    public static Route createOrder=(Request req, Response res)->{
        String orderJson = req.body();
        Order order = gson.fromJson(orderJson,Order.class);
        order.setStatus(OrderStatus.InPreparation);
        order.setId(orderDao.generateId());
        order.setDate(new Date());
        res.status(200);
        orderDao.create(order);
        return gson.toJson(order);
    };
}
