package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static user.UserController.gson;


public class Upload {
    public static Route uploadImage = (Request req, Response res) -> {
        File uploadDir =  new File("upload");
        Map<String, Object> model = new HashMap<>();
        String filename = "";
        req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        res.type("application/json");
        try (InputStream input = req.raw().getPart("file").getInputStream()) { // getPart needs to use same "name" as input field in form
            //Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            filename = req.queryParams("filename").toString();
            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", filename);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);

            File targetFile = new File(String.valueOf(tempFile));
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);

            model.put("filename", tempFile.getFileName().toString());
//            res.body(gson.toJson(model));
            res.status(200);
        }catch (Exception e){
            res.status(400);
        }

        return gson.toJson(model);
    };
}
