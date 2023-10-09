const form = document.querySelector('form');
form.addEventListener('submit', onFormSubmission);
console.log(form)
function onFormSubmission(event) {
    event.preventDefault();
    console.log('test');
}