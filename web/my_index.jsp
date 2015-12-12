<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>New Sexy - Index</title>
    <link rel="stylesheet" href="css/my_index.css">
    <script type="text/javascript" src="javascript/my_index.js" defer="defer" charset="utf-8"></script>
    <script src="javascript/lib/jquery-2.1.3.min.js"></script>
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

    <div id="body">
        <div id="main_body">
            <c:forEach items="${objectType_list}" var="objectType" varStatus="index">
                <c:if test="${not empty objectType_list}">
                    <c:if test="${objectType.type==0}">
                        <div class="question_block">
                            <a href="questionServlet?questionID=${objectType.object.questionID}"><p class="question_title">${objectType.object.questionTitle}</p></a>
                            <div class="question_footer">
                                <a href="userProfileServlet?userID=${objectType.object.userID}"><span class="ask_username">${objectType.object.username}</span></a>
                                <span class="ask_text">提问于</span>
                                <span class="ask_time">${objectType.object.questionDate}</span>
                            </div>
                        </div>
                        <hr/>
                    </c:if>
                    <c:if test="${objectType.type==1}">
                        <div class="answer_block">
                            <a href="questionServlet?questionID=${objectType.object.questionID}"><p class="answer_title">${objectType.object.questionTitle}</p></a>
                            <p class="answer_content">
                                    ${objectType.object.answerDetail}
                            </p>
                            <img class="answer_picture" src="${objectType.object.pictureURL}"/>
                            <div class="answer_footer">
                                <a href="userProfileServlet?userID=${objectType.object.userID}"><span class="answer_username">${objectType.object.username}</span></a>
                                <span class="answer_text">发表于</span>
                                <span class="answer_time">${objectType.object.answerDate}</span>
                            </div>
                        </div>
                        <hr/>
                    </c:if>


                </c:if>
            </c:forEach>
            <c:if test="${empty objectType_list}">
                <span class="no_hot">:( 暂时还没有问题</span>
            </c:if>
        </div>


        <div id="right_side">
            <div id="hot_question_block">
                <a class="hot_text" id="hot_question_text">热门问题</a>
                <c:forEach items="${hot_question_list}" var="question" varStatus="index">
                    <c:if test="${not empty hot_question_list}">
                        <li class="hot_question_list"><a href="questionServlet?questionID=${question.questionID}">${question.questionTitle}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${empty hot_user_list}">
                    <span class="no_hot">:( 暂时还没有热门问题</span>
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
                    <span class="no_hot">:( 暂时还没有热门用户</span>
                </c:if>
            </div>
            <hr/>
            <div id="footer">CopyRight©  2015 &nbsp;  New Sexy</div>
        </div>



    </div>
</div>

</body>
</html>