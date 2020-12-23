<template>
  <div id ="hi">
    <div id = "new" class="MailBox">
        <div>
            <input type="checkbox" >
        </div>
        <div class = "sender">{{mail.sender}}</div>
        <div class = "subject">{{mail.subject + "  - "}}</div>
        <div class = "content">{{ mail.body}}</div>
        <div class = "date" >{{mail.date}}</div>
    </div>
  </div>
</template>

<script>

export default {
    name: 'MailBox',
    props: {
        mail: Object,
        id:String
    },
    components: {
    },
    data(){
        return{
        }
    },
    methods:{
    },
    mounted() {
        var divh = document.getElementById("new");
        divh.id = this.id 
        console.log(divh.id)
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

.content{
    width: 40%;
    color:gray;
    margin: 0;
    height: 20px;
}
.date{
    margin-left: 0px;
    width: 15%;
    text-align: right;
    height: 20px;
}
.sender{
    display: inline-block;
    font-weight:600;
    width: 25%;
    height: 20px;
    left: 10%;
    margin-left:20px ;

    /* letter-spacing: 2px; */
    font-size: 17px;
}
.subject{
    width: 8.5%;
    margin: 0;
    height: 20px;

}

</style>
