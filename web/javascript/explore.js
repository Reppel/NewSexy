/**
 * Created by m on 2015/6/25.
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