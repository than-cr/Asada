$(document).ready(function () {
    $("#addLot").click(function () {
        event.preventDefault();

        $("#lotModal").modal('show');
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
    })
});

function editLot(lotId) {
    let url = "/lot/" + lotId;

    getRequest(url, loadLotData);
}

function addPayment(lotId) {
    let url = "/lot/payment/" + lotId;
    postRequest(url,null, function () {location.reload()});
}

function loadLotData(data) {
    $("#lotId").val(data.id);
    $("#name").val(data.name);
    $("#ownerId").val(data.owner);
    $("#status").val(data.status);
    $("#lastMonthPaid").val(data.lastMonthPaid);

    $("#lotModal").modal("show");
}