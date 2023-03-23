<template>
  <body>
  <div class="background_wrapper">
    <img style="width: 100%; height: 100%"
         src="@/assets/images/WallpaperDog-20533971.jpg">
  </div>

  <div class="layout">
    <section class="layout_header">
      <main class="layout_header-column">
        <div class="user_status">
          <img class="user_status_avatar" src="@/assets/images/user.png">
          <div style="display: flex; margin-left: -2%">
            <div class="user_status_username">

              <!--            сделать проверку статуса и в зависимости от него грузить зеленую либо серую версию (v-if юзать)-->
              <svg v-if="status==='В сети'" aria-hidden="true" width="11.08" height="11.08"
                   viewBox="-1.6 -1.6 19.2 19.2"
                   fill="#00ff00">
                <circle cx="8" cy="8" r="8"></circle>
              </svg>

              <svg v-else aria-hidden="true" width="11.08" height="11.08" viewBox="-1.6 -1.6 19.2 19.2"
                   fill="#E6E6FA">
                <circle cx="8" cy="8" r="8"></circle>
              </svg>
              <label class="user_status_username_name">{{ login }}</label>
            </div>
          </div>
          <div class="user_status_status">{{ status }}</div>

        </div>
      </main>

      <aside class="layout_header-column" style="color: #ffffff;  margin-left: 60%;">
        <div class="for_icon_text">

          <svg class="ov-icon" aria-hidden="true" width="46.08" height="46.08" viewBox="0 0 512 512" fill="#985fb2"
               style="font-size: 2.88em;">
            <path
              d="M408 480H184a72 72 0 01-72-72V184a72 72 0 0172-72h224a72 72 0 0172 72v224a72 72 0 01-72 72z"></path>
            <path
              d="M160 80h235.88A72.12 72.12 0 00328 32H104a72 72 0 00-72 72v224a72.12 72.12 0 0048 67.88V160a80 80 0 0180-80z"></path>
          </svg>

          <label class="label_class_for_games">{{ game_count }}</label>
        </div>
        <label class="label_class_for_games" style="margin-left: -6%;font-size: 17px; color: #f3f3f3">Игр на
          аккаунте</label>
      </aside>
    </section>


    <section class="layout_nav">
      <div class="profile_navigation">
        <div class="profile_navigation_wrapper">
          <div class="profile_navigation_section">
            <label class="label_profile">ПРОФИЛЬ</label>
            <label class="label_update_info" style="margin-left: 770px">ИНФОРМАЦИЯ</label>
          </div>
        </div>
      </div>
    </section>

    <section class="layout_content">
      <main class="layout_subcontent">

        <div v-if="login===visitor_login">
          <div class="text_editor">
            <div class="text_editor_wrapper">
              <img class="avatar_editor" src="@/assets/images/user.png">
              <div class="post_editor">
                <textarea v-model="activity_to_submit" placeholder="Что нового?"
                          class="post_editor_textarea"></textarea>
              </div>
              <button class="submit_button" @click="submitActivity">ОПУБЛИКОВАТЬ</button>
            </div>
          </div>

          <div class="activities">
            <section class="activities_list">
              <div class="prof_activity" v-for="activity in activities">
                <header class="prof_activity_header">
                  <div class="avatar_activity">
                    <img class="avatar_editor" src="@/assets/images/user.png">
                    <div class="prof_activity_header_nick">
                      <label class="nickname_activity">{{ login }}</label>
                      <label class="just_text">опубликовал(а)&nbspзапись</label>
                      <label class="just_text">{{ activity.send_date }}</label>
                    </div>
                  </div>
                </header>

                <section class="prof_activity_content">
                  <div class="post_activity">
                    <p class="message">{{ activity.text }}</p>
                  </div>
                </section>

              </div>
            </section>
          </div>
        </div>

        <div v-else>
          <div class="activities">
            <section class="activities_list" style="margin: -30px">
              <div v-if="activities.length>0" class="prof_activity" v-for="activity in activities">
                <header class="prof_activity_header">
                  <div class="avatar_activity">
                    <img class="avatar_editor" src="@/assets/images/user.png">
                    <div class="prof_activity_header_nick">
                      <label class="nickname_activity">{{ login }}</label>
                      <label class="just_text">опубликовал(а)&nbspзапись</label>
                      <label class="just_text">{{ activity.send_date }}</label>
                    </div>
                  </div>
                </header>

                <section class="prof_activity_content">
                  <div class="post_activity">
                    <p class="message">{{ activity.text }}</p>
                  </div>
                </section>

              </div>

              <div v-else
                   style="font-family: Lato;font-weight: 700;color: #ffffff;margin-top: 5%; margin-left: 30%; font-size: 20px">
                Пока здесь нет активности...
                <div style="font-family: Lato;font-weight: 700;color: #cccccc;margin-top: 5%;margin-left: -20%; font-size: 16px">
                  Играйте, публикуйте записи и получайте хорошее настроение!
                </div>
              </div>
            </section>
          </div>
        </div>

      </main>

      <aside class="layout_subcontent_second">
        <div class="date_reg">
          <div class="user_bio">
            <svg aria-hidden="true" width="21" height="15" viewBox="-1.6 -1.6 19.2 19.2"
                 fill="#cccccc">
              <path
                d="M3.5 0a.5.5 0 01.5.5V1h8V.5a.5.5 0 011 0V1h1a2 2 0 012 2v11a2 2 0 01-2 2H2a2 2 0 01-2-2V3a2 2 0 012-2h1V.5a.5.5 0 01.5-.5zM1 4v10a1 1 0 001 1h12a1 1 0 001-1V4H1z"></path>
            </svg>
            Зарегистрирован {{ registration_date }}
          </div>
        </div>

        <div class="date_reg" style="margin-left: 20.5px">
          <div v-if="last_login_date!='' && status==='Не в сети'" class="user_bio">
            Последний вход {{ last_login_date }}
          </div>
        </div>

        <div class="layout_separator"></div>
        <header class="title">
          Последний запуск
        </header>
        <div v-if="lastGames.length===0" class="catalog"
             style="font-family: Lato;font-weight: 700;color: #cccccc;margin-top: 5%">
          {{ login }} еще не играл(а) ни в одну игру
        </div>
        <div v-else class="catalog">
          <div class="catalog_main_part" v-for="game in lastGames">
            <a class="catalog_item" v-bind:href="'/game/'+game.game_name">
              <div class="catalog_item_img">
                <img class="img_catalog_class" v-bind:src="game.shop_picture"/>
              </div>
              <div class="catalog_item_name">
                {{ game.game_name }}
                <div class="catalog_item_name_genre">
                  <div class="genre_class">{{ game.last_enter_date }}</div>
                </div>
              </div>
            </a>
          </div>
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
  name: "Profile",


  data() {
    return {
      status: '',
      registration_date: '',
      last_login_date: '',
      game_count: 0,
      lastGames: new Array(0),
      activity_to_submit: '',
      activities: new Array(0),
      visitor_login: ''
    }
  },
  props: {
    login: ''
  },
  created() {
    this.visitor_login = localStorage.getItem('login');

    axios.post("http://localhost:8081/oasis/user/check-user-on-exist", {
      login: this.login
    }).catch(() => {
      this.$router.push({name: "main_page"});
    })


    axios.post("http://localhost:8081/oasis/user/get_status_and_dates", {
      login: this.login
    }).then(response => {
      this.status = response.data.status;
      this.registration_date = response.data.registrationDate;
      this.last_login_date = response.data.lastLoginDate;

    }).catch(error => {
      // this.ErrorHandler(error.response.data.message)
    })

    axios.post("http://localhost:8081/oasis/library/get_all_games_count", {
      login: this.login
    }).then(response => {
      this.game_count = response.data

    }).catch(error => {
      // this.ErrorHandler(error.response.data.message)
    })

    axios.post("http://localhost:8081/oasis/library/get_last_games", {
      login: this.login
    }).then(response => {
      console.log(response)
      this.lastGames = response.data
    }).catch(error => {
      // this.ErrorHandler(error.response.data.message)
    })

    this.getAllActivities();


  },
  methods: {
    submitActivity() {
      axios.post('http://localhost:8081/oasis/user_activity/activity_submit', {
        text: this.activity_to_submit
      }, {
        headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
      }).then(response => {
        this.SuccessHandler(response.data.message)
        this.getAllActivities()
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      });
      this.activity_to_submit = ''
    },

    getAllActivities() {
      axios.post("http://localhost:8081/oasis/user_activity/get_all_activities", {
        login: this.login
      }).then(response => {
        this.activities = response.data
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },

    SuccessHandler(message) {
      VueSimpleAlert.fire({
        title: "Успешно!",
        text: message,
        type: "success",
        timer: 2000
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
}

</script>

<style scoped>

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

.layout {
  margin-top: 8%;
}

.layout_header {
  display: flex;
  justify-content: space-between;
  margin: 55px auto;
  padding-left: 3%;
  padding-right: 3%;
  max-width: calc(1060px + 6%);
}


.layout_header-column {
  width: 50%;
}

.user_status {
  position: relative;
  padding-left: 84px;
  height: 70px;
  line-height: 70px;

}

.user_status_avatar {
  position: absolute;
  top: 50%;
  left: 0;
  margin-top: -35px;
  width: 70px;
  height: 70px;
}

.user_status_username {

  vertical-align: middle;
  position: relative;
  padding-left: 5px;
  line-height: normal;
  margin-top: 5%
}

.user_status_username_name {
  font-family: Lato;
  margin-left: 5px;
  margin-bottom: 5px;
  font-size: 21px;
  font-weight: 600;
  color: #ffffff;
}

.user_status_status {
  margin-left: -0.8%;
  margin-top: 1%;
  font-size: 14px;
  color: hsla(0, 0%, 100%, .7);
  line-height: 17px;
  font-family: Lato-hairline;
  font-weight: 800;
}

.for_icon_text {
  filter: drop-shadow(0 0 1px rgba(0, 0, 0, 1));
  display: flex;
}

.label_class_for_games {
  margin-top: 5px;
  margin-left: 5px;
  font-family: Lato;
  font-weight: 700;
  font-size: 24px;
}

.layout_nav {
  margin: 0;
  width: 100%;

}

.profile_navigation {
  width: 100%;
  margin-left: -15px;
  border-bottom: 1px solid hsla(0, 0%, 100%, .5);
}

.profile_navigation_wrapper {
  display: flex;
  justify-content: space-between;
  position: relative;
  margin: auto;
  padding-left: 3%;
  padding-right: 3%;
  max-width: calc(1060px + 6%);
}

.profile_navigation_section {
  display: flex;
}

.label_profile {
  margin-left: 76px;
  margin-bottom: 5px;
  font-family: Lato;
  font-weight: 600;
  font-size: 16px;
  color: #ffffff;
  text-shadow: 0 0 0 hsl(0deg 0% 100% / 40%), 0 1px 3px rgb(0 0 0 / 20%);
}

.label_update_info {
  color: #ffffff;
  font-size: 16px;
  margin-left: 76px;
  margin-bottom: 5px;
  font-weight: 600;
  font-family: Lato;
  text-shadow: 0 0 0 hsl(0deg 0% 100% / 40%), 0 1px 3px rgb(0 0 0 / 20%);

}


@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato2/latolight.woff") format("woff");
}

@font-face {
  font-family: "Lato-hairline";
  src: local("Lato"), url("../assets/font/Lato2/latohairline.woff") format("woff");
}


body {
  width: 100%;
  overflow-y: auto;
}

.layout_content {
  margin: 40px auto 55px;
  padding-left: 3%;
  padding-right: 3%;
  max-width: calc(1060px + 6%);
}

.layout_subcontent {
  width: 51.88679%;
  display: inline-block;
  vertical-align: top;
}

.text_editor {
  position: relative;
  height: 64px;
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
  position: absolute;
  left: 12px;
  border-radius: 50%;
  width: 40px;
  height: 40px;
}

.post_editor {
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
  font-size: 14px;
  line-height: 21px;
  font-family: Lato;
  font-weight: 1000;
}

.activities_list {
  opacity: 1;
  margin: 120px 0;
  transition: opacity .5s ease-in-out;
}

.prof_activity {
  margin-top: 5%;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  justify-content: center;
  width: 100%;
  height: 100%;
  background: rgba(28, 28, 28, .8);
  box-shadow: 0 1px 7px 0 rgb(0 0 0 / 30%);
  transition: opacity .3s ease-in-out;
}

.prof_activity_header {
  z-index: 31;
  height: 40px;
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


.date_reg {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-pack: justify;
  justify-content: space-between;
  color: hsla(0, 0%, 80%, .5);
  font-size: 16px;
  line-height: 26px;
  margin-left: -5px;
}


.user_bio {
  position: relative;
  left: 0;
  font-family: Lato;
  font-weight: 700;
  color: #cccccc;
}

.layout_separator {
  display: block;
  height: 40px;
}

.title {
  justify-content: space-between;
  border-bottom: 1px solid hsla(0, 0%, 100%, .5);
  display: flex;
  padding: 16px 0;
  box-shadow: 0 1px 0 0 hsl(0deg 0% 100% / 40%);
  font-size: 16px;
  color: #fff;
  line-height: 12px;
  position: relative;
  font-family: Lato;
  font-weight: 700;
}


.catalog {
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
  text-decoration: none;
  width: 445px;
  height: 50px;
  border-bottom: 0.5px solid hsla(0, 0%, 100%, .3);
  opacity: 0.6;
  transition: 0.1s;


}

.catalog_item:hover {
  opacity: 1.5;
  cursor: pointer;
}

.catalog_item:hover .catalog_item_name_genre {
  color: rgba(255,26,98,0.69);
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
  color: #cccccc;
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
  color: #858585;
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
}


.submit_button {
  height: 38px;
  margin: 2%;
  margin-left: 345px;
  width: 175px;
  text-decoration: none;
  display: block;
  border-radius: 3px;
  border: none;
  background: linear-gradient(to bottom, #985fb2 0%, #713b8d 100%);
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


</style>
