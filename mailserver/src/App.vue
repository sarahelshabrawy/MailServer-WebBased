<template>
  <div id="app">
    <Home></Home>
  </div>
</template>

<script>
import Home from './components/Home.vue'
export default {
  name: 'App',
  components: {
    Home
  }
}
</script>

<style>
#body, html {
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100%;
  overflow: hidden;
}
#app {
  margin: 0;
  padding: 0;
}
</style>
