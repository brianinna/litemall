<template>
  <div class="user_module">
    <van-cell-group>
      <van-cell icon="shoucang" title="充值" to="/user/pay" isLink />
      <van-cell icon="dingwei" title="收货地址" to="/user/address" isLink />
      <van-cell icon="kefu" title="服务中心" to="/user/server" isLink />
      <a
        href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzIxNTI5MjE4Ng==#wechat_redirect"
      >
        <van-cell icon="kefu" title="公众号" isLink />
      </a>
    </van-cell-group>
  </div>
</template>

<script>
export default {
  name: 'user-module'
};
</script>


<style scoped lang="scss">
.user_module {
  background-color: #fff;
}
</style>
