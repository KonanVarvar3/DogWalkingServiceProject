let objectClient;

let newClient = {
    lastName: "",
    name: "",
    middleName: "",
    birthDate: "",
    phoneNumber: "",
    email: "",
    address: ""
}

function clearClient() {
    let parent = document.getElementById("findTable");
    parent.innerText = "";
}

function clearAllClient() {
    let parent = document.getElementById("clientTable");
    parent.innerText = "";
}

function formFindClient() {
    let str = document.getElementById("uniqueId").value;

    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/client?uniqueId=" + str,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("findTable");
            parent.innerHTML = "";

            let table = document.createElement("table");

            let row = document.createElement("tr");
            table.append(row);

            createTitleTable(table, row);

            addDataFind(data, table);

            parent.append(table);
        }
    });
}

function checkValid() {
    let valid = document.getElementsByClassName("inputClient");

    let check = true;

    for (let i = 0; i < valid.length; i++) {
        if (valid[i].value === "") {
            switch (i) {
                case 0:
                    document.getElementById("inputLastName").innerHTML = "Fill Last name!";
                    break;
                case 1:
                    document.getElementById("inputName").innerHTML = "Fill Name!";
                    break;
                case 2:
                    document.getElementById("inputMiddleName").innerHTML = "Fill Middle name!";
                    break;
                case 3:
                    document.getElementById("inputBirthday").innerHTML = "Fill Birthday!";
                    break;
                case 4:
                    document.getElementById("inputPhone").innerHTML = "Fill Phone!";
                    break;
                case 5:
                    document.getElementById("inputEmail").innerHTML = "Fill Email!";
                    break;
                case 6:
                    document.getElementById("inputAddress").innerHTML = "Fill Address!";
                    break;
            }
            check = false;
        }
    }
    return check;
}

function inputValueLastName() {
    document.getElementById("inputLastName").innerHTML = "";
}

function inputValueName() {
    document.getElementById("inputName").innerHTML = "";
}

function inputValueMiddleName() {
    document.getElementById("inputMiddleName").innerHTML = "";
}

function inputValueBirthday() {
    document.getElementById("inputBirthday").innerHTML = "";
}

function inputValuePhone() {
    document.getElementById("inputPhone").innerHTML = "";
}

function inputValueEmail() {
    document.getElementById("inputEmail").innerHTML = "";
}

function inputValueAddress() {
    document.getElementById("inputAddress").innerHTML = "";
}

function crt() {
    if (checkValid() === true) {
        let out = document.getElementById("out");
        out.innerHTML = "";

        newClient.lastName = document.getElementById("lastName").value;
        newClient.name = document.getElementById("name").value;
        newClient.middleName = document.getElementById("middleName").value;
        newClient.birthDate = document.getElementById("birthDate").value;
        newClient.phoneNumber = document.getElementById("phoneNumber").value;
        newClient.email = document.getElementById("email").value +
            document.getElementById("selectDomains").value;
        newClient.address = document.getElementById("address").value;

        objectClient = JSON.stringify(newClient);

        AJS.$.ajax({
            type: 'post',
            url: 'http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/createClient',
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: objectClient,

            success: function () {
                document.getElementById("lastName").value = "";
                document.getElementById("name").value = "";
                document.getElementById("middleName").value = "";
                document.getElementById("birthDate").value = "";
                document.getElementById("phoneNumber").value = "";
                document.getElementById("email").value = "";
                document.getElementById("address").value = "";

                let inf = document.getElementById("newClt");
                inf.innerText = "Client has been created!";
                inf.style.display = "block";

                setTimeout(function () {
                    inf.style.display = "none";
                }, 5000);

                addFromBd();
            }
        });
    }
}

function createTitleTable(row) {
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
    col8.innerHTML = "Address";
    row.append(col8);
}

function addDataFind(array, table) {
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
                key = array.address;
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

//Functions for edit mode
function createModeEditRow(array, addRow) {
    for (let i = 0; i < 9; i++) {
        let addCol = document.createElement('td');

        if (i < 8) {
            let key = array.uniqueId;

            switch (i) {
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
                    key = array.address;
                    break;
            }
            if (i === 0) {
                addCol.innerHTML = key;

            }else if(i === 4){
                let calendar = document.createElement("input");
                calendar.type = "date";
                calendar.className = "form-control";
                calendar.value = key;
                addCol.append(calendar);
            }
            else {
                let field = document.createElement("input");
                field.style.width = "80%";
                field.value = key;
                addCol.append(field);
            }
            addRow.append(addCol);

        } else {
            addCol.append(createButtonsEditMode(addRow));
            addRow.append(addCol);
        }
    }
}

function createButtonsEditMode(row) {
    let parent = document.createElement("div");
    parent.append(cancel());
    parent.append(saveButton(row));
    return parent;
}

function saveButton(row) {
    let saveButton = document.createElement("input");
    saveButton.type = "button";
    saveButton.value = "Save";

    saveButton.addEventListener("click", function () {
        let question = confirm("Are you sure?");
        if(question) saveChanges(row);
    });
    return saveButton;
}

function saveChanges(row) {
    let editObject = {
        uniqueId: row.children[0].textContent,
        lastName: row.children[1].children[0].value,
        name: row.children[2].children[0].value,
        middleName: row.children[3].children[0].value,
        birthDate: row.children[4].children[0].value,
        phoneNumber: row.children[5].children[0].value,
        email: row.children[6].children[0].value,
        address: row.children[7].children[0].value,
    };

    let sendObject = JSON.stringify(editObject);

    AJS.$.ajax({
        type: "put",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/updateClient",
        dataType: "json",
        contentType:"application/json; charset=utf-8",
        data: sendObject,

        success: function () {
            let inf = document.getElementById("newClt");
            inf.innerText = "";
            inf.innerText = "Changes saved!";
            inf.style.display = "block";

            setTimeout(function () {
                inf.style.display = "none";
            }, 5000);

            addFromBd();
        }
    });
}

function cancel() {
    let cancelButton = document.createElement("input");
    cancelButton.type = "button";
    cancelButton.value = "Cancel";

    cancelButton.addEventListener("click", function () {
        let inf = document.getElementById("newClt");
        inf.innerText = "";
        inf.innerText = "Editing canceled!";
        inf.style.display = "block";

        setTimeout(function () {
            inf.style.display = "none";
        }, 5000);

        addFromBd();
    });
    return cancelButton;
}

/////////////////////////////////

function addDataTable(array, table, idEditRow) {
    for (let i = 0; i < array.length; i++) {
        let addRow = document.createElement('tr');
        addRow.id = "row" + i;
        table.append(addRow);

        if (addRow.id === idEditRow) {
            createModeEditRow(array[i], addRow);

        } else {
            for (let j = 0; j < 9; j++) {
                let addCol = document.createElement('td');

                if (j < 8) {
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
                            key = array[i].address;
                            break;
                    }
                    addCol.innerHTML = key;
                    addRow.append(addCol);

                } else {
                    addCol.append(createEdit("" + i));
                    addRow.append(addCol);
                }
            }
        }
    }
}

//Function for edit
function createEdit(id) {
    let parent = document.createElement("div");
    parent.id = id;
    parent.append(createButnEdit(id));
    parent.append(createButnDelete(id));
    return parent;
}

function createButnEdit(id) {
    let buttonEdit = document.createElement("input");
    buttonEdit.type = "button";
    buttonEdit.value = "Edit";

    buttonEdit.addEventListener("click", function () {
        let idChangeButtonDiv = buttonEdit.parentElement.id;
        let row = document.getElementById("row" + idChangeButtonDiv);
        addFromBd(row.id);

        let inf = document.getElementById("newClt");
        inf.innerText = "";
        inf.innerText = "Edit mode activated";
        inf.style.display = "block";

        setTimeout(function () {
            inf.style.display = "none";
        }, 5000);
    });
    return buttonEdit;
}

function createButnDelete() {
    let buttonDelete = document.createElement("input");
    buttonDelete.type = "button";
    buttonDelete.value = "Delete";

    buttonDelete.addEventListener("click", function () {
        let question = confirm("Are you sure?");

        if(question){
            let idDetButtonDiv = buttonDelete.parentElement.id;
            let row = document.getElementById("row" + idDetButtonDiv);
            let clientId = row.children[0].textContent;
            let clientFullName = row.children[1].textContent + " " + row.children[2].textContent;
            deleteClient(clientId, clientFullName);
        }
    });
    return buttonDelete;
}

function deleteClient(clientId, clientFullName) {
    AJS.$.ajax({
        type: "delete",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/deleteClient?uniqueId=" + clientId,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: "json",

        success: function () {
            addFromBd();

            let inf = document.getElementById("newClt");
            inf.innerText = "";
            inf.innerText = "Client: " + clientFullName + " deleted!";
            inf.style.display = "block";

            setTimeout(function () {
                inf.style.display = "none";
            }, 5000);
        }
    });
}
///////////////////////////////

function addFromBd(idEditRow) {
    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allClients",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {

            let parent = document.getElementById("clientTable");
            parent.innerHTML = "";

            let table = document.createElement("table");
            let row = document.createElement("tr");

            createTitleTable(row);

            let col = document.createElement("td");
            col.innerHTML = "Edit";
            row.append(col);
            table.append(row);

            addDataTable(data, table, idEditRow);

            parent.append(table);
        }
    });
}
