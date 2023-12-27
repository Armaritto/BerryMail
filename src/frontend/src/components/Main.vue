<template>

  <div>
  <div class="left"><SideBar :clientEmail="clientEmail"></SideBar></div>
  <div class="middle"><router-view> </router-view></div>
  <div class="right"><TestComposeEmailArmia :clientEmail="clientEmail"></TestComposeEmailArmia></div>
  </div>
  
</template>

<script>
// import main from "@/components/main.vue";
import MailEditor from "@/components/MailEditor.vue";
import Mail from "@/components/Mail.vue";
import MailPreview from "@/components/MailPreview.vue";
import MailFolder from "@/components/MailFolder.vue";
import SideBar from "@/components/SideBar.vue";
import TestComposeEmailArmia from "@/components/TestComposeEmailArmia.vue";

export default {
  name: 'Main',
  props:['clientEmail'],
  data(){
    return{
      myEmail:"armia404",
    }
  },
  components: {
    MailEditor,
    Mail,
    MailPreview,
    MailFolder,
    SideBar,
    TestComposeEmailArmia
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
    margin: auto;
    /* background: #601cb1; */
    display: flex;
    justify-content: center;
    border-radius: 10px;
    background: linear-gradient(to right, #3681f6, #601cb1);
    /* align-items: center; */
    /* height: 100vh; */
  }
  div.right{
    float: left;
    margin: 10px;
  }
</style>
