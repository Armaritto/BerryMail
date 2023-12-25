<template>
  
  <div class="left"><SideBar></SideBar></div>
  <div class="middle"><router-view></router-view></div>
  <div class="right"><MailEditor :myEmail="myEmail"></MailEditor></div>

</template>

<script>
// import main from "@/components/main.vue";
import MailEditor from "@/components/MailEditor.vue";
import Mail from "@/components/Mail.vue";
import MailPreview from "@/components/MailPreview.vue";
import MailFolder from "@/components/MailFolder.vue";
import SideBar from "@/components/SideBar.vue";

export default {
  name: 'App',
  data(){
    return{
      myEmail:"user1@berry.com",
      email: {
        id: "876370",
        from: "user1@berry.com",
        to: "user2@berry.com",
        subject: "subject",
        body: "body",
        dateNtime: "2023-12-22T22:42:04.595+00:00",
        priority: "",
        attachment: ""
    }
    }
  },
  components: {
    // main,
    MailEditor,
    Mail,
    MailPreview,
    MailFolder,
    SideBar
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
