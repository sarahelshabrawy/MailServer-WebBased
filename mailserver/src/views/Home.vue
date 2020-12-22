<template>
  <div id="home">
    <div id="brand">
        <h1>COMET</h1>
        <img src="..\assets\1.jpg" id="image">
        <div id="right-side">
          <div id="account-menu">
            <div id="account" @mouseover="display" @mouseout="hide"></div>
            <div id="account-info">
              <div>
                <div>Name</div>
                <div id="name">Aya Elsayed</div>
              </div>
              <div>
                <div>Email</div>
                <div id="email">ayaelsayed5209297897896896767676786@comet.com</div>
              </div>
            </div>
          </div>
          <div id="sign-out"><router-link id="sign-out-link" to="/register">Sign out</router-link>&nbsp; <i class="fas fa-sign-out-alt"></i></div>
        </div>
    </div>
    <div id="upper-bar"> 
      <div id="sort">
        <div id="sortBy"><i class="fas fa-sort"></i>  SORT BY</div> <i class="fas fa-caret-down list-icon" id="list-icon"></i>
        <div id="mySortList">
          <div id="first-item" @click="clickSort">Date</div>
          <ul id="sortList">
            <li @click="clickItem">Senders</li>
            <li @click="clickItem">Receivers</li>
            <li @click="clickItem">Importance</li>
            <li @click="clickItem">Subject</li>
            <li @click="clickItem">Body</li>
            <li @click="clickItem">Attachments</li>
          </ul>
        </div>
      </div>
      <div id="filtering">
        <div id="filter" @click="clickFilter">Filter results&nbsp;<i class="fas fa-caret-down check-icon" id="check-icon"></i></div>
        <div id="check-menu">
          <div>
          <input type="checkbox" value="sender">
          <label >Sender</label>
          </div>
          <div>
          <input type="checkbox" value="receiver">
          <label>Receiver</label>
          </div>
          <div>
          <input type="checkbox" value="date">
          <label>Date</label>
          </div>
           <div>
          <input type="checkbox" value="subject">
          <label>Subject</label>
          </div>
           <div>
          <input type="checkbox" value="importance">
          <label>Importance</label>
          </div>
           <div>
          <input type="checkbox" value="body">
          <label>Body</label>
          </div>
           <div>
          <input type="checkbox" value="attachment">
          <label>Attachment</label>
          </div>
        </div>
      </div>
      <div id="search">
        <input key="text" id="text" value="Search In Your Emails" @click="searchBar">
        <i class="fas fa-search searching"></i>
      </div>
    </div>
    <a href="#" class="float" id = "compose" @click="component = 'compose'" >
    <i class="fas fa-bars my-float"></i>
    </a>
    <div id="body">
      <div id="menu"> hi
      </div>
      <div id="content" >
        <!-- <component v-bind:is="component"  v-bind="Mails"></component> -->
        <mail-view  :maillist = "Mails" ></mail-view>
      </div>
    </div>
    <div id="side-bar">
        <div id="mycheck"><input type="checkbox" value="all" id="selectAll">Select all</div>
        <div id="trash"><i class="fas fa-trash-alt"></i> Delete</div>
        <div id="move"><i class="fas fa-folder-open"></i>  Move E-mails</div>
    </div>
  </div>
</template>

<script>
import MailView from '../components/MailView.vue'
// import MailsContent from '../components/MailsContent.vue'
// import Compose from '../components/Compose.vue'
import axios from 'axios'
let apiUrl = 'http://localhost:8085'
export default {
  name: 'Home',
  components: {
    // 'mails-content':MailsContent,
    'mail-view':MailView,
    // 'compose':Compose
  },
  data()
  {
    return{
      component:'mail-view',
      Mails:[],
      prop:this.Mails,
      currentFolder:"inbox"
    }
  },
  beforeMount(){
  },
  methods : {
    getMails(){
      axios.get(apiUrl + "/getMails", {
        params:{
          folderName : this.currentFolder
        }
      }).then(Response => {
        this.Mails = [];
        let indices = Object.keys( Response.data )
        for(let i = 0 ;i < indices.length ; i++){
          this.Mails[i] = {
            id : Response.data[indices[i]].id,
            subject : Response.data[indices[i]].subject,
            sender : Response.data[indices[i]].sender,
            body : Response.data[indices[i]].body,
            date : Response.data[indices[i]].date,
            receiver : Response.data[indices[i]].receiver
          }
          console.log(this.Mails[i])
        }
      })
    },

    searchBar() {
      var myvalue =  document.getElementById("text").value ;
      if(myvalue == "Search In Your Emails"){
        document.getElementById("text").value = "";
      }
    },
    clickItem(e){
      var listItem = e.target.innerHTML;
      var firstItem = document.getElementById("first-item");
      e.target.innerHTML = firstItem.innerHTML;
      firstItem.innerHTML = listItem;
    },
    clickSort(){
      var list = document.getElementById("sortList")
      var icon = document.getElementById("list-icon")
      if(list.style.display === "none")
      {
        list.style.display = "block";
        icon.className = "fas fa-caret-up list-icon";
      }
      else
      {
        list.style.display = "none";
        icon.className = "fas fa-caret-down list-icon";
      }
    },
    clickFilter(){
      var box = document.getElementById("check-menu");
      var icon = document.getElementById("check-icon")
      if(box.style.display === "none")
      {
        box.style.display = "grid";
        icon.className = "fas fa-caret-up check-icon";
      }
      else
      {
        box.style.display = "none";
        icon.className = "fas fa-caret-down check-icon"
      }
    },
    display()
    {
      var acc = document.getElementById("account-info");
      acc.style.display = "block";
    },
    hide()
    {
      var acc = document.getElementById("account-info");
      acc.style.display = "none";
    }
  }
}
</script>

<style scoped>
#home {
  margin: 0;
  padding: 0;
}
#brand {
    object-fit: fill;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px;
    font-weight: 700;
    font-size: 16px;
    padding: 12px;
    width: 98.7vw;
    margin-left: -0.6%;
    margin-top: -1.3%;
    color: white;
    background-color: black;
    font-family: 'Ubuntu', sans-serif;
    overflow: hidden;
}
#image {
    flex-shrink: 0;
    width:1710px;
    height: 1300px;
    display: block;
    margin-top: -17%;
}
#brand h1 {
    position: absolute;
    margin-left: -80%;
    letter-spacing: 5px;

}
#right-side {
  position: absolute;
  display: flex;
  left: 75%;
  top: 7%;
  font-family: 'Open sans', serif;
  font-weight: 100;
  letter-spacing: 3px;
}
#right-side > div {
    margin-left: 40px;
    cursor: pointer;
}
#sign-out {
  font-family: 'Open sans', serif;
  font-weight: 100;
  letter-spacing: 1px;
  margin-top: 8px;
}
#sign-out-link {
  text-decoration: none;
  color: white;
}
#account {
  height: 50px;
  width: 50px;
  background-image: url(../assets/pic.jpg);
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-color: black;
  border: 1px solid transparent;
  border-radius: 50px;
  margin-top: -7px;
  margin-right: -15px;
}
.float{
	position:fixed;
	width:60px;
	height:60px;
  top: 150px;
  left: 90px;
  color: #4a478a;
	background-color:white;
	border-radius:50px;
	text-align:center;
	box-shadow: 2px 2px 3px #999;
}
.my-float {
  margin-top: 20px;
}
.float:hover {
  background-color:  #da8a8b;
  color: white;
}
#upper-bar {
  display: flex;
  width: 100vw;
  margin-left: -0.5%;
  height: 55px;
  background-color: #081448;
}

#search {
  transform: translate(200%, 20%);
}
#text {
  width: 330px;
  height: 23px;
  border: 5px solid transparent;
  border-radius: 30px;
  padding-left: 15px;
  color: #6b6464;
  font-size: 15px;
  font-family: 'Open sans', serif;
  outline: none;
}
.searching {
  font-size: 20px;
  margin-left: -40px;
  color: #6b6464;
}
#body {
  display: flex;
  margin-left: -1%;
  width: 102%;
}
#menu {
  background-color: #4a478a;
  height: 650px;
  width: 300px;
}
#content {
  background-color: white;
  height: 650px;
  width: 1500px;
  padding-top: 25px;
}
#side-bar {
  position: fixed;
  display: flex;
	width:1500px;
	height:25px;
  left: 16.3%;
  top: 20.6%;
  color: white;
	background-color: #6f6d72;
  font-family: 'Open sans', serif;
}
#side-bar > div {
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
#sort {
  transform: translate(100%, 20%);
  display: flex;
  font-family: 'Open sans', serif;
  z-index: 9999999999999;

}
#sortBy {
  color: white;
  margin-right: 20px;
  letter-spacing: 3px;
  font-weight: 100;
  font-size: 16px;
  margin-top: 5px;
}
#sortList {
  margin-top: 0px;
  border: 2px, solid, transparent;
  background-color: white;
  color: #081448;
  text-transform: uppercase;
  letter-spacing: 1px;
  list-style: none;
  height: 240px;
  font-size: 17px;
  width: 160px;
  border-bottom-right-radius: 5px;
  border-bottom-left-radius: 5px;
  box-shadow: 2px 2px 3px #999;
  padding-top: 3px;
  display: none;
}
#sortList li {
  margin-left: -40px;
  cursor: pointer;
  width: 175px;
  height: 35px;
  padding-left: 25px;
  padding-top: 5px;
}
#sortList li:hover {
  background-color: #081448;
  color: white;
}
#first-item {
  height: 30px;
  background-color: white;
  color: #081448;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-size: 17px;
  padding-left: 22px;
  padding-top: 5px;
  border: 2px, solid, transparent;
  border-radius: 5px;
  cursor: pointer;
  width: 180px;
}
.list-icon {
  color: #081448;
  position: absolute;
  left: 310px;
  font-size: 18px;
  top: 7px;
}
#filtering {
  transform: translate(420%, 25%);
  z-index: 9999999999999;
}
#filter {
  background-color: #fabaa0;
  width: 150px;
  height: 25px;
  font-size: 18px;
  font-family: 'Open sans', serif;
  letter-spacing: 1px;
  color: white;
  padding-top: 3px;
  padding-left: 7px;
  border: 1px, solid, transparent;
  border-radius: 50px;
  cursor: pointer;
}
#check-menu {
  position: absolute;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  background-color: white;
  width: 300px;
  height: 150px;
  margin-top: 15px;
  box-shadow: 2px 2px 3px #999;
  color: #081448;
  font-family: 'Open sans', serif;
  letter-spacing: 1px;
  display: none;
}
#account-info {
  font-size: 15px;
  position: absolute;
  background-color: white;
  z-index: 99999999;
  padding: 10px;
  width: 200px;
  color: rgb(119, 111, 111);
  min-height: 100%;
  letter-spacing: 1px;
  left: 70px;
  border: 1px solid white;
  border-radius: 20px;
  border-top-left-radius: 0;
  word-wrap: break-word;
  display: none;
  box-shadow: 2px 2px 3px #999;
}
#account-info > div {
  margin-top: 10px;
}
#email, #name {
  color: #081448;
  font-weight: 600;
}
</style>
