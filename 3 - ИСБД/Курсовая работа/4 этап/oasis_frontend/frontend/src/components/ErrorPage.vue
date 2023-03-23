<template>
  <div id='content' style="text-align: center">
    <h1>{{ errorCode }}</h1>
    <h2>{{ errorMessage }}</h2>
    <router-link to='/auth' id='button'>Return to the start page</router-link>
  </div>
</template>

<script>
export default {
  name: "ErrorPage",
  props: {
    errorCode: String,
    errorMessage: String
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
  font-size: 8em;
  margin: 0;
}
h2 {
  margin-top: 35px;
  text-align: center;
  font-weight: 200;
  text-transform: uppercase;
  font-family: Poppins-Regular;
  letter-spacing: 3px;
}
#button{
  font-family: Poppins-Regular;
  color: white;
  letter-spacing: 1px;
  text-decoration: none;
  background-color: black;
  border: 2px solid black;
  border-radius: 6px;
}

</style>
