<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<c:set var="pageContent">
				<script>
					$(function(){
						new Signin()
					})
				</script>
	<div class="sign-body-wrapper d-flex jcc aic">
		<div class="field-group-wrapper">
			<div class="d-flex jcc aic">Sign In</div>
			
			<div class="field-wrapper">
				<input type="email" id="email" class="form-control" placeholder="email" autocomplete="off">
			</div>
			
			<div class="field-wrapper">
				<input type="password" id="password" class="form-control" placeholder="password" oninput="this.value = this.value.replace(/[^A-Za-z0-9!@#$%^&*(),.?:{}|<>_-]/g, '')">
			</div>
			
			<div class="field-wrapper">
				<div class="forgot-password">
					Forgot password?
				</div>
				<div class="not-registered" onclick="goToPage('<c:url value="/sign/signup"/>')">
					Not registered yet?
				</div>
			</div>
			
			<div class="field-wrapper d-flex jcc">
				<button type="button" id="btn-signin" class="btn btn-outline-dark">Sign in</button>
			</div>
		</div>
	</div>
</c:set>

<%@ include file="/WEB-INF/jsp/home/common/layout.jsp" %>
