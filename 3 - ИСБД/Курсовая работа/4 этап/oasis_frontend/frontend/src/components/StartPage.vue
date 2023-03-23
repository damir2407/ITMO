<template>
  <div>
    <img class="background-class"/>
    <table class="header_table">
      <!-- Шапка -->
      <tr>
        <td class="menu_class" colspan="3">
          <h1 class="left_side">&nbsp&nbspP3210 Baltabaev Damir</h1>
          <h1 class="center_side">Login Page</h1>
          <h1 class="right_side">109987&nbsp&nbsp&nbsp&nbsp&nbsp</h1>
        </td>
      </tr>
    </table>

    <div class="login-class">
      <form id="form" @submit.prevent="logIn">
        <div class="login-form">

        <span class="login-logo">
<!--          <img class="login-logo-params" src="@/assets/images/oasis_logo.png"/>-->
          <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 512 512" height="1em" width="1em"
               xmlns="http://www.w3.org/2000/svg">
            <path d="M179.125 20.625c-28.052.12-54.046 5.813-66.72 9.78 0 0 114.968 19.51 124.532 98.876C149.573 3.32 54.28 155.657 54.28 155.657c19.868-5.212 76.76-20.682 114.75-14.156 25.992 4.465 51.33 28.03 50.236 27.733-61.943 15.24-160.35 290.92-143.64 313.308 14.9 17.12 29.816 11.28 44.718 2.595 7.376-58.425 64.938-314.765 135.375-294.072.01.003.02-.003.03 0 5.93 2.03 11.54 5.59 11.844 11.03.58 10.363-6.11 27.3-4.53 39.063 3.662 27.296 9.007 36.79 16.78 46.313 18.564-10.435 36.326-48.057 40-67.564 16.634 7.284 43.373 24.155 65.187 86.813 11.404-58.716-5.042-105.03-59.03-125.595 23.38-10.105 125.142 41.03 137.563 69.53C475.648 199.264 390.167 136.378 319 139.72c13.644-3.56 28.638.6 42.906-9.907 19.146-14.098 41.474-26.24 62.28-39.282-69.972-30.435-134.545-15.407-139.092 16.095-3.573-69.916-57.83-86.204-105.97-86z"></path>
          </svg>


        </span>

          <span class="login-title">Log in</span>

          <div class="input-module">
            <img src="https://img.icons8.com/office/50/000000/user.png"
                 style="width: 8%;margin-bottom: -36px"/>
            <input class="input-class" v-model.trim="username" type="text" placeholder="Username" maxlength="15">
          </div>

          <div class="input-module">
            <img
              src="https://img.icons8.com/external-bearicons-outline-color-bearicons/50/000000/external-lock-call-to-action-bearicons-outline-color-bearicons.png"
              style="width: 8%;margin-bottom: -36px"/>
            <input class="input-class" v-model.trim="password" type="password" placeholder="Password" maxlength="15">
          </div>

          <div class="authorizes-button-module">
            <button class="login-button-class" @click="logIn">Sign in</button>
            <button class="register-button-class" @click="register">Sign up</button>
          </div>
        </div>
      </form>
    </div>
    <span class="date_time">{{ localeDate }}</span>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "StartPage",
  data() {
    return {
      username: "",
      password: "",
      date: 1580558031264,
    }
  },
  computed: {
    localeDate() {
      return (new Date(this.date)).toLocaleDateString() + " " + (new Date(this.date)).toLocaleTimeString()
    },
  },
  created() {
    setInterval(() => this.date = Date.now());
  },
  methods: {
    logIn(e) {
      e.preventDefault()
      axios.post('http://localhost:8090/user/auth/', {
        username: this.username,
        password: this.password
      }).then(response => {
        localStorage.setItem("jwt", response.data);
        this.$router.push({path: '/main'});
      }).catch(error => {
        this.AxiosErrorHandler(error.response.data)
      });
    },
    register(e) {
      e.preventDefault();
      axios.post('http://localhost:8090/user/register/', {
        username: this.username,
        password: this.password
      }).then(response => {
        alert(response.data)
      }).catch(error => {
        this.AxiosErrorHandler(error.response.data);
      });
    },

    AxiosErrorHandler(errorText) {
      alert(errorText)
    }
  }
}
</script>

<style scoped>


/*for header desktop*/
@media (min-width: 1038px) {
  .menu_class {
    color: white;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .header_table {
    margin-top: -15px;
    width: 100%;
    margin-bottom: 75px;
  }

  .left_side {
    float: left;
    width: 33.33333%;
    text-align: left;
  }

  .center_side {
    float: left;
    width: 33.33333%;
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
  }


  /*main-body*/
  .background-class {
    position: absolute;
    min-width: 960px;
    min-height: 870px;
    display: block;
    z-index: -1;
    width: 100%;
    height: 150%;
    filter: grayscale(20);
    opacity: 0.12;
  }

  .login-class {
    margin-left: auto;
    margin-right: auto;
    margin-top: 20px;
    width: 425px;
    height: 500px;
    border-radius: 10px;
    overflow: hidden;
    padding: 50px 65px 125px 5px;
    background: #9152f8;
    background: -webkit-linear-gradient(top, #7579ff, #b224ef);
  }

  .login-form {
    width: 100%;
    position: relative;
  }

  .login-logo {
    font-size: 60px;
    color: #333333;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 120px;
    height: 120px;
    border-radius: 50%;
    background-color: #fff;
    margin-left: 184px;
  }

  .login-logo-params {
    width: 100%;
  }

  @font-face {
    font-family: Poppins-Medium;
    src: url("../assets/font/poppins/Poppins-Medium.ttf");
  }

  @font-face {
    font-family: Poppins-Regular;
    src: url("../assets/font/poppins/Poppins-Regular.ttf");
  }

  .login-title {
    margin-top: 25px;
    margin-left: 60px;
    font-family: Poppins-Medium;
    font-size: 30px;
    color: #fff;
    line-height: 1.2;
    text-align: center;
    text-transform: uppercase;
    display: block;
    margin-bottom: 50px;
  }

  .date_time {
    margin-top: -30px;
    font-family: Poppins-Regular;
    letter-spacing: 1px;
    font-size: 16px;
    color: black;
    line-height: 1.2;
    text-align: center;
    display: block;
    margin-bottom: 30px;
  }

  .input-module {
    border-bottom: 2px solid rgba(255, 255, 255, 0.24);
    margin-left: 45px;
    margin-bottom: 30px;
  }

  input::-webkit-input-placeholder {
    color: white;
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

  .input-class {
    font-family: Poppins-Regular;
    font-size: 16px;
    color: #ffffff;
    line-height: 1.2;
    display: block;
    width: 100%;
    height: 45px;
    background: transparent;
    padding: 0 5px 0 38px;
    outline: none;
    border: none;
  }

  .authorizes-button-module {
    width: 100%;
    margin-top: 75px;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }

  .login-button-class {
    font-family: Poppins-Medium;
    margin-left: 62px;
    font-size: 16px;
    float: left;
    color: #ffffff;
    line-height: 1.2;
    justify-content: center;
    align-items: center;
    background: -webkit-linear-gradient(top, #b224ef, #333333);
    padding: 0 20px;
    min-width: 120px;
    height: 50px;
    border-radius: 25px;
    position: relative;
    border: none;
    transition: all 0.4s;
    z-index: 1;
  }

  .login-button-class:hover {
    cursor: pointer;
    color: black;
  }


  .register-button-class {
    font-family: Poppins-Medium;
    font-size: 16px;
    float: right;
    margin-left: 86px;
    color: black;
    line-height: 1.2;
    justify-content: center;
    align-items: center;
    padding: 0 20px;
    background: -webkit-linear-gradient(top, #ffffff, #9152f8);
    min-width: 120px;
    height: 50px;
    border-radius: 25px;
    position: relative;
    border: none;
    transition: all 0.4s;
    z-index: 1;
  }

  .register-button-class:hover {
    cursor: pointer;
    color: white;
  }


  button:active {
    border: solid 1px black;
  }
}


</style>
