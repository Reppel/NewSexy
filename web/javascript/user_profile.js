/**
 * Created by m on 2015/5/4.
 */

window.onload=init();
window.onbeforeunload=init();

var me_id=document.getElementById("me_id").value;
var user_userid=document.getElementById("user_userid").value;
var me_username=document.getElementById("me_username").value;
var user_username=document.getElementById("user_username").value;
var me_head=document.getElementById("me_head").value;
var user_head=document.getElementById("user_head").value;
var relation=document.getElementById("relation").value;
var reverse_flag=document.getElementById("reverse_flag").value;

var user_id=document.getElementById("user_id").value;
var username=document.getElementById("username").value;
var head_url=document.getElementById("head_url").value;
var sign=document.getElementById("sign").value;


function init(){
    //search_tip();
    omit_username();
    fit_explorer();
    //show_date();
}

//提交搜索内容
document.getElementById("button_search").setAttribute("onclick","click_search_button()");

//不是自己
if(me_id!=user_userid){
    //监听关注按钮
    document.getElementById("follow_button").setAttribute("onclick","click_follow_button()");

//私信按钮
    document.getElementById("message_button").setAttribute("onclick","click_send_message()");
    document.getElementById("cancel_message_button").setAttribute("onclick","click_cancel_message()");
    document.getElementById("send_message_button").setAttribute("onclick","click_submit_message()");
}


//搜索、提问
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

//显示时间去除小数点
function show_date(){
   var date_list=[];
    date_list=document.getElementsByClassName("ask_item_footer");
    for(var i=0;i<date_list.length;i++){
        var date=date_list[i].innerHTML;
        date=date.substring(0,date.indexOf("."));
        console.log(date);
        document.getElementsByClassName("ask_item_footer")[i].innerHTML=date;
        console.log("inner"+document.getElementsByClassName("ask_item_footer")[i].innerHTML);
    }
}


//切换标签页
$(function switch_tab() {
    // Your codes here for part A
    var $tab=$(".track_tab");
    $tab.click(function(){

        //更改选项卡标签状态
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
        var tab_index=$(this).index();

        //更改选项卡标签内容
        var $tabpane=$(".track_tab_content");
        var $clicked_tabpane=$($tabpane.get(tab_index));
        $clicked_tabpane.siblings().removeClass("active");
        $clicked_tabpane.addClass("active");
    });

});


//适配浏览器
function fit_explorer(){
    var button=document.getElementById("button_search");
    if(getExplorer()!="chrome"){
        button.style.top="0px";
    }
}


function click_follow_button(){
    var relation=document.getElementById("relation").value;
    var button=document.getElementById("follow_button");
    var new_relation=relation;
    if(relation==-1){
        new_relation=0;
        button.innerHTML="已关注";
    }else if(relation==0){
        new_relation=-1;
        button.innerHTML="点击关注";
    }else if(relation==1){
        new_relation=2;
        button.innerHTML="互相关注";
    }else if(relation==2){
        new_relation=1;
        button.innerHTML="关注了我";
    }
    document.getElementById("relation").value=new_relation;
    set_follow_relation(me_id,user_userid,new_relation,reverse_flag);

}

function set_follow_relation(from_id,to_id,new_relation,reverse_flag){
    $.ajax({
        type:"POST",
        url:"/followServlet",
        data:"from_id="+from_id+"&to_id="+to_id+"&new_relation="+new_relation+"&reverse_flag="+reverse_flag,
        datatype:"json",
        success:
            function(data){
                console.log("follow: succeeded!");
                location.reload(true);
            }
    });

}


function click_send_message(){
    document.getElementById("send_message_block").style.display="block";
}

function click_cancel_message(){
    document.getElementById("send_message_block").style.display="none";
}

function click_submit_message(){
    var content=document.getElementById("textarea_message").value;
    if(content!=null&&content!=""){
       submit_message(me_id,user_userid,content);
    }else{
        alert("内容不得为空！");
    }
}

function submit_message(from_id,to_id,content){
    var me_id=document.getElementById("me_id").value;
    var user_userid=document.getElementById("user_userid").value;
    var me_username=document.getElementById("me_username").value;
    var user_username=document.getElementById("user_username").value;
    var me_head=document.getElementById("me_head").value;
    var user_head=document.getElementById("user_head").value;
    $.ajax({
        type:"POST",
        url:"/sendMessageServlet",
        data:"from_id="+from_id+"&from_username="+me_username+"&from_head="+me_head+"&to_id="+to_id+"&to_username="+user_username+"&to_head="+user_head+"&content="+content,
        datatype:"json",
        success:
            function(data){
                alert("发送成功！");
                document.getElementById("send_message_block").style.display="none";
            }
    });
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