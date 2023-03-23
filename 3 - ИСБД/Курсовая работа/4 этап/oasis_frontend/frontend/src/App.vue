<template>
  <router-view/>
  <!-- Шапка -->
  <div v-if="this.jwt_token === undefined || this.jwt_token===null || this.jwt_token===''">
    <Header/>
  </div>
  <div v-else-if="this.role==='ROLE_DEV'">
    <Header_After_Auth_Dev/>
  </div>
  <div v-else>
    <Header_After_Auth/>
  </div>

  <!-- Конец шапки -->

</template>


<script>
import Header from "@/components/Header";
import Header_After_Auth from "@/components/Header_After_Auth";
import Header_After_Auth_Dev from "@/components/Header_After_Auth_Dev";
import "@fontsource/montserrat"
export default {
  name: 'App',

  components: {
    Header,
    Header_After_Auth,
    Header_After_Auth_Dev
  },

  data() {
    return {
      jwt_token: undefined,
      role: undefined
    }
  },
  created() {
    this.jwt_token = localStorage.getItem('jwt');
    this.role = localStorage.getItem('role')
  },



}

</script>

<style scoped>


</style>
