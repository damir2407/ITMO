<template>
  <body>
  <div class="background_wrapper">
    <img style="width: 100%; height: 100%"
         src="@/assets/images/geometry-of-lights-4k_1553075318.jpg">
  </div>

  <div class="mainContent">
    <div class="tabcontent_inventory">
      <div class="view_inventory_page">


        <div class="inventory_page_left">
          <div class="item_box">
            <div class="inventory_user">
              <div class="inventory_page_user">
                <div class="item_holder" v-for="item in item_data" @click="pickItem(item)">
                  <img style="width: 100%; height: 100%; border: none"
                       v-bind:src="item.item_picture">

                </div>

                <div class="item_holder" v-for="gap in blankGaps">

                </div>


              </div>
            </div>
          </div>
        </div>


        <div class="inventory_page_right">
          <div class="inventory_item_info">
            <div v-if="picked_item!=null" class="item_desc_info_real">
              <div class="item_desc_icon">
                <div class="item_desc_icon_center">
                  <img style="width: 100%; height: 100%"
                       v-bind:src="picked_item.item_picture">
                </div>
              </div>

              <div class="item_desc_description">
                <h1 class="hover_item_name" style="color: rgb(210, 210, 210);">{{ picked_item.item_name }}</h1>
              </div>

              <div class="item_desc_game_info">
                <div class="item_desc_game_icon">
                  <img style="width: 32px; height: 32px; border-radius: 3px"
                       v-bind:src="picked_item.game_picture">
                </div>
                <div class="item_desc_game_name">{{ picked_item.game_name }}</div>
                <div class="item_type">Редкость:
                  <span v-if="picked_item.rarity==='Обычная'" style="color: #cccccc">{{ picked_item.rarity }}</span>
                  <span v-else-if="picked_item.rarity==='Редкая'" style="color: #0094e8">{{ picked_item.rarity }}</span>
                  <span v-else style="color: #e96219">{{ picked_item.rarity }}</span>
                </div>
              </div>

              <div class="item_desc_description">
                <h1 class="hover_item_name" style="color: rgb(210, 210, 210);margin-top: 10%">Количество:
                  {{ picked_item.amount }}</h1>

                <button @click="sellItem" class="submit_button">ПРОДАТЬ</button>

              </div>

            </div>
          </div>
        </div>


      </div>
    </div>
  </div>

  </body>
</template>

<script>
import axios from "axios";
import VueSimpleAlert from "vue-simple-alert";

export default {
  name: "Inventory",
  data() {
    return {
      blankGaps: new Array(25),
      item_data: new Array(0),
      picked_item: null
    }
  },
  created() {
    this.getAllItems();
  },
  methods: {

    getAllItems() {
      axios.get("http://localhost:8081/oasis/inventory/get_all_items", {
        headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
      }).then(response => {
        console.log(response)
        this.item_data = response.data


        this.picked_item = response.data[0]


      }).catch(error => {
        this.ErrorHandler(error.response.data.violations[0].message)
      })
    },

    sellItem() {
      VueSimpleAlert.confirm("Вы действительно хотите продать эту вещь?").then(() => {
        var priceToSend = prompt("Введите стоимость вещи $USD")
        console.log(priceToSend);
        if (priceToSend !== undefined && priceToSend !== null) {
          axios.post('http://localhost:8081/oasis/market/sell_item', {
            game_name: this.picked_item.game_name,
            item_name: this.picked_item.item_name,
            rarity: this.picked_item.rarity,
            price: priceToSend
          }, {
            headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
          }).then(response => {
            this.SuccessHandler("Вещь успешно выставлена на Торговую площадку!")
            this.getAllItems()
          }).catch(error => {
            this.ErrorHandler(error.response.data.violations[0].message)
          });
        }
      });
    },

    pickItem(item) {
      this.picked_item = item;
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
        timer: 2000
      })
    },
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

.mainContent {
  max-width: 926px;
  padding: 0;
  text-align: left;
  margin: 8% auto;
}

.tabcontent_inventory {
  border: 1px solid #2c3235;
  background-color: #1c1f21;
  margin-top: 20px;
}

.view_inventory_page {
  display: flex;
  position: relative;
  background-color: #1c1e21;
  padding: 11px;
  width: 884px;
  margin-left: 6px
}

div {
  display: block;
}

.inventory_page_left {
  width: 528px;
}

.item_box {
  overflow-x: hidden;
  overflow-y: auto;
  width: 520px;
  height: 520px;
  margin-bottom: 8px;
  background-color: #000000;
  border: 1px solid #2c2c2d;
  padding: 6px 0 0 6px;
  user-select: none;
  -moz-user-select: none;
  -webkit-user-select: none;
}

.inventory_user {
  position: relative;
  width: 520px;
  height: 520px;
}

.inventory_page_user {
  display: block;
  float: left;
  width: 520px;
  height: 520px;
}

.item_holder {
  position: relative;
  width: 98px;
  height: 98px;
  background-color: #333333;
  background-repeat: no-repeat;
  background-position: center top;
  float: left;
  margin-right: 6px;
  margin-bottom: 6px;
  transition: 0.2s;
  cursor: pointer;

}

.item_holder:hover {
  transform: scale(1.1);

}

.inventory_page_right {
  float: right;
  width: 350px;
  position: relative;
}

.inventory_item_info {
  opacity: 1;
  z-index: -1000;
  border-color: rgb(210, 210, 210);
  width: 346px;
  top: 0px;
  left: 0px;
}

.item_desc_info_real {
  min-height: 207px;
  padding: 238px 11px 11px 11px;
  position: relative;
  background-color: #1d1d1d;

  background-repeat: no-repeat;
  background-position: center top;
  overflow: hidden;
}

.item_desc_description {
  color: #b4b4b4;
  font-size: 14px;
  padding-right: 8px;
}

.hover_item_name {
  font-size: 24px;
  margin-bottom: 5px;
  font-family: Lato;
  font-weight: 300;
  margin: 0px;
}

.item_desc_game_info {
  font-family: Lato;
  font-weight: 1000;
  position: relative;
  padding-left: 36px;
  color: #767676;
  min-height: 32px;
  margin-bottom: 16px;
  font-size: 14px;
  padding-right: 8px;
}

.item_desc_game_icon {
  position: absolute;
  left: 0;
  top: 0;
}

.item_desc_game_name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.item_desc_icon {
  position: absolute;
  left: 8px;
  top: 8px;
  right: 8px;
}

.item_desc_icon_center {
  position: absolute;
  height: 192px;
  width: 100%;
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}


.submit_button {
  height: 38px;
  margin-top: 6%;
  width: 175px;
  text-decoration: none;
  display: block;
  border-radius: 3px;
  border: none;
  background: linear-gradient(to bottom, rgb(0, 59, 59) 0%, rgb(4, 117, 121) 100%);
  box-shadow: 0px 0px 0px 1px rgb(0 0 0 / 70%), 0px 1px 3px 0px rgb(0 0 0 / 15%);
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
