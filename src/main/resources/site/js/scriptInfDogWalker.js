let objectDogWalker;

let newDogWalker = {
    lastName: "",
    name: "",
    middleName: "",
    birthDate: "",
    phoneNumber: "",
    email: ""
}

function clearDogWalker() {

    let parent = document.getElementById("findTableDW");
    parent.innerText = "";
}

function clearAllDogWalkers() {

    let parent = document.getElementById("dogWalkerTable");
    parent.innerText = "";
}

function formFindDogWalker() {
    let str = document.getElementById("uniqueIdDW").value;

    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/dogWalker?uniqueId=" + str,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("findTableDW");
            parent.innerHTML = "";

            let table = document.createElement("table");

            createTitleTableDW(table);

            addDataFindDW(data, table);

            parent.append(table);
        }
    });
}

function checkValid() {
    let valid = document.getElementsByClassName("inputDogWalker");
    let reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    let check = true;

    for (let i = 0; i < valid.length; i++) {
        if (valid[i].value === "") {
            switch (i) {
                case 0:
                    document.getElementById("inputLastNameDW").innerHTML = "Fill Last name!";
                    break;
                case 1:
                    document.getElementById("inputNameDW").innerHTML = "Fill Name!";
                    break;
                case 2:
                    document.getElementById("inputMiddleNameDW").innerHTML = "Fill Middle name!";
                    break;
                case 3:
                    document.getElementById("inputBirthdayDW").innerHTML = "Fill Birthday!";
                    break;
                case 4:
                    document.getElementById("inputPhoneDW").innerHTML = "Fill Phone!";
                    break;
                case 5:
                    document.getElementById("inputEmailDW").innerHTML = "Fill Email!";
                    break;
            }
            check = false;

        } else if (reg.test(valid[5].value) === false) {
            document.getElementById("inputEmailDW").innerHTML = "Incorrect Email!";
            check = false;
        }
    }
    return check;
}

function inputValueLastNameDW() {
    document.getElementById("inputLastNameDW").innerHTML = "";
}

function inputValueNameDW() {
    document.getElementById("inputNameDW").innerHTML = "";
}

function inputValueMiddleNameDW() {
    document.getElementById("inputMiddleNameDW").innerHTML = "";
}

function inputValueBirthdayDW() {
    document.getElementById("inputBirthdayDW").innerHTML = "";
}

function inputValuePhoneDW() {
    document.getElementById("inputPhoneDW").innerHTML = "";
}

function inputValueEmailDW() {
    document.getElementById("inputEmailDW").innerHTML = "";
}

$(function () {
    $('#date').daterangepicker({
        singleDatePicker: true,
        locale: {
            format: 'DD.MM.YYYY'
        }
    });
});

function crtDW() {
    if (checkValid() === true) {
        let out = document.getElementById("outDW");
        out.innerHTML = "";

        newDogWalker.lastName = document.getElementById("lastNameDW").value;
        newDogWalker.name = document.getElementById("nameDW").value;
        newDogWalker.middleName = document.getElementById("middleNameDW").value;
        newDogWalker.birthDate = document.getElementById("birthDateDW").value;
        newDogWalker.phoneNumber = document.getElementById("phoneNumberDW").value;
        newDogWalker.email = document.getElementById("emailDW").value;

        objectDogWalker = JSON.stringify(newDogWalker);

        AJS.$.ajax({
            type: 'post',
            url: 'http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/createDogWalker',
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: objectDogWalker,
            success: function () {

                document.getElementById("lastNameDW").value = "";
                document.getElementById("nameDW").value = "";
                document.getElementById("middleNameDW").value = "";
                document.getElementById("birthDateDW").value = "";
                document.getElementById("phoneNumberDW").value = "";
                document.getElementById("emailDW").value = "";

                document.getElementById("newDgWlkr").style.display = "block";

                setTimeout(function () {
                    document.getElementById("newDgWlkr").style.display = "none";
                }, 3000);

                addFromBdDW();
            }
        });
    }
}

function createTitleTableDW(table) {

    let row = document.createElement("tr");
    table.append(row);

    let col1 = document.createElement("td");
    col1.innerHTML = "Unique ID";
    row.append(col1);

    let col2 = document.createElement("td");
    col2.innerHTML = "Last name";
    row.append(col2);

    let col3 = document.createElement("td");
    col3.innerHTML = "Name";
    row.append(col3);

    let col4 = document.createElement("td");
    col4.innerHTML = "Middle name";
    row.append(col4);

    let col5 = document.createElement("td");
    col5.innerHTML = "Birth date";
    row.append(col5);

    let col6 = document.createElement("td");
    col6.innerHTML = "Phone number";
    row.append(col6);

    let col7 = document.createElement("td");
    col7.innerHTML = "Email";
    row.append(col7);

    let col8 = document.createElement("td");
    col8.innerHTML = "Status";
    row.append(col8);
}

function addDataFindDW(array, table) {
    let addRow = document.createElement('tr');
    table.append(addRow);

    for (let j = 0; j < 8; j++) {
        let addCol = document.createElement('td');

        let key = array.uniqueId;

        switch (j) {
            case 1:
                key = array.lastName;
                break;
            case 2:
                key = array.name;
                break;
            case 3:
                key = array.middleName;
                break;
            case 4:
                key = toDate(array.birthDate);
                break;
            case 5:
                key = array.phoneNumber;
                break;
            case 6:
                key = array.email;
                break;
            case 7:
                key = array.status;
                break;
        }
        addCol.innerHTML = key;
        addRow.append(addCol);
    }
}

function toDate(value) {
    let date = new Date(value);
    return date.toLocaleDateString();
}

function addDataTableDW(array, table) {
    for (let i = 0; i < array.length; i++) {

        let addRow = document.createElement('tr');
        table.append(addRow);

        for (let j = 0; j < 8; j++) {
            let addCol = document.createElement('td');

            let key = array[i].uniqueId;

            switch (j) {
                case 1:
                    key = array[i].lastName;
                    break;
                case 2:
                    key = array[i].name;
                    break;
                case 3:
                    key = array[i].middleName;
                    break;
                case 4:
                    key = toDate(array[i].birthDate);
                    break;
                case 5:
                    key = array[i].phoneNumber;
                    break;
                case 6:
                    key = array[i].email;
                    break;
                case 7:
                    key = array[i].status;
                    break;
            }
            addCol.innerHTML = key;
            addRow.append(addCol);
        }
    }
}

function addFromBdDW() {
    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allDogWalkers",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("dogWalkerTable");
            parent.innerHTML = "";

            let table = document.createElement("table");

            createTitleTableDW(table);

            addDataTableDW(data, table);

            parent.append(table);
        }
    });
}
