import static spark.Spark.get;

public class Server {

    public static void main(String[] args) {

        get("/test", (request, response) ->{
            return "Hello";
        });
    }
}
