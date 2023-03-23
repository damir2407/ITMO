<template>
  <body>
  <div class="global_content">
    <div class="content_header">

      <div class="header_top">
        <label class="header_top_label">Загрузить игру</label>
      </div>

      <div class="header_bottom">
        <h2>Заполните следующие поля</h2>
      </div>

    </div>

    <div class="content_main">
      <form style="display: block">
        <div class="columns">
          <div class="main_left_col">
            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите название игры</label>
              <input v-model="name" class="input_style" type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите описание</label>
              <textarea v-model="description" class="input_style" type="text" style="height: 300px"></textarea>
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000;">Выберите жанр(ы)</label>

              <div class="checkboxes" style="margin-top: 10px">
                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="Экшены" class="picker_handle">
                  <label style="margin-left: 10px">Экшены</label>
                </div>

                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="Стратегии" class="picker_handle">
                  <label style="margin-left: 10px">Стратегии</label>
                </div>


                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="Гонки" class="picker_handle">
                  <label style="margin-left: 10px">Гонки</label>
                </div>

                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="Казуальные" class="picker_handle">
                  <label style="margin-left: 10px">Казуальные</label>
                </div>


                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="RPG" class="picker_handle">
                  <label style="margin-left: 10px">RPG</label>
                </div>


                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="Симуляторы" class="picker_handle">
                  <label style="margin-left: 10px">Симуляторы</label>
                </div>


                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="Приключения" class="picker_handle">
                  <label style="margin-left: 10px">Приключения</label>
                </div>


                <div class="checkbox_style">
                  <input v-model="genres" type="checkbox" value="Спорт" class="picker_handle">
                  <label style="margin-left: 10px">Спорт</label>
                </div>
              </div>
            </div>


            <div class="input_row" style="margin-top: 140px">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на игру</label>
              <input v-model="game_url" placeholder="Введите ссылку" class="input_style"
                     type="text">
            </div>


            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите стоимость</label>
              <input v-model="price" placeholder="$0.00" class="input_style" type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку-обложку </label>
              <input v-model="picture_cover" placeholder="Рекомендуемое разрешение 2207x550" class="input_style"
                     type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку в магазине</label>
              <input v-model="picture_shop" placeholder="Рекомендуемое разрешение 108x72" class="input_style"
                     type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку-геймплей
                #1</label>
              <input v-model="picture_gameplay_1" placeholder="Рекомендуемое разрешение 600*300" class="input_style"
                     type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку-геймплей
                #2</label>
              <input v-model="picture_gameplay_2" placeholder="Рекомендуемое разрешение 600*300" class="input_style"
                     type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку-геймплей
                #3</label>
              <input v-model="picture_gameplay_3" placeholder="Рекомендуемое разрешение 600*300" class="input_style"
                     type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите название обычной вещи</label>
              <input placeholder="Введите название" v-model="common_item_name" class="input_style" type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку обычной
                вещи</label>
              <input v-model="common_item_url" placeholder="Введите ссылку на картинку" class="input_style" type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите название редкой вещи</label>
              <input placeholder="Введите название" v-model="rare_item_name" class="input_style" type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку редкой
                вещи</label>
              <input v-model="rare_item_url" placeholder="Введите ссылку на картинку" class="input_style"
                     type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите название легендарной вещи</label>
              <input placeholder="Введите название" v-model="legendary_item_name" class="input_style" type="text">
            </div>

            <div class="input_row">
              <label style="font-family: Lato-hairline; font-weight: 1000">Введите ссылку на картинку легендарной
                вещи</label>
              <input v-model="legendary_item_url" placeholder="Введите ссылку на картинку" class="input_style"
                     type="text">
            </div>


            <button @click="uploadGame" class="upload_button">ЗАГРУЗИТЬ</button>

          </div>
          <div class="main_right_col"></div>
        </div>


      </form>
    </div>


  </div>
  </body>
</template>

<script>
import  axios from "axios";
import VueSimpleAlert from "vue-simple-alert";

export default {
  name: "UploadGame",

  data() {
    return {
      name: '',
      description: '',
      genres: [],
      game_url: '',
      price: 0.00,
      picture_cover: '',
      picture_shop: '',
      picture_gameplay_1: '',
      picture_gameplay_2: '',
      picture_gameplay_3: '',
      common_item_name: '',
      common_item_url: '',
      rare_item_name: '',
      rare_item_url: '',
      legendary_item_name: '',
      legendary_item_url: '',
    }
  },

  methods: {
    uploadGame(event) {
      event.preventDefault()
      axios.post('http://localhost:8081/oasis/dev/upload_game',
        {
          name: this.name,
          description: this.description,
          genres: this.genres,
          game_url: this.game_url,
          price: this.price,
          picture_cover: this.picture_cover,
          picture_shop: this.picture_shop,
          picture_gameplay_1: this.picture_gameplay_1,
          picture_gameplay_2: this.picture_gameplay_2,
          picture_gameplay_3: this.picture_gameplay_3,
          devLogin: localStorage.getItem('login'),
          common_item_name: this.common_item_name,
          common_item_url: this.common_item_url,
          rare_item_name: this.rare_item_name,
          rare_item_url: this.rare_item_url,
          legendary_item_name: this.legendary_item_name,
          legendary_item_url: this.legendary_item_url,
        },
        {
          headers: {"Authorization": "Bearer " + localStorage.getItem('jwt')}
        }).then(response => {
        this.SuccessHandler(response.data.message)
      }).catch(error => {
        console.log(error)
        this.ErrorHandler(error.response.data.violations[0].message)
      });
    },

    SuccessHandler(message) {
      VueSimpleAlert.fire({
        title: "Успешно!",
        text: message,
        type: "success",
        timer: 3000
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

  }

}
</script>

<style scoped>

::placeholder {
  font-weight: 1000;
  font-family: Lato-hairline;
}

.global_content {
  width: 960px;
  height: 2250px;
  background: white;
  border: 1px solid #d0d0d0;
  box-shadow: 0 2px 4px rgb(0 0 0 / 5%);
  border-radius: 3px;
  margin: 100px auto 20px auto;
}

.content_header {
  width: 960px;
  height: 121.22px;
  display: block;
}

.header_top {
  position: relative;
  width: 960px;
  height: 40.2px;
  background-color: #F4F4F4;
  font-size: 16px;
  border-bottom: 1px solid;
  border-color: #dadada;
  border-radius: 3px 3px 0 0;

}


@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato2/latolight.woff") format("woff");
}

@font-face {
  font-family: "Lato-hairline";
  src: local("Lato"), url("../assets/font/Lato2/latohairline.woff") format("woff");
}


.header_top_label {
  overflow: hidden;
  position: absolute;
  margin-top: 1%;
  margin-left: 2%;
  font-weight: 1000;
  font-family: Lato;
  color: #858585;
}

.header_bottom {
  border-bottom: 1px solid;
  justify-content: center;
  border-color: #dadada;
  min-height: 80px;
  position: relative;
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 0 40px;
  font-family: Lato;
  font-weight: 1000;
}

.content_main {
  padding: 30px 40px;
  display: block;
}

.columns {
  display: flex;
  gap: 20px;
}

.main_left_col {
  flex: 1;
  min-width: 0;
}

.input_row {
  margin-bottom: 25px;
  font-size: 22px;
}

.main_right_col {
  min-width: 0;
  width: 317px;
}

.input_style {
  overflow: hidden;
  resize: none;
  font-size: 18px;
  font-family: Lato;
  font-weight: 100;
  padding: 8px;
  border: 2px solid;
  border-color: #cdcdcd;
  color: #222;
  background-color: white;
  border-radius: 2px;
  width: 524px;
  margin: 6px 0;
}

body {
  background: #F4F4F4;
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

.checkbox_style {
  font-family: Lato;
  font-weight: 100;
  font-size: 18px;
  float: left;
  width: 50%;
}

.upload_button {
  height: 38px;
  width: 225px;
  text-decoration: none;
  display: block;
  border-radius: 3px;
  border: none;
  background: linear-gradient(to bottom, #985fb2 0%, #713b8d 100%);
  box-shadow: 0px 0px 0px 1px rgb(0 0 0 / 15%), 0px 1px 3px 0px rgb(0 0 0 / 15%);
  line-height: 36px;
  color: #ffffff;
  font-weight: 1000;
  font-size: 15px;
  cursor: pointer;
  transition: 0.16s;
  font-family: Lato;
}

button:hover {
  background-color: #cccccc;
  color: #333333;

}
</style>
