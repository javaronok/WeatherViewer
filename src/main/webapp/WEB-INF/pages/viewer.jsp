<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Weather viewer</title>
  <jsp:include page="head.jsp" />
</head>

<body>

<input id="weatherByCity" type="hidden" value="<c:url value="/weather/weather_by_city"/>">
<input id="weatherByCords" type="hidden" value="<c:url value="/weather/weather_by_coords"/>">

<div id="wrapper">
  <jsp:include page="header.jsp"/>
  <!-- MAIN CONTENT -->
  <div id="page-wrapper">
    <div rv-show="data" class="container-fluid weather-container">
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header">Weather view</h1>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-4">
          <div class="panel panel-primary">
            <div class="panel-heading">
              Weather in {city}
            </div>
            <div class="panel-body">
              <div class="row">
                <span class="col col-2" style="display:inline-block;">
                  <img alt="Weather in Moscow, RU" src="http://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/01d.png" width="128" height="128">
                </span>
                <span class="col col-2" style="display:inline-block;">
                  <h1>-7<span>°</span></h1>
                </span>
                <span class="col col-2 col-lg-offset-1" style="display:inline-block;">
                                <div class="row">
                                    <span><strong>{data.main}</strong></span>
                                </div>
                                <div class="row">
                                    <span>{data.description}</span>
                                </div>
                </span>
              </div>
            </div>
            <div class="panel-footer">
              Open weather map
            </div>
          </div>
        </div>
      </div>

      <!-- END MAIN CONTENT -->
    </div>
  </div>
<script src="<c:url value="/js/viewer.js"/>"></script>

<jsp:include page="foot.jsp" />
</body>
</html>