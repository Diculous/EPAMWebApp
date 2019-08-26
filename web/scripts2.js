
function getData(callback) {
    var xhrequest = new XMLHttpRequest();
    function reqReadyStateChange() {
        if (xhrequest.readyState === 4) {
            var status = xhrequest.status;
            if (status === 200) {
                var data = JSON.parse(xhrequest.responseText);
                if (callback) {
                    callback(data);
                }
                var table = document.getElementById("table");
                for (var i = 0; i < data.length; i++) {
                    var tr = document.createElement("tr");

                    fillCell(data[i].accountNumber, tr);
                    fillCell(data[i].balance, tr);
                    fillCell(data[i].isBlocked, tr);

                    table.appendChild(tr);
                }
            } else {
                console.log("error");
            }
        }
    }
    xhrequest.open("POST", "loadClientAJAX", true);
    xhrequest.onreadystatechange = reqReadyStateChange;
    xhrequest.send();
}

function fillCell(value, row) {
    var td = document.createElement("td");
    td.textContent = value;
    row.appendChild(td);
}

function handleResponse(data) {
    document.getElementById("name").innerHTML = "Welcome to your accounts list, comrade " + data[0].name;
    document.getElementById("address").innerHTML = "Your address: " + "<b>" + data[0].address +"</b>";
    document.getElementById("passport").innerHTML = "Your passport number: " + "<b>" + data[0].passportNumber +"</b>";
    document.getElementById("dateOfBirth").innerHTML = "Your birth date: " + "<b>" + data[0].dateOfBirth +"</b>";
    var select = document.querySelector('#fill select');
    var selectPay = document.querySelector('#pay select');
    var selectBlock = document.querySelector('#block select');
    var option;
    var optionPay;
    var optionBlock;
    for(var i = 0; i < data.length; i++) {
        option = document.createElement('option');
        option.innerHTML = data[i].accountNumber;
        option.value = data[i].accountNumber;
        select.appendChild(option);
        optionPay = document.createElement('option');
        optionPay.innerHTML = data[i].accountNumber;
        optionPay.value = data[i].accountNumber;
        selectPay.appendChild(optionPay);
        optionBlock = document.createElement('option');
        optionBlock.innerHTML = data[i].accountNumber;
        optionBlock.value = data[i].accountNumber;
        selectBlock.appendChild(optionBlock);
    }
}

function init() {
    getData(handleResponse);
}