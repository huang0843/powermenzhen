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
                            <el-button @click="handleClick1(scope.row)" type="text" size="small">开药</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="角色管理" name="third">角色管理</el-tab-pane>
            <el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>
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
                list: [],
                tableData: [],
                tableData2: [],
                param:'',
                activeName: 'first'
            }
        },
        created(){
            this.getdata();
        },
        mounted() {
            this.getdata2();
        },
        methods:{
            searchx:function(str){
                console.log(str);
                console.log(this.param);
                this.getDrug()
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
                // this.imageUrl=row.managerImg;
                // this.manager=row;
                console.log(row);
                console.log(row.register[0].registerName);
                this.form=row.register[0];
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
