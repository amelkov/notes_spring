$(document).ready(function(){
    $("#registredUser").click(function registrationUser(){
        $("#errorBox").remove();
        var username = $("#username").val();
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        var user = {"username": username,"password": password,"confirmPassword":confirmPassword};
        if(!username.trim() || !password.trim() || !confirmPassword.trim()){
            $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                "<button type='button' class='close' data-dismiss='alert'>&times;</button>All fields are required!</div>")
                .insertBefore( "#registrationForm");
        }else{
            if(password!=confirmPassword){
                $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>Passwords aren't match!</div>")
                    .insertBefore( "#registrationForm");
            }else{
                $.ajax({
                    url: "registrationUser",
                    type: 'POST',
                    data: JSON.stringify(user),
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    mimeType: 'application/json; charset=utf-8',
                    beforeSend: function (xhr) {
                        var token = $("meta[name='_csrf']").attr("content");
                        var header = $("meta[name='_csrf_header']").attr("content");
                        $(document).ajaxSend(function (e, xhr, options) {
                            xhr.setRequestHeader(header, token);
                        });
                    },
                    success: function (response) {
                        if(response!="Success"){
                            $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                                "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + response +"</div>")
                                .insertBefore( "#registrationForm");
                        }else{
                            window.location.replace("notes");
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert("error:" + textStatus + " - exception:" + errorThrown);
                    }
                })
            }
        }
    })
});
