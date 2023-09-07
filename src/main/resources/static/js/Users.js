$(document).ready(function () {

    $("#userModalForm").validate({
        rules: {
            userId: {
                required: true,
                digits: true,
                minlength: 8
            },
            firstName: {
                required: true,
                minlength: 2
            },
            lastName: {
                required: true,
                minlength: 1
            },
            motherLastName: {
                required: true,
                minlength: 1
            },
            phone: {
                required: true,
                digits: true
            },
            email: {
                required: false,
                email: true
            },
            "status": {
                required: true
            },
            role: {
                required: true
            }
        }
    })

    $("#addUser").click(function () {
        event.preventDefault();
        $("#userId").val("").attr("disabled", false);
        $("#firstName").val("");
        $("#lastName").val("");
        $("#motherLastName").val("");
        $("#phone").val("");
        $("#email").val("");
        $("#status").val("");
        $("#role").val("");

        $("#userModal").modal('show');
    });

    $("#saveUser").click(function () {
        event.preventDefault();

        $("#userModalForm").validate();
        if (!$("#userModalForm").valid()) {
            return;
        }

       let user =  {
           username: $("#userId").val(),
           firstName: $("#firstName").val(),
           lastName: $("#lastName").val(),
           motherLastName: $("#motherLastName").val(),
           phoneNumber: $("#phone").val(),
           email: $("#email").val(),
           status: $("#status").val(),
           role: $("#role").val(),
       };

       let objectToSave = JSON.stringify(user);
       let url = "/users";
       postRequest(url, objectToSave, function (response) {
           successAlert(function () {
               location.reload();
           });
       }, errorAlert);
    });
});

function goToLots (userId) {
    window.location = "/lots/" + userId;
}

function editUser (userId) {
    let url = "/users/" + userId;

    getRequest(url, loadUserData);
}

function loadUserData(data) {
   $("#userId").val(data.username);
   $("#firstName").val(data.firstName);
   $("#lastName").val(data.lastName);
   $("#motherLastName").val(data.motherLastName);
   $("#phone").val(data.phoneNumber);
   $("#email").val(data.email);
   $("#status").val(data.status);
   $("#role").val(data.role);

   $("#userId").attr("disabled", true);

    $("#userModal").modal('show');
}