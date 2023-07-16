$(document).ready(function () {
    init();

    onShowAlert();

    $(".nav-link").on('click', function () {
        document.location.hash = $(this).attr('href');
    });

    // Check the initial state of the div
    if ($('#alertDiv').is(':visible')) {
        onDivVisible();
    }
});


function showNoteModal(noteId, noteTitle, noteDescription) {
    if(noteId !== undefined){
        $("#noteForm").prepend("<input type='hidden' name='_method' value='PUT'>");
    }
    $('#note-id').val(noteId ? noteId : '');
    $('#note-title').val(noteTitle ? noteTitle : '');
    $('#note-description').val(noteDescription ? noteDescription : '');
    $('#noteModal').modal('show');
}

// For opening the credentials modal
function showCredentialModal(credentialId, url, username, password) {
    $('#credential-id').val(credentialId ? credentialId : '');
    $('#credential-url').val(url ? url : '');
    $('#credential-username').val(username ? username : '');
    $('#credential-password').val(password ? password : '');
    $('#credentialModal').modal('show');
}

function init() {
    var hash = window.location.hash;
    if (hash) {
        $('.nav-link[href="' + hash + '"]').tab('show');
        $(hash).addClass('show');
    } else {
        $("#nav-files").addClass("show");
    }
}

const onShowAlert= () => {


}

function onDivVisible() {
    console.log("The div is now visible");
    setTimeout(() => {
        $('#alertDiv').hide();
    }, 2000);
}


