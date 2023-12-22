<template>
    <div class="email">
        <h2>{{email.subject}}</h2>
        <p class="from">from: {{email.from}}</p>
        <p class="date">{{email.dateNtime}}</p>
        <p class="content">{{email.body}}</p>
    </div>
</template>
<script>
export default {
    props:['email'],
    data(){
        return{

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