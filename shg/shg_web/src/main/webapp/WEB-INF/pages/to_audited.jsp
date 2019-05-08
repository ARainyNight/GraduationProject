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
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">商品审核</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/commodity/toAuditedList">待审核商品</a></dd>
                        <dd><a href="/commodity/auditPassList">已审核商品</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item ">
                    <a href="javascript:;">交易记录</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/trade/toUnshipped">未发货订单</a></dd>
                        <dd><a href="/trade/toShipped">已发货订单</a></dd>
                        <dd><a href="/trade/toOkOrder">已完成订单</a></dd>
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
                <a class="layui-btn layui-btn-xs" lay-event="audited" id="admin-edit">审核</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </script>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        @ 1507094243 - &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品状态0代表未审核，1代表审核通过
    </div>
</div>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script src="../src/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['table', 'element', 'layer', 'form'], function () {
        var table = layui.table;
        var element = layui.element,
            layer = layui.layer,
            form = layui.form;

        var index;

        //添加管理员form表单监听提交
        form.on('submit(*)', function(data) {
            console.log(data);
            $.ajax({
                "url":"http://localhost:8080/admin/admin_add",
                "type":"get",
                "data":{
                    "name":data.field.name,
                    "password":data.field.password
                },
                "dataType":"json"
            });
        });

        //编辑管理员form表单提交
        form.on('submit(**)', function(data) {
            console.log(data);
            $.ajax({
                "url":"http://localhost:8080/admin/admin_update",
                "type":"get",
                "data":{
                    "id":data.field.id,
                    "name":data.field.name,
                    "password":data.field.password
                },
                "dataType":"json"
            });
        });

        //第一个实例
        table.render({
            elem: '#demo'
            , height: 1000
            , url: '/commodity/toAuditedJson' //数据接口
            , title: '未审核商品表'
            , page: false //开启分页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'cid', title: 'ID', width: 60, height: 50, sort: true, fixed: 'left'}
                , {field: 'cname', title: '商品名称', height: 50, width: 180}
                , {field: 'cprice', title: '价格', height: 50, width: 90}
                , {field: 'category', title: '类别', height: 50, width: 100}
                , {field: 'cdate', title: '上架日期', height: 50, width: 180, templet : "<div>{{layui.util.toDateString(d.cdate, 'yyyy年MM月dd日 ')}}</div>"}
                , {field: 'user', title: '卖家姓名', height: 50, width: 100,templet: '<div>{{d.user.uname}}</div>'}
                , {field: 'user', title: '卖家联系方式', height: 50, width: 150,templet: '<div>{{d.user.upnum}}</div>'}
                , {field: 'cstatus', title: '状态', height: 50, width: 180}
                , {fixed: 'right', width: 165, align: 'center', height: 50, toolbar: '#barDemo'}
            ]]
        });

        function DeleteCommodity(cid) {
            $.ajax({
                "url": "http://localhost:8080/commodity/deleteCommodity",
                "type": "get",
                "data": {
                    "cid": cid
                },
                "dataType": "text",
                "success": function (json) {
                    console.log(".....");
                }
            })
        }

        function AuditPass(cid){
            $.ajax({
                "url":"http://localhost:8080/commodity/AuditPass",
                "type":"get",
                "data":{
                    "cid":cid
                },
                "dataType":"text",
                "success":function (json) {
                    console.log("审核通过！")
                }
            })
        }


        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            var cid = data.cid;
            console.log(data);
            console.log(data.user.uname);
            if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    console.log(data);
                    console.log(layEvent);
                    console.log(cid);
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    DeleteCommodity(cid);
                });
            } else if (layEvent === 'audited') {
               layer.confirm('确认审核通过',function (index) {
                   obj.del();
                   layer.close(index);
                   //向服务端发送审核通过指令
                   AuditPass(cid);
               })
            }
        });

    });


</script>
</body>
</html>
