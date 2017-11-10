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
  import storage from 'store'
  import ajax from '~/server/axios.config'
  import url from '~/server/url'
  import '~/filters/index'
  export default {
    name: "AuthModal",
    props: {
      value: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        showModal: false,
        showLoader: false,
        model: {
          username: '',
          password: ''
        }
      }
    },
    components: {
      LushxLoader
    },
    watch:{
      value(val) {
        this.showModal = val
      },
      showModal(val) {
        this.$emit('input', val)
      }
    },
    methods: {
      login() {
        if(!this.model.username.trim().length || this.model.username.length > 20) {
          this.$Message.warning('请输入有效用户名')
        } else if(!this.model.password.trim().length || this.model.password.length > 20) {
          this.$Message.warning('请输入有效密码')
        } else {
          this.showLoader = !this.showLoader
          ajax.post({
            url: url.LOGIN,
            data: this.model
          }).then((data) => {
            if(data.status === 0) {
              this.showLoader = !this.showLoader
              storage.set('userInfo', data.data.info)
              storage.set('Authorization', data.data.Authorization)
            }
            if(data.status === 1) {
              ajax.post({
                url: url.REGISTER,
                data: this.model
              }).then((data) => {
                ajax.post({
                  url: url.LOGIN,
                  data: this.model
                }).then((data) => {
                  if(data.status === 0) {
                    this.showLoader = !this.showLoader
                    storage.set('userInfo', data.data.info)
                    storage.set('Authorization', data.data.Authorization)
                  } else {
                    this.$Message.error(data.msg)
                    this.showLoader = !this.showLoader
                  }
                })
              })
            }
          })
        }
      }
    },
    mounted() {
      if (this.value) {
        this.showModal = true;
      }
    }
  }
</script>


<style lang="less" scoped>
  .modal-container {
    text-align: center;
  }
  .modal-content {
    .modal-item {
      margin-bottom: 18px;
    }
    .modal-input {
      width: 100%;
    }
    .auth-btn {
      width: 100%;
      height: 36px;
      border-radius: 3px;
      background-color: #57B99D;
      color: #fff;
      border: 0;
      outline: none;
      text-align: center;
      line-height: 36px;
      font-size: 16px;
      font-weight: 500;
      cursor: pointer;
      transition: border 0.25s linear, color 0.25s linear, background-color 0.25s linear;
      &:hover {
        background-color: #6DC6B1;
      }
    }
  }

</style>