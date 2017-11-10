package jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class studentDBUtil {
    private DataSource dataSource;

    public studentDBUtil(DataSource source) { dataSource = source; }

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

    public void addStudent(student student) {

    }
}
