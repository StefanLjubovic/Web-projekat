package cart;

import restaurant.Restaurant;
import restaurant.RestaurantSerialization;
import util.ModelDao;

import java.util.List;

public class CartDao implements ModelDao<Cart> {
    private List<Cart> carts;
    private String filePath="src/main/java/cart/Cart.json";
    private CartSerialization cartSerialization = new CartSerialization();
    public CartDao(){ carts = cartSerialization.load(filePath);}

    @Override
    public Boolean create(Cart obj) {
        if(objectExists(obj))
            return false;
        carts.add(obj);
        cartSerialization.save(filePath,carts);
        return true;
    }

    @Override
    public List<Cart> getAll() {
        return carts;
    }

    @Override
    public Cart getOne(String id) {
        System.out.println("Search for "+id);
        for (Cart cart: carts) {
            if(cart.getId().equals(id))
                return cart;
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        for (int i = 0; i < carts.size(); i++){
            if(carts.get(i).getId().equals(id)){
                carts.remove(i);
                cartSerialization.save(filePath,carts);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean objectExists(Cart object) {
        for (Cart cart:carts) {
            if(cart.getId().equals(object.getId()))
                return true;
        }
        return false;
    }
}
