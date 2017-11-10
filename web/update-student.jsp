<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
    <link type="text/css" rel="stylesheet" href="CSS/add-student-style.css" />
    <link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>
<div class="wrapper">
    <div class="header">
        <h2>${THE_STUDENT.id}</h2>
    </div>
</div>
<div class="container">
    <h3>Add Student</h3>
    <form action="/" method="get">
        <input type="hidden" name="command" value="UPDATE" />
        <input type="hidden" name="studentId" value="${THE_STUDENT.id}" />
        <table>
            <tbody>
            <tr>
                <td><label>First Name:</label></td>FooBar University
                <td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"></td>
            </tr>
            <tr>
                <td><label>Last Name:</label></td>
                <td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email" value="${THE_STUDENT.email}"></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"></td>
            </tr>
            </tbody>
        </table>
    </form>
    <div style="clear: both;">
        <p>
            <a href="/">Back to List</a>
        </p>
    </div>
</div>
</body>
</html>
