var kynamic = {
    /**
     * 凡是树的操作
     */
    kynamicTree: {
        pNode: '',
        zTree: '',
        setting: {
            isSimpleData: true,
            treeNodeKey: "kid",
            treeNodeParentKey: "pid",
            //keepParent:true,
            showLine: true,
            root: {
                isRoot: true,
                nodes: []
            },
            callback: {
                rightClick: function(event, treeId, treeNode){
                    /**
                     * 在点击右键的时候，把treeNode赋值给pNode
                     */
                    kynamic.kynamicTree.pNode = treeNode;
                    /**
                     * 判断点击的是文件夹节点还是文件节点
                     */
                    if (treeNode.isParent) {//文件夹节点
                        kynamic.kynamicTree.controlRMenu({
                            x: event.clientX,
                            y: event.clientY,
                            addFile: true,
                            addFolder: true,
                            deleteNode: true,
                            updateNode: true
                        });
                    }
                    else {//文件节点
                        kynamic.kynamicTree.controlRMenu({
                            x: event.clientX,
                            y: event.clientY,
                            addFile: false,
                            addFolder: false,
                            deleteNode: true,
                            updateNode: true
                        });
                    }
                },
                /**
                 * kynamic节点的点击事件
                 */
                click: function(event, treeId, treeNode){
                    /**
                     * 因为在单击的时候，右键事件不一定发生了，所以得重新给pNode赋值
                     */
                    kynamic.kynamicTree.pNode = treeNode;
                    var parameter = {
                        kid: kynamic.kynamicTree.pNode.kid
                    };
                    $.post("kynamicAction_showVersionsByKid.action", parameter, function(data){
                        if (data.versionList.length == 0) {//没有版本
                            alert("aaaaa");
                        }
                        else {//有版本
                            //控制div和checkin和checkout按钮的显示
                            kynamic.version.controlShowVersion({
                                addVersion: false,
                                versionList: true,
                                checkin: false,
                                checkout: false
                            });
                            kynamic.version.showVersionsByKID(data.versionList);
                        }
                    });
                }
            }
        },
        loadKynamicTree: function(){
            $.post("kynamicAction_showKynamicTree.action", null, function(data){
                kynamic.kynamicTree.zTree = $("#kynamicTree").zTree(kynamic.kynamicTree.setting, data.kynamicList);
            });
        },
        /**
         * 控制右键菜单的显示
         *    div的位置
         *    右键菜单的菜单项
         */
        controlRMenu: function(rMenuJson){
            /**
             * 菜单项的显示逻辑
             */
            $("#showRMenu").show();
            $("#rMenu").css({
                "top": rMenuJson.y + "px",
                "left": rMenuJson.x + "px",
                "display": "block"
            });
            /**
             * 增加文件的菜单的显示控制
             */
            if (rMenuJson.addFile) {
                $("#addFile").show();
            }
            else {
                $("#addFile").hide();
            }
            
            if (rMenuJson.addFolder) {
                $("#addFolder").show();
            }
            else {
                $("#addFolder").hide();
            }
            
            if (rMenuJson.deleteNode) {
                $("#deleteNode").show();
            }
            else {
                $("#deleteNode").hide();
            }
            
            if (rMenuJson.updateNode) {
                $("#updateNode").show();
            }
            else {
                $("#updateNode").hide();
            }
        },
        /**
         * 增加节点
         */
        addNode: function(addJSON){
            /**
             * 1、在kynamic表中增加一行数据
             * 2、在指定的父节点下增加一个子节点
             */
            var fileName = window.prompt(addJSON.fileMessage);
            if (fileName != null) {//fileName不为null
                if (fileName != "") {
                    //检查是否有重名的现象
                    $.post("kynamicAction_isExsitName.action", {
                        name: fileName
                    }, function(data){
                        if (data.message == "1") {//重名了
                            alert("该文件名称已经存在，请重新输入");
                        }
                        else {
                            var parameter = {
                                name: fileName,
                                isParent: addJSON.isParent,
                                pid: kynamic.kynamicTree.pNode.kid
                            };
                            $.post("kynamicAction_saveKynamic.action", parameter, function(data2){
                                var kid = data2.kid;
                                var newNode = {
                                    kid: kid,
                                    pid: kynamic.kynamicTree.pNode.kid,
                                    isParent: addJSON.isParent,
                                    name: fileName
                                };
                                kynamic.kynamicTree.zTree.addNodes(kynamic.kynamicTree.pNode, newNode, true);
                            });
                        }
                    });
                }
                else {
                    alert(addJSON.errorMessage);
                }
            }
        },
        /**
         * 增加文件
         */
        addFile: function(){
            kynamic.kynamicTree.addNode({
                fileMessage: '请输入文件的名称',
                errorMessage: '文件的名称不能为空',
                isParent: false
            });
        },
        /**
         * 增加文件夹
         */
        addFolder: function(){
            kynamic.kynamicTree.addNode({
                fileMessage: '请输入文件夹的名称',
                errorMessage: '文件夹的名称不能为空',
                isParent: true
            });
        },
        /**
         * 删除节点
         */
        deleteNode: function(){
            /**
             * 1、判断当前删除的节点是否是文件节点
             *     是
             *         直接删除
             *     否
             *         判断文件夹节点下是否有子节点
             *             如果没有
             *                  删除
             *             如果有
             *                  提示不能删除
             */
            if (kynamic.kynamicTree.pNode.isParent) {//文件夹节点
                if (kynamic.kynamicTree.zTree.getNodeByParam("pid", kynamic.kynamicTree.pNode.kid)) {
                    alert("因为有子节点，所以不能删除");
                }
                else {
                    if (window.confirm("您确认要删除吗?")) {
                        var pararmeter = {
                            kid: kynamic.kynamicTree.pNode.kid
                        };
                        //判断该节点是否有兄弟节点
                        $.post("kynamicAction_showSiblingNodes.action", pararmeter, function(data){
                            if (data.kynamicList.length < 2) {//没有兄弟节点
                                //kynamic.kynamicTree.zTree.getNodeByParam("kid",kynamic.kynamicTree.pNode.pid);
                                $.post("kynamicAction_showParentNode.action", pararmeter, function(data2){
                                    var pNode = kynamic.kynamicTree.zTree.getNodeByParam("kid", data2.kynamic.kid);//获取父节点
                                    $.post("kynamicAction_deleteNode.action", pararmeter, function(data3){
                                        kynamic.kynamicTree.zTree.removeNode(kynamic.kynamicTree.pNode);
                                        //更新父节点的isParent的属性为true
                                        pNode.isParent = true;
                                        kynamic.kynamicTree.zTree.refresh();
                                        alert(data3.message);
                                    });
                                });
                            }
                            else {//有兄弟节点的情况
                                $.post("kynamicAction_deleteNode.action", pararmeter, function(data3){
                                    kynamic.kynamicTree.zTree.removeNode(kynamic.kynamicTree.pNode);
                                    //更新父节点的isParent的属性为true
                                    alert(data3.message);
                                });
                            }
                        });
                    }
                }
            }
            else {//文件节点
                if (window.confirm("您确认要删除吗?")) {
                    var pararmeter = {
                        kid: kynamic.kynamicTree.pNode.kid
                    };
                    //判断该节点是否有兄弟节点
                    $.post("kynamicAction_showSiblingNodes.action", pararmeter, function(data){
                        if (data.kynamicList.length < 2) {//没有兄弟节点
                            //kynamic.kynamicTree.zTree.getNodeByParam("kid",kynamic.kynamicTree.pNode.pid);
                            $.post("kynamicAction_showParentNode.action", pararmeter, function(data2){
                                var pNode = kynamic.kynamicTree.zTree.getNodeByParam("kid", data2.kynamic.kid);//获取父节点
                                $.post("kynamicAction_deleteNode.action", pararmeter, function(data3){
                                    kynamic.kynamicTree.zTree.removeNode(kynamic.kynamicTree.pNode);
                                    //更新父节点的isParent的属性为true
                                    pNode.isParent = true;
                                    kynamic.kynamicTree.zTree.refresh();
                                    alert(data3.message);
                                });
                            });
                        }
                        else {
                            $.post("kynamicAction_deleteNode.action", pararmeter, function(data3){
                                kynamic.kynamicTree.zTree.removeNode(kynamic.kynamicTree.pNode);
                                alert(data3.message);
                            });
                        }
                    });
                }
            }
        },
        /**
         * 修改节点
         */
        updateNode: function(){
            var newName = window.prompt("请重新输入名称", kynamic.kynamicTree.pNode.name);
            var pararmeter = {
                name: newName
            };
            var para = {
                kid: kynamic.kynamicTree.pNode.kid,
                name: newName
            };
            $.post("kynamicAction_isExsitName.action", pararmeter, function(data){
                if (data.message == "1") {//重名了
                    alert("该文件名称已经存在，请重新输入");
                }
                else {//进行修改
                    $.post("kynamicAction_updateKynamic.action", para, function(data2){
                        kynamic.kynamicTree.pNode.name = newName;
                        kynamic.kynamicTree.zTree.refresh();
                    });
                }
            });
        }
    },
    /**
     * 版本的维护
     */
    version: {
        /**
         * 如果当前点击的节点有版本，则显示版本
         */
        showVersionsByKID: function(versionList){
            /**
             * <tr>
             <td height="26" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;"><a>1</a></td>
             <td align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;">2010-5-24 09:56:33</td>
             <td align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;"><a>删除</a></td>
             </tr>
             */
			/**
			 * 把原来的内容清空
			 */
			$("#showVersion").empty();
            for (var i = 0; i < versionList.length; i++) {
//                (function(){
//                    var version = versionList[i].version;
//                    var updatetime = versionList[i].updatetime;
//                    var $versionA = $("<a/>");
//                    $versionA.text(version);
//                    $versionA.css("cursor", "pointer");
//                    
//                    $versionA.unbind("click");
//                    /**
//                     * click事件的函数是在单击的时候触发的，这段代码所在的函数showVersionsByKID，而这个函数在回调函数中
//                     *    versionList是传递过来的形参，所以回调函数的声明周期结束以后，该参数就不存在了,所以
//                     *       versinList[i] is undefined
//                     */
//                    $versionA.bind("click", function(){
//                        //alert(versionList[i].version);
//                        alert(version);
//                    });
//                    
//                    var $versionTD = $("<td/>");
//                    $versionTD.attr("height", "26");
//                    $versionTD.attr("align", "center");
//                    $versionTD.attr("valign", "middle");
//                    $versionTD.attr("bgcolor", "#FFFFFF");
//                    $versionTD.attr("style", "border-bottom:1px solid #f3f8fd;");
//                    $versionTD.append($versionA);
//                    
//                    var $updatetimeTD = $("<td/>");
//                    $updatetimeTD.attr("align", "center");
//                    $updatetimeTD.attr("valign", "middle");
//                    $updatetimeTD.attr("bgcolor", "#FFFFFF");
//                    $updatetimeTD.attr("style", "border-bottom:1px solid #f3f8fd;");
//                    $updatetimeTD.text(updatetime);
//                    
//                    var $A = $("<a/>");
//                    $A.text("删除");
//                    var $delTD = $("<td/>");
//                    $delTD.attr("align", "center");
//                    $delTD.attr("valign", "middle");
//                    $delTD.attr("bgcolor", "#FFFFFF");
//                    $delTD.attr("style", "border-bottom:1px solid #f3f8fd;");
//                    $delTD.append($A);
//                    
//                    var $TR = $("<tr/>");
//                    $TR.append($versionTD);
//                    $TR.append($updatetimeTD);
//                    $TR.append($delTD);
//                    $("#showVersion").append($TR);
//                })();
                    var version = versionList[i].version;
                    var updatetime = versionList[i].updatetime;
                    var $versionA = $("<a/>");
                    $versionA.text(version);
                    $versionA.css("cursor", "pointer");
                    $versionA.attr("version",versionList[i].version);
					$versionA.attr("updatetime",updatetime);
					
                    $versionA.unbind("click");
                    /**
                     * click事件的函数是在单击的时候触发的，这段代码所在的函数showVersionsByKID，而这个函数在回调函数中
                     *    versionList是传递过来的形参，所以回调函数的声明周期结束以后，该参数就不存在了,所以
                     *       versinList[i] is undefined
                     *       
                     * 如果在$.post或者$.ajax中，回调函数中，调用了一个函数，而该函数中又有事件，那么事件中不能直接使用回调函数的形参
                     *    因为事件执行的时候，回调函数已经执行完毕了，形参已经不存在了
                     */
                    $versionA.bind("click", function(){
                        //alert(versionList[i].version);
                        //alert(version);
						alert($(this).attr("version"));
                    });
                    
                    var $versionTD = $("<td/>");
                    $versionTD.attr("height", "26");
                    $versionTD.attr("align", "center");
                    $versionTD.attr("valign", "middle");
                    $versionTD.attr("bgcolor", "#FFFFFF");
                    $versionTD.attr("style", "border-bottom:1px solid #f3f8fd;");
                    $versionTD.append($versionA);
                    
                    var $updatetimeTD = $("<td/>");
                    $updatetimeTD.attr("align", "center");
                    $updatetimeTD.attr("valign", "middle");
                    $updatetimeTD.attr("bgcolor", "#FFFFFF");
                    $updatetimeTD.attr("style", "border-bottom:1px solid #f3f8fd;");
                    $updatetimeTD.text(updatetime);
                    
                    var $A = $("<a/>");
                    $A.text("删除");
                    var $delTD = $("<td/>");
                    $delTD.attr("align", "center");
                    $delTD.attr("valign", "middle");
                    $delTD.attr("bgcolor", "#FFFFFF");
                    $delTD.attr("style", "border-bottom:1px solid #f3f8fd;");
                    $delTD.append($A);
                    
                    var $TR = $("<tr/>");
                    $TR.append($versionTD);
                    $TR.append($updatetimeTD);
                    $TR.append($delTD);
                    $("#showVersion").append($TR);
                
            }
        },
        /**
         * 控制两个div和checkin checkout的显示
         */
        controlShowVersion: function(versionShowJson){
            if (versionShowJson.addVersion) {
                $("#addVersion").show();
            }
            else {
                $("#addVersion").hide();
            }
            
            if (versionShowJson.versionList) {
                $("#versionList").show();
            }
            else {
                $("#versionList").hide();
            }
            
            if (versionShowJson.checkin) {
                $("#checkin").show();
            }
            else {
                $("#checkin").hide();
            }
            
            if (versionShowJson.checkout) {
                $("#checkout").show();
            }
            else {
                $("#checkout").hide();
            }
        },
		/**
		 * check in操作
		 */
		checkin:function(){
			/**
			 * 通过checkin操作生成一个新的版本号
			 *     如果该节点没有版本号
			 *         那么新的版本号为1
			 *     如果该节点有版本号
			 *         版本号为原来的最高版本+1
			 */
		},
		/**
		 * 点击某一个版本号，显示具体的title和content的内容
		 */
		showContent:function(){
			/**
			 * 显示title和content
			 */
		},
		/**
		 * 删除某一个版本
		 */
		deleteVersion:function(){
			
		}
    }
};

$().ready(function(){
    kynamic.kynamicTree.loadKynamicTree();
    /**
     * hover在这里仅仅是用于声明事件，事件函数中的内容到底是否执行，根据触发的时候判断
     */
    $("#rMenu").hover(function(){
        /**
         * 声明增、删、改的事件
         */
        $("#addFile").unbind("click");
        $("#addFile").bind("click", function(){
            kynamic.kynamicTree.addFile();
        });
        
        $("#addFolder").unbind("click");
        $("#addFolder").bind("click", function(){
            kynamic.kynamicTree.addFolder();
        });
        
        $("#deleteNode").unbind("click");
        $("#deleteNode").bind("click", function(){
            kynamic.kynamicTree.deleteNode();
        });
        
        $("#updateNode").unbind("click");
        $("#updateNode").bind("click", function(){
            kynamic.kynamicTree.updateNode();
        });
    }, function(){
        //当该方法被触发的时候，树早已经存在了，右键菜单已经显示了
        $("#rMenu").hide();
    });
});
