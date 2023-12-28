<template>
<div @dblclick="isRenaming=true">
  <div v-if="!isRenaming" class="items" @click.alt.prevent="handleDelete">
    <div>
      <lord-icon
          src="https://cdn.lordicon.com/jkzgajyr.json"
          trigger="hover"
          colors="primary:#ffffff"
          style="width:50px;height:50px">
      </lord-icon>
    </div>
    <div style="margin-left: 30px;margin-top: 14px">
      {{folderName}}
    </div>
  </div>
  <input v-if="isRenaming" :placeholder="folderName" v-model="newName" v-on:keyup.enter="handleEnter" >
  <div>
  </div>
</div>
</template>

<script>
  export default {
    props:['folderName', 'clientEmail'],
    data(){
        return{
          newName:'',
          isRenaming: false
        }

    },
    methods:{
      handleDelete(){
        const url = "http://localhost:8080/deleteFolder?"
        const params = {
          email: this.clientEmail + "@berry.com",
          name:this.folderName

        }
        const query = new URLSearchParams(params)
        const method = "DELETE"
        const body = ""

        fetch(url+query, {method: method})
            .then(res => {

              res.json();
              if(res.status === 200){
                console.log("success")


              }else {
                console.log("failure")

              }

            })
            .then(() => {
              this.$emit('delete')})
      }
      ,
      handleEnter(){
        const url = "http://localhost:8080/renameFolder?"
        const params = {
          email: this.clientEmail + "@berry.com",
          oldFolderName:this.folderName,
          newFolderName:this.newName || this.folderName


        }
        const query = new URLSearchParams(params)
        const method = "PUT"
        const body = ""

        fetch(url+query, {method: method})
            .then(res => {

              res.json();
              if(res.status === 200){
                console.log("success")


              }else {
                console.log("failure")

              }

            })
            .then(() => {this.isRenaming=false;
                          this.$emit('rename',this.newName)})
      }
   }
  }
</script>


<style scoped>
.items{
  text-decoration: none;
  color: #ffffff;
  display: flex;
}
</style>
