function randomNumber(low, high) {
    return Math.floor(Math.random() * (high - low + 1)) + low;
}

let makeTable = [];

function newTable() {
    makeTable = [];
    let size = Number(document.getElementById("boxSize").value);
    if(size<=10){
        let table = "";
        table += "<table id='bBoard'>";
        for (let i = 0; i < size; i++) {

            table += "<tr>";
            makeTable.push(new Array(size));
            for (let j = 0; j < size; j++) {
                let bigBoi = Math.floor(Math.random() * Math.pow(size, 2)) + 1;
                makeTable[i][j] = bigBoi;

                table += "<td class= 'bingoSquares' id='" + i + "," + j + "'>" + bigBoi + "</td>";
            }
            table += "</tr>";
        }
        table += "</table>";
        document.getElementById("bBoard").innerHTML = table;

    }
}
function changeColor() {

    let changeCellColor;

    let Red;
    if (Red.checked) {
        changeCellColor = "red";
    } else {
        let Green;
        if (Green.checked) {
            changeCellColor = "green";
        }
        else {
            let Blue;
            if (Blue.checked) {
                changeCellColor = "blue";
            }
        }
    }

    let lel = document.getElementsByClassName("bingoSquares");

    for (let i = 0; i < lel.length; i = i + 1) {
        lel[i].style.color = changeCellColor;
    }

}

function fontType() {

    let fontStyle;

    let arial;
    if (arial.selected) {
        fontStyle =
            "Arial, sans-serif";
    }
    else {
        let roman;
        if (roman.selected) {
            fontStyle = "Times New Roman, serif";
        }
        else {
            let courier;
            if (courier.selected) {
                fontStyle = "Courier, monospace";
            }

        }
    }

    let lel = document.getElementsByClassName("bingoSquares");

    for (let i = 0; i < lel.length; i = i + 1) {
        lel[i].style.fontFamily = fontStyle;
    }

}

function fSize() {

    let fontSize;

    let ten;
    if (ten.selected) {
        fontSize = "10px";
    } else {
        let twenty;
        if (twenty.selected) {
            fontSize = "20px";
        } else {
            let thirty;
            if (thirty.selected) {
                fontSize = "30px";
            }
        }
    }


    let lel = document.getElementsByClassName("bingoSquares");

    for (let i = 0; i < lel.length; i = i + 1) {
        lel[i].style.fontSize = fontSize;
    }
}

// document.getElementById(i + "," + j).style.backgroundColor = "red"

function randomizeN(low, high) {
    return Math.floor(Math.random() * (high - low + 1)) + low;
}

let getNums = [];
let makeCall;

function setSquares() {
    const size = Number(document.getElementById("boxSize").value);
    makeCall = randomizeN(1, size * size);
    const red = randomizeN(0, 255);
    const green = randomizeN(0, 255);
    const blue = randomizeN(0, 255);
    makeCall = '<span style=\"color:rgb(' + red + ',' + green + ',' + blue + ');\">' + makeCall + '</span>';
    getNums.push(" " + makeCall);
    callingNumbersBoi.innerHTML = getNums
}

let please = checkRows();
if (please !== -1) {
    for (let i = 0; i < a.length; i++) {
        const lets = document.getElementById("bingo");
        const get = lets.getElementsByTagName("tr")[i];
        const money = get.getElementsByTagName("td")[j];
        i.style.backgroundColor = "green";
        i.style.color = "white";
    }
}

function checkRows() {
    let callNums=[];
    for (let i = 0; i < callNums.length; i++) {
        let count = 0;
        for (let j = 0; j < callNums.length; j++) {
            if (callNums[i][j] === 1) {
                count++;
            }
        }
        if (count === callNums[i].length) {
            for (j = 0; j < a.length; j++) {
                const x = document.getElementById("bingo");
                const row = x.getElementsByTagName("tr")[i];
                const td = row.getElementsByTagName("td")[j];
                td.style.background = "blue";
                td.style.color = "white";
            }
            return i;
        }
    }
    return -1;
}
