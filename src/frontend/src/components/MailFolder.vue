<template>
    
    <div class="folder">
        <div v-if="emails.length == 0">
            <h1>{{this.folderName}} is empty</h1>
        </div>

        <table v-if="emails">
            <tr v-for="e in emails" :key="e.id">
                <MailPreview :emailMeta="e"></MailPreview>
            </tr>
            
        </table>
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
            fetchFolder: function(){
                if(this.folderName){
        const url = "http://localhost:8080/" + this.folderName +"?"
        const params = {
          email: this.clientEmail + "@berry.com",
          SortCriteria:""
        }
        const query = new URLSearchParams(params)
        const method = "GET"
        const body = ""
        
        fetch(url+query, {method: method})
        .then(res => res.json())
        .then(data => this.emails = data)

    }
            },
        emails:[]
        }
    },
    mounted(){
        this.fetchFolder()
    }
    
    
}
</script>
<style scoped>
    .folder{
        background: #f4f4f4;
        /* height: 500px; */
        width: 405px;
    }
    
</style>