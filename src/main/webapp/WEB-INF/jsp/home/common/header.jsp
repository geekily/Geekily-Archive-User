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
				<a href="#" onclick="goToPage('/${sessionScope.userMap.myArchiveUrlPath}')">My archive</a>
				<a href="#" onclick="goToPage('/sign/signout')">Sign out</a>
			</c:when>
			<c:otherwise>
				<a href="#" onclick="goToPage('/sign/signin')">Sign In</a>
				<a href="#" onclick="goToPage('/sign/signup')">Sign Up</a>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- sign :: e -->
</div>
<!-- header :: e -->