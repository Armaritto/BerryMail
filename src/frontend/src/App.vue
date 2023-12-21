<template>
  <!-- <main></main> -->
  <!-- <Mail></Mail>  
  <MailEditor></MailEditor> -->
  <!-- <MailPreview></MailPreview> -->
  <MailFolder></MailFolder>

</template>

<script>
// import main from "@/components/main.vue";
import MailEditor from "@/components/MailEditor.vue";
import Mail from "@/components/Mail.vue";
import MailPreview from "@/components/MailPreview.vue";
import MailFolder from "@/components/MailFolder.vue";

export default {
  name: 'App',
  components: {
    // main,
    MailEditor,
    Mail,
    MailPreview,
    MailFolder
  }
}
</script>
<style>

</style>
