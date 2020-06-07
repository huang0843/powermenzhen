<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>药房管理</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>
<div id="app">

    <template>
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="药品库存" name="first"><template>
                <el-table
                        :data="tableData"
                        border
                        style="width: 70%">
                    <el-table-column
                            fixed
                            prop="drugId"
                            label="药品id"
                            width="90">
                    </el-table-column>
                    <el-table-column
                            prop="drugSort"
                            label="药品分区"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="drugName"
                            label="药品名称"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="drugMoney"
                            label="药品单价"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="drugFunction"
                            label="药品功效"
                            width="200">
                    </el-table-column>
                    <el-table-column
                            prop="drugCount"
                            label="药品库存"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="drugCreattime"
                            label="药品生产日期"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="drugLasttime"
                            label="药品有限期"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick1(scope.row)" type="text" size="small">查看</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </template>
            </el-tab-pane>

            <el-tab-pane label="开药情况" name="second">
                <el-row :gutter="20">
                    <el-col :span="6" >
                        <el-input
                                @keyup.enter.native="searchx"
                                v-model="param"
                                placeholder="输入患者姓名搜索">
                            <el-button slot="append" icon="el-icon-search" @click="searchx"></el-button>
                        </el-input>
                    </el-col>
                </el-row>
                <el-table
                        :data="tableData2"
                        border
                        style="width:700px">
                    <el-table-column
                            fixed
                            prop="seekNumber"
                            label="挂号编号"
                            width="90">
                    </el-table-column>
                    <el-table-column
                            prop="seekName"
                            label="患者姓名"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="seekDrug"
                            label="医生开药"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="seekDrugcount"
                            label="药品数量"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="edituser(scope.row)" type="text" size="small">开药</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>

            <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="380px">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="患者姓名">
                        <el-input v-model="form.seekName"></el-input>
                    </el-form-item>
                    <el-form-item label="处方药">
                        <el-select
                        v-model="depatment1"
                        placeholder="请选择药品">
                        <el-option
                        v-for="item in depss"
                        :key="item.drugName"
                        :label="item.drugName"
                        :value="item.drugName">
                        </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="建议天数">
                        <el-input v-model="form.day1"></el-input>
                    </el-form-item>
                    <el-form-item label="处方药">
                        <el-select
                                v-model="depatment2"
                                placeholder="请选择药品">
                            <el-option
                                    v-for="item in depss"
                                    :key="item.drugName"
                                    :label="item.drugName"
                                    :value="item.drugName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="建议天数">
                        <el-input v-model="form.day2"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">立即保存</el-button>
                        <el-button @click="dialogFormVisible = false">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>

            <el-tab-pane label="开药记录" name="third">
                <el-row :gutter="20">
                    <el-col :span="6" >
                        <el-input
                                @keyup.enter.native="searchx"
                                v-model="param2"
                                placeholder="输入患者姓名搜索">
                            <el-button slot="append" icon="el-icon-search" @click="searchx2"></el-button>
                        </el-input>
                    </el-col>
                </el-row>
                <el-table
                        :data="tableData3"
                        border
                        style="width: 80%">
                    <el-table-column
                            fixed
                            prop="pdPatient"
                            label="患者姓名"
                            width="90">
                    </el-table-column>
                    <el-table-column
                            prop="pdName1"
                            label="开药1"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="pdDay1"
                            label="服用天数"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="pdName2"
                            label="开药2"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="pdDay2"
                            label="服用天数"
                            width="200">
                    </el-table-column>
                    <el-table-column
                            prop="pdMoneyall"
                            label="总费用"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick1(scope.row)" type="text" size="small">查看</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>


            <%--<el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>--%>
        </el-tabs>
    </template>



</div>
</body>
<script src="<%=basePath%>static/element/js/vue.js"></script>
<script src="<%=basePath%>static/element/js/index.js"></script>
<script src="<%=basePath%>static/element/js/axios.js"></script>
<script src="<%=basePath%>static/element/js/qs.js"></script>
<script>
    new Vue({
        el:"#app",
        data(){
            return {
                depss:[],
                depatment1:'',
                depatment2:'',
                form: {
                    seekName: '',
                },
                list: [],
                tableData: [],
                tableData2: [],
                tableData3: [],
                param:'',
                param2:'',
                activeName: 'first',
                dialogFormVisible: false
            }
        },
        created(){
            this.getdata();
        },
        mounted() {
            this.getdata2();
        },
        methods:{
            onSubmit() {
                var drug1=this.depatment1;
                var drug2=this.depatment2;
                // console.log(drug1);
                // console.log(drug2);
                // console.log(this.form);
                var paydrug={
                    'pdNumber':this.form.seekNumber,
                    'pdName1':drug1,
                    'pdDay1':this.form.day1,
                    'pdName2':drug2,
                    'pdDay2':this.form.day2,
                    'pdPatient':this.form.seekName,
                };
                console.log(paydrug);
                //ajaxs
                var self=this;
                axios.post('<%=basePath%>admin/menzhen/seekPayDrug', Qs.stringify(paydrug))
                    .then(function (response) {
                        if(response.data.code=='10000'){
                            self.$message({
                                type: 'success',
                                message: '保存成功!'
                            });
                            this.dialogFormVisible=false;
                            //保存后刷新数据表-----
                        }else {
                            self.$message({
                                type: 'error',
                                message: '保存失败!'
                            });
                        }
                    }).catch(()=> {
                    self.$message({
                        type: 'error',
                        message: '网络异常!'
                    });
                })
                },
            searchx:function(str){
                console.log(str);
                this.getDrug()
            },
            searchx2:function(str){
                console.log(str);
                // console.log(this.param);
                this.getDrug2()
            },
            getDrug2(){
                var data={
                    'name':this.param2
                }
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/selectPayDrug',{params:data}).
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.tableData3=response.data.obj;
                        console.log(self.tableData)
                    }else {
                        self.$message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$message({ message: '网路异常', status: 'error' })
                })
            },
            getDrug(){
                var data={
                    'name':this.param
                }
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/selectDrug',{params:data}).
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.tableData2=response.data.obj;
                        console.log(self.tableData)
                    }else {
                        self.$message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$message({ message: '网路异常', status: 'error' })
                })
            },
            getdata2(){
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/drugName').
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.depss=response.data.obj;
                    }else {
                        self.$message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$message({ message: '网路异常', status: 'error' })
                })
            },
            getdata(){
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/druglist').
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
                console.log(row);
                this.form=row;
                this.dialogFormVisible=true;
            },
            handleClick1(row) {
                console.log(row);
            },
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    })
</script>
</html>
