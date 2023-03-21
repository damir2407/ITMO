// Ограничение на выбор нескольких checkbox
$('.x-checkbox').click(function () {
    $('.x-checkbox').not(this).prop('checked', false);
});

// Событие для submit
$('[name="submit_button"]').click(function (event) {
    if (!validate()) {
        event.preventDefault();
    }


    // Проверка выбран(ы) ли X (чекбоксы)
    function checkX() {
        let k = 0;
        let chBoxes = document.getElementsByClassName("x-checkbox");
        for (let i = 0; i < chBoxes.length; i++) {
            if (chBoxes[i].checked) {
                k++;
            }
        }
        if (k === 0) {
            $('.checkBoxError').text("Выберите checkBox!");
            return false;
        } else {
            $('.checkBoxError').text("");
            return true;
        }
    }

    // Проверка Y на число, заполненность и на входимость в интервал(текст)
    function checkY() {
        let y;
        let yValue = ($('[name="id_y"]').val().replace(",", ".").trim());
        if ((isNaN(yValue)) || ((yValue.length === 0))) {
            $('.textError').text("Y должен быть представлен числом!");
            return false;
        } else {
            y = (parseFloat(yValue));
            if ((y <= -5) || (y >= 3)) {
                $('.textError').text("Y должен принимать значения (-5;3)");
                return false;
            } else {
                $('.textError').text("");
                return true;
            }
        }
    }

    function validate() {
        let xCheck = checkX();
        let yCheck = checkY();
        return xCheck && yCheck;
    }

})

// canvas
let canvas = document.getElementById("main_canvas");

canvas.onclick = function (event) {
    let rValue = $('#r_selected').val();
    let xValue = (event.offsetX - 110) / 67 * rValue;
    let yValue = (-event.offsetY + 110) / 67 * rValue;
    let newXValue = Math.round(xValue)
    if (yValue > -5 && yValue < 3) {
        ajaxCanvasSender(newXValue, yValue, rValue);
    }
}


function ajaxCanvasSender(x, y, r) {
    $.ajax({
        url: "controller",
        method: "GET",
        dataType: "json",
        data: {
            'canvasReady': 1,
            'id_x': x,
            'id_y': y,
            'id_r': r,
        },
        success: [
            function (data) {
                $('#result-table').append(
                    '<tr><td>' + data['x'] +
                    '</td><td>' + data['y'] +
                    '</td><td> ' + data['r'] +
                    '</td><td>' + data['hitValue'] +
                    '</td><td>' + data['executeTime'] +
                    '</td><td>' + data['currentTime'] +
                    '</td></tr>');
                draw(data['x'] / data['r'] * 67 + 110, -(data['y'] / data['r'] * 67 - 110), data['hitValue']);
            }
        ]
    })
}

function displayRSvg() {
    let svgPic = document.querySelector(".picture-svg").getSVGDocument();
    let rValue = $('#r_selected').val();
    svgPic.querySelector('.minus-Rx-coordinate').textContent = (-rValue).toString();
    svgPic.querySelector('.minus-half-Rx-coordinate').textContent = (-rValue / 2).toString();
    svgPic.querySelector('.half-Rx-coordinate').textContent = (rValue / 2).toString();
    svgPic.querySelector('.plus-Rx-coordinate').textContent = (rValue).toString();
    svgPic.querySelector('.minus-Ry-coordinate').textContent = (-rValue).toString();
    svgPic.querySelector('.minus-half-Ry-coordinate').textContent = (-rValue / 2).toString();
    svgPic.querySelector('.plus-half-Ry-coordinate').textContent = (rValue / 2).toString();
    svgPic.querySelector('.plus-Ry-coordinate').textContent = (rValue).toString();
}

function draw(x, y, hit) {
    let ctx = canvas.getContext("2d");
    ctx.beginPath();
    ctx.arc(x, y, 4, 0, Math.PI * 2);
    if (hit === true) ctx.fillStyle = 'green';
    else ctx.fillStyle = 'red';
    ctx.fill();
    ctx.closePath();
}



