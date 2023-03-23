<template>
  <div class='wrapper'>
    <div class="v-carousel" :style="{'margin-left': '-'+ (100*currentSlideIndex) + '%'}">
      <v-carousel-item-game
        v-for="item in carousel_data"
        :item_data="item"
      ></v-carousel-item-game>
    </div>

    <svg class="previous_next_button" @click="prevSlide" style="left: 0px; fill: #0094e9" aria-hidden="true" width="60.08"
         height="60.08"
         viewBox="-43.52 -43.52 599.04 599.04"
         fill="#222F3D">
      <path
        d="M256 504C119 504 8 393 8 256S119 8 256 8s248 111 248 248-111 248-248 248zM142.1 273l135.5 135.5c9.4 9.4 24.6 9.4 33.9 0l17-17c9.4-9.4 9.4-24.6 0-33.9L226.9 256l101.6-101.6c9.4-9.4 9.4-24.6 0-33.9l-17-17c-9.4-9.4-24.6-9.4-33.9 0L142.1 239c-9.4 9.4-9.4 24.6 0 34z"></path>
    </svg>

    <svg class="previous_next_button" @click="nextSlide" style="left: 540px; fill: #01b59a" aria-hidden="true" width="60.08"
         height="60.08"
         viewBox="-43.52 -43.52 599.04 599.04" fill="#222F3D">
      <path
        d="M256 8c137 0 248 111 248 248S393 504 256 504 8 393 8 256 119 8 256 8zm113.9 231L234.4 103.5c-9.4-9.4-24.6-9.4-33.9 0l-17 17c-9.4 9.4-9.4 24.6 0 33.9L285.1 256 183.5 357.6c-9.4 9.4-9.4 24.6 0 33.9l17 17c9.4 9.4 24.6 9.4 33.9 0L369.9 273c9.4-9.4 9.4-24.6 0-34z"></path>
    </svg>

  </div>
</template>

<script>
import VCarouselItemGame from "@/components/v-carousel-item-game";

export default {
  name: "v-carousel-game",
  components: {
    VCarouselItemGame
  },

  props: {
    carousel_data: {
      type: Array,
      default: () => []
    },
  },

  data() {
    return {
      currentSlideIndex: 0,

      interval_new:
        setInterval(() => {
          this.interval_new += 1;
        }, 1000),

    }
  },

  methods: {

    prevSlide() {
      this.interval_new = 0;
      if (this.currentSlideIndex > 0) {
        this.currentSlideIndex--;

      }
    },
    nextSlide() {
      this.interval_new = 0;
      if (this.currentSlideIndex >= this.carousel_data.length - 1) {
        this.currentSlideIndex = 0;
      } else {
        this.currentSlideIndex++
      }
    }
  },


  watch: {
    interval_new() {
      if (this.interval_new !== 0 && this.interval_new % 7 === 0) {
        this.nextSlide();
      }
    },

  }


}
</script>

<style scoped>
.wrapper {
  overflow: hidden;
  width: 600px;
  height: 300px;
  position: relative;

}

.v-carousel {
  display: flex;
  transition: all ease .7s;
}



.previous_next_button {
  position: absolute;
  bottom: 40%;
  stroke: #1B1615;
  stroke-width: 3;
  cursor: pointer;
}


</style>
