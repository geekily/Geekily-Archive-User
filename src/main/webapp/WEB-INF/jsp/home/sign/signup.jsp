<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>

<c:set var="pageContent">
	<script>
		$(function(){
			new Signup()
		})
	</script>
	<div class="sign-body-wrapper d-flex jcc aic">
		<div class="field-group-wrapper">
			<div class="d-flex jcc aic">Sign Up</div>
			
			<div class="field-wrapper">
				<input type="email" id="email" class="form-control" placeholder="email" autocomplete="off" maxlength="254">
			</div>
			
			<div class="field-wrapper d-flex">
				<div class="code-wrapper">
				    <input type="text" id="code" class="form-control" placeholder="code" autocomplete="off" readonly="readonly" maxlength="6" oninput="this.value = this.value.replace(/[^0-9]/g, '')">
				    <span id="codeTimer">3:00</span>
				</div>
				<button type="button" id="btn-send-code" class="btn btn-outline-dark">Send</button>
				<button type="button" id="btn-resend-code" class="hide btn btn-outline-dark" disabled>Resend</button>
				<button type="button" id="btn-check" class="btn btn-outline-dark" disabled>Check</button>
			</div>
			
			<div class="field-wrapper">
				<input type="password" id="password" class="form-control" readonly="readonly" placeholder="password (Min 10 chars, incl. letters, numbers & symbols.)" oninput="this.value = this.value.replace(/[^A-Za-z0-9!@#$%^&*(),.?:{}|<>_-]/g, '')">
			</div>
			
			<div class="field-wrapper">
				<input type="password" id="passwordCheck" class="form-control" readonly="readonly" placeholder="check password">
			</div>
			
			<div class="field-wrapper">
				<input type="text" id="userName" class="form-control" readonly="readonly" placeholder="your name" maxlength="20" oninput="this.value = this.value.replace(/[^^\p{L}\s]/gu, '').replace(/\s{2,}/g, ' ');">
			</div>
			
			<div class="field-wrapper">
				<input type="text" id="archiveName" class="form-control" readonly="readonly" placeholder="archive name (Max 12 chars, English & numbers only.)" maxlength="12" oninput="this.value = this.value.replace(/[^a-zA-Z0-9 ]/g, '').replace(/\s{2,}/g, ' ').toLowerCase();">
			</div>
			
			<div class="field-wrapper d-flex jcc">
				<button type="button" id="btn-save" class="btn btn-outline-dark" disabled>Save</button>
			</div>
		</div>
	</div>
</c:set>

<%@ include file="/WEB-INF/jsp/home/common/layout.jsp" %>
