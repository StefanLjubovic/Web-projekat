package util;

import lombok.Getter;

public class Path {

    public static class Web{
        @Getter public static final String INDEX = "/index/";
        @Getter public static final String LOGIN = "/login/";
        @Getter public static final String REGISTER_RESTUARANT="/create-restaurant/";
        @Getter public static final String GET_AVAILABLE_MANAGERS="/create-restaurant/managers/";
    }
}
