<template>
  <body>
  <div class="background_wrapper">
    <img style="width: 100%; height: 100%"
         src="@/assets/images/WallpaperDog-20534121.jpg">
  </div>

  <div class="global_content">

    <div class="text_header">
      <h1 class="text_header_h1">Руководства</h1>
    </div>

    <section class="container_class">

      <div v-if="(this.visitorLogin === undefined || this.visitorLogin === null || this.visitorLogin === '')"
           style="font-family: Lato; font-weight: 200;font-size: 21px; color: #cccccc;margin-left: -90%;margin-top: 5%">
        <p>Для написания руководства необходимо</p>
        <p style="margin-left: 9%">зарегистрироваться</p>
      </div>

      <div v-else-if="allUserGames.length===0"
           style="font-family: Lato; font-weight: 200;font-size:21px;color: #cccccc;margin-left: -90%;margin-top: 5%">
        <p>Необходимо иметь купленные игры для</p>
        <p style="margin-left: 4%">написания руководства по ним</p>
      </div>


      <div v-else
           class="text_editor">
        <div class="text_editor_wrapper">
          <select v-model="selectedGameToSubmitGuide" class="select_css"
                  style="background-color: #242424;color: #cccccc;">
            <option value="" disabled selected>Игра</option>
            <option v-for="game in allUserGames">{{ game.name }}</option>
          </select>

          <img class="avatar_editor" src="@/assets/images/user.png">
          <div class="post_editor">
                <textarea v-model="guide_to_submit" placeholder="Поделитесь мнением об игре!"
                          class="post_editor_textarea"></textarea>
          </div>
          <button class="submit_button" @click="submitGuide">СОЗДАТЬ</button>
        </div>
      </div>


      <aside class="main_aside">

        <div class="catalog_filter_block">
          <select @change="getAllGamesBySelectedGame" class="select_css" v-model="selectedGameToShowGuides">
            <option value="" selected>Все игры</option>
            <option v-for="game in allGames">{{ game.name }}</option>

          </select>
          <!--        <input type="text" placeholder="Введите название игры"-->
          <!--               class="for_find_input">-->
        </div>


        <div class="activities">
          <section class="activities_list">
            <div class="prof_activity" v-for="guide in allGuides">
              <header class="prof_activity_header">
                <div class="avatar_activity">
                  <img class="avatar_editor" style="margin-top: -0.5%" src="@/assets/images/user.png">
                  <div class="prof_activity_header_nick">
                    <label class="nickname_activity">{{ guide.user_login }}</label>
                    <label class="just_text">опубликовал(а)&nbspруководство</label>
                    <label class="just_text">{{ guide.send_date }}</label>

                  </div>

                </div>
                <div class="item_desc_game_info">
                  <div class="item_desc_game_icon">
                    <img style="width: 32px; height: 32px; border-radius: 3px"
                         v-bind:src="guide.game_picture_url">
                  </div>
                  <div class="item_desc_game_name">{{ guide.game_name }}</div>
                </div>
              </header>

              <section class="prof_activity_content">
                <div class="post_activity">
                  <p class="message">{{ guide.guide_text }}</p>
                </div>
              </section>

            </div>
          </section>
        </div>

      </aside>


    </section>

  </div>
  </body>
</template>

<script>
import axios from "axios";
import VueSimpleAlert from "vue-simple-alert";

export default {
  name: "Guides",

  data() {
    return {
      guide_to_submit: '',
      selectedGameToSubmitGuide: '',
      allGames: new Array(0),
      allUserGames: new Array(0),
      visitorLogin: '',
      selectedGameToShowGuides: '',
      allGuides: new Array(0)
    }
  },

  methods: {
    submitGuide() {
      axios.post('http://localhost:8081/oasis/guide/add_guide', {
        game_name: this.selectedGameToSubmitGuide,
        guide_text: this.guide_to_submit

      }, {
        headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
      }).then(response => {
        this.SuccessHandler("Руководство успешно опубликовано!")
        this.getAllGamesBySelectedGame()
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      });
      this.guide_to_submit = ''
    },

    getAllGames() {
      axios.get("http://localhost:8081/oasis/game/get_all_games", {}).then(response => {
        console.log(response)
        this.allGames = response.data;
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },

    getAllGamesByLogin() {
      axios.get("http://localhost:8081/oasis/game/get_all_games_by_login", {
        headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
      }).then(response => {
        console.log(response);
        this.allUserGames = response.data
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },

    getAllGamesBySelectedGame() {
      axios.post("http://localhost:8081/oasis/guide/get_guides_by_selected_game", {
        selected_game_to_show: this.selectedGameToShowGuides
      }).then(response => {
        this.allGuides = response.data
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },

    ErrorHandler(message) {
      VueSimpleAlert.fire({
        title: "Ошибка!",
        text: message,
        type: "error",
        timer: 3000
      })
    },
    SuccessHandler(message) {
      VueSimpleAlert.fire({
        title: "Успешно!",
        text: message,
        type: "success",
        timer: 3000
      })

    }
  },

  created() {
    this.visitorLogin = localStorage.getItem('login');
    if (!(this.visitorLogin === undefined || this.visitorLogin === null || this.visitorLogin === '')) {
      this.getAllGamesByLogin();
    }
    this.getAllGames();
    this.getAllGamesBySelectedGame();

  }
}
</script>

<style scoped>


.text_editor {
  position: relative;
  height: 64px;
  right: 70%;
}

.text_editor_wrapper {
  position: absolute;
  z-index: 50;
  top: 0;
  left: 0;
  right: 0;
  padding: 12px 12px 12px 64px;
  background: rgba(28, 28, 28, .8);
  box-shadow: 0 1px 7px 0 rgb(0 0 0 / 30%);
  transition: height .15s ease-in-out;
}

.avatar_editor {
  margin-top: 3%;
  position: absolute;
  left: 12px;
  border-radius: 50%;
  width: 40px;
  height: 40px;
}

.post_editor {
  margin-top: 3%;
  position: relative;
  border: 1px solid hsla(0, 0%, 100%, .05);
  padding: 8px 10px;
  min-height: 40px;
  background: #242424;
  font-size: 14px;
  font-weight: 300;
  line-height: 21px;
  color: hsla(0, 0%, 100%, .4);
}

.post_editor_textarea {
  display: inline-block;
  overflow: hidden;
  border: none;
  padding: 0;
  width: 100%;
  max-height: 150px;
  resize: none;
  background: transparent;
  color: #ccc;
  height: 200px;
  font-size: 14px;
  line-height: 21px;
  font-family: Lato;
  font-weight: 1000;
}


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

  max-width: 500px;
  padding-left: 24px;
  padding-right: 24px;
  margin-right: auto;
  margin-left: auto;
  display: block;
}

.catalog {
  margin-top: -89px;
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
  background: rgba(28, 28, 28, .8);
  box-shadow: 0 1px 7px 0 rgb(0 0 0 / 30%);
  text-decoration: none;
  width: 650px;
}

.catalog_item:hover {
  cursor: pointer;
  box-shadow: 0px 1px 1px 1px rgb(0 0 0 / 20%);
  text-decoration: #f1f1f1;
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
  color: rgb(0, 228, 212);
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
  position: fixed;
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
  margin-top: -65px;
  left: 45.66666667%;
  width: 100%;
  position: relative;
  display: block;
}

.catalog_filter_block {
  padding: 10px;
  background: rgba(28, 28, 28, .8);
  box-shadow: 0 1px 7px 0 rgb(0 0 0 / 30%);
  width: 700px;
  height: 50px;
}

.catalog_filter_block_head {
  color: #ffffff;
  text-align: center;
  font-size: 17px;
  line-height: 20px;
  font-family: Lato;
  font-weight: 700;
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
  width: 300px;
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
  border: 2px solid rgb(0, 228, 212);
  padding: 14px 60px;
  text-transform: uppercase;
  overflow: hidden;
  transition: 1s all ease;
}

.btn::before {

  background: rgb(1, 108, 103);
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
  overflow-y: auto;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}

.submit_button {
  height: 38px;
  margin: 2%;
  margin-left: 250px;
  width: 175px;
  text-decoration: none;
  display: block;
  border-radius: 3px;
  border: none;
  background: linear-gradient(to bottom, rgb(230, 46, 93) 0%, rgb(124, 23, 47) 100%);
  box-shadow: 0px 0px 0px 1px rgb(0 0 0 / 15%), 0px 1px 3px 0px rgb(0 0 0 / 15%);
  line-height: 36px;
  color: #ffffff;
  font-weight: 100;
  font-size: 13px;
  cursor: pointer;
  transition: 0.2s;
  font-family: Lato;
  font-weight: 1000;
}


.submit_button:hover {
  background-color: #cccccc;
  color: #333333;
}


.activities_list {
  opacity: 1;
  margin: 0;
  transition: opacity .5s ease-in-out;
}

.prof_activity {
  margin-top: 5%;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  justify-content: center;
  width: 720px;
  height: 100%;
  background: rgba(28, 28, 28, .8);
  box-shadow: 0 1px 7px 0 rgb(0 0 0 / 30%);
  transition: opacity .3s ease-in-out;
}

.prof_activity_header {
  z-index: 31;
  height: 80px;
  box-shadow: 0 1px 0 0 hsl(0deg 0% 100% / 5%);
  align-items: center;
  position: relative;
  padding: 12px;
  width: 96%;
  flex-direction: row;
  display: flex;
}


::placeholder {
  font-family: Lato;
  font-weight: 1000;
}

.avatar_activity {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
}

.prof_activity_header_nick {
  margin-left: 0%;
  font-size: 14px;
  line-height: 18px;
  color: hsla(0, 0%, 100%, .4);
  width: 450px;
}

.nickname_activity {
  font-family: Lato;
  font-size: 15px;
  font-weight: 600;
  color: #ffffff;
  margin-left: 12%;
}

.just_text {
  font-family: Lato;
  font-size: 13px;
  font-weight: 100;
  margin-left: 4%;
  color: #cccccc;
}

.prof_activity_content {
  flex: 1;
  position: relative;
  max-height: calc(100% - 64px - 40px);
  word-wrap: break-word;
}

.post_activity {
  align-items: stretch;
  width: 100%;
  height: 100%;
  flex-direction: column;
  display: flex;
}

.message {
  padding: 15px 12px;
  font-size: 18px;
  line-height: 24px;
  word-break: break-word;
  font-family: Lato;
  color: #ffffff;
}

.layout_subcontent_second {
  margin-left: 5.66038%;
  display: inline-block;
  vertical-align: top;
  width: 42.45283%;

}

.select_css {
  margin-top: 1%;
  display: block;
  font-size: 16px;
  font-family: Lato;
  font-weight: 700;
  color: #444;
  height: 40px;
  line-height: 1.3;
  padding: .6em 1.4em .5em .8em;
  width: 100%;
  max-width: 100%;
  box-sizing: border-box;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, .04);
  border-radius: .2em;
  background-color: #fff;
  background-repeat: no-repeat, repeat;
  background-position: right .7em top 50%, 0 0;
  background-size: .65em auto, 100%;
  cursor: pointer;
}

.select-css::-ms-expand {
  display: none;
}

.select-css:hover {
  border-color: #888;
}

.select-css:focus {
  border-color: #aaa;
  box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
  box-shadow: 0 0 0 3px -moz-mac-focusring;
  color: #222;
  outline: none;
}

.select-css option {
  font-weight: normal;
}


*[dir="rtl"] .select-css, :root:lang(ar) .select-css, :root:lang(iw) .select-css {
  background-position: left .7em top 50%, 0 0;
  padding: .6em .8em .5em 1.4em;
}

.item_desc_game_info {
  font-family: Lato;
  font-weight: 1000;
  position: relative;
  padding-left: 36px;
  color: #767676;
  min-height: 32px;
  font-size: 14px;
  padding-right: 8px;
  margin-top: 9%;
  margin-left: -395px;
}

.item_desc_game_icon {
  position: absolute;
  left: 0;
  top: 0;
}

.item_desc_game_name {
  color: rgb(230, 46, 93);
  margin-top: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

option {
  cursor: pointer;
}

</style>
