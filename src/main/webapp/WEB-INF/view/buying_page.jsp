<script src="js/jspdf.js" type="text/javascript"></script>
<script src="js/jspdf.plugin.from_html.js" type="text/javascript"></script>
<script src="js/jspdf.plugin.split_text_to_size.js" type="text/javascript"></script>
<script src="js/jspdf.plugin.standard_fonts_metrics.js" type="text/javascript"></script>
<script src="js/jspdf.plugin.addimage.js" type="text/javascript"></script>
<script src="js/jspdf.plugin.cell.js" type="text/javascript"></script>
<script src="js/jspdf.debug.js" type="text/javascript"></script>
<script src="js/addhtml.js" type="text/javascript"></script>
<script src="js/html2canvas.js" type="text/javascript"></script>
<script src="js/fabric.min.js" type="text/javascript"></script>
<script src="js/canvas.js" type="text/javascript"></script>

<script type="text/javascript">

    function onPush() {
        var pdf = new jsPDF('p', 'mm', 'a4');
        pdf.addHTML(document.body, function () {
            pdf.save('order.pdf');
        });
    }

</script>

<div id="singleColumn">

    <p id="confirmationText">
        <strong><fmt:message key='confirmationTextProcessed'/></strong>
        <br><br>
        <fmt:message key='confirmationTextConfirmation'/>
        <strong>${orderRecord.confirmationNumber}</strong>
        <br>
        <fmt:message key='confirmationTextConcerning'/> <a href="<c:url value='contact'/>"><fmt:message key='contact'/></a>.
        <br><br>
        <fmt:message key='Thank'/>
    </p>

    <div class="summaryColumn" >

        <table id="orderSummaryTable" class="detailsTable">
            <tr class="header">
                <th colspan="3"><fmt:message key='orderSummary'/></th>
            </tr>

            <tr class="tableHeading">
                <td id="ignorePDF"><fmt:message key='product'/></td>
                <td id="ignorePDF"><fmt:message key='quantity'/></td>
                <td id="ignorePDF"><fmt:message key='price'/></td>
            </tr>

            <c:forEach var="orderedProduct" items="${orderedProducts}" varStatus="iter">

                <tr class="${((iter.index % 2) != 0) ? 'lightBlue' : 'white'}">
                    <td>${products[iter.index].name}</td>
                    <td class="quantityColumn">
                        ${orderedProduct.quantity}
                    </td>
                    <td class="confirmationPriceColumn">
                        &euro; ${products[iter.index].price * orderedProduct.quantity}
                    </td>
                </tr>

            </c:forEach>

            <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>

            <tr class="lightBlue">
                <td colspan="2" id="deliverySurchargeCellLeft"><strong><fmt:message key='deliveryBP'/></strong></td>
                <td id="deliverySurchargeCellRight">&euro; ${deliveryCost}</td>
            </tr>

            <tr class="lightBlue">
                <td colspan="2" id="totalCellLeft"><strong><fmt:message key='total'/></strong></td>
                <td id="totalCellRight">&euro; <fmt:formatNumber type="number" 
                                  maxFractionDigits="2" value="${orderRecord.amount}"/></td>
            </tr>

            <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>

            <tr class="lightBlue">
                <td colspan="3" id="dateProcessedRow"><strong><fmt:message key='date processed'/></strong>
                        ${orderRecord.dateCreated}
                </td>
            </tr>
        </table>

    </div>

    <strong><fmt:message key='getOrder'/></strong> 
                       
    <input type="button" name="SaveOnPDF" onclick="onPush();
            return true;" value="<fmt:message key='savePDF'/>">

    <div class="summaryColumn" >

        <table id="deliveryAddressTable" class="detailsTable">
            <tr class="header">
                <th colspan="3"><fmt:message key='delivery address'/></th>
            </tr>

            <tr>
                <td colspan="3" class="lightBlue">
                    ${customer.name}
                    <br>
                    ${customer.address}
                    <br>
                    <fmt:message key='delivery'/> ${customer.cityRegion}
                    <br>
                    <hr>
                    <strong><fmt:message key='fieldEmail'/></strong> ${customer.email}
                    <br>
                    <strong><fmt:message key='fieldPhone'/></strong> ${customer.phone}
                </td>
            </tr>
        </table>


    </div>
</div>


