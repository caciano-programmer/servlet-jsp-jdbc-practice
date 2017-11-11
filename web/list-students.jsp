<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<html>
<head>
    <title>Student Tracker App</title>
    <link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <h2>FooBar University</h2>
        </div>
    </div>
    <div class="container">
        <div id="content">
            <input type="button" value="Add Student"
                   onclick="window.location.href='add-student-form.jsp'; return false;"
                   class="add-student-button"
            />
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <core:forEach var="temp" items="${STUDENT_LIST}">
                    <core:url var="tempLink" value="students">
                        <core:param name="command" value="LOAD"/>
                        <core:param name="studentId" value="${temp.id}"/>
                    </core:url>
                    <core:url var="deleteLink" value="students">
                        <core:param name="command" value="DELETE"/>
                        <core:param name="studentId" value="${temp.id}"/>
                    </core:url>
                    <tr>
                        <td>${temp.firstName}</td>
                        <td>${temp.lastName}</td>
                        <td>${temp.email}</td>
                        <td>
                            <a href="${tempLink}">Update</a> | <a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) return;">Delete</a>
                        </td>
                    </tr>
                </core:forEach>
            </table>
        </div>
    </div>
</body>
</html>
