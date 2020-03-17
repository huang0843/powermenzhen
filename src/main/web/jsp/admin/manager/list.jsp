<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>管理者列表</title>
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
                <el-button type="primary" icon="el-icon-delete" @click="deletall"></el-button>
            </el-button-group>
        </el-col>
        <el-col :span="8">
            <el-upload
                    class="upload-demo"
                    action="<%=basePath%>admin/manager/import"
                    :on-success="importsuccess"
                    :on-error="importfail"
                    :show-file-list="false"
            >
                <a href="<%=basePath%>static/template/manager.xlsx">模板下载</a>
                <el-button size="small" type="primary">批量添加</el-button>
            </el-upload>
        </el-col>
        <el-col :span="6" >
            <el-input
                    @keyup.enter.native="searchx"
                    v-model="param"
                    placeholder="输入关键字搜索">
                <el-button slot="append" icon="el-icon-search" @click="searchx"></el-button>
            </el-input>
        </el-col>

    </el-row>

        <%--dialog嵌套表单--%>
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="380px">
            <el-form :model="manager" :rules="rules" ref="managerForm">
                <div style="margin: 0 auto;text-align: center;margin-bottom: 8px">
                    <el-upload
                            class="avatar-uploader"
                            action="<%=basePath%>admin/manager/upload"
                            :multiple=false
                            name="file"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </div>

                <el-form-item label="姓名" :label-width="formLabelWidth" prop="managerName">
                    <el-input v-model="manager.managerName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth" prop="managerSex">
                    <el-select v-model="manager.managerSex" placeholder="请选择性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话" :label-width="formLabelWidth"  prop="managerPhone">
                    <el-input v-model="manager.managerPhone" autocomplete="off" ></el-input>
                </el-form-item>
                <el-form-item label="身份证" :label-width="formLabelWidth" prop="managerIdcard">
                    <el-input v-model="manager.managerIdcard" autocomplete="off" ></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('managerForm')">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="授权信息" :visible.sync="dialogRoleVisible" width="380px">
            <el-checkbox-group v-model="checkedRoles" @change="handleCheckedRolesChange">
                <el-checkbox v-for="role in roles" :label="role.roleId" :key="role.roleId">{{role.roleName}}</el-checkbox>
            </el-checkbox-group>
        </el-dialog>
        <%--显示数据栏--%>
    <template>
        <el-table
                ref="multipleTable"
                :data="list"
                border
                style="width: 100%"
                :default-sort = "{prop: 'date', order: 'descending'}"
                @sort-change="sort"
                 >
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="managerId"
                    label="ID"
                    sortable
                    width="50">
            </el-table-column>
            <el-table-column
                    label="头像"
                    width="120">
                <template slot-scope="scope">
                    <img style="height: 90px;width: 90px" :src="'<%=basePath%>'+scope.row.managerImg"
                    onerror="javascript:this.src='<%=basePath%>static/imgs/defult.jpg'"
                    >
                </template>
            </el-table-column>
            <el-table-column
                    prop="managerName"
                    label="姓名"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="managerPhone"
                    label="手机号码"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="managerSex"
                    label="性别"
                    sortable
                    width="90">
            </el-table-column>
            <el-table-column
                    prop="managerIdcard"
                    label="身份证号码"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="managerCreatetime"
                    label="创建时间"
                    sortable
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="managerLastmodify"
                    label="最后修改时间"
                    width="200">
            </el-table-column>

            <el-table-column
                    prop="rolestr"
                    label="角色"
                    width="250">
            </el-table-column>

            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="power_role(scope.row)" type="text" size="small">授权</el-button>
                    <el-button @click="edituser(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteuser(scope.row.managerId)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>
    <%--分页--%>
    <el-pagination
            background
            layout="prev, pager, next,sizes,jumper"
            :total="page.total"
            :page-size="page.size"
            @current-change="pagechange"
            @prev-click="prevpage"
            @next-click="nextpage"
            @size-change="sizechange"
           >
    </el-pagination>
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
                roles:[],
                checkedRoles:[],
                param:'',
                order:'asc',
                pro:'managerId',
             list:[],
                page:{
                    total:50,
                    index:1,
                    size:20
                },
                dialogFormVisible: false,
                dialogRoleVisible: false,
                imageUrl: '',
                manager:{
                    managerName:'黄辉',managerSex:'男',managerPhone:'18270630844',
                    managerIdcard:'362201199708294838'
                },
                formLabelWidth:'80px',
                rules: {
                    managerName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 2, max: 50, message: '长度在 2 到 50个字符', trigger: 'blur'}
                    ],
                    managerSex: [
                        {required: true, message: '请选择性别', trigger: 'change'}
                    ],
                    managerPhone: [
                        {required: true, message: '请输入手机号码', trigger: 'blur'},
                        { pattern: /^1[3456789]\d{9}$/ ,message: '请输人正确的手机号码' }
                    ],
                    managerIdcard: [
                        {required: true, message: '请输入身份证', trigger: 'blur'},
                        { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/ ,message: '请输人合法的身份证' }
                    ]
                },
                currentManagerid:0
           }
        },
        created:function () {
           this.getdata(this.page.index,this.page.size);
           this.getRoles();
        },

        methods:{
            power_role(row){
                var roles=row.roles;
                this.checkedRoles=[];
                for (let i = 0; i < roles.length; i++) {
                    this.checkedRoles.push(roles[i].roleId);
                }
                console.log(row.managerId+"-----");
                this.currentManagerid=row.managerId;
                this.dialogRoleVisible=true;
            },
            handleCheckedRolesChange:function(){
                var ids=[];
                for (let i = 0; i <this.checkedRoles.length ; i++) {
                    ids.push(this.checkedRoles[i]);
                }

                var self=this;
                var data={'roleids':ids.join(','),'managerid':this.currentManagerid}
                axios.post('<%=basePath%>admin/manager/savepower', Qs.stringify(data))
                    .then(function (response) {
                        if(response.data.code=='10000'){
                            self.getdata();
                            self.$message({
                                type: 'success',
                                message: '已保存!'
                            });
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
            getRoles:function () {
                var self=this;
                axios.get("<%=basePath%>admin/role/list")
                    .then(function (response) {
                        if(response.data.code=='10000'){
                            self.roles=response.data.obj;
                        }
                    }).catch(function (error) {
                    console.log(error);
                });

            },
            //删除ids
            deletall(){
               var rows= this.$refs.multipleTable.selection;
               var ids=[];
                for (let i = 0; i < rows.length; i++) {
                    ids.push(rows[i].managerId)
                }
                console.log("Ids:"+ids);
                if(ids.length<=0){
                    this.$message({
                        type: 'error',
                        message: '删除不能为空!'
                    });
                    return;
                };
                var data={'ids':ids.join(',')};
                var self=this;
                axios.post('<%=basePath%>admin/manager/deleteall', Qs.stringify(data))
                    .then(function (response) {
                        if(response.data.code=='10000'){
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //删除后刷新数据表-----
                            self.getdata();
                        }else {
                            self.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                    }).catch(()=> {
                    self.$message({
                        type: 'error',
                        message: '网路异常!'
                    });
                })
            },
            searchx:function(str){
                    console.log(str);
                    console.log(this.param);
                    this.getdata()
            },
            sort:function(e){
                console.log(e.column+"--"+e.prop+"--"+e.order)
                this.pro=e.prop;
                if(e.order=='ascending'){
                    this.order='asc';
                }
                if(e.order=='descending'){
                    this.order='desc';
                }
                this.getdata();
            },
            importsuccess:function(response, file, fileList){
                 if(response.code=='10000'){
                     this.$message({
                         type: 'success',
                         message: '上传成功!'
                     });
                     this.getdata();
                 }else {
                     this.$message({
                         type: 'error',
                         message: '上传失败!'
                     });
                 }
            },
            importfail:function(err, file, fileList){
                this.$message({
                    type: 'error',
                    message: '网络异常!'
                });
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
                this.imageUrl=row.managerImg;
                this.manager=row;
                console.log(row)
                this.dialogFormVisible=true;
            },
            //进入保存
            opendsave(){
                this.manager={};
                this.dialogFormVisible=true;
                this.managerForm='manager';
            },
            //保存
            save(){
                var self=this;
                axios.post('<%=basePath%>admin/manager/save', Qs.stringify(self.manager))
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
            //图片传递
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
                if(res.code=='10000'){
                    this.manager.managerImg=res.obj;
                }
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            deleteuser(id){
                var self=this;
                this.$confirm('此操作将永久删除本行数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var data={ id:id}
                    axios.get('<%=basePath%>admin/manager/delete',{params:data}).
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

            sizechange(size){
              this.page.size=size;
              this.getdata();
            },
            nextpage(){
                this.page.index++;
                this.getdata()
            },
            prevpage(){
                this.page.index--;
                this.getdata()
            },
            pagechange(index){
                this.page.index=index;
                this.getdata()
            },
           getdata:function () {
               var data={
                   'index':this.page.index,
                   'size':this.page.size,
                   'order':this.order,
                   'prop':this.pro,
                   'param':this.param
               }
               var self=this;
               axios.get("<%=basePath%>admin/manager/list",{params:data})
               .then(function (response) {
                   if(response.data.code=='10000'){
                       self.list=response.data.obj.list;
                       self.page.total=response.data.obj.total
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
