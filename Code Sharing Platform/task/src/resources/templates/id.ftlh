<!DOCTYPE HTML>
<html>
<head>
    <title>Code</title>
    <meta charset=UTF-8">
    <style>span {color: forestgreen; font-family: "Myriad Pro Light"}
        pre {border: 2px solid grey; width: 40%; background-color: ghostwhite}
    </style>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">${code.date}</span>
    <#if code.viewsRestriction == true>
        <p><u><span id="views_restriction" style="">${code.views}</span></u> more views allowed</p>
    </#if>
    <#if code.timeRestriction == true>
        <p>The code will be available for <u><span id="time_restriction">${code.time}</span></u> seconds</p>
    </#if>
    <pre id="code_snippet"><code>${code.code}</code></pre>
</body>
</html>