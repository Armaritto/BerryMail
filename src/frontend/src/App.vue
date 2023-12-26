<template>
  <div>
    <router-view ></router-view>
<!--    <compose></compose>-->
     <TestComposeEmailArmia myEmail="armia404@berry.com"></TestComposeEmailArmia>

  </div>
  <!-- <div>
    <router-view name="loginRouter"></router-view>
  </div> -->
</template>
<script>
import TestComposeEmailArmia from './components/TestComposeEmailArmia.vue'
export default {
  name:'App',
  components:{
    TestComposeEmailArmia
  },
  mounted(){
    console.log(this.$router.currentRoute.path)
  }
}
</script>
<style>

</style>
