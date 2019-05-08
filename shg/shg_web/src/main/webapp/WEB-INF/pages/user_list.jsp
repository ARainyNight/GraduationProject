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
                <li class="layui-nav-item layui-nav-itemed">
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
    <div id="addUser"
         style="display: none;padding: 0px;margin-top:50px;margin-left:10px">
        <form class="layui-form layui-form-pane1" action="/user/userList">
            <div class="layui-form-item">
                <label class="layui-form-label" >用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required"
                           placeholder="请输入姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="password" lay-verify="required" id="password"
                           placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" lay-verify="required" id="address"
                           placeholder="请输入地址" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-inline">
                    <input type="text" name="pnum" lay-verify="required" id="pnum"
                           placeholder="请输入联系电话" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div style="margin-top:100px;margin-left:70px"
                     class="layui-btn-container">
                    <button class="layui-btn" lay-submit lay-filter="*">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>

    <div id="updateUser"
         style="display: none;padding: 0px;margin-top:50px;margin-left:10px">
        <form class="layui-form layui-form-pane1" action="/user/userList">
            <div class="layui-form-item">
                <label class="layui-form-label" >ID</label>
                <div class="layui-input-inline">
                    <input type="text" name="id" lay-verify="required" readonly="readonly"
                           placeholder="" autocomplete="off" class="inputid layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" >用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required"
                           placeholder="请输入姓名" autocomplete="off" class="inputname layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="password" lay-verify="required"
                           placeholder="请输入密码" autocomplete="off" class="inputpassword layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" lay-verify="required"
                           placeholder="请输入地址" autocomplete="off" class="inputaddress layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-inline">
                    <input type="text" name="pnum" lay-verify="required"
                           placeholder="请输入联系电话" autocomplete="off" class="inputpnum layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div style="margin-top:100px;margin-left:70px"
                     class="layui-btn-container">
                    <button class="layui-btn" lay-submit lay-filter="**">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
    <div class="layui-body">

        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div>
                <button class="layui-btn layui-btn-normal" id="user_add">添加用户</button>
            </div>
            <table id="demo" lay-filter="test"></table>
            <script type="text/html" id="barDemo">
                <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">添加</a>--%>
                <a class="layui-btn layui-btn-xs" lay-event="edit" id="admin-edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </script>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        @ 1507094243 - 底部固定区域
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

        //添加用户form表单监听提交
        form.on('submit(*)', function(data) {
            console.log(data);
            $.ajax({
                "url":"http://localhost:8080/user/userAdd",
                "type":"get",
                "data":{
                    "name":data.field.name,
                    "password":data.field.password,
                    "address":data.field.address,
                    "pnum":data.field.pnum
                },
                "dataType":"json",
                "success":function (json) {
                    console.log("添加用户成功")
                }
            });
        });

        //编辑用户form表单提交
        form.on('submit(**)', function(data) {
            console.log(data);
            $.ajax({
                "url":"http://localhost:8080/user/updateUser",
                "type":"get",
                "data":{
                    "id":data.field.id,
                    "name":data.field.name,
                    "password":data.field.password,
                    "address":data.field.address,
                    "pnum":data.field.pnum
                },
                "dataType":"json"
            });
        });

        //第一个实例
        table.render({
            elem: '#demo'
            , height: 1000
            , url: 'http://localhost:8080/user/userJson' //数据接口
            , title: '用户表'
            , page: false //开启分页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'uid', title: 'ID', width: 80, height: 50, sort: true, fixed: 'left'}
                , {field: 'uname', title: '用户姓名', height: 50, width: 180}
                , {field: 'upassword', title: '密码', height: 50, width: 180}
                , {field: 'uaddress', title: '地址', height: 50, width: 320}
                , {field: 'upnum', title: '联系方式', height: 50, width: 320}
                , {fixed: 'right', width: 165, align: 'center', height: 50, toolbar: '#barDemo'}
            ]]
        });

        function DeleteUser(uid) {
            $.ajax({
                "url": "http://localhost:8080/user/deleteUser",
                "type": "get",
                "data": {
                    "uid": uid
                },
                "dataType": "text",
                "success": function (json) {
                    console.log("删除用户成功");
                }
            })
        }


        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            var uid = data.uid;
            // if (layEvent === 'detail') {
            //     // layer.msg('查看操作');
            // } else
            if (layEvent === 'del') {
                layer.confirm('真的删除这条消息么', function (index) {
                    console.log(data);
                    console.log(layEvent);
                    console.log(data.uid);
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    DeleteUser(uid);
                });
            } else if (layEvent === 'edit') {
                // layer.msg('编辑操作');
                $(".inputid").val(data.uid);
                $(".inputname").val(data.uname);
                $(".inputpassword").val(data.upassword);
                $(".inputaddress").val(data.uaddress);
                $(".inputpnum").val(data.upnum);
                layer.open({
                    title: '编辑用户',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['400px', '500px'],//宽，高
                    content: $('#updateUser')
                });
            }
        });

        //添加管理员事件
        $("#user_add").on('click',function () {
                layer.open({
                    title: '添加用户',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['400px', '450px'],
                    content: $('#addUser')
                });
        })

    });


</script>
</body>
</html>
