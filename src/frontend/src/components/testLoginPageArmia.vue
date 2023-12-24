<template>
  <div class="login-container" style="background-color: #7B1FA2;">
    <div class="logo">
      <img src="berry-logo.png" alt="BerryMail Logo" />
    </div>
    <h1 style="color: white;">BerryMail</h1>
    <form class="login-form">
      <input type="email" placeholder="Email" required />
      <input type="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'LoginPage',
  // Add your Vue.js options here
};
</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.logo img {
  width: 100px; /* Adjust as needed */
}

.login-form input {
  margin: 10px 0;
  padding: 10px;
}

.login-form button {
  background-color: #D32F2F;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
}
</style>
