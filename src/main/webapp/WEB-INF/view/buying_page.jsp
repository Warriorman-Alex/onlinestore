
<div id="singleColumn">

    <p id="confirmationText">
        <strong><fmt:message key='confirmationTextProcessed'/></strong>
        <br><br>
        <fmt:message key='confirmationTextConfirmation'/>
        <strong>${orderRecord.confirmationNumber}</strong>
        <br>
        <fmt:message key='confirmationTextConcerning'/> <a href="#"><fmt:message key='contact'/></a>.
        <br><br>
        <fmt:message key='Thank'/>
    </p>

    <div class="summaryColumn" >

        <table id="orderSummaryTable" class="detailsTable">
            <tr class="header">
                <th colspan="3"><fmt:message key='orderSummary'/></th>
            </tr>

            <tr class="tableHeading">
                <td><fmt:message key='product'/></td>
                <td><fmt:message key='quantity'/></td>
                <td><fmt:message key='price'/></td>
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
                <td colspan="2" id="deliverySurchargeCellLeft"><strong><fmt:message key='delivery surcharge'/></strong></td>
                <td id="deliverySurchargeCellRight">&euro; ${initParam.deliverySurcharge}</td>
            </tr>

            <tr class="lightBlue">
                <td colspan="2" id="totalCellLeft"><strong><fmt:message key='total'/></strong></td>
                <td id="totalCellRight">&euro; ${orderRecord.amount}</td>
            </tr>

            <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>

            <tr class="lightBlue">
                <td colspan="3" id="dateProcessedRow"><strong><fmt:message key='date processed'/></strong>
                    ${orderRecord.dateCreated}
                </td>
            </tr>
        </table>

    </div>

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


