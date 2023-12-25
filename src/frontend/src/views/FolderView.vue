<template>
    
        <MailFolder v-if="folderName" :folderName="folderName"></MailFolder>
   
</template>
<script>
import MailFolder from '../components/MailFolder.vue';

export default {
    props:['folderName'],
    components: { MailFolder }
}
</script>
<style>
    
</style>