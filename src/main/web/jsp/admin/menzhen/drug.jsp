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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/vxe-table/lib/index.css">
</head>
<body>
<div id="app">
    <vxe-toolbar>
        <template v-slot:buttons>
            <vxe-button icon="fa fa-plus" @click="insertEvent()">新增</vxe-button>
            <%--<vxe-button @click="removeEvent()">删除</vxe-button>--%>
            <vxe-button icon="fa fa-save" @click="getInsertEvent">保存</vxe-button>
        </template>
    </vxe-toolbar>
    <vxe-table
            border
            highlight-hover-row
            highlight-current-row
            show-overflow
            keep-source
            ref="xTable"
            max-height="400"
            :data="tableData"
            :edit-config="{trigger: 'dblclick', mode: 'cell', icon: 'fa fa-pencil',showStatus: true}">
        <vxe-table-column type="radio" width="60"></vxe-table-column>
        <vxe-table-column field="drugId" title="药品id" sortable :edit-render="{name: 'input'}" :visible="false"></vxe-table-column>
        <vxe-table-column field="drugSort" title="药品分区" sortable :edit-render="{name: 'input'}"></vxe-table-column>
        <vxe-table-column field="drugName" title="药品名称" sortable :edit-render="{name: 'input', defaultValue: '请输入病人姓名'}"></vxe-table-column>
        <vxe-table-column field="drugMoney" title="药品单价" sortable :edit-render="{name: 'input'}"></vxe-table-column>
        <vxe-table-column field="drugFunction" title="药品功效" sortable :edit-render="{name: 'input'}"></vxe-table-column>
        <vxe-table-column field="drugCount" title="药品库存" :edit-render="{name: 'select', options: typeList,defaultValue: '普通号'}"></vxe-table-column>
        <vxe-table-column field="drugCreattime" title="药品生产日期" sortable :edit-render="{name: 'input', defaultValue: 10}"></vxe-table-column>
        <vxe-table-column field="drugLasttime" title="药品有限期" sortable :edit-render="{name: '$input', props: {type: 'date'}, autoselect: true}"></vxe-table-column>
    </vxe-table>

</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script src="https://cdn.jsdelivr.net/npm/xe-utils"></script>
<script src="https://cdn.jsdelivr.net/npm/vxe-table"></script>
<!-- 使用 cdn 引用方式需要注意是否锁定版本，默认指向最新版本 -->
<script src="<%=basePath%>static/element/js/axios.js"></script>
<script src="<%=basePath%>static/element/js/qs.js"></script>
<script>
    var Main = {
        data() {
            return {
                tableData: [],
                typeList: [
                    { label: '', value: '' },
                    { label: '普通号', value: '普通号' },
                    { label: '专家号', value: '专家号' }
                ]
            }
        },
        created () {
            this.getdata();
        },
        methods: {
            getdata(){
                var self=this;
                axios.get('<%=basePath%>admin/menzhen/druglist').
                then(function (response) {
                    if(response.data.code=='10000'){
                        self.tableData=response.data.obj;
                        console.log(self.tableData)
                    }else {
                        self.$XModal.message({ message: '加载失败', status: 'error' })
                    }
                }).catch(function (error) {
                    self.$XModal.message({ message: '网路异常', status: 'error' })
                })
            },
            insertEvent (row) { //新增
                let record = {
                    register_type: '普通号'
                }
                this.$refs.xTable.insertAt(record, row)
                    .then(({ row }) => this.$refs.xTable.setActiveCell(row, 'register_number'));
            },
            getInsertEvent () { //保存
                var insertRecords = this.$refs.xTable.getInsertRecords();
                console.log(insertRecords);
                console.log(insertRecords[0].registerName);
                var self=this;
                var register={
                    'registerNumber':insertRecords[0].registerNumber,
                    'registerName':insertRecords[0].registerName,
                    'registerReason':insertRecords[0].registerReason,
                    'registerDoctor':insertRecords[0].registerDoctor,
                    'registerType':insertRecords[0].registerType,
                    'registerMoney':insertRecords[0].registerMoney,
                    'registerDate':insertRecords[0].registerDate,
                }
                axios.post('<%=basePath%>admin/menzhen/save', Qs.stringify(register))
                    .then(function (response) {
                        if(response.data.code=='10000'){
                            self.$XModal.message({ message: '保存成功', status: 'success' });
                            //保存后刷新数据表-----
                            self.getdata();
                        }else {
                            self.$XModal.message({ message: '保存失败', status: 'error' });
                        }
                    }).catch(()=> {
                    self.$XModal.message({ message: '网路异常', status: 'error' });
                })
            }
        }
    };
    var Ctor = Vue.extend(Main);
    new Ctor().$mount('#app')
</script>
</html>
