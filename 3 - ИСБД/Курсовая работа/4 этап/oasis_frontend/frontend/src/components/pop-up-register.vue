<template>
  <div class="popup">
    <div class="popup-inner">
      <slot/>
      <svg @click="TogglePopup" class="popup-close" aria-hidden="true" width="36.08" height="36.08"
           viewBox="-1.6 -1.6 19.2 19.2" fill="#595959"
           style="font-size: 2.88em;">
        <path fill-rule="evenodd"
              d="M13.854 2.146a.5.5 0 010 .708l-11 11a.5.5 0 01-.708-.708l11-11a.5.5 0 01.708 0z"></path>
        <path fill-rule="evenodd"
              d="M2.146 2.146a.5.5 0 000 .708l11 11a.5.5 0 00.708-.708l-11-11a.5.5 0 00-.708 0z"></path>
      </svg>
    </div>
  </div>
</template>

<script>
export default {
  name: "pop-up-register",

  props: ['TogglePopup']
}
</script>

<style scoped>
.popup {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 99;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.popup-inner {
  border-radius: 7px;
  position: relative;
  background-color: #dedede;
  padding: 32px;
  display: block;
  overflow: hidden;
  width: 390px;
  height: 550px;

}

.popup-close {
  position: absolute;
  top: 0;
  right: 0;
  margin-left: 20%;
  cursor: pointer;
}


</style>
