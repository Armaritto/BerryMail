<template>
    
    <div class="folder">
        <div v-if="emails.length == 0">
            <h1>{{this.folderName}} is empty</h1>
        </div>
        <div v-if="emails.length !== 0">
        <table>
            <label class="Sorting-Label">Sort By:</label>
            <div class="Priority-group">
            <span  class="btn btn-priority" :class="{ 'true': this.sortByTime.value, 'false': !this.sortByTime.value }" @click="handleSort(sortByTime)">Time</span>
            <span  class="btn btn-priority" :class="{ 'true': this.sortByPriority.value, 'false': !this.sortByPriority.value }" @click="handleSort(sortByPriority)">Priority</span>
            </div>
            <label for="inputField" class="input-label">Filter By</label>
            <select id="inputField" class="input-field">
                <option value="">No Filter</option>
                <option value="1">Time</option>
                <option value="2">Subject</option>
                <option value="3">Priority</option>
                <option value="4">HELP</option>
            </select>
            <tr v-for="e in emails" :key="e.id" class="mails">
                <MailPreview :emailMeta="e"></MailPreview>
            </tr>
            
        </table>
    </div>
    </div>
</template>
<script>
import MailPreview from "@/components/MailPreview.vue";
export default {
   props:['clientEmail', 'folderName'],
   watch: { 
    folderName: function() {
          this.fetchFolder()
          console.log("fetched")
        }
    },
    components:{
        MailPreview
    },
    data(){
        return{
            folders:["inbox", "sent", "draft", "trash"],
            fetchFolder: function(){
                var url = null
                if(this.folderName){
        if(!this.folders.includes(this.folderName)){
             url = "http://localhost:8080/" + "customFolder" + "?"
        }
        else {
         url = "http://localhost:8080/" + this.folderName +"?"
    }
        const params = {
          email: this.clientEmail + "@berry.com",
          SortCriteria: this.sortBy,
          folderName: this.folderName
        }
        const query = new URLSearchParams(params)
        const method = "GET"
        const body = ""
        
        fetch(url+query, {method: method})
        .then(res => res.json())
        .then(data => this.emails = data)
    }
            },
        emails:[],
        sortByTime: {value: true},
        sortByPriority:{value: false},
        sortBy: 'Time'
        }
    },
    mounted(){
        this.fetchFolder()
    },
    methods: {
        handleSort(interval){
            this.sortByTime.value = false
            this.sortByPriority.value = false
            interval.value = true
            if(interval.value === this.sortByTime.value){
                this.sortByTime.value = true
                this.sortBy = 'Time'
            }
            else if(interval.value === this.sortByPriority.value){
                this.sortByPriority.value = true
                this.sortBy = 'Priority'
            }
            this.fetchFolder()
        }
    }
    
    
}
</script>
<style scoped>
    .folder{
        /* height: 500px; */
        display: flex;
        float: center;
        justify-content: center;
        width: 350px;
        padding: 10px;
        /* position: relative; */
    }
    .mails{
        width: 330px; /* set the width of the table to 100% of its parent container */
        margin: 0 auto; /* center the table horizontally */
        float: center;
        margin: 10px;
        display: flex;
        justify-content: center; 
        border-radius: 10px;
    }

    .btn-priority{
    background-color: rgba(255, 255, 255, 0.2);
    border-radius: 3px;
    color: #ffffff;
    margin-right: 20px;
    width: 100px;
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
    background-color: rgba(255, 255, 255, 0.2);
    color: white;
    transition: 1s;
    }

    .Sorting-Label{
        
        float: left;
        display: block;
        font-size: 14px;
        color: white;
        margin-bottom: 5px;
    }

.input-label {
    float: left;
    display: block;
    font-size: 14px;
    color: white;
    margin-bottom: 5px;
}

.input-field {
    width: 100%;
    padding: 8px 12px;
    border: 1px solid #ced4da;
    border-radius: 4px;
    box-sizing: border-box;
    margin-bottom: 10px;
    background-color: #fff;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
}

.input-field:focus {
    outline: none;
    border-color: #80bdff;
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
    
</style>