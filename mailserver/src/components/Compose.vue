<template>
<div id="compose">
  <div id="To">
  <label for="to">To:</label>
  <input type="text" id="to" name="to" @change="setReciever()"><br><br>
</div>
<div id="Subject">
   <label  for="subject">Subject: </label>
  <input type="text" id="subject" name="subject"  @change="setSubject()"><br><br>
  </div>

  <div>
<textarea id="textArea" name="w3review" rows="4" cols="50"  @change="setbody()">
  </textarea>
  </div>

   <formatBar @attach_file="setAttachment()" @importance="setImportance()" @send_mail="setmail()"></formatBar>

  </div>
</template>

<script>
let mail= {};
import formatBar from './formatBar.vue'
import axios from 'axios'
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
            importance:"",
            files:[]
        }
    },
  methods:{
    setReciever(){
      var recieverNumber=0;
       this.Receivers[recieverNumber]="";
      var recieve=document.getElementById("to").value;
      for(var i=0;i<recieve.length;i++){
        if(recieve.charAt(i)==" "){
          recieverNumber++;
          this.Receivers[recieverNumber]="";
        }
        else{
           this.Receivers[recieverNumber]+=recieve.charAt(i);
        }
      }
       console.log(this.Receivers)

    },
    setSubject(){
      this.subject=document.getElementById("subject").value;
      console.log(this.subject)

    },
    setbody(){
      this.body=document.getElementById("textArea").value;
      console.log(this.body)

    },
    setAttachment(){
      console.log("helo")
     this.files=document.getElementById("file-input").files; 
     console.log(this.files)
       
    },
    setDate(){
      this.date=new Date();
      console.log(this.date)
      
    },
    setImportance(){
      this.importance=document.getElementById("priority").value;
      console.log(this.importance)

    },
    setmail(){
      this.setDate();
      mail={
        receivers:this.Receivers,
        subject:this.subject,
        body:this.body,
       // attachments:this.files,
        date:this.date,
        priority:this.importance

      },
      console.log(mail)
      axios.post('http://localhost:8085//compose',mail)
      /* axios.get('http://localhost:8085' + '//compose', {
        data:
        {
        receivers:this.Receivers,
        subject:this.subject,
        body:this.body,
       // attachments:this.files,
        date:this.date,
        priority:this.importance
        }
      })
      .then(response => {
        console.log(response.data)
        
      })
      .catch(function (error) {
          console.log(error);
      });
     
      */
    }
   

}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#textArea{
  border: none;
  height: 417px;
  width: 700x;
}

#To{
border-bottom: 1px solid #fabba2;
height: 40px;

}
#Subject{
border-bottom: 1px solid #fabba2;
height: 40px;
}
#to{
  border-color: transparent;
  background-color:t transparent;
  width: 1250px;
}
#subject{
  border-color: transparent;
   width: 1000px;
}

textarea:focus, input:focus{
    outline: 0;
    background-color:transparent ;
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

</style>
