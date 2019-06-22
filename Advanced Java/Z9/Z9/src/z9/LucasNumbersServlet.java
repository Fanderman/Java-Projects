package z9;


import java.io.IOException;
import java.math.BigInteger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LucasNumbers", urlPatterns = {"/lucas-numbers-servlet"}, loadOnStartup = 1)
public class LucasNumbersServlet extends HttpServlet {
    protected final static long serialVersionUID = 1l;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("n");
        int n;

        try {
            if ((n = Integer.parseInt(param)) < 0)
                throw new Exception("Undefined arguments.");
        }
        catch (Exception ignore) {
            n = 10;
        }

        request.setAttribute("table", LucasNumbers.lucasTable(new BigInteger("2"), new BigInteger("1"), n));
        request.getRequestDispatcher("lucas-numbers-servlet-response.jsp").forward(request, response);
    }
}
