<template>
  <div id="renameFolder">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="header">
            Rename Folder
          </div>
            <label for="name"></label>
            <input type="text" placeholder="Folder Name" name="name" id="folder-rename">
            <div id="message-folder-rename"></div>
            <button class="modal-default-button" id="okBtn-rename" @click="addFolderName">
                Ok
            </button>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RenameFolder',
  components: {
  },
  data(){
    return{
      newFolderName :''
    }
  },
  mounted() {
    
  },
  methods:{
    addFolderName()
    {
      var text = document.getElementById("folder-rename");
      this.newFolderName = text.value;
      console.log(this.newFolderName);
      this.$emit('renameFolder', this.newFolderName);
    }
  }
}
</script>

<style scoped>
</style>
