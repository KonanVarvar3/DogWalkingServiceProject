let objectReq;

let newReq = {
    requestPlace: "",
    timeWalk: "",
    petId: "",
    walkDuration: "",
    clientId: ""
}

function clearReq() {
    let parent = document.getElementById("findTableReq");
    parent.innerText = "";
}

function clearAllReq() {
    let parent = document.getElementById("requestTable");
    parent.innerText = "";
}

function formFindReq() {
    let str = document.getElementById("uniqueIdReq").value;

    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/requestWalk?uniqueId=" + str,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("findTableReq");
            parent.innerHTML = "";

            let table = document.createElement("table");
            let row = document.createElement("tr");
            table.append(row);

            createTitleTableReq(table, row);

            addDataFindReq(data, table);

            parent.append(table);
        }
    });
}

function checkValid() {
    let valid = document.getElementsByClassName("inputReq");

    let check = true;

    for (let i = 0; i < valid.length; i++) {
        if (valid[i].value === "") {
            switch (i) {
                case 1:
                    document.getElementById("inputPet").innerHTML = "Go to pets page and create" +
                        " it for this owner!";
                    break;
                case 2:
                    document.getElementById("inputRequestPlace").innerHTML = "Fill Request place!";
                    break;
                case 3:
                    document.getElementById("inputTimeWalk").innerHTML = "Fill Time walk!";
                    break;
                case 4:
                    document.getElementById("inputWalkDuration").innerHTML = "Fill Walk duration!";
                    break;
            }
            check = false;
        }
        if ((valid[0].value || valid[1].value) === "default") {
            document.getElementById("inputOwner").innerHTML = "Select Owner!";
            check = false;
        }
        if(!(valid[4].value.match(/\d/g))){
            document.getElementById("inputWalkDuration").innerHTML = "Enter number!";
        }
    }
    return check;
}

function inputValueSelectOwner() {
    document.getElementById("inputOwner").innerHTML = "";
    document.getElementById("inputPet").innerHTML = "";
}

function inputValueRequestPlace() {
    document.getElementById("inputRequestPlace").innerHTML = "";
}

function inputValueTimeWalk() {
    document.getElementById("inputTimeWalk").innerHTML = "";
}

function inputValueWalkDuration() {
    document.getElementById("inputWalkDuration").innerHTML = "";
}

function crtReq() {
    if (checkValid() === true) {
        let out = document.getElementById("outReq");
        out.innerHTML = "";

        newReq.clientId = document.getElementById("selectReqOwner").value;
        newReq.petId = document.getElementById("selectPet").value;
        newReq.requestPlace = document.getElementById("requestPlace").value;
        newReq.timeWalk = document.getElementById("timeWalk").value;
        newReq.walkDuration = document.getElementById("walkDuration").value;

        objectReq = JSON.stringify(newReq);

        AJS.$.ajax({
            type: 'post',
            url: 'http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/createRequestWalk',
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: objectReq,

            success: function () {
                document.getElementById("requestPlace").value = "";
                document.getElementById("timeWalk").value = "";
                document.getElementById("walkDuration").value = "";

                document.getElementById("newReq").style.display = "block";

                setTimeout(function () {
                    document.getElementById("newReq").style.display = "none";
                }, 3000);

                addFromBdReq();
            }
        });
    }
}

function createTitleTableReq(table, row) {
    let col1 = document.createElement("td");
    col1.innerHTML = "Owner";
    row.append(col1);

    let col2 = document.createElement("td");
    col2.innerHTML = "Pet";
    row.append(col2);

    let col3 = document.createElement("td");
    col3.innerHTML = "Request place";
    row.append(col3);

    let col4 = document.createElement("td");
    col4.innerHTML = "Time walk";
    row.append(col4);

    let col5 = document.createElement("td");
    col5.innerHTML = "Walk duration,min";
    row.append(col5);

    let col6 = document.createElement("td");
    col6.innerHTML = "Request status";
    row.append(col6);

    let col7 = document.createElement("td");
    col7.innerHTML = "ID request";
    row.append(col7);

    let col8 = document.createElement("td");
    col8.innerHTML = "Dog walker";
    row.append(col8);
}

function toDate(value) {
    let date = new Date(value);
    return date.toLocaleString();
}

function findClient(id) {
    let owner = "";

    let xhttp = new XMLHttpRequest();

    xhttp.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/client?uniqueId="
        + id, false);
    xhttp.send();

    if (xhttp.status === 200) {
        myFunction(xhttp.responseText);
    } else {
        // alert(xhttp.status + ': ' + xhttp.statusText);
        owner = "No owner";
    }

    function myFunction(data) {
        let array = JSON.parse(data);
        owner = array.name + " " + array.lastName;
    }

    return owner;
}

function findDog(id) {
    let dog = "";

    let xhttp = new XMLHttpRequest();

    xhttp.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/dog?uniqueId="
        + id, false);
    xhttp.send();

    if (xhttp.status === 200) {
        myFunction(xhttp.responseText);
    } else {
        dog = "No dog";
    }

    function myFunction(data) {
        let array = JSON.parse(data);
        dog = array.dogName + " " + array.breed;
    }

    return dog;
}

function findDogWalker(id) {
    let dogWalker = "";

    let xhttp = new XMLHttpRequest();

    xhttp.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/dogWalker?uniqueId="
        + id, false);
    xhttp.send();

    if (xhttp.status === 200) {
        myFunction(xhttp.responseText);
    } else {
        dogWalker = "No dog walker";
    }

    function myFunction(data) {
        if (data === "") {
            dogWalker = "No dog walker";
        } else {
            let array = JSON.parse(data);
            dogWalker = array.lastName + " " + array.name;
        }
    }
    return dogWalker;
}

function addDataFindReq(array, table) {
    let addRow = document.createElement('tr');
    table.append(addRow);

    for (let j = 0; j < 8; j++) {
        let addCol = document.createElement('td');

        let key = findClient(array.clientId);

        switch (j) {
            case 1:
                key = findDog(array.petId);
                break;
            case 2:
                key = array.requestPlace;
                break;
            case 3:
                key = toDate(array.timeWalk);
                break;
            case 4:
                key = array.walkDuration;
                break;
            case 5:
                key = array.requestWalkStatus;
                break;
            case 6:
                key = array.uniqueId;
                break;
            case 7:
                key = findDogWalker(array.dogWalkerId);
                break;
        }
        addCol.innerHTML = key;
        addRow.append(addCol);
    }
}

function addDataTableReq(array, table) {
    for (let i = 0; i < array.length; i++) {
        let statusReq = "";
        let walkerId = "";
        let dogId = "";

        let addRow = document.createElement('tr');
        addRow.id = "row" + i;
        table.append(addRow);

        for (let j = 0; j < 9; j++) {
            let addCol = document.createElement('td');

            if (j < 8) {
                let key = findClient(array[i].clientId);

                switch (j) {
                    case 1:
                        key = findDog(array[i].petId);
                        dogId = array[i].petId;
                        break;
                    case 2:
                        key = array[i].requestPlace;
                        break;
                    case 3:
                        key = toDate(array[i].timeWalk);
                        break;
                    case 4:
                        key = array[i].walkDuration;
                        break;
                    case 5:
                        key = array[i].requestWalkStatus;
                        statusReq = key;
                        break;
                    case 6:
                        key = array[i].uniqueId;
                        break;
                    case 7:
                        key = findDogWalker(array[i].dogWalkerId);
                        walkerId = array[i].dogWalkerId;
                        break;
                }
                addCol.innerHTML = key;
                addRow.append(addCol);

            } else {
                if(statusReq === "SEARCHING_WALKER"){
                    addCol.append(createPickReq("" + i, dogId));
                }
                if(statusReq === "WALKING"){
                    addCol.append(createFinishReq("" + i, walkerId, dogId));
                }
                if(statusReq === "COMPLETED"){
                    addCol.innerHTML = "COMPLETED";
                }
                addRow.append(addCol);
            }
        }
    }
}

//Functions for pick request like a dog walker
function getAllWalkers() {
    let parent = document.createElement("select");
    parent.id = "selectWalkerReq";

    let xhttp = new XMLHttpRequest();

    xhttp.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allDogWalkers", false);
    xhttp.send();

    if (xhttp.status === 200) {
        myFunction(xhttp.responseText);
    }

    function myFunction(data){
        if (data !== "") {
            let array = JSON.parse(data);

            for (let i = 0; i < array.length; i++) {
                let walker = document.createElement("option");
                walker.innerHTML = array[i].lastName + " " + array[i].name;
                walker.value = array[i].uniqueId;
                parent.append(walker);
            }
        }
        else {
            let noWalkers = document.createElement("option");
            noWalkers.innerHTML = "No Walkers";
            noWalkers.value = "No Walkers";
            parent.append(noWalkers);
        }
    }
    return parent;
}

function updateReqWalk(idRequest, idWalker) {
    let reqObject = {
        uniqueId: idRequest,
        dogWalkerId: idWalker,
        requestWalkStatus: "WALKING"
    }
    let sendObject = JSON.stringify(reqObject);

    AJS.$.ajax({
        type: "put",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/updateRequestWalk",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: sendObject,

        success: function () {
            addFromBdReq();
        }
    });
}

function updateDogWalker(id, status) {
    let dogWalkerObject = {
        uniqueId: id,
        dogWalkerStatus: status
    }
    let sendObject = JSON.stringify(dogWalkerObject);

    AJS.$.ajax({
        type: "put",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/updateDogWalker",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: sendObject,

        success: function () {}
    });
}

function updateDog(id, status) {
    let dogObject = {
        uniqueId: id,
        dogStatus: status
    }
    let sendObject = JSON.stringify(dogObject);

    AJS.$.ajax({
        type: "put",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/updateDog",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: sendObject,

        success: function () {}
    });
}

function pickRequest(dogId) {
    let butn = document.createElement("input");
    butn.type = "button";
    butn.value = "Pick request";

    butn.addEventListener("click", function () {
        let idDivButton = butn.parentElement.id;
        let idRow = document.getElementById("row" + idDivButton);
        let idRequest = idRow.children[6].textContent;
        let idWalker = document.getElementById(idDivButton).children[0].value;

        alert("Request has been picked!");
        updateReqWalk(idRequest, idWalker);
        updateDogWalker(idWalker, "BUSY");
        updateDog(dogId, "WALKING");
    });
    return butn;
}

function createPickReq(id, dogId) {
    let parent = document.createElement("div");
    parent.id = id;
    parent.append(getAllWalkers());
    parent.append(pickRequest(dogId));
    return parent;
}
////////////////////////////////////////////////

//Functions for finish request like a dog walker
function updateFinishReqWalk(idRequest){
    let reqObject = {
        uniqueId: idRequest,
        requestWalkStatus: "COMPLETED"
    }
    let sendObject = JSON.stringify(reqObject);

    AJS.$.ajax({
        type: "put",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/updateRequestWalk",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: sendObject,

        success: function () {
            addFromBdReq();
        }
    });
}

function createFinishReq(id, walkerId, dogId){
    let parent = document.createElement("div");
    parent.id = id;

    let finishButn = document.createElement("input");
    finishButn.type = "button";
    finishButn.value = "Finish request";

    finishButn.addEventListener("click", function () {
        let idFinishButton = finishButn.parentElement.id;
        let idRow = document.getElementById("row" + idFinishButton);
        let idRequest = idRow.children[6].textContent;

        alert("Request has been completed!");
        updateFinishReqWalk(idRequest);
        updateDogWalker(walkerId, "FREE");
        updateDog(dogId, "AT_HOME");
    });
    parent.append(finishButn);

    return parent;
}
////////////////////////////////////////////

loadOwners();

function createOptionPet(parent, strHtml, value) {
    let pet = document.createElement("option");
    pet.innerHTML = strHtml;
    pet.value = value;
    parent.append(pet);
}

function loadAddress(clientId) {
    if (clientId === "default") {
        let address = document.getElementById("requestPlace");
        address.value = "";
        return;
    }

    AJS.$.ajax({
        type: "get",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/client?uniqueId=" + clientId,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: "json",

        success: function (data) {
            if (data !== null) {
                let address = document.getElementById("requestPlace");
                address.value = "";
                address.value = data.address;
            }
        }
    });
}

function loadInf() {
    let str = document.getElementById("selectReqOwner").value;
    let parent = document.getElementById("selectPet");

    loadAddress(str);

    if (str !== "default") {
        AJS.$.ajax({
            type: 'get',
            url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/ownerDogs?ownerId=" + str,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: "json",

            success: function (data) {
                parent.innerHTML = "";

                if (data !== null) {
                    for (let i = 0; i < data.length; i++) {
                        createOptionPet(parent, data[i].dogName + " " + data[i].breed,
                            data[i].uniqueId);
                    }
                } else {
                    createOptionPet(parent, "no pets", "");
                }
            }
        });
    } else {
        parent.innerHTML = "";
        createOptionPet(parent, "no pets", "default");
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
            if (data !== "") {
                let parent = document.getElementById("selectReqOwner");
                parent.innerHTML = "";

                let owner = document.createElement("option");
                owner.innerHTML = "default";
                owner.value = "default";
                parent.append(owner);

                for (let i = 0; i < data.length; i++) {
                    let ownerReq = document.createElement("option");
                    ownerReq.innerHTML = data[i].name + " " + data[i].lastName;
                    ownerReq.value = data[i].uniqueId;
                    parent.append(ownerReq);
                }
            }
        }
    });
}

function addFromBdReq() {
    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allRequestWalks",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("requestTable");
            parent.innerHTML = "";

            let table = document.createElement("table");
            let row = document.createElement("tr");
            table.append(row);

            createTitleTableReq(table, row);

            let col = document.createElement("td");
            col.innerHTML = "Pick request";
            row.append(col);

            addDataTableReq(data, table);

            parent.append(table);
        }
    });
}
























