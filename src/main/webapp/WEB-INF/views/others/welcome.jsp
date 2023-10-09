<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>

    <link href="../../../resources/assets/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <table class="table table-striped">
        <caption>Your todos are</caption>
        <thead>
        <tr>
            <th>Description</th>
            <th>Target Date</th>
            <th>Is it Done?</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Todo 1</td>
            <td>10/12/2017</td>
            <td>No</td>
            <td><a class="btn btn-warning" href="/edit-todo">Edit Todo</a></td>
            <td><a class="btn btn-warning" href="/delete-todo">Delete Todo</a></td>
        </tr>
        </tbody>
    </table>
    <div>
        <a class="btn btn-default" href="/add-todo">Add a Todo</a>
        <p>Hello world</p>

    </div>
    <script src="../../../resources/assets/dist/js/jquery.min.js"></script>
    <script src="../../../resources/assets/dist/js/bootstrap.min.js"></script>
    <script>
         $(function(){
             $("p").click(function (){
                 $(this).hide();
             })
         })
    </script>
</div>
</body>
</html>

