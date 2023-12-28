<template>
  <div style="">
    <router-view ></router-view>
    <!-- <compose></compose> -->
<!--     <testComposeEmailArmia></testComposeEmailArmia>-->
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
<style>
body{
  margin: 0;
  padding: 0;
  background: url('bg.jpg') no-repeat center center fixed;
  background-size: cover;
}
</style>
