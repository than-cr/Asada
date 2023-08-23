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