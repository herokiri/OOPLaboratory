package ru.dstu.laba3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "deleteBookServlet", value = "/deleteBookServlet")
public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookId = request.getParameter("bookId");

        // Ваш код для удаления книги по ID

        // После удаления, перенаправьте пользователя обратно на страницу с результатом
        response.sendRedirect("library.jsp");
    }
}
