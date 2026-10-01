package org.example.lab7;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

/**
 * Сервлет для работы с Cookie.
 * Обрабатывает POST-запросы для сохранения имени пользователя в Cookie
 * и GET-запросы для перенаправления на главную страницу.
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {

    /**
     * Обрабатывает GET-запросы.
     * Перенаправляет пользователя на главную страницу с формой ввода имени.
     *
     * @param request HTTP-запрос
     * @param response HTTP-ответ
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Перенаправляем на главную страницу, где находится форма
        response.sendRedirect(request.getContextPath() + "/");
    }

    /**
     * Обрабатывает POST-запросы.
     * Сохраняет имя пользователя из формы в Cookie и выводит подтверждение.
     *
     * @param request HTTP-запрос, содержащий параметр "username"
     * @param response HTTP-ответ для отправки Cookie и HTML-страницы
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Устанавливаем тип контента и кодировку для корректного отображения кириллицы
        response.setContentType("text/html;charset=UTF-8");

        // Получаем имя пользователя из параметров запроса
        String name = request.getParameter("username");

        // Создаём Cookie с именем пользователя
        Cookie cookie = new Cookie("username", name);
        // Устанавливаем время жизни Cookie - 1 час (3600 секунд)
        cookie.setMaxAge(60 * 60);
        // Добавляем Cookie в ответ
        response.addCookie(cookie);

        // Формируем HTML-страницу с подтверждением
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Имя сохранено в Cookie!</h3>");
        out.println("<a href='ReadCookieServlet'>Перейти к чтению Cookie</a>");
        out.println("</body></html>");
    }
}
