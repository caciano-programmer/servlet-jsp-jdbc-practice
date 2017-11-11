package jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class studentDBUtil {
    private DataSource dataSource;

    public studentDBUtil(DataSource source) { dataSource = source; }

    public student getStudent(String id) throws Exception {
        student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int studentId;

        try {
            studentId = Integer.parseInt(id);
            connection = dataSource.getConnection();
            String sql = "select * from student where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                student = new student(Integer.parseInt(id), firstName, lastName, email);
            } else {
                throw new Exception("Could not find student id: " + studentId);
            }

            return student;
        } finally { close(connection, statement, resultSet); }
    }

    public List<student> getStudents() throws Exception {
        List<student> students = new ArrayList<student>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from student order by last_name";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                student temp = new student(id, firstName, lastName, email);
                students.add(temp);
            }
        } finally { close(connection, statement, resultSet); }

        return students;
    }

    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void addStudent(student student) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "insert into student "
                    + "(first_name, last_name, email) "
                    + "values (?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setString(1, student.getFirstName());
            myStmt.setString(2, student.getLastName());
            myStmt.setString(3, student.getEmail());

            // execute sql insert
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void updateStudent(student student) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "update student " +
                    "set first_name = ?, last_name = ?, email = ? " +
                    "where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setInt(4, student.getId());

            statement.execute();
        } finally { close(connection, statement, null); }
    }

    public void deleteStudent(String id) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "delete from student where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            statement.execute();
        } finally {
            close(connection, statement, null);
        }
    }
}
