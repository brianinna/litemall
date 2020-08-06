<template>
  <div class="user_header" :style="{backgroundImage: `url(${background_image})`}">
    <van-icon name="set" class="user_set" @click="toSetting" />
    <div class="user_avatar">
      <img :src="avatar" alt="头像" width="55" height="55" />
    </div>
    <div>{{nickName}}</div>
    <div>{{ levelDic[userLevel] }} | 余额：{{balance}} 元</div>
  </div>
</template>

<script>
import avatar_default from '@/assets/images/avatar_default.png';
import bg_default from '@/assets/images/user_head_bg.png';
import { getLocalStorage } from '@/utils/local-storage';
import { authInfo } from '@/api/api';

export default {
  name: 'user-header',

  props: {
    isLogin: {
      type: Boolean,
      default: false
    }
  },

  data() {
    return {
      nickName: '昵称',
      avatar: avatar_default,
      background_image: bg_default,
      levelDic: ['普通用户', 'VIP用户', '高级VIP用户'],
      userLevel: 0,
      balance: '0.00'
    };
  },

  activated() {
    this.getUserInfo();
    this.getBalanceInfo();
  },

  methods: {
    getBalanceInfo() {
      authInfo().then(res => {
        this.userLevel = res.data.data.userLevel;
        this.balance = res.data.data.balance || 0.00;
      })
    },
    getUserInfo() {
      const infoData = getLocalStorage(
        'nickName',
        'avatar',
        /*  'userLevel',
         'balance' */
      );
      console.log(infoData)
      this.avatar = infoData.avatar || avatar_default;
      this.nickName = infoData.nickName || '昵称';
      /*  this.userLevel = infoData.userLevel || 0;
       this.balance = infoData.balance || '0.00'; */
    },
    toSetting() {
      this.$router.push({ name: 'user-information' });
    }
  }
};
</script>

<style lang="scss" scoped>
.user_header {
  background-repeat: no-repeat;
  background-size: cover;
  height: 150px;
  text-align: center;
  color: #fff;
  padding-top: 30px;
}

.user_set {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
}
.user_avatar {
  margin-bottom: 10px;
  img {
    border: 0;
    border-radius: 50%;
  }
}
</style>
