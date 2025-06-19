<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta property="og:url" content="https://archive.geekive.com">
    <meta property="og:title" content="Geekive Archive">
    <meta property="og:type" content="website">
    <meta property="og:image" content="https://archive.geekive.com/storage/image/common/meta_logo.png">
    <meta property="og:description" content="The simplest blog.">
    
    <link rel="icon" href="<c:url value="/image/favicon.ico"/>">
    <link rel="apple-touch-icon" href="<c:url value="/image/favicon.ico"/>">
    
	<title>${empty currentArchiveLogo ? 'Geekive Archive Home' : currentArchiveLogo}</title>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrapIcon/font/bootstrap-icons.min.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/swiper/swiper-bundle.min.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
	
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jquery/jquery-3.7.1.min.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/bootstrap/popper.min.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/bootstrap/bootstrap.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/swiper/swiper-bundle.min.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/tool.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/sign.js"/>"></script>
	
	<!-- prism :: s -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/plugins/line-numbers/prism-line-numbers.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/themes/prism-tomorrow.min.css">
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/prism.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/plugins/line-numbers/prism-line-numbers.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/components/prism-java.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/components/prism-javascript.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/components/prism-markup.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/components/prism-properties.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/components/prism-css.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/prismjs@1.30.0/components/prism-nginx.min.js"></script>
	<!-- prism :: e -->
</head>