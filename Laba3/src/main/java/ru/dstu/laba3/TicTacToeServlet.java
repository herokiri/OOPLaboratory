package ru.dstu.laba3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.dstu.laba3.models.TicTacToeModel;

@WebServlet(name = "TicTacToeServlet", value = "/TicTacToeServlet")
public class TicTacToeServlet extends HttpServlet {
    private TicTacToeModel model;

    @Override
    public void init() {
        model = new TicTacToeModel();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action != null && action.equals("reset")) {
            model.resetBoard();
        }

        // Check if the current player is the computer and make its move
        if (model.getWinner() == ' ' && model.getCurrentPlayer() == 'O') {
            model.makeComputerMove(); // Make a computer move
        }

        request.setAttribute("board", model.getBoard());
        request.setAttribute("winner", model.getWinner());
        request.setAttribute("player", model.getCurrentPlayer());

        try {
            request.getRequestDispatcher("tictactoe.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int row = Integer.parseInt(request.getParameter("row"));
        int col = Integer.parseInt(request.getParameter("col"));

        if (model.getWinner() == ' ') {
            model.makeMove(row, col);
        }

        // Redirect back to the doGet to handle the computer's move
        doGet(request, response);
    }
}
