<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>New Sexy - ${question.questionTitle}</title>
    <link rel="stylesheet" href="css/questions.css">
    <script src="javascript/lib/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="javascript/questions.js" defer="defer" charset="utf-8"></script>
</head>
<body>
<div id="ask_block">
    <form id="ask_form" method="POST">
        <span id="ask_title_text">问题标题：</span><input type="text" id="input_ask_title"/>
        <p id="ask_description_text">问题描述（选填）:</p>
        <textarea id="textarea_ask_description" form="ask_form"></textarea>
        <button id="cancel_ask_button">取消提问</button>
        <input type="button" id="ask_button" value="提交问题"/>
    </form>
</div>
    <div class="wrapper">
        <div class="navigation">
            <div class="nav_inner">
                <a id="logo">
                    New Sexy
                </a>

                <div id="search_ask_block" >
                    <form name="form_search" id="form_search" action="" method="POST">
                        <input type="text" name="search" id="input_search"  placeholder="搜索问题或用户"/>
                        <input type="button" id="button_search" value="" title="搜索"/>
                    </form>
                    <a href="#"><button id="button_ask">提问</button></a>
                </div>

                <div class="menubar">
                    <li class="menu"><a href="myIndexServlet">首页</a></li>
                    <li class="menu"><a href="#">话题</a></li>
                    <li class="menu"><a href="exploreServlet">发现</a></li>
                </div>
                <div class="user_info_block">
                    <input type="hidden" id="user_id" value="${sessionScope.userID}"/>
                    <input type="hidden" id="username" value="${sessionScope.username}"/>
                    <input type="hidden" id="head_url" value="${sessionScope.headURL}"/>
                    <input type="hidden" id="sign" value="${sessionScope.sign}"/>
                    <a href="userProfileServlet?userID=${sessionScope.userID}">
                        <img
                                <c:if test="${not empty sessionScope.headURL}">
                                    src="${sessionScope.headURL}"
                                </c:if>
                                <c:if test="${empty sessionScope.headURL}">
                                    src="images/0.jpg"
                                </c:if>
                                id="user_head_image"/>

                        <%--若用户已经登录，则显示为用户名。否则显示为游客。--%>
                        <span id="username_text"><c:if test="${not empty sessionScope.username}">${sessionScope.username}</c:if><c:if test="${empty sessionScope.username}">游客</c:if></span>
                    </a>
                    <ul class="user_submenubar">
                        <li class="user_submenu" id="my_homepage">
                            <a href="userProfileServlet?userID=${sessionScope.userID}">
                                <img src="images/user.png"/>
                                <span>我的主页</span>
                            </a>
                        </li>
                        <li class="user_submenu" id="my_message">
                            <a href="messageServlet">
                                <img src="images/mail.png"/>
                                <span>私信</span>
                            </a>
                        </li>
                        <li class="user_submenu" id="my_settings">
                            <a href="edit_profile.jsp">
                                <img src="images/setting.png"/>
                                <span>设置</span>
                            </a>
                        </li>
                        <li class="user_submenu" id="my_exit">
                            <a href="welcomeServlet">
                                <img src="images/exit.png"/>
                                <span>退出</span>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

       <div class="body">
           <div class="main_body">
               <div class="question_block" id="question_block">
                   <input id="question_id" type="hidden" name="question_id" value=${question.questionID}>
                   <input id="answer_count" type="hidden" name="answer_count" value="${question.answerCount}"/>
                   <h3 class="question_title" id="question_title">${question.questionTitle}</h3>
                   <p class="question_content" id="question_content">${question.questionDetail}</p>
                   <div class="question_footer" id="question_footer">
                       <a href="#"><span class="ask_username" id="ask_username">${question.username}</span></a>
                       <span class="ask_text" id="ask_text">提问于</span>
                       <span class="ask_time" id="ask_time">${question.questionDate}</span>
                   </div>
                   <hr/>
               </div>
               <div class="answer_block" id="answer_block">

                   <c:forEach items="${answer_list}" var="answer" varStatus="index">
                       <c:if test="${not empty answer_list}">
                           <div class="answer_item_block" id="answer_item_block">
                               <input type="hidden" class="answer_id" name="answer_id" value="${answer.answerID}"/>
                               <div class="answer_user" id="answer_user">
                                   <a href="#"><img src="${answer.headURL}" class="answer_user_head" id="answer_user_head"></a>
                                   <a href="#"><span class="answer_username" id="answer_username">${answer.username}，</span></a>
                                   <span class="answer_user_sign" id="answer_user_sign">${answer.sign}</span>
                               </div>
                               <p class="answer_content" id="answer_content">
                                  ${answer.answerDetail}
                               </p>
                               <img class="answer_picture" src="${answer.pictureURL}"/>
                               <div class="answer_footer" id="answer_footer">
                                   <span class="answer_comment_text" id="answer_comment_text">评论(${answer.commentCount})</span>
                                   <span class="answer_time" id="answer_time">${answer.answerDate}</span>
                               </div>
                               <div class="comment_block" id="comment_block">
                                   <div class="send_comment_block" id="send_comment_block">
                                       <form class="form_comment" id="form_comment" name="form_comment" action="" method="post">
                                           <input type="text" class="comment_input_text" id="comment_input_text" name="comment_input_text"/>
                                           <input type="button" class="comment_button_submit" id="comment_button_submit" value="提交评论"/>
                                       </form>
                                   </div>
                               </div>
                           </div>
                           <hr/>

                       </c:if>
                   </c:forEach>
                   <c:if test="${empty answer_list}">
                       <span class="no_answer">:( 这个问题暂时还没有回答<br/>来贡献第一个回答吧！</span>
                   </c:if>
               </div>
               <%--<hr/>--%>

               <div id="load_more_block">
                   <a id="load_more">点击加载更多...</a>
               </div>

               <div id="submit_answer_block">
                    <div id="submit_answer_user">
                        <img src="${sessionScope.headURL}" id="submit_answer_head"/>
                        <span id="submit_answer_username">${sessionScope.username}，</span>
                        <span id="submit_answer_sign">${sessionScope.sign}</span>
                    </div>
                   <form id="submit_answer_form" name="submit_answer_form">
                        <textarea name="submit_answer_textarea" id="submit_answer_textarea" cols="90" rows="10" wrap="hard" form="submit_answer_form"></textarea>
                        <a id="insert_picture_button" value="" title="插入图片"><img src="images/picture0.png"></a><input type="file" id="upload_picture"/>
                        <input type="button" id="submit_answer_button" value="发布回答"/>
                   </form>
               </div>
           </div>

           <div class="right_side">
                <div id="hot_question_block">
                    <a class="hot_text" id="hot_question_text">热门问题</a>
                    <c:forEach items="${hot_question_list}" var="question" varStatus="index">
                        <c:if test="${not empty hot_question_list}">
                            <li class="hot_question_list"><a href="#">${question.questionTitle}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${empty hot_user_list}">
                        <span class="no_hot"><br/><br/>:( 暂时还没有热门问题</span>
                    </c:if>
                </div>
               <hr/>
               <div id="hot_user_block">
                   <a class="hot_text" id="hot_user_text">热门用户</a>

                   <c:forEach items="${hot_user_list}" var="user" varStatus="index">
                       <c:if test="${not empty hot_user_list}">
                           <div class="hot_user_item">
                               <a href="userProfileServlet?userID=${user.userID}"><img class="hot_user_head" src="${user.headURL}"/></a>
                               <a href="userProfileServlet?userID=${user.userID}" class="hot_username">${user.username}</a><br/>
                               <span class="hot_user_sign">${user.sign}</span>
                           </div>
                       </c:if>
                   </c:forEach>
                   <c:if test="${empty hot_user_list}">
                       <span class="no_hot"><br/><br/>:( 暂时还没有热门用户</span>
                   </c:if>
               </div>
               <hr/>
               <div id="footer">CopyRight©  2015 &nbsp;  New Sexy</div>
           </div>
       </div>
    </div>
</body>
</html>