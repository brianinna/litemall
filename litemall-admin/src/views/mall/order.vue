<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.userId"
        clearable
        class="filter-item"
        style="width: 160px"
        placeholder="请输入用户ID"
      />
      <el-input
        v-model="listQuery.orderId"
        clearable
        class="filter-item"
        style="width: 160px"
        placeholder="请输入订单ID"
      />
      <el-input
        v-model="listQuery.orderSn"
        clearable
        class="filter-item"
        style="width: 160px"
        placeholder="请输入订单编号"
      />
      <el-select
        v-model="listQuery.message"
        style="width: 200px"
        class="filter-item"
        placeholder="请选择推荐人"
      >
        <el-option
          v-for="key in employeeDic"
          :key="key"
          :label="key"
          :value="key"
        />
      </el-select>
      <el-select
        v-model="listQuery.orderStatusArray"
        multiple
        style="width: 200px"
        class="filter-item"
        placeholder="请选择订单状态"
      >
        <el-option
          v-for="(key, value) in statusMap"
          :key="key"
          :label="key"
          :value="value"
        />
      </el-select>
      <el-option
        v-for="(key, value) in employeeDic"
        :key="key"
        :label="key"
        :value="value"
      />
      <el-date-picker
        v-model="listQuery.timeArray"
        type="datetimerange"
        value-format="yyyy-MM-dd HH:mm:ss"
        class="filter-item"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :picker-options="pickerOptions"
      />
      <el-select
        v-model="listQuery.orderStatusArray"
        multiple
        style="width: 200px"
        class="filter-item"
        placeholder="请选择订单状态"
      >
        <el-option
          v-for="(key, value) in statusMap"
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
        v-permission="['GET /admin/order/list']"
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

    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="peoples" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">新用户数量</div>
            <count-to
              :start-val="0"
              :end-val="newUser"
              :duration="2600"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('messages')">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="chart" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">送水数量</div>
            <count-to
              :start-val="0"
              :end-val="goodsTotal"
              :duration="3000"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('purchases')">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="chart" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">桶回收数量</div>
            <count-to
              :start-val="0"
              :end-val="returnTotal"
              :duration="3200"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('shoppings')">
          <div class="card-panel-icon-wrapper icon-shoppingCard">
            <svg-icon icon-class="chart" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">订单数量</div>
            <count-to
              :start-val="0"
              :end-val="orderTotal"
              :duration="3600"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
    </el-row>
    <!--   <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">充值金额</div>
            <count-to :start-val="0" :end-val="newCredit" :duration="2600" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('messages')">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="chart" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">新用户试喝数量</div>
            <count-to :start-val="0" :end-val="freeOrder" :duration="3000" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('purchases')">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">订单面额</div>
            <count-to
              :start-val="0"
              :end-val="orderTodayMoney"
              :duration="3200"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('shoppings')">
          <div class="card-panel-icon-wrapper icon-shoppingCard">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">实际流水收入</div>
            <count-to
              :start-val="0"
              :end-val="getPaidToday"
              :duration="3600"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
    </el-row>-->
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
        min-width="100"
        label="订单编号"
        prop="orderSn"
      />

      <el-table-column
        align="center"
        label="用户信息"
        width="250"
        prop="userId,consignee,mobile,address"
      >
        <template slot-scope="scope">
          {{ scope.row.userId }}.{{ scope.row.consignee }}
          <br>
          {{ scope.row.mobile }}
          <br>
          {{ scope.row.address }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="订单状态" prop="orderStatus">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.orderStatus | orderStatusFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="订单金额" prop="orderPrice" />

      <el-table-column align="center" label="支付金额" prop="actualPrice" />
      <el-table-column align="center" label="支付方式">
        <template slot-scope="scope">
          <el-tag>{{ scope.row | paymentTypeFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="支付时间"
        prop="payTime"
        min-width="110"
      />

      <!--     <el-table-column align="center" label="物流单号" prop="shipSn" />-->
      <el-table-column align="center" label="推荐人" prop="message" />
      <el-table-column align="center" label="配送员" prop="shipChannel" />
      <el-table-column
        align="center"
        label="送达时间"
        prop="goodConfirm.orderTime"
      />

      <el-table-column
        align="center"
        label="操作"
        width="250"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            v-permission="['GET /admin/order/detail']"
            type="primary"
            size="mini"
            @click="handleDetail(scope.row)"
          >详情</el-button>
          <el-button
            v-permission="['POST /admin/order/delete']"
            type="danger"
            size="mini"
            @click="handleDelete(scope.row)"
          >删除</el-button>
          <el-button
            v-if="scope.row.orderStatus == 201"
            v-permission="['POST /admin/order/ship']"
            type="primary"
            size="mini"
            @click="handleShip(scope.row)"
          >发货</el-button>
          <el-button
            v-if="scope.row.orderStatus == 301"
            v-permission="['POST /admin/order/ship']"
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

    <!-- 订单详情对话框 -->
    <el-dialog :visible.sync="orderDialogVisible" title="订单详情" width="800">
      <section ref="print">
        <el-form :data="orderDetail" label-position="left">
          <el-form-item label="订单编号">
            <span>{{ orderDetail.order.orderSn }}</span>
          </el-form-item>
          <el-form-item label="订单状态">
            <el-tag>{{
              orderDetail.order.orderStatus | orderStatusFilter
            }}</el-tag>
          </el-form-item>
          <el-form-item label="订单用户">
            <span>{{ orderDetail.user.nickname }}</span>
          </el-form-item>
          <el-form-item label="用户留言">
            <span>{{ orderDetail.order.message }}</span>
          </el-form-item>
          <el-form-item label="收货信息">
            <span>（收货人）{{ orderDetail.order.consignee }}</span>
            <span>（手机号）{{ orderDetail.order.mobile }}</span>
            <span>（地址）{{ orderDetail.order.address }}</span>
          </el-form-item>
          <el-form-item label="商品信息">
            <el-table
              :data="orderDetail.orderGoods"
              border
              fit
              highlight-current-row
            >
              <el-table-column
                align="center"
                label="商品名称"
                prop="goodsName"
              />
              <el-table-column align="center" label="商品编号" prop="goodsSn" />
              <el-table-column
                align="center"
                label="货品规格"
                prop="specifications"
              />
              <el-table-column align="center" label="货品价格" prop="price" />
              <el-table-column align="center" label="货品数量" prop="number" />
              <el-table-column align="center" label="货品图片" prop="picUrl">
                <template slot-scope="scope">
                  <img :src="scope.row.picUrl" width="40">
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item label="费用信息">
            <span>
              (实际费用){{ orderDetail.order.actualPrice }}元 = (商品总价){{
                orderDetail.order.goodsPrice
              }}元 + (快递费用){{ orderDetail.order.freightPrice }}元 -
              (优惠减免){{ orderDetail.order.couponPrice }}元 - (积分减免){{
                orderDetail.order.integralPrice
              }}元
            </span>
          </el-form-item>
          <el-form-item label="支付信息">
            <span>（支付渠道）微信支付</span>
            <span>（支付时间）{{ orderDetail.order.payTime }}</span>
          </el-form-item>
          <el-form-item label="快递信息">
            <span>（快递公司）{{ orderDetail.order.shipChannel }}</span>
            <span>（快递单号）{{ orderDetail.order.shipSn }}</span>
            <span>（发货时间）{{ orderDetail.order.shipTime }}</span>
          </el-form-item>
          <el-form-item label="退款信息">
            <span>（退款金额）{{ orderDetail.order.refundAmount }}元</span>
            <span>（退款类型）{{ orderDetail.order.refundType }}</span>
            <span>（退款备注）{{ orderDetail.order.refundContent }}</span>
            <span>（退款时间）{{ orderDetail.order.refundTime }}</span>
          </el-form-item>
          <el-form-item label="收货信息">
            <span>（支付时间）{{ orderDetail.order.payTime }}</span>
            <span>（送达时间）{{ orderDetail.order.confirmTime }}</span>
          </el-form-item>
          <el-form-item label="桶信息">
            <el-table
              :data="orderDetail.confirms"
              border
              fit
              highlight-current-row
            >
              <el-table-column align="center" label="日期" prop="orderDate" />
              <el-table-column
                align="center"
                label="送水"
                prop="orderGoodNum"
              />
              <el-table-column
                align="center"
                label="本品牌桶回收"
                prop="originalNum"
              />
              <el-table-column
                align="center"
                label="杂牌桶回收"
                prop="otherNum"
              />
              <el-table-column align="center" label="押卡" prop="fundCardNum" />
              <el-table-column align="center" label="押金" prop="fundPayNum" />
              <el-table-column align="center" label="总欠桶" prop="totalOwe" />
            </el-table>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="orderDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="printOrder">打 印</el-button>
      </span>
    </el-dialog>

    <!-- 发货对话框 -->
    <el-dialog :visible.sync="shipDialogVisible" title="发货">
      <el-form
        ref="shipForm"
        :model="shipForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="配送员" prop="courier">
          <el-select v-model="shipForm.courierId" placeholder="请选择">
            <el-option
              v-for="item in couriers"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="shipDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmShip">确定</el-button>
      </div>
    </el-dialog>

    <!-- 退款对话框 -->
    <el-dialog :visible.sync="refundDialogVisible" title="退款">
      <el-form
        ref="refundForm"
        :model="refundForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="退款金额" prop="refundMoney">
          <el-input v-model="refundForm.refundMoney" :disabled="true" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="refundDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmRefund">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { detailOrder, listOrder, refundOrder, deleteOrder, shipOrder, orderInfo } from '@/api/order'
import { fetchList } from '@/api/courier'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import checkPermission from '@/utils/permission' // 权限判断函数
import { findNames } from '@/api/admin'
import CountTo from 'vue-count-to'

const statusMap = {
  101: '未付款',
  102: '用户取消',
  103: '系统取消',
  201: '已付款',
  202: '申请退款',
  203: '已退款',
  301: '已发货',
  401: '配送成功',
  402: '系统收货'
}

export default {
  name: 'Order',
  components: { Pagination, CountTo },
  filters: {
    orderStatusFilter(status) {
      return statusMap[status]
    }, paymentTypeFilter(row) {
      if (row.actualPrice > 0) {
        return '微信支付'
      } else if (row.couponPrice > 0) {
        return '新用户体验'
      } else {
        return '余额支付'
      }
    }
  },
  data() {
    return {
      storeDic: [{ id: 1003, name: '龙湖店' }, { id: 1004, name: '百荣店' }],
      newUser: undefined,
      goodsTotal: undefined,
      returnTotal: undefined,
      orderTotal: undefined,
      newCredit: undefined,
      freeOrder: undefined,
      orderTodayMoney: undefined,
      getPaidToday: undefined,
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        id: undefined,
        name: undefined,
        timeArray: [],
        orderStatusArray: [],
        sort: 'add_time',
        order: 'desc',
        orderId: undefined,
        message: undefined,
        cid: 1003
      },
      employeeDic: undefined,

      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      statusMap,
      orderDialogVisible: false,
      orderDetail: {
        order: {},
        user: {},
        orderGoods: [],
        confirms: []
      },
      shipForm: {
        orderId: undefined,
        courierId: undefined
      },
      shipDialogVisible: false,
      refundForm: {
        orderId: undefined,
        refundMoney: undefined
      },
      refundDialogVisible: false,
      downloadLoading: false,
      couriers: []
    }
  },
  created() {
    this.getPromoters()
    this.getList()
    this.getCouriers()
  },
  methods: {
    checkPermission,
    getList() {
      this.listLoading = true
      if (this.listQuery.timeArray && this.listQuery.timeArray.length === 2) {
        this.listQuery.start = this.listQuery.timeArray[0]
        this.listQuery.end = this.listQuery.timeArray[1]
      } else {
        this.listQuery.start = null
        this.listQuery.end = null
      }
      if (this.listQuery.orderId) {
        detailOrder(this.listQuery.orderId).then(response => {
          this.list = []
          if (response.data.data.order) {
            this.list.push(response.data.data.order)
            this.total = 1
            this.listLoading = false
          }
        }).catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
      } else {
        listOrder(this.listQuery).then(response => {
          this.list = response.data.data.list
          this.total = response.data.data.total
          this.listLoading = false
        }).catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
      }

      orderInfo(this.listQuery).then(response => {
        console.log(response.data)
        this.newUser = response.data.data.newUser
        this.orderTotal = response.data.data.orderTotal
        this.goodsTotal = response.data.data.goodsTotal
        this.returnTotal = response.data.data.goodsTotal
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    }, getPromoters() {
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
    getCouriers() {
      fetchList().then(response => {
        this.couriers = response.data.data.list
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDetail(row) {
      detailOrder(row.id).then(response => {
        this.orderDetail = response.data.data
      })
      this.orderDialogVisible = true
    },
    handleShip(row) {
      this.shipForm.orderId = row.id
      this.shipForm.shipChannel = row.shipChannel
      this.shipForm.shipSn = row.shipSn

      this.shipDialogVisible = true
      this.$nextTick(() => {
        this.$refs['shipForm'].clearValidate()
      })
    },
    confirmShip() {
      this.$refs['shipForm'].validate((valid) => {
        if (valid) {
          shipOrder(this.shipForm).then(response => {
            this.shipDialogVisible = false
            this.$notify.success({
              title: '成功',
              message: '确认发货成功'
            })
            this.getList()
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleDelete(row) {
      deleteOrder({ orderId: row.id }).then(response => {
        this.$notify.success({
          title: '成功',
          message: '订单删除成功'
        })
        this.getList()
      }).catch(response => {
        this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
    },
    handleRefund(row) {
      this.refundForm.orderId = row.id
      this.refundForm.refundMoney = row.actualPrice

      this.refundDialogVisible = true
      this.$nextTick(() => {
        this.$refs['refundForm'].clearValidate()
      })
    },
    confirmRefund() {
      this.$refs['refundForm'].validate((valid) => {
        if (valid) {
          refundOrder(this.refundForm).then(response => {
            this.refundDialogVisible = false
            this.$notify.success({
              title: '成功',
              message: '确认退款成功'
            })
            this.getList()
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['订单ID', '订单编号', '用户ID', '订单状态', '是否删除', '收货人', '收货联系电话', '收货地址']
        const filterVal = ['id', 'orderSn', 'userId', 'orderStatus', 'isDelete', 'consignee', 'mobile', 'address']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '订单信息')
        this.downloadLoading = false
      })
    },
    printOrder() {
      this.$print(this.$refs.print)
      this.orderDialogVisible = false
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }
  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
    border-color: rgba(0, 0, 0, 0.05);
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
        background: #40c9c6;
      }
      .icon-message {
        background: #36a3f7;
      }
      .icon-money {
        background: #f4516c;
      }
      .icon-shoppingCard {
        background: #34bfa3;
      }
    }
    .icon-people {
      color: #40c9c6;
    }
    .icon-message {
      color: #36a3f7;
    }
    .icon-money {
      color: #f4516c;
    }
    .icon-shoppingCard {
      color: #34bfa3;
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}
</style>
