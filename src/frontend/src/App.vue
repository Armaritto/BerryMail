<template>
  <!-- <main></main> -->
  <!-- <div>
    <MailEditor :myEmail="myEmail"></MailEditor>  
  </div> -->
  <!-- <Mail :email="email"></Mail>
  <MailPreview :emailMeta="email"></MailPreview> -->
 <MailFolder :emails="emails"></MailFolder>

</template>

<script>
// import main from "@/components/main.vue";
import MailEditor from "@/components/MailEditor.vue";
import Mail from "@/components/Mail.vue";
import MailPreview from "@/components/MailPreview.vue";
import MailFolder from "@/components/MailFolder.vue";

export default {
  name: 'App',
  data(){
    return{
      myEmail:"user1@berry.com",
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
    emails: [ {
            id: "976370",
            from: "user1@berry.com",
            to: "user2@berry.com",
            subject: "subject1",
            body: "body",
            dateNtime: "2023-12-22T22:42:04.595+00:00",
            priority: "",
            attachment: ""
    },  {
            id: "876370",
            from: "user1@berry.com",
            to: "user2@berry.com",
            subject: "subject2",
            body: "body",
            dateNtime: "2023-12-22T22:42:04.595+00:00",
            priority: "",
            attachment: ""
    },  {
            id: "776370",
            from: "user1@berry.com",
            to: "user2@berry.com",
            subject: "subject3",
            body: "body",
            dateNtime: "2023-12-22T22:42:04.595+00:00",
            priority: "",
            attachment: ""
    }]
    }
  },
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
