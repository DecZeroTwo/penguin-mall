<template>
  <div id="app">
    <INDEX></INDEX>
  </div>
</template>

<script>
import INDEX from './views/index.vue'

export default {
  name: 'App',
  components: {
    INDEX
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
</style>
