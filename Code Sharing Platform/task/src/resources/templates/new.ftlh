<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <style>
        textarea {width: 40%; height: 40% }
    </style>
</head>
<body>
    <textarea id="code_snippet">Write here</textarea><br/>
    <a>Time restriction:</a><input id="time_restriction" type="text"/><br/>
    <a>Views restriction:</a><input id="views_restriction" type="text"/><br/>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
<script>function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": document.getElementById("time_restriction").value,
            "views": document.getElementById("views_restriction").value
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status == 200) {
            alert("Success!");
        }
    }</script>
</body>
</html>