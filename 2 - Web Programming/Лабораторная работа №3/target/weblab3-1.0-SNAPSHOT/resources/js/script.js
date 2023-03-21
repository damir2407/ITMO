let currentR;

$(document).ready(function () {


    let lastXClickedElement;
    let xClickCount = 0;

    let lastRClickedElement;
    let rClickCount = 0;
    let r;

    $('.x-commandLink').click(function () {
        if (xClickCount == 0) {
            this.style.color = '#FF0000';
            xClickCount++;
            lastXClickedElement = this;
        } else {
            lastXClickedElement.style.color = "";
            lastXClickedElement = this;
            this.style.color = '#FF0000';
        }
    })


    $('.r-commandLink').click(function () {
        if (rClickCount == 0) {
            this.style.color = '#FF0000';
            rClickCount++;
            lastRClickedElement = this;
        } else {
            lastRClickedElement.style.color = "";
            lastRClickedElement = this;
            this.style.color = '#FF0000';
        }
    })


    // Событие для submit
    $('.sub-button').click(function (event) {

        if (!validate()) {
            event.preventDefault();
        }

        setTimeout(function () {
            drawAllPoints();
        }, 400);


    })

    // Проверка выбран ли X
    function checkX() {
        if (xClickCount === 0) {
            $('.xCommandLinkError').text("Выберите commandLink!");
            return false;
        } else {
            $('.xCommandLinkError').text("");
            return true;
        }
    }

    // Проверка Y на число, заполненность и на входимость в интервал(текст)
    function checkY() {
        let y;
        let yValue = $(PrimeFaces.escapeClientId("formId:id_y")).val().replace(",", ".").trim();
        if ((isNaN(yValue)) || ((yValue.length === 0))) {
            $('.inputTextError').text("Y должен быть представлен числом!");
            return false;
        } else {
            y = (parseFloat(yValue));
            if ((y <= -3) || (y >= 3)) {
                $('.inputTextError').text("Y должен принимать значения (-3;3)");
                return false;
            } else {
                $('.inputTextError').text("");
                return true;
            }
        }
    }

    // Проверка выбран ли R
    function checkR() {
        if (rClickCount === 0) {
            $('.rCommandLinkError').text("Выберите commandLink!");
            return false;
        } else {
            $('.rCommandLinkError').text("");
            return true;
        }
    }

    function validate() {
        let xCheck = checkX();
        let yCheck = checkY();
        let rCheck = checkR();
        return xCheck && yCheck && rCheck;
    }


    $('.res-button').click(function (event) {
        xClickCount = 0;
        rClickCount = 0;
        if (lastXClickedElement != undefined) lastXClickedElement.style.color = "";
        if (lastRClickedElement != undefined) lastRClickedElement.style.color = "";
        $(PrimeFaces.escapeClientId("formId:id_y")).val("");
        $('.xCommandLinkError').text("");
        $('.inputTextError').text("");
        $('.rCommandLinkError').text("");
        let svgPic = document.querySelector(".picture-svg").getSVGDocument();
        svgPic.querySelector('.minus-Rx-coordinate').textContent = ("-R");
        svgPic.querySelector('.minus-half-Rx-coordinate').textContent = ("-R/2")
        svgPic.querySelector('.half-Rx-coordinate').textContent =("R/2");
        svgPic.querySelector('.plus-Rx-coordinate').textContent = ("R");
        svgPic.querySelector('.minus-Ry-coordinate').textContent = ("-R");
        svgPic.querySelector('.minus-half-Ry-coordinate').textContent = ("-R/2");
        svgPic.querySelector('.plus-half-Ry-coordinate').textContent = ("R/2");
        svgPic.querySelector('.plus-Ry-coordinate').textContent = ("R");
        clearCanvas();
    })


    let canvas = document.getElementById("main_canvas");
    canvas.onclick = function (event) {
        if (checkR()) {
            let xValue = (event.offsetX - 110) / 67 * currentR;
            let yValue = (-event.offsetY + 110) / 67 * currentR;
            let newXValue = Math.round(xValue);
            if (yValue > -3 && yValue < 3) {
                $(".hidden_x").val(newXValue);
                $(".hidden_y").val(yValue);
                $(".submit_canvas").click();
            }
            setTimeout(function () {
                drawAllPoints();
            }, 400);
        }
    }


})

function displayRSvg(rValue) {
    currentR = rValue;
    let svgPic = document.querySelector(".picture-svg").getSVGDocument();
        svgPic.querySelector('.minus-Rx-coordinate').textContent = (-rValue).toString();
    svgPic.querySelector('.minus-half-Rx-coordinate').textContent = (-rValue / 2).toString();
    svgPic.querySelector('.half-Rx-coordinate').textContent = (rValue / 2).toString();
    svgPic.querySelector('.plus-Rx-coordinate').textContent = (rValue).toString();
    svgPic.querySelector('.minus-Ry-coordinate').textContent = (-rValue).toString();
    svgPic.querySelector('.minus-half-Ry-coordinate').textContent = (-rValue / 2).toString();
    svgPic.querySelector('.plus-half-Ry-coordinate').textContent = (rValue / 2).toString();
    svgPic.querySelector('.plus-Ry-coordinate').textContent = (rValue).toString();
    clearCanvas();
    setTimeout(function () {
        drawAllPoints();
    }, 400);



}

function draw(x, y, hit) {
    let canvas = document.getElementById("main_canvas");
    let ctx = canvas.getContext("2d");
    ctx.beginPath();
    ctx.arc(x, y, 4, 0, Math.PI * 2);
    if (hit === "true") ctx.fillStyle = 'green';
    else ctx.fillStyle = 'red';
    ctx.fill();
    ctx.closePath();
}

function drawAllPoints() {
    $("#main_table tbody tr").each(function () {
        const point = $(this);
        let x = parseFloat(point.find("td:first-child").text());
        let y = parseFloat(point.find("td:nth-child(2)").text());
        let hit = point.find("td:nth-child(6)").text();
        hit = hit.trim();
        if (isNaN(x) || isNaN(y)) return;
        draw((x / currentR * 67 + 110), -(y / currentR * 67 - 110), hit);
    })
}

function clearCanvas() {
    let canvas = document.getElementById("main_canvas");
    canvas.getContext('2d').clearRect(0, 0, 220, 220);
}


