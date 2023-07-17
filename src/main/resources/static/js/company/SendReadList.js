pageTage();
function pageTage(){
    $('.active').css('background','none');
    $('.active').css('color','rgb(255,139,38)');

}

console.log('연결됐다');


$(document).ready(function () {
    $("#summernote").summernote({
        height: 150,
        lang: "ko-KR",
        toolbar: [
            ["style", ["style"]],
            ["font", ["bold", "italic", "underline", "clear"]],
            ["fontname", ["fontname"]],
            ["color", ["color"]],
            ['insert', ['link', 'picture', 'video']],
            ["para", ["ul", "ol", "paragraph"]],
        ],
    });
});

