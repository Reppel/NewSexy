<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>New Sexy - Edit</title>
    <link rel="stylesheet" href="css/edit_profile.css">
    <script type="text/javascript" src="javascript/edit_profile.js" defer="defer" charset="utf-8"></script>
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
        <form name="edit_form" id="edit_form" action="" method="post">
            <div id="edit_head_block">
                <image id="edit_head_image" src="${sessionScope.headURL}"/>
            </div>
            <div id="edit_head_div"><a id="edit_head_button" title="建议上传正方形图片作为头像">选择图片</a></div>
            <input id="file_upload" type="file"/>
            <table id="edit_table">
                <tr>
                    <td><span class="edit_text">邮&nbsp;箱：</span><span class="edit_content">${sessionScope.email}</span></td>
                </tr>
                <tr>
                    <td><span class="edit_text">用户名：</span><span class="edit_content">${sessionScope.username}</span></td>
                </tr>
                <tr>
                    <td><span class="edit_text">原密码：</span><input class="edit_input" id="old_password" type="password" placeholder="大小写字母、数字、下划线(6-16字符)"/>
                </tr>
                <tr>
                    <td><span class="edit_text">新密码：</span><input class="edit_input" id="new_password" type="password" placeholder="大小写字母、数字、下划线(6-16字符)"/></td>
                </tr>
                <tr>
                    <td><span class="edit_text">签&nbsp;名：</span></td>
                </tr>
            </table>
            </td>
            <div id="edit_sign_block">
                <textarea name="edit_sign_area" id="edit_sign_area">${sessionScope.sign}</textarea>
            </div>
            <input id="edit_submit_button" type="button" value="保存修改">
        </form>
        <div class="error_div" id="old_error_div"><span class="error_text" id="old_password_error"></span></div>
        <div class="error_div" id="new_error_div"><span class="error_text" id="new_password_error"></span></div>

    </div>

</div>
<div id="footer">CopyRight©  2015 &nbsp;  New Sexy</div>

</body>
</html>