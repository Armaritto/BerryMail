<template>

<!--  <div>-->
<!--  <div class="left"><SideBar></SideBar></div>-->
<!--  <div class="middle"><router-view> </router-view></div>-->
<!--  <div class="right"><MailEditor :myEmail="myEmail"></MailEditor></div>-->
<!--  </div>-->

</template>

<script>
// import main from "@/components/Main.vue";
import MailEditor from "@/components/MailEditor.vue";
import Mail from "@/components/Mail.vue";
import MailPreview from "@/components/MailPreview.vue";
import MailFolder from "@/components/MailFolder.vue";
import SideBar from "@/components/SideBar.vue";

export default {
  name: 'Main',
  data(){
    return{
      myEmail:"user1@berry.com",
    }
  },
  components: {
    MailEditor,
    Mail,
    MailPreview,
    MailFolder,
    SideBar
  },
  mounted(){

  }
}
</script>
<style>
  div.left{
    float: left;
    margin: 10px;
  }
  div.middle{
    float: left;
    margin: 10px;
  }
  div.right{
    float: left;
    margin: 10px;
  }
</style>
