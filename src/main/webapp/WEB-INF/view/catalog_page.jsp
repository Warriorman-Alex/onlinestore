<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='/catalog_page' scope='session' />

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="categoryLeftColumn">
    <c:forEach var="category" items="${catalog}">

        <c:choose>
            <c:when test="${category.name == selectedCategory.name}">
                <div class="categoryButton" id="selectedCategory">
                    <span class="categoryText">
                        <fmt:message key='${category.name}'/>
                    </span>
                </div>
            </c:when>
            <c:otherwise>
                <a href="<c:url value='catalog?${category.name}'/>" class="categoryButton">
                    <div class="categoryText">
                        <fmt:message key='${category.name}'/>
                    </div>
                </a>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</div>

<div id="categoryRightColumn">
    <p id="categoryTitle">
        <span style="background-color: #f5eabe; padding: 7px;"><fmt:message key='${selectedCategory.name}'/></span>
    </p>

    <table id="productTable">
        <c:forEach var="product" items="${categoryProducts}" varStatus="iter">

            <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                <td>
                    <img src="${initParam.productImagePath}${product.name}.jpg"
                         alt="${product.name}">
                </td>
                <td>
                    ${product.name}
                    <br>
                    <span class="smallText"><fmt:message key='${product.description}'/></span>
                </td>
                <td>
                    &euro; ${product.price} 
                </td>
                <td>
                    <form action="<c:url value='addToCart'/>" method="post">
                        <input type="hidden"
                               name="productId"
                               value="${product.id}">
                        <input type="submit"
                               name="submit"
                               value="<fmt:message key='add to cart'/>">
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>


