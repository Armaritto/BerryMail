<template>
  <div style="display: flex">
  <div class="icon" v-if="emailMeta.priority === 'Critical'">
    <lord-icon
        src="https://cdn.lordicon.com/vihyezfv.json"
        trigger="hover"
        colors="primary:#ffffff"
        style="width:30px;height:30px">
    </lord-icon>
  </div>
  <div class="icon" v-else-if="emailMeta.priority === 'Urgent'">
    <lord-icon
        src="https://cdn.lordicon.com/rpgflpkp.json"
        trigger="hover"
        colors="primary:#ffffff"
        style="width:30px;height:30px">
    </lord-icon>
  </div>
  <div class="icon" v-else-if="emailMeta.priority === 'Moderate'">
    <lord-icon
        src="https://cdn.lordicon.com/yxczfiyc.json"
        trigger="hover"
        colors="primary:#ffffff"
        style="width:30px;height:30px">
    </lord-icon>
  </div>
  <div class="icon" v-else-if="emailMeta.priority === 'Default'">
    <lord-icon
        src="https://cdn.lordicon.com/nzixoeyk.json"
        trigger="hover"
        colors="primary:#ffffff"
        style="width:30px;height:30px">
    </lord-icon>
  </div>
  <div class="preview" style="width: 90%" >
    <p class="from"><strong>{{emailMeta.from}}</strong></p>
    <table style="width: 100%;">
      <tr>
        <td>
          <p class="subject">{{emailMeta.subject}}</p>
        </td>
        <td class="date-cell">
          <p class="date"><strong>{{emailMeta.dateNtime.substr(0, 10) +'   '+ emailMeta.dateNtime.substr(11, 11).substr(0, 5) || "1/1/1900"}}</strong></p>
        </td>
      </tr>
    </table>
    <p class="content" v-if="emailMeta.body !== null">{{emailMeta.body.substr(0, 10) + "..."}}</p>
    <p class="content" v-else>{{emailMeta.body  || "No preview Available"}}</p>
    <!-- <p v-if="emailMeta.body" class="content">...</p> -->
    <p v-if="!isRenaming" @dblclick="isRenaming = true">{{folderName}}</p>
    <input v-if="isRenaming" :placeholder="folderName" v-model="newName" v-on:keyup.enter="handleEnter(this.id); for(let i in selected){ handleEnter(selected[i]) ;selected.splice(i, 1)}" >

  </div>
  </div>
</template>
<script>
export default {
    props:['emailMeta', 'selected','folderName', 'id', 'clientEmail'],
    data(){
        return{
          isRenaming:false,
          newName:''
        }
    },
    methods:{
      handleEnter(id){
        const url = "http://localhost:8080/addToFolder?"
        const params = {
          email: this.clientEmail + "@berry.com",
          folderName:this.newName,
          mailID:id,
          oldFolder:this.folderName


        }
        const query = new URLSearchParams(params)
        const method = "PUT"
        const body = ""

        fetch(url+query, {method: method})
            // .then(res => {

            //   res.json();
            //   if(res.status === 200){
            //     console.log("success")


            //   }else {
            //     console.log("failure")

            //   }

            // })
            .then((res) => {this.isRenaming=false;
                          this.$emit('move',this.newName);
                          console.log(res)
                        })
      }
    }
}
</script>
<style scoped>
.icon{
  padding: 0;
  width: 12%;
  justify-content: center;
  margin-top: 45px;
  background-color: rgba(255, 255, 255, 0);
}
.icon:hover{
  background-color: rgba(255, 255, 255, 0);
  color: #ffffff;
  cursor: auto;
}
.preview{
  display: flex;
  flex-direction: column;
  margin: 5px;
  padding: 10px;
}
.from{
  margin: 0;
  padding: 0;
}
.subject{
  margin: 0;
  padding: 0;
}
.content{
  margin: 0;
  padding: 0;
}
.date{
  margin: 0;
  padding: 0;
}
table {
  table-layout: fixed;
}

td {
  width: 50%; /* Each table cell takes up 50% of the table width */
}

.date-cell {
  text-align: right; /* Align the text to the right within the table cell */
}
.preview{
    width: 100%;
    font-size: small;
    background-image: linear-gradient(to left, rgba(255,255,255,0.07), rgba(255,255,255,0.07), #2765c5, #6e21cc);
    background-size: 300%;
    background-position: right;
    border: 0;
    border-radius: 10px;
    transition: 1s background-position ease-in-out;
    color: #ffffff;
}
.preview:hover {
  background-position: left;
  color: #fff;
  cursor: pointer;
}

  /* .from{
  position: absolute;
  left: 0%;
  }
  .date{
  position: absolute;
  right: 100%;
  } */
</style>
