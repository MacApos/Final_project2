document.addEventListener("DOMContentLoaded", function (event) {
    const img = document.querySelector("[name='img']");
    const imgDiv = document.querySelector("div img");
    imgDiv.setAttribute('hidden', 'hidden')

    img.addEventListener("change", (event) => {
        imgDiv.setAttribute('src',
            '../../../resources/product_images/');
        const fileList = event.target.files[0];
        imgDiv.setAttribute('src', imgDiv.getAttribute('src')+fileList.name)
        imgDiv.removeAttribute('hidden');
    })

})