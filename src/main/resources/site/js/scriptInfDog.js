let objectDog;

let newDog = {
    dogName: "",
    gender: "",
    dogBirthDate: "",
    breed: "",
    color: "",
    dogCharacter: "",
    ownerId: "no owner"
}

loadOwners();

function clearDog() {
    let parent = document.getElementById("findTableD");
    parent.innerText = "";
}

function clearAllDog() {
    let parent = document.getElementById("dogTable");
    parent.innerText = "";
}

function findClient(id) {
    let str = id;
    let owner = "";

    let xhttp = new XMLHttpRequest();

    xhttp.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/client?uniqueId=" + str, false);
    xhttp.send();

    if (xhttp.status === 200) {
        myFunction(xhttp.responseText);
    } else {
        alert(xhttp.status + ': ' + xhttp.statusText);
    }

    function myFunction(data) {

        let array = JSON.parse(data);
        owner = array.name + " " + array.lastName;
    }
    return owner;

    // AJS.$.ajax({
    //     async: 'false',
    //     type: 'get',
    //     url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/client?uniqueId=" + str,
    //     dataType: "json",
    //     contentType: "application/json; charset=utf-8",
    //     data: 'json',
    //
    //     success: function (data) {
    //         owner = data.lastName + " " + data.name;
    //         return owner;
    //     }
    // });
}

function formFindDog() {
    let str = document.getElementById("uniqueIdD").value;

    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/dog?uniqueId=" + str,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("findTableD");
            parent.innerHTML = "";

            let table = document.createElement("table");

            createTitleTableD(table);

            addDataFindD(data, table);

            parent.append(table);
        }
    });
}

function checkValid() {
    let valid = document.getElementsByClassName("inputDog");

    let check = true;

    for (let i = 0; i < valid.length; i++) {
        if (valid[i].value === "") {
            switch (i) {
                case 0:
                    document.getElementById("inputDogName").innerHTML = "Fill Dog name!";
                    break;
                case 1:
                    document.getElementById("inputGender").innerHTML = "Fill Gender!";
                    break;
                case 2:
                    document.getElementById("inputDogBirthday").innerHTML = "Fill Birthday!";
                    break;
                case 3:
                    document.getElementById("inputBreed").innerHTML = "Fill Breed!";
                    break;
                case 4:
                    document.getElementById("inputColor").innerHTML = "Fill Color!";
                    break;
                case 5:
                    document.getElementById("inputCharacter").innerHTML = "Fill Character!";
                    break;
            }
            check = false;
        }
    }
    return check;
}

function inputValueDogName() {
    document.getElementById("inputDogName").innerHTML = "";
}

function inputValueGender() {
    document.getElementById("inputGender").innerHTML = "";
}

function inputValueDogBirthday() {
    document.getElementById("inputDogBirthday").innerHTML = "";
}

function inputValueBreed() {
    document.getElementById("inputBreed").innerHTML = "";
}

function inputValueColor() {
    document.getElementById("inputColor").innerHTML = "";
}

function inputValueCharacter() {
    document.getElementById("inputCharacter").innerHTML = "";
}

function crtD() {
    if (checkValid() === true) {
        let out = document.getElementById("outD");
        out.innerHTML = "";

        newDog.dogName = document.getElementById("dogName").value;
        newDog.gender = document.getElementById("gender").value;
        newDog.dogBirthDate = document.getElementById("dogBirthDate").value;
        newDog.breed = document.getElementById("breed").value;
        newDog.color = document.getElementById("color").value;
        newDog.dogCharacter = document.getElementById("dogCharacter").value;
        newDog.ownerId = document.getElementById("selectOwner").value;

        objectDog = JSON.stringify(newDog);

        AJS.$.ajax({
            type: 'post',
            url: 'http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/createDog',
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: objectDog,
            success: function () {

                document.getElementById("dogName").value = "";
                document.getElementById("gender").value = "";
                document.getElementById("dogBirthDate").value = "";
                document.getElementById("breed").value = "";
                document.getElementById("color").value = "";
                document.getElementById("dogCharacter").value = "";

                document.getElementById("newDg").style.display = "block";

                setTimeout(function () {
                    document.getElementById("newDg").style.display = "none";
                }, 3000);

                addFromBdD();
            }
        });
    }
}

function createTitleTableD(table) {

    let row = document.createElement("tr");
    table.append(row);

    let col1 = document.createElement("td");
    col1.innerHTML = "Unique ID";
    row.append(col1);

    let col2 = document.createElement("td");
    col2.innerHTML = "Dog name";
    row.append(col2);

    let col3 = document.createElement("td");
    col3.innerHTML = "Dog Birthday";
    row.append(col3);

    let col4 = document.createElement("td");
    col4.innerHTML = "Dog Birthday";
    row.append(col4);

    let col5 = document.createElement("td");
    col5.innerHTML = "Breed";
    row.append(col5);

    let col6 = document.createElement("td");
    col6.innerHTML = "Color";
    row.append(col6);

    let col7 = document.createElement("td");
    col7.innerHTML = "Dog character";
    row.append(col7);

    let col8 = document.createElement("td");
    col8.innerHTML = "Dog status";
    row.append(col8);

    let col9 = document.createElement("td");
    col9.innerHTML = "Owner";
    row.append(col9);
}

function toDate(value) {
    let date = new Date(value);
    return date.toLocaleDateString();
}

function addDataFindD(array, table) {
    let addRow = document.createElement('tr');
    table.append(addRow);

    for (let j = 0; j < 9; j++) {
        let addCol = document.createElement('td');

        let key = array.uniqueId;

        switch (j) {
            case 1:
                key = array.dogName;
                break;
            case 2:
                key = array.gender;
                break;
            case 3:
                key = toDate(array.dogBirthDate);
                break;
            case 4:
                key = array.breed;
                break;
            case 5:
                key = array.color;
                break;
            case 6:
                key = array.dogCharacter;
                break;
            case 7:
                key = array.dogStatus;
                break;
            case 8:
                key = findClient(array.ownerId);
                break;
        }
        addCol.innerHTML = key;
        addRow.append(addCol);
    }
}

function addDataTableD(array, table) {
    for (let i = 0; i < array.length; i++) {
        let addRow = document.createElement('tr');
        table.append(addRow);

        for (let j = 0; j < 9; j++) {
            let addCol = document.createElement('td');

            let key = array[i].uniqueId;

            switch (j) {
                case 1:
                    key = array[i].dogName;
                    break;
                case 2:
                    key = array[i].gender;
                    break;
                case 3:
                    key = toDate(array[i].dogBirthDate);
                    break;
                case 4:
                    key = array[i].breed;
                    break;
                case 5:
                    key = array[i].color;
                    break;
                case 6:
                    key = array[i].dogCharacter;
                    break;
                case 7:
                    key = array[i].dogStatus;
                    break;
                case 8:
                    key = findClient(array[i].ownerId);
                    break;
            }
            addCol.innerHTML = key;
            addRow.append(addCol);
        }
    }
}

function loadOwners() {
    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allClients",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            if(data !== ""){
                let parent = document.getElementById("selectOwner");
                parent.innerHTML = "";

                for (let i = 0; i < data.length; i++) {
                    let owner = document.createElement("option");
                    owner.innerHTML = data[i].name + " " + data[i].lastName;
                    owner.value = data[i].uniqueId;
                    parent.append(owner);
                }
            }
        }
    });
}

function addFromBdD() {
    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allDogs",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("dogTable");
            parent.innerHTML = "";

            let table = document.createElement("table");

            createTitleTableD(table);

            addDataTableD(data, table);

            parent.append(table);
        }
    });
}
