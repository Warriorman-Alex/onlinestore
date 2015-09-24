<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='/check_page' scope='session' />

<script src="js/jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript">

    $(document).ready(function () {
        $("#checkoutForm").validate({
            rules: {
                name: "required",
                email: {
                    required: true,
                    email: true
                },
                phone: {
                    required: true,
                    number: true,
                    minlength: 9
                },
                address: {
                    required: true
                },
                creditcard: {
                    required: true,
                    creditcard: true
                }
            }
        });
    });
</script>

<div id="centerColumn">

    <h2><fmt:message key='checkout'/></h2>

    <p><fmt:message key='information'/></p>

    <c:if test="${!empty orderFailureFlag}">
        <p class="error" style="color:#CC0000"><fmt:message key='error'/></p>
    </c:if>

    <form action="<c:url value='purchase'/>" method="post">
        <table id="checkoutTable">
            <c:if test="${!empty validationErrorFlag}">

                <tr>
                    <td colspan="2" style="text-align:left">
                        <span class="error smallText" style="color:#CC0000"><fmt:message key='error smallText'/>

                            <c:if test="${!empty nameError}">
                                <br><span class="indent"><strong><fmt:message key='nameError'/></strong> (e.g., Alex Trofymenko)</span>
                                    </c:if>
                                    <c:if test="${!empty emailError}">
                                <br><span class="indent"><strong><fmt:message key='emailError'/></strong> (e.g., a.trofym@gmail.com)</span>
                                    </c:if>
                                    <c:if test="${!empty phoneError}">
                                <br><span class="indent"><strong><fmt:message key='phoneError'/></strong> (e.g., 222333444)</span>
                                    </c:if>
                                    <c:if test="${!empty addressError}">
                                <br><span class="indent"><strong><fmt:message key='addressError'/></strong> (e.g., Borisoglebskaya 1)</span>
                                    </c:if>
                                    <c:if test="${!empty cityRegionError}">
                                <br><span class="indent"><strong><fmt:message key='cityRegionError'/></strong> (e.g., 1)</span>
                                    </c:if>
                                    <c:if test="${!empty ccNumberError}">
                                <br><span class="indent"><strong><fmt:message key='ccNumberError'/></strong> (e.g., 1111-2222-3333-4444)</span>
                                    </c:if>

                        </span>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td><label for="name"><fmt:message key='fieldName'/></label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="name"
                           name="name"
                           value="${param.name}">
                </td>
            </tr>
            <tr>
                <td><label for="email"><fmt:message key='fieldEmail'/></label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="email"
                           name="email"
                           value="${param.email}">
                </td>
            </tr>
            <tr>
                <td><label for="phone"><fmt:message key='fieldPhone'/></label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="16"
                           id="phone"
                           name="phone"
                           value="${param.phone}">
                </td>
            </tr>
            <tr>
                <td><label for="address"><fmt:message key='fieldAddress'/></label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="address"
                           name="address"
                           value="${param.address}">

                    <br>
                    <fmt:message key='delivery'/>
                    <select name="cityRegion">
                        <option value="Regular shipping"><fmt:message key='Regular shipping'/></option>
                        <option value="Premium service"><fmt:message key='Premium service'/></option>                        
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="creditcard"><fmt:message key='fieldCC'/></label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="19"
                           id="creditcard"
                           name="creditcard"
                           value="${param.creditcard}">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="<fmt:message key='submit purchase'/>">
                </td>
            </tr>
        </table>
    </form>

    <div id="infoBox">

        <ul>
            <li><fmt:message key='deliveryText'/></li>
            <li>&euro; ${initParam.deliverySurcharge}
                <fmt:message key='deliveryCost'/></li>
        </ul>

        <table id="priceBox">
            <tr>
                <td><fmt:message key='subtotal'/>:</td>
                <td class="checkoutPriceColumn">
                    &euro; <fmt:formatNumber type="number" maxFractionDigits="2" value="${cart.subtotal}"/></td>
            </tr>
            <tr>
                <td><fmt:message key='delivery surcharge'/></td>
                <td class="checkoutPriceColumn">
                    &euro; ${initParam.deliverySurcharge}</td>
            </tr>
            <tr>
                <td class="total"><fmt:message key='total'/></td>
                <td class="total checkoutPriceColumn">
                    &euro; <fmt:formatNumber type="number" maxFractionDigits="2" value="${cart.total}"/></td>
            </tr>
        </table>
    </div>
    <div id="infoBox">

        <ul>
            <li><fmt:message key='deliveryTextPremium'/></li>
            <li>&euro; ${initParam.deliverySurchargePremium}
                <fmt:message key='deliveryCostPremium'/></li>
        </ul>

        <table id="priceBox">
            <tr>
                <td><fmt:message key='subtotal'/>:</td>
                <td class="checkoutPriceColumn">
                    &euro; <fmt:formatNumber type="number" maxFractionDigits="2" value="${cart.subtotal}"/></td>
            </tr>
            <tr>
                <td><fmt:message key='deliverySurchargePremium'/></td>
                <td class="checkoutPriceColumn">
                    &euro; ${initParam.deliverySurchargePremium}</td>
            </tr>
            <tr>
                <td class="total"><fmt:message key='total'/></td>
                <td class="total checkoutPriceColumn">
                    &euro; <fmt:formatNumber type="number" maxFractionDigits="2" value="${cart.totalPremium}"/></td>
            </tr>
        </table>
    </div>
</div>


