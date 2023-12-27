<template>
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
  <div style="display: flex; justify-content: center;position: absolute;top: 25%; left: 50%">
    <form>
      <label for="sender">Sender</label>
      <input type="text" placeholder="example@berry.com" id="sender">
      <label for="receiver">Receiver</label>
      <input type="text" placeholder="example@berry.com" id="receiver">
      <label for="sender">Subject</label>
      <input type="text" placeholder="Type Subject" id="subject">
      <label for="sender">Date</label>
      <div class="date_options">
        <input type="datetime-local" placeholder="Type Subject" id="subject" style="width: 50%">
        <span class="date" :class="{ 'true': this.isBefore.value, 'false': !this.isBefore.value }" @click="handleTime(this.isBefore)"  >Before</span>
        <span class="date" :class="{ 'true': this.isOn.value , 'false': !this.isOn.value  }"  @click="handleTime(this.isOn)"  >On</span>
        <span class="date" :class="{ 'true': this.isAfter.value, 'false': !this.isAfter.value }"  @click="handleTime(this.isAfter)"  >After</span>
      </div>
      <label for="sender" >Body</label>
      <input type="text" placeholder="Body" id="username">
      <label for="sender">Attachment</label>
      <input type="text" placeholder="Enter type of Attachment" id="attachment">
      <button>Search</button>
    </form>
  </div>
  </body>
</template>

<script>
export default {
  name: 'main',
  props: {
    msg: String
  },
  data(){
    return{
      isBefore:{value: false} ,
      isOn: {value: true},
      isAfter: {value: false}
    }
  },
  methods:{
    handleTime(interval){
      this.isBefore.value = false
      this.isOn.value = false
      this.isAfter.value = false
      interval.value = true
    }
  }
}
</script>


<style>
.date_options{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}
.date.true {
  background-color: #521bb4;
  color: white;
}

.date.false {
  background-color: rgba(255, 255, 255, 0.44);
  color: white;
}
.date{
  background-color: rgba(255,255,255,0.07);
  margin-top: 0px;
  margin-bottom: 7px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  width: 15%;
  padding: 2px;
  font-size: 8px;
  color: #ffffff;
  font-weight: 20;
}
.date:hover{
  cursor: pointer;
}
*,
*:before,
*:after{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
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

form{
  height: 520px;
  width: 300px;

  background-color: rgba(44, 19, 66, 0.34);

  position: absolute;
  border-radius: 10px;
  backdrop-filter: blur(10px);
  border: 2px solid  rgba(44, 19, 66, 0.13);
  box-shadow: 0 0 40px rgba(8,7,16,0.6);
  padding: 30px 20px;
}

form *{
  font-family: 'Poppins',sans-serif;
  color: #ffffff;
  letter-spacing: 0.5px;
  outline: none;
  border: none;
}
label{
  display: block;
  margin-top: 1px;
  font-size: 15px;
  font-weight: 500;
}
input{
  display: block;
  height: 30px;
  width: 100%;
  background-color: rgba(255,255,255,0.07);
  border-radius: 3px;
  padding: 0 10px;
  font-size: 12px;
  font-weight: 300;
  margin-bottom: 10px;
}
::placeholder{
  color: #e5e5e5;
}
button{
  margin-top: 10px;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.44);
  color: #080710;
  padding: 7px 0;
  font-size: 16px;
  font-weight: 600;
  border-radius: 5px;
  cursor: pointer;
}
button:hover {
  background-color: #521bb4;
  color: #080710;
}

</style>
