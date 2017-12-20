<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/12/19 14:43
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<body>
<div class="banxin">
    <textarea id="fileInput"></textarea>
    <div class="btnBox"><input id="num" type="" class="vertical_center" name=""><button class="vertical_center">增加版本号</button></div>
    <textarea id="fileOutput"></textarea>
</div>
</body>



js:

<script type="text/javascript">
    $(function(){
        var fileInput=$("#fileInput");
        var fileOutput=$("#fileOutput");

        var scr="script";
        var num=$("#num");
        $("button").on("click",function(){
            var scriptStr="";
            var cssStr="";
            var fileInputText=fileInput.val().replace(/^\s+|\s+$/gm,'');
            var scrArry=fileInputText.split("src");
            var cssArry=fileInputText.split("href");
            var newscrArry=[];
            var newcssArry=[];
            $.each(scrArry,function(index,ele){
                if(ele.charAt(0)=="="){
                    newscrArry.push(ele.split('"')[1]);
                }
            });
            $.each(cssArry,function(index,ele){
                if(ele.charAt(0)=="="){
                    newcssArry.push(ele.split('"')[1]);
                }
            });
            newscrArry=unique(newscrArry);
            newcssArry=unique(newcssArry);
            $.each(newscrArry,function(index,ele){
                scriptStr+='<script type="text/javascript" src="'+ele+'?'+num.val()+'"></'+scr+'>'+ '\n';
            });
            $.each(newcssArry,function(index,ele){
                cssStr+='<link rel="stylesheet" type="text/css" href="'+ele+'?'+num.val()+'"/>'+ '\n';

            })

            fileOutput.val(cssStr+scriptStr);
        })
    })
    //数组去重
    function unique(arr) {
        var result = [], hash = {};
        for (var i = 0, elem; (elem = arr[i]) != null; i++) {
            if (!hash[elem]) {
                result.push(elem);
                hash[elem] = true;
            }
        }
        return result;
    }
</script>
</body>
</html>
