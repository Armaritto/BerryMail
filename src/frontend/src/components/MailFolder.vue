<template>
    <div class="folder">
        <table v-if="emails">
            <tr v-for="e in emails" :key="e.id">
                <MailPreview :emailMeta="e"></MailPreview>
                <!-- {{e.id}} -->
            </tr>
            
        </table>
    </div>
</template>
<script>
import MailPreview from "@/components/MailPreview.vue";
export default {
    props:['emails'],
    components:{
        MailPreview
    },
    data(){
        return{
        email: {
            id: "876370",
            from: "user1@berry.com",
            to: "user2@berry.com",
            subject: "subject",
            body: "body",
            dateNtime: "2023-12-22T22:42:04.595+00:00",
            priority: "",
            attachment: ""
    },
    
        }
    }
    
    
}
</script>
<style scoped>
    .folder{
        background: #f4f4f4;
        /* height: 500px; */
        width: 705px;
    }
    
</style>