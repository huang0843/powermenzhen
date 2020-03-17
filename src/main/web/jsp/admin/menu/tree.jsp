<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>系统菜单</title>
    <link rel="stylesheet" href="<%=basePath%>static/element/css/index.css">
</head>
<body>
<div id="app">
    <el-row :gutter="20">
    <el-col :span="6"><div class="grid-content bg-purple"></div>
    <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"
    :highlight-current="true" :default-expand-all="true">
    <span class="custom-tree-node" slot-scope="{ node, data }">
    <span>
    <i :class="data.menuIco"></i>{{ node.label }}
    </span>
    </span>
    </el-tree>
    </el-col>
    <el-col :span="10"><div class="grid-content bg-purple"></div>
    <el-form :model="menu" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="菜单名称" prop="menuTitle">
        <el-input v-model="menu.menuTitle"></el-input>
        </el-form-item>
    <el-form-item label="菜单链接" prop="menuLink">
    <el-input v-model="menu.menuLink"></el-input>
    </el-form-item>
    <el-form-item label="菜单图标" prop="menuIco">
    <el-input v-model="menu.menuIco"></el-input>
    </el-form-item>
    <el-form-item label="父级菜单" prop="pmenutitle">
    <el-input v-model="menu.pmenutitle" :disabled="true"></el-input>
    <input type="text" v-model="menu.menuPid" style="display: none">
    </el-form-item>
    <el-form-item label="菜单描述" prop="menuAlt">
    <el-input v-model="menu.menuAlt"></el-input>
    </el-form-item>
    <el-form-item label="菜单排序" prop="menu_order">
    <el-input v-model="menu.menu_order"></el-input>
    </el-form-item>
    <el-form-item label="是否顶级菜单" prop="istop">
    <el-switch v-model="menu.istop" @change="istop"></el-switch>
    </el-form-item>
    <el-form-item label="添加/修改" prop="isnew">
    <el-select v-model="isnew" placeholder="请选择" @change="changesave">
        <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
    </el-option>

    </el-select>
    </el-form-item>

        <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即保存</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
        </el-form>
    </el-col>
    </el-row>
</div>

    <script src="<%=basePath%>static/element/js/vue.js"></script>
    <script src="<%=basePath%>static/element/js/index.js"></script>
    <script src="<%=basePath%>static/element/js/axios.js"></script>
    <script src="<%=basePath%>static/element/js/qs.js"></script>
<script>
new Vue({
    el:"#app",
    data:function(){
    return {
        data: [],
        defaultProps: {
        children: 'list',
        label: 'menuTitle'
         },
    menu:{},
    rules:{},
    tempmenu:{},
        options: [
              {value: 'update', label: '修改'},
                {value: 'insert', label: '新增'}
        ],
        isnew: ''
    }
    },
    created:function () {
        this.inittree();
    },
    methods:{
         istop(value){
            if(value){
               this.tempmenu=this.menu;
               this.menu={};
                this.menu.menuPid=0;
                this.menu.pmenutitle='顶级目录';
                this.isnew='insert'
            }else {
            this.menu=this.tempmenu;
            this.isnew='update';
          }
                this.menu.istop=value;
        },
    changesave(){
        if(this.isnew=='insert'){
            var pid=this.menu.menuId;
            var  ptitle=this.menu.menuTitle;
            this.tempmenu=this.menu;
            this.menu={};
            this.menu.menuPid=pid;
            this.menu.pmenutitle=ptitle;//父级菜单名称
            this.isnew='insert'
         }else {
        this.menu=this.tempmenu;
        this.isnew='update'
      }
   },
        submitForm(formName) {
                  this.save();
                },
        save(){
            var  self=this;
            console.log(self.menu.list);
            if(self.menu.list!=null){
                delete self.menu.list;
                }
        axios.get('<%=basePath%>admin/menu/save',{params:self.menu}).
             then(function (response) {
            if(response.data.code=='10000'){
                    self.$message({
                    type: 'success',
                    message: '保存成功!'
                    });
                   self.inittree();
            }else {
                self.$message({
                type: 'error',
                message: '保存失败!'
                });
            }
            }).catch(function (error) {
                self.$message({
                type: 'error',
                message: '网路异常!'
                });
            })
    },
        inittree(){
               var  self=this;
                axios.get('<%=basePath%>admin/menu/list').
                then(function (response) {
                if(response.data.code=='10000'){
                    self.data=response.data.obj;
                }else {
                    self.$message({
                    type: 'error',
                    message: '菜单加载失败!'
                    });
                }
                }).catch(function (error) {
                    self.$message({
                    type: 'error',
                    message: '网路异常!'
                    });
                })
                },
        handleNodeClick(data,node) {
            this.isnew='update';
            this.menu=data;
            console.log(node);
            if(this.menu.menuPid=='0'){
                this.menu.pmenutitle="顶级目录";
               }else {
                this.menu.pmenutitle=node.parent.label;
            }
        }
    }
 })
</script>
</body>
</html>
