<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>
<c:set var="pageContent">
	<form id="form-archive">
		<input type="hidden" name="currentArchiveUserUid" 		value="${currentArchiveUserUid}">
		<input type="hidden" name="currentCategoryUid" 			value="${currentCategoryUid}">
		<input type="hidden" name="searchValue" 				value="${param.searchValue}">
		<input type="hidden" name="currentPageNumber" 			value="1">
		<input type="hidden" name="viewType" 					value="CARD">
	</form>
	<div class="article-list-header-wrapper d-flex jcb">
		<div class="d-flex aic">
			${empty categoryName ? 'All' : categoryName} (${articleTotalCount})
		</div>
		<div class="article-view-toggle-wrapper d-flex aic">
			<i class="bi bi-grid-fill <c:if test="${empty cookie.viewType.value || cookie.viewType.value eq 'CARD'}">selected</c:if>" 	role="button" onclick="fnToggleArticleView('CARD', this);"></i>
			<i class="bi bi-list <c:if test="${!empty cookie.viewType.value && cookie.viewType.value eq 'LIST'}">selected</c:if>" 		role="button" onclick="fnToggleArticleView('LIST', this);"></i>
		</div>
	</div>
	<div class="article-body-wrapper">
		<c:choose>
			<c:when test="${articleTotalCount != 0}">
				<div id="data-wrapper" class="row"></div>
			</c:when>
			<c:otherwise>
				<div class="no-data-wrapper d-flex jcc aic">
					No data
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
		let fnToggleArticleView = (viewType, element) => {
			let isCard = viewType == 'CARD';
			$('.article-card').toggleClass('hide', !isCard);
			$('.article-list').toggleClass('hide', isCard);
			$(element).addClass('selected').siblings().removeClass('selected');
			$('input[name="viewType"]').val(viewType);
			
			setCookie('viewType', viewType, '/', '86400');
		}

		$(function(){
			let isLoading = false;
			
			let fnPagination = function(){
				$.ajax({
		            type			: 'post'
		            , url			: getFullPath('/list')
		            , data			: $('#form-archive').serialize()
		            , beforeSend	: function(){
		            	let $currentPageNumber = $('input[name="currentPageNumber"]');
		            	$currentPageNumber.val(Number($currentPageNumber.val()) + 1);
		            	openLoading();
		            }
					, success		: function(data){
						$('#data-wrapper').append(data);
						setTimeout(() => {
							closeLoading();
							isLoading = false;
						}, 500);
					}
		        });
			}
			fnPagination();
			
			$(window).on('scroll.pagination', function(){
				if(isLoading) return;
				
				let $lastArticle 	= $('.article:not(.hide)').last();
				let scrollBottom 	= $(window).scrollTop() + $(window).height();
				let postOffset 		= $lastArticle.offset().top;
				
				if(scrollBottom > postOffset){
					isLoading = true;
					fnPagination();
				}
			})
		})
	</script>
</c:set>
<%@ include file="/WEB-INF/jsp/archive/common/layout.jsp" %>