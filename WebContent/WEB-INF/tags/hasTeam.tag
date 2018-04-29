<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%
	HttpSession httpSession = request.getSession(true);
	if ((httpSession != null) && (httpSession.getAttribute("authTeam") != null)
			&& (httpSession.getAttribute("authStdUser") != null)) {
%>
<jsp:doBody />
<%
	}
%>