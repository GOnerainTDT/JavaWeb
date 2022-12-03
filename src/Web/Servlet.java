package Web;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {
    public void service(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        String userName = request.getParameter("userName");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h3>Hello, " + userName + "</h3>");
        pw.println("</body>");
        pw.println("</html>");
    }

}
