<template>
  <div>
    <div id = "mails_view" class="Mails"  v-for="male in maillist" :key="male.id" >
      <!-- <mail-box  :mail= "male" :id= "male.id" ></mail-box>  -->
      <component @click="component = 'mails-content'" :is="component" v-bind:id = "male.id" :mail="male" :currentFolder="currentFolder" ></component>
    </div>
  </div>
</template>

<script>
import MailBox from './MailBox.vue'
import MailsContent from './MailsContent.vue'
export default {
  components: {
    'mail-box': MailBox ,
    'mails-content':MailsContent
  },
  name: 'MailView',
  props: {
    maillist: Array,
    currentFolder:String
  },
  data(){
    return{
      
      component:'mail-box'
    }
  },
  methods:{
    change(){
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
