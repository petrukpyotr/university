package org.example.lab7;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

/**
 * Сервлет для чтения Cookie.
 * Извлекает имя пользователя из Cookie и отображает приветственное сообщение.
 */
@WebServlet("/ReadCookieServlet")
public class ReadCookieServlet extends HttpServlet {

    /**
     * Обрабатывает GET-запросы.
     * Читает Cookie с именем пользователя и выводит приветствие.
     *
     * @param request HTTP-запрос, содержащий Cookie
     * @param response HTTP-ответ для отправки HTML-страницы
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Устанавливаем тип контента и кодировку для корректного отображения кириллицы
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Получаем все Cookie из запроса
        Cookie[] cookies = request.getCookies();
        // Значение по умолчанию, если Cookie не найдена
        String name = "неизвестно";

        // Ищем Cookie с именем "username"
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    name = c.getValue();
                    break; // Cookie найдена, выходим из цикла
                }
            }
        }

        // Формируем HTML-страницу с приветствием
        out.println("<html><body>");
        out.println("<h2>Привет, " + name + "!</h2>");
        out.println("</body></html>");
    }
}
