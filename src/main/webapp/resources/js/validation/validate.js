document.addEventListener("DOMContentLoaded", function () {
    const submit = document.querySelector("[type='submit']");
    const form = document.querySelector("form[method='POST']");

    const name = form.querySelector("[name='name']");
    const description = form.querySelector("[name='description']");
    const scent = form.querySelector("[name='scent']")
    const scentSibling = scent.nextElementSibling;
    const color = form.querySelector("[name='color']");
    const colorSibling = color.nextElementSibling;
    const price = form.querySelector("[name='price']");
    const type = form.querySelector("[name='type']");
    const category = form.querySelector("[name='category']");
    const img = form.querySelector("[name='img']");

    const imgDiv = document.querySelector("img");
    const imgDivParent = imgDiv.parentElement;
    imgDivParent.setAttribute("hidden", "hidden");

    img.addEventListener("change", function(event) {
        imgDiv.setAttribute("src", "../../../resources/product_images/" + this.files[0].name);
        imgDivParent.removeAttribute("hidden");
    })

    // const browse = form.querySelector("span.input-group-text");
    // const imgInput = browse.nextElementSibling;
    // const imgInputParent = browse.parentElement;
    //
    // imgInput.addEventListener("mouseover", function () {
    //     browse.classList.add("btn-light-hover");
    // });
    //
    // imgInput.addEventListener("mouseout", function () {
    //     browse.classList.remove("btn-light-hover");
    // });
    //
    // browse.addEventListener("click", makeInput);
    // imgInput.addEventListener("click", makeInput);
    // console.log(imgInput)
    //
    // function makeInput() {
    //     // browse.classList.add("btn-light-border");
    //     // browse.classList.add("btn-light-shadow");
    //     // imgInput.classList.add("btn-light-border");
    //
    //     const newInput = document.createElement("input");
    //     newInput.type = "file";
    //     newInput.click();
    //
    //     newInput.addEventListener("change", function (event) {
    //         img.setAttribute("src", "../../../resources/product_images/" + this.files[0].name)
    //         imgParent.removeAttribute("hidden");
    //     })
    // }
    //
    // document.addEventListener("mouseup", function () {
    //     browse.classList.remove("btn-light-border");
    //     imgInput.classList.remove("btn-light-border");
    //     browse.classList.remove("btn-light-shadow");
    //     // imgInputParent.classList.remove("form-control");
    // });
    //
    // $(document).on("click", function () {
    //     if (document.activeElement !== color && $(color).val() === "") {
    //         $(color).prop("disabled", true);
    //         $(color).next("div").show();
    //         console.log(color.nextElementSibling);
    //     }
    // })
    //
    // $(color).next().on("click", function (evt) {
    //     $(this).hide()
    //     $(this).prev("input").prop("disabled", false).focus();
    //     console.log(this)
    // });
    //
    // document.addEventListener("click", function () {
    //     if (document.activeElement !== color && color.value === "") {
    //         color.removeAttribute("hidden");
    //         input.setAttribute("disabled", "disabled");
    //     }
    // })
    //
    // color.addEventListener("click", function () {
    //     input.removeAttribute("disabled");
    //     color.setAttribute("hidden", "hidden");
    //     input.focus();
    //     console.log(input);
    // })

    colorSibling.addEventListener("click", removeDisable);
    scentSibling.addEventListener("click", removeDisable);

    document.addEventListener("click", addDisable);

    function removeDisable() {
        this.setAttribute("hidden", "hidden");
        const input = this.previousElementSibling;
        input.removeAttribute("disabled");
        input.focus();
    }

    function addDisable() {
        this.querySelectorAll("input + div[hidden='hidden']").forEach(function (element) {
            const input = element.previousElementSibling;
            if (document.activeElement !== input && input.value === "") {
                element.removeAttribute("hidden");
                input.setAttribute("disabled", "disabled");
            }
        })
    }

    form.addEventListener("submit", function (event) {
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }

        form.classList.add("was-validated");
    }, false)

})
