<template>
  <div>
    <img class="background-class" src="@/assets/images/bg-01.jpg"/>
    <table class="header_table">
      <!-- Шапка -->
      <tr>
        <td class="header_part" colspan="3">
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
          <img class="login-logo-params" src="@/assets/images/login-logo.png"/>
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
  .header_part {
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

/*планшет*/
@media (max-width: 1038px ) {
  .header_part {
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


/*телефон*/
@media (max-width: 802px ) {
  .header_part {
    color: white;
    background: -webkit-linear-gradient(top, #333333, dimgrey);
    width: 100%;
  }

  .header_table {
    margin-top: -15px;
    margin-bottom: 75px;
    min-width: 500px;
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
    min-width: 500px;
    min-height: 1500px;
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
