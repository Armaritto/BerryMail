<template lang="">
  <div>
    <router-view ></router-view>
    <!-- <testComposeEmailArmia></testComposeEmailArmia> -->
  </div>
  <!-- <div>
    <router-view name="loginRouter"></router-view>
  </div> -->
</template>
<script>

export default {
  name:'App',
  components:{
    
  },
  mounted(){
    console.log(this.$router.currentRoute.path)
  }
}
</script>
<style lang="">
  
</style>