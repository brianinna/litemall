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
      <el-select
        v-model="listQuery.promoter"
        style="width: 200px"
        class="filter-item"
        placeholder="请选择推荐人"
      >
        <el-option v-for="(key, value) in employeeDic" :key="key" :label="key" :value="value" />
      </el-select>

      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
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

      <el-table-column align="center" label="用户名" prop="username" />

      <el-table-column align="center" label="手机号码" prop="mobile" />

      <el-table-column align="center" label="地址" prop="password">
        <template slot-scope="scope">
          <span style=" white-space: pre-wrap;">{{ scope.row.id | addressFilter }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="推荐人" prop="password">
        <template slot-scope="scope">
          <el-tag>{{ employeeDic[scope.row.password] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户类型" prop="status">
        <template slot-scope="scope">
          <el-tag>{{ statusDic[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户等级" prop="userLevel">
        <template slot-scope="scope">
          <el-tag>{{ levelDic[scope.row.userLevel] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-permission="['GET /admin/user/list']"
            type="primary"
            size="mini"
            @click="handleAddress(scope.row)"
          >地址修改</el-button>
          <el-button
            v-permission="['POST /admin/order/delete']"
            type="danger"
            size="mini"
            @click="handleDelete(scope.row)"
          >删除</el-button>
          <el-button
            v-if="scope.row.orderStatus==201"
            v-permission="['POST /admin/order/ship']"
            type="primary"
            size="mini"
            @click="handleShip(scope.row)"
          >定水记录</el-button>
          <el-button
            v-if="scope.row.orderStatus==301"
            v-permission="['POST /admin/order/list']"
            type="primary"
            size="mini"
            @click="handleShip(scope.row)"
          >转单</el-button>
          <el-button
            v-if="scope.row.orderStatus==202||scope.row.orderStatus==204"
            v-permission="['POST /admin/order/refund']"
            type="primary"
            size="mini"
            @click="handleRefund(scope.row)"
          >退款</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

    <!-- 地址修改对话框 -->
    <el-dialog :visible.sync="addressDialogVisible" title="发货">
      <el-form
        ref="addressForm"
        :model="addressForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model="addressForm.name" />
        </el-form-item>
        <el-form-item label="手机号" prop="tel">
          <el-input v-model="addressForm.tel" />
        </el-form-item>
        <el-form-item label="详细地址" prop="addressDetail">
          <el-input v-model="addressForm.addressDetail" />
        </el-form-item>
        <el-form-item label="备注" prop="postalCode">
          <el-input v-model="addressForm.postalCode" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addressDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateAdrees()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, listAddress, updateAddrees } from '@/api/user'
import { findNames } from '@/api/admin'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
let that

export default {
  name: 'User',
  components: { Pagination },
  filters: {
    addressFilter(id) {
      const list = that.address
      const tmp = list[id - 1]
      return '姓名：' + tmp.name + '\n手机号：' + tmp.tel + '\n详细地址：' + tmp.addressDetail + '\n备注: ' + tmp.postalCode
    }
  },
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
        promoter: undefined,
        sort: 'id',
        order: 'desc'
      },
      downloadLoading: false,
      genderDic: ['未知', '男', '女'],
      levelDic: ['普通用户', 'VIP用户', '高级VIP用户'],
      statusDic: ['位置错误', '商户', '住户'],
      employeeDic: [],
      address: [],
      addressDialogVisible: false,
      addressForm: {
        id: undefined,
        name: undefined,
        tel: undefined,
        addressDetail: undefined,
        rem: undefined
      }
    }
  }, beforeCreate() {
    that = this
  },
  created() {
    this.getAddress()
    this.getPromoters()
    this.getList()
  },
  methods: {
    getAddress() {
      listAddress(this.listQuery).then(response => {
        console.log('address respsne', response)
        this.address = response.data.data.list
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    getPromoters() {
      findNames()
        .then(response => {
          this.employeeDic = response.data.data
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    getList() {
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
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户名', '手机号码', '性别', '生日', '状态']
        const filterVal = ['username', 'mobile', 'gender', 'birthday', 'status']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    },
    handleAddress(row) {
      console.log('handleaddres')
      this.addressDialogVisible = true
      this.addressForm = this.address[row.id - 1]
      console.log('addresform', this.addressForm)
      this.$nextTick(() => {
        this.$refs['addressForm'].clearValidate()
      })
    }, updateAdrees() {
      updateAddrees(this.addressForm).then(response => {
        console.log('respones seccuss', response)
        this.addressDialogVisible = false
        this.$notify.success({
          title: '成功',
          message: '地址修改成功'
        })
        this.address[this.addressForm.id - 1] = this.addressForm
      }).catch(response => {
        console.log('respones fail', response)

        this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
    }
  }
}
</script>
