package Web;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ParamServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setCharacterEncoding("gb2312");
        response.setContentType("text/html;charset=gb2312");
        String name = request.getParameter("name");
        name = new String(name.getBytes("iso8859-1"),"gb2312");
        String age = request.getParameter("age");
        age = new String(age.getBytes("iso8859-1"),"gb2312");

        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h3>姓名："+name+"</h3><p>");
        pw.println("<h3>年龄："+age+"</h3><p>");
        pw.println("</body>");
        pw.println("</html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }
}

