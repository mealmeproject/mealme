$(document).ready(function() {
    $('#summernote').summernote({
        height: 300,
        lang: 'ko-KR',
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'italic', 'underline', 'clear']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['insert', ['link', 'picture', 'video']],
            ['para', ['ul', 'ol', 'paragraph']],
        ],
    });
});





    function redirectToSendReadList() {
    window.location.href = "http://localhost:10000/company/SendReadList";
}
