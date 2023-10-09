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

    const browse = document.querySelector("button.btn-light");
    const imgInput = browse.nextElementSibling;
    const imgInputParent = browse.parentElement;

    const img = document.querySelector("img");
    const imgParent = img.parentElement;
    imgParent.setAttribute('hidden', 'hidden');

    imgInput.addEventListener("mouseover", function () {
        browse.classList.add('btn-light-hover');
    });

    imgInput.addEventListener("mouseout", function () {
        browse.classList.remove('btn-light-hover');
    });

    browse.addEventListener("click", makeInput);
    imgInput.addEventListener("click", makeInput);

    function makeInput() {
        browse.classList.add("btn-light-border");
        imgInput.classList.add("btn-light-border");
        imgInputParent.classList.add("btn-light-shadow");

        const newInput = document.createElement("input");
        newInput.type = "file";
        newInput.click();

        newInput.addEventListener("change", function (event){
            img.setAttribute('src', img.getAttribute('src') + this.files[0].name)
            imgParent.removeAttribute('hidden');
        })
    }

    document.addEventListener("mouseup", function () {
        browse.classList.remove("btn-light-border");
        imgInput.classList.remove("btn-light-border");
        imgInputParent.classList.remove("btn-light-shadow");
    });

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

    // document.addEventListener("click", function () {
    //     if (document.activeElement !== color && color.value === "") {
    //         color.removeAttribute("hidden");
    //         input.setAttribute("disabled", "disabled");
    //     }
    // })

    // color.addEventListener("click", function () {
    //     input.removeAttribute("disabled");
    //     color.setAttribute("hidden", "hidden");
    //     input.focus();
    //     console.log(input);
    // })

    colorSibling.addEventListener("click", addDisable);
    scentSibling.addEventListener("click", addDisable);

    document.addEventListener("click", removeDisable);

    function addDisable() {
        this.setAttribute("hidden", "hidden");
        const input = this.previousElementSibling;
        input.removeAttribute("disabled");
        input.focus();
    }


    function removeDisable() {
        this.querySelectorAll("input + div[hidden='hidden']").forEach(function (element) {
            const input = element.previousElementSibling;
            if (document.activeElement !== input && input.value === "") {
                element.removeAttribute("hidden");
                input.setAttribute("disabled", "disabled");
            }
        })
    }

    form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }

        form.classList.add('was-validated');
    }, false)

})
