<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Alteração de Roles">

    <section id="index-section" class="container middle">
        <div class="container">

            <c:if test="${not empty message }">
                <h1 class="cdc-call">${message }</h1>
            </c:if>

            <form:form action="${s:mvcUrl('UC#alterarRoles').arg(0, usuario).arg(1,usuario.email).build() }" method="post" commandName="usuario" cssClass="container">
                <h2 id="cart-title">Cadastro de Persmissões para ${usuario.nome}</h2>
                <div class="form-group">
                    <label>Permissões:</label>
                    <form:checkboxes path="roles" items="${roles}"></form:checkboxes>
                    <form:errors path="roles"/>
                </div>
                <button type="submit" class="btn btn-primary">Atualizar</button>
            </form:form>
        </div>
    </section>
</tags:pageTemplate>