/* global bootstrap: false */

document.addEventListener("DOMContentLoaded", function (event) {
    const select = document.querySelectorAll("select");
    select.forEach(function (element) {
        const parent = element.parentElement;
        const newOption = element.querySelector("option[value='new']");
        const defaultOption = element.querySelector("option#placeholder");
        const input = parent.querySelector("input");
        const errorDiv = parent.querySelector("input ~ div.invalid-feedback");
        parent.removeChild(input);
        parent.removeChild(errorDiv);
        console.log(errorDiv)

        element.addEventListener("change", function (event) {
            if (newOption.selected) {
                parent.removeChild(element);
                parent.appendChild(input);
                parent.appendChild(errorDiv);
                input.focus();
            }
        });

        input.addEventListener("input", function (event) {
            if (input.value.trim() === "") {
                input.innerText = "";
                parent.removeChild(input);
                parent.removeChild(errorDiv);
                parent.appendChild(element);
                defaultOption.selected = true;
            }
        });
    });
});