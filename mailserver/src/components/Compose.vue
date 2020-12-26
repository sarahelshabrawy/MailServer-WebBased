<template>

 
<div id="compose">

 <div>
  <formatBar id="formatBar"  @attach_file="setAttachment" @importance="setImportance()" @send_mail="setmail" ></formatBar>
  </div>

  <div id="Subject_">
   <label  for="subject">Subject:  </label>
  <input type="text" id="subject" name="subject"  @change="setSubject()"><br><br>
  </div>
  <div id="To_">
    <span id="Recievers">
  <label for="to">To:</label>
  <input type="text" id="to" name="to" >
    </span>
  <span><i class="fas fa-plus" @click="addReciever"></i> </span>
  
</div>



  <div>
<textarea id="textArea" name="w3review" rows="4" cols="50"  @change="setbody()">
  </textarea>
  </div>
</div>

</template>

<script>
let mail= {};
import formatBar from './formatBar.vue'
import axios from 'axios'
let apiUrl = 'http://localhost:8085//';
export default {
  name: 'Compose',
 
  components: {
 formatBar
  },
   data() {
        return {
            Receivers:[],
            subject:"",
            body:"",
            date:"",
            importance:0,
            secondaryRecieversCount:0,
            file:new FormData(),

        }
    },
  methods:{
     addReciever(){
      const recieversContainer = document.getElementById("Recievers")
      const input = document.createElement("input");
      input.type = "text";
      input.style.width = "250px";
      input.style.borderRadius="12px";
      input.style.border= "2px solid #fabba2" ;
      input.id= "Secondary Reciever "+ (parseInt(this.secondaryRecieversCount) + 1 );
      this.secondaryRecieversCount ++;
      recieversContainer.appendChild(input);
     
    },
    setReciever(){
      this.Receivers[0]=document.getElementById("to").value;
     for(var i = 0;i<this.secondaryRecieversCount;i++){
          this.Receivers[i+1]=document.getElementById("Secondary Reciever "+ (parseInt(i) + 1 )).value;
       }
        
      },
    setSubject(){
      this.subject=document.getElementById("subject").value;
      console.log(this.subject)
    },
    setbody(){
      this.body=document.getElementById("textArea").value;
      console.log(this.body)
    },
    setAttachment(file){
      console.log("helo")
      // const formData = ;
     // this.files=document.getElementById("file-input").files;

      // const formData = ;
     // this.files=document.getElementById("file-input").files;

      this.file.append('file',file)
      // this.file = formData
      // this.file.push(formData)
      console.log(this.file)
       
    },
    setDate(){
      this.date=new Date();
      console.log(this.date)
      
    },
    setImportance(){
      const priority = document.getElementById("priority").value;
      if(priority==="Very Important"){
        this.importance=1;
      }
      else if(priority==="Important"){
        this.importance=2;
      }
      else if(priority==="Normal"){
        this.importance=3;
      }
       else {
        this.importance=4;
      }
      console.log(this.importance)
    },
    async setmail(folder){
      console.log(folder)
      this.setDate();
      this.setImportance();
      this.setReciever();
      mail={
        receivers:this.Receivers,
        subject:this.subject,
        body:this.body,
        // attachments:this.file,
        date:this.date,
        priority:this.importance
      }
      console.log(mail)

      await axios.post(apiUrl+"/attachment",this.file)
      .then(response => {
        console.log(response.data)
      })
      .catch(function (error) {
          console.log(error);
      });

      axios.post(apiUrl+folder,mail)
      // axios.get(apiUrl+folder,{
      //   params: {
      //     receivers: encodeURI(this.Receivers),
      //     subject: encodeURI(this.subject),
      //     body: encodeURI(this.body),
      //     date: encodeURI(this.date),
      //     priority: encodeURI(this.importance)
      //   }
      // })
      .then(response => {
        console.log(response.data)
      })
      .catch(function (error) {
          console.log(error);
      });

    }
}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#textArea{
  border: none;
  height: 450px;
  width: 1250px;
}
#Recievers{
   display: inline-block;
}
#Subject_{
height: 40px;
}
#to{
  background-color:transparent;
  border-radius: 12px;
  width: 250px;
}
#subject{
 
   border-radius: 12px;
   width: 1200px;
}
input[type=text] {
  border: 2px solid #fabba2;
 
}
textarea:focus, input:focus{
 
    outline: 0;
}
.button {
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}
.Send{
 
  background-color: #008CBA; 
  color: black; 
  border: 2px solid #008CBA;
  height: 50px;
  width: 100px;
}
.format{
  padding-top: 20px;
}
/* #formatBar{
 position: absolute;
} */
</style>