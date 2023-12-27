<template>
  <div class="container">
    <table>
      <tr style="color: white">
        <h3>
          Berry Mail
        </h3>
      </tr>
      <tr v-for="folder in folders">
        <router-link :to="{path: '/' + clientEmail + '/folder/' + folder}" style="text-decoration: none; color: #ffffff">
          <div class="folder" style="display: flex; flex-direction: row; align-items: center">
            <div v-if="folder === 'inbox'">
              <lord-icon
                  src="https://cdn.lordicon.com/xtnsvhie.json"
                  trigger="hover"
                  colors="primary:#ffffff"
                  style="width:50px;height:50px">
              </lord-icon>
            </div>
            <div v-else-if="folder === 'sent'">
              <lord-icon
                  src="https://cdn.lordicon.com/aycieyht.json"
                  trigger="hover"
                  stroke="bold"
                  colors="primary:#ffffff,secondary:#ffffff"
                  style="width:50px;height:50px">
              </lord-icon>
            </div>
            <div v-else-if="folder === 'trash'">
              <lord-icon
                  src="https://cdn.lordicon.com/wpyrrmcq.json"
                  trigger="hover"
                  colors="primary:#ffffff"
                  style="width:50px;height:50px">
              </lord-icon>
            </div>
            <div v-else-if="folder === 'draft'">
              <lord-icon
                  src="https://cdn.lordicon.com/ghhwiltn.json"
                  trigger="hover"
                  stroke="bold"
                  colors="primary:#ffffff,secondary:#ffffff"
                  style="width:50px;height:50px">
              </lord-icon>
            </div>
            <div style="margin-left: 30px; margin-bottom: 5px">
              {{folder}}
            </div>
          </div>
        </router-link>
      </tr>
      <tr style="justify-content: center">
        <div class="preview" style="width: 110%" @click="addFolder">
          <div style="margin-left: 10px">
            <lord-icon
                src="https://cdn.lordicon.com/hqymfzvj.json"
                trigger="hover"
                colors="primary:#ffffff"
                style="width:50px;height:50px">
            </lord-icon>
          </div>
          <div style="margin-left: 30px">
            Create Folder
          </div>
        </div>
      </tr>
      <tr v-for="folder in customFolders" :key="folder" >
        <router-link :to="{path: '/' + clientEmail + '/folder/' + folder}" style="text-decoration: none;">
          <SideBarItem @rename="handleRename" :clientEmail="clientEmail" :folderName="folder" class="folder">
          </SideBarItem>
        </router-link>
      </tr>
    </table>
  </div>
</template>
<!--<template>-->
<!--<div class="container">-->
<!--    <body>-->

<!--<nav>-->
<!--    <hr class="line">-->
<!--    <ul class="list">-->
<!--        <div class="active" v-for="folder in folders">-->
<!--            <div>-->
<!--              <div v-if="folder === 'inbox'">-->
<!--                <lord-icon-->
<!--                    src="https://cdn.lordicon.com/xtnsvhie.json"-->
<!--                    trigger="hover"-->
<!--                    colors="primary:#ffffff"-->
<!--                    style="width:50px;height:50px">-->
<!--                </lord-icon>-->
<!--              </div>-->
<!--              <div v-if="folder === 'sent'">-->
<!--                <lord-icon-->
<!--                    src="https://cdn.lordicon.com/aycieyht.json"-->
<!--                    trigger="hover"-->
<!--                    stroke="bold"-->
<!--                    colors="primary:#ffffff,secondary:#ffffff"-->
<!--                    style="width:50px;height:50px">-->
<!--                </lord-icon>-->
<!--              </div>-->
<!--              <div v-if="folder === 'trash'">-->
<!--                <lord-icon-->
<!--                    src="https://cdn.lordicon.com/wpyrrmcq.json"-->
<!--                    trigger="hover"-->
<!--                    colors="primary:#ffffff"-->
<!--                    style="width:50px;height:50px">-->
<!--                </lord-icon>-->
<!--              </div>-->
<!--              <div v-if="folder === 'draft'">-->
<!--                <lord-icon-->
<!--                    src="https://cdn.lordicon.com/ghhwiltn.json"-->
<!--                    trigger="hover"-->
<!--                    stroke="bold"-->
<!--                    colors="primary:#ffffff,secondary:#ffffff"-->
<!--                    style="width:50px;height:50px">-->
<!--                </lord-icon>-->
<!--              </div>-->

<!--            <router-link :to="{path: '/' + clientEmail + '/folder/' + folder}"><span>{{folder}}</span></router-link>-->
<!--            </div>-->
<!--        </div>-->
<!--        <div class="active" v-for="folder in customFolders">-->
<!--            <div>-->
<!--              <div>-->
<!--                <lord-icon-->
<!--                    src="https://cdn.lordicon.com/jkzgajyr.json"-->
<!--                    trigger="hover"-->
<!--                    colors="primary:#ffffff"-->
<!--                    style="width:50px;height:50px">-->
<!--                </lord-icon>-->
<!--              </div>-->
<!--            </div>-->
<!--            <router-link :to="{path: '/' + clientEmail + '/folder/' + folder}">-->
<!--              <SideBarItem @rename="handleRename" :clientEmail="clientEmail" :folderName="folder" style="color: white; font-size: 26px"></SideBarItem>-->
<!--            </router-link>-->
<!--        </div>-->

<!--      <button @click="addFolder">-->
<!--        <lord-icon-->
<!--            src="https://cdn.lordicon.com/hqymfzvj.json"-->
<!--            trigger="hover"-->
<!--            colors="primary:#ffffff"-->
<!--            style="width:50px;height:50px">-->
<!--        </lord-icon>-->
<!--      </button>-->
<!--    </ul>-->
<!--&lt;!&ndash;    <SideBarItem @click="addFolder"> +Folder </SideBarItem>&ndash;&gt;-->
<!--</nav>-->



<!--</body>-->
<!--</div>-->
<!--</template>-->
<script>
import SideBarItem from "@/components/SideBarItem.vue";
export default {
props:['clientEmail'],
data(){
    return{
        newName:'',
        viewMenu: false,
        top: '0px',
        left: '0px',
        h: async function(){
            this.$router.replace({path:'/folder/sent'})
        },
        folders: ["inbox", "sent", "draft", "trash"],
        customFolders: [],

        fetchFolderList: function(){

        const  url = "http://localhost:8080/" + 'customFoldersNames' + "?"

        const params = {
          email: this.clientEmail + "@berry.com",
        }
        const query = new URLSearchParams(params)
        const method = "GET"
        const body = ""

        fetch(url+query, {method: method})
        .then(res => res.json())
        .then(data => {this.customFolders = data; console.log(data); console.log('datadata')})

            }
    }
},
methods:{
    handle(){
        this.h()
    },
  addFolder(){
    swal("Enter Folder Name:", {
      content: "input",
    })
        .then((value) => {
          swal(`Folder Created!`);
          let folderNamesss = '';
          folderNamesss = value;
    const url = "http://localhost:8080/createFolder?"
    const params = {
      email: this.clientEmail + "@berry.com",
      folderName:folderNamesss || "newFolder",
    }
    const query = new URLSearchParams(params)
    const method = "POST"
    const body = ""

    fetch(url+query, {method: method})
        .then(res => {

          res.json();
          if(res.status === 200){
            console.log("success")


          }else {
            console.log("failure")

          }
        });
      }) .then(this.fetchFolderList)
    this.fetchFolderList
  },handleRename(newName){

      const path = '/' + this.clientEmail + '/folder/inbox'
    this.$router.replace({path: path})
    this.fetchFolderList()
    console.log("renamed")
  }

},components:{
    SideBarItem
  },
mounted(){
    this.fetchFolderList()
    console.log(this.clientEmail)
}
}
</script>
<style scoped>
.buttonAdd{

}
.folder{
  padding: 10px;
  width: 100%;
  background: rgba(230, 235, 240, .15) no-repeat bottom;
  border-radius: 10px;
  outline: none;
  cursor: pointer;
  margin-left: 10px;
  margin-top: 10px;
}
.folder:hover{
  background: rgba(230, 235, 240, .3) no-repeat bottom;
}
.container{
  display: flex;
  justify-content: center;
  width: 330px;
  height: 100vh;
  padding: 16px;
  border-radius: 0;
  background: transparent;
  //background: rgba(40, 183, 185, 0.6);
  box-shadow: 0 0 30px rgba(0, 0, 0, .05);
  transition: .6s cubic-bezier(.8, .5, .5, .8);
}
.preview{
  margin-top: 40px;
  margin-bottom: 40px;
  width: 100%;
  font-size: small;
  background-image: linear-gradient(to left, #6e21cc, #2765c5, #2765c5, #6e21cc);
  background-size: 300%;
  background-position: right;
  border: 0;
  border-radius: 10px;
  transition: 1s background-position ease-in-out;
  color: #ffffff;
  display: flex;
  jsutify-content: center;
  align-items: center;
}
.preview:hover {
  background-position: left;
  color: #fff;
  cursor: pointer;
}
/*
.active{
  background: rgba(230, 235, 240, .05);
    border-radius: 8px;
    margin: 24px 0;
    padding: 20px;
    cursor: pointer;
    height: 50px;
    transition: .1s ease-in-out;
}
.folders{
    background-color: black;
    font-size: 26px;
}
* {
margin: 0;
padding: 0;
border: 0;
list-style: none;
box-sizing: border-box;
text-decoration: none;

}

body {
  display: flex;
//background: linear-gradient(
//    //#9fc0cf,
//    //#300d8c
//
//);
font-family: 'Roboto', sans-serif;
}

.container {
width: 100%;
height: 100%;
display: flex;

justify-content: left;
background-repeat: no-repeat;
background-position: bottom;
}

.container nav {
width: 100px;
height: 100vh;
padding: 16px;
border-radius: 0;
backdrop-filter: blur(18px);
background: rgba(230, 235, 240, .05);
//background-color: #980697;
box-shadow: 0 0 30px rgba(0, 0, 0, .05);
transition: .6s cubic-bezier(.8, .5, .5, .8);
}

.container nav {
width: 320px;
}

nav .brand {
width: 100%;
display: flex;
align-items: center;
}

nav .brand img {
width: 65px;
border-radius: 50%;
border: 4px solid rgba(230, 235, 240, .8);
  height: 50px;
}

nav .brand p {
color: #505257;
font-weight: 500;
font-style: italic;
text-align: left;
line-height: 22px;
margin: 0 0 0 12px;
  height: 50px;
}

nav .brand p span {
color: #3c3e43;
display: block;
font-size: 26px;
font-style: normal;
  height: 50px;
}

nav .line {
width: 100%;
height: 2px;
margin: 32px 0;
border-radius: 30%;
  height: 50px;
background: #87a3af;
}

nav .list {
width: 100%;height: 50px;
}

nav .list li, .router-link-exact-active{
display: flex;
margin: 12px 0;
padding: 20px;
cursor: pointer;
border-radius: 8px;
  height: 50px;
/* transition: .1s ease-in-out; */
/*
}

nav .list li {
  background: rgba(230, 235, 240, .4);
  height: 50px;
}

.router-link-exact-active{
  background:rgba(230, 235, 240, .7);
}

nav .list li.active span,.router-link-exact-active {
color: #fff;
  height: 50px;
}

nav .list li.active i .router-link-exact-active{
background: none;
-webkit-text-fill-color: unset;
color: #e6ebf0;
  height: 50px;
}

nav .list i {
font-size: 20px;
background: linear-gradient(
    -135deg,
    #3c3e43,
    #838487
);

-webkit-text-fill-color: transparent;
  height: 50px;
}

nav .list span {
color: #505257;
font-size: 25px;
font-weight: 500;
margin: 0 0 0 12px;
  height: 50px;
}

nav .brand p,
nav .list span {
opacity: 0;
pointer-events: none;
transform: translateX(20px);
  height: 50px;
}

@keyframes fadeIn {
to {
    opacity: 1;
    pointer-events: unset;
    transform: translateX(0);
}
}


nav .list span {
animation: fadeIn .4s ease-out forwards;
}
/*
nav:hover .list li:nth-child(1) span {
animation-delay: .5s;
}

nav:hover .list li:nth-child(2) span {
animation-delay: .6s;
}

nav:hover .list li:nth-child(3) span {
animation-delay: .7s;
}

nav:hover .list li:nth-child(4) span {
animation-delay: .8s;
}

nav:hover .list li:nth-child(5) span {
animation-delay: .9s;
}

nav:hover .list li:nth-child(7) span {
animation-delay: 1s;
} */
/*
background-color: #FF3CAC;
background-image: linear-gradient(315deg, #FF3CAC 0%, #784BA0 50%, #2B86C5 100%);

*/
</style>
