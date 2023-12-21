<template>
    <div class="folder">
        <table>
            <tr>
                <MailPreview></MailPreview>
            </tr>
            <tr>
                <MailPreview></MailPreview>
            </tr>
            <tr>
                <MailPreview></MailPreview>
            </tr>
            <tr>
                <MailPreview></MailPreview>
            </tr>
            <tr>
                <MailPreview></MailPreview>
            </tr>
        </table>
    </div>
</template>
<script>
import MailPreview from "@/components/MailPreview.vue";
export default {
    components:{
        MailPreview
    }
    
}
</script>
<style scoped>
    .folder{
        background: rgb(74, 38, 107, 0.2);
        /* height: 500px; */
        width: 705px;
    }
    
</style>