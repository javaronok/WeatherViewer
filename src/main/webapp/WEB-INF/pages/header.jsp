<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize var="isAuth" access="isAuthenticated()" />
<sec:authentication var="principal" property="principal" />

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="<c:url value="/viewer"/>">Weather viewer</a>

  </div>

  <ul class="nav navbar-top-links navbar-right">
    <c:choose>
    <c:when test="${isAuth}">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-user fa-fw"></i> ${principal.username} <i class="fa fa-caret-down"></i>
          </a>
          <ul class="dropdown-menu dropdown-user">
            <li><a href="<c:url value="/j_security_logout" />"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
          </ul>
        </li>
    </c:when>
    </c:choose>
  </ul>

  <!-- /.navbar-header -->

  <div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
      <ul class="nav" id="side-menu">
        <li class="sidebar-search">
          <div class="input-group custom-search-form">
            <input type="text" class="form-control search-input" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default search-button" type="button">
                                  <i class="fa fa-search"></i>
                                </button>
                            </span>
          </div>
          <!-- /input-group -->
        </li>
        <li>
          <a href="<c:url value="/viewer"/>"><i class="fa fa-dashboard fa-fw"></i> Weather view</a>
          <c:choose>
          <c:when test="${!isAuth}">
            <a href="<c:url value="/login"/>"><i class="fa fa-user fa-fw"></i> SignIn</a>
          </c:when>
          </c:choose>
        </li>
      </ul>
    </div>
    <!-- /.sidebar-collapse -->
  </div>
  <!-- /.navbar-static-side -->
</nav>