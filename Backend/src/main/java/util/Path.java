package util;

import lombok.Getter;

public class Path {

    public static class Web{
        @Getter public static final String INDEX = "/index";
        @Getter public static final String LOGIN = "/login";
        @Getter public static final String RESTAURANT="/restaurants/";
        @Getter public static final String GET_AVAILABLE_MANAGERS="/restaurants/managers";
        @Getter public static final String GET_SINGLE_RESTAURANT = "/restaurantById";
        @Getter public static final String GET_USER_BY_TOKEN = "/userByToken";
        @Getter public static final String REGISTER_RESTAURANT="/restaurants/register";
        @Getter public static final String CREATE_USER="/register";

        @Getter public static final String UPLOAD_IMAGE = "/upload";
        @Getter public static final String CREATE_RESTAURANT = "/createRestaurant";
        @Getter public static final String SAVE_ITEM = "/saveItem/:restaurantId" ;

        @Getter public static final String UPDATE_USER="/userList/update";
        @Getter public static final String GET_ALL_USERS="/userList";
        @Getter public static final String UPDATE_PASSWORD="/editProfile/updatePassword";

        @Getter public static final String UPDATE_RESTAURANT = "/updateRestaurant";
        @Getter public static final String CREATE_ORDER ="/createOrder" ;
        @Getter public static final String GET_ALL_ORDERS="/getOrders";
        @Getter public static final String UPDATE_ORDER_STATUS="/orders/update";
        @Getter public static final String SAVE_REVIEW="/orders/saveReview";
        @Getter public static final String GET_RESTAURANT_REVIEWS="/restaurant/reviews";
        @Getter public static final String UPDATE_RESTAURANT_REVIEW="/restaurant/reviewsUpdate";
        @Getter public static final String GET_SINGLE_MANAGER = "/getManagerById";
        @Getter public static final String SUSPICIOUS_USERS="/userList/suspicious";
    }
    public static class Storage{
        @Getter public static final String Restaurant = "src/main/java/restaurant/Restaurant.json";
    }
}
