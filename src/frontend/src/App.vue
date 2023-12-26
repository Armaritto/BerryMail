<template lang="">
  <div>
    <router-view ></router-view>
  </div>
  <!-- <div>
    <router-view name="loginRouter"></router-view>
  </div> -->
</template>
<script>
import Main from "@/components/Main.vue";


export default {
  name:'App',
  components:{
    Main,
    
  },
  mounted(){
    console.log(this.$router.currentRoute.path)
  }
}
</script>
<style lang="">
  
</style>