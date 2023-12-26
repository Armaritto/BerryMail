<template>
    <div>
        <p>{{emailMeta.subject}}</p>
        <p class="from">from: {{emailMeta.from}}</p>
        <p class="date">{{emailMeta.date}}</p>
        <p class="content">{{emailMeta.contentPreview}}</p>
    </div>
</template>
<script>
export default {
    props:['emailMeta'],
    data(){
        return{
            
        }
    }
}
</script>
<style scoped>
   div{
    height: 60px;
    width: 400px;
    font-size: small;
    background: #f4f4f4;
    font-family: arial;
    border-color: #4f4f4f;
    border-style: solid;
    border-width: 1px;
    /* margin: 5px; */
    /* border-radius: 15px; */
    
   } 
   div:hover {
     background-color: rgba(170, 115, 205, 0.5);
}
    /* .from{
    position: absolute;
    left: 0%;
   }
   .date{
    position: absolute;
    right: 100%;
   } */
</style>