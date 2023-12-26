<template>
   <div>
    <div class="background">
      <div class="shape"></div>
      <div class="shape"></div>
    </div>
    <div style="display: flex; justify-content: center;position: absolute;top: 25%; left: 50%">
      <form>
        <h3>Login</h3><p v-if="isErr">try again</p>
        <label>Email</label>
        <input v-model="e" type="text"  id="username" placeholder="example@berry.com">
  
        <label>Password</label> 
        <input v-model="p" type="password"  id="password" placeholder="password">
        <span  @click="handleLogin" >Log In</span>
        <span>Create Account</span>
        
      </form>
     
    </div>
     
   </div>
  </template>
  
    <script>
    export default {
        name: 'LoginView',
        data(){
            return{
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
            handleLogin(){
              const url = "http://localhost:8080/Login?"
              const params = {
                email:this.e,
                password:this.p,
              
              }
              const query = new URLSearchParams(params)
              const method = "GET"
              const body = ""
              
              fetch(url+query, {method: method})
              .then(res => {
                
                res.json();
                if(res.status === 200){
                  this.isErr = false;
                  console.log("success")
                  this.rout()

                }else if(res.status === 500){
                  this.isErr = false;
                  window.setTimeout( function(){this.isErr = true}.bind(this), 300)
                  
                }
              
              })
              // .then(data => console.log(data))
              // .catch(err => console.log("error"))
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
    height: 520px;
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
    margin-top: 30px;
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
    margin-bottom: 30px;
    font-size: 14px;
    font-weight: 300;
  }
  ::placeholder{
    color: #e5e5e5;
  }
  span{
    margin-top: 100px;
    width: 100%;
    background-color: #ffffff;
    color: #181530;
    padding: 20px 0;
    font-size: 18px;
    font-weight: 600;
    border-radius: 5px;
    cursor: pointer;
  }
  span:last-child{
    background-color: rgba(255,255,255,0.07);
    background-color: #bc3fdb;
    margin-left: 20px;
  }
  span:hover{
    background-color: #521bb4;
    color: #080710;
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
  p {
    text-emphasis-color: red;
    color: red;
  }
  </style>