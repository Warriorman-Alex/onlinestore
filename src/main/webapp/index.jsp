<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='/index' scope='session' />

<div id="indexLeftColumn">
    <div id="welcomeText">
        <p>
            <i><b>                                      
                    <fmt:message key='greeting'/>
                </b>
            </i><br/><br/>
        </p>
        <p>
            <fmt:message key='introText'/>
        </p>        
    </div>
</div>

<div id="indexRightColumn">
    <c:forEach var="category" items="${catalog}">
        <div class="categoryBox">

            <a href="<c:url value='catalog?${category.name}'/>">

                <span class="categoryLabelText"><fmt:message key='${category.name}'/></span>

                <img src="${initParam.catalogImagePath}${category.name}.jpg"
                     alt="<fmt:message key='${category.name}'/>" class="categoryImage">
            </a>
        </div> 
    </c:forEach>

</div>


