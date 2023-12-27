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
    <div style="display: flex; justify-content: center;position: absolute;top: 7%; left: 50%">
      <form>
        <h3>Sign Up</h3>
        <p v-if="isErr">try again</p>
        <label for="fname">First Name</label>
        <input v-model="fname" type="text" placeholder="First Name" id="username">
        <label for="lname">Last Name</label>
        <input v-model="lname" type="text" placeholder="Last Name" id="username">
        <label for="username">Username</label>
        <input v-model="uname" type="text" placeholder="Username" id="username">
        <label for="email">Email</label>
        <input v-model="e" type="text" placeholder="example@berry.com" id="username">
        <label for="password">Password</label>
        <input v-model="p" type="password" placeholder="Password" id="password">
        <div style="display: flex; flex-direction: column; align-content: center">
          <span @click="handleAccount">Create Account</span>
          <router-link to="/login">
            <span style="display: flex; flex-direction: column; align-content: center">Log In</span>
          </router-link>
          <div style="color: #ffffff; align-content: center;display: flex; flex-direction: column;">{{ condition }}</div>
          
        </div>
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
            fromBack:'',
            condition:'',
            fname:'',
            lname:'',
            uname:'',
            e:'',
            p:'',
            isErr: false,
                rout: function(){
                  const clientEmail = this.e.slice(0, -10)
                  const path = clientEmail + "/folder/inbox"
                  this.$router.push({path: path})
                }
        }
    },
    methods:{
        handleAccount(){
              const url = "http://localhost:8080/Signup?"
              const params = {
                firstName:this.fname,
                lastName:this.lname,
                username:this.uname,
                email:this.e,
                password:this.p,

              }
              const query = new URLSearchParams(params)
              const method = "POST"
              const body = ""

              fetch(url+query, {method: method})
              .then(res => {
                return res.text();
                // res.json();
                // if(res.status === 200){
                //   this.isErr = false;
                //   console.log("success")
                //   this.rout()

                // }else {
                //   this.isErr = false;
                //   window.setTimeout( function(){this.isErr = true}.bind(this), 300)

                // }
             }
            )
            .then(data => {
            this.fromBack = data;
            if(this.fromBack!=="Account created"){
              this.condition=this.fromBack;
            }
            else{
              alert("Account created!")
              const path = this.e + "/folder/inbox"
              this.$router.push({path: path})
            }
          });
        }
    }
  }
  </script>

  <style scoped>
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
    height: 830px;
    width: 400px;

    background-color: rgba(44, 19, 66, 0.34);

    position: absolute;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid  rgba(44, 19, 66, 0.13);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    padding: 50px 35px;
  }

  form *{
    font-family: 'Poppins',sans-serif;
    color: #ffffff;
    letter-spacing: 0.5px;
    outline: none;
    border: none;
  }
  form h3{
    font-size: 32px;
    font-weight: 500;
    line-height: 42px;
    text-align: center;
  }
  label{
    display: block;
    margin-top: 25px;
    font-size: 16px;
    font-weight: 500;
  }
  input{
    display: block;
    height: 50px;
    width: 100%;
    background-color: rgba(255,255,255,0.07);
    border-radius: 3px;
    padding: 0 10px;
    margin-top: 8px;
    font-size: 14px;
    font-weight: 300;
  }
  ::placeholder{
    color: #e5e5e5;
  }
  span{
    margin-top: 30px;
    width: 100%;
    background-color: #a41eb1;
    padding: 14px 0;
    font-size: 18px;
    font-weight: 600;
    border-radius: 5px;
    cursor: pointer;
    align-self: center;
    text-align: center;
    color: #ffffff;
    transition: 1s;
  }
  span:last-child{
    margin-top: 18px;
    color: #ffffff;
    background-color: rgba(255,255,255,0.07);
    transition: 1s;
  }
  span:hover{
    background-color: #856cd1;
    color: #ffffff;
  }
  .social{
    margin-top: 30px;
    display: flex;
  }
  .social div{
    width: 150px;
    border-radius: 3px;
    padding: 5px 10px 10px 5px;
    background-color: rgba(255,255,255,0.27);
    color: #eaf0fb;
    text-align: center;
  }
  .social div:hover{
    background-color: rgba(255,255,255,0.47);
  }
  .social .fb{
    margin-left: 25px;
  }
  .social i{
    margin-right: 4px;
  }
  </style>
