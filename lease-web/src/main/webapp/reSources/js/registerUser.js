/* -- -- -- --
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/10/27 11:02
  Comment: 
 -- -- -- -- */

function regUser() {
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[2];
    var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
    //账号名称
    var userphone=$("#userPhone").val();
    var pwd=$("#passWord").val();
    var verifyCode=$("#verifyCode").val();
    var ok=true;
    if(ok){
        $.ajax({
            type: "POST",
            url: basePath+"/register.do",
            data: {'phone':userphone,'userpsw':pwd,'verifyCode':verifyCode},
            success: function(data){
                if(data.success == true){
                    window.location.href=basePath+"/index1.jsp";
                }else{
                    alert(data.msg);
                }

            }
        });
    }
}