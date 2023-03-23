<template>
  <!-- Шапка -->
  <div class="menu_class ">
    <div class="menu_container">

      <a href="/" class="menu_logo">

        <svg class="menu__logo-icon" aria-hidden="true"
             viewBox="-35.84 -35.84 583.68 583.68">
          <path
            d="M307.4 20.97c-21.7-.15-43.9 3.68-64.9 9.72C296.7 39.1 344.7 55 364.3 80.71c-39.8-9.4-74.5 34.19-75.7 69.09 23.4-24.2 47.8-41.4 87.4-43.7 27.9 56.7 5.1 141.1 7.6 199.7.6 15.3 47.8 24.6 47.2 10.1-.2-51.5-4-145-25.8-208.1 38.8 7.3 74.1 33 74.1 33-1.1-23-26.9-48.99-58.6-53.59 7.7-9.6 27-24.9 71.1-26.71-23.4-18.4-59.9-17.7-88.3 2.31-25.1-30.36-59.8-41.61-95.9-41.84zm-153 35.84c-18.3.1-36.9 6.89-56.35 21.82-25.99-18.58-56.2-25.59-77.61-8.5 40.35 1.68 49.53 21.74 56.57 30.57-29 4.3-50.49 25.9-51.49 47.1 0 0 34.94-13.1 70.44-19.9-19.94 58.5-20.49 91.1-20.68 139 .57 11.5 41.52 17.8 42.22 1.5 2.3-54.5-13.6-79.4 2.4-134.1 11.5-5.9 52.2.5 73.6 23-1.1-32.3-19-64.41-55.4-55.7 9.4-18.03 26.8-21.13 78-20.99-20.7-15.28-41-23.87-61.7-23.8zm50.4 227.69c-22.3-.1-44.3 3.4-65.2 12.2-57.09 24.2-85.37 48.5-90.55 75.7-2.58 13.6 1.58 26.9 9.74 38.2 8.16 11.3 20.17 21.1 34.67 29.9 58.04 35.1 156.94 55.3 222.24 49.6 26.6-2.3 62.7-7 93.7-18.4 15.4-5.8 29.7-13.2 40.8-23.4 11-10.3 18.8-23.7 20-39.7v-.1c1-14.1-4.2-26.7-12.6-36.5-8.4-9.8-19.8-17.4-32.3-23.8-25.1-12.9-55.1-21.3-76.5-29.3-44-16.5-94.8-34.2-144-34.4zm11.2 18.7c12.3.8 24.9 2.6 37.4 5.2-70.8 23.9-127.7 59.7-171.65 101-4.55-4.4-8.4-8.9-11.35-14 39-36.4 87.3-68.4 145.6-92.2zm58.3 10.1c17.4 4.6 34.7 10.3 51.4 16.3-71.7 31.2-138.6 65.8-192.3 110.7-12.8-5.3-24-11.3-34.64-17.7 50.54-45 111.14-79.4 175.54-109.3z">
          </path>
          <text x="5" y="643" class="menu__logo-text_oasis">OASIS</text>
        </svg>


      </a>


      <div class="menu-main">
        <div class="part_of_main_menu">


          <a class="menu_link" href="/game_upload">
            Загрузить игру
          </a>


          <div class="dropdown">
            <a class="menu_link" style="margin-left: 50px">
              {{ login }}

            </a>
            <div class="dropdown-content">
              <a v-bind:href="'/profile/'+login">Профиль</a>
              <a @click="logOut" href="#">Выйти</a>
            </div>
          </div>

          <svg class="menu-ling__dropdown-icon" style="position: relative; margin-left: -68px; margin-top: 5px"
               aria-hidden="true" viewBox="0 0 24 24">
            <path fill="none" d="M0 0h24v24H0z"></path>
            <path d="M12 13.172l4.95-4.95 1.414 1.414L12 16 5.636 9.636 7.05 8.222z"></path>
          </svg>


        </div>

      </div>
    </div>
  </div>
  <!-- Конец шапки -->
</template>

<script>
import axios from "axios";

export default {
  name: "Header_After_Auth_Dev",

  data() {
    return {
      login: ''
    }
  },

  methods: {
    logOut() {
      axios.post('http://localhost:8081/oasis/user/logout_user',
        {login: this.login},
        {
          headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
        }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      });
      localStorage.clear()
      location.reload();
    }
  },

  created() {
    this.login = localStorage.getItem('login');
  }
}
</script>

<style scoped>
/*for header*/
.menu_class {
  background: repeat-x top center rgba(26, 26, 26, 0.9);
  position: fixed;
  box-sizing: border-box;
  padding: 0 2%;
  width: 100%;
  top: 0;
  left: 0;
  right: 0;
  height: 60px;
  -webkit-tap-highlight-color: transparent;
  box-shadow: 0px 1px 3px 1px rgb(0 0 0 / 30%);
  z-index: 100;
}

.menu_container {

  box-sizing: border-box;
  position: relative;
  height: 60px;
  padding-top: 4px;
  display: flex;
  margin: auto;
}

.menu_logo {
  display: flex;
  position: relative;
  float: left;
  height: 60px;
  margin-right: 29px;
}

.menu__logo-icon {
  width: 34px;
  height: 45px;
  fill: #ffffff;
}

.menu__logo-icon_2 {
  width: 100px;
  height: 100px;
  fill: #595959;
  margin-top: 10px;
  right: 0;
  left: 0;
  padding: 0;
}


.menu__logo-icon:hover {
  transition: 0.2s;
  fill: #cccccc;
}


.menu__logo-text_oasis {
  font-size: 176px;
  font-family: Poppins-Regular
}

.menu__logo-text_oasis_2 {
  font-size: 180px;
  font-family: Poppins-Regular
}

.menu-main {
  display: flex;
  flex-grow: 1;
}

.part_of_main_menu {
  display: flex;
  font-size: 14px;
  font-weight: 600;
  line-height: 52px;
}

.menu_link {
  display: block;
  position: relative;
  cursor: pointer;
  text-transform: uppercase;
  text-decoration: none;
  color: #ccc;
  letter-spacing: .25px;
  font-size: 14px;
  margin-top: 5px;
  font-weight: 500;

}

.menu-ling__dropdown-icon {
  position: absolute;
  left: 54px;
  top: 20px;
  height: 10px;
  width: 40px;
  fill: #ffffff;
  vertical-align: middle;
  transition: opacity .2s ease-in-out;
}


* {
  font-family: "Lato", sans-serif;
  font-weight: normal;
}


/* Контейнер <div> - необходим для размещения выпадающего содержимого */
.dropdown {
  position: relative;
  display: inline-block;

}

/* Выпадающее содержимое (скрыто по умолчанию) сообщество*/
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 230px;
  box-shadow: 0px 30px 16px 15px rgba(0, 0, 0, 0.2);
  z-index: 1;
  margin-left: 16%;

}

/* Выпадающее содержимое (скрыто по умолчанию) войти */
.dropdown-content_login {
  display: none;
  position: absolute;
  background: #ffffff;
  min-width: 600px;
  min-height: 400px;
  box-shadow: 0px 30px 16px 15px rgba(0, 0, 0, 0.2);
  z-index: 1;

}

.dropdown-buttons {
  box-sizing: border-box;
  width: 100%;
  display: flex;
  height: 72px;
  border-bottom: 1px solid #e6e6e6;
  margin-bottom: 10px;
  background: #f2f2f2;
  line-height: normal;
}

.dropdown_picture {
  position: absolute;
  margin-top: 17%;
  width: 100%;
  height: 30%;
  box-shadow: 0px 2px 4px 2px rgb(0 0 0 / 30%);
}

.dropdown_text {
  position: absolute;
  margin-top: 41%;
  width: 100%;
  height: 50%;
  padding: 0 3px;
  font-size: 14px;
  font-weight: 400;
  line-height: 24px;
  text-align: center;
}

.dropdown_picture_bottom {
  width: 100%;
  height: 100%;
}


/* Ссылки внутри выпадающего списка сообщества */
.dropdown-content a {
  color: black;
  padding: 0 16px;
  text-decoration: none;
  display: block;
  border-bottom: 1px solid #e6e6e6;
  box-shadow: 0px 1px 1px 1px rgb(0 0 0 / 2%);

}

/* Ссылки внутри выпадающего списка войти */
.dropdown-content_login_button2 {
  height: 38px;
  margin: 3%;
  margin-left: 95px;
  width: 225px;
  text-decoration: none;
  display: block;
  border-radius: 3px;
  border: none;
  background: linear-gradient(to bottom, #985fb2 0%, #713b8d 100%);
  box-shadow: 0px 0px 0px 1px rgb(0 0 0 / 15%), 0px 1px 3px 0px rgb(0 0 0 / 15%);
  line-height: 36px;
  color: #ffffff;
  font-weight: 600;
  font-size: 13px;
  cursor: pointer;
}

.dropdown-content_login_button1 {
  height: 38px;
  margin: 3%;
  width: 225px;
  text-decoration: none;
  display: block;
  border-radius: 3px;
  border: none;
  background: #e6e6e6;
  box-shadow: 0px 0px 0px 1px rgb(0 0 0 / 15%), 0px 1px 3px 0px rgb(0 0 0 / 15%);
  line-height: 36px;
  color: #4c4c4c;
  font-weight: 600;
  font-size: 13px;
  cursor: pointer;

}


/* Изменение цвета выпадающих ссылок при наведении курсора на сообщество*/
.dropdown-content a:hover {
  background-color: #cccccc;

}

/* Изменение цвета выпадающих ссылок при наведении курсора на войти*/
.dropdown-content_login button:hover {
  background-color: #cccccc;
  color: #333333;

}

/* Показать выпадающее меню при наведении курсора на сообщество*/
.dropdown:hover .dropdown-content {
  display: block;
}

/* Показать выпадающее меню при наведении курсора на войти*/
.dropdown:hover .dropdown-content_login {
  display: flex;

}


/* Изменение цвета фона кнопки раскрывающегося списка при отображении содержимого раскрывающегося списка */
.dropdown:hover .menu_link {
  color: #ffffff;
}

.menu_link:hover {
  color: #ffffff;

}


.registrationForm {
  text-align: center;
}

.form_title {

  font-size: 2.46154em;
  color: #595959;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 20px;
  font-weight: normal;
}

.form__fieldset {
  list-style: none;
  padding: 0;
  margin-top: 20px;
}

.form__fieldset_field {
  margin: 10px 0;
}


.form_field_input {
  box-shadow: inset 0 1px 3px rgb(0 0 0 / 10%);
  background: #ededed;
  font-family: inherit;
  color: #4c4c4c;
  border-radius: initial;
  border: #b1b1b1 solid 1px;
  width: 370px;
  min-height: 42px;
  text-indent: 10px;
  font-size: 1em;
  line-height: 20px;
}

.labelPolitics {
  font-size: 12px;
  line-height: 13px;
  color: rgba(0, 0, 0, 0.5);
  width: 100%;
}

.labelPolitics2 {
  font-size: .99308em;
  line-height: 1.25em;
  text-align: center;
  color: #595959;
}

.registerButton {
  background: linear-gradient(#9fbf00, #80ab00);
  background-color: #9fbf00;
  border-color: #7ca600;
  -moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.15);
  -webkit-box-shadow: 0 1px 3px rgb(0 0 0 / 15%);
  box-shadow: 0 1px 3px rgb(0 0 0 / 15%);
  color: #fff;
  font-weight: 500;
  font-size: 14px;
  letter-spacing: .5px;
  width: 370px;
  min-height: 42px;
  margin-top: 30px;
  transition: all 0.34s;
  cursor: pointer;
  border: none;
}


@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato/lato-regular-webfont.woff") format("woff");
}
</style>
