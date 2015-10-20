<c:set var='view' value='/contact_page' scope='session' />

<form action="EmailFidbekSendingServlet" method="post" >
    
    <p><b><fmt:message key='userContact'/></b><br>
        <input type="text" name="subject" size="40">
    </p>
    <p><fmt:message key='userQuestion'/><Br>
        <textarea name="content" cols="40" rows="10"></textarea></p>
    <p><input type="submit" value=<fmt:message key='sendButton'/>>
        <input type="reset" value=<fmt:message key='resetButton'/>></p>
</form>