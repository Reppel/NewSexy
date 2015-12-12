/**
 * Created by m on 2015/6/17.
 */

window.onload=init();
window.onbeforeunload=init();

function init(){
    omit_username();
    fit_explorer();
}

var user_id=document.getElementById("user_id").value;
var username=document.getElementById("username").value;
var head_url=document.getElementById("head_url").value;
var sign=document.getElementById("sign").value;


document.getElementById("button_ask").setAttribute("onclick","click_to_ask()");
document.getElementById("cancel_ask_button").setAttribute("onclick","click_cancel_ask()");
document.getElementById("ask_button").setAttribute("onclick","click_submit_ask()");
document.getElementById("button_search").setAttribute("onclick","click_search()");


function click_to_ask(){
    document.getElementById("ask_block").style.display="block";
}

function click_cancel_ask(){
    document.getElementById("ask_block").style.display="none";
}

function click_submit_ask(){
    var title=document.getElementById("input_ask_title").value;
    var detail=document.getElementById("textarea_ask_description").value;
    if(title!=null&&title!=""){
        submit_question(user_id,username,title,detail);
    }else{
        alert("问题标题不得为空！");
    }
}

function submit_question(user_id,username,title,detail){
    $.ajax({
        type:"POST",
        url:"/submitQuestionServlet",
        data:"user_id="+user_id+"&username="+username+"&question_title="+title+"&question_detail="+detail,
        datatype:"json",
        success:
            function(data){
                if(data!=null){
                    var str = data;
                    var json = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject
                    window.location="questionServlet?questionID="+json.questionID;
                    //document.getElementById("ask_form").setAttribute("action","questionServlet?questionID="+json.questionID);
                    //document.getElementById("ask_button").setAttribute("type","submit");
                    alert("提问成功！");
                }

            }
    });
}


function click_search(){
    var input_search=document.getElementById("input_search").value;
    if(input_search!=null&&input_search!=""){
        window.location="searchServlet?key="+input_search;
    }else{
        alert("搜索内容不得为空！");
    }
}



//用户名过长显示为省略
function omit_username(){
    var username=document.getElementById("username_text");
    if(username.innerHTML.length>4){
        username.innerHTML=username.innerHTML.substr(0,4)+"...";
    }
}

//适配浏览器
function fit_explorer(){
    var button=document.getElementById("button_search");
    if(getExplorer()!="chrome"){
        button.style.top="0px";
    }
}

//判断浏览器类型
function getExplorer() {
    var explorer = window.navigator.userAgent ;

//ie
    if (explorer.indexOf("MSIE") >= 0) {
        return "ie";
    }
//firefox
    else if (explorer.indexOf("Firefox") >= 0) {
        return "firefox";
    }
//Chrome
    else if(explorer.indexOf("Chrome") >= 0){
        return "chrome"
    }

}

document.getElementById("old_password").setAttribute("onblur","check_password_format('old_password','old')");
document.getElementById("new_password").setAttribute("onblur","check_password_format('new_password','new')");
document.getElementById("edit_submit_button").setAttribute("onclick","submit_form()");
document.getElementById("file_upload").setAttribute("onchange","preview_head()");

var image_file;
//设置文件上传的按钮
$('#edit_head_button').on('click', function() {
    $('#file_upload').trigger('click');
});



//检查密码格式是否正确
var old_format_flag=true;
var new_format_flag=true;
function check_password_format(input_password,flag){
    var password=document.getElementById(input_password).value;
    var error;
    var format_flag;
    if(input_password=="old_password"){
        error="old_password_error";
    }
    else if(input_password=="new_password"){
        error="new_password_error";
    }

    var error_text=document.getElementById(error);

    var check_password=/^[a-zA-Z0-9_]{6,16}$/ ;
    var check_number=/^[0-9]*$/;

    var password_correct=(password=="")||((check_password.test(password))&&(!check_number.test(password)));

    if(!password_correct){
        error_text.innerHTML="* 密码格式错误！";
        //error_text.style.display="inline";
        error_text.style.visibility="visible";
        format_flag=false;

    }else{
        error_text.innerHTML="";
        //error_text.style.display="none";
        error_text.style.visibility="hidden";
        format_flag=true;

        //检查旧密码是否正确，若正确，检查新密码是否与旧密码重复
        if(password!=""){
            no_password=false;
            check_old_password();
            if(password_correct_flag){
                check_new_password();
            }
        }

    }

    if(flag=="old"){
        old_format_flag=format_flag;
    }
    else if(flag=="new"){
        new_format_flag=format_flag;
    }

}

//检查旧密码是否正确
var password_correct_flag=false;
function check_old_password(){
    var password=document.getElementById("old_password").value;
    var error_text=document.getElementById("old_password_error");
    $.ajax({
        type:'GET',
        url:"/editProfileServlet",
        data:"password="+password,
        datatype:'json',
        success:
            function(data){
                if(data!=null){
                    var str = data;
                    var obj = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject
                    if(obj.flag==1){
                        error_text.innerHTML="√";
                        error_text.style.visibility="visible";
                        password_correct_flag=true;
                        console.log("true");
                    }
                    else{
                        error_text.innerHTML="密码不正确！";
                        error_text.style.visibility="visible";
                        password_correct_flag=false;
                        console.log("false");
                    }
                }

            }
    });
}

//检查新密码是否与旧密码重复
var password_not_repeat=true;
function check_new_password(){
    var old_password=document.getElementById("old_password").value;
    var new_password=document.getElementById("new_password").value;
    var error_text=document.getElementById("new_password_error");
    if(old_password===new_password){
        password_not_repeat=false;
        error_text.innerHTML="不得与旧密码重复！";
    }
    else{
        password_not_repeat=true;
        error_text.innerHTML="√";
    }
    error_text.style.visibility="visible";
}



function submit_form(){
    var edit_form=document.getElementById("edit_form");
    //var image=document.getElementById("edit_head_image").src;
    //image=image.substring(image.lastIndexOf("/")+1);
    var sign=document.getElementById("edit_sign_area").value;
    var old_password=document.getElementById("old_password").value;
    var new_password=document.getElementById("new_password").value;
    var image=document.getElementById("head_url").value;

    console.log("old_format_flag:"+old_format_flag+" ;new_format_flag:"+new_format_flag+" ;password_correct_flag:"+password_correct_flag+"; password_not_repeat:"+password_not_repeat+";");
    if(head_changed_flag){
        uploadImage(image_file);
        image="images/heads/"+image_file.name;
    }
    if(old_format_flag&&new_format_flag&&password_correct_flag&&password_not_repeat){
        alert("修改成功！");
        document.getElementById("edit_submit_button").type="submit";
        edit_form.action="editProfileServlet?password="+new_password+"&sign="+sign+"&image="+image;
    }
    else if(old_password==""&&new_password==""){
        alert("修改成功！");
        document.getElementById("edit_submit_button").type="submit";
        edit_form.action="editProfileServlet?password="+""+"&sign="+sign+"&image="+image;
    }
}


var head_changed_flag=false;
function preview_head(){
    var file=document.getElementById("file_upload").files[0];
    if(file){
        if(file.type.substring(0,5)=="image"){
            head_changed_flag=true;
            image_file=file;
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                    var urlData = this.result;
                    document.getElementById("edit_head_image").setAttribute("src",urlData);
            }
        }
        else{
            alert("只能上传图片...年轻人不要调皮！");
        }
    }
}

function uploadImage(file) {

    var formData = new FormData();
    formData.append('imageURL',file);

    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest();
    }
    else {
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.open("POST","uploadHeadServlet",true);
    xmlhttp.send(formData);
    xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log("upload succeeded!")
            //var imgUrl = xmlhttp.responseText;
            //var text = document.getElementById("text");
            //var content = text.value;
            //text.value = "";
            //content = content.replace(/\(F_(\d+).gif\)/g, '<img src="./img/faces/F_$1.gif">');
            //document.getElementById("promote").innerHTML = 140;
            //if(content.length > 0 || imgUrl != ""){
            //    sendMessage(content, imgUrl);
            //}
            ////fileEle.value="";
        }
        else if(xmlhttp.readyState==4 && xmlhttp.status!=200){
            alert('连接出错，请检查网络！');
        }
    }
}
