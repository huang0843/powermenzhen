<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>医生就诊</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>
<div id="app">
    <template>
        <el-table
                :data="tableData"
                border
                style="width: 70%">
            <el-table-column
                    fixed
                    prop="registerNumber"
                    label="挂号编号"
                    width="90">
            </el-table-column>
            <el-table-column
                    prop="registerName"
                    label="姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="registerDoctor"
                    label="接诊医生"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="registerReason"
                    label="就诊原因"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="seekProposal"
                    label="医生建议"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="seekDrug"
                    label="处方药"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="seekDrugcount"
                    label="建议服用天数"
                    width="90">
            </el-table-column>
            <el-table-column
                    prop="seekDate"
                    label="接诊日期"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    <el-button type="text" size="small" @click="edituser(scope.row)">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="380px">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="患者姓名">
                <el-input v-model="form.registerName"></el-input>
            </el-form-item>
            <el-form-item label="处方药">
                <%--<el-select--%>
                        <%--multiple--%>
                        <%--v-model="depatment"--%>
                        <%--placeholder="请选择药品">--%>
                    <%--<el-option--%>
                            <%--v-for="item in depss"--%>
                            <%--:key="item.drugName"--%>
                            <%--:label="item.drugName"--%>
                            <%--:value="item.drugName">--%>
                    <%--</el-option>--%>
                <%--</el-select>--%>
                <el-select
                        v-model="value"
                        multiple
                        filterable
                        remote
                        reserve-keyword
                        placeholder="请输入关键词"
                        :remote-method="remoteMethod"
                        :loading="loading">
                    <el-option
                            v-for="item in options"
                            :key="item.drugName"
                            :label="item.drugName"
                            :value="item.drugName">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="建议天数">
                <el-input v-model="form.seekDrugcount"></el-input>
            </el-form-item>
            <%--<el-form-item label="活动区域">--%>
                <%--<el-select v-model="form.region" placeholder="请选择活动区域">--%>
                    <%--<el-option label="区域一" value="shanghai"></el-option>--%>
                    <%--<el-option label="区域二" value="beijing"></el-option>--%>
                <%--</el-select>--%>
            <%--</el-form-item>--%>

            <el-form-item label="医生建议">
                <el-input type="textarea" v-model="form.proposal"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
                <el-button @click="dialogFormVisible = false">取消</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
</div>
</body>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<!-- 使用 cdn 引用方式需要注意是否锁定版本，默认指向最新版本 -->
<script src="<%=basePath%>static/element/js/axios.js"></script>
<script src="<%=basePath%>static/element/js/qs.js"></script>
<script>
    new Vue({
        el:"#app",
        data(){
            return {
                // depatment:'',
                options: [],
                value: [],
                list: [],
                loading: false,
                dialogFormVisible: false,
                form: {
                    registerName: '',
                    region: '',
                    desc: ''
                },
                tableData: [],
                tableData2: [],
            }
        },
        created(){
            this.getdata();
        },
        mounted() {
            this.getdata2();
        },
        methods:{
            remoteMethod(query) {
                if (query !== '') {
                    this.loading = true;
                    setTimeout(() => {
                        this.loading = false;
                        this.options = this.list.filter(item => {
                            return item.drugName.toLowerCase()
                                .indexOf(query.toLowerCase()) > -1;
                        });
                    }, 200);
                } else {
                    this.options = [];
                }
            },
            getdata2(){
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/drugName').
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.list=response.data.obj;
                        // console.log(self.list);
                    }else {
                        self.$message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$message({ message: '网路异常', status: 'error' })
                })
            },
            getdata(){
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/SeekList').
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.tableData=response.data.obj;
                        console.log(self.tableData)
                    }else {
                        self.$message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$message({ message: '网路异常', status: 'error' })
                })
            },
            edituser(row){
                // this.imageUrl=row.managerImg;
                // this.manager=row;
                console.log(row);
                console.log(row.register[0].registerName);
                this.form=row.register[0];
                this.dialogFormVisible=true;
            },
            onSubmit() {
                var row=this.form;
                console.log(this.form);
                console.log(this.value);
                var drug=this.value.join(',');
                // console.log("类型是："+typeof(this.depatment)+"，字符串是："+drug);
                var seek={
                    'seekDocter':row.registerDoctor,
                    'seekNumber':row.registerNumber,
                    'seekName':row.registerName,
                    'seekProposal':row.proposal,
                    'seekDrug':drug,
                    'seekDrugcount':row.seekDrugcount,
                };
                console.log(seek);
                //ajaxs
                axios.post('<%=basePath%>admin/menzhen/seekSave', Qs.stringify(seek))
                    .then(function (response) {
                        if(response.data.code=='10000'){
                            self.$message({ message: '保存成功', status: 'success' });
                            this.dialogFormVisible=false;
                            //保存后刷新数据表-----
                            self.getdata();
                        }else {
                            self.$message({ message: '保存失败', status: 'error' });
                        }
                    }).catch(()=> {
                    self.$message({ message: '网路异常', status: 'error' });
                })
            },
            handleClick(row) {
                console.log(row);
            },
        }
    })
</script>
</html>
