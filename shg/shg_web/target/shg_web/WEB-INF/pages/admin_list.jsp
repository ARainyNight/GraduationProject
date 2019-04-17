<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    管理员
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">个人信息</a></dd>
                    <dd><a href="">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
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
                <li class="layui-nav-item"><a href="">商品审核</a></li>
                <li class="layui-nav-item"><a href="">交易记录</a></li>
            </ul>
        </div>
    </div>
    <div id="test11111"
         style="display: none;padding: 0px;margin-top:50px;margin-left:10px">
        <form class="layui-form layui-form-pane1" action="/admin/adminList">
            <div class="layui-form-item">
                <label class="layui-form-label" >管理员姓名</label>
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
                <div style="margin-top:100px;margin-left:70px"
                     class="layui-btn-container">
                    <button class="layui-btn" lay-submit lay-filter="*">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>

    <div id="test222"
         style="display: none;padding: 0px;margin-top:50px;margin-left:10px">
        <form class="layui-form layui-form-pane1" action="/admin/adminList">
            <div class="layui-form-item">
                <label class="layui-form-label" >ID</label>
                <div class="layui-input-inline">
                    <input type="text" name="id" lay-verify="required" readonly="readonly"
                           placeholder="" autocomplete="off" class="inputid layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" >管理员姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required"
                           placeholder="" autocomplete="off" class="inputname layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="password" lay-verify="required"
                           placeholder="" autocomplete="off" class="inputpassword layui-input">
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
                <button class="layui-btn layui-btn-normal" id="admin_add">添加管理员</button>
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
            , url: '/admin/adminJson' //数据接口
            , title: '管理员表'
            , page: false //开启分页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'aid', title: 'ID', width: 80, height: 50, sort: true, fixed: 'left'}
                , {field: 'aname', title: '管理员姓名', height: 50, width: 180}
                , {field: 'apassword', title: '密码', height: 50, width: 180}
                , {fixed: 'right', width: 165, align: 'center', height: 50, toolbar: '#barDemo'}
            ]]
        });

        function DeleteAdmin(aid) {
            $.ajax({
                "url": "http://localhost:8080/admin/deleteAdmin",
                "type": "get",
                "data": {
                    "aid": aid
                },
                "dataType": "text",
                "success": function (json) {
                    console.log(".....");
                }
            })
        }


        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            var aid = data.aid;
            // if (layEvent === 'detail') {
            //     // layer.msg('查看操作');
            // } else
            if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    console.log(data);
                    console.log(layEvent);
                    console.log(data.aid);
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    DeleteAdmin(aid);
                });
            } else if (layEvent === 'edit') {
                // layer.msg('编辑操作');
                $(".inputid").val(data.aid);
                $(".inputname").val(data.aname);
                $(".inputpassword").val(data.apassword);
                layer.open({
                    title: '编辑管理员',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['350px', '400px'],
                    content: $('#test222')
                });
            }
        });

        //添加管理员事件
        $("#admin_add").on('click',function () {
                layer.open({
                    title: '添加管理员',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['350px', '350px'],
                    content: $('#test11111')
                });
        })

    });


</script>
</body>
</html>
