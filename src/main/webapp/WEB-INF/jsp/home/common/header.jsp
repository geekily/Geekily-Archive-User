<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<!-- header :: s -->
<div class="home-header-wrapper container d-flex jcb">
	<!-- logo :: s -->
	<div class="logo-wrapper d-flex aic" onclick="goToPage('/')">
		Geekily Archive
	</div>
	<!-- logo :: e -->
	
	<!-- sign :: s -->
	<div class="sign-wrapper d-flex jcb aic">
		<c:choose>
			<c:when test="${sessionScope.isSignedIn}">
				<a href="#" onclick="goToPage('/${sessionScope.userMap.myArchiveUrlPath}')"><spring:message code="home.header.myarchive"/></a>
				<a href="#" onclick="goToPage('/sign/signout')"><spring:message code="home.header.signout"/></a>
			</c:when>
			<c:otherwise>
				<a href="#" onclick="goToPage('/sign/signin')"><spring:message code="home.header.signin"/></a>
				<a href="#" onclick="goToPage('/sign/signup')"><spring:message code="home.header.signup"/></a>
			</c:otherwise>
		</c:choose>
		
		<!-- language :: s -->
		<a href="#" class="dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false"><spring:message code="home.header.language"/></a>
		<ul class="dropdown-menu">
		  <li><a class="dropdown-item" href="#" onclick="fnChangeLanguage('en')"><spring:message code="home.header.language.english"/></a></li>
		  <li><a class="dropdown-item" href="#" onclick="fnChangeLanguage('ko')"><spring:message code="home.header.language.korean"/></a></li>
		</ul>
		<script>
			let fnChangeLanguage = function(value){
				setCookie('lang', value, '/', '2592000');
				location.reload();
			}
		</script>
		<!-- language :: e -->
	</div>
	<!-- sign :: e -->
</div>
<!-- header :: e -->