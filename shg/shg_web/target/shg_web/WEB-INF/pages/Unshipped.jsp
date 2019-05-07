<%@ page import="com.nuc.shg.entity.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%Admin admin = (Admin) session.getAttribute("admin");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>二手物品交易平台后台管理系统</title>
    <link rel="stylesheet" href="../src/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <div class="layui-header">
        <div class="layui-logo"> 后台管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <%--<ul class="layui-nav layui-layout-left">--%>
        <%--<li class="layui-nav-item"><a href="">控制台</a></li>--%>
        <%--<li class="layui-nav-item"><a href="">商品管理</a></li>--%>
        <%--<li class="layui-nav-item"><a href="">用户</a></li>--%>
        <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">其它系统</a>--%>
        <%--<dl class="layui-nav-child">--%>
        <%--<dd><a href="">邮件管理</a></dd>--%>
        <%--<dd><a href="">消息管理</a></dd>--%>
        <%--<dd><a href="">授权管理</a></dd>--%>
        <%--</dl>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <%=admin.getAname()%>
                </a>
                <%--<dl class="layui-nav-child">--%>
                    <%--<dd><a href="">个人信息</a></dd>--%>
                    <%--<dd><a href="">修改密码</a></dd>--%>
                <%--</dl>--%>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/quit">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item ">
                    <a href="javascript:;">管理员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/adminList">所有管理员</a></dd>
                        <%--<dd><a href="javascript:;">添加管理员</a></dd>--%>
                        <%--<dd><a href="javascript:;">列表三</a></dd>--%>
                        <%--<dd><a href="">超链接</a></dd>--%>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/user/userList">所有用户</a></dd>
                        <%--<dd><a href="javascript:;">列表二</a></dd>--%>
                        <%--<dd><a href="">超链接</a></dd>--%>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">商品审核</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/commodity/toAuditedList">待审核商品</a></dd>
                        <dd><a href="/commodity/auditPassList">已审核商品</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">交易记录</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/trade/toUnshipped">未发货订单</a></dd>
                        <dd><a href="javascript:;">已发货订单</a></dd>
                        <dd><a href="javascript:;">已完成订单</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">

        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <table id="demo" lay-filter="test"></table>
            <script type="text/html" id="barDemo">
                <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">添加</a>--%>
                <%--<a class="layui-btn layui-btn-xs" lay-event="audited" id="admin-edit">审核</a>--%>
                <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
            </script>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        @ 1507094243 - &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单状态0代表未发货，1代表已发货，2代表交易完成
    </div>
</div>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script src="../src/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['table', 'element', 'layer', 'form'], function () {
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            , height: 1000
            , url: '/trade/getUnshipped' //数据接口
            , title: '未发货订单表'
            , page: false //开启分页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'tid', title: 'ID', width: 60, height: 50, sort: true, fixed: 'left'}
                , {field: 'tcname', title: '商品名称', height: 50, width: 120}
                , {field: 'tcprice', title: '价格', height: 50, width: 60}
                , {field: 'tdate', title: '上架日期', height: 50, width: 150, templet : "<div>{{layui.util.toDateString(d.tdate, 'yyyy年MM月dd日 ')}}</div>"}
                , {field: 'buyid', title: '买家id', height: 50, width: 80}
                , {field: 'buyname', title: '买家姓名', height: 50, width: 90}
                , {field: 'buyaddress', title: '买家地址', height: 50, width: 150}
                , {field: 'sellerid', title: '卖家id', height: 50, width: 80}
                , {field: 'sellername', title: '卖家姓名', height: 50, width: 100}
                , {field: 'status', title: '订单状态', height: 50, width: 100}
            ]]
        });
    });
</script>
</body>
</html>
