<template>
  <body>

  <div class="global_content">

    <div class="text_header">
      <h1 class="text_header_h1">Торговая площадка</h1>
    </div>

    <section class="container_class">

      <aside class="main_aside">
        <div class="catalog_filter_block">
          <div class="catalog_filter_block_head">Поиск</div>
          <input type="text" v-model="itemName" @input="filterTrigger" placeholder="Введите название вещи"
                 class="for_find_input">
        </div>

        <div class="catalog_filter_block" style="margin-top: 6px">
          <div class="catalog_filter_block_head">Игры</div>

          <div class="catalog_filter_checklist">

            <div class="catalog_item" @click="selectGame('')"
                 style="width: 315px; height: 20px; background-color: rgb(46,50,55)">
              <div v-if="selectedGame===''"
                   style="background-color: #75b5fe ;width: 100%; height: 100%; position: absolute;margin-left: -6%;z-index: -1"
              ></div>

              <div class="catalog_item_name" style="margin-left: -27px">
                Все игры
              </div>
            </div>

            <div v-for="game in allGames" @click="selectGame(game.game_name)" class="catalog_item"
                 style="width: 315px; height: 20px; background-color: rgb(46,50,55)">

              <div v-if="selectedGame===game.game_name"
                   style="background-color:#75b5fe;width: 100%; height: 100%; position: absolute;margin-left: -6%;z-index: -1"
              ></div>

              <div style="background-color: rgb(46,50,55)"></div>
              <div class="catalog_item_img" style="width: 32px; height: 32px">
                <img class="img_catalog_class" v-bind:src="game.game_picture"/>
              </div>
              <div class="catalog_item_name" style="margin-left: -20px">
                {{ game.game_name }}
              </div>
            </div>


          </div>

        </div>


      </aside>


      <div class="catalog">


        <div v-if="allSlots.length>0" class="catalog_main_part">

          <div class="catalog_item" v-for="slot in allSlots" @click="selectItem(slot)">

            <div class="catalog_item_img">
              <img class="img_catalog_class" v-bind:src="slot.item_picture"/>
            </div>

            <div class="catalog_item_name">
              <!--                сделать название вещи цветом ее редкости, типа если редкость легендарная то название оранжевое-->

              <div v-if="slot.rarity==='Обычная'" style="color: #cccccc">{{ slot.item_name }}</div>
              <div v-else-if="slot.rarity==='Редкая'" style="color: #0094e8">{{ slot.item_name }}</div>
              <div v-else style="color: #e96219">{{ slot.item_name }}</div>


              <div class="catalog_item_name_genre">

                <div class="genre_class">{{ slot.game_name }}</div>

              </div>

            </div>

            <div class="catalog_item_price">
              <span class="catalog_item_price_span">
                ${{ slot.price.toFixed(2) }} USD
              </span>

            </div>


          </div>


        </div>
        <div v-else
             style="font-family: Lato; font-weight: 200;font-size: 36px; margin: 100px; margin-left: 80px; color: #ffffff">
          Пусто...
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
  name: "TradingFloor",

  data() {
    return {
      allGames: new Array(0),
      itemName: '',
      selectedGame: '',
      allSlots: new Array(0),
      selectedItemToBuy: ''
    }
  },
  methods: {
    getAllGames() {
      axios.get("http://localhost:8081/oasis/game/get_all_games_for_market", {}).then(response => {
        this.allGames = response.data;
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },


    selectGame(game_name) {
      this.selectedGame = game_name;
      this.filterTrigger();
    },

    filterTrigger() {
      axios.post("http://localhost:8081/oasis/market/get_all_slots", {
        game_name: this.selectedGame,
        item_name: this.itemName
      }).then(response => {
        console.log(response)
        this.allSlots = response.data;
      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },

    selectItem(item) {
      VueSimpleAlert.confirm("Вы действительно хотите купить эту вещь? После нажатия кнопки OK средства автоматически спишутся с вашего кошелька").then(() => {
        this.selectedItemToBuy = item;
        this.buySlot();
      })
    },

    buySlot() {
      axios.post('http://localhost:8081/oasis/market/buy_item', {
        game_name: this.selectedItemToBuy.game_name,
        item_name: this.selectedItemToBuy.item_name,
        rarity: this.selectedItemToBuy.rarity,
        market_id: this.selectedItemToBuy.marketId
      }, {
        headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
      }).then(response => {
        this.SuccessHandler("Вещь успешно приобретена и занесена в ваш инвентарь!")
        this.filterTrigger()
      }).catch(error => {
        if (error.response.status === 401) {
          this.ErrorHandler("Для покупки вещей необходимо авторизоваться!")
        } else if (error.response.status === 403) this.ErrorHandler("Разработчики не могут приобретать вещи!")
        else this.ErrorHandler(error.response.data.violations[0].message)
      });
      this.selectedItemToBuy = ''
    },
    ErrorHandler(message) {
      VueSimpleAlert.fire({
        title: "Ошибка!",
        text: message,
        type: "error",
        timer: 5000
      })
    },
    SuccessHandler(message) {
      VueSimpleAlert.fire({
        title: "Успешно!",
        text: message,
        type: "success",
        timer: 5000
      })
    },

  },

  created() {
    this.getAllGames()
    this.filterTrigger();
  }
}
</script>

<style scoped>

.global_content {
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  display: block;
  margin-top: -5%;
}

.text_header {
  position: relative;
  max-width: 1236px;
  margin: 0 auto;
  padding: 0 24px;
  border: 0;
  margin-top: 14%;
}

.text_header_h1 {
  font-size: 30px;
  padding-left: 0;
  margin-top: 0;
  font-family: Lato;
  font-weight: 900;
  color: #ffffff;
}


.container_class {

  max-width: 1236px;
  padding-left: 24px;
  padding-right: 24px;
  margin-right: auto;
  margin-left: auto;
  display: flex;
}

.catalog {
  margin-top: -13px;
  padding: 0 12px;
  float: left;
  position: absolute;

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
  background-color: rgb(22, 32, 45);
  text-decoration: none;
  width: 860px;
  opacity: 0.99;
  box-shadow: 0px 1px 1px 1px rgb(0 0 0 / 20%);
  transition: 0.1s;
  margin-top: 1.5%;
}

.catalog_item:hover {
  cursor: pointer;
  text-decoration: #f1f1f1;
  box-shadow: 0px 1px 1px 4px rgb(0 0 0 / 20%);
}


.catalog_main_part {
  margin-top: 1.4px;

}


.catalog_item_img {
  width: 190px;
  height: 72px;
}


.catalog_item_name {
  padding-left: 27px;
  padding-right: 15px;
  vertical-align: middle;
  color: #ffffff;
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
  -webkit-box-flex: 1;
  -ms-flex: 1 auto;
  flex: 1 auto;
  -webkit-box-pack: end;
  -ms-flex-pack: end;
  justify-content: flex-end;
  font-size: 19px;
  color: #ffffff;
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

}

.img_catalog_class {
  width: 100%;
  height: 100%;
}

.catalog_item_price_span {
  width: 200px;
  text-align: right;
  font-family: Montserrat;
  font-weight: 600;
}

body {
  width: 100%;
  height: 100%;
  background: linear-gradient(to bottom, rgb(35, 63, 92) 0%, rgb(27, 40, 56) 100%);
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

  left: 75.66666667%;
  width: 33.33333333%;
  position: relative;
  display: block;
}

.catalog_filter_block {
  padding: 10px;
  background: rgba(0, 0, 0, 0.4) none repeat scroll 0 0;
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
  margin-top: -2%;
  width: 100%;
  padding: 5%;
  font-family: Lato-hairline;
  font-weight: 700;

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
  width: 337px;
  margin: 6px 0;
  margin-left: 21px;
}


</style>
