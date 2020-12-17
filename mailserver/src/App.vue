<template>
  <div id="app">
    <Home></Home>
    <div id="nav">
      <router-link to="/">Login</router-link> |
      <router-link to="/register">Register</router-link>
    </div>
    <router-view/>
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

</template>

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
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
  background: crimson;
}
</style>
