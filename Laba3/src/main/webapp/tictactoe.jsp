<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tic-Tac-Toe</title>
</head>
<body>
<h1>Tic-Tac-Toe</h1>

<table>
    <%
        char[][] board = (char[][]) request.getAttribute("board");
        char winner = (char) (request.getAttribute("winner") != null ? request.getAttribute("winner") : ' ');
        char player = (char) (request.getAttribute("player") != null ? request.getAttribute("player") : ' ');

        for (int i = 0; i < 3; i++) {
    %>
    <tr>
        <%
            for (int j = 0; j < 3; j++) {
        %>
        <td>
            <%
                if (board[i][j] == ' ') {
            %>
            <form action="TicTacToeServlet" method="post">
                <input type="hidden" name="row" value="<%=i%>">
                <input type="hidden" name="col" value="<%=j%>">
                <input type="submit" value="">
            </form>
            <%
                } else {
                    out.print(board[i][j]);
                }
            %>
        </td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>

<p>
    <% if (winner != ' ') { %>
    Winner: <%= winner %>
    <a href="TicTacToeServlet?action=reset">Play again</a>
    <% } else { %>
    Current player: <%= player %>
    <% } %>
</p>
</body>
</html>
