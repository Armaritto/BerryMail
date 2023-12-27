<template>
  <div>
    <table>
      <tr>
        <td>
            <div class="left"><SideBar :clientEmail="clientEmail"></SideBar></div>
        </td>
        <td>
          <div class="middle"><router-view @itemPressed="handleItemPressed"> </router-view></div>
          <div class="right"><TestComposeEmailArmia :clientEmail="clientEmail"></TestComposeEmailArmia></div>
          <div class="right"><Mail :id="id"></Mail></div>
        </td>
      </tr>
    </table>
  </div>
  <a href="#" class="float">
    <div class="my-float">
      <lord-icon
          src="https://cdn.lordicon.com/wuvorxbv.json"
          trigger="hover"
          stroke="bold"
          colors="primary:#ffffff,secondary:#ffffff"
          style="width:40px;height:40px">
      </lord-icon>
    </div>
  </a>
</template>

<script>
import MailEditor from "@/components/MailEditor.vue";
import Mail from "@/components/Mail.vue";
import MailPreview from "@/components/MailPreview.vue";
import MailFolder from "@/components/MailFolder.vue";
import SideBar from "@/components/SideBar.vue";
import TestComposeEmailArmia from "@/components/TestComposeEmailArmia.vue";
import ContextMenu from "@/components/ContextMenu";
import ContextMenuItem from "@/components/ContextMenuItem";

export default {
  name: 'Main',
  props:['clientEmail'],
  data(){
    return{
      id: null,
      myEmail:"armia404",
    }
  },
  components: {
    MailEditor,
    Mail,
    MailPreview,
    MailFolder,
    SideBar,
    TestComposeEmailArmia,
    ContextMenu,
    ContextMenuItem
  },methods:{
    handleItemPressed(id){
      this.id = id
    }
  },
  mounted(){

  }
}
</script>
<style>
div.left {
  display: flex;
  height: 100vh;
  position: fixed;
  left: 0;
  top: 0;
  overflow-y: auto;
  background: rgba(0, 0, 0, 0.4) no-repeat bottom;
  //background: rgba(230, 235, 240, .4) no-repeat bottom;
  backdrop-filter: blur(18px);
  //border: 1px solid #ccc;
}
/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  background: rgba(230, 235, 240, .05);
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: rgba(230, 235, 240, .2);
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: rgba(63, 36, 131, 0.44);
}
  div.middle{
    float: left;
    margin-left: 350px;
    /* background: #601cb1; */
    display: flex;
    justify-content: center;
    border-radius: 10px;
    background: rgba(44, 19, 66, 0.34);
    backdrop-filter: blur(5px);
    border: 2px solid  rgba(44, 19, 66, 0.13);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    /* align-items: center; */
    /* height: 100vh; */
  }
  div.right{
    float: left;
    margin-left: 4rem;
    min-width: 900px;
  }
.float{
  position:fixed;
  width:60px;
  height:60px;
  bottom:40px;
  right:40px;
  background-image: linear-gradient(to left, #2765c5, #6e21cc);
  color:#FFF;
  border-radius:50px;
  text-align:center;

}

.my-float{
  margin-top:12px;
  margin-right: 2px;
}
</style>
