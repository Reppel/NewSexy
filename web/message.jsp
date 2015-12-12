<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>New Sexy - Message</title>
    <link rel="stylesheet" href="css/message.css">
    <script src="javascript/lib/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="javascript/message.js" defer="defer" charset="utf-8"></script>
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
        <p id="my_message_text">我的私信</p>
        <hr/>
        <div id="message_block">

            <c:forEach items="${objectType_list}" var="objectType" varStatus="index">
                <c:if test="${not empty objectType_list}">
                    <c:if test="${objectType.type==0}">
                        <div class="message_item_block">
                            <div class="message_item_piece received">
                                <input type="hidden" class="message_id" value="${objectType.object.id}"/>
                                <a href="userProfileServlet?userID=${objectType.object.sendUserID}"><img src="${objectType.object.sendHeadURL}" class="you_head"/></a>
                                <p class="message_item"> <a href="userProfileServlet?userID=${objectType.object.sendUserID}"> <span class="you_username">${objectType.object.sendUsername} </span></a>：${objectType.object.messageContent}</p>
                                <div class="message_footer">
                                    <input type="hidden" class="read_status" value="${objectType.object.readStatus}"/>
                                    <%--此处原本是展开对话按钮 现在作为标记已读按钮--%>
                                    <c:if test="${objectType.object.readStatus==0}">
                                        <span class="message_dialog_text">[未读]</span>
                                    </c:if>
                                    <c:if test="${objectType.object.readStatus==1}">
                                        <span class="message_dialog_text">[已读]</span>
                                    </c:if>
                                    <%--<span class="message_dialog_text">展开对话</span>--%>
                                    <span class="message_time">${objectType.object.messageDate}</span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${objectType.type==1}">
                        <div class="message_item_block">
                            <div class="message_item_piece sended">
                                <a href="userProfileServlet?userID=${objectType.object.sendUserID}"><img src="${objectType.object.sendHeadURL}" class="me_head"/></a>
                                <p class="message_item"><span>我发送给 </span> <a href="userProfileServlet?userID=${objectType.object.receiveUserID}"><span class="you_username">${objectType.object.receiveUsername} </span></a>：${objectType.object.messageContent}</p>
                                <div class="message_footer">
                                    <%--<span class="message_dialog_text">展开对话</span>--%>
                                    <span class="message_time">${objectType.object.messageDate}</span>
                                </div>
                            </div>
                        </div>
                    </c:if>


                </c:if>
            </c:forEach>
            <c:if test="${empty objectType_list}">
                <span class="no_hot">:( 暂时还没有私信</span>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>