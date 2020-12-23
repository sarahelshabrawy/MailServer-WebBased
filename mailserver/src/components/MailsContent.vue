<template>
  <div class="MailsContent" >
    <div class = "Menu">
      <button id="delete"><i class="material-icons">delete</i></button>
      <button id="Move"><i class="material-icons">move_to_inbox</i></button>      
      <div class="input_slider">
        <label class="switch">
        <input type="checkbox" checked>
        <span class="slider round"></span>
        </label>
      </div>
      <label class="important">Important:</label> 
      <h1>{{mail.subject}}</h1>
    </div>   
    <a v-for="attach in mail.attachments" :key="attach.id" :href="attach" >
      <img :src= "attach" :alt= "attach" download >
    </a>
    
    <div class="sender">{{"from : " + mail.sender}}</div>
    <h3>{{mail.body}}</h3>
  </div>
</template>

<script>
import axios from 'axios'
let apiUrl = 'http://localhost:8085'
export default {
  name: 'MailsContent',
  props: {
    id:String,
    currentFolder:String
  },
  data(){
    return{
      beforeMount:true,
      mail:{}
    }
  },
  beforeMount(){
    if(this.beforeMount)
      this.getMailContent()
  },
  methods:{
    getMailContent(){
      axios.get(apiUrl + "/openMail", {
        params:{
          id:this.id,
          currentFolder:this.currentFolder
        }
      }).then(Response =>{
        this.mail = {
          subject : Response.data.subject,
          body : Response.data.body,
          Attachments : Response.data.attachments,
          sender : Response.data.sender
        }
        console.log(this.mail)
      })
      this.beforeMount = false
    }

  },
  mounted() {
    console.log(this.mail)
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.MailsContent{
  background: linear-gradient(90deg,  #ffdde9 0%,white 100%);
  padding:0px ;
  height: 600px;
}
.Menu{
  border: 0.1px solid rgb(253, 231, 246);
  background-color: #ffdde9;
  height: 45px;
  width: 100%;
  margin: 0px;
  top: -10%;
  padding: 0px;
  text-align: left;
}
.Menu button,.Menu label,.input_slider{
  float: right;
  display: block;
}
.Menu h1{
  margin: 5px;
  font-family: 'Open sans', serif;
  letter-spacing: 2px;
}

.sender{
  background-color: rgb(255, 247, 254);
  color: #4a478a;
  display: inline-block;
  margin: 10px;
  border: 1px solid transparent;
  border-radius: 10px;
  font-family: 'Open sans', serif ;
  font-weight: 100;
  letter-spacing: 3px;
}
button{
  background-color:rgb(250, 233, 255);
  border: none;
  height: 40px;
  width: 40px;
  margin:3px 7px;
  outline: none;
  top: 0%;
  border-radius: 50%;
}
button:hover{
  background-color:#f5d5e0;
}

h1,h4,h3{
  margin: 10px;
  text-align: left;
  font-family: 'Open sans', serif;
  letter-spacing:1px
}
/* Important */
.input_slider{
  padding:0 ;
  margin: 9px;
  margin-right: 50px;
}
.important{
  margin: 12px;
  margin-right: 0;
}
.switch {
  position: relative;
  display: inline-block;
  width: 52px;
  height: 25px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 18px;
  width: 18px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #f321ad;
}

input:focus + .slider {
  box-shadow: 0 0 1px #f321ad;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}

</style>
