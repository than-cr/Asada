$(document).ready(function () {

    $("#addUser").click(function () {
        event.preventDefault();
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
       let url = "/user";
       postRequest(url, objectToSave, function (response) {
           location.reload();
       })
    });
});

function goToLots (userId) {
    window.location = "/lots/" + userId;
}

function editUser (userId) {
    let url = "/user/" + userId;

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

    $("#userModal").modal('show');
}