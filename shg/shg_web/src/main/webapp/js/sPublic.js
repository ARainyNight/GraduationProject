/**
 * ************************************************* 后端接口01--用户登陆 ************************************************
 * 1. 获取用户名和密码，将密码md5加密
 * 2. 发送数据至到后台
 */
function login() {
    var username = $("#username").val();
    var passwordTemp = $("#passwordTemp").val();
    $("#password").val($.md5(passwordTemp));
    if (username == "" || username == null || passwordTemp == "" || passwordTemp == null){
        var warnMessage = Messager["ms100012"];
        $("#warn-message").html(warnMessage).css("display","block");
        return;
    }
    var loginForm = $("#login-form");
    var url = "http://www.baidu.com"; // TODO 填入后台url
    loginForm.attr({
        "action":url,
        "method":"post"
    });
    loginForm.submit();
}

///////////////////////////// 获取用户信息并填写到 info-box 中，然后显示 info-box //////////////////////////////////////////
function modify(e){
    var wrapEle = $(e).siblings(".hidden");
    var pageWordWrap = $("#page-word-wrap");
    if (wrapEle.children("input[name='role']").val() == "ms100054"){ // 管理员
        alert("管理员用户不允许编辑！请在数据库中修改，或在个人页面中修改");
        return;
    }

    $("#info-modify").removeClass("faded");
    $("#shadow").removeClass("hidden");

    // 获取表格行信息充填到卡片中
    if (wrapEle.children("input[name='role']").val() == "ms100051"){ // 学生用户
        $("#qq").addClass("no-data").attr("disabled","disabled").val("");
        $("#intro").addClass("no-data").attr("disabled","disabled").val("");
        $("#percentage").addClass("no-data").attr("disabled","disabled").val("");
        $("#quo").addClass("no-data").attr("disabled","disabled").val("");
        $("#recommend").addClass("hidden").attr("disabled","disabled").val("");
    } else if (wrapEle.children("input[name='role']").val() == "ms100052"){ // 供应商
        $("#qq").addClass("no-data").attr("disabled","disabled").val("");
        $("#intro").addClass("no-data").attr("disabled","disabled").val("");
        $("#quo").addClass("no-data").attr("disabled","disabled").val("");
        $("#recommend").addClass("hidden").attr("disabled","disabled").val("");

        $("#qq").removeClass("no-data").val(wrapEle.children("input[name='qq']").val()).removeAttr("disabled");
        $("#percentage").removeClass("no-data").val(wrapEle.children("input[name='precentage']").val()).removeAttr("disabled");
    } else { // 讲师用户
        $("#qq").removeClass("no-data").val(wrapEle.children("input[name='qq']").val()).removeAttr("disabled");
        $("#percentage").removeClass("no-data").val(wrapEle.children("input[name='precentage']").val()).removeAttr("disabled");
        $("#intro").removeClass("no-data").val(wrapEle.children("input[name='intro']").val()).removeAttr("disabled");
        $("#quo").removeClass("no-data").val(wrapEle.children("input[name='quo']").val()).removeAttr("disabled");

        // 处理多选框
        $("#recommend").removeClass("hidden").removeAttr("disabled");
        var recomInmain = wrapEle.children("input[name='首页推荐code']").val();
        var recomInCenter = wrapEle.children("input[name='中心推荐code']").val();
        if (recomInCenter == true){
            $("#r-center").attr("checked","checked")
        } else {
            $("#r-center").removeAttr("checked")
        }

        if (recomInmain == true){
            $("#r-main").attr("checked","checked")
        } else {
            $("#r-main").removeAttr("checked")
        }
    }
    $("#myImg").attr("src", wrapEle.children("input[name='user-img']").val());
    $("#memberName").val(wrapEle.children("input[name='member-name']").val());
    $("#password").val(wrapEle.children("input[name='password']").val());
    setRole(wrapEle.children("input[name='role']").val());
    $("#createTime").val(wrapEle.children("input[name='create-time']").val());
    pageWordWrap.children("input[name='selectOption']").val($("#select").val());
    pageWordWrap.children("input[name='selectTime']").val($("#data").val());
    pageWordWrap.children("input[name='searchWord']").val($("#search-input").val());
    pageWordWrap.children("input[name='allPage']").val($("#allPage").val());
    pageWordWrap.children("input[name='targetPage']").val($("#targetPage").val());

}
function setRole(code) {
    var roleStr = "option[value='"+code+"']";
    switch (code){
        case "ms100051": $("#role").children(roleStr).attr("selected","selected");break;
        case "ms100052": $("#role").children(roleStr).attr("selected","selected");break;
        case "ms100053": $("#role").children(roleStr).attr("selected","selected");break;
        case "ms100054": $("#role").children(roleStr).attr("selected","selected");break;
    }
    console.log($("#role").children(roleStr));
}

/**
 * *********************************************** 后端接口 -- 删除用户 **************************************************
 * 1. 获取用户id，发送至服务器请求删除
 * 2. 根据结果表现（成功则更新表格，失败则提示信息）
 * @param e
 */
function deleteIt(e) {
    var wrapEle = $(e).siblings(".hidden");
    var data = {
        "uid":wrapEle.children("input[name='user-id']").val(),
        "selectOption":$("#select").val(),
        "selectTime":$("#data").val(),
        "searchWord":$("#search-input").val()
    };

    $.ajax({
        url:"json/delete-user.txt",
        method:"get",
        data:data,
        dataType:"json",
        success:function (data) {
            var code = data[Messager["CODE"]];
            var test = Messager["CODE"];
            var test01 = data["code"];
            var icon = 0;
            if (code == "ms100064"){
                icon = 1;
                // 从页面上删除
                $(e).parent().parent().remove();
            } else {
                icon = 2;
            }
            layer.alert(Messager[code],{icon:icon})
        }
    })


}



function search(e) {
    var data = {
        "targetPage":1,
        "pageNum":30,
        "selectOption":$("#select").val(),
        "selectTime":$("#data").val(),
        "searchWord":$("#search-input").val()
    };
    console.log(data);
    initUserPage(data);
}
function selectSearch(e) {
    var data = {
        "targetPage":1,
        "pageNum":30,
        "selectOption":$("#select").val(),
        "selectTime":$("#data").val(),
        "searchWord":""
    };
    console.log(data);
    initUserPage(data);
}
function gotoPage(e) {
    var data = {
        "targetPage":$(e).html(),
        "pageNum":30,
        "selectOption":$("#select").val(),
        "selectTime":$("#data").val(),
        "searchWord":$("#search-input").val()
    };
    console.log(data);
    initUserPage(data);
}
function gotoButton(e) {
    if ($("#page-goto").val()==""){
        return
    }
    var data = {
        "targetPage":$("#page-goto").val(),
        "pageNum":30,
        "selectOption":$("#select").val(),
        "selectTime":$("#data").val(),
        "searchWord":$("#search-input").val()
    };
    console.log(data);
    initUserPage(data);
}

/**
 * ********************************************查询数据接口，搜索、分页共用**************************************************
 * 后台转发到原页面
 * @param url
 */
function initUserPage(data) {
    $.ajax({
        url:"",
        data:data,
        method:"post"
    })
}

/**
 * **************************************************** 添加用户 ********************************************************
 */
function addUser(){
    var formdata = new FormData();

    var userImg = $("#user-img-add")[0].files[0]; // 文件对象
    var username = $("#username-add").val();
    var password = $("#password-add").val();
    var memberName = $("#memberName-add").val();
    var role = $("#role-add").val();
    var qq = $("#qq-add").val();
    var precentage = $("#precentage-add").val();
    var intro = $("#intro-add").val();
    var quo = $("#quo-add").val();

    var img = $("#user-img-add")[0].value;
    if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(img) && userImg != undefined) {
        layer.alert("图片类型必须是.gif,jpeg,jpg,png中的一种",{icon:2});
        return;
    }
    if (username == ""){
        layer.alert("手机号不能为空",{icon:2});
        return
    } else if (username.length != 11){
        layer.alert("请输入正确的手机号",{icon:2});
        return
    }
    if (password == ""){
        layer.alert("密码不能为空",{icon:2});
        return
    } else if (password.length<8 || password.length>16){
        layer.alert("密码长度应在8~16位",{icon:2});
        return
    }
    if (memberName == ""){
        layer.alert("会员名不能为空",{icon:2});
        return
    } else if (memberName.length>16){
        layer.alert("会员名长度不能超过16个字",{icon:2});
        return
    }
    if (role!="ms100051" && precentage == ""){
        layer.alert("必须设置分销比",{icon:2});
        return
    } else if (parseInt(precentage)>100 || precentage < 0){
        layer.alert("请输入正确的分销比",{icon:2});
        return
    }

    formdata.append("formdata", formdata);
    formdata.append("userImg", userImg);
    formdata.append("username", username);
    formdata.append("password", password);
    formdata.append("memberName", memberName);
    formdata.append("role", role);
    formdata.append("qq", qq);
    formdata.append("precentage", precentage);
    formdata.append("intro", intro);
    formdata.append("quo", quo);

    console.log(formdata);
    $.ajax({
        url: 'json/add.txt',
        type: 'POST',
        cache: false,
        data: formdata,
        dataType:"json",
        processData: false,
        contentType: false,
        success:function (data) {
            var code = data[Messager["CODE"]];
            if (code == "ms100060"){
                $("#add-one-wrap").empty().append("" +
                    "<h3 class='add-one-title'>添加用户</h3>\n" +
                    "                <!--<p class='error-info'><span class='fa fa-exclamation-circle'></span>这是提示信息</p>-->\n" +
                    "                <div class='ipt-wrap'>\n" +
                    "                    <span>头像</span>\n" +
                    "                    <label for='user-img-add' class='user-img'>选择头像</label>\n" +
                    "                    <input type='file' id='user-img-add' class='hidden'>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap'>\n" +
                    "                    <span>手机号</span>\n" +
                    "                    <input type='text' class='ipt-default' id='username-add'>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap'>\n" +
                    "                    <span>密码</span>\n" +
                    "                    <input type='password' class='ipt-default' id='password-add'>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap'>\n" +
                    "                    <span>会员名</span>\n" +
                    "                    <input type='text' class='ipt-default' id='memberName-add'>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap'>\n" +
                    "                    <span>角色</span>\n" +
                    "                    <select style='font-size: 10px;height: 20px;border-color: #ccc;color: #555;' id='role-add' onchange='changeRole(this)'>\n" +
                    "                        <option value='ms100053'>教师用户</option>\n" +
                    "                        <option value='ms100051'>学生用户</option>\n" +
                    "                        <option value='ms100052'>供应商</option>\n" +
                    "                        <option value='ms100054'>管理员</option>\n" +
                    "                    </select>\n" +
                    "                    <script>\n" +
                    "                        function changeRole(e) {\n" +
                    "                            var code = $(e).val();\n" +
                    "                            switch (code){\n" +
                    "                                case 'ms100053':$('#qq-wrap').removeClass('hidden');\n" +
                    "                                                $('#pre-wrap').removeClass('hidden');\n" +
                    "                                                $('#intro-wrap').removeClass('hidden');\n" +
                    "                                                $('#quo-wrap').removeClass('hidden');\n" +
                    "                                    break;\n" +
                    "                                case 'ms100054':;\n" +
                    "                                case 'ms100051':$('#qq-wrap').addClass('hidden');\n" +
                    "                                                $('#pre-wrap').addClass('hidden');\n" +
                    "                                                $('#intro-wrap').addClass('hidden');\n" +
                    "                                                $('#quo-wrap').addClass('hidden');\n" +
                    "                                                break;\n" +
                    "                                case 'ms100052':$('#qq-wrap').removeClass('hidden');\n" +
                    "                                                $('#pre-wrap').removeClass('hidden');\n" +
                    "                                                $('#intro-wrap').addClass('hidden');\n" +
                    "                                                $('#quo-wrap').addClass('hidden');\n" +
                    "                                                break;\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    </script>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap' id='qq-wrap'>\n" +
                    "                    <span>腾讯QQ</span>\n" +
                    "                    <input type='text' class='ipt-default' id='qq-add'>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap' id='pre-wrap'>\n" +
                    "                    <span>分销比</span>\n" +
                    "                    <input type='number' placeholder='分销比在 0~100之间' class='ipt-default' id='precentage-add'>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap' id='intro-wrap'>\n" +
                    "                    <span class='exp-span'>讲师简介</span>\n" +
                    "                    <textarea type='text' class='scroller-default' id='intro-add'></textarea>\n" +
                    "                </div>\n" +
                    "                <div class='ipt-wrap' id='quo-wrap'>\n" +
                    "                    <span class='exp-span'>讲师语录</span>\n" +
                    "                    <textarea type='text' class='scroller-default' id='quo-add'></textarea>\n" +
                    "                </div>\n" +
                    "                <button class='btn-default class-11 btn' onclick='addUser()'>确定添加</button>" +
                    "");
                layer.alert(Messager[code],{icon:1});
            } else {
                layer.alert(Messager[code],{icon:2});
            }
        }
    })
}

/////////////////////////////////////////////////////// 填充信息 ///////////////////////////////////////////////////////
function modifyGood(e) {
    var wrapEle = $(e).siblings(".hidden");
    var pageWordWrap = $("#page-word-wrap");
    if (wrapEle.children("input[name='role']").val() == "ms100054"){ // 管理员
        alert("管理员用户不允许编辑！请在数据库中修改，或在个人页面中修改");
        return;
    }

    $("#info-modify").removeClass("faded");
    $("#shadow").removeClass("hidden");


    var gTitle = wrapEle.children("input[name='g-title']").val();
    var gType = wrapEle.children("input[name='g-type']").val();
    var gClass = wrapEle.children("input[name='g-class']").val();
    var gImgMain = wrapEle.children("input[name='g-img-main']").val();
    var gImgDetail = wrapEle.children("input[name='g-img-detail']").val();
    var gPrice = wrapEle.children("input[name='g-price']").val();
    var gStatus = wrapEle.children("input[name='g-status']").val();
    var gSaledNum = wrapEle.children("input[name='g-saled-num']").val();
    var gCreateTime = wrapEle.children("input[name='g-create-time']").val();
    var codeMain = wrapEle.children("input[name='首页推荐code']").val();

    var gBeginTime = wrapEle.children("input[name='g-begin-time']").val();
    var gLong = wrapEle.children("input[name='g-long']").val();
    var gIntro = wrapEle.children("input[name='g-intro']").val();
    var gSubject = wrapEle.children("input[name='g-subject']").val();
    var gUrl = wrapEle.children("input[name='g-url']").val();
    var gRestNum = wrapEle.children("input[name='g-rest-num']").val();
    var gPostCost = wrapEle.children("input[name='g-post-cost']").val();

    var codeCenter = wrapEle.children("input[name='中心推荐code']").val();
    var codeCenterCu = wrapEle.children("input[name='中心轮播推荐code']").val();

    var codeMall = wrapEle.children("input[name='商城推荐code']").val();
    var codeMallCu = wrapEle.children("input[name='商城轮播推荐code']").val();


    $("#g-type").val(Messager[gType]);
    $("#title").val(gTitle);
    $("#g-class").val(gClass);
    $("#g-price").val(gPrice);
    $("#createTime").val(gCreateTime);
    $("#saledNum").val(gSaledNum);
    $("#main-img").removeClass('hidden').attr('src', gImgMain);
    if (gImgDetail!="") {
        $("#main-img").removeClass('hidden').attr('src', gImgMain);
    } else {
        $("#main-img").addClass('hidden');

    }
    if (gStatus == 'true'){
        $("#ms110097").attr('selected','selected');
        $("#ms110098").removeAttr('selected')
    } else {
        $("#ms110098").attr('selected','selected');
        $("#ms110097").removeAttr('selected')
    }
    if (codeMain == 'true'){
        $("#r-main").attr('checked', 'checked');
    } else {
        $("#r-main").removeAttr('checked');
    }

    $("#ms200021").addClass('hidden');
    $("#ms200022").addClass('hidden');
    $("#ms200023").addClass('hidden');
    $("#ms200024").addClass('hidden');
    if (gType == "ms110092" || gType=="ms110093" || gType=="ms110094" || gType=="ms110095" ){
        if (codeCenter == 'true'){
            $("#ms200021").removeClass('hidden');
        }
        if (codeCenterCu == 'true'){
            $("#ms200022").removeClass('hidden');
        }
    }
    if (gType == "ms110096"){
        if (codeMall == 'true'){
            $("#ms200023").removeClass('hidden');
        }
        if (codeMallCu == 'true'){
            $("#ms200024").removeClass('hidden');
        }
    }

    $("#saledNum").addClass('no-data').attr('disabled','disabled');

    switch (gType){
        case "ms110092":"直播类";
            $("#beginTime").removeClass('no-data').removeAttr('disabled').val(gBeginTime);
            $("#long").removeClass('no-data').removeAttr('disabled').val(gLong);

            if (gSaledNum!=0){
                $("#beginTime").attr('disabled');
                $("#long").attr('disabled');
            }

            $("#g-post-cost").addClass('no-data').attr('disabled','disabled');
            $("#restNum").addClass('no-data').attr('disabled','disabled');
            $("#g-subject").addClass('hidden');
            $("#intro").addClass('no-data').attr('disabled','disabled');
            $("#url").addClass('no-data').attr('disabled','disabled');
            break;
        case "ms110093":"录播类";
            $("#long").removeClass('no-data').removeAttr('disabled').val(gLong);
            if (gSaledNum!=0){
                $("#long").attr('disabled');
            }

            $("#beginTime").addClass('no-data').attr('disabled','disabled');
            $("#g-post-cost").addClass('no-data').attr('disabled','disabled');
            $("#restNum").addClass('no-data').attr('disabled','disabled');
            $("#g-subject").addClass('hidden');
            $("#url").addClass('no-data').attr('disabled','disabled');
            $("#intro").addClass('no-data').attr('disabled','disabled');
            break;
        case "ms110094":"线下类";
            $("#g-subject").removeClass('hidden').removeAttr('disabled');
            $("#g-subject").children("option[value='"+gSubject+"']");

            $("#title").addClass('no-data').attr('disabled','disabled');
            $("#main-img").addClass('hidden');
            $("#recommend").addClass('hidden');
            $("#g-class").addClass('no-data').attr('disabled','disabled');
            $("#intro").addClass('no-data').attr('disabled','disabled');
            $("#createTime").addClass('no-data').attr('disabled','disabled');
            $("#main-img").addClass('hidden');
            $("#beginTime").addClass('no-data').attr('disabled','disabled');
            $("#g-post-cost").addClass('no-data').attr('disabled','disabled');
            $("#restNum").addClass('no-data').attr('disabled','disabled');
            $("#long").addClass('no-data').attr('disabled','disabled');
            $("#url").addClass('no-data').attr('disabled','disabled');
            break;
        case "ms110095":"电子类";
            $("#intro").removeClass('hidden').val(gIntro);
            $("#url").removeClass('no-data').removeAttr('disabled').val(gUrl);

            $("#long").addClass('no-data').attr('disabled','disabled');
            $("#beginTime").addClass('no-data').attr('disabled','disabled');
            $("#g-post-cost").addClass('no-data').attr('disabled','disabled');
            $("#restNum").addClass('no-data').attr('disabled','disabled');
            $("#g-subject").addClass('hidden');
            break;
        case "ms110096":"教辅类";
            $("#g-post-cost").removeClass('no-data').removeAttr('disabled').val(gPostCost);

            $("#long").removeClass('no-data').removeAttr('disabled').val(gLong);
            $("#beginTime").addClass('no-data').attr('disabled','disabled');
            $("#restNum").addClass('no-data').attr('disabled','disabled');
            $("#g-subject").addClass('hidden');
            $("#url").addClass('no-data').attr('disabled','disabled');
            $("#intro").addClass('no-data').attr('disabled','disabled');
            break;
    }


}

function deleteItGood(e) {

}

/**
 * ********************************************* 后台接口，添加商品 ******************************************************
 */
function addGood() {
    var formdata = new FormData();

    if ($("#add-g-img-main").val() == ""){
        layer.alert("必须选择一张商品图片！",{icon:2});
        return;
    }

    var img = $("#add-g-img-main")[0].files[0];
    var title = $("#quo-add").val();
    var type = $("#add-g-type").val();
    var classMark = $("#add-g-class").val();
    var price = $("#add-g-price").val();
    var beginTime = $("#add-g-begin-time").val();
    var intro = $("#add-g-intro").val();
    var url = $("#add-g-url").val();
    var restNum = $("#add-g-rest-num").val();
    var postCost = $("#add-g-post-cost").val();
    var c01Value = $("#label-c01 input").val();
    var c02Value = $("#label-c02 input").val();
    var m01Value = $("#label-m01 input").val();
    var m02Value = $("#label-m02 input").val();
    var mainValue = $("#ms200020-add").val();
    var subject = $("#g-subject-value").val(); // 保留学科选择

    if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($("#add-g-img-main")[0].value)) {
        layer.alert("图片类型必须是.gif,jpeg,jpg,png中的一种",{icon:2});
        return;
    }
    if (title == ""){
        layer.alert("商品标题不能为空",{icon:2});
        return;
    }
    if (type == ""){
        layer.alert("商品分类必须选择",{icon:2});
        return;
    }
    if (classMark == ""){
        layer.alert("商品类型不能为空",{icon:2});
        return;
    }
    if (price == ""){
        layer.alert("商品价格不能为空",{icon:2});
        return;
    }
    if (beginTime == "" && type=="ms110092"){
        layer.alert("请选择开课时间",{icon:2});
        return;
    }
    if (intro == "" && type == "ms110095"){
        layer.alert("请输入电子类商品介绍信息",{icon:2});
        return;
    }
    if (url == "" && type == "ms110095"){
        layer.alert("请输入电子类商品链接",{icon:2});
        return;
    }
    if (postCost == "" && type == "ms110096"){
        layer.alert("请输入邮费.（包邮邮费为0）",{icon:2});
        return;
    }
    if (restNum == "" && type == "ms110096"){
        layer.alert("请输入商品库存",{icon:2});
        return;
    }

    formdata.append("img", img);
    formdata.append("title", title);
    formdata.append("type", type);
    formdata.append("classMark", classMark);
    formdata.append("price", price);
    formdata.append("beginTime", beginTime);
    formdata.append("intro", intro);
    formdata.append("url", url);
    formdata.append("restNum", restNum);
    formdata.append("postCost", postCost);
    formdata.append("c01Value", c01Value);
    formdata.append("c02Value", c02Value);
    formdata.append("m01Value", m01Value);
    formdata.append("m02Value", m02Value);
    formdata.append("mainValue", mainValue);
    formdata.append("subject", subject);

    console.log(formdata);
    $.ajax({
        url: 'json/goodAdd.txt',
        type: 'POST',
        cache: false,
        data: formdata,
        dataType:"json",
        processData: false,
        contentType: false,
        success:function (data) {
            var code = data[Messager["CODE"]];
            if (code == "ms100080"){
                layer.alert(Messager[code],{icon:1});
                $("#add-g-img-main").val("");
                $("#quo-add").val("");
                $("#add-g-type option[value='']").attr('selected','selected'); // 保留大类选择
                $("#add-g-type").trigger("change");
                $("#add-g-class").val(); // 保留小类选择
                $("#add-g-price").val("");
                $("#add-g-begin-time").val("");
                $("#add-g-intro").val("");
                $("#add-g-url").val("");
                $("#add-g-rest-num").val("");
                $("#add-g-post-cost").val("");
                $("#label-c01 input").removeAttr('checked');
                $("#label-c02 input").removeAttr('checked');
                $("#label-m01 input").removeAttr('checked');
                $("#label-m02 input").removeAttr('checked');
                $("#ms200020-add").removeAttr('checked');
                $("#g-subject-value").val(); // 保留学科选择

            } else {
                layer.alert(Messager[code],{icon:2});
            }
        }
    })
}




























