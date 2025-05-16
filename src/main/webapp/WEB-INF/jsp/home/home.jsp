<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<c:set var="pageContent">
	<div class="section row">
		<div class="new-article-wrapper col-lg-8">
			<div class="section-title">
				<spring:message code="home.body.newarticle"/>
			</div>
			 <div class="newest-article-wrapper d-flex jcs" onclick="goToPage('${newArticleList[0].articleUrlPath}')">
				<img src="${newArticleList[0].thumbnailUrlPath}" onerror="getErrorImageUrl(this, 'thumbnail')">
				<div class="content-wrapper">
					<div class="title">
						${newArticleList[0].title}
					</div>
					<div class="content">
						${newArticleList[0].content}
					</div>
					<div class="date d-flex aic">
						${newArticleList[0].registrationDate}
					</div>
				</div>
			</div>
			<c:forEach items="${newArticleList}" var="newArticle" begin="1">
				<div class="recent-article-wrapper d-flex aic jcs" onclick="goToPage('${newArticle.articleUrlPath}')">
					<img src="${newArticle.thumbnailUrlPath}" onerror="getErrorImageUrl(this, 'thumbnail')">
					<div class="content-wrapper">
						<div class="title">
							${newArticle.title}
						</div>
						<div class="date">
							${newArticle.registrationDate}
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div class="random-archvie-wrapper col-lg-4">
			<div class="section-title">
				<spring:message code="home.body.exploremorearchives"/>
			</div>
			
			<div class="swiper random-archive-swiper"> 
			  <div class="swiper-wrapper">
			    <c:forEach items="${randomArchiveList}" var="randomArchive">
			    	<div class="swiper-slide d-flex aic" onclick="goToPage('/${randomArchive.archiveUrlPath}')">
			    		<img class="rounded-circle" src="${randomArchive.profileImagelUrlPath}" onerror="getErrorImageUrl(this, 'profile')">
			    		<font>${randomArchive.archiveLogo}</font>
			    	</div>
			    </c:forEach>
			    <c:forEach items="${randomArchiveList}" var="randomArchive">
			    	<div class="swiper-slide d-flex aic" onclick="goToPage('/${randomArchive.archiveUrlPath}')">
			    		<img class="rounded-circle" src="${randomArchive.profileImagelUrlPath}" onerror="getErrorImageUrl(this, 'profile')">
			    		<font>${randomArchive.archiveLogo}</font>
			    	</div>
			    </c:forEach>
			    <c:forEach items="${randomArchiveList}" var="randomArchive">
			    	<div class="swiper-slide d-flex aic" onclick="goToPage('/${randomArchive.archiveUrlPath}')">
			    		<img class="rounded-circle" src="${randomArchive.profileImagelUrlPath}" onerror="getErrorImageUrl(this, 'profile')">
			    		<font>${randomArchive.archiveLogo}</font>
			    	</div>
			    </c:forEach>
			    <c:forEach items="${randomArchiveList}" var="randomArchive">
			    	<div class="swiper-slide d-flex aic" onclick="goToPage('/${randomArchive.archiveUrlPath}')">
			    		<img class="rounded-circle" src="${randomArchive.profileImagelUrlPath}" onerror="getErrorImageUrl(this, 'profile')">
			    		<font>${randomArchive.archiveLogo}</font>
			    	</div>
			    </c:forEach>
			  </div>
			</div>
		
		</div>
	</div>

	<script>
		const swiper = new Swiper(".random-archive-swiper", {
			  direction: 'vertical',
			  slidesPerView: 6,        // 한 화면에 5개
			  slidesPerGroup: 1,       // 한 번에 1개씩 이동
			  loop: true,
			  speed: 800,
			  autoplay: {
			    delay: 2000,
			    reverseDirection: true,   // 아래 → 위
			    disableOnInteraction: false
			  }
			});
	</script>
</c:set>

<%@ include file="/WEB-INF/jsp/home/common/layout.jsp" %>
