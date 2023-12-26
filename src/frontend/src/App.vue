<<<<<<< Updated upstream
<template>
  <main></main>
=======
<template lang="">
  <div>
    <Main></Main>
    <router-view ></router-view>
  </div>
  <!-- <div>
    <router-view name="loginRouter"></router-view>
  </div> -->
>>>>>>> Stashed changes
</template>

<script>
import main from "@/components/main.vue";
export default {
<<<<<<< Updated upstream
  name: 'App',
  components: {
    main
=======
  name:'App',
  components:{
    Main,
  },
  mounted(){
    console.log(this.$router.currentRoute.path)
>>>>>>> Stashed changes
  }
}
</script>
<style>

</style>
