<template>
  <div id ="hi">
    <div id = "new" class="MailBox">
        <div id = "sender">{{mail.sender}}</div>
        <div id = "subject">{{mail.subject + "  - "}}</div>
        <div id = "contentxx">{{ mail.body}}</div>
        <div id = "date" >{{mail.date}}</div>
    </div>
  </div>
</template>

<script>

export default {
    name: 'MailBox',
    props: {
        mail: Object,
        id:String,
        searchResults:[]
    },
    components: {
    },
    data(){
        return{
        }
    },
    methods:{
      boxChecked()
      {
        console.log("hiiiiiiiiiiiii");
      },
      highlight(element,keys){
      const inputText = document.getElementById(element);
      let innerHTML = inputText.innerHTML;
      let newHTML = "";
      console.log(innerHTML)
      console.log("KEEEYSS")
      console.log(keys)
      let from = 0;
      for(let i = 0 ;i < keys.length ; i++){
        newHTML = newHTML + innerHTML.substring(from,keys[i].start) + "<span style='background-color: " + "yellow" + ";'>"
            + innerHTML.substring(keys[i].start,keys[i].end+1) + "</span>"
        from = keys[i].end+1
      }
      newHTML = newHTML + innerHTML.substring(from);
      inputText.innerHTML = newHTML;
      console.log(inputText.innerHTML)
    },
        showSearchResults() {
          const temp = JSON.parse(JSON.stringify(this.searchResults));
          const mySearchResults = temp[0];
          if(!mySearchResults)
            return
          console.log(mySearchResults)
          console.log("LALALA")
          const subjectOccurrences = JSON.parse(JSON.stringify(mySearchResults.subjectOccurrences))
          const bodyOccurrences = JSON.parse(JSON.stringify(mySearchResults.bodyOccurrences))
          const senderOccurrences = JSON.parse(JSON.stringify(mySearchResults.senderOccurrences))
          const dateOccurrences = JSON.parse(JSON.stringify(mySearchResults.dateOccurrences))
          // const priorityOccurrences= JSON.parse(JSON.stringify(mySearchResults.subjectOccurrences))
          console.log(subjectOccurrences)
          if(subjectOccurrences)
          this.highlight("subject",mySearchResults.subjectOccurrences)

          if(bodyOccurrences)
          this.highlight("contentxx",mySearchResults.bodyOccurrences)

          if(senderOccurrences)
          this.highlight("sender",mySearchResults.senderOccurrences)

          // this.highlight(temp.priorityOccurrences)
          if(dateOccurrences)
          this.highlight("date",mySearchResults.dateOccurrences)
        }
    },
    mounted() {
      const divh = document.getElementById("new");
      divh.id = this.id
      console.log("مرت سارة من هنا")
        console.log(this.searchResults)
      this.showSearchResults()
    }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.MailBox {
    height: 25px;
    padding: 10px 10px;
    width :100%;
    border-width: 0.1px;
    background-color: #fce0d59c;
    margin: 1px;
    font-family: 'Open sans', serif;
}
.MailBox:hover{
    /* background-color: rgb(214, 214, 168); */
    box-shadow: 0 0 15px #422d25; 
}
#hi{
    padding: 0;
    margin: 0;
    width :100%;
}
div {
    overflow: hidden;
    float: left;
    text-align: left;
    font-size: 17px;
}

#contentxx{
    width: 40%;
    color:gray;
    margin: 0;
    height: 20px;
}
#date{
    margin-left: 0;
    width: 15%;
    text-align: right;
    height: 20px;
}
#sender{
    display: inline-block;
    font-weight:600;
    width: 25%;
    height: 20px;
    left: 10%;
    margin-left:20px ;

    /* letter-spacing: 2px; */
    font-size: 17px;
}
#subject{
    width: 8.5%;
    margin: 0;
    height: 20px;

}

</style>
