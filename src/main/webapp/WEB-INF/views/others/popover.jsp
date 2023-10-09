<%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 04.10.2023
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <link href="../../../resources/assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-body-tertiary">

<article class="my-3" id="popovers">
    <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
        <h3>Popovers</h3>
    </div>

    <div>
        <div class="bd-example-snippet bd-code-snippet">
            <div class="bd-example m-0 border-0">
                <button type="button" class="btn btn-lg btn-danger" data-bs-toggle="popover" title="Popover title"
                        data-bs-content="And here's some amazing content. It's very engaging. Right?">Click to toggle
                    popover
                </button>
            </div>
        </div>
        <form>
            <div class="container w-25 padding-small text-center">
                <input class="form-control" id="test">
            </div>

            <div class="container w-25 padding-small text-center">
                <div class="bd-example m-0 border-0">
                    <input id="start" class="form-control infolink" data-bs-container="body" data-bs-toggle="popover"
                           data-bs-placement="right"
                    <%--                       data-bs-trigger="focus"--%>
                           data-bs-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
                </div>
            </div>

            <div class="container w-25 padding-small text-center">
                <div class="bd-example m-0 border-0">
                    <input class="form-control" data-bs-container="body" data-bs-toggle="popover"
                           data-bs-placement="right"
                           data-bs-trigger="focus"
                           data-bs-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Wyślij</button>
        </form>
        <button class="btn btn-lg btn-danger" data-bs-toggle="popover" data-bs-trigger="hover"
                title="Dismissible popover"
                data-bs-content="And here's some amazing content. It's very engaging. Right?">Dismissible
            popover
        </button>
    </div>
</article>


<script src="../../../resources/assets/dist/js/jquery.min.js"></script>
<script src="../../../resources/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="../../../resources/assets/dist/js/bootstrap.min.js"></script>
<script src="../../../resources/js/popover/cheatsheet.js"></script>
<script src="../../../resources/js/popover/popover.js"></script>

<script>
    // $(document).on("DOMContentLoaded", function () {
    //     $('input').each(function (index, element) {
    //         element.focus();
    //     });
    // });

    // $(function () {
    //     $('input').each(function (index, element) {
    //         $(this).on('input', function () {
    //             console.log($(this).val())
    //         })
    //         if ($(this).val() === "") {
    //             console.log('empty')
    //         }
    //         // if ($(this).val() === "") {
    //         //
    //         // }
    //     });
    // })

    $(document).on("DOMContentLoaded", function () {
        // $(function (){
        const foo = $("#test");
        const bar = $('form');
        const submit = $("[type='submit']")
        console.log(submit[0]);


        foo.on("input", function () {
            console.log(foo.val());
            if (foo.val().length <= 4) {
                // foo.popover({
                //     placement: "right",
                //     content: "Must be greater then 4."
                // });
                // foo.popover("show");
            } else {
                // foo.popover("dispose");
            }
        })
    })

</script>
</body>
</html>
