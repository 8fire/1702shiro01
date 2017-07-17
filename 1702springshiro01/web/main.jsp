
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/icon.css">
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/easyui.js"></script>
<script type="text/javascript">
    function init() {
        //表格的设计
        $("#first").datagrid({
            title : "用户管理",
            // url:"selectByUsers.do",
            pagination : true,
            striped:true,
            columns : [ [
                {
                    field : 'uid',
                    width : 50,
                    checkbox : true
                },
                {
                    field : "account",
                    title : "员工",
                    width : 100,
                    formatter:function (value,row,index) {
                        return row.users.account;
                    }
                },
                {
                    field : "dakatime",
                    title : "打卡时间",
                    width : 150
                },
         {
             field : "dakastate",
             title : "状态",
             width : 100
         }
            ] ],
            striped : true,
            loadMsg : '我们正在努力获取数据，请您稍等。。。',
            rownumbers : true,
            toolbar : [
                {
                    text: "删除",
                    iconCls: 'icon-remove',
                    handler: function () {
                        <shiro:hasPermission name="em:delete">
                        remove();
                        </shiro:hasPermission>
                        <shiro:lacksPermission name="em:delete">
                            $.messager.alert("提示","对不起你没有该权限")
                        </shiro:lacksPermission>
                    }
                }, {
                    text:"签到",
                    iconCls:'icon-add',
                    handler:function () {
                        <shiro:hasPermission name="em:daka">
                        daKa();
                        </shiro:hasPermission>
                        <shiro:lacksPermission name="em:daka">
                        $.messager.alert("提示","该用户不需要打卡")
                        </shiro:lacksPermission>

                    }
                }
            ]
        });
        load2(1,2);
    }
    $(init);

    //分页处理
    function load2(p,size) {
        $.getJSON(
            <shiro:hasPermission name="em:my">
             "selectById.do",
            </shiro:hasPermission>
            <shiro:hasPermission name="em:root">
            "selectByUsers.do",
            </shiro:hasPermission>
            {
                page: p,
                size:size
            },
            function(msg) {
               // var st= JSON.stringify(msg);
               // alert("jiazaishuju:"+st);
                $("#first").datagrid("loadData", msg.usersList);
                var pager = $("#first").datagrid("getPager");
                pager.pagination({
                    total : msg.total,
                    pageNumber : p,
                    pageList : [ 2, 4 ],
                    onSelectPage : function(page, size) {
                        //alert("大小"+size);
                        load2(page,size);
                    },
                    beforePageText : '第',
                    afterPageText : '页,共{pages}页',
                    displayMsg : '共{total}条数据',
                });
            })

    }
    function daKa() {
        var id= $("#uid").val();
        // alert(id)
        $.getJSON("daKa.do",{id:id},function(data) {
             var st= JSON.stringify(data);
             alert("jiazaishuju:"+st);
            if(data==1){
                load2(1,2);
                $.messager.alert("提示","恭喜你签到成功") ;
            }else if(data==2){
                $.messager.alert("提示","今天你已经签到过了") ;
            }
        })
    }
    //多条数据删除
    function remove() {
        var data = $("#first").datagrid('getSelections');
       // var st= JSON.stringify(data);
        //alert(st);
        if(data.length!=0){
            $.messager.confirm('提示框', '你确定要删除吗', function (r) {
                if (r) {
                    var ids = []
                    for (var i = 0; i < data.length; i++) {
                        ids[i] = data[i].id;
                    }
                    //var x = JSON.stringify(ids);
                   // alert(x)
                    $.ajax({
                        url : "removeDakaRecode.do",
                        method : "post",
                        data : x,
                        contentType : "application/json",
                        success : function(msg) {
                            alert(msg)
                            if (msg ==1) {
                                $.messager.alert('提示框', '恭喜您删除成功');
                                load2(1,2);
                            }else {
                                $.messager.alert('提示框', '删除数据失败');
                            }
                        }
                    });
                }
            });
        }else {
            $.messager.alert('提示框',"请选择要删除的用户");
        }
    }



</script>


</head>
<body>
<div>欢迎你<span style="color: red">${sessionScope.users.account}</span></div>
<input type="hidden" value="${sessionScope.users.uid}" id="uid">
<div id="first">

</div>


<!-- 弹窗 -->
<%--<div id="users_window" title="权限分配" class="easyui-window"
     style="width:300px;height:400px" data-options="closed:true,modal:true">
    <ul id="muen_tree" class="easyui-tree"
        data-options="url:'../seleteAllResource.do',checkbox:true"></ul>
    <div style="display:flex;justify-content:center">
        <a href="javascript:dofenp()" class="easyui-linkbutton">保存</a>
    </div>

</div>--%>

</body>
</html>
