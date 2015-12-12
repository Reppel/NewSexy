/**
 * Created by m on 2015/5/4.
 */

window.onload=init();
window.onbeforeunload=init();


function init(){
    //search_tip();
    omit_username();
    fit_explorer();
    show_load_more();
}

//设置图片传的按钮
$('#insert_picture_button').on('click', function() {
    $('#upload_picture').trigger('click');
});

var question_id=document.getElementById("question_id").value;
var question_title=document.getElementById("question_title").innerHTML;
var user_id=document.getElementById("user_id").value;
var username=document.getElementById("username").value;
var head_url=document.getElementById("head_url").value;
var sign=document.getElementById("sign").value;



document.getElementById("button_ask").setAttribute("onclick","click_to_ask()");
document.getElementById("cancel_ask_button").setAttribute("onclick","click_cancel_ask()");
document.getElementById("ask_button").setAttribute("onclick","click_submit_ask()");
document.getElementById("button_search").setAttribute("onclick","click_search()");


//展开、折叠评论框
//document.getElementById("answer_comment_text").setAttribute("onclick","click_comment()");
var answer_comment_text=document.getElementsByClassName("answer_comment_text");
var comment_button_submit=document.getElementsByClassName("comment_button_submit");
for(var i=0;i<answer_comment_text.length;i++){
    answer_comment_text[i].setAttribute("onclick","click_comment("+i+")");
    answer_comment_text[i].setAttribute("onmouseover","change_comment_color_over("+i+")");
    answer_comment_text[i].setAttribute("onmouseout","change_comment_color_out("+i+")");
    comment_button_submit[i].setAttribute("onclick","click_submit_comment("+i+")");
}

//document.getElementsByClassName("answer_comment_text").setAttribute("onclick","click_comment()");

//提交回答
document.getElementById("submit_answer_button").setAttribute("onclick","click_submit_answer()");


//点击加载更多
document.getElementById("load_more").setAttribute("onclick","load_more()");



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

var comment_visible=false;
var loaded_comments=new Array();
for(var i=0;i<answer_comment_text.length;i++){
    loaded_comments.push(false);
}
function click_comment(index){
    if(!loaded_comments[index]){
        var answer_id=document.getElementsByClassName("answer_id")[index].value;
        load_comment(index,answer_id);
        loaded_comments[index]=true;
    }
    comment_button_submit[index].setAttribute("onclick","click_submit_comment("+index+")");

    var comment_block=document.getElementsByClassName("comment_block")[index];
    var comment_text=document.getElementsByClassName("answer_comment_text")[index];
    with(comment_block){
        if(comment_visible){
            style.display="none";
            comment_visible=false;
            comment_text.style.color="gray";
        }
        else{
            style.display="block";
            comment_visible=true;
            comment_text.style.color="#333333";
        }
    }

}


function load_comment(index,answer_id){
    $.ajax({
        type:"GET",
        url:"/loadCommentServlet",
        data:"answer_id="+answer_id,
        datatype:"json",
        success:
            function(data){
                if(data!=null){
                    var str = data;
                    var json = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject
                    var objects=json[0];
                    var length=objects.length;
                    for(var i=0;i<length;i++){
                        var object=objects[i];
                        append_comment(index,object.userID,object.username,object.headURL,object.commentDetail,object.commentDate);
                    }
                }
            }
    });
}




//评论按钮样式改变
function change_comment_color_over(index){
    var comment_text=document.getElementsByClassName("answer_comment_text")[index];
    with(comment_text){
        if(comment_visible){
            style.color="gray";
        }
        else{
            style.color="#333333";
        }
    }
}

function change_comment_color_out(index){
    var comment_text=document.getElementsByClassName("answer_comment_text")[index];
    with(comment_text){
        if(comment_visible){
           style.color="#333333";
        }
        else{
            style.color="gray";
        }

    }
}


//提交评论
function click_submit_comment(index){
    var content=document.getElementsByClassName("comment_input_text")[index].value;
    var answer_id=document.getElementsByClassName("answer_id")[index].value;
    if(content!=null&&content!=""){
        submit_comment(index,answer_id,content);
        document.getElementsByClassName("comment_input_text")[index].value="";

    }
    else{
        alert("评论不能为空！");
    }
}

function submit_comment(index,answer_id,content){
    $.ajax({
        type:'POST',
        url:"/submitCommentServlet",
        data:"answer_id="+answer_id+"&user_id="+user_id+"&username="+username+"&head_url="+head_url+"&content="+content,
        datatype:"json",
        success:
            function(data){
               if(data!=null){
                   var str = data;
                   var json = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject
                   append_comment(index,user_id,username,head_url,content,json.date);
                   location.reload(false);
               }
            }
    });

}




//var question_id=document.getElementById("question_id");
//var answer_count=document.getElementById("answer_count");
//var showed_answer_count=document.getElementsByClassName("answer_item_block");
//显示加载更多提示框
function show_load_more(){
    var answer_count=document.getElementById("answer_count").value;
    var showed_answer_count=document.getElementsByClassName("answer_item_block").length;

    var load_more=document.getElementById("load_more_block");
    if(answer_count>showed_answer_count){
        load_more.style.display="block";
    }
    else{
        load_more.style.display="none";
    }

}


//添加回答
function click_submit_answer(){
    var file=document.getElementById("upload_picture").files[0];
    var textarea=document.getElementById("submit_answer_textarea");
    var content=textarea.value;
    if(content!=null&&content!=""){
       if(file!=null){
          if(file.type.substring(0,5)=="image"){
              var picture="images/answers/"+file.name;
              upload_picture(file);
              submit_answer(content,picture);
              document.getElementById("upload_picture").outerHTML=document.getElementById("upload_picture").outerHTML;
          }
           else{
              alert("只能上传图片...年轻人不要调皮！");
          }
       }else{
           submit_answer(content,"");
       }

        textarea.value="";
    }
    else{
        alert("回答不能为空！");

    }
}


function upload_picture(file){
    var formData = new FormData();
    formData.append('imageURL',file);

    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest();
    }
    else {
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.open("POST","/uploadPictureServlet",true);
    xmlhttp.send(formData);
    xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log("upload succeeded!")
        }
        else if(xmlhttp.readyState==4 && xmlhttp.status!=200){
            alert('连接出错，请检查网络！');
        }
    }
}

function submit_answer(content,picture){
    $.ajax({
        type:'POST',
        url:"/submitAnswerServlet",
        data:"question_id="+question_id+"&question_title="+question_title+"&user_id="+user_id+"&username="+username+"&head_url="+head_url+"&sign="+sign+"&content="+content+"&picture="+picture,
        datatype:"json",
        success:
            function(data){
                if(data!=null){
                    var str = data;
                    var json = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject
                    append_new_answer(json.answerID,user_id,username,head_url,sign,content,picture,json.date,0);
                    location.reload(false);
                }
            }
    });
}






//点击加载更多

function load_more(){
    //var id=question_id.value;
    var id=document.getElementById("question_id").value;
    var showed_answer_count=document.getElementsByClassName("answer_item_block").length;
    $.ajax({
        type:'GET',
        url:"/loadMoreQuestionServlet",
        data:"question_id="+id+"&showed_count="+showed_answer_count,
        datatype:'json',
        success:
            function(data){
                if(data!=null){
                    var str = data;
                    var json = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject
                    var objects=json[0];
                    var length=objects.length;
                    for(var i=0;i<length;i++){
                        var object=objects[i];
                        append_answer(object.answerID,object.userID,object.username,object.headURL,object.sign,object.answerDetail,object.pitureURL,object.answerDate,object.commentCount);

                    }

                }
                //alert(document.getElementsByClassName("answer_item_block").length);
                show_load_more();
                var showed_answer_count=document.getElementsByClassName("answer_item_block").length;
                answer_comment_text=document.getElementsByClassName("answer_comment_text");
                for(var i=0;i<showed_answer_count;i++){
                    loaded_comments.push(false);
                    answer_comment_text[i].setAttribute("onclick","click_comment("+i+")");
                    answer_comment_text[i].setAttribute("onmouseover","change_comment_color_over("+i+")");
                    answer_comment_text[i].setAttribute("onmouseout","change_comment_color_out("+i+")");

                }

            }

    });

}

function append_answer(answer_id,userid,username,head,sign,answer_detail,picture,answer_date,comment_count){
    var answer_block=document.getElementById("answer_block");
    var html="<div class='answer_item_block'>"
    html+="<input type='hidden' class='answer_id' name='answer_id' value='"+answer_id+"'>";
      html+="<div class='answer_user'>";
         html+="<a href='userProfileServlet?userID="+userid+"'><img src='"+head+"' class='answer_user_head'/></a>";
         html+="<a href='userProfileServlet?userID="+userid+"'><span class='answer_username' id='answer_username'>"+username+"， </span></a>";
         html+="<span class='answer_user_sign'>"+sign+"</span>";
      html+="</div>";
      html+="<p class='answer_content'>"+answer_detail+"</p>";
      html+="<img class='answer_picture src='"+picture+"'/>";
      html+="<div class='answer_footer'>";
         html+="<span class='answer_comment_text'>评论("+comment_count+") </span>";
         html+="<span class='answer_time'>"+answer_date+"</span>";
      html+="</div>"
      html+="<div class='comment_block'>"
         html+="<div class='send_comment_block'>";
             html+="<form class='form_comment' name='form_comment' action='' method='post'>";
                 html+="<input type='text' class='comment_input_text' name='comment_input_text'/>";
                 html+="<input type='button' class='comment_button_submit' value='提交评论'/>";
             html+="</form>";
         html+="</div>";
      html+="</div>";
    html+="</div>"
    html+="<hr/>";
    answer_block.innerHTML+=html;
}

function append_new_answer(answer_id,userid,username,head,sign,answer_detail,picture,answer_date,comment_count){
    var answer_block=document.getElementById("answer_block");
    var html="<div class='answer_item_block'>"
    html+="<input type='hidden' class='answer_id' name='answer_id' value='"+answer_id+"'>";
    html+="<div class='answer_user'>";
    html+="<a href='userProfileServlet?userID="+userid+"'><img src='"+head+"' class='answer_user_head'/></a>";
    html+="<a href='userProfileServlet?userID="+userid+"'><span class='answer_username' id='answer_username'>"+username+"， </span></a>";
    html+="<span class='answer_user_sign'>"+sign+"</span>";
    html+="</div>";
    html+="<p class='answer_content'>"+answer_detail+"</p>";
    html+="<img class='answer_picture src='"+picture+"'/>";
    html+="<div class='answer_footer'>";
    html+="<span class='answer_comment_text'>评论("+comment_count+") </span>";
    html+="<span class='answer_time'>"+answer_date+"</span>";
    html+="</div>"
    html+="<div class='comment_block'>"
    html+="<div class='send_comment_block'>";
    html+="<form class='form_comment' name='form_comment' action='' method='post'>";
    html+="<input type='text' class='comment_input_text' name='comment_input_text'/>";
    html+="<input type='button' class='comment_button_submit' value='提交评论'/>";
    html+="</form>";
    html+="</div>";
    html+="</div>";
    html+="</div>"
    html+="<hr/>";
    var answer_count=document.getElementById("answer_count").value;
    if(answer_count<=0){
        document.getElementsByClassName("no_answer")[0].style.display="none";
        answer_block.innerHTML+=html;
    }else{
        document.getElementsByClassName("answer_item_block")[0].insertAdjacentHTML('beforebegin',html);
    }

}



//追加评论
function append_comment(index,userid,username,head,comment_detail,comment_date){
    var answer_block=document.getElementById("answer_block");
    var comment_block=document.getElementsByClassName("comment_block")[index];
    var submit_comment_block=document.getElementsByClassName("send_comment_block")[index];
    var html="<div class='comment_item_block'>";
        html+="<div class='comment_user'>";
            html+="<a href='userProfileServlet?userID="+userid+"'><img src='"+head+"' class='comment_user_head'/></a>";
            html+="<a href='userProfileServlet?userID="+userid+"'><span class='comment_username'>"+username+"</span></a>";
        html+="</div>";
        html+="<p class='comment_content'>"+comment_detail+"</p>";
        html+="<span class='comment_footer'>"+comment_date+"</span>";
    html+="</div>";
    html+="<hr/>";
    submit_comment_block.insertAdjacentHTML('beforebegin',html);



}





//浏览器适配
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
    else{
        return "other";
    }

}