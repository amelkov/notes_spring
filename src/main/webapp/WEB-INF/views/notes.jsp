<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/customstyles.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/notes.js"/>"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div id="header">
    <%@ include file="header.jspf" %>
</div>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">
            <table class="table table-striped table-bordered" id="noteTable">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Note</th>
                    <th/>
                </tr>
                </thead>
                <tbody id="noteTableBody">
                </tbody>
            </table>
            <br>
            <textarea class="form-control custom-control" id="noteText" rows="3" style="resize:vertical" placeholder="Enter your note"></textarea>
            <br>
            <button class="btn btn-success" id="addNote">Add note</button>
            <input hidden id="selectedNote" />
            <button class="btn btn-warning" id="editNote">Update note</button>
            <input hidden id="switcher" value="last" />
            <button class="btn btn-info" id="switcherNote" style="float: right;">Show all notes</button>
        </div>
    </div>
</div>
</body>
</html>
