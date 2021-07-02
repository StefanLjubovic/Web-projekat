package util;

import lombok.Getter;

public class Path {

    public static class Web{
        @Getter public static final String INDEX = "/index";
        @Getter public static final String LOGIN = "/login";
        @Getter public static final String RESTAURANT="/restaurants/";
        @Getter public static final String GET_AVAILABLE_MANAGERS="/restaurants/managers";
        @Getter public static final String GET_SINGLE_RESTAURANT = "/restaurantById";
    }
    public static class Storage{
        @Getter public static final String Restaurant = "src/main/java/restaurant/Restaurant.json";
    }
}
