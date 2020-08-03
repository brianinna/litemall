<template>
  <md-field-group class="register_submit">
    <span style="font-size:50px">新用户注册</span>
    <van-radio-group v-model="radio" direction="horizontal" style="height:200px">
      <van-radio name="1" style="padding-left:50px">
        <template #icon="props">
          <img class="img-icon" :src="props.checked ? activeIcon : inactiveIcon" />
        </template>
      </van-radio>
      <van-radio name="2" style="padding-left:50px">
        <template #icon="props">
          <img class="img-icon" :src="props.checked ? homeActiveIcon : homeInactiveIcon" />
        </template>
      </van-radio>
    </van-radio-group>

    <md-field v-model="receiver" icon="username" placeholder="请输入收货人"></md-field>
    <md-field v-model="tel" icon="mobile" placeholder="请输入手机号码" />
    <md-field v-model="dragData.address" icon="dingwei1" />
    <md-field v-model="address" icon="dingwei1" placeholder="请输入详细地址" />

    <mapDrag @drag="dragMap" class="mapbox"></mapDrag>

    <div class="register_submit_btn">
      <van-button type="danger" size="large" @click="registerSubmit">确定</van-button>
    </div>
  </md-field-group>
</template>

<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=0628686f4620797ee6a4f17a0c21fdc7"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
<script>
import field from '@/components/field/';
import fieldGroup from '@/components/field-group/';
import mapDrag from '@/components/mapdrag/'
import { authRegisterCaptcha } from '@/api/api';
import { authRegister } from '@/api/api';
import { Toast, RadioGroup, Radio } from "vant";
import { setLocalStorage } from '@/utils/local-storage';


export default {
  props: {
    token: String
  },
  data() {
    return {
      radio: '1',
      activeIcon: 'https://wechat.magicmirrortech.cn/public/images/company-ac.png',
      inactiveIcon: 'https://wechat.magicmirrortech.cn/public/images/company.png',
      homeActiveIcon: 'https://wechat.magicmirrortech.cn/public/images/home-ac.png',
      homeInactiveIcon: 'https://wechat.magicmirrortech.cn/public/images/home.png',
      counting: true,
      code: '',
      tel: '',
      receiver: '',
      address: '',
      dragData: {
        lng: null,
        lat: null,
        address: null,
        nearestJunction: null,
        nearestRoad: null,
        nearestPOI: null
      }    };
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
          nickName: this.userInfo.nickName,
          AddressId: this.userInfo.AddressId
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
      console.log('drag data', this.dragData)
      const addressDrag = this.dragData.address
      const lat = this.dragData.lat
      const lng = this.dragData.lng
      return {
        receiver: receiver,
        tel: tel,
        rem: address,
        address: addressDrag,
        lat: lat,
        lng: lng,
        token: this.$route.query.token,
        status: this.radio,
        rem: address
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
    }, dragMap(data) {
      this.dragData = {
        lng: data.position.lng,
        lat: data.position.lat,
        address: data.address,
        nearestJunction: data.nearestJunction,
        nearestRoad: data.nearestRoad,
        nearestPOI: data.nearestPOI
      }
    }
  },

  components: {
    [field.name]: field,
    [fieldGroup.name]: fieldGroup,
    mapDrag
  }
};
</script>


<style lang="scss" scoped>
@import '../../../assets/scss/mixin';
.img-icon {
  height: 100px;
}
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

body {
  margin: 0;
}
.page-header {
  color: #fff;
  text-align: center;
  background: #159957;
  background-image: -webkit-linear-gradient(330deg, #155799, #159957);
  background-image: linear-gradient(120deg, #155799, #159957);
  padding: 3rem 4rem;
  margin-bottom: 30px;
}
.page-header h1 {
  margin: 0;
  font-size: 40px;
}
.page-header p {
  color: #ccc;
  margin: 0;
  margin-bottom: 30px;
}
.page-header a {
  display: inline-block;
  border: 1px solid #fff;
  margin-right: 10px;
  line-height: 40px;
  padding: 0 20px;
  border-radius: 4px;
  color: #fff;
  text-decoration: none;
  transition: all 0.3s;
}
.page-header a:hover {
  background: #fff;
  color: #333;
}
.g-wraper {
  width: 1000px;
  margin: 0 auto;
  color: #666;
  font-size: 16px;
  line-height: 30px;
}
.m-part {
  margin-bottom: 30px;
}
.m-part::after {
  content: '';
  display: block;
  clear: both;
}
.m-part .title {
  font-size: 30px;
  line-height: 60px;
  margin-bottom: 10px;
  color: #333;
}
.m-part .mapbox {
  width: 600px;
  height: 400px;
  margin-bottom: 20px;
  float: left;
}
.m-part .info {
  margin: 0;
  padding: 0;
  list-style: none;
  line-height: 30px;
  margin-left: 620px;
}
.m-part .info span {
  display: block;
  color: #999;
}
.m-part ol {
  line-height: 40px;
  margin-left: 0;
  padding-left: 0;
}
.m-part pre {
  padding: 10px 20px;
  line-height: 30px;
  border-radius: 3px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
}
.m-footer {
  background: #eee;
  line-height: 60px;
  text-align: center;
  color: #999;
  font-size: 12px;
}
.m-footer a {
  margin: 0 5px;
  color: #999;
  text-decoration: none;
}
</style>
