<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/customstyles.css"/>">
  <script type="text/javascript" src="<c:url value="/resources/js/registration.js"/>"></script>
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
    <div class="col-md-5">
      <div class="panel panel-default">
        <div class="panel-heading">Registration</div>
        <div class="panel-body">
          <c:if test="${not empty error}">
            <div class="alert alert-danger fade in">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
                ${error}
            </div>
          </c:if>
          <form class="form-horizontal" role="form" id="registrationForm" name="registrationForm">
            <div class="form-group">
              <label  for="username"
                      class="col-sm-2 control-label">Login</label>
              <div class="col-sm-9">
                <input type="text" class="form-control" id="username" required="true"
                       placeholder="Login" name="username" />
              </div>
            </div>
            <div class="form-group">
              <label  for="password"
                      class="col-sm-2 control-label">Password</label>
              <div class="col-sm-9">
                <input type="password" class="form-control" id="password" required="true"
                       placeholder="Password" name="password" />
              </div>
            </div>
            <div class="form-group">
              <label for="confirmPassword"
                     class="col-sm-2 control-label">Confirm Password</label>
              <div class="col-sm-9">
                <input type="password" class="form-control" id="confirmPassword" required="true"
                       placeholder="Please confirm your password" name="confirmPassword"  />
              </div>
            </div>
            <div class="form-group last">
              <div class="col-sm-offset-2 col-sm-9">
                <button type="button" id="registredUser" name="registredUser"
                        class="btn btn-success btn-sm">Register</button>
                <button type="reset" class="btn btn-default btn-sm">Reset</button>
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
