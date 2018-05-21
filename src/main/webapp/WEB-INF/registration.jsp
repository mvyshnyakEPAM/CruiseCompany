<%@ include file="components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_guest.jsp"/>

<!-- LoginCommand Form -->
<section class="container-fluid">
    <section class="row justify-content-center">
        <section class="col-12 col-sm-6 col-md-3">
            <form class="needs-validation form-container" novalidate method="post" action="${pageContext.request.contextPath}/company/register">
                <h4 class="text-center font-weight-bold"><fmt:message key="signup_form_message"/></h4>
                <br>
                <div class="form-group">
                    <input type="text" name="login" class="form-control" pattern="^[A-Za-z0-9_-]{3,16}$" placeholder="<fmt:message key="login_input_message"/>" required>
                    <c:if test="${not empty requestScope.messages}">
                        <fmt:message key="${requestScope.messages['loginMismatch']}"/>
                    </c:if>
                    <div class="invalid-feedback">
                        <fmt:message key="invalid_login_input_message"/>
                    </div>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" pattern="^[A-Za-z0-9_-]{3,18}$" placeholder="<fmt:message key="password_input_message"/>" required>
                    <c:if test="${not empty requestScope.messages}">
                        <fmt:message key="${requestScope.messages['passwordMismatch']}"/>
                    </c:if>
                    <c:if test="${not empty requestScope.message}">
                        <span style="font-size: small; color: red;"><fmt:message key="${requestScope.message}"/></span>
                    </c:if>
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

<script>
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

<jsp:include page="/WEB-INF/components/footer.jsp"/>
