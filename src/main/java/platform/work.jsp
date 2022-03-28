  <html>
  <head>
  <title>Create</title>
  <style>
    #send_snippet{color:black}
     #code_snippet{color:black}
  </style>
  </head>
  <body>
  <form>
     <textarea placeholder="Your code goes here"id="code_snippet"  rows="10" cols="10"> </textarea>
      <br><br>
      <button id="send_snippet" type="submit" onclick="send()">Submit</button>
      </form>
      <script>
      function send() {
          let object = {
              "code": document.getElementById("code_snippet").value
          };
          let json = JSON.stringify(object);
          let xhr = new XMLHttpRequest();
          xhr.open("POST", '/api/code/new', false)
          xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
          xhr.send(json);

          if (xhr.status == 200) {
            alert("Success!");
          }
      }
      </script>
  </body>
  </html>