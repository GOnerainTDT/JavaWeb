package Web;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println(" <font color = '#0000ff'>");
        out.println("<h3>Hello,World!</h3>");
        out.println("The time now is:" + new java.util.Date());
        out.println("</body>");
        out.println("</html>");
    }
}
