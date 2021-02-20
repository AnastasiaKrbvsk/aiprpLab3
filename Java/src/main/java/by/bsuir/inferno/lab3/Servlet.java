package by.bsuir.inferno.lab3;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "Servlet", urlPatterns = "/app")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String originalText = request.getParameter("original_text_param");
        if (!StringUtils.isBlank(originalText)) {
            request.setAttribute("original_text_attr", originalText);
            request.setAttribute("translated_text_attr", getTranslation(originalText));
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private String getTranslation(String originalText) {
        Properties dictionary = getDictionary();
        dictionary.forEach((eng, rus) -> {
            System.out.println(eng + " = " + rus);
        });
        String[] originalWordsArray = originalText.split(" ");
        AtomicReference<String> translatedText = new AtomicReference<>("");
        for (String word : originalWordsArray) {
            System.out.println(word);
            dictionary.forEach((eng, rus) -> {
                if (((String)rus).trim().equalsIgnoreCase(word)) {
                    translatedText.updateAndGet(v -> v + eng + " ");
                }
            });
        }
        return translatedText.get();
    }

    private Properties getDictionary(){
        try(FileInputStream fileInputStream = new FileInputStream("C:\\Users\\User\\Desktop\\inferno\\BSUIR\\Java\\Lab3\\Java\\src\\main\\resources\\dictionary.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}