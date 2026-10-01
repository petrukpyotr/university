package org.example.lab7;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

/**
 * Сервлет для работы с сессиями.
 * Подсчитывает количество обращений к странице в рамках одной сессии пользователя.
 */
@WebServlet ("/SessionServlet")
public class SessionServlet extends HttpServlet {

    /**
     * Обрабатывает GET-запросы.
     * Увеличивает счётчик обращений к странице и сохраняет его в сессии.
     *
     * @param request HTTP-запрос
     * @param response HTTP-ответ для отправки HTML-страницы
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Устанавливаем тип контента и кодировку для корректного отображения кириллицы
        response.setContentType("text/html;charset=UTF-8");

        // Получаем или создаём сессию пользователя
        HttpSession session = request.getSession();

        // Получаем текущее значение счётчика из сессии
        Integer counter = (Integer) session.getAttribute("counter");

        // Если счётчик не существует (первое обращение), устанавливаем его в 1
        // Иначе увеличиваем на 1
        if (counter == null) {
            counter = 1;
        } else {
            counter++;
        }

        // Сохраняем обновлённое значение счётчика в сессии
        session.setAttribute("counter", counter);

        // Формируем HTML-страницу с информацией о количестве обращений
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Количество обращений к странице: " + counter + "</h3>");
        out.println("</body></html>");
    }
}
