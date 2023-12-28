<template>
  <div>
    <table>
      <tr>
        <td>
            <div class="left"><SideBar :folderName="folder" :clientEmail="clientEmail"></SideBar></div>
        </td>
        <td>
          <div class="middle"><router-view :isSearching="isSearching" :searchData="searchData" @itemPressed="handleItemPressed" @folderChanged="handleFolderChanged"> </router-view></div>
          <div class="right" v-if="this.isCompose === true" ><TestComposeEmailArmia :clientEmail="clientEmail"></TestComposeEmailArmia></div>
          <div class="right" v-if="this.isCompose === false && this.id !== null"><Mail :id="id"></Mail></div>
          <div class="search" v-if="this.isSearch === true"><TestSearchArmia  @searchEvent="handleSearchEvent()" :clientEmail="clientEmail" :currentFolder="folder"></TestSearchArmia></div>
          <div class="contact" v-if="this.isContact === true"><ContactMenu :clientEmail="clientEmail"></ContactMenu></div>
        </td>
      </tr>
    </table>
  </div>
  <div class="float" @click="handleMain">
    <div class="my-float">
      <lord-icon
          src="https://cdn.lordicon.com/wuvorxbv.json"
          trigger="hover"
          stroke="bold"
          colors="primary:#ffffff,secondary:#ffffff"
          style="width:40px;height:40px">
      </lord-icon>
    </div>
  </div>
  <div class="float2" @click="handleSearch">
    <div class="my-float2">
      <lord-icon
          src="https://cdn.lordicon.com/unukghxb.json"
          trigger="hover"
          stroke="bold"
          colors="primary:#ffffff,secondary:#ffffff"
          style="width:40px;height:40px">
      </lord-icon>
    </div>
  </div>
  <div class="float3" @click="handleContact">
    <div class="my-float3">
      <lord-icon
          src="https://cdn.lordicon.com/kthelypq.json"
          trigger="hover"
          colors="primary:#ffffff"
          style="width:40px;height:40px">
      </lord-icon>
    </div>
  </div>
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
import TestSearchArmia from "@/components/TestSearchArmia.vue";
import ContactMenu from "@/components/ContactMenu.vue";
export default {
  name: 'Main',
  props:['clientEmail'],
  data(){
    return{
      searchData:[],
      isSearching: false,
      folder:'inbox',
      id: null,
      myEmail:"armia404",
      isCompose: false,
      isSearch: false,
      isContact: false,
      handleMain(){
        if(this.isCompose){
          this.isCompose = false
        }else{
          this.isCompose = true
        }
      },
      handleSearch() {
        if(this.isSearch){
          this.isSearch = false
        }else{
          this.isSearch = true
        }
      },
      handleContact() {
        if(this.isContact){
          this.isContact = false
        }else{
          this.isContact = true
        }
      },
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
    ContextMenuItem,
    TestSearchArmia,
    ContactMenu
  },methods:{
    handleItemPressed(id){
      this.id = id
    },
    handleFolderChanged(folderName){
      this.folder = folderName
      console.log(folderName)
    },handleSearchEvent(data){
      this.searchData = data;
      this.isSearching = true;
    }
  },
  mounted(){

  }
}
</script>
<style>
  div.search{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    //background: rgba(0, 0, 0, 0.4) no-repeat bottom;
    //background: rgba(230, 235, 240, .4) no-repeat bottom;
    backdrop-filter: blur(18px);
    //border: 1px solid #ccc;
  }
  div.contact{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    //background: rgba(0, 0, 0, 0.4) no-repeat bottom;
    //background: rgba(230, 235, 240, .4) no-repeat bottom;
    backdrop-filter: blur(18px);
    //border: 1px solid #ccc;
  }

div.left {
  display: flex;
  height: 100vh;
  position: fixed;
  left: 0;
  top: 0;
  overflow-y: auto;
  //background: rgba(0, 0, 0, 0.4) no-repeat bottom;
  background: rgba(230, 235, 240, .4) no-repeat bottom;
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
    margin-left: 327px;
    /* background: #601cb1; */
    height: 87vh;
    display: flex;
    justify-content: center;
    text-align: center;
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
  margin-top:10px;
  margin-right: 2px;
}
.my-float:hover{
  cursor: pointer;
}
.float2{
  position:fixed;
  width:60px;
  height:60px;
  bottom:40px;
  right:150px;
  background-image: linear-gradient(to left, #2765c5, #6e21cc);
  color:#FFF;
  border-radius:50px;
  text-align:center;
}
.my-float2{
  margin-top:10px;
  margin-right: 2px;
}
.my-float2:hover{
  cursor: pointer;
}
  .float3{
    position:fixed;
    width:60px;
    height:60px;
    bottom:40px;
    right:260px;
    background-image: linear-gradient(to left, #2765c5, #6e21cc);
    color:#FFF;
    border-radius:50px;
    text-align:center;
  }
  .my-float3{
    margin-top:10px;
    margin-right: 2px;
  }
  .my-float3:hover{
    cursor: pointer;
  }
</style>
