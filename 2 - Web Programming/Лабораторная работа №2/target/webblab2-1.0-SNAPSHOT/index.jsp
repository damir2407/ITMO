<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="icon" href="pictures/icon.ico">
    <title>Лабораторная работа №1</title>


    <!-- Стили -->
    <style>

        body {
            font-size: 18.5px;
            font-family: sans-serif;
            background-color: #e9e6ee;
        }

        /* Селекторы классов */
        .header_part {
            width: 100%;
            color: #F5F5DC;
            background-color: #CD853F;
            height: 30px;
        }

        .picture_word,
        .table_word,
        .value_word {
            display: inline-block;
            position: relative;
            vertical-align: top;
            width: 100%;
            height: 54px;
            text-align: center;
            background-color: #CD853F;
            font-size: 18.5px;
            font-weight: normal;
        }

        .picture {
            position: relative;
            width: 100%;
            text-align: center;
        }


        .coords-col {
            width: 13%;
        }


        .value_label {
            width: auto;
            padding: 25px 15px;
            text-align: left;
        }

        .center-labeled {
            position: relative;
            font-size: 14px;
            margin-left: 10px;
        }

        .center-labeled label {
            position: absolute;
            top: 13px;
            left: 5px;
        }


        .button {
            height: 27px;
            width: 50%;
            color: #ffffff;
            background-color: #CD853F;
            margin: 0px 100px;
            float: right;
        }


        .textError,
        .checkBoxError,
        .selectError {
            caret-color: transparent;
            padding: 10px;
            color: red;
            font-size: 12px;
        }


        /* Селекторы псевдоклассов */
        .button:hover {
            background-color: #FF7F50;
        }


        #result-table tr:nth-child(2n):hover {
            background-color: #e9e9e9;
        }

        #result-table tr:nth-child(2n-1):hover {
            background-color: #e9e9e9;
        }


        /* Селекторы дочерних элементов */

        span > h1 {
            background-color: #CD853F !important;
        }

        span > h1 {
            font-size: 18.5px;
            font-weight: normal;
            color: #F5F5DC;
            background-color: red;
        }


        body > table {
            width: 100%;
            border-spacing: 30px;
        }


        /* Селекторы атрибутов */
        td[subject_part] {
            background-color: #ffffff;
        }

        td[subject_part="picture_part"] {
            width: 50%;
            vertical-align: top;
        }

        td[subject_part="table_part"] {
            width: 65%;
            vertical-align: top;
        }


        h1[header_word] {
            background-color: #CD853F;
            font-size: 18.5px;
            font-weight: normal;
        }

        h1[header_word="left_side"] {
            float: left;
            width: 33.33333%;
            text-align: left;
        }

        h1[header_word="center_side"] {
            float: left;
            width: 33.33333%;
            text-align: center;
        }

        h1[header_word="right_side"] {
            float: right;
            width: 33.33333%;
            text-align: right;
        }


        #result-table {
            border-collapse: collapse;
            width: 100%;
            text-align: center;
            background-color: #F5DEB3;
            overflow-x: auto;
            overflow-y: auto;
            height: 20%;
        }

        #value_part {
            width: 100%;
            margin: auto;
            text-align: center;
        }

        #r_selected {
            width: 23%;
        }


        #main_canvas {
            position: absolute;
            left: 0;
            right: 0;
            margin: 0 auto;
        }

        .picture-svg {
        }

    </style>
</head>


<!-- Основная таблица -->

<body>

<table>

    <!-- Шапка -->
    <tr>
        <td class="header_part" colspan="3">
            <h1 header_word="left_side">Автор: Балтабаев Дамир</h1>
            <h1 header_word="center_side">Группа: P3210</h1>
            <h1 header_word="right_side">Вариант: 10215</h1>
        </td>
    </tr>


    <tr>
        <!-- Рисунок -->

        <td subject_part="picture_part">
        <span class="picture_word">
          <h1>Рисунок</h1>
        </span>
            <div class="picture">
                <object class="picture-svg" type="image/svg+xml" data="pictures/picture-svg.svg">
                    <img src="pictures/picture-svg.svg" width="220" height="220">
                </object>
                <canvas id="main_canvas" width="220" height="220"></canvas>
            </div>
        </td>


        <!-- Значения -->
        <td subject_part="table_part">
        <span class="value_word">
          <h1>Значения</h1>
        </span>

            <form method="GET" action="controller">
                <table id="value_part">

                    <!-- X -->
                    <tr>
                        <td class="value_label">
                            <label>X:</label>
                        </td>

                        <td>
                <span class="center-labeled">
                  <label class="xbox-label">-3</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="-3">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">-2</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="-2">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">-1</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="-1">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">0</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="0">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">1</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="1">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">2</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="2">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">3</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="3">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">4</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="4">
                </span>
                            <span class="center-labeled">
                  <label class="xbox-label">5</label>
                  <input class="x-checkbox" type="checkbox" name="id_x" value="5">
                </span>
                            <div class="checkBoxError"></div>
                        </td>
                    </tr>

                    <!-- Y -->
                    <tr>
                        <td class="value_label">
                            <label>Y:</label>
                        </td>
                        <td>
                            <input type="text" name="id_y" class="dss" maxlength="10" autocomplete="off"
                                   placeholder="Числа (-5;3)">
                            <div class="textError"></div>
                        </td>
                    </tr>


                    <!-- R -->
                    <tr>
                        <td class="value_label">
                            <label>R:</label>
                        </td>

                        <td>
                <span class="center-labeled">
                  <select name="id_r" id="r_selected" onclick="displayRSvg()">
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                  </select>
                </span>
                            <div class="selectError"></div>
                        </td>
                    </tr>


                    <!-- Кнопка submit -->
                    <tr>
                        <td colspan="2">
                <span class="buttons">
                  <input name="submit_button" class="button" type="submit" value="Разместить">
                </span>
                        </td>

                    </tr>
                </table>
            </form>
        </td>
    </tr>

    <tr>
        <!-- Таблица -->
        <td subject_part="table_part">
        <span class="table_word">
          <h1>Таблица</h1>
        </span>
            <div>
                <table id="result-table">
                    <tr>
                        <th class='coords-col'>X</th>
                        <th class='coords-col'>Y</th>
                        <th class='coords-col'>R</th>
                        <th>Попадание</th>
                        <th>Время работы скрипта</th>
                        <th>Текущее время</th>
                    </tr>
                    <c:forEach var="result" items="${resultList}">
                        <tr>
                            <td>${result.x}</td>
                            <td>${result.y}</td>
                            <td>${result.r}</td>
                            <td>${result.hitValue}</td>
                            <td>${result.executeTime}</td>
                            <td>${result.currentTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </td>
    </tr>

</table>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/script.js"></script>
</body>

</html>