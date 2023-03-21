document.addEventListener('DOMContentLoaded', () => {
    "use strict";

    let reset = 1;


    if (reset == 1) {
        $.ajax({
            url: 'script.php',
            method: 'GET',
            dataType: 'html',
            data: {
                'reset': 1,
            },
            success: function (data) {
                document.getElementById("result-table").innerHTML = document.getElementById("result-table").innerHTML+ data;
                reset = 0;
            }
        });
    }
    


    // Обозначил форму
    const form = document.querySelector("form");


    // Переменные для R(подсчет нажатий и последний нажатый)
    let r;
    let lastClickedElement;
    let clickCount = 0;

    var selectedChBoxes;
    // Переменная для X

    let x 

    let ft = () =>  {
        console.log("hello");  
    }

    ft();




    // Подцветка выбранной кнопки
    $('.button1').click(function () {
        if (clickCount == 0) {
            this.style.outline = "2px solid #0000ff";
            this.style.transform = "scale(1.05)";
            clickCount++;
            lastClickedElement = this;
            r = lastClickedElement.value;
        } else {
            lastClickedElement.style.outline = "";
            lastClickedElement.style.transform = "";
            lastClickedElement = this;
            r = lastClickedElement.value;
            this.style.outline = "2px solid #0000ff";
            this.style.transform = "scale(1.05)";
        }

    });






    // Событие для submit
    form.addEventListener('submit', (even) => {

        // Убрал перезагрузку сайта при submit
        even.preventDefault();



        // Проверка X на число, заполненность и на входимость в интервал(текст)
        function checkX() {

            if ((isNaN(($('[name="id_x"]').val().replace(",", ".")))) || (($('[name="id_x"]').val().trim().length == 0))) {
                $('.textError').text("X должен быть представлен числом!");
                return false;
            } else {
                x = (parseFloat($('[name="id_x"]').val().trim().replace(",", ".")));
                if ((x <= -3) || (x >= 3)) {
                    $('.textError').text("X должен принимать значения (-3;3)");
                    return false;
                } else {
                    $('.textError').text("");
                    return true;
                }
            }
        }



        // Проверка выбран(ы) ли Y (чекбоксы)
        function checkY() {

            // Выделяю все чекбоксы
            var chBoxes = document.getElementsByClassName("r-checkbox");

            // Для хранения value выбранных checkBox
            selectedChBoxes = new Array();

            for (var i = 0; i < chBoxes.length; i++) {
                if (chBoxes[i].checked) {
                    selectedChBoxes.push(chBoxes[i].value);
                }

            }
            if (selectedChBoxes.length < 1) {
                $('.checkBoxError').text("Выберите checkBox!");
                return false;
            } else {
                $('.checkBoxError').text("");
                return true;
            }

        }



        // Проверка выбран ли R (кнопки)
        function checkR() {
            if (!isFinite(r)) {
                $('.button1Error').text("Выбери button!");
                return false;
            } else {
                $('.button1Error').text("");
                return true;
            }


        }

        // Исполнение всех валидаций с возвращением их результатов
        function validate() {
            var xBool = checkX();
            var yBool = checkY();
            var rBool = checkR();
            return xBool && yBool && rBool;
        }



        // Отправка на php
        if (validate()) {

            $.ajax({
                url: 'script.php',
                method: 'GET',
                dataType: 'html',
                data: {
                    'xValue': x,
                    'yValue': selectedChBoxes,
                    'rValue': r,
                },
                success: function (data) {
                    document.getElementById("result-table").innerHTML = document.getElementById("result-table").innerHTML + data;
                }
            });
            selectedChBoxes = [];

        }




    });
});