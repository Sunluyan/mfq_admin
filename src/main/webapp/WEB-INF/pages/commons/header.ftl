<!DOCTYPE html>
<#include "/commons/pager.ftl">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="imagetoolbar" content="no">
<link rel="shortcut icon" href="/favicon.ico" >
<title>${_title!} 超级系统管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<#if !_bootstrap3>
    <link href="/static/css/bootstrap.min.css?2.3.2" rel="stylesheet" media="screen">
</#if>
<style type="text/css">
  body { padding-top: 60px; padding-bottom: 40px;}
</style>
<#if _bootstrap3>
    <link href="/static/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/bootstrap-3.3.4/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/static/bootstrap-3.3.4/css/bootstrap-submenu.css" rel="stylesheet">
<#else>
    <link href="/static/css/bootstrap-responsive.min.css?2.3.2" rel="stylesheet">
</#if>
<link href="/static/css/bootstrap-datepicker.min.css?1.4.0" rel="stylesheet">
<link href="/static/font-awesome/css/font-awesome.min.css?3.2" rel="stylesheet">
<link href="/static/css/plugin/jquery-ui.css?20130403" rel="stylesheet">
<link href="/static/css/admin.css?20150715" rel="stylesheet">
<script type="text/javascript" src="/static/js/jquery.min.js?1.10.1"></script>
<script type="text/javascript" src="/static/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.cookie.js"></script>
<#if _bootstrap3>
    <script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap.min.js"></script>
<#else>
    <script type="text/javascript" src="/static/js/bootstrap.min-2.3.1.js"></script><!-- 2.3.2有BUG，下拉菜单无法点击 -->
</#if>
<script type="text/javascript" src="/static/js/bootstrap-plugins.js?20140208"></script>
<script type="text/javascript" src="/static/js/bootstrap-datepicker.min.js?1.4.0"></script>
<script type="text/javascript" src="/static/js/bootstrap-datepicker.zh-CN.min.js?1.4.0"></script>
<script type="text/javascript" src="/static/js/angular1.3.0.min.js"></script>
<script type="text/javascript" src="/static/js/angular-route.min.js"></script>

<#if _csss><#list _csss as _css><link rel="stylesheet" type="text/css" href="${_css}"></#list></#if>
<#if _jss><#list _jss as _js><script type="text/javascript" src="${_js}"></script></#list></#if>
</head>
<body>
