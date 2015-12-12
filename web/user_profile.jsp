<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>New Sexy - ${user.username}</title>
    <link rel="stylesheet" href="css/user_profile.css">
    <script src="javascript/lib/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="javascript/user_profile.js" defer="defer" charset="utf-8"></script>
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
<div id="send_message_block">
    <form id="message_form" method="post">
        <span id="message_content_text">私信给${user.username}</span>
        <textarea id="textarea_message" form="message_form"></textarea>
        <input type="button" id="cancel_message_button" value="取消发送"/>
        <input type="button" id="send_message_button" value="发送私信"/>
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
            <div id="user_description"><span>${user.username},</span>${user.sign}</div>
            <div id="user_track_block">
                <div id="track_nav_bar">
                    <ul class="track_tab_list">
                        <li class="track_tab active" id="track_tab_ask">提问</li>
                        <li class="track_tab" id="track_tab_answer">回答</li>
                    </ul>
                </div>
                <div id="track_tab_panel">
                    <div class="track_tab_content active" id="track_ask">
                        <c:forEach items="${question_list}" var="question" varStatus="index">
                            <c:if test="${question_list!=null}">
                                <div class="ask_item_block">
                                    <a href="questionServlet?questionID=${question.questionID}"> <p class="ask_item_title">${question.questionTitle}</p></a>
                                    <span class="ask_item_footer">${question.questionDate}</span>
                                </div>
                                <hr/>

                            </c:if>

                        </c:forEach>
                        <c:if test="${empty question_list}">
                           <span class="no_question_answer">诶，${user.username}还没有提过问题...</span>
                        </c:if>




                    </div>
                    <div class="track_tab_content" id="track_answer">
                        <c:forEach items="${answer_list}" var="answer" varStatus="index">
                            <c:if test="${answer_list!=null}">
                                <div class="answer_item_block">
                                    <a href="#"><p class="answer_item_title">${answer.questionTitle}</p></a>
                                    <p class="answer_item_content">
                                      ${answer.answerDetail}
                                    </p>
                                    <img class="answer_picture" src="${answer.pictureURL}"/><br/>
                                    <span class="answer_item_footer">${answer.answerDate}</span>
                                </div>
                                <hr/>

                            </c:if>

                        </c:forEach>
                        <c:if test="${empty answer_list}">
                            <span class="no_question_answer">诶，${user.username}还没有回答过问题...</span>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
        <div class="right_side">
            <div class="user_profile_block">
                <img id="profile_head_img" src="${user.headURL}"/>
                <div id="follow_follower_info">
                    <div id="follow_info_block" class="profile_info">
                        <span>关注了</span><br/>
                        <a href="#"><span class="people_number" id="follow_number">${user.followingCount}</span></a><br/>
                        <span>人</span>
                    </div>
                    <div id="follower_info_block" class="profile_info">
                        <span>关注者</span><br/>
                        <a href="#"><span class="people_number" id="follower_number">${user.followerCount}</span></a><br/>
                        <span>人</span>
                    </div>
                </div>
            </div>

            <input type="hidden" id="me_id" value="${sessionScope.userID}"/>
            <input type="hidden" id="me_username" value="${sessionScope.username}"/>
            <input type="hidden" id="me_head" value="${sessionScope.headURL}">
            <input type="hidden" id="user_userid" value="${user.userID}"/>
            <input type="hidden" id="user_username" value="${user.username}"/>
            <input type="hidden" id="user_head" value="${user.headURL}"/>
            <input type="hidden" id="relation" value="${relation}"/>
            <input type="hidden" id="reverse_flag" value="${reverse_flag}"/>
            <c:if test="${sessionScope.username!=user.username}">
                <div id="follow_message_block">
                    <button id="follow_button" class="follow_message_button">
                        <c:if test="${relation==-1}">点击关注</c:if>
                        <c:if test="${relation==0}">已关注</c:if>
                        <c:if test="${relation==1}">关注了我</c:if>
                        <c:if test="${relation==2}">互相关注</c:if>
                    </button>
                    <button id="message_button" class="follow_message_button">发私信</button>
                </div>
            </c:if>
            <hr/>

            <div id="follow_block" class="follow_follower_block">
                <p>最近关注了</p>
                <c:forEach items="${following_list}" var="user" varStatus="index">
                    <c:if test="${following_list!=null}">
                        <a href="userProfileServlet?userID=${user.userID}"><img class="follow_follower_head" src="${user.headURL}" title="${user.username}" alt="${user.username}"/></a>
                    </c:if>

                </c:forEach>
            </div>
            <hr/>

            <div id="follower_block" class="follow_follower_block">
                <p>最近被他们关注</p>
                <c:forEach items="${follower_list}" var="user" varStatus="index">
                    <c:if test="${follower_list!=null}">
                        <a href="userProfileServlet?userID=${user.userID}"><img class="follow_follower_head" src="${user.headURL}" title="${user.username}" alt="${user.username}"></a>
                    </c:if>

                </c:forEach>

            </div>
            <hr/>
            <div id="footer">CopyRight©  2015 &nbsp;  New Sexy</div>
        </div>
    </div>


</div>

</body>
</html>