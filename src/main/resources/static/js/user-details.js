$(document).ready(function () {
    console.log("ready");

    let url_params = new URLSearchParams(window.location.search);
    let userId = url_params.get('user');
    console.log(userId);

    let jsonString = {};

    $.ajax({
        type: 'GET',
        url: "/api/v1/users/user/" + userId,
        contentType: 'application/json',
        data: jsonString,
        dataType: 'json',
        success: function (data) {
            console.log(data);

            let table = new Tabulator("#user-details", {
                data:Array.of(data),
                movableColumns:false,
                layout:"fitDataFill",
                columns:[
                    {title:"Name", field:"name"},
                    {title:"Surname", field:"surname"},
                    {title:"Gender", field:"gender"},
                    {title:"Date", field:"date"},
                    {title:"Work Address", field:"address.homeAddress"},
                    {title:"Home Address", field:"address.workAddress"},
                ],
            });
        },
        error: function (e) {
            console.log(e);
        }
    });
});
