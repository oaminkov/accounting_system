$(document).ready(function () {
    //Select2 init
    $('.select2').select2();

    //bsCustomFileInput init
    bsCustomFileInput.init();

    // Автоматическое изменение размера textarea
    $("textarea").each(function () {
        this.setAttribute("style", "height:" + (this.scrollHeight) + "px;overflow-y:hidden;resize:none;");
    }).on("input", function () {
        this.style.height = "auto";
        this.style.height = (this.scrollHeight) + "px";
    });
});