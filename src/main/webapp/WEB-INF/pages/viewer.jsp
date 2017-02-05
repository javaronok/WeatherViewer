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

<input id="weatherByCity" type="hidden" value="<c:url value="/weather/weather_by_city"/>">
<input id="weatherByCoords" type="hidden" value="<c:url value="/weather/weather_by_coords"/>">

<div id="wrapper">
  <jsp:include page="header.jsp"/>
  <!-- MAIN CONTENT -->
  <div id="page-wrapper">
    <div class="container-fluid weather-container">
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header">Weather view</h1>
        </div>
      </div>

      <div rv-show="error" class="alert alert-danger fade in">
        <button class="close" data-dismiss="alert">×</button>
        <strong>Error:</strong> {error.errorText}
      </div>

      <div rv-show="data" class="row">
        <div class="col-lg-6">
          <div class="panel panel-primary">
            <div class="panel-heading">
              Weather in {data.name}
            </div>
            <div class="panel-body">
              <div class="row">
                <span class="col col-2 weather-item clearfix">
                  <img rv-walt="data.name" rv-wsrc="data.weather.icon" width="128" height="128">
                </span>
                <span class="col col-2 weather-item clearfix">
                  <h1>{data.main.temp}<span>°</span></h1>
                </span>
                <span class="col col-2 col-lg-offset-1 weather-item clearfix">
                                <div class="row">
                                    <span><strong>{data.weather.main}</strong></span>
                                </div>
                                <div class="row">
                                    <span>{data.weather.description}</span>
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

  <div id='ajax_loader' style="position: fixed; left: 50%; top: 50%; display: none;">
      <img src="<c:url value="/img/ajax-loader.gif"/>"/>
  </div>

<script src="<c:url value="/js/viewer.js"/>"></script>

<jsp:include page="foot.jsp" />
</body>
</html>