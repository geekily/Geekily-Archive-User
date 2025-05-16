<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>
<div style="font-family: Arial, sans-serif; font-size: 16px; color: #000; margin: 0; padding: 0; background-color: #fff; height: 60vh; display: flex; justify-content: center; align-items: center;">
    <div style="width: 100%; max-width: 600px; padding: 20px; background-color: #fff; border: 2px solid #000;">
        <div style="text-align: center; margin-bottom: 20px; user-select: none;">
            <h1 style="color: #000; font-size: 24px;"><spring:message code="email.common.greeting"/></h1>
        </div>
        <div style="margin-bottom: 20px; text-align: center; user-select: none;">
            <p style="font-size: 16px; color: #000;">{{message}}</p>
        </div>
        <div style="padding: 10px; font-size: 20px; text-align: center; font-weight: bold; background-color: #fff; color: #000; user-select: text;">
            <span>{{code}}</span>
        </div>
        <div style="margin-top: 20px; text-align: center; user-select: none;">
            <p style="font-size: 14px; color: #000;"><spring:message code="email.common.ignore"/></p>
        </div>
        <div style="margin-top: 30px; text-align: center; user-select: none;">
            <p style="font-size: 14px; color: #000;"><spring:message code="email.common.regards"/></p>
            <p style="font-size: 14px; color: #000;"><spring:message code="email.common.signature"/></p>
        </div>
    </div>
</div>