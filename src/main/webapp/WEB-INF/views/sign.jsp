<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/customstyles.css"/>">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div id="header">
    <%@ include file="header.jspf" %>
</div>
<br>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Log In
                </div>
                <div class="panel-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger fade in">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                                ${error}
                        </div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="alert alert-success fade in">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                                ${msg}
                        </div>
                    </c:if>
                    <c:url value="/login" var="loginUrl" />
                    <form class="form-horizontal" role="form" name="loginForm"
                          action="${loginUrl}" method='POST'>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                        <div class="form-group">
                            <label for="username" class="col-sm-3 control-label">Login</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="username"
                                       placeholder="Login" required name="username">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="password"
                                       placeholder="Password" required name="password">
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-success btn-sm">
                                    Log in</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
