$(document).ready(function() {
    //Select2 init
    $('.select2').select2();
    //bsCustomFileInput init
    bsCustomFileInput.init();
});

// Автоматическое изменение размера textarea
const tx = document.getElementsByTagName("textarea");
for (let i = 0; i < tx.length; i++) {
    tx[i].setAttribute("style", "height:" + (tx[i].scrollHeight) + "px;overflow-y:hidden;resize:none;");
    tx[i].addEventListener("input", onInput, false);
}
function onInput() {
    this.style.height = "auto";
    this.style.height = (this.scrollHeight) + "px";
}

/* // То же самое, что и сверху, но на jQuery
$("textarea").each(function () {
    this.setAttribute("style", "height:" + (this.scrollHeight) + "px;overflow-y:hidden;resize:none;");
}).on("input", function () {
    this.style.height = "auto";
    this.style.height = (this.scrollHeight) + "px";
});*/
