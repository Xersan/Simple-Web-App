$(document).ready(function () {
    console.log("ready");

    $("#register-form").hide();
    $("#users-table").hide();

    $(document).on("click", "#display-form", function () {
        $("#register-form").show(350);
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

                $("#users-table").show(500);

                table.on("rowClick", function(e, row){
                    alert("Row " + row.getData().id + " Clicked!!!!");
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
        if (!(workAddress === "" && homeAddress === ""))
        {
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
            },
            error: function (e) {
                console.log(e);
            }
        });

    });

})
