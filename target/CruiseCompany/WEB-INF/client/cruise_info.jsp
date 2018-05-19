<%@ include file="../components/include.jsp"%>

<jsp:include page="/WEB-INF/components/head.jsp"/>
<jsp:include page="/WEB-INF/components/header.jsp"/>
<jsp:include page="/WEB-INF/components/menu_client.jsp"/>

<!-- Nav tabs -->
<div id="content" style="padding-top: 50px">
    <div class="container" id="cruise-info">
        <div class="row">
            <div class="col-xl-12" align="center" style="border:1px solid #333; font-weight: bold; font-size: larger; padding: 5px">
                ${sessionScope.cruise.name} (${sessionScope.cruise.shipClass}) - ${sessionScope.cruise.cruiseDuration} days
            </div>
        </div>
        <div class="row">
            <div class="col-xl-9" style="border:1px solid #333; font-size: small">
                <div style="padding: 5px">
                    <img src="<c:url value='/resources/images/logo_en_lg1.png'/>" class="img-resp" align="left">
                    <span style="font-weight: bold">Ports visited: </span> ${sessionScope.cruise.portsVisited}<br>
                    <span style="font-weight: bold">Departure: </span><ctg:format-date date="${sessionScope.cruise.departure}" locale="${sessionScope.language}"/><br>
                    <span style="font-weight: bold">Arrival: </span><ctg:format-date date="${sessionScope.cruise.arrival}" locale="${sessionScope.language}"/><br>
                    <span style="font-weight: bold">Passenger capacity: </span>${sessionScope.cruise.passengerCapacity}<br>
                    <span style="font-weight: bold">Staff: </span>${sessionScope.cruise.staff}<br>
                    <button type="button" class="btn btn-warning btn-sm font-weight-bold" data-toggle="modal" data-target=".bd-example-modal-sm">Bonuses</button>
                    <div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <ul class="list-group">
                                    <c:forEach var="bonus" items="${sessionScope.cruise.bonuses}">
                                        <li class="list-group-item font-weight-bold"><fmt:message key="${bonus.key}"/></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3" align="center" style="border:1px solid #333; vertical-align: text-bottom">
                <div style="padding: 5px; font-size: large">
                    <p style="font-weight: bold">${sessionScope.cruise.freePlaces} places</p>
                    <p style="font-weight: bold"><fmt:formatNumber type="currency" currencySymbol="$" value="${requestScope.price}" /></p>
                    <p><a href="${pageContext.request.contextPath}/company/pay-cruise" class="btn btn-warning font-weight-bold">Pay</a></p>
                </div>
            </div>
        </div>
    </div>
    <%--<div class="container">
        <table class="table table-bordered">
            <thead>
            <tr class="bg-primary text-white">
                <th>Cruise Name</th>
                <th>Bonuses</th>
                <th>Departure</th>
                <th>Arrival</th>
                <th>Duration</th>
                <th>Class</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${sessionScope.cruise.name}</td>
                    <td>
                        <c:forEach var="bonus" items="${sessionScope.cruise.bonuses}">
                            <fmt:message key="${bonus.key}"/>;
                        </c:forEach>
                    </td>
                    <td><ctg:format-date date="${sessionScope.cruise.departure}" locale="${sessionScope.language}"/></td>
                    <td><ctg:format-date date="${sessionScope.cruise.arrival}" locale="${sessionScope.language}"/></td>
                    <td>${sessionScope.cruise.cruiseDuration}</td>
                    <td><fmt:message key="${sessionScope.cruise.shipClass.key}"/> </td>
                    <td>${requestScope.price}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/company/client/pay-cruise" class="btn btn-warning font-weight-bold">Pay</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>--%>
    <br>
    <div class="container">
        <div class="row no-gutter">
            <c:forEach var="port" items="${sessionScope.cruise.ports}">
                <div class="col">
                    <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#${port.nameEn}">${port.name}</button>
                    <div class="modal fade bd-example-modal-lg modal-open" id="${port.nameEn}" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="container">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr class="bg-primary text-white">
                                            <th>Excursion</th>
                                            <th>Price</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="excursion" items="${port.excursions}">
                                            <tr>
                                                <td>${excursion.name}</td>
                                                <td>${excursion.price}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/company/add-excursion?excursion=${excursion.nameEn}" class="btn btn-warning font-weight-bold">Add</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br>
    <div class="container">
        <table class="table table-bordered table-sm">
            <c:if test="${not empty requestScope.excursions}">
                <thead>
                <tr class="bg-primary text-white">
                    <th>Excursion</th>
                    <th>Port</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                </thead>
            </c:if>
            <tbody>
                <c:forEach var="excursion" items="${requestScope.excursions}">
                    <tr>
                        <td>${excursion.name}</td>
                        <td>${excursion.port}</td>
                        <td>${excursion.price}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/company/remove-excursion?excursion=${excursion.nameEn}" class="btn btn-warning font-weight-bold">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <%--<ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
        <c:forEach var="port" items="${sessionScope.cruise.ports}">
            <li class="nav-item">
                <a class="nav-link" id="pills-${port.nameEn}-tab" data-toggle="pill" href="#pills-${port.nameEn}" role="tab" aria-controls="pills-${port.name}" aria-selected="false">${port.name}</a>
            </li>
        </c:forEach>
    </ul>
    <div class="tab-content justify-content-center" id="pills-tabContent">
        <c:forEach var="port" items="${sessionScope.cruise.ports}">
                <div class="tab-pane fade" id="pills-${port.nameEn}" role="tabpanel" aria-labelledby="pills-${port.nameEn}-tab">
                    <div class="container">
                        <table class="table table-bordered">
                            <thead>
                            <tr class="bg-primary text-white">
                                <th>Excursion</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="excursion" items="${port.excursions}">
                                    <tr>
                                        <td>${excursion.name}</td>
                                        <td>${excursion.price}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/company/client/add-excursion?pill=${port.nameEn}" class="btn btn-warning font-weight-bold">Add</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
        </c:forEach>
    </div>--%>
</div>
<%--<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">--%>
    <%--$(function() {
        $('a[data-toggle="pill"]').on('click', function(e) {
            window.localStorage.setItem('activeTab', $(e.target).attr('href'));
        });
        var activeTab = window.localStorage.getItem('activeTab');
        if (activeTab) {
            $('#pills-tab a[href="' + activeTab + '"]').tab('show');
            window.localStorage.removeItem("activeTab");
        }
    });*/
    /*$(function() {
        // for bootstrap 3 use 'shown.bs.tab', for bootstrap 2 use 'shown' in the next line
        $('a[data-toggle="pill"]').on('shown.bs.tab', function (e) {
            // save the latest tab; use cookies if you like 'em better:
            localStorage.setItem('lastTab', $(this).attr('href'));
        });

        // go to the latest tab, if it exists:
        var lastTab = localStorage.getItem('lastTab');
        if (lastTab) {
            $('[href="' + lastTab + '"]').tab('show');
            window.localStorage.removeItem("lastTab");
        }
    });*/
    /*$('a[data-toggle="pill"]').click(function(e) {
        var hash = $(this).attr('href');
        location.hash = hash;
    });
    $(function() {
        var hash = window.location.hash;
        var $nav = $('ul.nav a[href="' + hash + '"]');
        hash && $nav.trigger('click');
    });--%>
</script>
<jsp:include page="/WEB-INF/components/footer.jsp"/>
