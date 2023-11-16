$(document).ready(function (){
    $("#saveReceiptMod").click(function () {
        let data = {
            id : $("#receiptId").val(),
            receiptId: $("#receipt").val(),
            status: $("#status").val(),
            monthToPay: $("#monthToPay").val(),
            cost: $("#totalToPay").val()
        };

        let objectToSave = JSON.stringify(data);
        let url = "/receipts/edit";

        postRequest(url, objectToSave, function (response) {
            successAlert(function () {
                location.reload();
            });
        }, errorAlert)
    });
})

function deleteReceipt (receiptId) {
    deletePrint(function() {
        let url = '/receipts/delete'

        let receipt = {
            id: receiptId
        }

        let objectToSave = JSON.stringify(receipt);

        postRequest(url, objectToSave, function (response) {
            successAlert(function () {
                location.reload();
            });
        }, errorAlert)
    })
}

function editReceipt (receiptId) {
    let url = "/receipts/edit/" + receiptId;

    getRequest(url, function (response) {
        $("#receiptId").val(response.id);
        $("#receipt").val(response.receiptId);
        $("#monthToPay").val(response.monthToPay);
        $("#totalToPay").val(response.cost)
        $("#status").val(response.status);

        $("#editModal").modal('show');
    }, errorAlert);
}