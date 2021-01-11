<!DOCTYPE HTML>
<html>
<head>
    <title>Latest</title>
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
    <#if codeList?size = 0>
        <span id="empty_state">No snippets</span>
    <#else>
        <#list codeList as snippet>
            <span id="load_date">${snippet.date}</span>
            <pre id="code_snippet"><code>${snippet.code}</code></pre>
        </#list>
    </#if>
</body>
</html>