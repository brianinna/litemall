<template>
  <md-field-group class="foget_view">
    <div>充值金额(元)</div>
    <div style="	padding-top: 20px;">
      一键充值
      <van-button size="large" type="info" @click="onVip(128)">充值128元，送VIP</van-button>
    </div>
    <div style="	padding-top: 20px;">
      <van-button size="large" type="info" @click="onVip(200)">充值200元，送VIP返10元余额</van-button>
    </div>
    <div style="	padding-top: 20px;">
      <van-button size="large" type="info" @click="onVip(288)">充值288元，送VIP返20元余额</van-button>
    </div>
    <div style="	padding-top: 20px;">
      <van-button size="large" type="info" @click="onVip(500)">充值500元，送VIP返60元余额</van-button>
    </div>
    <div style="	padding-top: 20px;">
      <van-button size="large" type="info" @click="onVip(1000)">充值1000元，送VIP返150元余额</van-button>
    </div>
    <div style="	padding-top: 50px;">
      自定义金额
      <md-field data-vv-as="自定义金额" v-model="money" placeholder="请输入充值金额" />
    </div>
    <div class="foget_submit" style="	padding-top: 200px;">
      <van-button size="large" type="danger" @click="onSubmit">充值</van-button>
    </div>
  </md-field-group>
</template>

<script>
import { removeLocalStorage, getLocalStorage, setLocalStorage } from '@/utils/local-storage';
import { orderSubmit } from '@/api/api';

import field from '@/components/field/';
import fieldGroup from '@/components/field-group/';

export default {
  name: 'address-edit',

  data() {
    return {
      money: undefined
    };
  },
  created() {

  },

  methods: {
    onSubmit() {
      const { AddressId } = getLocalStorage('AddressId');

      this.isDisabled = true;

      orderSubmit({
        addressId: AddressId,
        cartId: this.money,
        message: '会员充值'
      }).then(res => {

        // 下单成功，重置下单参数。
        setLocalStorage({ CartId: 0, CouponId: 0 });

        let orderId = res.data.data.orderId;
        this.$router.push({
          name: 'payment',
          params: { orderId: orderId }
        });
      }).catch(error => {
        console.log(error)
        this.isDisabled = false;
        this.$toast("下单失败");
      })

    }, onVip(vip) {


      orderSubmit({
        cartId: vip,
        message: '会员充值'
      }).then(res => {

        // 下单成功，重置下单参数。
        setLocalStorage({ CartId: 0, CouponId: 0 });

        let orderId = res.data.data.orderId;
        this.$router.push({
          name: 'payment',
          params: { orderId: orderId }
        });
      }).catch(error => {
        console.log(error)
        this.isDisabled = false;
        this.$toast("下单失败");
      })

    }
  },

  components: {
    [field.name]: field,
    [fieldGroup.name]: fieldGroup
  }
};
</script>
