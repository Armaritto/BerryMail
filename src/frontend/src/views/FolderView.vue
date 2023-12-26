<template>
        <div key="">
        <MailFolder  :folderName="folderName"></MailFolder>
        </div>
</template>
<script>
import MailFolder from '../components/MailFolder.vue';

export default {
    props:['folderName'],
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