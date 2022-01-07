<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
<form action="getBook" method="post">

    <input type="text" placeholder="First Name" name="firstName"/>
    <input type="text" placeholder="Last Name" name="lastName"/>

    <input type="text" placeholder="Russian Name" name="russianname"/>
    <input type="text" placeholder="Author" name="authorName"/>
    <input type="submit" value="Get">
</form>
<form action="getReader" method="post">
    <label>Email</label>
    <input type="text" placeholder="${email}" name="email"/>
    <input type="submit" value="Get">
</form>
<td><a href="getReader?email=${email}">Check</a></td>
<td><a href="addBook.jsp">Add reader</a></td>
<table class="table" id="table">
    <tr id="zag">
        <td class="sorted-asc">Фамилия</td>
        <td>Имя</td>
        <td>Дата рождения</td>
        <td>Адрес</td>
        <td>Email</td>
    </tr>
    <c:forEach items="${list}" var="reader" >
        <tr>
            <td>${reader.getLastName() }</td>
            <td>${reader.getFirstName() }</td>
            <td>${reader.getDate() }</td>
            <td>${reader.getAddress() }</td>
            <td>${reader.getEmail() }</td>
        </tr>
    </c:forEach>
</table>
<hr />
<c:if test="${currentPage != 1}">
    <td><a href="getBook?page=${currentPage - 1}">Previous</a></td>
</c:if>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="getBook?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="getBook?page=${currentPage + 1}">Next</a></td>
</c:if>
</body>
<script>
    window.addEventListener("DOMContentLoaded", function() {
        (function(f) {
            function g(c) {
                return function(b, a) {
                    b = b.cells[c].textContent;
                    a = a.cells[c].textContent;
                    b = +b || b;
                    a = +a || a;
                    return b > a ? 1 : b < a ? -1 : 0
                }
            }
            var d = document.querySelector(f),
                e = [].slice.call(d.rows, 1);
            [].slice.call(d.rows[0].cells).forEach(function(c, b) {
                var a = 0;
                c.addEventListener("click", function() {
                    e.sort(g(b));
                    a && e.reverse();
                    e.forEach(function(a) {
                        d.appendChild(a)
                    });
                    a ^= 1
                })
            })
        })(".table")
    });
</script>
</html>
