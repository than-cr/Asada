$(document).ready(function () {

    $("#addUser").click(function () {
        event.preventDefault();
        $("#userId").val("");
        $("#firstName").val("");
        $("#lastName").val("");
        $("#motherLastName").val("");
        $("#phone").val("");
        $("#email").val("");
        $("#status").val("");
        $("#role").val("");

        $("#userId").attr("disabled", false);

        $("#userModal").modal('show');
    });

    $("#saveUser").click(function () {
        event.preventDefault();
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
           location.reload();
       })
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