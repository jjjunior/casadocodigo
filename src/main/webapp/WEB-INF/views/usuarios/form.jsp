<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de Usuarios">

    <section id="index-section" class="container middle">
    <div class="container">

        <c:if test="${not empty message }">
            <h1 class="cdc-call">${message }</h1>
        </c:if>

        <h2 id="cart-title">Cadastro de Usuários</h2>
        <form:form action="${s:mvcUrl('UC#gravarUsuario').build() }" method="post" commandName="usuario" cssClass="container">
            <div class="form-group">
                <form:label path="nome">Nome</form:label>
                <form:input path="nome" cssClass="form-control" />
                <form:errors path="nome" />
            </div>
            <div class="form-group">
                <form:label path="email">Email</form:label>
                <form:input path="email" cssClass="form-control" />
                <form:errors path="email" />
            </div>
            <div class="form-group">
                <form:label path="senha">Senha</form:label>
                <form:password path="senha" cssClass="form-control" />
                <form:errors path="senha" />
            </div>
            <div class="form-group">
                <form:label path="senhaRepetida">SenhaRepetida</form:label>
                <form:password path="senhaRepetida" cssClass="form-control" />
                <form:errors path="senhaRepetida" />
            </div>

            <button type="submit" class="btn btn-primary">Cadastrar</button>
        </form:form>
    </div>
    </section>
</tags:pageTemplate>