/**
 * Created by m on 2015/4/19.
 */

window.onload=init();
window.onbeforeunload=init();

function init(){

}

//切换登录、注册表单
document.getElementById("register_text").setAttribute("onclick","click_register_text()");
document.getElementById("login_text").setAttribute("onclick","click_login_text()");

//登录、注册提交表单
document.getElementById("button_login").setAttribute("onclick","click_login_button()");
document.getElementById("button_register").setAttribute("onclick","click_register_button()");


document.getElementById("register_email").setAttribute("onblur","checkUserExisting()");
document.getElementById("register_username").setAttribute("onblur","checkUserExisting()");

//切换登录、注册表单
function click_register_text(){
    document.getElementById("login_block").style.display="none";
    document.getElementById("register_block").style.display="block";
}

function click_login_text(){
    document.getElementById("register_block").style.display="none";
    document.getElementById("login_block").style.display="block";
}


//登录、注册提交表单
function click_login_button(){
    var email=document.getElementById("input_email").value;
    var password=document.getElementById("input_password").value;
    var error_text=document.getElementById("login_error_text");

    var check_email=/^[a-zA-Z0-9]+@+[a-zA-Z0-9]+(.com)|(.cn)$/ ;
    var check_password=/^[a-zA-Z0-9_]{6,16}$/ ;
    var check_number=/^[0-9]*$/;

    var email_correct=check_email.test(email);
    var password_correct=(check_password.test(password))&&(!check_number.test(password));

    if(!email_correct){
        error_text.innerHTML="邮箱格式不正确！";
        error_text.style.display="block";
    }
    else if(!password_correct){
        error_text.innerHTML="密码格式不正确！";
        error_text.style.display="block";
    }

    if((email_correct)&&(password_correct)){
        error_text.style.display="none";
        document.getElementById("login").action="loginServlet?email="+email+"&password="+password;
        document.getElementById("button_login").type="submit";
    }


}

var register_pass_flag=false;
function click_register_button(){
    var email=document.getElementById("register_email").value;
    var username=document.getElementById("register_username").value;
    var password=document.getElementById("register_password").value;
    var error_text=document.getElementById("register_error_text");

    var check_email=/^[a-zA-Z0-9]+@+[a-zA-Z0-9]+(.com)|(.cn)$/ ;
    var check_username=/^[a-zA-Z0-9_\u4E00-\u9FA5]{2,16}$/ ;
    var check_password=/^[a-zA-Z0-9]{6,16}$/ ;
    var check_number=/^[0-9]*$/;

    var email_correct=check_email.test(email);
    var username_correct=check_username.test(username);
    var password_correct=(check_password.test(password))&&(!check_number.test(password));

    if(!email_correct){
        error_text.innerHTML="邮箱格式不正确！";
        error_text.style.display="block";
    }
    else if(!username_correct){
        error_text.innerHTML="用户名格式不正确！";
        error_text.style.display="block";
    }
    else if(!password_correct){
        error_text.innerHTML="密码格式不正确！";
        error_text.style.display="block";
    }

    if((email_correct)&&(username_correct)&&(password_correct)&&(register_pass_flag)){
        document.getElementById("register_error_text").innerHTML="注册成功！";
        document.getElementById("register").action="registerServlet?email="+email+"&username="+username+"&password="+password;
        document.getElementById("button_register").type="submit";
    }
}


function checkUserExisting(){
    var email=document.getElementById("register_email").value;
    var username=document.getElementById("register_username").value;
    if((email!="")||(username!="")){
        isUserExisted(email,username);
    }

}


function isUserExisted(email,username){
    $.ajax({
        type:'GET',
        url:"/registerServlet",
        data:"email="+email+"&username="+username,
        datatype:'json',
        success:
            function(data){
                if(data!=null){
                    var str = data;
                    var obj = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject
                    if(obj.flag==1){
                        document.getElementById("register_error_text").innerHTML="该用户已存在";
                        document.getElementById("register_error_text").style.display="block";
                        register_pass_flag=false;
                    }
                    else{
                        register_pass_flag=true;
                        document.getElementById("register_error_text").innerHTML="";
                        document.getElementById("register_error_text").style.display="display";
                    }
                }

            }


    });




}

