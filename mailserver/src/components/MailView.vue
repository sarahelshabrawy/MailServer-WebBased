<template>
  <div>
    <div id = "mails_view" class="Mails"  v-for="male in maillist" :key="male.id" >
      <mail-box  :mail= "male" :id= "male.id" ></mail-box> 
    </div>
  </div>
</template>

<script>
import MailBox from './MailBox.vue'
export default {
  components: { MailBox },
  name: 'MailView',
  props: {
    maillist: Array
  },
  data(){
    return{
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
