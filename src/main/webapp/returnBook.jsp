<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<body>
<form><input type="search" placeholder="Reader name"/></form>

<table>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Дата рождения</th>
        <th>Адрес</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${list}" var="reader" >
        <tr>
            <td>${reader.getLastName() }</td>
            <td>${reader.getName() }</td>
            <td>${reader.getDate() }</td>
            <td>${reader.getAddress() }</td>
            <td>${reader.getEmail() }</td>
        </tr>
    </c:forEach>
</table>
<form action="returnBook" method="post">
    <label>Return book</label>

    <input type="text" placeholder="Russian Name" name="russianname"/>
    <input type="text" placeholder="Author" name="authorName"/>
    <input type="number" placeholder="Raiting" name="raiting"/>
    <input type = "checkbox"  required name = "defects" value = "true">Defects
    <input type="file" name="image">
    <input type="date" id="date"/>
    <input type="submit" value="Return">
</form>
=
<script>
    (function () {
        var date = new Date().toISOString().substring(0, 10),
            field = document.querySelector('#date');
        field.value = date;
        console.log(field.value);

    })()
</script>
</body>
</html>
