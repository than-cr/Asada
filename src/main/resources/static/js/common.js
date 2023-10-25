function getRequest(url, success, error) {
    $.ajax({
        url: url,
        type: "GET",
        success: function (response) {success(response)},
        error: function (response) {error(response)},
    });
}

function postRequest(url, data, success, error) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        contentType: "application/json",
        processData: false,
        success: function (result) {
            success(result);
        },
        error: function (result) {
            error(result);
        }
    })
}

function generateUuid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c === 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

function successAlert(action) {
    Swal.fire({
        title: "Bien hecho",
        text: "Información guardada exitosamente.",
        icon: "success",
        button: "Cerrar",
    }).then(action);
}

function errorAlert() {
    Swal.fire({
        title: "Error",
        text: "Hubo un error al procesar la información.",
        icon: "warning",
        button: "Cerrar",
    });
}

var deletePrint = function (callback) {
    Swal.fire({
        title: 'Está seguro?',
        text: 'Esta acción no podra ser revertida en el futuro.',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        confirmButtonColor: 'Red'
    }).then((result) => {
        if (result.isConfirmed) {
            callback();
        }
    });
}