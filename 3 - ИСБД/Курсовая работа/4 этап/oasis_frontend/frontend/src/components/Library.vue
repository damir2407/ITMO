<template>
  <body>
  <div class="background_wrapper">
    <img style="width: 100%; height: 100%"
         src="@/assets/images/WallpaperDog-20534059.jpg">
  </div>

  <div class="global_content">

    <div class="text_header">
      <h1 class="text_header_h1">Библиотека</h1>
    </div>

    <section class="container_class">



      <div class="catalog_filter_block">
        <input type="text" v-model="gameName" @input="filterTrigger" placeholder="Поиск игры"
               class="for_find_input">
      </div>

      <div class="catalog">

        <div v-if="allGames.length>0" class="catalog_main_part" v-for="game in allGames">
          <a class="catalog_item" v-bind:href="'/game/'+game.game_name">


            <div class="catalog_item_img">
              <img class="img_catalog_class" v-bind:src="game.picture_shop"/>
            </div>

            <div class="catalog_item_name">
              {{ game.game_name }}

              <div class="catalog_item_name_genre">

                <div v-if="game.last_run_date===''" class="genre_class">Вы еще не запускали эту игру</div>
                <div v-else class="genre_class">Последний запуск: {{ game.last_run_date }}</div>

              </div>
            </div>


            <div class="catalog_item_price">
              <a @click="enterGame(game.game_name)" class="btn btn1" v-bind:href="game.game_url">
                ИГРАТЬ
              </a>
            </div>

          </a>
        </div>

        <div
          style="font-family: Lato; font-weight: 200;font-size: 36px; margin: 100px; margin-left: 80px; color: #ffffff"
          v-else>Библиотека пуста.
        </div>

      </div>


    </section>

  </div>
  </body>
</template>

<script>
import axios from "axios";
import VueSimpleAlert from "vue-simple-alert";

export default {
  name: "Library",

  data() {
    return {
      allGames: new Array(0),
      gameName: '',
    }
  },
  methods: {
    filterTrigger() {
      axios.post("http://localhost:8081/oasis/library/get_by_game_name_library", {
          game_name: this.gameName,
        },
        {
          headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
        }).then(response => {
        this.allGames = response.data;
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },

    enterGame(game) {
      axios.post("http://localhost:8081/oasis/library/enter_game", {
          game_name: game
        },
        {
          headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
        })
    },


    ErrorHandler(message) {
      VueSimpleAlert.fire({
        title: "Ошибка!",
        text: message,
        type: "error",
        timer: 3000
      })
    }
  },
  created() {
    this.filterTrigger();
  },

}
</script>

<style scoped>


.global_content {
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  display: block;
  margin-top: -2.5%;
}

.text_header {
  position: relative;
  max-width: 1236px;
  margin: 0 auto;
  padding: 0 24px;
  border: 0;
  margin-top: 10%;
}

.text_header_h1 {
  font-size: 30px;
  padding-left: 0;
  margin-top: 0;
  font-family: Lato;
  font-weight: 900;
  color: #f3f3f3;
}


.container_class {

  max-width: 1236px;
  padding-left: 24px;
  padding-right: 24px;
  margin-right: auto;
  margin-left: auto;
  display: block;
}

.catalog {
  margin-top: auto;
  padding: 0 12px;
  float: left;
  position: relative;
}

.catalog_item {
  position: relative;
  padding: 19px 22px;
  -webkit-transition: background-color .3s ease, -webkit-box-shadow .3s ease;
  transition: background-color .3s ease, -webkit-box-shadow .3s ease;
  transition: box-shadow .3s ease, background-color .3s ease;
  transition: box-shadow .3s ease, background-color .3s ease, -webkit-box-shadow .3s ease;
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  background: rgba(28, 28, 28, .85);
  box-shadow: 0 1px 7px 0 rgb(0 0 0 / 30%);
  text-decoration: none;
  width: 860px;
}

.catalog_item:hover {
  cursor: pointer;
  box-shadow: 0px 1px 1px 1px rgb(0 0 0 / 20%);
  text-decoration: #f1f1f1;
  background: rgba(28, 28, 28, 1)
}




.catalog_main_part {

  margin-top: 1.4px;
}


.catalog_item_img {
  width: 107.73px;
  height: 72px;

}


.catalog_item_name {
  padding-left: 27px;
  padding-right: 15px;
  vertical-align: middle;
  color: rgb(0,228,212);
  -webkit-transition: all .3s ease;
  transition: all .3s ease;
  font-size: 16px;
  overflow: hidden;
  white-space: normal;
  max-width: 530px;
  text-overflow: ellipsis;
  font-family: Lato;
  font-weight: 700;
}

.catalog_item_name_genre {
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  color: #969696;
  font-size: 14px;
  margin-top: 5px;
  width: 900px;
}

.catalog_item_price {
  margin-right: -165px;
  transform: translate(-50%, -50%);
  -webkit-box-flex: 1;
  -ms-flex: 1 auto;
  flex: 1 auto;
  -webkit-box-pack: end;
  -ms-flex-pack: end;
  justify-content: flex-end;
  font-size: 19px;
  color: #1B1615;
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -ms-flex-negative: 0;
  flex-shrink: 0;
  white-space: nowrap;
  -webkit-transition: all .3s ease;
  transition: all .3s ease;
  margin-top: 40px;

}

.img_catalog_class {
  width: 107.73px;
  height: 72px;
}

.catalog_item_price_span {
  text-align: right;
  font-family: Montserrat;
  font-weight: 600;
  border: 1px solid;
}

.catalog_item_price_span:hover {
  color: #7ca600;
}


body {
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  right: 0;
  margin: 0;
  border: 0;
  overflow-y: auto;
}

div {
  display: block;
}

.menu__logo-icon {
  width: 28px;
  height: 28px;
  fill: #cccccc;
}

.main_aside {

  left: 75.66666667%;
  width: 33.33333333%;
  position: relative;
  display: block;
}

.catalog_filter_block {
  padding: 10px;
  width: 500px;
  margin-left: -28px;
}




.genre_class {
  font-weight: 200;
}

.slider_top {

  width: 100%;
  height: 114%;
  background: #1B1615;
}


.slider_content {
  margin-top: -1%;
  position: relative;
  height: 350px;
  width: 1000px;
  top: 15%;
  transform: translateX(-50%);
}

.slider_main_content {
  position: fixed;
  left: 58%;
  width: 100%;
  height: 1000px;

}


.catalog_filter_checklist {
  display: inline-block;
  margin-top: -4%;
  width: 100%;
  padding: 5%;
  font-family: Lato-hairline;
  font-weight: 700;
  height: 110px;

}

.picker_checkbox {
}

.picker_handle {
  cursor: pointer;
}

@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato2/latolight.woff") format("woff");
}

@font-face {
  font-family: "Lato-hairline";
  src: local("Lato"), url("../assets/font/Lato2/latohairline.woff") format("woff");
}


.for_find_input {
  overflow: hidden;
  font-family: Lato;
  font-weight: 1000;
  padding: 8px;
  border: 2px solid;
  border-color: #cdcdcd;
  color: #222;
  background-color: white;
  border-radius: 2px;
  width: 392px;
  margin: 6px 0;
  margin-left: 30px;
}


.btn {
  position: relative;
  display: block;
  color: #ffffff;
  font-size: 14px;
  font-family: Montserrat;
  text-decoration: none;
  border: 2px solid rgb(0,228,212);
  padding: 14px 60px;
  text-transform: uppercase;
  overflow: hidden;
  transition: 1s all ease;
}

.btn::before {

  background: rgb(1,108,103);
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: -1;
  transition: all 0.6s ease;
}

.btn1::before {
  width: 0%;
  height: 100%;
}

.btn1:hover::before {
  width: 100%;
}

.background_wrapper {
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: -1;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;

}


</style>
