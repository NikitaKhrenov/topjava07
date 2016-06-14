<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 14.06.2016
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<UserMealWithExceed> mealList = (List<UserMealWithExceed>)request.getAttribute("mealList"); %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<div>
    <table>
        <tbody>
            <tr>
                <td>dateTime</td>
                <td>Description</td>
                <td>Calories</td>
            </tr>
            <c:forEach var="meal" items="<%= mealList%>">
                <tr style="${meal.exceed ? 'color: red' : 'color: green'}">
                    <td>
                        <c:out value="${meal.dateTime}"/>
                    </td>
                    <td>
                        <c:out value="${meal.description}"/>
                    </td>
                    <td>
                        <c:out value="${meal.calories}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form method="post" action="meals">
        <input type="datetime" name="dateTime" value="14.06.2016 19:20"/>
        <input type="text" name="description"/>
        <input type="text" name="calories">
        <input type="submit" value="Добавить">
    </form>
</div>
</body>
</html>
