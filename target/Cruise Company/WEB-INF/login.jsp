<%@ include file="components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_guest.jsp"/>
<!-- LoginCommand Form -->
<section class="container-fluid">
    <section class="row justify-content-center">
        <section class="col-12 col-sm-6 col-md-3">
            <form class="needs-validation form-container" novalidate method="post" action="${pageContext.request.contextPath}/guest/login">
                <h4 class="text-center font-weight-bold"><fmt:message key="signin_form_message"/></h4>
                <div class="form-group">
                    <input type="text" name="login" class="form-control" pattern="^[A-Za-z0-9_-]{3,16}$" placeholder="<fmt:message key="login_input_message"/>" required>
                    <div class="invalid-feedback">
                        <fmt:message key="invalid_login_input_message"/>
                    </div>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" pattern="^[A-Za-z0-9_-]{3,18}$" placeholder="<fmt:message key="password_input_message"/>" required>
                    <div class="invalid-feedback">
                        <fmt:message key="invalid_password_input_message"/>
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-warning btn-block font-weight-bold"><fmt:message key="submit_button"/></button>
                </div>
            </form>
        </section>
    </section>
</section>
<jsp:include page="/WEB-INF/components/footer.jsp"/>
