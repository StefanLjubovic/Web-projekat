package util;

import spark.*;

public class Filters {

    public static Filter correctSplashes = (Request request, Response response) -> {
        if (!request.pathInfo().endsWith("/")) {
            System.out.println(request.pathInfo());
            response.redirect(request.pathInfo() + '/');
        }
    };

}