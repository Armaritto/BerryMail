<template>
  <div>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <br>
  <br>
  <div class="container bootdey">
    <div class="email-app">
      <main>
        <form @submit.prevent="handleSend" v-if="true">
        <h3 class="text-center">Compose Email</h3>
        <div class="form-row mb-3">
          <label for="to" class="col-2 col-sm-1 col-form-label">To:</label>
          <div class="col-10 col-sm-11">
            <input class="form-control" id="to" placeholder="Type email" v-model="to">
          </div>
        </div>
        <div class="form-row mb-3">
          <label for="Priority" class="pri col-2 col-sm-1 col-form-label">Priority:</label>
          <div class="Priority-group">
            <span  class="btn btn-priority" :class="{ 'true': this.isCritical.value, 'false': !this.isCritical.value }" @click="handlePriority(isCritical)" >
              <div style="display: flex; flex-direction: column; align-items: center">
                <lord-icon
                    src="https://cdn.lordicon.com/vihyezfv.json"
                    trigger="hover"
                    colors="primary:#ffffff"
                    style="width:30px;height:30px">
                </lord-icon>
                <div>
                  Critical
                </div>
              </div>
            </span>
            <span  class="btn btn-priority" :class="{ 'true': this.isUrgent.value, 'false': !this.isUrgent.value }" @click="handlePriority(isUrgent)" >
              <div style="display: flex; flex-direction: column; align-items: center">
                <lord-icon
                    src="https://cdn.lordicon.com/rpgflpkp.json"
                    trigger="hover"
                    colors="primary:#ffffff"
                    style="width:30px;height:30px">
                </lord-icon>
                <div>
                  Urgent
                </div>
              </div>
            </span>
            <span  class="btn btn-priority" :class="{ 'true': this.isModerate.value, 'false': !this.isModerate.value }" @click="handlePriority(isModerate)">
              <div style="display: flex; flex-direction: column; align-items: center">
                <lord-icon
                    src="https://cdn.lordicon.com/yxczfiyc.json"
                    trigger="hover"
                    colors="primary:#ffffff"
                    style="width:30px;height:30px">
                </lord-icon>
                <div>
                  Moderate
                </div>
              </div>
            </span>
            <span  class="btn btn-priority" :class="{ 'true': this.isDefault.value, 'false': !this.isDefault.value }" @click="handlePriority(isDefault)">
              <div style="display: flex; flex-direction: column; align-items: center">
                <lord-icon
                    src="https://cdn.lordicon.com/nzixoeyk.json"
                    trigger="hover"
                    colors="primary:#ffffff"
                    style="width:30px;height:30px">
                </lord-icon>
                <div>
                  Default
                </div>
              </div>
            </span>
          </div>

        </div>
          <div class="form-row mb-3">
            <label for="Priority" class="col-2 col-sm-1 col-form-label" style=" margin-right: 30px">Attachment:</label>
            <input
                type="file"
                multiple
                ref="fileInput"
                @change="handleFileChange"
            />
            <div v-for="attachment in attachments" :key="attachment.name">
              <span>{{ attachment.name}}</span>
            </div>
          </div>
        <div class="form-row mb-3">
          <label for="subject" class="col-2 col-sm-1 col-form-label">Subject:</label>
          <div class="col-10 col-sm-11">
            <input type="text" class="form-control" id="subject" placeholder="Type Subject" v-model="subject">
          </div>
        </div>
        <div class="row">
          <div class="col-sm-11 ml-auto">
            <div class="form-group mt-4">
              <textarea class="form-control" id="message" name="body" rows="12" placeholder="Click here to reply" v-model="body"></textarea>
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-success">Send</button>
              <span @click="handleDraft" class="btn btn-danger">Draft</span>
            </div>
          </div>
        </div>
        </form>
      </main>
    </div>
  </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
import swal from '@sweetalert/with-react';
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";
// define "lord-icon" custom element with default properties
defineElement(lottie.loadAnimation);
export default {
  props:['clientEmail'],
  data() {
    return {
      to: '',
      arrayReceivers:[],
      subject:'',
      body:'',
      priority: 'Default',
      isCritical:{value: false} ,
      isDefault: {value: true},
      isUrgent: {value: false},
      isModerate: {value: false},
      attachments: []
    }
  },
  methods: {
    async handleFileChange() {
      this.file = this.$refs.fileInput.files;
      for (let i = 0; i < this.file.length; i++) {
        let temp = {
          name: this.file[i].name,
          type: this.file[i].type,
          content: await this.convertFileToBase64(this.file[i]),
        };
        this.attachments.push(temp);
      }
      console.log(this.attachments.at(0));
    },
    handlePriority(interval){
      this.isCritical.value = false
      this.isDefault.value = false
      this.isUrgent.value = false
      this.isModerate.value = false
      interval.value = true
      if(interval.value === this.isCritical.value){
        this.priority = "Critical"
      }
      else if(interval.value === this.isDefault.value){
        this.priority = "Default"
      }
      else if(interval.value === this.isUrgent.value){
        this.priority = "Urgent"
      }
      else if(interval.value === this.isModerate.value){
        this.priority = "Moderate"
      }
    },
    addSkill($event) {
      if($event.key === ',' && this.tempSkill) {
        if (!this.skills.includes(this.tempSkill)) {
          this.skills.push(this.tempSkill)
        }
        this.tempSkill = ''
      }
    },
    deleteSkill(skill) {
      this.skills = this.skills.filter(item => {
        return skill !== item
      })
    },
    convertFileToBase64(file) {
      return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result.split(",")[1]);
        reader.onerror = (error) => reject(error);
      });
    },
    handleSend() {
      if(this.to === '' || this.subject === '' || this.body === ''){
        swal({
          title: "Please fill all the fields!",
          icon: "error",
          button: "Ok!",
        });
        return
      }
      this.arrayReceivers = this.to.split(', ');
      const url = "http://localhost:8080/send?"

      const params = {
        // from:this.myEmail,
        from:this.clientEmail + "@berry.com",
        to:this.arrayReceivers,
        subject:this.subject,
        priority:this.priority,
      }

      const query = new URLSearchParams(params)
      const method = "POST"
      const body = JSON.stringify({body:this.body, attachment:this.attachments})
      fetch(url+query, {
        method: method,
        body: body,
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        }})
          .then(res => res.text())
          .then(data => console.log(data))
      this.to = ''
      this.subject = ''
      this.body = ''
      this.priority = 'Default'
      this.isCritical.value = false
      this.isDefault.value = true
      this.isUrgent.value = false
      this.isModerate.value = false
      this.attachments = []
      swal({
        title: "Email Sent Successfuly!",
        icon: "success",
        button: "Yaaay!",
      });
    },
    handleDraft() {
      if(this.to === '' || this.subject === '' || this.body === ''){
        swal({
          title: "Please fill all the fields!",
          icon: "error",
          button: "Ok!",
        });
        return
      }
      this.arrayReceivers = this.to.split(',');
      const url = "http://localhost:8080/addToDraft?"
      const params = {
        // from:this.myEmail,
        from:this.clientEmail + "@berry.com",
        to:this.arrayReceivers,
        subject:this.subject,
        priority:this.priority,
      }
      const query = new URLSearchParams(params)
      const method = "POST"
      const body = JSON.stringify({body:this.body, attachment:this.attachments})
      fetch(url+query, {
        method: method,
        body: body,
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        }})
          .then(res => res.text())
          .then(data => console.log(data))
      this.to = ''
      this.subject = ''
      this.body = ''
      this.priority = 'Default'
      this.isCritical.value = false
      this.isDefault.value = true
      this.isUrgent.value = false
      this.isModerate.value = false
      this.attachments = []
      swal({
        title: "Email Saved to Draft!",
        button: "Ok!",
      });
    },
  }
}
</script>


<style>
.pri{
  margin-top: 13px;
}
.priority-group{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.btn-priority{
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 3px;
  color: #ffffff;
  margin-right: 20px;
  width:85px;
  transition: 1s;
}
.btn-priority:first-child{
  margin-left: 5px;
  transition: 1s;
}
.btn-priority:hover{
  background-color: #0a5ed5;
  border-radius: 3px;
  color: #ffffff;
  cursor: pointer;
  transition: 1s;
}
.btn-priority.true:hover{
  background-color: #0a5ed5;
  border-radius: 3px;
  color: #ffffff;
  cursor: pointer;
  transition: 1s;
}
.btn-priority.false:hover{
  background-color: #0a5ed5;
  border-radius: 3px;
  color: #ffffff;
  cursor: pointer;
  transition: 1s;
}
.btn-priority.true {
  background-color: #980697;
  color: white;
  transition: 1s;
}

.btn-priority.false {
  background-color: rgba(255, 255, 255, 0.13);
  color: white;
  transition: 1s;
}
.btn-success{
  margin-right: 20px;
  width: 100px;
  border: none;
  background-color: #0d600d;
  color: #ffffff;
  transition: 1s;
}
.btn-success:hover{
  cursor: pointer;
}
.btn-danger:hover{
  cursor: pointer;
}
.btn-attach{
  background-color: rgba(255,255,255,0.07);
  border-radius: 5px;
  color: #ffffff;
  margin-left: 20px;
}
.btn-attach:hover{
  cursor: pointer;
  background-color: rgba(255, 255, 255, 0.2);
}
.btn-danger{
  margin-right: 20px;
  width: 100px;
  border: none;
  background-color: rgba(255, 255, 255, 0.2);
}
.form-control{
  background-color: rgba(255,255,255,0.07);
  border-radius: 3px;
  color: #ffffff;
}
.form-control:focus{
  background-color: rgba(255,255,255,0.07);
  border-radius: 3px;
  color: #ffffff;
}
.form-control::placeholder{
  color: #bebebe;
}
body{margin-top:20px;}
.email-app {
  display: flex;
  flex-direction: row;
  background: rgba(44, 19, 66, 0.34);
  color: #ffffff;
  border-radius: 10px;
  backdrop-filter: blur(5px);
  border: 2px solid  rgba(44, 19, 66, 0.13);
  box-shadow: 0 0 40px rgba(8,7,16,0.6);
}
h3{
  font-size: 32px;
  font-weight: 500;
  line-height: 42px;
  text-align: center;
  padding-bottom: 20px;
}

.email-app nav {
  flex: 0 0 200px;
  padding: 1rem;
  border-right: 1px solid #e1e6ef;
}

.email-app nav .btn-block {
  margin-bottom: 15px;
}

.email-app nav .nav {
  flex-direction: column;
}

.email-app nav .nav .nav-item {
  position: relative;
}

.email-app nav .nav .nav-item .nav-link,
.email-app nav .nav .nav-item .navbar .dropdown-toggle,
.navbar .email-app nav .nav .nav-item .dropdown-toggle {
  color: #151b1e;
  border-bottom: 1px solid #e1e6ef;
}

.email-app nav .nav .nav-item .nav-link i,
.email-app nav .nav .nav-item .navbar .dropdown-toggle i,
.navbar .email-app nav .nav .nav-item .dropdown-toggle i {
  width: 20px;
  margin: 0 10px 0 0;
  font-size: 14px;
  text-align: center;
}

.email-app nav .nav .nav-item .nav-link .badge,
.email-app nav .nav .nav-item .navbar .dropdown-toggle .badge,
.navbar .email-app nav .nav .nav-item .dropdown-toggle .badge {
  float: right;
  margin-top: 4px;
  margin-left: 10px;
}

.email-app main {
  min-width: 0;
  flex: 1;
  padding: 1rem;

}

.email-app .inbox .toolbar {
  padding-bottom: 1rem;
  border-bottom: 1px solid #e1e6ef;
}

.email-app .inbox .messages {
  padding: 0;
  list-style: none;
  background-color: rgba(255,255,255,0.07);
  border-radius: 3px;
}

.email-app .inbox .message {
  position: relative;
  padding: 1rem 1rem 1rem 2rem;
  cursor: pointer;
  border-bottom: 1px solid #e1e6ef;
  background-color: rgba(255,255,255,0.07);
  border-radius: 3px;
}

.email-app .inbox .message:hover {
  background: #f9f9fa;

}

.email-app .inbox .message .actions {
  position: absolute;
  left: 0;
  display: flex;
  flex-direction: column;
}

.email-app .inbox .message .actions .action {
  width: 2rem;
  margin-bottom: 0.5rem;
  color: #c0cadd;
  text-align: center;
}

.email-app .inbox .message a {
  color: #000;

}

.email-app .inbox .message a:hover {
  text-decoration: none;

}

.email-app .inbox .message.unread .header,
.email-app .inbox .message.unread .title {
  font-weight: bold;

}

.email-app .inbox .message .header {
  display: flex;
  flex-direction: row;
  margin-bottom: 0.5rem;

}

.email-app .inbox .message .header .date {
  margin-left: auto;

}

.email-app .inbox .message .title {
  margin-bottom: 0.5rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

}

.email-app .inbox .message .description {
  font-size: 12px;
}

.email-app .message .toolbar {
  padding-bottom: 1rem;
  border-bottom: 1px solid #e1e6ef;

}

.email-app .message .details .title {
  padding: 1rem 0;
  font-weight: bold;

}

.email-app .message .details .header {
  display: flex;
  padding: 1rem 0;
  margin: 1rem 0;
  border-top: 1px solid #e1e6ef;
  border-bottom: 1px solid #e1e6ef;

}

.email-app .message .details .header .avatar {
  width: 40px;
  height: 40px;
  margin-right: 1rem;

}

.email-app .message .details .header .from {
  font-size: 12px;
  color: #9faecb;
  align-self: center;

}

.email-app .message .details .header .from span {
  display: block;
  font-weight: bold;

}

.email-app .message .details .header .date {
  margin-left: auto;

}

.email-app .message .details .attachments {
  padding: 1rem 0;
  margin-bottom: 1rem;
  border-top: 3px solid #f9f9fa;
  border-bottom: 3px solid #f9f9fa;

}

.email-app .message .details .attachments .attachment {
  display: flex;
  margin: 0.5rem 0;
  font-size: 12px;
  align-self: center;

}

.email-app .message .details .attachments .attachment .badge {
  margin: 0 0.5rem;
  line-height: inherit;

}

.email-app .message .details .attachments .attachment .menu {
  margin-left: auto;

}

.email-app .message .details .attachments .attachment .menu a {
  padding: 0 0.5rem;
  font-size: 14px;
  color: #e1e6ef;

}

@media (max-width: 767px) {
  .email-app {
    flex-direction: column;
  }
  .email-app nav {
    flex: 0 0 100%;
  }
}

@media (max-width: 575px) {
  .email-app .message .header {
    flex-flow: row wrap;
  }
  .email-app .message .header .date {
    flex: 0 0 100%;
  }
}
.dropdown {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  padding: 12px 16px;
  z-index: 1;
  cursor: pointer;
}

.dropdown:hover .dropdown-content {
  display: block;
  cursor: pointer;
}
.dropdown-content p:hover{
  background: #aaa6cc;
  cursor: pointer;
}
input[type=file]::file-selector-button {
  border: 0;
  padding: .4em .4em;
  border-radius: .2em;
  background-color: rgba(255, 255, 255, 0.2);
  transition: 1s;
  color: white;
  margin-right: 10px;
}

input[type=file]::file-selector-button:hover {
  border: 0;
  background-color: #0a5ed5;
  cursor: pointer;
}
@import'~bootstrap/dist/css/bootstrap.css';
</style>

