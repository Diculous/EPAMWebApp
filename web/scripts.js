
function postServletJSON(srvURL, form) {
    var bank = {};
    var mainform = document.getElementById(form);
    for(var i = 0; i < mainform.elements.length; i++) {
        if (mainform.elements[i].type === "text") {
            bank[mainform.elements[i].name] = mainform.elements[i].value;
        }
    }

    console.log(bank.toString());
    var bankJSON = JSON.stringify(bank);
    var xhrequest = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhrequest.readyState === 4) {
            var status = xhrequest.status;
            if (status !== 200) {
                document.write("Неверный формат данных" + xhrequest.statusText);
            }
        }
    }
    xhrequest.open("POST", srvURL, true);
    xhrequest.onreadystatechange = reqReadyStateChange;
    xhrequest.send(bankJSON);
}

function getData(callback) {
    var xhrequest = new XMLHttpRequest();
    function reqReadyStateChange() {
        if (xhrequest.readyState === 4) {
            var status = xhrequest.status;
            if (status === 200) {
                var data = JSON.parse(xhrequest.responseText);

                var table = document.getElementById("table");
                for (var i = 0; i < data.length; i++) {
                    var tr = document.createElement("tr");

                    fillCell(data[i].idAccount, tr);
                    fillCell(data[i].ownerId, tr);
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
    xhrequest.open("POST", "loadAJAX", true);
    xhrequest.onreadystatechange = reqReadyStateChange;
    xhrequest.send();
}

function fillCell(value, row) {
    var td = document.createElement("td");
    td.textContent = value;
    row.appendChild(td);
}
