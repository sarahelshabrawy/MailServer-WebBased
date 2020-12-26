<template>
  <div id="controlBar">
    <div id="mycheck"><input type="checkbox" value="all" id="selectAll">&nbsp;Select all</div>
    <div id="trash" @click="$emit('getCheckedMails', true)"><i class="fas fa-trash-alt"></i>&nbsp;Delete</div>
    <div id="move" @click="$emit('openMoveTo')"><i class="fas fa-folder-open"></i>&nbsp;Move E-mails</div>
  </div>
</template>

<script>
export default {
  name: 'ControlBar',
  components: {
  },
  props: {
  },
  data(){
    return{
    }
  },
  mounted() {
    
  },
  methods:{
   
  }
}
</script>

<style scoped>
#controlBar {
  position: fixed;
  display: flex;
  width:1500px;
  height:25px;
  left: 15.8%;
  top: 20.6%;
  color: white;
  background-color: #6f6d72;
  font-family: 'Open sans', serif;
}
#controlBar > div {
  position: absolute;
}
#trash {
  cursor: pointer;
  left: 300px;
}
#mycheck {
  left: 50px;
}
#move {
  left: 550px;
  cursor: pointer;
}
</style>
