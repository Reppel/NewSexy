<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>New Sexy</title>
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="javascript/index.js" defer="defer" charset="utf-8"></script>
    <script  type="text/javascript" src="javascript/lib/jquery-2.1.3.min.js"></script>
</head>

<%--the error warning of login--%>
<script type="text/javascript">
    var state ="<%=session.getAttribute("user_state")%>";
    if(state=="failed"){
        alert("邮箱或密码错误！");
    }
    <%session.setAttribute("user_state","");%>
</script>
<body>
<div class="content">
<div class="head">
      <div class="introduction_block">
           <a id="logo">
               New Sexy
           </a>
           <div id="description">
              - <b>Smart</b> is the new sexy.
           </div>
      </div>

      <div class="login_block" id="login_block">
          <a id="register_text">
              注册账号 →
          </a>
          <form name="login" id="login" action="" method="POST">
                <table id="login_table">
                    <tr>
                        <td><input type="email" name="email" id="input_email" placeholder="邮箱地址"/></td>
                    </tr>
                    <br/>
                    <tr>
                        <td><input type="password" name="password" id="input_password" placeholder="密码 (6-16字符)"/></td>
                    </tr>
                    <br/>
                    <tr>
                        <td><input type="button" name="submit_button" value="登录" id="button_login"/></td>
                    </tr>

                </table>
          </form>
          <div id="login_error_text" class="error_text"></div>
      </div>

    <div class="register_block" id="register_block">
        <a id="login_text">
            已有账号 →
        </a>
        <form name="register" id="register" action="" method="POST">
            <table id="register_table">
                <tr>
                    <td><input type="email" name="email" id="register_email" placeholder="邮箱地址"/></td>
                </tr>
                <br/>
                <tr>
                    <td><input type="text" name="username" id="register_username" placeholder="用户名 (2-16字符)"/></td>
                </tr>
                <br/>
                <tr>
                    <td><input type="password" name="password" id="register_password" placeholder="密码 (6-16字符)"/></td>
                </tr>
                <br/>
                <tr>
                    <td><input type="button" name="submit_button" value="注册" id="button_register"/></td>
                </tr>
            </table>
        </form>
        <div id="register_error_text" class="error_text"></div>
    </div>

</div>
<div class="body">
    <div class="inner_body">
        <div class="hot_users">
            <a class="hot_text" id="hot_user_text">热门用户</a>
            <hr/>
            <div class="hot_user_image">
                <%--<a href="#"><img src="images/1.jpg"/></a>--%>
                <%--<a href="#"><img src="images/2.jpg"/></a>--%>
                <%--<a href="#"><img src="images/3.jpg"/></a>--%>
                <%--<a href="#"><img src="images/4.jpg"/></a>--%>
                <%--<a href="#"><img src="images/5.jpg"/></a>--%>
                <%--<a href="#"><img src="images/6.jpg"/></a>--%>
                <%--<a href="#"><img src="images/7.jpg"/></a>--%>
                <%--<a href="#"> <img src="images/8.jpg"/></a>--%>

                    <c:forEach items="${user_list}" var="user" varStatus="index">
                        <c:if test="${user_list!=null}">
                           <a href="userProfileServlet?userID=${user.userID}"><img src=${user.headURL} title=${user.username} alt=${user.username}></a>

                        </c:if>

                        <%--<c:if test="${empty user_list}">--%>
                            <%--<span class="no_hot"> <span>:( 暂时还没有热门用户</span></span>--%>
                        <%--</c:if>--%>
                    </c:forEach>
                    <c:if test="${empty user_list}">
                        <span class="no_hot">:( 暂时还没有热门用户</span>
                    </c:if>

            </div>
        </div>
        <div class="hot_questions">
            <a class="hot_text" id="hot_question_text">热门问题</a>
            <hr/>
            <%--<li><a href="#">水煮鱼和酸菜鱼哪个更好吃？</a></li>--%>
            <%--<li><a href="#">星期一晚上应该吃什么？</a></li>--%>
            <%--<li><a href="#">室友很逗比是什么体验？</a></li>--%>
            <%--<li><a href="#">你所认识的最棒的人是怎么样的？</a></li>--%>
            <%--<li><a href="#">如何捏造奇怪的数据？</a></li>--%>
            <%--<li><a href="#">TA看到这个PJ会有什么感想？</a></li>--%>
            <%--<li><a href="#">请大家帮助我测试一下这个页面，我想试试一个很长的标题，来看看它会不会换行以及效果怎么样，我猜应该是会换行的吧？</a></li>--%>

            <c:forEach items="${question_list}" var="question" varStatus="index">
                <c:if test="${question_list!=null}">
                    <li><a href="questionServlet?questionID=${question.questionID}">${question.questionTitle}</a></li>

                </c:if>

                <%--<c:if test="${empty question_list}">--%>
                    <%--<span class="no_hot"> <span>:( 暂时还没有热门问题</span></span>--%>
                <%--</c:if>--%>
            </c:forEach>
            <c:if test="${empty question_list}">
                <span class="no_hot">:( 暂时还没有热门问题</span>
            </c:if>


        </div>
    </div>
</div>
</div>
<div id="footer">CopyRight©  2015 &nbsp;  New Sexy</div>
</body>
</html>