
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <style>
                  #send_snippet{color:black}
                  #code_snippet{color:black}
                  #views_restriction{color:black}
                  #time_restriction{color:black}
                 </style>
      </head>
  <body>
 <form>
         <textarea placeholder="Your code goes here" id="code_snippet"  rows=20 cols=60> </textarea>
         <br><br>
          <label for="time_restriction">Time restriction:</label>
         <input id="time_restriction" type="text"/><br><br>
          <label for="views_restriction">Views restriction:</label>
         <input id="views_restriction" type="text"/>
         <br><br>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
        </form>
      <script>
           function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction").value
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