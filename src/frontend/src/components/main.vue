<template>

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <br>
  <br>
  <div class="container bootdey">
    <div class="email-app">
      <main>
        <h3 class="text-center">New Message</h3>
        <form>
          <div class="form-row mb-3">
            <label for="to" class="col-2 col-sm-1 col-form-label">To:</label>
            <div class="col-10 col-sm-11">
              <input type="email" class="form-control" id="to" placeholder="Type email">
            </div>
          </div>
          <div class="form-row mb-3">
            <label for="Priority" class="col-2 col-sm-1 col-form-label">Priority:</label>
            <div class="Priority-group">
              <button onclick="" class="btn btn-priority">Critical</button>
              <button type="submit" class="btn btn-priority">Urgent</button>
              <button type="submit" class="btn btn-priority">Moderate</button>
              <button type="submit" class="btn btn-priority">Default</button>
            </div>
          </div>
          <div class="form-row mb-3">
            <label for="subject" class="col-2 col-sm-1 col-form-label">Subject:</label>
            <div class="col-10 col-sm-11">
              <input type="email" class="form-control" id="subject" placeholder="Type Subject">
            </div>
          </div>
        </form>
        <div class="row">
          <div class="col-sm-11 ml-auto">

            <div class="form-group mt-4">
              <textarea class="form-control" id="message" name="body" rows="12" placeholder="Click here to reply"></textarea>
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-success">Send</button>
              <button type="submit" class="btn btn-light">Draft</button>
              <button type="submit" class="btn btn-danger">Discard</button>
            </div>
          </div>
        </div>
      </main>
    </div>

  </div>
  <div style="display: flex; justify-content: center;position: absolute;top: 25%; left: 50%">
    <form>
      <h3>Login</h3>
      <label for="email">Email</label>
      <input type="text" placeholder="example@berry.com" id="username">

      <label for="password">Password</label>
      <input type="password" placeholder="Password" id="password">
      <button>Log In</button>
      <button>Create Account</button>
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


<style>
.priority-group{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.btn-priority{
  background-color: rgba(255,255,255,0.07);
  border-radius: 3px;
  color: #ffffff;
}
.btn-priority:hover{
  background-color: #0a5ed5;
  border-radius: 3px;
  color: #ffffff;
  cursor: pointer;
}
.btn-success{
  background-color: #27c766;
  border-radius: 3px;
  color: #ffffff;
}
.btn-success:hover{
  cursor: pointer;
}
.btn-danger:hover{
  cursor: pointer;
}
.btn-light:hover{
  cursor: pointer;
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
@import'~bootstrap/dist/css/bootstrap.css';
=======
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
  background-color: rgba(44, 19, 66, 0.13);
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
  background-color: #ffffff;
  color: #080710;
  padding: 15px 0;
  font-size: 18px;
  font-weight: 600;
  border-radius: 5px;
  cursor: pointer;
}
button:last-child{
  background-color: rgba(255,255,255,0.07);
  background-color: #bc3fdb;
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
