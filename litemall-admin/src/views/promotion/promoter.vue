<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.username"
        clearable
        class="filter-item"
        style="width: 200px;"
        placeholder="请输入用户名"
      />
      <el-input
        v-model="listQuery.mobile"
        clearable
        class="filter-item"
        style="width: 200px;"
        placeholder="请输入手机号"
      />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button
        v-permission="['POST /admin/courier/create']"
        class="filter-item"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >添加</el-button>

      <el-button
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
      >导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="正在查询中。。。"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" width="100px" label="用户ID" prop="id" sortable />

      <el-table-column align="center" label="姓名" prop="name" />

      <el-table-column align="center" label="手机号码" prop="phone" />
      <el-table-column align="center" label="今日统计" prop="phone" />

      <el-table-column align="center" label="本月统计" prop="phone" />

      <el-table-column align="center" label="总计" prop="phone" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        ref="dataForm"
        :model="dataForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="推广员名称">
          <el-input v-model="dataForm.name" />
        </el-form-item>
        <el-form-item label="推广员手机号">
          <el-input v-model="dataForm.phone" auto-complete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, updatePromoter, createPromoter } from '@/api/promoter'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import store from '@/store'
import vPinyin from '@/lang/vue-py'
export default {
  name: 'Courier',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
        mobile: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      downloadLoading: false,
      dataForm: {
        cid: undefined,
        username: undefined,
        phone: undefined,
        name: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      console.log('-user ifo', store.getters.cid)
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.data.list
        this.total = response.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    }, handleCreate() {
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    }, createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          this.dataForm.cid = store.getters.cid
          this.dataForm.username = vPinyin.chineseToPinYin(this.dataForm.name)

          console.log(this.dataForm)
          createPromoter(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加配送员成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    }, updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          updatePromoter(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.id === this.dataForm.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新管理员成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    }, resetForm() {
      this.dataForm = {
        cid: undefined,
        username: undefined,
        phone: undefined
      }
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户名', '手机号码', '性别', '生日', '状态']
        const filterVal = ['username', 'mobile', 'gender', 'birthday', 'status']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
