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
            let row = document.createElement("tr");

            createTitleTableDW(row);
            table.append(row);

            addDataFindDW(data, table);

            parent.append(table);
        }
    });
}

function checkValid() {
    let valid = document.getElementsByClassName("inputDogWalker");

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

function crtDW() {
    if (checkValid() === true) {
        let out = document.getElementById("outDW");
        out.innerHTML = "";

        newDogWalker.lastName = document.getElementById("lastNameDW").value;
        newDogWalker.name = document.getElementById("nameDW").value;
        newDogWalker.middleName = document.getElementById("middleNameDW").value;
        newDogWalker.birthDate = document.getElementById("birthDateDW").value;
        newDogWalker.phoneNumber = document.getElementById("phoneNumberDW").value;
        newDogWalker.email = document.getElementById("emailDW").value
            +document.getElementById("selectDomainsDW").value;

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

                let inf = document.getElementById("newDgWlkr");
                inf.innerText = "";
                inf.innerText = "Dog walker has been created!";
                inf.style.display = "block";

                setTimeout(function () {
                    document.getElementById("newDgWlkr").style.display = "none";
                }, 3000);

                addFromBdDW();
            }
        });
    }
}

function createTitleTableDW(row) {
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
                key = array.dogWalkerStatus;
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
                    key = array.dogWalkerStatus;
                    break;
            }
            if (i === 0 || i === 7) {
                addCol.innerHTML = key;

            }else if(i === 4){
                createCalendar(addCol);
            }
            else {
                createInput(addCol, key);
            }
            addRow.append(addCol);

        } else {
            addCol.append(createButtonsEditMode(addRow));
            addRow.append(addCol);
        }
    }
}

function createInput(col, key) {
    let field = document.createElement("input");
    field.style.width = "80%";
    field.value = key;
    col.append(field);
}

function createCalendar(col) {
    let calendar = document.createElement("input");
    calendar.type = "date";
    calendar.className = "form-control";
    col.append(calendar);
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
        dogWalkerStatus: row.children[7].textContent
    };

    let sendObject = JSON.stringify(editObject);

    AJS.$.ajax({
        type: "put",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allUpdateDogWalker",
        dataType: "json",
        contentType:"application/json; charset=utf-8",
        data: sendObject,

        success: function () {
            let inf = document.getElementById("newDgWlkr");
            inf.innerText = "";
            inf.innerText = "Changes saved!";
            inf.style.display = "block";

            setTimeout(function () {
                inf.style.display = "none";
            }, 5000);

            addFromBdDW();
        }
    });
}

function cancel() {
    let cancelButton = document.createElement("input");
    cancelButton.type = "button";
    cancelButton.value = "Cancel";

    cancelButton.addEventListener("click", function () {
        let inf = document.getElementById("newDgWlkr");
        inf.innerText = "";
        inf.innerText = "Editing canceled!";
        inf.style.display = "block";

        setTimeout(function () {
            inf.style.display = "none";
        }, 5000);

        addFromBdDW();
    });
    return cancelButton;
}
/////////////////////////////////

function addDataTableDW(array, table, idEditRow) {
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
                            key = array[i].dogWalkerStatus;
                            break;
                    }
                    addCol.innerHTML = key;
                    addRow.append(addCol);

                }else {
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
    parent.append(createButnEdit());
    parent.append(createButnDelete());
    return parent;
}

function createButnEdit() {
    let buttonEdit = document.createElement("input");
    buttonEdit.type = "button";
    buttonEdit.value = "Edit";

    buttonEdit.addEventListener("click", function () {
        let idChangeButtonDiv = buttonEdit.parentElement.id;
        let row = document.getElementById("row" + idChangeButtonDiv);
        addFromBdDW(row.id);

        let inf = document.getElementById("newDgWlkr");
        inf.innerText = "";
        inf.innerText = "Edit mode activated!";
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
            let dogWalkerId = row.children[0].textContent;
            let dogWalkerFullName = row.children[1].textContent + " " + row.children[2].textContent;
            deleteDogWalker(dogWalkerId, dogWalkerFullName);
        }
    });
    return buttonDelete;
}

function deleteDogWalker(dogWalkerId, dogWalkerFullName) {
    AJS.$.ajax({
        type: "delete",
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/deleteDogWalker?uniqueId=" + dogWalkerId,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: "json",

        success: function () {
            addFromBdDW();

            let inf = document.getElementById("newDgWlkr");
            inf.innerText = "";
            inf.innerText = "Dog walker: " + dogWalkerFullName + " deleted!";
            inf.style.display = "block";

            setTimeout(function () {
                inf.style.display = "none";
            }, 5000);
        }
    });
}
///////////////////////////////

function addFromBdDW(idEditRow) {
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
            let row = document.createElement("tr");

            createTitleTableDW(row);

            let col = document.createElement("td");
            col.innerHTML = "Edit";
            row.append(col);
            table.append(row);

            addDataTableDW(data, table, idEditRow);

            parent.append(table);
        }
    });
}
