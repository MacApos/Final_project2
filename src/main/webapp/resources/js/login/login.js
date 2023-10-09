document.addEventListener("DOMContentLoaded", function (event) {
    const session = document.querySelector('#session');
    const admin = document.querySelector('#admin');
    const logout = document.querySelector('#logout');
    const login = document.querySelector('#login');
    const adminLogin = document.querySelector('#adminLogin');

    const errors = document.querySelectorAll(".errorDiv");

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

    errors.forEach(function(element){
        if(element.innerText===""){
            element.classList.add("d-none");
        }

    })


})