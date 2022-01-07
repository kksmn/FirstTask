<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.01.2022
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form action="addReader" method="post">

    <input type="text" placeholder="Last name" required minlength="3"  name="lastName"/>
    <input type="text" placeholder="First name" required minlength="3"  name="firstName"/>
    <input type="text" placeholder="Patronymic"  minlength="3"  name="patronymic"/>
    <input type="text" placeholder="Passport number"  minlength="3"  name="passportNumber"/>
    <input type="date" placeholder="Birth date" required name="birthDate"/>
    <input type="email" placeholder="Email"  minlength="3"  name="email"/>
    <input type="text" placeholder="Address"  minlength="3"  name="address"/>


    <input type="submit" value="Добавить">
</form>
</body>
</html>
