<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="templates/css/addBook.css">
    <script src="templates/js/addBooks.js"></script>
</head>

<body>
<form action="addBook" method="post" >

    <input type="text" placeholder="Название на русском языке" required minlength="3" pattern="^[а-яА-ЯёЁ\s]+$" name="russianName"/>
    <input type="text" placeholder="Оригинальное название" pattern="^[a-zA-Z0-9\s]+$" name="originalName"/>
    <input type="price" placeholder="Цена" required name="price" />
    <input type="number" placeholder="Количество" required  pattern="^[0-9]+$" name="count"/>
    <input type="price" placeholder="Цена за день использования" required name="priceForDay"/>
    <input type="year" placeholder="Год "  name="year"/>
    <input type="number" placeholder="Количество страниц" pattern="^[1-9][0-9]$" name="countPages"/>
    <input type = "checkbox" class='genre' onclick='deRequire("genre")' required name = "genre" value = "Fantastic"> Фантастика
    <input type = "checkbox" class='genre' onclick='deRequire("genre")' required name = "genre" value = "Children"> Детская литература

    <input type="text" placeholder="Автор" pattern="^[а-яА-ЯёЁa-zA-Z\s\.]+$" name="author"/>
    <input type="file" name="authorImage">
    <div id="input0"></div>
    <div class="add" onclick="addInput()">+</div>
    <input type="file" name="image">
    <input type="hidden" name="command" value="addBook" />
    <input type="submit" value="Добавить">
</form>

</body>
</html>
