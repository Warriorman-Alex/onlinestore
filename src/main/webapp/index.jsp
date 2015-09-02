

<div id="indexLeftColumn">
    <div id="welcomeText">
        <p>
            <i><b>Welcome to the online store!</b></i><br/><br/>
            Shop in our store, and you are guaranteed 
            to be happy with the result. 
            Every client we offer individual approach, 
            prompt delivery and reasonable prices and 
            deliver a lot of positive emotions. 
            We love and respect our clients and their 
            work trying to satisfy all your desires. 
            Come, choose, make a order!
        </p>        
    </div>
</div>

<div id="indexRightColumn">
    <c:forEach var="category" items="${catalog}">
        <div class="categoryBox">
            
            <a href="<c:url value='catalog?${category.id}'/>">
                
                <span class="categoryLabelText">${category.name}</span>

                <img src="${initParam.catalogImagePath}${category.name}.jpg"
                     alt="${category.name}">
            </a>
        </div> 
    </c:forEach>

</div>


