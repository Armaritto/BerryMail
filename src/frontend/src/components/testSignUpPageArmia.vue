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
      <label for="fname">First Name</label>
      <input type="text" placeholder="First Name" id="username">
      <label for="lname">Last Name</label>
      <input type="text" placeholder="Last Name" id="username">
      <label for="username">Username</label>
      <input type="text" placeholder="Username" id="username">
      <label for="email">Email</label>
      <input type="text" placeholder="example@berry.com" id="username">
      <label for="password">Password</label>
      <input type="password" placeholder="Password" id="password">
      <button>Create Account</button>
      <button>Log In</button>
    </form>
  </div>
  </body>
</template>

<script>
export default {
  name: 'main',
  props: {
    msg: String
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
  height: 820px;
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
  font-size: 14px;
  font-weight: 300;
}
::placeholder{
  color: #e5e5e5;
}
button{
  margin-top: 50px;
  width: 100%;
  background-color: #bc3fdb;
  color: #080710;
  padding: 15px 0;
  font-size: 18px;
  font-weight: 600;
  border-radius: 5px;
  cursor: pointer;
}
button:last-child{
  background-color: rgba(255,255,255,0.07);
  color: #ffffff;
  margin-top: 20px;
}
button:hover{
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
</style>
