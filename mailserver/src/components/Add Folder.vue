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
            <button class="modal-default-button" id="okBtn" @click="addText">
                OK
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
      this.$emit('closeFolder', this.folderName);
    }
  }
}
</script>

<style scoped>
.modal-mask {
position: fixed;
z-index: 9998;
top: 0;
left: 0;
width: 100%;
height: 100%;
background-color: rgba(0, 0, 0, 0.5);
display: table;
transition: opacity 0.3s ease;
}

.modal-wrapper {
display: table-cell;
vertical-align: middle;
}

.modal-container {
width: 300px;
margin: 0 auto;
padding: 20px 30px;
background-color: #fff;
border-radius: 30px;
box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
transition: all 0.3s ease;
font-family: Helvetica, Arial, sans-serif;
}


.modal-default-button {
  background: linear-gradient(to right,#210535,#7b337d);
  color: white;
  font-size: 15px;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
  outline: none;
  border-radius: 30px;

}


/* Full-width input fields */
input[type=text] {
  width: 100%;
  padding: 5px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  border-bottom: grey 1px solid;
}

input[type=text]:focus {
  background-color: #ddd;
  outline: none;
}

.header{
  text-align: center;
  margin-bottom: 20px;
  font-size: 20px;
  font-family: "Arial Rounded MT Bold",sans-serif;
  font-weight: bold;
}

</style>
