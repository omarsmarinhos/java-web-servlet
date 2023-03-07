<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label class="form-label" for="username">Username</label>
    <div class="row my-2">
        <input class="form-control" type="text" name="username" id="username">
    </div>
    <label class="form-label" for="password">Password</label>
    <div class="row my-2">
        <input class="form-control" type="password" name="password" id="password">
    </div>
    <div class="row my-2">
        <input class="btn btn-primary" type="submit" value="Login">
    </div>
</form>
<jsp:include page="layout/footer.jsp"/>
