<template>
        <div key="">
        <MailFolder  :clientEmail="clientEmail" :folderName="folderName"></MailFolder>
        </div>
</template>
<script>
import MailFolder from '../components/MailFolder.vue';

export default {
    props:['clientEmail', 'folderName'],
    watch: { 
    folderName: function() {
          console.log("changed")
        }
    },
    components: { MailFolder },
    mounted(){
        console.log("FV mounted");
    }
}
</script>
<style>
    
</style>