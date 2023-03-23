<template>
  <body>


  <div class="slider_top">
    <div class="slider_content">
      <div class="slider_main_content">
        <v-carousel-shop
          :carousel_data="sliderItemsFirstHalf"
        />

      </div>
    </div>
  </div>


  <div class="global_content">

    <div class="text_header">
      <h1 class="text_header_h1">Каталог продукции</h1>
    </div>

    <section class="container_class">

      <aside class="main_aside">
        <div class="catalog_filter_block">
          <div class="catalog_filter_block_head">Поиск</div>
          <input type="text" v-model="gameName" @input="filterTrigger" placeholder="Введите название игры"
                 class="for_find_input">
        </div>

        <div class="catalog_filter_block" style="margin-top: 6px">
          <div class="catalog_filter_block_head">Жанры</div>

          <div class="catalog_filter_checklist">

            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="Экшены" class="picker_handle">
              <label style="margin-left: 10px">Экшены</label>
            </div>

            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="Стратегии" class="picker_handle">
              <label style="margin-left: 10px">Стратегии</label>
            </div>


            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="Гонки" class="picker_handle">
              <label style="margin-left: 10px">Гонки</label>
            </div>

            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="Казуальные" class="picker_handle">
              <label style="margin-left: 10px">Казуальные</label>
            </div>


            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="RPG" class="picker_handle">
              <label style="margin-left: 10px">RPG</label>
            </div>


            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="Симуляторы" class="picker_handle">
              <label style="margin-left: 10px">Симуляторы</label>
            </div>


            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="Приключения" class="picker_handle">
              <label style="margin-left: 10px">Приключения</label>
            </div>


            <div class="picker_checkbox">
              <input v-model="genres" @change="filterTrigger" type="checkbox" value="Спорт" class="picker_handle">
              <label style="margin-left: 10px">Спорт</label>
            </div>


          </div>

        </div>


      </aside>


      <div class="catalog">

        <div v-if="allGames.length>0" class="catalog_main_part" v-for="game in allGames">
          <a class="catalog_item" v-bind:href="'/game/'+game.game_name">

            <div class="catalog_item_img">
              <img class="img_catalog_class" v-bind:src="game.picture_shop"/>
            </div>

            <div class="catalog_item_name">
              {{ game.game_name }}

              <div class="catalog_item_name_genre">

                <div class="menu__logo-icon">
                  <svg aria-hidden="true"
                       viewBox="-35.84 -35.84 583.68 583.68">
                    <path
                      d="M307.4 20.97c-21.7-.15-43.9 3.68-64.9 9.72C296.7 39.1 344.7 55 364.3 80.71c-39.8-9.4-74.5 34.19-75.7 69.09 23.4-24.2 47.8-41.4 87.4-43.7 27.9 56.7 5.1 141.1 7.6 199.7.6 15.3 47.8 24.6 47.2 10.1-.2-51.5-4-145-25.8-208.1 38.8 7.3 74.1 33 74.1 33-1.1-23-26.9-48.99-58.6-53.59 7.7-9.6 27-24.9 71.1-26.71-23.4-18.4-59.9-17.7-88.3 2.31-25.1-30.36-59.8-41.61-95.9-41.84zm-153 35.84c-18.3.1-36.9 6.89-56.35 21.82-25.99-18.58-56.2-25.59-77.61-8.5 40.35 1.68 49.53 21.74 56.57 30.57-29 4.3-50.49 25.9-51.49 47.1 0 0 34.94-13.1 70.44-19.9-19.94 58.5-20.49 91.1-20.68 139 .57 11.5 41.52 17.8 42.22 1.5 2.3-54.5-13.6-79.4 2.4-134.1 11.5-5.9 52.2.5 73.6 23-1.1-32.3-19-64.41-55.4-55.7 9.4-18.03 26.8-21.13 78-20.99-20.7-15.28-41-23.87-61.7-23.8zm50.4 227.69c-22.3-.1-44.3 3.4-65.2 12.2-57.09 24.2-85.37 48.5-90.55 75.7-2.58 13.6 1.58 26.9 9.74 38.2 8.16 11.3 20.17 21.1 34.67 29.9 58.04 35.1 156.94 55.3 222.24 49.6 26.6-2.3 62.7-7 93.7-18.4 15.4-5.8 29.7-13.2 40.8-23.4 11-10.3 18.8-23.7 20-39.7v-.1c1-14.1-4.2-26.7-12.6-36.5-8.4-9.8-19.8-17.4-32.3-23.8-25.1-12.9-55.1-21.3-76.5-29.3-44-16.5-94.8-34.2-144-34.4zm11.2 18.7c12.3.8 24.9 2.6 37.4 5.2-70.8 23.9-127.7 59.7-171.65 101-4.55-4.4-8.4-8.9-11.35-14 39-36.4 87.3-68.4 145.6-92.2zm58.3 10.1c17.4 4.6 34.7 10.3 51.4 16.3-71.7 31.2-138.6 65.8-192.3 110.7-12.8-5.3-24-11.3-34.64-17.7 50.54-45 111.14-79.4 175.54-109.3z">
                    </path>
                  </svg>
                </div>

                <div class="genre_class">{{ game.genres.join(', ') }}</div>

              </div>

            </div>

            <div class="catalog_item_price">
              <span v-if="game.price>0" class="catalog_item_price_span">
                {{ game.price.toFixed(2) }}$
              </span>

              <span v-else class="catalog_item_price_span">
                Бесплатно
              </span>
            </div>

          </a>
        </div>

        <div style="font-family: Lato; font-weight: 200;font-size: 30px; margin: 100px; margin-left: 220px" v-else>К
          сожалению, ничего не найдено.
        </div>


      </div>


    </section>

  </div>
  </body>
</template>

<script>
import vCarouselShop from './v-carousel-shop'
import axios from "axios";
import VueSimpleAlert from "vue-simple-alert";

export default {
  name: "Shop",
  components: {
    vCarouselShop,
  },

  data() {
    return {

      sliderItemsFirstHalf: [
        {
          id: 1,
          img: 'photoeditorsdk-export (22).png',
          description: 'Resident Evil Village',
          description2: 'лидер продаж',
          item_url: '/game/Resident%20Evil%20Village',


          img_2: 'photoeditorsdk-export (28).png',
          item2_url: '/game/Need%20For%20Speed%20PAYBACK',


          img_3: 'photoeditorsdk-export (24).png',
          item3_url: '/game/Grand%20Theft%20Auto%20V',


        },
        {
          id: 2,
          img: 'photoeditorsdk-export (21).png',
          description: 'God of War 3',
          description2: 'лидер продаж',
          item_url: '/game/God%20Of%20War%203',


          img_2: 'photoeditorsdk-export (25).png',
          item2_url: '/game/The%20Witcher%203:%20Wild%20Hunt',


          img_3: 'photoeditorsdk-export (27).png',
          item3_url: '/game/Far%20Cry%203',

        },
        {
          id: 3,
          img: 'photoeditorsdk-export (16).png',
          description: 'Hellsing',
          description2: 'лидер продаж',
          item_url: '/game/Hellsing',

          img_2: 'photoeditorsdk-export (26).png',
          item2_url: '/game/BEYOND%20Two%20Souls',

          img_3: 'photoeditorsdk-export (23).png',
          item3_url: '/game/Call%20of%20Duty:%20Black%20Ops',

        },
      ],
      allGames: new Array(0),
      genres: [],
      gameName: '',
    }
  },

  methods: {

    filterTrigger() {
      axios.post("http://localhost:8081/oasis/shop/get_by_game_name_and_genres", {
        gameName: this.gameName,
        genres: this.genres
      }).then(response => {
        this.allGames = response.data;
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
  margin-top: -5%;
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
  margin-top: -293px;
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
  background-color: #fff;
  text-decoration: none;
  width: 860px;
}

.catalog_item:hover {
  cursor: pointer;
  box-shadow: 0px 1px 1px 1px rgb(0 0 0 / 20%);
  text-decoration: #f1f1f1;
}

.catalog_item:hover .catalog_item_name {
  color: #75b5fe;
}

.catalog_item:hover .catalog_item_price {
  color: #75b5fe;
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
  color: #0a0a0a;
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
  background-color: #f3f3f3;
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
  background: #e9e8e8;
}

.catalog_filter_block_head {

  text-align: center;
  font-size: 17px;
  line-height: 20px;
  font-family: Lato;
  font-weight: 700;
}


.genre_class {
  font-weight: 200;
  margin-left: 15px;
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


</style>
