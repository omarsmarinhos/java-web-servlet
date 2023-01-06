<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/usuarios/form" method="post">
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="username">Username</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="username" id="username"
                   value="${usuario.username}">
        </div>
        <c:if test="${errores != null && errores.containsKey('username')}">
            <div style="color: red;">${errores.username}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="password">Password</label>
        <div class="col-sm-4">
            <input class="form-control" type="password" name="password" id="password"
                   value="${usuario.password}">
        </div>
        <c:if test="${errores != null && not empty errores.password}">
            <div style="color: red;">${errores.password}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="email">Email</label>
        <div class="col-sm-4">
            <input class="form-control" type="email" name="email" id="email"
                   value="${usuario.email}">
        </div>
        <c:if test="${errores != null && not empty errores.email}">
            <div style="color: red;">${errores.email}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit"
                   value="${usuario.id != null && usuario.id > 0 ? "Editar" : "Crear"}">
        </div>
    </div>
    <input type="hidden" name="id" value="${usuario.id}">
</form>
<jsp:include page="layout/footer.jsp"/>

