<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:forward page="singleLogin!run.do?ac=loginWithUserId">
	<jsp:param name="ivuser" value='<%= request.getHeader("iv-user")%>' />
</jsp:forward>
