document.addEventListener("DOMContentLoaded", function (event) {
    const session = document.querySelector('#session');
    const admin = document.querySelector('#admin');
    const logout = document.querySelector('#logout');
    const login = document.querySelector('#login');
    const adminLogin = document.querySelector('#adminLogin');

    const errors = document.querySelectorAll(".errorDiv");
    const categories = Array.from(document.querySelector("#categories").children);
    const categoriesList = document.querySelector("#categoriesList");

    // if (Array.isArray(categories) && categories.length) {
        categories.forEach(function (element) {
            const listItemToClone = categoriesList.children;
            const listItem = categoriesList.children[0].cloneNode(true);
            const listLink = listItem.children[0];
            listLink.innerHTML = (element.innerHTML);
            console.log(listLink)
            categoriesList.appendChild(listItem);
            // const listLink = listItem.children();
            // console.log(listItem);
            // console.log(listLink);
        })
    // }

    session.setAttribute("hidden", "hidden");
    admin.setAttribute("hidden", "hidden");

    login.setAttribute("hidden", "hidden");
    logout.setAttribute("hidden", "hidden");
    adminLogin.setAttribute("hidden", "hidden");

    if (session.innerText === "") {
        logout.removeAttribute("hidden")
    } else if (admin.innerText === "1") {
        adminLogin.removeAttribute("hidden")
    } else {
        login.removeAttribute("hidden");
    }

    errors.forEach(function (element) {
        if (element.innerText === "") {
            element.classList.add("d-none");
        }

    })


})