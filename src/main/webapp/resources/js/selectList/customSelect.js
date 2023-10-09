/* global bootstrap: false */

document.addEventListener("DOMContentLoaded", function (event) {
    const select = document.querySelectorAll("select");
    select.forEach(function (element) {
        const newOption = element.querySelector("option[value='new']");
        const defaultOption = element.querySelector("option[value='placeholder']");
        const form = element.parentElement.querySelector("input");

        element.addEventListener("change", function (event) {
            if (newOption.selected) {
                form.classList.remove('d-none');
                element.classList.add('d-none');
                form.focus();
            }
        });

        form.addEventListener("input", function (event) {
            if (form.value.trim() === "") {
                element.classList.remove('d-none');
                form.classList.add('d-none');
                defaultOption.selected = true;
            }
        });
    });
});