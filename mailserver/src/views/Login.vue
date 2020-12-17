<template>
  <div id="background">
    <div class="container">
      <h1>Login</h1>
      <hr>

      <label for="email"></label>
      <input type="text" placeholder="Email" name="email" id="email" required>

      <label for="psw"></label>
      <input type="password" placeholder="Password" name="psw" id="psw" required>

      <hr>
      <button type="submit" class="registerbtn" >Sign in</button>

    </div>

  </div>
</template>

<script>

export default {
  name: "Register",
  data(){
    return{
    }
  },
  methods(){

  }
}
</script>

<style scoped>

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

#background{
  position: absolute;
  top: 0; right: 0; bottom: 0; left: 0;
  background-image: linear-gradient(rgba(0,0,0,0.1),rgba(0,0,0,4)),url('login.jpg');
  background-size:cover;
  background-position: center;
}
/* Add padding to containers */
.container {
  text-align: center;
  width: 20%;
  margin: 150px auto auto;
  padding: 16px;
  background: white;
  position: relative;
  border-radius: 15px;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 10px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  border-bottom: grey 1px solid;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
h1{
  margin-bottom: 10px;

}
/* Set a style for the submit/register button */
.registerbtn {
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

.registerbtn:hover {
  opacity:1;
}

/* Add a blue text color to links */
a {
  color: #f5d5e0;
}


</style>