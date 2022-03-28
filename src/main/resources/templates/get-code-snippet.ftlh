
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
                 <style>
                  #load_date{color:green}
                   #code_snippet{color:black}
                 </style>
                 <link rel="stylesheet"
                        href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
                 <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
                 <script>hljs.initHighlightingOnLoad();</script>
                  </head>
                  <body>
                <span id="load_date">${code.date}</span>
                <br>
                  <#if (code.time >0) && ((code.views == 0) && (code.lastNumber == 1)) && code.restriction == true >
                  <span id="views_restriction"> <u>${code.views}</u> more views allowed</span>
                  <br>
                  <span id="time_restriction"> The code will be available for <u>${code.time}</u> seconds</span>
                   <pre id="code_snippet"><code>${code.code}</code></pre>
                <#elseif ((code.time < 1) && (code.timeUp == false)) && (code.views >= 0) && code.restriction == true >
                <span id="views_restriction"> <u>${code.views}</u> more views allowed</span>
                <pre id="code_snippet"><code>${code.code}</code></pre>
                <#elseif (code.time >= 0) && (code.views <1) && code.restriction == true>
                <span id="time_restriction"> The code will be available for <u>${code.time}</u> seconds</span>
                <pre id="code_snippet"><code>${code.code}</code></pre>
                <#elseif (code.time >= 0) && (code.views >=0) && (code.restriction == true)>
                 <span id="views_restriction"> <u>${code.views}</u> more views allowed</span>
                 <br>
                 <span id="time_restriction"> The code will be available for <u>${code.time}</u> seconds</span>
                  <pre id="code_snippet"><code>${code.code}</code></pre>
                <#else>
                <pre id="code_snippet"><code>${code.code}</code></pre>
                </#if>
 </body>
</html>


