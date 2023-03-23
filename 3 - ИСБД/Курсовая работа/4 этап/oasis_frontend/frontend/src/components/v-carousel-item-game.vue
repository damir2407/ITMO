<template>
  <div>
    <div>
      <img class="image_class" :src="item_data.img " alt="">
    </div>
  </div>
</template>

<script>
export default {
  name: "v-carousel-item-game",
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
}

* {
  font-family: "Lato", sans-serif;
  font-weight: 800;
}



@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato/lato-regular-webfont.woff") format("woff");
}


</style>
