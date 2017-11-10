package jdbc;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "studentController")
public class studentController extends HttpServlet {

    private studentDBUtil studentDBUtil;

    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            studentDBUtil = new studentDBUtil(dataSource);
        } catch (Exception e) { throw new ServletException(e); }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String command = request.getParameter("command");
            if(command == null) command = "LIST";
            switch (command) {
                case "LIST":
                    listStudents(request, response);
                    break;
                case "ADD":
                    addStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
            }
        } catch (Exception e) {throw new ServletException(e);}
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<student> students = studentDBUtil.getStudents();
        req.setAttribute("STUDENT_LIST", students);
        RequestDispatcher dispatcher = req.getRequestDispatcher("list-students.jsp");
        dispatcher.forward(req, res);
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lasttName");
        String email = req.getParameter("email");

        student student = new student(firstName, lastName, email);
        studentDBUtil.addStudent(student);
        listStudents(req, res);
    }

}
