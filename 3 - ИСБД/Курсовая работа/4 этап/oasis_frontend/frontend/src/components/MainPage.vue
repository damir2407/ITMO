`
<template>
  <body>


  <!-- Верхняя часть сайта -->
  <div class="oasis_top_content">
    <picture>
      <img class="background_oasis" src="@/assets/images/photoeditorsdk-export.png"/>
    </picture>
  </div>
  <!-- Конец верхней части сайта -->

  <!-- Разделение верхней от нижней части сайта -->
  <div class="center_container">
    <div class="center_container_title">
      <div class="center_container_title_text">Рекомендуемое</div>
    </div>
  </div>
  <!-- Конец разделение верхней от нижней части сайта -->

  <!-- Начало нижней части сайта -->
  <div class="oasis_bottom_content">
    <div class="oasis_bottom_main_content">
      <v-carousel
        :carousel_data="sliderItems"
      />

    </div>
  </div>
  <!-- Конец нижней части сайта -->


  </body>
</template>

<script>
import vCarousel from './v-carousel'


export default {
  name: "MainPage",
  components: {
    vCarousel
  },
  data() {

    return {

      sliderItems: [
        {
          id: 1,
          name: 'img1',
          img: 'photoeditorsdk-export (14).png',
          description: 'Узнайте больше о нашем',
          description2: 'игровом сервисе',
          item_url: '/about_us'

        },
        {
          id: 2,
          name: 'img2',
          img: 'mbmbm.jpg',
          description: 'Переходите в магазин и',
          description2: 'приобретайте игровые ключи',
          item_url: '/shop'
        },
        {
          id: 3,
          name: 'img3',
          img: 'skins.webp',
          description: 'Переходите в торговую площадку и',
          description2: 'приобретайте понравившиеся вещи!',
          item_url: '/trade'
        },
      ],
    }
  },


}
</script>

<style scoped>


* {
  font-family: Lato-hairline;
  font-weight: 1000;
}

.oasis_top_content {

  display: block;
  position: relative;
  z-index: 1;
  overflow: hidden;
  margin: 0;
  border-radius: 0;
  height: 360px;
  width: 100%;
  max-width: none;
  box-shadow: none;
}

.background_oasis {
  width: 100%;

}

.center_container {
  margin-top: 35px;
  margin-right: auto;
  margin-left: auto;
  width: 1096px;
  height: 32px;
}

.center_container_title {

  border-bottom: 1px solid var(--theme-section-separator);
  --fp-section-separator: #41322F;
  --theme-section-separator: var(--fp-section-separator, #b8b8b8);
  font-size: 16px;
  font-weight: 600;
  line-height: 16px;
  text-align: left;

}

.center_container_title_text {
  overflow: hidden;
  width: -moz-max-content;
  width: max-content;
  color: #DCD7C1;
  text-overflow: ellipsis;
  padding: 0 2%;


}

.oasis_bottom_content {
  position: relative;
  height: 396px;
  margin-left: 7%;
  width: 1000px;
  transform: translateX(-50%);


}

.oasis_bottom_main_content {
  position: fixed;
  left: 58%;
  width: 100%;
  height: 1000px;
}


body {
  overflow-y: auto;
  overflow-x: hidden;
  background: #1B1615;
  min-width: 100%;
  position: fixed;
  max-height: 1500px;
  top: 0;
  left: 0;
  right: 0;
  margin: 0;
  border: 0;
}

@font-face {
  font-family: "Open Sans";
  src: local("Open Sans"), url("../assets/font/OpenSans/OpenSans-Regular.ttf") format("ttf");
  font-weight: 400;
  font-style: normal;
}

@font-face {
  font-family: "Open Sans";
  src: local("Open Sans"), url("../assets/font/OpenSans/OpenSans-Bold.ttf") format("ttf");
  font-weight: 700;
  font-style: normal;
}

@font-face {
  font-family: "Open Sans";
  src: local("Open Sans"), url("../assets/font/OpenSans/OpenSans-BoldItalic.ttf") format("ttf");
  font-weight: 700;
  font-style: italic;
}

@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato2/latolight.woff") format("woff");
}

@font-face {
  font-family: "Lato-hairline";
  src: local("Lato"), url("../assets/font/Lato2/latohairline.woff") format("woff");
}

@font-face {
  font-family: Poppins-Medium;
  src: url("../assets/font/poppins/Poppins-Medium.ttf");
}


@font-face {
  font-family: Poppins-Regular;
  src: url("../assets/font/poppins/Poppins-Regular.ttf");
}


</style>

