<template>
  <div>
    <img class="background-class" src="@/assets/images/bg-01.jpg"/>
    <table class = "header_table">
      <!-- Шапка -->
      <tr>
        <td class="header_part" colspan="3">
          <button class="left_side" @click="logOut">← Log out</button>
          <h1 class="center_side">Main Page</h1>
          <h1 class="right_side"></h1>
        </td>
      </tr>
    </table>

    <div class="picture_and_table">
      <div class="picture_module">
        <div class="name_of_module_class">
          <h1 style="text-align: center">Picture</h1>
        </div>
        <div style="position: relative;text-align: center;margin-top: 40px">
          <svg width="220" height="220" id="svg-graph" xmlns="http://www.w3.org/2000/svg">

            <!-- Line X -->
            <line class="axis" x1="10" y1="110" x2="210" y2="110" stroke="black"/>
            <polygon points="210,110 200,105 200,115"/>

            <!-- Line Y -->
            <line class="axis" x1="110" y1="10" x2="110" y2="210" stroke="black"/>
            <polygon points="110,10 105,20 115,20"/>

            <!-- X coordinates-->
            <line class="coordinate-line" x1="43" y1="105" x2="43" y2="115" stroke="black"/>
            <text class="minus-Rx-coordinate" x="33" y="100" font-size="14">-R</text>

            <line class="coordinate-line" x1="76" y1="105" x2="76" y2="115" stroke="black"/>
            <text class="minus-half-Rx-coordinate" x="66" y="100" font-size="14">-R/2</text>

            <line class="coordinate-line" x1="143" y1="105" x2="143" y2="115" stroke="black"/>
            <text class="half-Rx-coordinate" x="138" y="100" font-size="14">R/2</text>

            <line class="coordinate-line" id="id_r_svg" x1="176" y1="105" x2="176" y2="115" stroke="black"/>
            <text class="plus-Rx-coordinate" x="171" y="100" font-size="14">R</text>

            <!-- Y coordinates-->
            <line class="coordinate-line" x1="105" y1="176" x2="115" y2="176" stroke="black"/>
            <text class="minus-Ry-coordinate" x="120" y="181" font-size="14">-R</text>

            <line class="coordinate-line" x1="105" y1="143" x2="115" y2="143" stroke="black"/>
            <text class="minus-half-Ry-coordinate" x="120" y="148" font-size="14">-R/2</text>

            <line class="coordinate-line" x1="105" y1="76" x2="115" y2="76" stroke="black"/>
            <text class="plus-half-Ry-coordinate" x="120" y="81" font-size="14">R/2</text>

            <line class="coordinate-line" x1="105" y1="43" x2="115" y2="43" stroke="black"/>
            <text class="plus-Ry-coordinate" x="120" y="48" font-size="14">R</text>

            <!-- Triangle -->
            <polygon class="svg-figure triangle-figure" points="110,110 143,110 110,176"
                     fill="purple" fill-opacity="0.25" stroke="black" stroke-opacity="0.5"/>

            <!--Rectangle-->
            <polygon class="svg-figure rectangle-figure" points="43,110 110,110 110,176 43,176"
                     fill="black" fill-opacity="0.25" stroke="black" stroke-opacity="0.5"/>

            <!-- Circle -->
            <path class="svg-figure circle-figure" d="M 176 110 A 70 70 0 0 0 110 43 L 110 110 Z"
                  fill="red" fill-opacity="0.25" stroke="black" stroke-opacity="0.5"/>
          </svg>

          <canvas id="main_canvas" width="220" height="220" @click="validateFromGraph($event)"/>
        </div>
      </div>

      <div class="table_module" style="overflow-y: auto">
        <div class="name_of_module_class">
          <h1 style="text-align: center">Table</h1>
        </div>
        <table class="result-table" id="res-table">
          <thead>
          <tr style="background-color: darkgray">
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Hit</th>
            <th>Current time</th>
            <th>Execute time</th>
            <th>Username</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="point in allPoints">
            <td class="td-style-class">{{ point.x }}</td>
            <td class="td-style-class">{{ point.y }}</td>
            <td class="td-style-class">{{ point.r }}</td>
            <td class="td-style-class">{{ point.hitValue }}</td>
            <td class="td-style-class">{{ point.currentTime }}</td>
            <td class="td-style-class">{{ point.executeTime }}</td>
            <td class="td-style-class">{{ point.username }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="value_class">
      <div class="value_module">
        <div class="name_of_module_class">
          <h1 style="text-align: center">Values</h1>
        </div>
        <div class="x_values" style="text-align: center; margin-top: 10px">
          <label style="float: left">X:</label>
          <input type="radio" class="radio_class" v-model="x" value="-4" id="r1" name="r-radio">
          <label style="margin-right: 10.5px">-4</label>
          <input type="radio" class="radio_class" v-model="x" value="-3" id="r2" name="r-radio">
          <label style="margin-right: 10.5px">-3</label>
          <input type="radio" class="radio_class" v-model="x" value="-2" id="r3" name="r-radio">
          <label style="margin-right: 10.5px">-2</label>
          <input type="radio" class="radio_class" v-model="x" value="-1" id="r4" name="r-radio">
          <label style="margin-right: 10.5px">-1</label>
          <input type="radio" class="radio_class" v-model="x" value="0" id="r5" name="r-radio">
          <label style="margin-right: 10.5px">0</label>
          <input type="radio" class="radio_class" v-model="x" value="1" id="r6" name="r-radio">
          <label style="margin-right: 10.5px">1</label>
          <input type="radio" class="radio_class" v-model="x" value="2" id="r7" name="r-radio">
          <label style="margin-right: 10.5px">2</label>
          <input type="radio" class="radio_class" v-model="x" value="3" id="r8" name="r-radio">
          <label style="margin-right: 10.5px">3</label>
          <input type="radio" class="radio_class" v-model="x" value="4" id="r9" name="r-radio">
          <label style="margin-right: 10.5px">4</label>
        </div>
        <div class="y_values" style="text-align: center">
          <label style="float: left">Y:</label>
          <input style="font-family: Poppins-Regular; width: 80%" id="input-y" v-model.trim="y" name="y" type="text"
                 placeholder="Number(-3;3)" maxlength="10">
        </div>
        <div class="r_values" style="text-align: center; margin-top: 32px">
          <label style="float: left">R:</label>
          <input type="radio" class="radio_class" v-model="r" value="-4" id="r_1" name="r-radio2">
          <label style="margin-right: 10.5px">-4</label>
          <input type="radio" class="radio_class" v-model="r" value="-3" id="r_2" name="r-radio2">
          <label style="margin-right: 10.5px">-3</label>
          <input type="radio" class="radio_class" v-model="r" value="-2" id="r_3" name="r-radio2">
          <label style="margin-right: 10.5px">-2</label>
          <input type="radio" class="radio_class" v-model="r" value="-1" id="r_4" name="r-radio2">
          <label style="margin-right: 10.5px">-1</label>
          <input type="radio" class="radio_class" v-model="r" value="0" id="r_5" name="r-radio2">
          <label style="margin-right: 10.5px">0</label>
          <input type="radio" class="radio_class" v-model="r" value="1" id="r_6" name="r-radio2">
          <label style="margin-right: 10.5px">1</label>
          <input type="radio" class="radio_class" v-model="r" value="2" id="r_7" name="r-radio2">
          <label style="margin-right: 10.5px">2</label>
          <input type="radio" class="radio_class" v-model="r" value="3" id="r_8" name="r-radio2">
          <label style="margin-right: 10.5px">3</label>
          <input type="radio" class="radio_class" v-model="r" value="4" id="r_9" name="r-radio2">
          <label style="margin-right: 10.5px">4</label>
        </div>
        <div style="margin-top: 2vw">
          <button class="submit-button-style" @click="validateValuesFromForm" type="submit">Submit</button>
          <button class="reset-button-style" @click="deletePoints" type="submit">Reset</button>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "MainPage",
  data() {
    this.loadTable()
    return {
      x: "",
      y: "",
      r: "",
      errorMessage: "",
      allPoints: new Array(0),
      graphX: "",
      graphY: ""
    }
  },
  watch: {
    r() {
      let svgPic = document.getElementById('svg-graph');
      if (this.r !== "") {
        svgPic.querySelector('.minus-Rx-coordinate').textContent = (-this.r).toString();
        svgPic.querySelector('.minus-half-Rx-coordinate').textContent = (-this.r / 2).toString();
        svgPic.querySelector('.half-Rx-coordinate').textContent = (this.r / 2).toString();
        svgPic.querySelector('.plus-Rx-coordinate').textContent = (this.r).toString();
        svgPic.querySelector('.minus-Ry-coordinate').textContent = (-this.r).toString();
        svgPic.querySelector('.minus-half-Ry-coordinate').textContent = (-this.r / 2).toString();
        svgPic.querySelector('.plus-half-Ry-coordinate').textContent = (this.r / 2).toString();
        svgPic.querySelector('.plus-Ry-coordinate').textContent = (this.r).toString();
      } else {
        svgPic.querySelector('.minus-Rx-coordinate').textContent = ("-R");
        svgPic.querySelector('.minus-half-Rx-coordinate').textContent = ("-R/2");
        svgPic.querySelector('.half-Rx-coordinate').textContent = ("R/2");
        svgPic.querySelector('.plus-Rx-coordinate').textContent = ("R");
        svgPic.querySelector('.minus-Ry-coordinate').textContent = ("-R");
        svgPic.querySelector('.minus-half-Ry-coordinate').textContent = ("-R/2");
        svgPic.querySelector('.plus-half-Ry-coordinate').textContent = ("R/2");
        svgPic.querySelector('.plus-Ry-coordinate').textContent = ("R");
      }
      this.clearMyCanvas();
      this.drawAllPoints();
    }
  },
  methods: {
    logOut() {
      localStorage.clear()
      this.$router.push({name: "auth-page"});
    },
    validateValuesFromForm() {
      this.errorMessage = "";
      if (this.x === "") {
        this.errorMessage += ("Enter the X coordinate!\n")
      }
      if (this.y.trim() === '') {
        this.errorMessage += ("Enter the Y coordinate!\n")
      }
      if (this.y.trim() > 3 || this.y.trim() < -3) {
        this.errorMessage += ("Y coordinate must be between [-3;3]!\n")
      }
      if (isNaN(this.y)) {
        this.errorMessage += ("Y coordinate must be a number!\n")
      }
      if (this.r === "") {
        this.errorMessage += ("Enter the R coordinate!\n")
      }
      if (this.errorMessage !== "") {
        this.ErrorHandler(this.errorMessage);
      } else {
        this.addPoint()
      }
      this.errorMessage = "";
    },
    addPoint() {
      axios.post("http://localhost:8090/points/add/",
        {x: this.x, y: this.y, r: this.r},
        {
          headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
        }).then(() => {
        this.loadTable();
      }).catch(() => {
        this.ErrorHandler("Failed to add point")
      })
    },
    addPointFromGraph() {
      axios.post("http://localhost:8090/points/add/",
        {x: this.graphX, y: this.graphY, r: this.r},
        {
          headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
        }).then(() => {
        this.loadTable();
      }).catch(() => {
        this.ErrorHandler("Failed to add point")
      })
    },
    loadTable() {
      axios.get("http://localhost:8090/points/get/", {
        headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
      }).then(response => {
        this.allPoints = response.data;
        this.drawAllPoints();
      }).catch(() => {
        this.ErrorHandler("Failed to load points")
      })
    },
    drawAllPoints() {
      this.allPoints.forEach(point => {
        this.drawPoint((point.x / this.r * 67 + 110), -(point.y / this.r * 67 - 110), point.hitValue)
      })
    },
    drawPoint(x, y, hit) {
      let canvas = document.getElementById("main_canvas");
      let ctx = canvas.getContext("2d");
      ctx.beginPath();
      ctx.arc(x, y, 4, 0, Math.PI * 2);
      if (hit == true) ctx.fillStyle = 'green';
      else ctx.fillStyle = 'red';
      ctx.fill();
      ctx.closePath();
    },
    deletePoints() {
      axios.delete("http://localhost:8090/points/delete/", {
        headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
      })
      this.x = "";
      this.y = "";
      this.r = "";
      this.allPoints = new Array(0);
      this.graphX = "";
      this.graphY = "";
      this.clearMyCanvas();
    },
    validateFromGraph(event) {
      if (this.r !== "") {
        let mouseEvent = getMousePosition(document.getElementById("svg-graph"), event);
        this.graphX = (mouseEvent.x - 110) / 67 * this.r;
        this.graphY = (-mouseEvent.y + 110) / 67 * this.r;
        this.graphX = Math.round(this.graphX);
        if ((this.graphY > -3 && this.graphY < 3) && (this.graphX >= -4 && this.graphX <= 4)) {
          this.addPointFromGraph();
        }
      } else this.ErrorHandler("To send a point from the graph, you must select the R coordinate!")

      function getMousePosition(element, event) {
        let rect = element.getBoundingClientRect();
        return {
          x: event.clientX - rect.left,
          y: event.clientY - rect.top
        };
      }
    },
    clearMyCanvas() {
      let canvas = document.getElementById("main_canvas");
      canvas.getContext('2d').clearRect(0, 0, 220, 220);
    },
    ErrorHandler(errorText) {
      alert("Error: " + errorText);
    }
  }
}
</script>

<style scoped>

@media (min-width: 1038px ) {
  input::-webkit-input-placeholder {
    color: #333333;
    font-family: Poppins-Regular;
  }

  .header_table {
    margin-top: -15px;
    width: 100%;
    margin-bottom: 75px;
  }

  input::-webkit-input-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input::-moz-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:-moz-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:-ms-input-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:focus::-webkit-input-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus::-moz-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus:-moz-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus:-ms-input-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }


  label {
    font-family: Poppins-Medium;
  }

  /*for header*/
  .header_part {
    color: white;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .left_side {
    margin-top: 10px;
    margin-left: 6px;
    float: left;
    width: 12%;
    height: 30px;
    font-family: Poppins-Medium;
    font-size: 14px;
    color: white;
    margin-right: 46px;
    background: -webkit-linear-gradient(top, #b224ef, #7579ff);
    min-width: 120px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  #res-table {
  }

  .center_side {
    text-align: center;
    float: left;
    width: 69.33333%;
    height: 0px;
    text-align: center;
  }

  .right_side {
    float: right;
    width: 33.33333%;
    text-align: right;
  }

  h1 {
    font-family: Poppins-Medium;
    font-size: 18px;
    color: white;

  }

  /*main-body*/
  .background-class {
    min-width: 960px;
    min-height: 870px;
    position: absolute;
    display: block;
    z-index: -1;
    width: 100%;
    height: 150%;
    filter: grayscale(20);
    opacity: 0.12;
  }

  .picture_module {
    border: solid 1px black;
    width: 30%;
    height: 400px;
    background-color: white;
    float: left;
  }

  .value_module {
    border: solid 1px black;
    width: 30%;
    height: 350px;
    background-color: white;
    float: left;
  }

  .name_of_module_class {
    display: inline-block;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .picture-svg {

  }

  #main_canvas {
    position: absolute;
    left: 0;
    right: 0;
    margin: 0 auto;
  }

  @font-face {
    font-family: Poppins-Medium;
    src: url("../assets/font/poppins/Poppins-Medium.ttf");
  }

  @font-face {
    font-family: Poppins-Regular;
    src: url("../assets/font/poppins/Poppins-Regular.ttf");
  }

  .table_module {
    border: solid 1px black;
    width: 63%;
    height: 400px;
    background-color: white;
    float: right;
  }

  #svg-graph {

  }

  .result-table {
    border-collapse: collapse;
  }

  th {
    font-family: Poppins-Regular;
    width: 12.2vw;
  }

  .value_class {
    margin-top: 550px;
  }

  .radio_class {
    margin-right: 4px;
  }

  .y_values, .r_values {
    margin-top: 30px;
  }

  .submit-button-style {
    font-family: Poppins-Medium;
    margin-left: 2vw;
    font-size: 13px;
    float: left;
    color: #ffffff;
    background: -webkit-linear-gradient(top, darkgray, #333333);
    min-width: 100px;
    height: 35px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  .reset-button-style {
    font-family: Poppins-Medium;
    font-size: 13px;
    float: right;
    color: white;
    margin-right: 2vw;
    background: -webkit-linear-gradient(top, darkgray, #333333);
    min-width: 100px;
    height: 35px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  .td-style-class {
    border: 0.5px solid black;
    text-align: center;
    font-family: Poppins-Regular;
    width: 12.2vw;
  }

  text {
    font-family: Poppins-Medium;
  }
}


/*планшет*/
@media (max-width:  1038px ) {
  input::-webkit-input-placeholder {
    color: #333333;
    font-family: Poppins-Regular;
  }

  .header_table {
    margin-top: -15px;
    width: 100%;
    margin-bottom: 75px;
  }


  input::-webkit-input-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input::-moz-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:-moz-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:-ms-input-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:focus::-webkit-input-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus::-moz-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus:-moz-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus:-ms-input-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }


  label {
    font-family: Poppins-Medium;
  }

  /*for header*/
  .header_part {
    color: white;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .left_side {
    margin-top: 10px;
    margin-left: 6px;
    float: left;
    width: 12%;
    height: 30px;
    font-family: Poppins-Medium;
    font-size: 14px;
    color: white;
    margin-right: 46px;
    background: -webkit-linear-gradient(top, #b224ef, #7579ff);
    min-width: 120px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  #res-table {
  }

  .center_side {
    text-align: center;
    float: left;
    width: 69.33333%;
    height: 0px;
    text-align: center;
  }

  .right_side {
    float: right;
    width: 33.33333%;
    text-align: right;
  }

  h1 {
    font-family: Poppins-Medium;
    font-size: 18px;
    color: white;

  }

  /*main-body*/
  .background-class {

    position: absolute;
    display: block;
    z-index: -1;
    width: 100%;
    height: 150%;
    filter: grayscale(20);
    opacity: 0.12;
  }

  .picture_module {
    border: solid 1px black;
    width: 30%;
    height: 400px;
    background-color: white;
    float: left;
  }

  .value_module {
    border: solid 1px black;
    width: 30%;
    height: 350px;
    background-color: white;
    float: left;
  }

  .name_of_module_class {
    display: inline-block;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .picture-svg {

  }

  #main_canvas {
    position: absolute;
    left: 0;
    right: 0;
    margin: 0 auto;
  }

  @font-face {
    font-family: Poppins-Medium;
    src: url("../assets/font/poppins/Poppins-Medium.ttf");
  }

  @font-face {
    font-family: Poppins-Regular;
    src: url("../assets/font/poppins/Poppins-Regular.ttf");
  }

  .table_module {
    border: solid 1px black;
    width: 63%;
    height: 400px;
    background-color: white;
    float: right;
  }

  #svg-graph {

  }

  .result-table {
    border-collapse: collapse;
  }

  th {
    font-family: Poppins-Regular;
    width: 12.2vw;
  }

  .value_class {
    margin-top: 550px;
  }

  .radio_class {
    margin-right: 4px;
  }

  .y_values, .r_values {
    margin-top: 30px;
  }

  .submit-button-style {
    font-family: Poppins-Medium;
    margin-left: 2vw;
    font-size: 13px;
    float: left;
    color: #ffffff;
    background: -webkit-linear-gradient(top, darkgray, #333333);
    min-width: 100px;
    height: 35px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  .reset-button-style {
    font-family: Poppins-Medium;
    font-size: 13px;
    float: right;
    color: white;
    margin-right: 2vw;
    background: -webkit-linear-gradient(top, darkgray, #333333);
    min-width: 100px;
    height: 35px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  .td-style-class {
    border: 0.5px solid black;
    text-align: center;
    font-family: Poppins-Regular;
    width: 12.2vw;
  }

  text {
    font-family: Poppins-Medium;
  }
}



/*телефон*/
@media (max-width:  789px ) {
  input::-webkit-input-placeholder {
    color: #333333;
    font-family: Poppins-Regular;
  }

  .header_table {
    margin-top: -15px;
    height: 100px;
    width: 100%;
    margin-bottom: 75px;
  }


  input::-webkit-input-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input::-moz-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:-moz-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:-ms-input-placeholder {
    text-indent: 0px;
    transition: text-indent 0.3s ease;
  }

  input:focus::-webkit-input-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus::-moz-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus:-moz-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }

  input:focus:-ms-input-placeholder {
    text-indent: 500px;
    transition: text-indent 0.3s ease;
  }


  label {
    font-family: Poppins-Medium;
  }

  /*for header*/
  .header_part {
    color: white;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .left_side {
    margin-top: 10px;
    margin-left: 6px;
    float: left;
    width: 12%;
    height: 30px;
    font-family: Poppins-Medium;
    font-size: 14px;
    color: white;
    margin-right: 46px;
    background: -webkit-linear-gradient(top, #b224ef, #7579ff);
    min-width: 120px;

    position: relative;
    border: none;
  }

  #res-table {
  }

  .center_side {
    text-align: center;
    float: left;
    width: 48%;
    height: 0px;
    text-align: center;
  }

  .right_side {
    float: right;
    width: 33.33333%;
    text-align: right;
  }

  h1 {
    font-family: Poppins-Medium;
    font-size: 18px;
    color: white;

  }

  /*main-body*/
  .background-class {
    min-height: 1500px;
    position: absolute;
    display: block;
    z-index: -1;
    width: 100%;
    height: 150%;
    filter: grayscale(20);
    opacity: 0.12;
  }

  .picture_module {
    border: solid 1px black;
    width: 100%;
    height: 400px;
    background-color: white;
    float: left;
  }

  .value_module {
    border: solid 1px black;
    width: 100%;
    height: 350px;
    background-color: white;
    float: left;
  }

  .name_of_module_class {
    display: inline-block;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .picture-svg {

  }

  #main_canvas {
    position: absolute;
    left: 0;
    right: 0;
    margin: 0 auto;
  }

  @font-face {
    font-family: Poppins-Medium;
    src: url("../assets/font/poppins/Poppins-Medium.ttf");
  }

  @font-face {
    font-family: Poppins-Regular;
    src: url("../assets/font/poppins/Poppins-Regular.ttf");
  }

  .table_module {
    border: solid 1px black;
    width: 100%;
    height: 400px;
    background-color: white;
  }

  #svg-graph {

  }

  .result-table {
    border-collapse: collapse;
  }

  th {
    font-family: Poppins-Regular;
    width: 14vw;
  }

  .value_class {
    margin-top: 550px;
  }

  .radio_class {
    margin-right: 4px;
  }

  .y_values, .r_values {
    margin-top: 30px;
  }

  .submit-button-style {
    font-family: Poppins-Medium;
    margin-left: 2vw;
    font-size: 13px;
    float: left;
    color: #ffffff;
    background: -webkit-linear-gradient(top, darkgray, #333333);
    min-width: 80px;
    height: 35px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  .reset-button-style {
    font-family: Poppins-Medium;
    font-size: 13px;
    float: right;
    color: white;
    margin-right: 2vw;
    background: -webkit-linear-gradient(top, darkgray, #333333);
    min-width: 80px;
    height: 35px;
    border-radius: 25px;
    position: relative;
    border: none;
  }

  .td-style-class {
    border: 0.5px solid black;
    text-align: center;
    font-family: Poppins-Regular;
    width: 12.2vw;
  }

  text {
    font-family: Poppins-Medium;
  }
}

</style>
