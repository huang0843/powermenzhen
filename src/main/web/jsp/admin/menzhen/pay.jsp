<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>缴纳费用</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>
<div id="app">

    <template>
        <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
            <el-tab-pane label="缴纳费用" name="first">
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
                <template>
                    <el-table
                            :data="tableData"
                            border
                            style="width: 500px">
                        <el-table-column
                                prop="registerName"
                                label="姓名"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="registerType"
                                label="挂号类别"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="registerMoney"
                                label="挂号费用"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="pdMoneyall"
                                label="药品费用"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                fixed="right"
                                label="操作"
                                width="100">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" @click="edituser(scope.row)">缴纳</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </template>
            </el-tab-pane>



            <el-tab-pane label="缴纳记录" name="second">
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
                <template>
                    <el-table
                            :data="tableData2"
                            border
                            style="width: 580px">
                        <el-table-column
                                prop="payName"
                                label="姓名"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="payRegister"
                                label="挂号费用"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="payDrug"
                                label="取药费用"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="payAll"
                                label="总共费用"
                                width="80">
                        </el-table-column>
                            <el-table-column
                                    prop="payDate"
                                    label="支付时间"
                                    width="180">
                            </el-table-column>
                        <%--<el-table-column--%>
                                <%--fixed="right"--%>
                                <%--label="操作"--%>
                                <%--width="100">--%>
                            <%--<template slot-scope="scope">--%>
                                <%--<el-button type="text" size="small" @click="edituser(scope.row)">缴纳</el-button>--%>
                            <%--</template>--%>
                        <%--</el-table-column>--%>
                    </el-table>
                </template>
            </el-tab-pane>
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
                param:'',
                param2:'',
                tableData: [],
                tableData2: [],
                activeName: 'first'
            }
        },
        created(){
            this.getdata();
        },
        mounted() {
            // this.getdata2();
        },
        methods:{
            searchx:function(str){
                console.log(str);
                console.log(this.param);
                this.getdata()
            },
            searchx2:function(str){
                console.log(str);
                console.log(this.param2);
                this.getdata2()
            },
            getdata2(){
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/selectAll').
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.tableData2=response.data.obj;
                    }else {
                        self.$message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$message({ message: '网路异常', status: 'error' })
                })
            },
            getdata(){
                var data={
                    'name':this.param
                }
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/payName',{params:data}).
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.tableData=response.data.obj;
                    }else {
                        self.$message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$message({ message: '网路异常', status: 'error' })
                })
            },
            edituser(row){
                console.log(row);
                var pay={
                    'payName':row.registerName,
                    'payRegister':row.registerMoney,
                    'payDrug':row.pdMoneyall,
                };
                var self=this;
                axios.post('<%=basePath%>admin/menzhen/paySave', Qs.stringify(pay))
                    .then(function (response) {
                        if(response.data.code=='10000'){
                            self.$message({
                                type: 'success',
                                message: '缴纳成功!'
                            });
                            this.dialogFormVisible=false;
                            //保存后刷新数据表-----
                        }else {
                            self.$message({
                                type: 'error',
                                message: '缴纳失败!'
                            });
                        }
                    }).catch(()=> {
                    self.$message({
                        type: 'error',
                        message: '网络异常!'
                    });
                })
            },
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    })
</script>
</html>
