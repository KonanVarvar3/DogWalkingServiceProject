
let objectDog;

let newDog = {
    dogName:"",
    gender:"",
    dogBirthDate:"",
    breed:"",
    color:"",
    dogCharacter:""
    // dogStatus:""
}

function clearDog() {

    let parent = document.getElementById("findTableD");
    parent.innerText="";
}

function clearAllDog() {

    let parent = document.getElementById("dogTable");
    parent.innerText="";
}

function formFindDog(){

    let xhttp1 = new XMLHttpRequest();

    var str = document.getElementById("uniqueIdD").value;

    xhttp1.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            myFunctionD(this.responseText);
        }
    }

    xhttp1.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/dog?uniqueId=" + str);
    xhttp1.send();

    function myFunctionD(data) {
        let array = JSON.parse(data, function(key, value) {
            if (key === 'dogBirthDate') return new Date(value);
            return value;
        });

        //let array = JSON.parse(data);

        let parent = document.getElementById("findTableD");
        parent.innerHTML = "";

        let table = document.createElement("table");

        createTitleTableD(table);

        addDataFindD(array,table);

        parent.append(table);
    }
}

function crtD() {

    let out = document.getElementById("outD");
    out.innerHTML="";

    newDog.dogName = document.getElementById("dogName").value;
    newDog.gender = document.getElementById("gender").value;
    newDog.dogBirthDate = document.getElementById("dogBirthDate").value;
    newDog.breed = document.getElementById("breed").value;
    newDog.color = document.getElementById("color").value;
    newDog.dogCharacter = document.getElementById("dogCharacter").value;
    // newDog.dogStatus = document.getElementById("dogStatus").value;

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
            //document.getElementById("dogStatus").value = "";

            document.getElementById("newDg").style.display = "block";

            setTimeout(function () {
                document.getElementById("newDg").style.display = "none";
            }, 3000);

            addFromBdD();
        }
    });
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
}

function addDataFindD(array,table) {

    let addRow = document.createElement('tr');
    table.append(addRow);

    for (let j = 0; j < 8; j++) {

        let addCol = document.createElement('td');

        let key = array.uniqueId;

        switch (j) {
            case 1: key = array.dogName;
                break;

            case 2: key = array.gender;
                break;

            case 3: key = array.dogBirthDate;
                break;

            case 4: key = array.breed;
                break;

            case 5: key = array.color;
                break;

            case 6: key = array.dogCharacter;
                break;

            case 7: key = array.dogStatus;
                break;
        }

        addCol.innerHTML = key;
        addRow.append(addCol);
    }
}

function addDataTableD(array,table) {
    for (let i = 0; i < array.length; i++) {

        let addRow = document.createElement('tr');
        table.append(addRow);

        for (let j = 0; j < 8; j++) {

            let addCol = document.createElement('td');

            let key = array[i].uniqueId;

            switch (j) {
                case 1: key = array[i].dogName;
                    break;

                case 2: key = array[i].gender;
                    break;

                case 3: key = array[i].dogBirthDate;
                    break;

                case 4: key = array[i].breed;
                    break;

                case 5: key = array[i].color;
                    break;

                case 6: key = array[i].dogCharacter;
                    break;

                case 7: key = array[i].dogStatus;
                    break;
            }

            addCol.innerHTML = key;
            addRow.append(addCol);
        }
    }
}

function addFromBdD(){

    let xhttp2 = new XMLHttpRequest();

    xhttp2.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            myFunctionD(this.responseText);
        }
    }

    xhttp2.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allDogs");
    xhttp2.send();

    function myFunctionD(data) {

        let array = JSON.parse(data, function(key, value) {
            if (key === 'dogBirthDate') return new Date(value);
            return value;
        });

        //let array = JSON.parse(data);

        let parent = document.getElementById("dogTable");
        parent.innerHTML = "";

        let table = document.createElement("table");

        createTitleTableD(table);

        addDataTableD(array,table);

        parent.append(table);
    }
}
