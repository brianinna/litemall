<template>
  <md-field-group class="register_submit">
    <md-field v-model="receiver" icon="username" placeholder="请输入收货人"></md-field>
    <md-field v-model="tel" icon="mobile" placeholder="请输入手机号码" />
    <md-field v-model="address" icon="dingwei1" placeholder="请输入详细地址" />

    <div class="register_submit_btn">
      <van-button type="danger" size="large" @click="registerSubmit">确定</van-button>
    </div>
  </md-field-group>
</template>

<script>
import field from '@/components/field/';
import fieldGroup from '@/components/field-group/';
import { authRegisterCaptcha } from '@/api/api';
import { authRegister } from '@/api/api';
import { Toast } from "vant";
import { setLocalStorage } from '@/utils/local-storage';


export default {
  props: {
    token: String
  },
  data() {
    return {
      counting: true,
      code: '',
      tel: '',
      receiver: '',
      address: ''    };
  },
  mounted: function () {
  },

  methods: {
    registerSubmit() {
      if (this.receiver === '' || this.tel === '' || this.address === '') {
        return
      }
      this.token = this.$route.query.token
      console.log('token', this.token)
      let data = this.getRegisterData();
      authRegister(data).then(res => {
        console.log('111res', res)
        this.userInfo = res.data.data.userInfo;
        setLocalStorage({
          Authorization: res.data.data.token,
          avatar: this.userInfo.avatarUrl,
          nickName: this.userInfo.nickName
        });

        this.routerRedirect();


      }).catch(error => {
        Toast.fail(error.data.errmsg);
        if (error.data.errno == 705) {
          window.location = '#/login/';
        }
      });
    },


    getRegisterData() {
      const receiver = this.receiver;
      const tel = this.tel;
      const address = this.address;

      return {
        receiver: receiver,
        tel: tel,
        address: address,
        token: this.$route.query.token

      };
    },
    routerRedirect() {
      // const { query } = this.$route;
      // this.$router.replace({
      //   name: query.redirect || 'home',
      //   query: query
      // });
      window.location = '#/user/';
    },
    countDownEnd() {
      this.counting = false;
    }
  },

  components: {
    [field.name]: field,
    [fieldGroup.name]: fieldGroup
  }
};
</script>


<style lang="scss" scoped>
@import '../../../assets/scss/mixin';

.register_submit {
  padding-top: 40px;
  background-color: #fff;
}

.register_submit_btn {
  padding-top: 30px;
}

.time_down {
  color: $red;
}
</style>
