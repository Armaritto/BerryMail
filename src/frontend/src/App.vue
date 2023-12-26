<template>
  <div>
    <router-view ></router-view>
    <!-- <compose></compose> -->
    <!-- <testComposeEmailArmia></testComposeEmailArmia> -->
  </div>
  <!-- <div>
    <router-view name="loginRouter"></router-view>
  </div> -->
</template>
<script>
import compose from './components/compose.vue'
export default {
  name:'App',
  components:{
    compose
  },
  mounted(){
    console.log(this.$router.currentRoute.path)
  }
}
</script>
<style>
  
</style>