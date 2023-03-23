<template>
  <div class="main_div">

    <div class="first_half_image">
      <a :href="item_data.item_url">
        <img class="image_class" :src=" require('../assets/images/'+ item_data.img) " alt="">
      </a>
      <h2 class="description_class" v-text="item_data.description"></h2>
      <h2 class="description2_class" v-text="item_data.description2"></h2>
      <a class="navigate_button" :href="item_data.item_url">
        <p class="button_text">Перейти</p>
      </a>

    </div>

    <div class="second_half_images">
      <div class="image_of_half">
        <a :href="item_data.item2_url">
          <img class="image_class" :src=" require('../assets/images/'+ item_data.img_2) " alt="">
        </a>
      </div>

      <div class="image_of_half">
        <a :href="item_data.item3_url">
        <img class="image_class" :src=" require('../assets/images/'+ item_data.img_3) " alt="">
        </a>

      </div>
    </div>
  </div>


</template>

<script>
export default {
  name: "v-carousel-item-shop",
  props: {
    item_data: {
      type: Object,
      default: () => {
      }
    }
  },
  data() {
    return {}
  },


}
</script>

<style scoped>


.main_div {

  display: flex;

}

.first_half_image {
  width: 56%;
  position: relative;


}

.second_half_images {
  width: 100%;
  height: 100%;


}

.image_of_half {
  width: 90.91%;
  height: 49.45%;
  overflow: hidden;
}


.description_class {
  position: absolute;
  font-size: 28px;
  width: 100%;
  color: #f1f1f1;
  top: 450px;
  margin-left: 2%;
}

.description2_class {
  position: absolute;
  font-size: 28px;
  width: 100%;
  color: #f1f1f1;
  top: 485px;
  margin-left: 2%;
}

* {
  font-family: "Lato", sans-serif;
  font-weight: 800;
}

.navigate_button {
  right: 5%;
  top: 485px;
  position: absolute;
  height: 55px;
  width: 22%;
  font-size: 20px;
  background-color: #595959;
  color: #f2f2f2;
  border-radius: 3px;
  border: none;
  box-shadow: 0px 0px 0px 1px rgb(0 0 0 / 15%), 0px 1px 3px 0px rgb(0 0 0 / 15%);
  transition: all 0.3s;
  text-decoration: none;
  text-align: center;
}

.navigate_button:hover {
  cursor: pointer;
  color: #1B1615;
}


.image_class {

  position: relative;
  transition: 2s;

}

.image_class:hover {
  cursor: pointer;
  transform: scale(1.2);
}

@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato/lato-regular-webfont.woff") format("woff");
}

.button_text {
  text-decoration: none;
  margin-top: 9%;
}

</style>
