<template lang="html">
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
</template>


<script>
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
        model: {
          username: '',
          password: ''
        }
      }
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
        ajax.post({
          url: url.LOGIN,
          data: this.model
        }).then((data) => {
          console.log(data)
        })
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