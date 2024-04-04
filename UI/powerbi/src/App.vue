<template>
  <img src="./assets/img.png" width="150" height="200" alt="logo">
  <div>
    <h1> WELCOME TO MY APP!!</h1>
  </div>
  <PowerBIReport msg="Welcome to Your Vue.js App"/>
</template>

<script>
  import PowerBIReport from './components/PowerBIReport.vue'

export default {
  name: 'App',
  components: {
    PowerBIReport
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
body{
  background-color: black;
}
h1{
  color: #0056b3;
}
</style>
