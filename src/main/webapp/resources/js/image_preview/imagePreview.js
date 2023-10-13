document.addEventListener("DOMContentLoaded", function (event) {
    const imgForm = document.querySelector("[name='img']");
    const img = document.querySelector("img");
    const imgDiv = img.parentElement;
    imgDiv.setAttribute('hidden', 'hidden');

    imgForm.addEventListener("change", (event) => {
        img.setAttribute('src', '../../../resources/product_images/');
        const fileList = event.target.files[0];
        // console.log(imgForm.files[0]) //same result with img.files[0]
        img.setAttribute('src', img.getAttribute('src') + fileList.name)
        imgDiv.removeAttribute('hidden');
    })

})