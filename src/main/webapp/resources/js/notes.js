$(document).ready(function () {
    $('#addNote').click(function addNote() {
        var note = $('#noteText').val();
        var json = {"text": note};
        $("#errorBox").remove();
        $("#messageBox").remove();
        if (!note.trim()) {
            $("<div id='errorBox' class='alert alert-danger fade in'>" +
                "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note can't be empty!</div>")
                .insertBefore("#noteText");
            $("#noteText").val('');
        } else {
            $.ajax({
                url: "notes/addNote",
                type: 'POST',
                data: JSON.stringify(json),
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
                    if (response != "Success") {
                        $("<div id='errorBox' class='alert alert-danger fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + response + "</div>")
                            .insertBefore("#noteText");
                    } else {
                        $("<div id='messageBox' class='alert alert-success fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note successfully added</div>")
                            .insertBefore("#noteText");
                    }
                    getNotes();
                    $("#noteText").val('');
                    $("#selectedNote").removeAttr("name");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error:" + textStatus + " - exception1:" + errorThrown + " " + jqXHR);
                }
            })
        }
    })

    $('#editNote').click(function editNote() {
        var id = $("#selectedNote").attr('name');
        var note = $("#noteText").val();
        var json = {"id": id, "text": note};
        $("#errorBox").remove();
        $("#messageBox").remove();
        if (!note.trim()) {
            $("<div id='errorBox' class='alert alert-danger fade in'>" +
                "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note can't be empty!</div>")
                .insertBefore("#noteText");
            $("#noteText").val('');
        } else {
            $.ajax({
                url: "notes/editNote",
                type: 'PUT',
                data: JSON.stringify(json),
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
                    if (response != "Success") {
                        $("<div id='errorBox' class='alert alert-danger fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + response + "</div>")
                            .insertBefore("#noteText");
                    } else {
                        $("<div id='messageBox' class='alert alert-success fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note successfully updated</div>")
                            .insertBefore("#noteText");
                    }
                    getNotes();
                    $("#addNote").show();
                    $("#editNote").hide();
                    $("#noteText").val('');
                    $("#selectedNote").removeAttr("name");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error:" + textStatus + " - exception2:" + errorThrown);
                }
            })
        }
    })

    $("#switcherNote").click(function switcherNote() {
        $("#errorBox").remove();
        $("#messageBox").remove();
        if ($("#switcher").val() === "last") {
            $("#switcher").val("all");
            getNotes();
            $("#switcherNote").html('Show last notes');
        } else {
            $("#switcher").val("last");
            getNotes();
            $("#switcherNote").html('Show all notes');
        }
    })

});

$(document).ready(function ready() {
    $("#editNote").hide();
    getNotes();
});

function getNotes() {
    var url;
    if ($("#switcher").val() === "last") {
        url = "notes/getLastNotes";
    } else {
        url = "notes/getAllNotes";
    }
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        beforeSend: function (xhr) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        },
        success: function (response) {
            $("#noteTableBody").empty();
            var table = $("#noteTable tbody");
            $.each(response, function (idx, elem) {
                var date = new Date(elem.dateCreate);
                table.append("<tr><td style='display:none'>" +
                    elem.id + "</td>" +
                    "<td width='200'>" + date +
                    "<td>" + elem.text + "</td>" +
                    "<td width='55'><a onclick='selectNote(this)'><span class='glyphicon glyphicon-pencil' data-toggle='tooltip' title='Edit note'/></a>   " +
                    "<a onclick='deleteNote(this)'><span class='glyphicon glyphicon-remove' data-toggle='tooltip' title='Delete note'/></td></tr>");
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " - exception4:" + errorThrown);
        }
    })
};

function selectNote(tmp) {
    var id = +($(tmp).parents('tr:first').find('td:first').text());
    var note = {"id": id};
    $("#errorBox").remove();
    $("#messageBox").remove();
    $.ajax({
        url: "notes/getNote",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(note),
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
            if (response === null) {
                $("<div id='errorBox' class='alert alert-danger fade in'>" +
                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>This note is not found!</div>")
                    .insertBefore("#noteText");
                getNotes();
            } else {
                $("#addNote").hide();
                $("#editNote").show();
                $("#selectedNote").attr("name", response.id);
                $("#noteText").val(response.text);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " - exception5:" + errorThrown);
        }
    })
};

function deleteNote(tmp) {
    var id = +($(tmp).parents('tr:first').find('td:first').text());
    var json = {"id": id};
    $("#errorBox").remove();
    $("#messageBox").remove();
    $.ajax({
        url: "notes/deleteNote",
        type: 'DELETE',
        data: JSON.stringify(json),
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
            if (response != "Success") {
                $("<div id='errorBox' class='alert alert-danger fade in'>" +
                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + response + "</div>")
                    .insertBefore("#noteText");
            } else {
                $("<div id='messageBox' class='alert alert-success fade in'>" +
                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note successfully deleted</div>")
                    .insertBefore("#noteText");
            }
            getNotes();
            $("#addNote").show();
            $("#editNote").hide();
            $("#noteText").val('');
            $("#selectedNote").removeAttr("name");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " - exception3:" + errorThrown);
        }
    })
};