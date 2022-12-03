package Web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("gb2312");
        response.setContentType("text/html;charset=gb2312");
        String name = request.getParameter("name");
        name = new String(name.getBytes("iso8859-1"),"gb2312");
        String sno = request.getParameter("sno");
        sno = new String(sno.getBytes("iso8859-1"),"gb2312");

        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h3>姓名："+name+"</h3><p>");
        pw.println("<h3>学号："+sno+"</h3><p>");
        pw.println("</body>");
        pw.println("</html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }
}
