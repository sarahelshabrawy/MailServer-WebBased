<template>
  <div id="addFolder">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="header">
            Add New Folder
          </div>
            <label for="name"></label>
            <input type="text" placeholder="Folder Name" name="name" id="folder">
            <div id="message-folder"></div>
            <button class="modal-default-button" id="okBtn" @click="addText">
                Ok
            </button>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AddFolder',
  components: {
  },
  data(){
    return{
      folderName :''
    }
  },
  mounted() {
    
  },
  methods:{
    addText()
    {
      var text = document.getElementById("folder");
      this.folderName = text.value;
      console.log(this.folderName);
      this.$emit('sendFolder', this.folderName);
    }
  }
}
</script>

<style scoped>
.header {
    padding-bottom: 20px;
}
</style>
