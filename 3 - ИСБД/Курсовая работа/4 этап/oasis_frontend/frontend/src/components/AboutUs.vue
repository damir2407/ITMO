<template>
  <body>

  <img class="background_about_us" src="@/assets/images/geometry-abstract-4k_1553075423.jpg"/>


  <!-- 1   -->
  <div class="main_container">
    <div class="search_title">
      <img src="@/assets/images/game-center.png"/>
      <div class="title_text">
        <h2>Мы отбираем только лучшие игры</h2>
        <h3>Коллекция замечательных игр от лучших новинок до вечной классики. Вы обязательно найдете игру по
          душе.</h3>
      </div>
    </div>

    <div class="main_info">

      <div class="gaming-box">
        <img class="pictures_class" src="@/assets/images/free-icon-ski-goggles-6473587.png"/>
        <h3>Уникальная подборка</h3>
        <p>От изумительных AAA-игр и необычных инди-проектов до нестареющей классики. Каждая игра попала в нашу
          коллекцию не случайно.</p>
      </div>

      <div class="gaming-box">
        <img class="pictures_class" src="@/assets/images/free-icon-christmas-star-1366893.png"/>
        <h3>Ваша личная коллекция</h3>
        <p>Находить для вас только лучшие игры - наша главная задача в OASIS.com. Даже если в поисках сокровищ нам
          придется исследовать давно забытые подвалы и пыльные чердаки.</p>
      </div>

      <div class="gaming-box">
        <img class="pictures_class" src="@/assets/images/free-icon-diamond-2650871.png"/>
        <h3>Каждая игра - особенная</h3>
        <p>Мы не просто внимательно отбираем игры в нашу коллекцию. Для нас важно, чтобы каждая игра на OASIS.com была
          замечена и нашла своих поклонников. Для этого мы поддерживаем каждый релиз, отдельно рассказывая о нем.</p>
      </div>

      <div class="gaming-box">
        <img class="pictures_class" src="@/assets/images/free-icon-heart-7761809.png"/>
        <h3>Старая классика на новых ПК</h3>
        <p>Даже если игра старше, чем вы, мы подарим ей новую жизнь! Протестируем и доработаем так, чтобы вы смогли
          комфортно играть даже на новеньком ПК с современной операционной системой.</p>
      </div>

      <div class="gaming-box">
        <img class="pictures_class" src="@/assets/images/free-icon-developer-1118890.png"/>
        <h3>Уважение к разработчикам</h3>
        <p>Покупая игры у нас, вы можете быть уверены, что поддерживаете их создателей.</p>
      </div>

    </div>
  </div>


  <!--   2 -->
  <div class="main_container" style="margin-top: -40px">
    <div class="search_title">
      <img src="@/assets/images/free-icon-man-3345984.png"/>
      <div class="title_text">
        <h2 style="color: #e96219">Игроки - важнее всего</h2>
        <h3>Попробуйте и почувствуйте разницу! Выбирайте игры из нашего большого каталога и играйте без проблем и
          DRM.</h3>
      </div>
    </div>

    <div class="main_info">

      <div class="gaming-box">
        <img class="pictures_class" src="@/assets/images/free-icon-achievement-3631571.png"/>
        <h3 style="color: #e96219">Купленные игры принадлежат вам</h3>
        <p>Мы не считаем, что вас и ваши игры нужно контролировать. У вас никогда не будет проблем с доступом к игре, за
          которую вы заплатили, вам не придется постоянно вводить свои данные, чтобы запустить игру. Это и есть "без
          DRM".</p>
      </div>

      <div class="gaming-box">
        <img class="pictures_class" src="@/assets/images/free-icon-team-8454992.png"/>
        <h3 style="color: #e96219">Мы хотим быть лучшими для вас</h3>
        <p>Мы - компания геймеров и стремимся сделать OASIS.com таким, чтобы мы сами хотели им пользоваться. Отсюда и
          уникальный подход к DRM, справедливые цены, программа возврата игр и многое другое..</p>
      </div>


    </div>
  </div>


  </body>

</template>

<script>

export default {
  name: "AboutUs",

}
</script>

<style scoped>

.main_container {
  float: left;
  width: 100%;
  opacity: 0;
  animation: ani 3s forwards;
}

@keyframes ani {
  0% {opacity: 0;}
  100% {opacity: 1;}
}


.search_title {
  float: left;
  width: 100%;
  text-align: center;
  margin-top: 7%;
}

.search_title img {
  width: 100px;
  height: 100px;
}


div {
  display: block;
}

body {
  font-family: "Lato", sans-serif;
}

.background_about_us {
  position: fixed;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -10000;
  top: 0;
}

.title_text h2 {
  font-size: 44px;
  color: #75b5fe;
  font-weight: 600;
  line-height: 46px;
  letter-spacing: .5px;
  font-family: Lato-hairline;


}

.title_text h3 {
  font-size: 40px;
  color: #cca8d6;
  line-height: 54px;
  margin-top: 9px;
  letter-spacing: .5px;
  font-weight: 600;
  font-family: Lato-hairline;
}

.main_info {
  text-align: center;
  font-size: 0;
  float: left;
  width: 100%;
  padding-top: 100px;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: stretch;
  align-items: stretch;
  -ms-flex-pack: center;
  justify-content: center;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;

}

.gaming-box {
  display: inline-block;
  width: 33.10%;
  padding: 0 25px;
  margin-bottom: 40px;
  vertical-align: top;
  -ms-flex-preferred-size: 33.10%;
  flex-basis: 25.10%;
}

.pictures_class {
  width: 70px;
  height: 70px;
}


@font-face {
  font-family: "Lato";
  src: local("Lato"), url("../assets/font/Lato2/latolight.woff") format("woff");
}

@font-face {
  font-family: "Lato-hairline";
  src: local("Lato"), url("../assets/font/Lato2/latohairline.woff") format("woff");
}

.gaming-box h3 {
  font-size: 22px;
  color: #75b5fe;
  margin-top: 5px;
  letter-spacing: .1px;
  font-weight: 600;
  font-family: Lato-hairline;
}

.gaming-box p {
  font-size: 16px;
  color: #f1f1f1;
  letter-spacing: .1px;
  font-weight: 600;
  font-family: Lato-hairline;

}

</style>
