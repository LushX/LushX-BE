<template lang="html">
  <div>
    <Modal
      v-model="showModal"
      :value="value"
      :closable="false"
      :width="360"
      class="modal-container">
      <div class="modal-content">
        <Input v-model="model.username" class="modal-item modal-input" icon="android-person" type="text" placeholder="用户名, 没有会自动注册哦" size="large"></Input>
        <Input v-model="model.password"class="modal-item modal-input" icon="locked" type="password" placeholder="请输入密码" size="large"></Input>
        <button class="modal-item auth-btn" @click="login">登录</button>
      </div>
      <div slot="footer"></div>
    </Modal>
    <lushx-loader v-show="showLoader"></lushx-loader>
  </div>
</template>


<script>
  import LushxLoader from '~/components/Loader.vue'
  import { mapActions } from 'vuex'
  import storage from 'store'
  import ajax from '~/server/axios.config'
  import url from '~/server/url'
  export default {
    name: "UserSettingModal",
    data() {
      return {
        showLoader: false,
        showModal: false
      }
    }
  }
</script>


<style lang="less" scoped>
</style>