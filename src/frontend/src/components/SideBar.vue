<template>
<div>
    <body>
<div class="container">
<nav>
    <hr class="line">
    <ul class="list">
        <li class="active" v-for="folder in folders">
            <i class="fa-solid fa-home"></i>
            <router-link :to="{path: '/' + clientEmail + '/folder/' + folder}"><span>{{folder}}</span></router-link>
        </li>
        <li class="active" v-for="folder in customFolders">
            <i class="fa-solid fa-home"></i>
            <router-link :to="{path: '/' + clientEmail + '/folder/' + folder}"><span>{{folder}}</span></router-link>
        </li>
        <!-- <li>
            <i  class="fa-solid fa-user"></i>
            <router-link :to="{path: '/' + clientEmail + '/folder/sent'}"><span>Sent</span></router-link>
        </li>
        <li>
            <i class="fa-solid fa-box"></i>
            <router-link :to="{path: '/' + clientEmail +'/folder/draft'}"><span>Draft</span></router-link>
        </li>
        <li>
            <i class="fa-solid fa-chart-simple"></i>
            <router-link :to="{path: '/' + clientEmail +'/folder/trash'}"><span>Trash</span></router-link>
        </li> -->
        <!-- <li>
            <i class="fa-solid fa-phone"></i>
            <span>Support</span>
        </li>

        <hr class="line">

        <li>
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Logout</span>
        </li> -->

    </ul>
    <button> +Folder </button>
</nav>

</div>

</body>
</div>
</template>
<script>
export default {
props:['clientEmail'],
data(){
    return{
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
        .then(data => this.customFolders = data)

            }
    }
},
methods:{
    handle(){
        this.h()
    }

},
mounted(){
    this.fetchFolderList()
    console.log(this.clientEmail)
}
}
</script>
<style scoped>
* {
margin: 0;
padding: 0;
border: 0;
list-style: none;
box-sizing: border-box;
text-decoration: none;

}

body {
height: 100vh;
background: linear-gradient(
    #9fc0cf,
    #e5edf1
);
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
height: min-content;
padding: 16px;
border-radius: 12px;
backdrop-filter: blur(18px);
background: rgba(230, 235, 240, .7);
border: 2px solid rgba(230, 235, 240, .7);
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
}

nav .brand p {
color: #505257;
font-weight: 500;
font-style: italic;
text-align: left;
line-height: 22px;
margin: 0 0 0 12px;
}

nav .brand p span {
color: #3c3e43;
display: block;
font-size: 26px;
font-style: normal;
}

nav .line {
width: 100%;
height: 2px;
margin: 32px 0;
border-radius: 30%;
background: #87a3af;
}

nav .list {
width: 100%;
}

nav .list li, .router-link-exact-active{
display: flex;
margin: 12px 0;
padding: 20px;
cursor: pointer;
border-radius: 8px;
/* transition: .1s ease-in-out; */
}

nav .list li {
background: rgba(230, 235, 240, .4);
}

.router-link-exact-active{
background: #3681f6;
}

nav .list li.active span,.router-link-exact-active {
color: #fff;
}

nav .list li.active i .router-link-exact-active{
background: none;
-webkit-text-fill-color: unset;
color: #e6ebf0;
}

nav .list i {
font-size: 20px;
background: linear-gradient(
    -135deg,
    #3c3e43,
    #838487
);

-webkit-text-fill-color: transparent;
}

nav .list span {
color: #505257;
font-size: 25px;
font-weight: 500;
margin: 0 0 0 12px;
}

nav .brand p,
nav .list span {
opacity: 0;
pointer-events: none;
transform: translateX(20px);
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
</style>
