<template>
    <div class="email">
        <h2>{{email.subject}}</h2>
        <p class="from">from: {{email.from}}</p>
        <p class="date">{{email.date}}</p>
        <p class="content">{{email.content}}</p>
    </div>
</template>
<script>
export default {
    data(){
        return{
            email:{
                subject:"Test email",
                from:"someone@berry.com",
                content:"hi somebody, I am someone",
                date:"19 dec"
            }
        }
    }
}
</script>
<style scoped>
div.email {
    font-family: Arial, Helvetica, sans-serif;
    font-size: small;
    height: 700px;
    width: 1000px;
    background: #f4f4f4;
}
textarea{
    align-self: center;
}
.content{
    font-size: medium;
}
    
</style>