<template>
    <div class="folder">
    <form @submit.prevent="handleSend" v-if="true">
      <label>to:</label>
      <input type="email" v-model="to" required>
  
      <label>Subject:</label>
      <input type="text" v-model="subject">
  
      <label></label>
      <textarea name="paragraph_text" cols="44" rows="10" v-model="body"></textarea>
      
      <div class="submit">
        <button>send</button>
      </div>
    </form>
    </div>
</template>
  
  <script>
  export default {
    props:['myEmail'],
    data() {
      return {
        to: '',
        subject:'',
        body:''
      }
    },
    methods: {
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
      handleSend() { 
        const url = "http://localhost:8080/send?"
        const params = {
          from:this.myEmail,
          to:this.to,
          subject:this.subject,
          body:this.body,
          priority:"Default",
          attachment:"PNG"
        }
        const query = new URLSearchParams(params)
        const method = "POST"
        const body = ""
        
        fetch(url+query, {method: method, body: body})
        .then(res => res.text())
        .then(data => console.log(data))

        // make request to database to save user
      }
    }
  }
  </script>
  
  <style scoped>
    .folder{

    width: 420px;
    height: 310px;
    margin: 10em auto 10em;

    border-radius: 5px 25px 25px 25px;
    filter: drop-shadow(0 0 0.75rem grey);

    background: white;
    position: relative;
    right:0;
    bottom:90px;
    }

    .folder::before{
    content:  '';
    position: absolute;
    top: -18px;
    width: 200px;
    height: 25px;
    background: white;
    border-radius: 25px 0 0 0;
    clip-path: path('M 0 0 L 160 0 C 185 2, 175 16, 200 18 L 0 50 z');
    }
    .folder::after{
    content: '';
    position: absolute;
    left:  40px;
    width: 85px;
    height: 5px;
    top: -18px;
    background: #7036E9;
    border-radius: 0 0 5px 5px;

    } 
    .editor{
        box-shadow: 2px 2px 20px 2px #f4f4f4;
        height: 300px;
        width: 500px;
        position: absolute;
        bottom: 150px;
        right: 0;
    }
    form {
      max-width: 420px;
      margin: 30px auto;
      background: white;
      text-align: left;
      padding: 40px;
      border-radius: 10px;
    }
    label {
      color: #aaa;
      display: inline-block;
      margin: 25px 0 15px;
      font-size: 0.6em;
      text-transform: uppercase;
      letter-spacing: 1px;
      font-weight: bold;
    }
    input, select {
      display: block;
      padding: 10px 6px;
      width: 100%;
      box-sizing: border-box;
      border: none;
      border-bottom: 1px solid #ddd;
      color: #555;
    }
    input[type="checkbox"] {
      display: inline-block;
      width: 16px;
      margin: 0 10px 0 0;
      position: relative;
      top: 2px;
    }
    .pill {
      display: inline-block;
      margin: 20px 10px 0 0;
      padding: 6px 12px;
      background: #eee;
      border-radius: 20px;
      font-size: 12px;
      letter-spacing: 1px;
      font-weight: bold;
      color: #777;
      cursor: pointer;
    }
    button {
      background: #7036E9;
      border: 0;
      padding: 10px 20px;
      margin-top: 20px;
      color: white;
      border-radius: 20px;
    }
    .submit {
      text-align: center;
    }
    .error {
      color: #ff0062;
      margin-top: 10px;
      font-size: 0.8em;
      font-weight: bold;
    }
    textarea {
   resize: none;
}
  </style>