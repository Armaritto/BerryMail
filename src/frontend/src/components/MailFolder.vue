<template>
  <div @contextmenu.prevent="$refs.wrapper.$refs.menu.open($event, 'Payload')">
<!--    <ContextMenu ref="wrapper" reference="menu">-->
<!--      <template slot-scope="{ contextData }">-->
<!--        <ContextMenuItem >-->
<!--          Action 1 {{ contextData }}-->
<!--        </ContextMenuItem>-->
<!--        <ContextMenuItem >-->
<!--          Action 2 {{ contextData }}-->
<!--        </ContextMenuItem>-->
<!--        <ContextMenuItem >-->
<!--          Action 3 {{ contextData }}-->
<!--        </ContextMenuItem>-->
<!--        <ContextMenuItem >-->
<!--          Action 4 {{ contextData }}-->
<!--        </ContextMenuItem>-->
<!--      </template>-->
<!--    </ContextMenu>-->


    <div class="folder">
        <div v-if="emails.length == 0">
            <h1>{{this.folderName}} is empty</h1>
        </div>
        <div v-if="emails.length !== 0">
        <table>
            <div class="sort_options">
            <div class="Priority-group">
            <span class="btn btn-priority" :class="{ 'true': this.sortByTime.value, 'false': !this.sortByTime.value }" @click="handleSort(sortByTime)">
              <div style="display: flex; flex-direction: column; align-items: center">
                <lord-icon
                    src="https://cdn.lordicon.com/abfverha.json"
                    trigger="hover"
                    colors="primary:#ffffff"
                    style="width:30px;height:30px">
                </lord-icon>
                <div>
                  Date
                </div>
              </div>
            </span>
            <span  class="btn btn-priority" :class="{ 'true': this.sortByPriority.value, 'false': !this.sortByPriority.value }" @click="handleSort(sortByPriority)">
              <div style="display: flex; flex-direction: column; align-items: center">
                <lord-icon
                    src="https://cdn.lordicon.com/axteoudt.json"
                    trigger="hover"
                    colors="primary:#ffffff"
                    style="width:30px;height:30px">
                </lord-icon>
                <div>
                  Priority
                </div>
              </div>
            </span>
            </div>
            </div>
<!--            <label for="inputField" class="input-label">Filter By</label>-->
<!--            <select id="inputField" class="input-field">-->
<!--                <option value="">No Filter</option>-->
<!--                <option value="1">Time</option>-->
<!--                <option value="2">Subject</option>-->
<!--                <option value="3">Priority</option>-->
<!--                <option value="4">HELP</option>-->
<!--            </select>-->
          <tr v-for="e in pageEmails" :key="e.id" class="mails" @click="$emit('itemPressed', e.id)">
                <MailPreview :emailMeta="e" ></MailPreview>
          </tr>
        </table>
        <div class="page_nav">
          <span v-if="currentPage > 1" class="btn btn-priority" @click="prevPage()">
            <div style="display: flex; flex-direction: column; align-items: center">
              <lord-icon
                src="https://cdn.lordicon.com/vduvxizq.json"
                trigger="hover"
                style="width:30px;height:30px; transform: scaleX(-1);">
              </lord-icon>
              <div>
                Previous Page
              </div>
            </div>
          </span>
          <span v-if="currentPage < numOfPages" class="btn btn-priority" @click="nextPage()">
            <div style="display: flex; flex-direction: column; align-items: center">
              <lord-icon
                src="https://cdn.lordicon.com/vduvxizq.json"
                trigger="hover"
                style="width:30px;height:30px">
              </lord-icon>
              <div>
                Next Page
              </div>
            </div>
          </span>
        </div>


<!--          <vue-awesome-paginate-->
<!--              :total-items="50"-->
<!--              :items-per-page="5"-->
<!--              :max-pages-shown="5"-->
<!--              :current-page="1"-->
<!--              :on-click="onClickHandler"-->
<!--          />-->
        </div>
    </div>
  </div>
</template>
<script>
import MailPreview from "@/components/MailPreview.vue";
import ContextMenu from "@/components/ContextMenu";
import ContextMenuItem from "@/components/ContextMenuItem";
import {VueAwesomePaginate} from "vue-awesome-paginate";
// import VuePagination from 'vue3-pagination';

// const changePage = (page: number) => console.log('New page: ', page);

export default {
  props: ['clientEmail', 'folderName'],
  watch: {
    folderName: function () {
      this.fetchFolder()
      console.log("fetched")
      this.$emit("folderChanged", this.folderName)
    }
  },
  components: {
    VueAwesomePaginate,
    MailPreview,
    ContextMenu,
    ContextMenuItem
  },
  data() {
    return {
      folders: ["inbox", "sent", "draft", "trash"],
       fetchFolder: function () {
        var url = null
        if (this.folderName) {
          if (!this.folders.includes(this.folderName)) {
            url = "http://localhost:8080/" + "customFolder" + "?"
          } else {
            url = "http://localhost:8080/" + this.folderName + "?"
          }
          const params = {
            email: this.clientEmail + "@berry.com",
            SortCriteria: this.sortBy,
            folderName: this.folderName
          }
          const query = new URLSearchParams(params)
          const method = "GET"
          const body = ""

          fetch(url + query, {method: method})
              .then(res => res.json())
              .then(data => {
                this.emails = data;
                console.log(data)
                this.paginate();
                this.numOfPages = Math.ceil(this.emails.length / this.emailsPerPage)
              })
        }
      },
      paginate: function(){
        var startIndex = (this.currentPage - 1) * this.emailsPerPage
        this.pageEmails = this.emails.slice(startIndex, startIndex + this.emailsPerPage)
        console.log("page" + this.pageEmails)
      },
      emails: [],
      pageEmails: [],
      sortByTime: {value: true},
      sortByPriority: {value: false},
      sortBy: 'Time',
      emailsPerPage: 2,
      currentPage: 1,
      numOfPages: 1
      }
  },
  mounted() {
    this.fetchFolder()
  },
  methods: {
    handleSort(interval) {
      this.sortByTime.value = false
      this.sortByPriority.value = false
      interval.value = true
      if (interval.value === this.sortByTime.value) {
        this.sortByTime.value = true
        this.sortBy = 'Time'
      } else if (interval.value === this.sortByPriority.value) {
        this.sortByPriority.value = true
        this.sortBy = 'Priority'
      }
      this.fetchFolder()
      this.paginate()
    },
    nextPage(){
      this.currentPage++
      this.paginate()
    },
    prevPage(){
      this.currentPage--
      this.paginate()
    }
    // onClickHandler: function(page){
    //   console.log(page);
    // }
  }
}
</script>
<style scoped>
.sort_options{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
  margin-left: 20px;
}
.page_nav{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
  margin-left: 20px;
}
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
    width: 70px;
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
        font-size: 20px;
        color: white;
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
