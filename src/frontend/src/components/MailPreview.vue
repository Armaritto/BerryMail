<template>
    <div>
        <p>{{emailMeta.subject}}</p>
        <!-- <p class="from">from: {{emailMeta.from}}</p>
        <p class="date">{{emailMeta.date}}</p>
        <p class="content">{{emailMeta.contentPreview}}</p> -->
    </div>
</template>
<script>
export default {
    data(){
        return{
            emailMeta:{
                from:"someone",
                subject:"Test email",
                date:"19 dec",
                contentPreview:"hi sombody I am..."
            }
        }
    }
}
</script>
<style scoped>
   div{
    height: 50px;
    width: 700px;
    font-size: small;
    background: #f4f4f4;
    font-family: arial;
    /* margin: 5px; */
    border-radius: 15px;
    
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