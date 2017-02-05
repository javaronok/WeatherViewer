<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
  <title>Weather viewer</title>
  <jsp:include page="head.jsp" />
</head>

<link rel="stylesheet" href="<c:url value="/css/viewer.css"/>">

<body>

  <div class="container">
    <div class="row">
      <div class="col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Please Sign In</h3>
          </div>
          <div class="panel-body">
            <form role="form" action="<c:url value='/j_security_login'/>" method="POST">
              <fieldset>
                <div class="form-group">
                  <input class="form-control" placeholder="User" name="j_username" type="text" autofocus>
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Password" name="j_password" type="password" value="">
                </div>
                <!--div class="checkbox">
                  <label>
                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                  </label>
                </div-->
                <!-- Change this to a button or input when using this as a form -->
                <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="foot.jsp" />
</body>
</html>