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
      <label for="sender">Search Contact</label>
      <input type="text" placeholder="Type Contact" id="sender" v-model="Sender">
      <button type="button" @click="handleSearch">Search</button>
    </form>
    <div style="padding-top: 170px">
      <table >
        <tr v-for="(contact, name, index) in contacts">
          <h5>{{name}}</h5>
          <tr v-for="email in contact">
            <p>{{email}}</p>
          </tr>
        </tr>
      </table>
    </div>
    <div class="new_cont">
    <span class="btn btn-priority">
      <div style="display: flex; flex-direction: column; align-items: center">
        <lord-icon
          src="https://cdn.lordicon.com/jgnvfzqg.json"
          trigger="hover"
          style="width:30px;height:30px;">
        </lord-icon>
        <div>
          Add Contact
        </div>
      </div>
    </span>
  </div>
  </div>

  </body>
</template>

<script>
export default {
  name: 'main',
  props: ['clientEmail'],
  data(){
    return{
      contacts: {},
      sortContactsBy: 'date',
      fetchContacts: function(){
        var url = "http://localhost:8080/" + "contacts" + "?"
        const params = {
          email: this.clientEmail + "@berry.com",
          SortCriteria: this.sortContactsBy,
        }
        const query = new URLSearchParams(params)
        const method = "GET"
        const body = ""
        fetch(url + query, {method: method})
            .then(res => res.json())
            .then(data => {
              this.contacts = data;
              console.log(data)
              // this.paginate();
              // this.numOfPages = Math.ceil(this.emails.length / this.emailsPerPage)
            })
      }
    }
  },
  mounted(){
    this.fetchContacts
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
      switch (this.folder){
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
          folderName: this.folder,
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
      const body = JSON.stringify({
        sender:this.Sender,
        receiver:this.Receiver,
        subject:this.Subject,
        date:[this.Date, this.criteriaTime],
        body:this.Body,
        attachment:this.Attachment,
      })
      fetch(url+query, {
        method: method,
        body: body,
        headers: {
          'Content-Type': 'application/json'
        }
      })
          .then(res => res.json())
          .then(data => {
            console.log(data)
          })
    }
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
.new_cont{
  display: flex;
  flex-direction: column;
  align-items: bottom;
  justify-content: right;
  margin-bottom: auto;
  margin-left: auto;
}
.btn-priority{
    background-color: rgba(255, 255, 255, 0.2);
    border-radius: 3px;
    color: #ffffff;
    margin-right: 20px;
    width: 70px;
    transition: 1s;
    }


</style>
