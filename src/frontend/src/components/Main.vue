<template>

  <div>
    <table>
      <tr>
        <td>
          <VueStickyElement>
            <div class="left"><SideBar :clientEmail="clientEmail"></SideBar></div>
          </VueStickyElement>
        </td>
        <td>
          <div class="middle"><router-view @itemPressed="handleItemPressed"> </router-view></div>
<!--          <div class="right"><TestComposeEmailArmia :clientEmail="clientEmail"></TestComposeEmailArmia></div>-->
          <div class="right"><Mail :id="id"></Mail></div>
        </td>
      </tr>
    </table>
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
  div.left{
    position: sticky;
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
</style>
