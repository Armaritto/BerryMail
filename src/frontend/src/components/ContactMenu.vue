<template>
  <div>
    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Glass morphism</title>
    </head>
    <body>
    <div class="background">
      <div class="shape"></div>
      <div class="shape"></div>
    </div>

    <div v-if="!createCont" style="display: flex; justify-content: center; position: absolute; top: 25%; left: 50%">
      <form>
        <label for="sender">Search Contact</label>
        <input type="text" placeholder="Type Contact" id="sender" v-model="sender">
        <button type="button" @click="handleSearch">Search</button>
      </form>

      <div style="padding-top: 170px; color: white">
        <table>
            <tr @click.alt.exact.prevent="handleDeleteContact(name)" v-for="(contact, name, index) in contacts" :key="index">
              <tr>
                <h5>{{ name }}</h5>
                <tr v-for="(email, emailIndex) in contact" :key="emailIndex">
                  <p>{{ email }}</p>
                </tr>
                <td>
                  <div class="delete deleteH" @click="handleDeleteContact(name)" style="display: flex; flex-direction: column; align-items: center; border: none; border-radius: 10px">
                    <lord-icon
                        src="https://cdn.lordicon.com/wpyrrmcq.json"
                        trigger="hover"
                        style="width:30px;height:30px">
                    </lord-icon>
                  </div>
                </td>
              </tr>
            </tr>


        </table>
      </div>

      <div class="new_cont">
          <span class="btn btn-priority" @click="addContact">
            <div class="icon-container-right-top">
              <lord-icon
                  src="https://cdn.lordicon.com/jgnvfzqg.json"
                  trigger="hover"
                  colors="primary:#ffffff"
                  style="width:30px;height:30px;">
              </lord-icon>
              <div>Add</div>

            </div>
          </span>
      </div>

      <div v-if="createCont" style="display: flex; justify-content: center; position: absolute; top: 25%; left: 50%"></div>
    </div>
    </body>
  </div>
</template>

<script>
import CreateContact from "@/components/CreateContact.vue";
export default {
  name: 'ContactMenu',
  props: ["mail"],
  components: {
    CreateContact
  },
  data(){
    return{
      searchName: '',
      contacts: {},
      sortContactsBy: 'date',
      createCont: false,
      mail: this.mail,
      fetchContacts: function(){
        var url = "http://localhost:8080/contacts?"
        const params = {
          email: this.mail + "@berry.com",
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
      },
      addContact: async function () {
        console.log("add contact"+this.mail);
        this.createCont = true;
        await swal("Enter Name of Contact:", {
          content: "input",
        })
            .then(async (value) => {
              let NameOfContact = value;
              await swal(`Enter List of Emails for: ${value}`, {
                content: "input",
              })
                  .then((value) => {
                    let ListOfEmails = value;
                    console.log("N"+NameOfContact);
                    console.log("L"+ListOfEmails);
                    swal(`Successfully Created: ${NameOfContact}`);
                    const url = "http://localhost:8080/addContact?"
                    const params = {
                      email: this.mail + "@berry.com",
                      name: NameOfContact,
                      mails: ListOfEmails,
                    }
                    const query = new URLSearchParams(params)
                    const method = "POST"
                    fetch(url + query, {method: method})
                        .then(res => res.json())
                        .then(data => {
                          console.log(data)
                        })
                    this.fetchContacts();
                  });
            });
      },
    }
  },
  mounted(){
    this.fetchContacts()
  },
  methods:{
    storeData(){
      this.searchName = document.getElementById("sender").value

    },
    handleDeleteContact: function (name) {
      console.log("delete contact"+this.mail);
      const url = "http://localhost:8080/deleteContact?"
      const params = {
        email: this.mail + "@berry.com",
        name: name,
      }
      const query = new URLSearchParams(params)
      const method = "DELETE"
      fetch(url + query, {method: method})
          .then(res => res.json())
          .then(data => {
            console.log(data)
          })
      this.fetchContacts();
    },
    handleSearch: function () {
      // const url = "http://localhost:8080/searchContact?"
      // const params = {
      //   email: this.mail + "@berry.com",
      //   name: this.searchName,
      // }
      // const query = new URLSearchParams(params)
      // const method = "GET"
      // fetch(url + query, {method: method})
      //     .then(res => res.json())
      //     .then(data => {
      //       console.log(data)
      //       this.contacts = {};
      //       this.contacts.push({
      //         name: data.name,
      //         mails: data.mails,
      //       })
      //     })
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

.icon-container-right-top {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  position: fixed;
  top: 400px;
  right: 400px;
}
.delete{
  background-color: rgba(255, 255, 255, 0.05);
}
.deleteH:hover{
  background-color: rgba(255, 255, 255, 0.2);
  cursor: pointer;
}
</style>
