<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>角色列表</title>
    <link rel="stylesheet" href="<%=basePath%>static/element/css/index.css">
    <style>
        body{
            margin: 0px; padding: 0px;
         }
        .avatar-uploader .el-upload {
            border: 1px dashed #d9d9d9;
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
        }
        .avatar-uploader .el-upload:hover {
            border-color: #409EFF;
        }
        .avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 100px;
            height: 100px;
            line-height: 100px;
            text-align: center;
        }
        .avatar {
            width: 100px;
            height: 100px;
            display: block;
        }

    </style>
</head>
<body>
<div id="app">
    <%--头部按钮栏--%>
    <el-row :gutter="20">
        <el-col :span="8" style="margin-bottom: 10px">
            <el-button-group>
                <el-button type="primary" icon="el-icon-plus" @click="opendsave"></el-button>
                <el-button type="primary" icon="el-icon-edit"></el-button>
            </el-button-group>
        </el-col>
    </el-row>

        <%--dialog嵌套表单--%>
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="380px">
                <el-form :model="role" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="角色名称" prop="roleName">
                        <el-input v-model="role.roleName"></el-input>
                    </el-form-item>
                </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
        <%--dialog嵌套表单  树--%>
        <el-dialog title="授权信息" :visible.sync="dialogPowerVisible" width="380px">
            <el-tree :data="data"  ref="tree"   :props="defaultProps"
                     @check="changepower"
                     :check-on-click-node="false"
                     show-checkbox
                     :highlight-current="true"
                     :default-expand-all="true"
                     node-key="menuId"
                     :default-checked-keys="ckarray"
            >
            </el-tree>
        </el-dialog>


        <%--显示数据栏--%>
    <template>
        <el-table
                ref="multipleTable"
                :data="list"
                border
                style="width: 100%"
                 >
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="roleId"
                    label="ID"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="roleName"
                    label="角色名称"
                    width="100">
            </el-table-column>

            <el-table-column
                    prop="roleCreatetime"
                    label="创建时间"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="roleLastmodify"
                    label="最后修改时间"
                    width="250">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    >
                <template slot-scope="scope">
                    <el-button @click="power(scope.row.roleId)" type="text">授权</el-button>
                    <el-button @click="edituser(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteuser(scope.row.roleId)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>
</div>

<script src="<%=basePath%>static/element/js/vue.js"></script>
<script src="<%=basePath%>static/element/js/index.js"></script>
<script src="<%=basePath%>static/element/js/axios.js"></script>
<script src="<%=basePath%>static/element/js/qs.js"></script>
<script>
    new Vue({
        el:"#app",
        data:function () {
            return{
                //默认选中的数组
                ckarray:[],
                defaultProps: {
                    children: 'list',
                    label: 'menuTitle'
                },
                data:[],
               list:[],
                dialogFormVisible: false,
                dialogPowerVisible:false,
                role:{},
                rules: {
                    roleName: [
                        {required: true, message: '请输入角色姓名', trigger: 'blur'},
                        {min: 2, max: 50, message: '长度在 2 到 50个字符', trigger: 'blur'}
                    ]
                },
                currentid:0,
           }
        },
        created:function () {
           this.getdata();
        },

        methods:{
            changepower(){
                this.savepower();
            },
            savepower(){
                //获取当前树上选中的节点
                var node=this.$refs.tree.getCheckedNodes(false,false);
                console.log(node[0]);
                var ids=[];
                for (let i = 0; i <node.length ; i++) {
                    ids.push(node[i].menuId);
                }
                var data={
                    roleid:this.currentid ,
                    menuids:ids.join(",")
                }
                var  self=this;
                axios.get('<%=basePath%>admin/role/power',{params: data}).
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.$message({
                            type: 'success',
                            message: '保存成功!'
                        });
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
            handleNodeClick() {
                this.savepower();
            },
            power(id){
                this.currentid=id;
                this.inittree();
               var self=this;
                axios.get('<%=basePath%>admin/role/powerlist',{params:{roleid:id}}).
                then(function (response) {
                    self.ckarray=[];
                    if(response.data.code=='10000'){
                        var list=response.data.obj;//得到的结果数组
                        for (let i = 0; i <list.length; i++)
                        {
                            self.ckarray.push(list[i].rmrfMenuid);//人物选中的ID
                            console.log(list[i].rmrfMenuid)
                        }
                        self.dialogPowerVisible=true;
                    }else {
                        self.$message({
                            type: 'error',
                            message: '权限加载失败!'
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
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.save()
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();//清空数据
            },

            //编辑 之前赋值
            edituser(row){
                this.role=row;
                console.log(row)
                this.dialogFormVisible=true;
            },
            //进入保存
            opendsave(){
                this.role={};
                this.dialogFormVisible=true;
                this.managerForm='manager';
            },
            //保存
            save(){
                var self=this;
                axios.post('<%=basePath%>admin/role/save', Qs.stringify(self.role))
                    .then(function (response) {
                    if(response.data.code=='10000'){
                        self.$message({
                            type: 'success',
                            message: '保存成功!'
                        });
                        //保存后刷新数据表-----
                        self.getdata();
                        //隐藏表单
                        self.dialogFormVisible = false;
                    }else {
                        self.$message({
                            type: 'error',
                            message: '保存失败!'
                        });
                    }
                }).catch(()=> {
                    self.$message({
                        type: 'error',
                        message: '网路异常!'
                    });
                })
            },

            deleteuser(id){
                var self=this;
                this.$confirm('此操作将永久删除本行数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var data={ id:id}
                    axios.get('<%=basePath%>admin/role/delete',{params:data}).
                    then(function (response) {
                        if(response.data.code=='10000'){
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            self.getdata();
                        }else {
                            self.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                    }).catch(function (error) {
                        self.$message({
                            type: 'error',
                            message: '网路异常!'
                        });
                    })
                }).catch(() => {

                });

            },

           getdata:function () {
               var self=this;
               axios.get("<%=basePath%>admin/role/list")
               .then(function (response) {
                   if(response.data.code=='10000'){
                       self.list=response.data.obj;
                   }
               }).catch(function (error) {
                       console.log(error);
                   });

           }
        }
    })
</script>
</body>
</html>
