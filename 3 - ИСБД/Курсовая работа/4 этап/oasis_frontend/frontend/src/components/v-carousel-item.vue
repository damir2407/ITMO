<template>
  <div>
    <a :href="item_data.item_url">
      <img class="image_class" :src=" require('../assets/images/'+ item_data.img) " alt="">
    </a>
    <h2 class="description_class" v-text="item_data.description"></h2>
    <h2 class="description2_class" v-text="item_data.description2"></h2>

    <a class="navigate_button" :href="item_data.item_url">
      <p class="button_text">Перейти</p>
    </a>
  </div>
</template>

<script>
export default {
  name: "v-carousel-item",
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

.image_class {
  position: relative;
  transition: 2s;
}

.image_class:hover{
  cursor: pointer;
  transform: scale(1.2);
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
  top: 485px;
  position: absolute;
  margin-left: -17%;
  height: 55px;
  width: 13.5%;
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

@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato/lato-regular-webfont.woff") format("woff");
}

.button_text{
  text-decoration: none;
  margin-top: 9%;
}

</style>
