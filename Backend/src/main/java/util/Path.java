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

    }
    public static class Storage{
        @Getter public static final String Restaurant = "src/main/java/restaurant/Restaurant.json";
    }
}
