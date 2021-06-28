import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import restaurant.Location;
import restaurant.Restaurant;
import restaurant.RestaurantRepository;
import user.User;
import user.UserRepository;
import user.UserRoles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        port(8080);
    }
}
