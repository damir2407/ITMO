<template>
  <body>



  <main class="product_main_content">
    <div class="product_bg" v-bind:src="picture_cover">
      <img style="width: 100%; height: 100%" v-bind:src="picture_cover"/>


    </div>
    <div class="product_main_container">
      <h1 class="product_title">{{ game_name }}</h1>
      <div class="product_content">
        <div class="product_left">
          <div class="product_info_left_block">
            <ul class="product_info_left_block_list">
              <li class="product_info_left_block_list_item">
                <div class="product_info-key">Жанр</div>
                <div class="product_info-value">
                  {{ genres.join(', ') }}
                </div>
              </li>

              <li class="product_info_left_block_list_item">
                <div class="product_info-key">Дата выхода</div>
                <div class="product_info-value">
                  {{ development_date }}
                </div>
              </li>

              <li class="product_info_left_block_list_item">
                <div class="product_info-key">Разработчик</div>
                <div class="product_info-value">
                  {{ dev_login }}
                </div>
              </li>

              <li class="product_info_left_block_list_item">
                <div class="product_info-key">Языки</div>
                <div class="product_info-value">
                  Русский
                </div>
              </li>

            </ul>
          </div>

        </div>


        <div class="product_info_right_block">
          <div class="product_header">
            <div class="product_extra_info">
              <div class="product_advantages">
                <ul class="product_advantages_list">
                  <li class="product_advantages_item">
                    <svg aria-hidden="true" width="22.08" height="22.08"
                         viewBox="-43.52 -110.52 599.04 599.04" fill="#75b5fe">
                      <path
                        d="M255.967 8c-14.42 0-28.913 3.167-39.842 9.51L69.402 102.207c-21.954 12.675-39.899 43.754-39.899 69.084v169.42c0 25.336 17.945 56.425 39.9 69.094l146.722 84.714c10.929 6.324 25.421 9.482 39.842 9.482 14.486 0 28.97-3.158 39.9-9.482l146.722-84.714c21.922-12.669 39.908-43.758 39.908-69.094V171.29c0-25.33-17.986-56.41-39.908-69.084L295.866 17.511C284.937 11.167 270.453 8 255.967 8zm99.189 144.373c4.227 0 8.45 1.602 11.651 4.813l17.847 17.827c6.402 6.428 6.402 16.938 0 23.36L228.22 354.823c-6.466 6.416-16.938 6.416-23.34 0l-68.942-68.913c-6.402-6.415-5.044-18.277 0-23.34l17.855-17.828c6.403-6.428 16.94-6.428 23.341 0l27.746 27.726c6.402 6.415 16.874 6.415 23.34 0l115.236-115.282a16.556 16.556 0 0111.699-4.813z"></path>
                    </svg>
                    Наличие:
                    <span class="product_advantages-blue">есть в наличии</span>
                  </li>

                  <li class="product_advantages_item">
                    <svg aria-hidden="true" width="22.08" height="22.08" viewBox="-0.5 -2.5 26 26"
                         fill="#75b5fe">
                      <path
                        d="M1.836 7.574h-1.1v3.97h2.822v-.965H1.836zM17.08 9.943h1.794V9.15H17.08v-.636h1.987v-.941H15.99v3.97h3.106v-.936h-2.021v-.665zm-12.075 0H6.8V9.15H5.005v-.636h1.992v-.941H3.92v3.97h3.106v-.936H5.005zm17.314.217c.492-.208.815-.613.815-1.22v-.01c0-.391-.12-.7-.352-.927-.265-.27-.685-.43-1.293-.43h-1.877v3.971h1.1v-1.201h.483l.8 1.201h1.27zm-.29-1.153c0 .29-.217.472-.588.472h-.724v-.95h.719c.367 0 .593.16.593.473zm-8.731-1.433h-1.53v3.97h1.51c1.428 0 2.263-.849 2.263-1.997v-.01c-.005-1.148-.825-1.963-2.243-1.963zm1.11 1.992c0 .642-.44 1.004-1.096 1.004h-.448V8.553h.448c.656 0 1.095.367 1.095 1.004zM11.734 0L5.497 6.238h12.475zm-2.88 7.574l-1.68 3.97h1.149l.28-.704h1.52l.289.704h1.172l-1.679-3.97zm.072 2.417l.444-1.158.439 1.158zm-.781 5.248h.482l.8 1.202h1.27l-.946-1.385c.492-.207.815-.613.815-1.22v-.01c0-.39-.12-.7-.352-.926-.265-.27-.685-.43-1.293-.43H7.046v3.966h1.1zm0-1.813h.718c.367 0 .594.159.594.472v.01c0 .29-.217.473-.589.473h-.723zm-1.54.453v-.01c0-.878-.646-1.394-1.65-1.394h-1.7v3.966h1.1v-1.134H4.9c.984 0 1.708-.492 1.708-1.428zm-1.1.048c0 .314-.236.516-.626.516h-.526v-1.046h.516c.396 0 .637.183.637.52zm6.788-1.457H11.19v3.966h1.104zm2.634 4.091c.695 0 1.341-.342 1.737-.916l.024-.034-.892-.613-.025.034a1.02 1.02 0 01-1.862-.574 1.02 1.02 0 011.023-1.018c.338 0 .651.164.84.444l.024.034.892-.613-.024-.034a2.103 2.103 0 00-1.737-.912 2.108 2.108 0 00-2.103 2.104c0 1.153.94 2.098 2.103 2.098zm3.295-1.056v-.666h1.906v-.791h-1.906v-.637h2.103v-.936h-3.193v3.966h3.218v-.936zM11.735 24l6.237-6.238H5.497z"></path>
                    </svg>
                    Самые низкие цены
                  </li>

                  <li class="product_advantages_item">
                    <svg aria-hidden="true" width="22.08" height="22.08" viewBox="0.48 -4 26.04 26.04"
                         fill="#75b5fe">
                      <path fill="none" d="M0 0h24v24H0z"></path>
                      <path
                        d="M9 16h6.5a2.5 2.5 0 000-5h-.05c-.24-1.69-1.69-3-3.45-3-1.4 0-2.6.83-3.16 2.02h-.16A2.994 2.994 0 006 13c0 1.66 1.34 3 3 3zM21 3H3c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h18c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16.01H3V4.99h18v14.02z"></path>
                    </svg>

                    ОС:
                    <span class="product_advantages-blue">Windows, Mac, Linux</span>
                  </li>

                  <li class="product_advantages_item">
                    <svg aria-hidden="true" width="22.08" height="22.08"
                         viewBox="-75.52 -260.52 730.04 730.04" fill="#75b5fe">
                      <path
                        d="M48 32C21.5 32 0 53.5 0 80v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V80c0-26.5-21.5-48-48-48H48zm176 64c13.02 9.05 130.934 120.222 137.143 131.145v60.183c-8.605.21-122.971-119.626-137.143-125.318-14.172 5.692-128.538 125.529-137.143 125.318v-60.183C93.066 216.222 210.98 105.049 224 96zm0 128.672c13.02 9.05 130.934 120.222 137.143 131.144V416c-8.605.21-122.971-119.627-137.143-125.32C209.828 296.373 95.462 416.21 86.857 416v-60.184C93.066 344.894 210.98 233.721 224 224.672z"></path>
                    </svg>
                    Опперативная техподдержка
                  </li>
                </ul>
              </div>
            </div>

            <div class="product_cart_block">
              <div class="product_price_block">
                <div class="product__discount">
                  OASIS
                </div>

                <div v-if="price>0" class="product_price">
                  <div class="product_current_price">
                    {{ price.toFixed(2) }}
                    <sup class="product_price_curr">$</sup>
                  </div>
                </div>

                <div v-else class="product_price">
                  <div class="product_current_price">
                    БЕСПЛАТНО
                  </div>
                </div>

              </div>
              <div class="product_md_block">
                <div class="product_buy_block">
                  <div class="buy_block">
                    <a @click="confirmBuy" class="product_buy_button">
                      <span>КУПИТЬ</span>
                    </a>
                  </div>
                </div>
              </div>
            </div>

          </div>

          <div class="product_description">
            <h2 class="product_block_title">Описание</h2>
            <div class="product_description_content">
              <p>
                <strong style="font-family: Montserrat; font-weight: bolder ">{{ game_name }}</strong>
                &nbsp;- {{ description }}
              </p>
            </div>
          </div>

          <div class="product_description" style="margin-top:20px">
            <h2 class="product_block_title">Скриншоты геймплея</h2>
            <div class="product_description_content" style="margin-left: 7%">
              <v-carousel-game
                :carousel_data="sliderItems"
              />
            </div>
          </div>

        </div>
      </div>
    </div>
  </main>
  </body>
</template>

<script>
import VCarouselGame from "@/components/v-carousel-game";
import axios from "axios";
import VueSimpleAlert from "vue-simple-alert";

export default {
  name: "Game",

  components: {
    VCarouselGame
  },

  data() {
    return {
      sliderItems: [
        {
          id: 1,
          img: 'agk2-games-01.png',
        },
        {
          id: 2,
          img: 'agk2-games-01.png',
        },
        {
          id: 3,
          img: 'agk2-games-01.png',

        },
      ],
      game_info: {
        type: Object,
        default: () => {
        }
      },
      price: '',
      genres: [],
      development_date: '',
      dev_login: '',
      description: '',
      picture_cover: '',
    }
  },
  props: {
    game_name: ''
  },
  created() {

    axios.post("http://localhost:8081/oasis/game/check-game-on-exist", {
      game_name: this.game_name
    }).then(response => {
      this.getInfo()
    }).catch(() => {
      this.$router.push({name: "shop"});
    })
  },
  methods: {

    getInfo() {
      axios.post('http://localhost:8081/oasis/game/get_game_info', {
        game_name: this.game_name,
      }).then(response => {
        this.price = response.data.price;
        this.genres = response.data.genres;
        this.development_date = response.data.development_date;
        this.dev_login = response.data.dev_login;
        this.description = response.data.game_description;
        this.picture_cover = response.data.picture_cover;
        this.sliderItems[0].img = response.data.picture_gameplay_1;
        this.sliderItems[1].img = response.data.picture_gameplay_2;
        this.sliderItems[2].img = response.data.picture_gameplay_3;
      }).catch(error => {
        this.$router.push({name: "main_page"});
        // this.ErrorHandler(error.response.data.violations[0].message)
      });
    },

    confirmBuy() {
      VueSimpleAlert.confirm("Вы действительно хотите купить эту игру? После нажатия кнопки OK средства автоматически спишутся с вашего кошелька").then(() => {


        axios.post('http://localhost:8081/oasis/game/buy_game',
          {
            game_name: this.game_name
          },
          {
            headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
          }).then(response => {
          if (response.data === 1)
            VueSimpleAlert.fire({
              title: "Спасибо за покупку!",
              text: "Игра занесена в вашу Библиотеку, а выпавшую вещь вы можете посмотреть в Инвентаре!",
              type: "success",
              timer: 10000
            })
          else
            VueSimpleAlert.fire({
              title: "Спасибо за покупку!",
              text: "Игра занесена в вашу Библиотеку",
              type: "success",
              timer: 10000
            })

        }).catch(error => {
          if (error.response.status === 401) {
            this.ErrorHandler("Для покупки игр необходимо авторизоваться!")
          } else if (error.response.status === 403) this.ErrorHandler("Разработчики не могут приобретать игры!")
          else this.ErrorHandler(error.response.data.violations[0].message.substring(8, error.response.data.violations[0].message.indexOf('Где')))

          console.log(error)
        });


      });
    },
    ErrorHandler(message) {
      VueSimpleAlert.fire({
        title: "Ошибка!",
        text: message,
        type: "error",
        timer: 3000
      })
    }
  }


}

</script>

<style scoped>
.product_main_content {
  position: relative;
  margin-bottom: 80px;
  -webkit-box-flex: 1;
  flex-grow: 1;
  display: block;
  box-sizing: border-box;
}

body {
  overflow-y: auto;
  color: #141414;
  background: #f3f3f3;
  width: 100%;
  margin: 0;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  font-size: 15px;
  line-height: 1.4;
  font-weight: 400;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  flex-direction: column;
  min-height: 100%;
}

.product_bg {
  height: 550px;
  background-size: cover;
  width: 100%;
  position: relative;
  background: #383838 50% no-repeat;
  display: block;
}

.product_main_container {
  max-width: 1236px;
  padding-left: 24px;
  padding-right: 24px;
  margin-right: auto;
  margin-left: auto;
  box-sizing: border-box;
  margin-top: -466px;
  position: relative;
}

.product_title {
  font-family: Play, Arial, sans-serif;
  font-weight: 700;
  font-size: 55px;
  text-transform: uppercase;
  color: #ffffff;
  margin-bottom: 50px;
  margin-top: 0;
  margin: 0.67em 0;
}

.product_content {
  display: flex;
  box-sizing: border-box;
}

.product_left {
  width: 405px;
  flex-shrink: 0;
  padding-right: 30px;
  position: relative;
  box-sizing: border-box;
}

.product_info_left_block {
  background-color: #3b3b3b;
  padding: 5px 20px;
  font-weight: 400;
  margin-top: 20px;
  font-size: 14px;
  color: #111;
  box-sizing: border-box;
  margin-top: 370px;
}

.product_info_left_block_list {
  padding: 0;
  list-style-position: inside;
  margin-top: 0;
  margin-bottom: 10px;
  box-sizing: border-box;
}

.product_info_left_block_list_item {
  display: flex;
  box-sizing: border-box;
  text-align: -webkit-match-parent;
  margin-top: 15px;
}

.product_info-key {

  color: #8b8b8b;
  width: 35%;
  padding-right: 13px;
  text-align: right;
  flex-shrink: 0;
  box-sizing: border-box;
  font-family: Lato;
  font-weight: 1000;
}

.product_info-value {
  font-family: Lato;
  color: #fff;
  -webkit-box-flex: 1;
  flex: 1;
  box-sizing: border-box;
  font-weight: 200;
}

.product_info_right_block {
  -webkit-box-flex: 1;
  flex: 1;
  max-width: 783px;
  box-sizing: border-box;
}

.product_header {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  min-height: 324px;
  box-sizing: border-box;
}

.product_extra_info {
  font-family: Play, Arial, sans-serif;
  font-size: 14px;
  width: 370px;
  box-sizing: border-box;

}

.product_advantages {
  height: 197px;
  background-color: rgba(0, 0, 0, .5);
  color: #cbcbcb;
  padding: 27px 25px;
  box-sizing: border-box;
}

.product_advantages_list {
  list-style: none;
  margin: 0;
  padding: 0;
  box-sizing: border-box;

}

.product_advantages_item {
  position: relative;
  padding-left: 40px;
  line-height: 19px;
  padding-top: 6px;
  box-sizing: border-box;
}

.product_advantages-blue {
  color: #75b5fe;
  box-sizing: border-box;
}

.product_cart_block {
  flex: 1;
  margin-left: 40px;
  -webkit-box-flex: 1;
  box-sizing: border-box;
}

.product_price_block {
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  box-sizing: border-box;
}

.product_price {
  display: flex;
  font-family: Play, Arial, sans-serif;
  font-weight: 700;
  padding-top: 5px;
  padding-left: 20px;
  min-height: 100px;
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
}

.product_current_price {
  font-size: 48px;
  color: #fff;
  line-height: 1;
  box-sizing: border-box;
}

.product_price_curr {
  font-size: 18px;
  text-transform: uppercase;
  top: -19px;
  left: -5px;
  line-height: 0;
  position: relative;
  vertical-align: baseline;
}

.product__discount {
  font-family: Play, Arial, sans-serif;
  font-weight: 700;
  color: #fff;
  background: -webkit-gradient(linear, left top, right top, from(#0094e8), to(#01b59a));
  background: -webkit-linear-gradient(left, #0094e8, #01b59a);
  background: -o-linear-gradient(left, #0094e8 0, #01b59a 100%);
  background: linear-gradient(90deg, #0094e8 0, #01b59a);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-size: 30px;
  padding: 30px 18px;
  position: relative;
}

.product__discount:before {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-position: 0 0, 0 100%;
  background-repeat: no-repeat;
  background-size: 100% 3px;
  background-image: -webkit-gradient(linear, left top, right top, from(#0094e8), to(#01b59a)), -webkit-gradient(linear, left top, right top, from(#0094e8), to(#01b59a));
  background-image: -webkit-linear-gradient(left, #0094e8, #01b59a), -webkit-linear-gradient(left, #0094e8, #01b59a);
  background-image: -o-linear-gradient(left, #0094e8 0, #01b59a 100%), -o-linear-gradient(left, #0094e8 0, #01b59a 100%);
  background-image: linear-gradient(90deg, #0094e8 0, #01b59a), linear-gradient(90deg, #0094e8 0, #01b59a);
}


.product__discount:after {
  background-position: 0 0, 100% 0;
  background-repeat: no-repeat;
  background-size: 3px 100%;
  background-image: -webkit-gradient(linear, left top, left bottom, from(#0094e8), to(#0094e8)), -webkit-gradient(linear, left bottom, left top, from(#01b59a), to(#01b59a));
  background-image: -webkit-linear-gradient(top, #0094e8, #0094e8), -webkit-linear-gradient(bottom, #01b59a, #01b59a);
  background-image: -o-linear-gradient(top, #0094e8 0, #0094e8 100%), -o-linear-gradient(bottom, #01b59a 0, #01b59a 100%);
  background-image: linear-gradient(180deg, #0094e8 0, #0094e8), linear-gradient(0deg, #01b59a 0, #01b59a);
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}

.product_md_block {
}

.product_buy_block {
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  margin-top: 17px;
  box-sizing: border-box;
}


.buy_block {
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
  box-sizing: border-box;
}

.product_buy_button {
  font-family: Play, Arial, sans-serif;
  font-weight: 700;
  font-size: 18px;
  text-transform: uppercase;
  background-color: #0094e9;
  border: none;
  color: #fff;
  height: 29px;
  cursor: pointer;
  display: block;
  padding: 23px 20px;
  -webkit-transition: all .3s ease;
  transition: all .3s ease;
  position: relative;
  overflow: hidden;
  width: 100%;
  outline: none;
  text-align: center;
  outline: none !important;
  text-decoration: none;
}

.product_buy_button:hover {
  background-color: #01b59a;

}

.product_description {
  background-color: #fff;
  margin-top: 46px;
  font-size: 14px;
  color: #111;
  padding: 40px 43px;
  font-weight: 400;
  box-sizing: border-box;
}


.product_block_title {
  font-family: Lato;
  font-weight: 700;
  color: #0d0d0d;
  font-size: 18px;
  margin-bottom: 25px;
  margin-top: 0;
}


.product_description_content {
  font-family: Lato-hairline;
  font-weight: 1000;
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
