$(document).ready(function () {
    console.log("ready");

    $("#register-form").hide();
    $("#users-table").hide();
    $("#success-alert").hide();
    $("#error-alert").hide();

    $(document).on("click", "#display-form", function () {
        $("#register-form").show(350);
    });

    $(document).on("click", "#delete-all", function () {
        if(confirm("DELETE ALL USERS?"))
            $.ajax({
                type: 'DELETE',
                url: "/api/v1/users/deleteall",
                success: function (result) {
                    console.log(result);
                    $("#error-alert").hide();
                    $("#success-alert").show();
                },
                error: function (e) {
                    console.log(e);
                    $("#success-alert").hide();
                    $("#error-alert").show();
                }
            });
    });

    $(document).on("click", "#display-users", function () {
        let jsonString = {};

        $.ajax({
            type: 'GET',
            url: "/api/v1/users/all",
            contentType: 'application/json',
            data: jsonString,
            dataType: 'json',
            success: function (data) {
                console.log(data);

                let table = new Tabulator("#users-table", {
                    data:data,
                    movableColumns:false,
                    layout:"fitDataFill",
                    columns:[
                        {title:"Name", field:"name"},
                        {title:"Surname", field:"surname"},
                    ],
                });

                $("#users-table").show(350);

                table.on("rowClick", function(e, row){
                    window.open("html/user-details.html?user=" + row.getData().id, '_blank');
                });
            },
            error: function (e) {
                console.log(e);
            }
        });
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
        if (!(workAddress === "" && homeAddress === "")) {
            obj2.workAddress = workAddress;
            obj2.homeAddress = homeAddress;
            obj.address = obj2;
        }

        let jsonString = JSON.stringify(obj);
        console.log("jsonstring", jsonString);

        $.ajax({
            type: 'POST',
            url: "/api/v1/users/add",
            contentType: 'application/json',
            data: jsonString,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                $("#error-alert").hide();
                $("#success-alert").show();
            },
            error: function (e) {
                console.log(e);
                $("#success-alert").hide();
                $("#error-alert").show();
            }
        });

    });

})
