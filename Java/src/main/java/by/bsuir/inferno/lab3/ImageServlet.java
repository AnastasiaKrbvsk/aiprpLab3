package by.bsuir.inferno.lab3;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/image_servlet")
public class ImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try(OutputStream outputStream = response.getOutputStream()) {
            String name = request.getParameter("name");
            if (!StringUtils.isBlank(name)) {
                outputStream.write(Files.readAllBytes(Path.of(
                        "C:\\Users\\User\\Desktop\\inferno\\BSUIR\\Java\\Lab3\\Java\\src\\main\\webapp\\WEB-INF\\imgs\\" +
                                name)));
                outputStream.flush();
            } else {
                System.out.println("Image name is blank");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
