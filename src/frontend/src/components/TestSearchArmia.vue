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
      <input type="text" placeholder="example@berry.com" id="sender" v-model="Sender">
      <label for="receiver">Receiver</label>
      <input type="text" placeholder="example@berry.com" id="receiver" v-model="Receiver">
      <label for="sender">Subject</label>
      <input type="text" placeholder="Type Subject" id="subject" v-model="Subject">
      <label for="sender">Date</label>
      <div class="date_options">
        <input type="datetime-local" placeholder="Type Subject" id="subject" style="width: 50%" v-model="Date">
        <span class="date" :class="{ 'true': this.isBefore.value, 'false': !this.isBefore.value }" @click="handleTime(this.isBefore)"  >Before</span>
        <span class="date" :class="{ 'true': this.isOn.value , 'false': !this.isOn.value  }"  @click="handleTime(this.isOn)"  >On</span>
        <span class="date" :class="{ 'true': this.isAfter.value, 'false': !this.isAfter.value }"  @click="handleTime(this.isAfter)"  >After</span>
      </div>
      <label for="sender" @click="console.log(Sender)">Body</label>
      <input type="text" placeholder="Body" id="username" v-model="Body">
      <label for="sender">Attachment</label>
      <input type="text" placeholder="Enter Attachment Name" id="attachment" v-model="Attachment">
      <label for="sender">Criteria</label>
      <span class="criteria" :class="{ 'true': this.isAND.value, 'false': !this.isAND.value }" @click="handleCriteria(this.isAND)"  >And</span>
      <span class="criteria" :class="{ 'true': this.isOR.value , 'false': !this.isOR.value  }"  @click="handleCriteria(this.isOR)"  >Or</span>
      <button type="button" @click="handleSearch">Search</button>
    </form>
  </div>
  </body>
  <MailFolder :emailsList="emails"></MailFolder>
</template>

<script>
import MailFolder from "@/components/MailFolder.vue";
export default {
  name: 'main',
  props: ['clientEmail','currentFolder'],
  data(){
    return{
      emails:[],
      folder:'inbox',
      isBefore:{value: false} ,
      isOn: {value: true},
      isAfter: {value: false},
      Sender:'',
      Receiver:'',
      Subject:'',
      Date:'',
      Body:'',
      criteriaTime:'',
      Attachment:'',
      isAND: {value: true},
      isOR:{value: false},
    }
  },components:{
    MailFolder
  },
  methods:{
    handleTime(interval){
      this.isBefore.value = false
      this.isOn.value = false
      this.isAfter.value = false
      interval.value = true
    },
    handleCriteria(criteria){
      this.isAND.value = false
      this.isOR.value = false
      criteria.value = true
    },
    handleSearch() {

      if(this.isBefore.value){
        this.criteriaTime = "Before"
      }
      else if(this.isOn.value){
        this.criteriaTime = "On"
      }
      else if(this.isAfter.value){
        this.criteriaTime = "After"
      }
      let url;
      let isCustom = false;
      console.log(this.currentFolder)
      switch (this.currentFolder){
        case "inbox":
          url = "http://localhost:8080/filterInbox?"
          break;
        case "sent":
          url = "http://localhost:8080/filterSent?"
          break;
        case "draft":
          url = "http://localhost:8080/filterDraft?"
          break;
        case "trash":
          url = "http://localhost:8080/filterTrash?"
          break;
        default:
          isCustom = true
          url = "http://localhost:8080/filterCustomFolder?"
          break;
      }
      let params;
      if(isCustom){
        params = {
          email: this.clientEmail + "@berry.com",
          folderName: this.currentFolder,
          SortCriteria: "Time",
          Type:this.isAND.value ? "AND" : "OR"
        }
      }
      else{
        params = {
          email: this.clientEmail + "@berry.com",
          SortCriteria: "Time",
          Type:this.isAND.value ? "AND" : "OR"
        }
      }
      const query = new URLSearchParams(params)
      const method = "POST"
      var obj1 = {}
      if(this.Sender !== ''){
        obj1["sender"] = [this.Sender]
      }
      if(this.Receiver !== ''){
        obj1["receiver"] = [this.Receiver]
      }
      if(this.Subject !== ''){
        obj1["subject"] = [this.Subject]
      }
      if(this.Date !== ''){
        obj1["date"] = [this.Date,this.criteriaTime]
      }
      if(this.Body !== ''){
        obj1["body"] = [this.Body]
      }
      if(this.Attachment !== ''){
        obj1["attachment"] = [this.Attachment]
      }

      const body = JSON.stringify(obj1)

      fetch(url+query, {
        method: method,
        body: body,
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(res => res.json())
      .then(data => {
        console.log(data);
        console.log("that was data1");
        this.emails = data;

      })
    },
  }
}
</script>

<style scoped>
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
  margin-top: 0;
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
.criteria.true {
  background-color: #521bb4;
  color: white;
}

.criteria.false {
  background-color: rgba(255, 255, 255, 0.44);
  color: white;
}
.criteria{
  background-color: rgba(255,255,255,0.07);
  margin-top: 0;
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
.criteria:hover{
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
