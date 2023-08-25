$(document).ready(function () {
    $("#addLot").click(function () {
        event.preventDefault();

        $("#lotModal").modal('show');
    });

    $("#btnGenerateReceipt").click(function () {
        event.preventDefault();
       $("#receipt").val(generateUuid());
    });

    $("#saveLot").click(function () {
        event.preventDefault();

        let lot = {
            id: $("#lotId").val(),
            name: $("#name").val(),
            owner: $("#ownerId").val(),
            status: $("#status").val(),
            lastMonthPaid: $("#lastMonthPaid").val()
        };

        let objectToSave = JSON.stringify(lot);
        let url = "/lot";

        postRequest(url, objectToSave, function (response) {
            location.reload();
        });
    });

    $("#savePayment").click(function () {
        event.preventDefault();

        let payment = {
            id: $("#paymentLotId").val(),
            receiptId: $("#receipt").val()
        };

        let objectToSave = JSON.stringify(payment);
        let url = "/lot/payment";

        postRequest(url, objectToSave, function (response) {
            location.reload();
        })
    })
});

function editLot(lotId) {
    let url = "/lot/" + lotId;

    getRequest(url, loadLotData);
}

function addPayment(lotId) {
    let url = "/lot/payment/" + lotId;
    getRequest(url, loadPaymentData);
}

function loadPaymentData(data) {
    $("#paymentLotId").val(data.id);
    const formatter = new Intl.DateTimeFormat("es", {month: "long", year: "numeric"});
    const date = new Date(data.lastMonthPaid);
    $("#monthToPay").val(formatter.format(new Date(date.getFullYear(), date.getMonth(), date.getDate())));
    $("#totalToPay").val(data.payment);

    $("#paymentModal").modal("show");
}

function loadLotData(data) {
    $("#lotId").val(data.id);
    $("#name").val(data.name);
    $("#ownerId").val(data.owner);
    $("#status").val(data.status);
    $("#lastMonthPaid").val(data.lastMonthPaid);

    $("#lotModal").modal("show");
}