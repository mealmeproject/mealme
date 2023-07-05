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


//
// new_note_data = '<table><tr><td>test value</td></tr></table>'
//
// $('#summernote').summernote('reset');
// $('#summernote').summernote('destroy');
// $('#summernote').val(new_note_data);
//
// $('#summernote').summernote({
//     airMode: true
// });


