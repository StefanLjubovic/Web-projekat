package order;

import restaurant.Item;

import java.util.Date;
import java.util.List;

public class Order {
    String id;
    List<Item> items;
    String restaurantId;
    Date date;
    Double price;
    OrderStatus status;
    Integer buyerId;
}
