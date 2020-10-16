<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.nickname"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入商户名称"
      />
      <el-input
        v-model="listQuery.mobile"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入手机号"
      />
      <el-select
        v-model="listQuery.promoter"
        style="width: 200px"
        class="filter-item"
        placeholder="请选择推荐人"
      >
        <el-option
          v-for="(key, value) in employeeDic"
          :key="key"
          :label="key"
          :value="value"
        />
      </el-select>

      <el-select
        v-model="listQuery.cid"
        style="width: 200px"
        class="filter-item"
        placeholder="请选择门店"
      >
        <el-option
          v-for="item in storeDic"
          :key="item.name"
          :label="item.name"
          :value="item.id"
        />
      </el-select>

      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="handleFilter"
      >查找</el-button>
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
      <el-table-column
        align="center"
        width="100px"
        label="用户ID"
        prop="id"
        sortable
      />

      <el-table-column align="center" label="微信昵称" prop="username" />
      <el-table-column align="center" label="商户名称" prop="nickname" />

      <el-table-column align="center" label="手机号码" prop="mobile" />

      <el-table-column align="center" label="地址" prop="password">
        <template slot-scope="scope">
          <span
            style="white-space: pre-wrap"
            @click="handleAddress(scope.row)"
          >{{ scope.row.address | addressFilter }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="推荐人" prop="password">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.password }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="注册日期" prop="addTime" />
      <el-table-column align="center" label="用户类型" prop="status">
        <template slot-scope="scope">
          <el-tag @click="handleStatus(scope.row)">{{
            statusDic[scope.row.status]
          }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户等级" prop="userLevel">
        <template slot-scope="scope">
          <el-tag @click="handleLevel(scope.row)">{{
            levelDic[scope.row.userLevel]
          }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="操作"
        width="250"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- el-button
            v-permission="['GET /admin/user/list']"
            type="primary"
            size="mini"
          >地址修改</el-button>-->
          <el-button
            v-permission="['GET /admin/user/list']"
            type="primary"
            size="mini"
            @click="handleAddress(scope.row)"
          >定水记录</el-button>
          <el-button
            v-permission="['GET /admin/user/list']"
            type="primary"
            size="mini"
            @click="handleCredit(scope.row)"
          >增加余额</el-button>
          <el-button
            v-permission="['POST /admin/order/delete']"
            type="danger"
            size="mini"
            @click="handleDelete(scope.row)"
          >删除</el-button>
          <el-button
            v-if="scope.row.orderStatus == 301"
            v-permission="['POST /admin/order/list']"
            type="primary"
            size="mini"
            @click="handleShip(scope.row)"
          >转单</el-button>
          <el-button
            v-if="scope.row.orderStatus == 202 || scope.row.orderStatus == 204"
            v-permission="['POST /admin/order/refund']"
            type="primary"
            size="mini"
            @click="handleRefund(scope.row)"
          >退款</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

    <!-- 地址修改对话框 -->
    <el-dialog :visible.sync="addressDialogVisible" title="地址修改">
      <el-form
        ref="addressForm"
        :model="addressForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
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

    <!-- 更改用户类型 -->
    <el-dialog :visible.sync="statusDialogVisible" title="更改用户类型">
      <el-form
        ref="statusForm"
        :model="statusForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="用户类型" prop="status">
          <el-radio v-model="statusForm.status" label="1">商户</el-radio>
          <el-radio v-model="statusForm.status" label="2">住户</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="statusDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateStatus()">确定</el-button>
      </div>
    </el-dialog>
    <!-- 更改vip -->
    <el-dialog :visible.sync="levelDialogVisible" title="更改用户类型">
      <el-form
        ref="statusForm"
        :model="statusForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="用户类型" prop="status">
          <el-radio v-model="statusForm.userLevel" label="0">普通用户</el-radio>
          <el-radio v-model="statusForm.userLevel" label="1">VIP</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="statusDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateStatus()">确定</el-button>
      </div>
    </el-dialog>

    <!-- 增加余额 -->
    <el-dialog :visible.sync="creditDialogVisible" title="增加余额">
      <el-form
        ref="creditForm"
        :model="creditForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="当前余额" prop="credit">
          <el-input v-model="this.credit" />
        </el-form-item>

        <el-form-item label="金额" prop="money">
          <el-input v-model="creditForm.money" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="creditDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateCredit()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, updateAddrees, addCredit, getCredit, updateUserType } from '@/api/user'
import { findNames } from '@/api/admin'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'User',
  components: { Pagination },
  filters: {
    addressFilter(address) {
      return '姓名：' + address.name + '\n手机号：' + address.tel + '\n详细地址：' + address.addressDetail + '\n备注: ' + address.postalCode
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
        nickname: undefined,
        mobile: undefined,
        promoter: undefined,
        sort: 'id',
        order: 'desc',
        userId: undefined,
        cid: 1003
      },
      downloadLoading: false,
      genderDic: ['未知', '男', '女'],
      levelDic: ['普通用户', 'VIP用户', '高级VIP用户'],
      statusDic: ['位置错误', '商户', '住户'],
      employeeDic: undefined,
      storeDic: [{ id: 1003, name: '龙湖店' }, { id: 1004, name: '百荣店' }],
      address: [],
      addressDialogVisible: false,
      creditDialogVisible: false,
      statusDialogVisible: false,
      levelDialogVisible: false,
      status: undefined,

      addressForm: {
        id: undefined,
        name: undefined,
        tel: undefined,
        addressDetail: undefined,
        postalCode: undefined
      },
      creditForm: {
        id: undefined,
        money: undefined
      }, statusForm: {
        id: undefined,
        status: '',
        userLevel: ''
      },
      credit: ''
    }
  },
  created() {
    this.getPromoters()
    this.getList()
  },
  methods: {
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
        for (var i = 0; i < this.list.length; i++) {
          var id = parseInt(this.list[i].password)
          this.list[i].password = this.employeeDic[id]
          // console.log(this.list[i].password)
        }
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
        const tHeader = ['用户名', '手机号码', '地址', '推荐人', '用户类型1商户2用户', '用户等级0普通1vip']
        const filterVal = ['username', 'mobile', 'birthday', 'password', 'status', 'userLevel']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    },
    handleAddress(row) {
      console.log('handleaddres')
      this.addressDialogVisible = true
      this.addressForm.id = row.address.id
      this.addressForm.tel = row.address.tel
      this.addressForm.addressDetail = row.address.addressDetail
      this.addressForm.postalCode = row.address.postalCode
      this.addressForm.name = row.address.name
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
        this.getList()
      }).catch(response => {
        console.log('respones fail', response)

        this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
    }, handleCredit(row) {
      this.listQuery.id = row.id
      getCredit(this.listQuery).then(response => {
        this.credit = response.data.data
      })
      console.log('creditDialogVisible')
      this.creditDialogVisible = true
      this.creditForm.id = row.id
      /*  this.$nextTick(() => {
        this.$refs['addressForm'].clearValidate()
      }) */
    }, handleStatus(row) {
      console.log('here')
      this.statusForm.status = row.status.toString()
      this.statusDialogVisible = true
      console.log(this.statusForm.status)

      this.statusForm.id = row.id
      /*  this.$nextTick(() => {
        this.$refs['addressForm'].clearValidate()
      }) */
    }, handleLevel(row) {
      console.log('here')
      this.statusForm.userLevel = row.userLevel.toString()
      this.levelDialogVisible = true
      this.statusForm.id = row.id
    }, updateCredit() {
      addCredit(this.creditForm).then(response => {
        console.log('respones seccuss', response)
        this.creditDialogVisible = false
        this.$notify.success({
          title: '成功',
          message: '信用修改成功'
        })
      }).catch(response => {
        console.log('respones fail', response)

        this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
    }, updateStatus() {
      // this.statusForm.status = this.status
      updateUserType(this.statusForm).then(response => {
        console.log('respones seccuss', response)
        this.statusDialogVisible = false
        this.levelDialogVisible = false

        this.$notify.success({
          title: '成功',
          message: '用户类型修改成功'
        })
        this.getList()
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
