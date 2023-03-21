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

        .table_word {
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


        .coords-col {
            width: 13%;
        }


        .center-labeled label {
            position: absolute;
            top: 13px;
            left: 5px;
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

        #return_button {
            height: 40px;
            width: 150px;
            color: seagreen;
        }

    </style>
</head>

<body>
<table>

    <!-- Шапка -->
    <tr>
        <td>
            <form method="GET" action="/webblab2-1.0-SNAPSHOT">
                <input class="button" type="submit" id="return_button" value="&#9664 Назад">
            </form>
        </td>
    </tr>
    <tr>
        <td class="header_part" colspan="3">
            <h1 header_word="left_side">Автор: Балтабаев Дамир</h1>
            <h1 header_word="center_side">Группа: P3210</h1>
            <h1 header_word="right_side">Вариант: 10215</h1>
        </td>
    </tr>

    <tr>
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
</body>
</html>
