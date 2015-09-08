<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='/cart_page' scope='session' />

<div id="centerColumn">
    
    <c:choose>
        <c:when test="${cart.numberOfItems > 1}">
            <p><fmt:message key='Your shopping cart contains'/> ${cart.numberOfItems} <fmt:message key='items'/>.</p>
        </c:when>
        <c:when test="${cart.numberOfItems == 1}">
            <p><fmt:message key='Your shopping cart contains'/> ${cart.numberOfItems} <fmt:message key='item'/>.</p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key='Your shopping cart is empty'/>.</p>
        </c:otherwise>
    </c:choose>

    <div id="actionBar">
        <%-- clear cart widget --%>
        <c:if test="${!empty cart && cart.numberOfItems != 0}">
            <c:url var="url" value="viewCart">
                <c:param name="clear" value="true"/>
            </c:url>
            
            <a href="${url}" class="bubble hMargin"><fmt:message key='clear cart'/></a>            
        </c:if>

        <%-- continue shopping widget --%>
        <c:set var="value">
            <c:choose>
                <%-- if 'selectedCategory' session object exists, send user to previously viewed category --%>
                <c:when test="${!empty selectedCategory}">
                    catalog
                </c:when>
                <%-- otherwise send user to welcome page --%>
                <c:otherwise>
                    index.jsp
                </c:otherwise>
            </c:choose>
        </c:set>
        <c:url var="url" value="${value}"/>                
        <a href="${url}" class="bubble hMargin"><fmt:message key='continue shopping'/></a>

        <%-- checkout widget --%>
        <c:if test="${!empty cart && cart.numberOfItems != 0}">
            <a href="<c:url value='checkout'/>" class="bubble hMargin"><fmt:message key='proceed to checkout'/> &#x279f;</a>
        </c:if>
    </div>

    <c:if test="${!empty cart && cart.numberOfItems != 0}">
        <c:set var="subtotal" value="${cart.subtotal}" />
        <h4 id="subtotal"><fmt:message key='subtotal'/>: &euro; <fmt:formatNumber type="number" 
                          maxFractionDigits="2" value="${subtotal}" /></h4>

        <table id="cartTable">

            <tr class="header">
                <th><fmt:message key='product'/></th>
                <th><fmt:message key='name'/></th>
                <th><fmt:message key='price'/></th>
                <th><fmt:message key='quantity'/></th>
            </tr>

            <c:forEach var="cartItem" items="${cart.items}" varStatus="iter">

                <c:set var="product" value="${cartItem.product}"/>

                <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                    <td>
                        <img src="${initParam.productImagePath}${product.name}.jpg"
                             alt="${product.name}">
                    </td>

                    <td>${product.name}</td>

                    <td>
                        &euro; ${cartItem.total}
                        <br>
                        <span class="smallText">( &euro; ${product.price} / <fmt:message key='unit'/> )</span>
                    </td>

                    <td>
                        <form action="<c:url value='updateCart'/>" method="post">
                            <input type="hidden"
                                   name="productId"
                                   value="${product.id}">
                            <input type="text"
                                   maxlength="2"
                                   size="2"
                                   value="${cartItem.quantity}"
                                   name="quantity"
                                   style="margin:5px">
                            <input type="submit"
                                   name="submit"
                                   value="<fmt:message key='update'/>">
                        </form>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </c:if>

</div>


