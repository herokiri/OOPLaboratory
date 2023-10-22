package ru.dstu.laba3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.dstu.laba3.models.Book;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "LibraryServlet", value = "/LibraryServlet")
public class LibraryServlet extends HttpServlet {
    private Library library;

    public void init() throws ServletException {
        library = new Library();
        library.loadBooksFromXML("D:/ooplabi/oop_laba3/Laba3/src/main/resources/books.xml");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filterGenre = request.getParameter("genre");
        List<Book> filteredBooks = library.filterBooks(filterGenre);

        request.setAttribute("books", filteredBooks);


        getServletContext().getRequestDispatcher("/library.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String genre = request.getParameter("genre");
            Book newBook = new Book(title, author, genre);
            library.addBook(newBook);
        } else if ("remove".equals(action)) {
            String title = request.getParameter("titleToRemove");
            library.removeBook(title);
        }

        library.saveBooksToXML("D:/ooplabi/oop_laba3/Laba3/src/main/resources/books.xml");
        response.sendRedirect(request.getContextPath() + "/LibraryServlet");
    }
}
