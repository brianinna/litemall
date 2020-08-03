<template>
  <md-field-group class="foget_view">
    充值金额
    <md-field v-model="money" placeholder="请输入充值金额" />

    <div class="foget_submit">
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
      money: 0
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

    },
  },

  components: {
    [field.name]: field,
    [fieldGroup.name]: fieldGroup
  }
};
</script>
