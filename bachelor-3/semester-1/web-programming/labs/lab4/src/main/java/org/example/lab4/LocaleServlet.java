package org.example.lab4;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleServlet extends HttpServlet {
    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String lang = req.getParameter("lang");
        Locale locale;

        if ("ru".equalsIgnoreCase(lang)) {
            locale = new Locale("ru", "RU");
        } else {
            locale = new Locale("en", "US");
        }

        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>" + bundle.getString("title") + "</title></head>");
        out.println("<body style='font-family:Arial; text-align:center; margin-top:100px;'>");
        out.println("<h1>" + bundle.getString("greeting") + "</h1>");
        out.println("<br>");
        out.println("<a href='?lang=" + ("ru".equals(lang) ? "en" : "ru") + "'>"
                + bundle.getString("language_button") + "</a>");
        out.println("</body></html>");
    }
}
