<template>
  <div>
    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Glass morphism </title>
    </head>
    <body>
    <div class="background">
      <div class="shape"></div>
      <div class="shape"></div>
    </div>
    </body>
  </div>
</template>


<script>
export default{
  name: 'main',
  props: ['clientEmail'],
  data(){
    return{

    }
  }
}
</script>

<style scoped>
body{
  background-color: #080710;
}

.background{
  width: 430px;
  height: 520px;
  position: absolute;
  transform: translate(-50%,-50%);
  left: 50%;
  top: 50%;
}
.background .shape{
  height: 200px;
  width: 200px;
  position: absolute;
  border-radius: 50%;
}


</style>
