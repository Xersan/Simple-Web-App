$(document).ready(function () {
    //console.log("ready");

    $("#update-form").hide();
    $("#success-alert").hide();
    $("#error-alert").hide();

    let url_params = new URLSearchParams(window.location.search);
    let userId = url_params.get('user');
    //console.log(userId);

    $(document).on("click", "#display-form", function () {
        $("#update-form").show(350);
    });

    let jsonString = {};

    $.ajax({
        type: 'GET',
        url: "/api/v1/users/user/" + userId,
        contentType: 'application/json',
        data: jsonString,
        dataType: 'json',
        success: function (data) {
            //console.log(e);

            $("#input-name").val(data.name);
            $("#input-surname").val(data.surname);
            $("#input-gender").val(data.gender);
            $("#input-date").val(data.date);
            if(data.address !== null) {
                $("#input-work-address").val(data.address.workAddress);
                $("#input-home-address").val(data.address.homeAddress)
            }

            new Tabulator("#user-details", {
                data:Array.of(data),
                movableColumns:false,
                layout:"fitDataFill",
                columns:[
                    {title:"Name", field:"name"},
                    {title:"Surname", field:"surname"},
                    {title:"Gender", field:"gender"},
                    {title:"Date", field:"date"},
                    {title:"Work Address", field:"address.workAddress"},
                    {title:"Home Address", field:"address.homeAddress"},
                ],
            });
        },
        error: function (e) {
            //console.log(e);
            let errorText = e.responseText;
            errorText = errorText.substring(errorText.lastIndexOf('[') + 2, errorText.length);
            $("#error-alert").text(errorText.substring(0, errorText.indexOf(']') - 1)).show(350);
        }
    });

    $("#submit-form").click(function (e) {
        e.preventDefault();
        let obj = {};
        let obj2 = {};
        obj.name = $("#input-name").val();
        obj.surname = $("#input-surname").val();
        obj.gender = $("#input-gender").val();
        obj.date = $("#input-date").val();
        let workAddress = $("#input-work-address").val();
        let homeAddress = $("#input-home-address").val();
        obj2.workAddress = workAddress;
        obj2.homeAddress = homeAddress;
        obj.address = obj2;

        let jsonString = JSON.stringify(obj);
        //console.log("jsonstring", jsonString);

        $.ajax({
            type: 'PUT',
            url: "/api/v1/users/user/" + userId,
            contentType: 'application/json',
            data: jsonString,
            dataType: 'json',
            success: function (e) {
                //console.log(e);
                $("#error-alert").hide();
                $("#success-alert").text("User updated.").show(350);
            },
            error: function (e) {
                //console.log(e)
                let errorText = e.responseText;
                errorText = errorText.substring(errorText.lastIndexOf('[') + 1, errorText.length);
                errorText = errorText.substring(0, errorText.indexOf(']'));
                if (errorText[0] === "\"")
                    errorText = errorText.substring(1, errorText.length - 1);
                $("#error-alert").text(errorText).show(350);
                $("#success-alert").hide();
            }
        });

    });

    $("#delete-user").click(function () {
        $.ajax({
            type: 'DELETE',
            url: "/api/v1/users/user/" + userId,
            contentType: 'application/json',
            data: jsonString,
            dataType: 'json',
            success: function (e) {
                //console.log(e);
                $("#error-alert").hide();
                $("#success-alert").text("User deleted.").show(350);
            },
            error: function (e) {
                //console.log(e);
                let errorText = e.responseText;
                errorText = errorText.substring(errorText.lastIndexOf('[') + 2, errorText.length);
                $("#error-alert").text(errorText.substring(0, errorText.indexOf(']') - 1)).show(350);
                $("#success-alert").hide();
            }
        });

        $("#delete-user-modal").modal('hide');
    });

});
