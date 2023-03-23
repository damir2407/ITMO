<template>
  <body>
  <div class="background_wrapper">
    <img style="width: 100%; height: 100%"
         src="@/assets/images/WallpaperDog-20533971.jpg">
  </div>

  <div class="layout">


    <section class="layout_nav">
      <div class="profile_navigation">
        <div class="profile_navigation_wrapper">
          <div class="profile_navigation_section">
            <label class="label_profile">ВАШ ТЕКУЩИЙ БАЛАНС</label>
          </div>
        </div>
      </div>
    </section>

    <section class="layout_content">
      <main class="layout_subcontent">
        <div style="float: left;
    width: 30.18868%;">
          <div class="wallet-funds-box">
            <div class="wallet-funds-box_amount">
            <span class="currency">
              <span>{{ balance }}</span>
            </span>
            </div>
          </div>
        </div>

        <div style="float: right;position: relative;width: 66.03774%;margin-left: 10%">
          <div class="wallet-amount-selector">
            <div class="wallet_amount_top">
              <div @click="replenishBalance(10)" class="wallet_amount">
                <div class="wallet_amount_value">
                  10
                  <sup class="wallet_amount_tip">00</sup>
                </div>
                <span @click="replenishBalance(10)" class="wallet_amount_buy">
                  <svg aria-hidden="true" width="27" height="27" viewBox="-1.6 -5 21 21"
                       fill="#222F3D" style="margin-left: 20%"><path
                    d="M16 8A8 8 0 110 8a8 8 0 0116 0zM8.5 4.5a.5.5 0 00-1 0v3h-3a.5.5 0 000 1h3v3a.5.5 0 001 0v-3h3a.5.5 0 000-1h-3v-3z"></path></svg>
                  <p class="buy_text"> КУПИТЬ</p>
                </span>
              </div>

              <div @click="replenishBalance(25)"  class="wallet_amount">
                <div class="wallet_amount_value">
                  25
                  <sup class="wallet_amount_tip">00</sup>
                </div>
                <span @click="replenishBalance(25)" class="wallet_amount_buy">
                  <svg aria-hidden="true" width="27" height="27" viewBox="-1.6 -5 21 21"
                       fill="#222F3D" style="margin-left: 20%"><path
                    d="M16 8A8 8 0 110 8a8 8 0 0116 0zM8.5 4.5a.5.5 0 00-1 0v3h-3a.5.5 0 000 1h3v3a.5.5 0 001 0v-3h3a.5.5 0 000-1h-3v-3z"></path></svg>
                  <p class="buy_text"> КУПИТЬ</p>
                </span>
              </div>

              <div @click="replenishBalance(60)" class="wallet_amount">
                <div class="wallet_amount_value">
                  60
                  <sup class="wallet_amount_tip">00</sup>
                </div>
                <span @click="replenishBalance(60)" class="wallet_amount_buy">
                  <svg aria-hidden="true" width="27" height="27" viewBox="-1.6 -5 21 21"
                       fill="#222F3D" style="margin-left: 20%"><path
                    d="M16 8A8 8 0 110 8a8 8 0 0116 0zM8.5 4.5a.5.5 0 00-1 0v3h-3a.5.5 0 000 1h3v3a.5.5 0 001 0v-3h3a.5.5 0 000-1h-3v-3z"></path></svg>
                  <p class="buy_text"> КУПИТЬ</p>
                </span>
              </div>

              <div @click="replenishBalance(100)" class="wallet_amount">
                <div class="wallet_amount_value">
                  100
                  <sup class="wallet_amount_tip">00</sup>
                </div>
                <span @click="replenishBalance(100)" class="wallet_amount_buy">
                  <svg aria-hidden="true" width="27" height="27" viewBox="-1.6 -5 21 21"
                       fill="#222F3D" style="margin-left: 20%"><path
                    d="M16 8A8 8 0 110 8a8 8 0 0116 0zM8.5 4.5a.5.5 0 00-1 0v3h-3a.5.5 0 000 1h3v3a.5.5 0 001 0v-3h3a.5.5 0 000-1h-3v-3z"></path></svg>
                  <p class="buy_text"> КУПИТЬ</p>
                </span>
              </div>


              <div  class="wallet_amount">
                <div class="wallet_amount_value">
                  <input type="text" class="input_buy" placeholder="сколько" v-model="balance_to_add_input">
                  $
                </div>
                <span @click="replenishBalance(this.balance_to_add_input)" class="wallet_amount_buy">
                  <svg aria-hidden="true" width="27" height="27" viewBox="-1.6 -5 21 21"
                       fill="#222F3D" style="margin-left: 20%"><path
                    d="M16 8A8 8 0 110 8a8 8 0 0116 0zM8.5 4.5a.5.5 0 00-1 0v3h-3a.5.5 0 000 1h3v3a.5.5 0 001 0v-3h3a.5.5 0 000-1h-3v-3z"></path></svg>
                  <p class="buy_text"> КУПИТЬ</p>
                </span>
              </div>

            </div>
          </div>


        </div>


      </main>
    </section>


  </div>
  </body>
</template>

<script>
import axios from "axios";
import VueSimpleAlert from "vue-simple-alert";

export default {
  name: "Wallet",

  data() {
    return {
      balance_to_add_input: '',
      balance: 0,
      login: localStorage.getItem('login')
    }
  },
  methods: {
    replenishBalance(value) {
      VueSimpleAlert.prompt("Введите номер карты", "", "Пополнение баланса").then(() => {
        VueSimpleAlert.prompt("Введите срок действия карты", "", "Пополнение баланса").then(() => {
          VueSimpleAlert.prompt("Введите CVV-код", "", "Пополнение баланса").then(() => {

            axios.post('http://localhost:8081/oasis/user/add_money', {
              balance: value
            }, {
              headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
            }).then(response => {
              location.reload();
            }).catch(error => {
              this.ErrorHandler(error.response.data.violations[0].message)
            });
          })
        })
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
    axios.get("http://localhost:8081/oasis/user/get_balance", {
      headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
    }).then(response => {
      console.log(response)
      this.balance = response.data.balance.toFixed(2)
    }).catch(error => {
      this.ErrorHandler(error.response.data.violations[0].message)
    })
  }


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
  margin-top: 15%;
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
  position: fixed;
}

.layout_content {
  margin: 40px auto 55px;
  padding-left: 3%;
  padding-right: 3%;
  max-width: calc(1060px + 6%);
}

.layout_subcontent {
  width: 100%;
  display: flex;
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
  margin: 60px 0;
  transition: opacity .5s ease-in-out;
}

.prof_activity {
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
  width: 100%;
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
}

.nickname_activity {
  font-family: Lato;
  font-size: 12px;
  font-weight: 600;
  color: #ffffff;
  margin-left: 30%;
}

.just_text {
  font-family: Lato;
  font-size: 10px;
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
  font-size: 14px;
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
  color: #595959;
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

.wallet-funds-box {
  width: 100%;
  height: 133px;
  border-top: 1px solid #b3b3b3;
  border-bottom: 1px solid #b3b3b3;
  text-align: center;
}

.wallet-funds-box_amount {
  height: 85px;
  padding-top: 15px;
  color: #f3f3f3;
  font-size: 70px;
  line-height: 70px;
}

.currency {
  font-family: Lato;
  font-weight: 1000;
  text-transform: none;
}

.wallet-funds-box__tip {
  margin-left: 5px;
  border-bottom: 3px solid #404040;
  vertical-align: top;
  line-height: 65px;
  font-size: 25px;
}

.currency:after {
  content: "\202F$";
}

.wallet_amount_tip:after {
  content: "\202F$";
}


.wallet-amount-selector {
  position: relative;
  display: block;
}

.wallet_amount {
  font-family: Lato-hairline;
  font-weight: 1000;
  display: inline-block;
  position: relative;
  width: 17.71429%;
  height: 136px;
  margin-right: .85714%;
  border-top: 1px solid #e5e5e5;
  border-radius: 5px;
  text-align: center;
  color: #404040;
  line-height: normal;
  box-shadow: 0 0 3px 1px rgb(0 0 0 / 11%);
  background: #cccccc no-repeat 100% 100%;
  vertical-align: top;
  transition: 0.4s;
  cursor: pointer;

}
.wallet_amount:hover{
  padding: 0.5%;
}

.wallet_amount_value {
  height: 50px;
  margin: 0;
  padding-top: 45px;
  font-size: 28px;
  line-height: 20px;
}

.wallet_amount_tip {
  margin-left: 3px;
  font-size: 16px;
  line-height: 24px;
  text-decoration: underline;
  vertical-align: top;
}

.wallet_amount_buy {
  display: flex;
  font-size: 12px;
  line-height: 25px;
  color: black;
}

.wallet_amount_buy:hover {
  fill: #7ca600;
  color: #7ca600;
  cursor: pointer;
}

::placeholder {
  font-family: Lato-hairline;
  font-weight: 1000;
}

.input_buy {
  font-family: Lato;
  font-weight: 1000;
  display: inline-block;
  width: 75px;
  color: black;
  background: none;
  border: none;
  border-bottom: 1px solid #fff;
  margin-left: 9%;
  font-size: 17px;
}

.buy_text {
  margin-top: 4px;
  transition: 0.2s;
}

.buy_text:hover {
  color: #7ca600;
  cursor: pointer;
}
</style>
