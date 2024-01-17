<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 1/16/2024
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<form action="/save" method="post">
    <legend>Disabled fieldset example</legend>
    <c:if test="${song.id!=null}">
        <input type="hidden" value="${song.id}" name="id">
    </c:if>
    <div class="mb-3">
        <label for="disabledTextInput" class="form-label">Song Name</label>
        <input name="songName" type="text" id="disabledTextInput" class="form-control" placeholder="Song Name"
               value="${song.songName}">
    </div>
    <div class="mb-3">
        <label for="disabledTextInput" class="form-label">Author</label>
        <input name="author" type="text" class="form-control" placeholder="author" value="${song.author}">
    </div>
    <div class="mb-3">
        <label for="disabledTextInput" class="form-label">Description</label>
        <input name="description" type="text" class="form-control" name="description" placeholder="description"
               value="${song.description}">
    </div>
    <div class="mb-3">
        <label for="disabledTextInput" class="form-label">Image Url</label>
        <input name="imageUrl" type="text" class="form-control" placeholder="Image Url" value="${song.imageUrl}">
    </div>
    <div class="mb-3">
        <label for="disabledTextInput" class="form-label">Video Url</label>
        <input name="videoUrl" type="text" class="form-control" placeholder="Video Url" value="${song.videoUrl}">
    </div>
    <div class="mb-3">
        <label for="disabledTextInput" class="form-label">Status</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="status" id="flexRadioDefault1"
                   value="1" ${song.status? "checked" : ''} >
            <label class="form-check-label" for="flexRadioDefault1">
                true
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="status" id="flexRadioDefault2"
                   value="0" ${!song.status? "checked" : ''} >
            <label class="form-check-label" for="flexRadioDefault2">
                false
            </label>
        </div>
    </div>
    <button class="btn btn-primary" type="submit">Commit</button>
    </fieldset>
</form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
