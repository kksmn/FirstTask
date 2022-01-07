<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<form action="findBook" method="post">

    <input type="search" placeholder="Russian Name" name="russianname"/>
    <input type="submit" value="Get">
</form>
<table class="table" id="table">
    <tr id="zag">
        <td class="sorted-asc">Название</td>
        <td>Автор</td>
        <td>Год выпуска</td>
        <td>Общее количество</td>
    </tr>
    <tr>
        <c:forEach items="${bookMap.entrySet()}" var="book" >

            <th>${book.getValue().getName() }</th>

            <th><c:forEach items="${book.getValue().getAuthors()}" var="genre" >
                ${genre.getAuthorName() } </c:forEach> </th>

            <th>${book.getValue().getYear() }</th>
            <th>${book.getValue().getCount() }</th>

        </c:forEach>
    </tr>
</table>

</body>
</html>
