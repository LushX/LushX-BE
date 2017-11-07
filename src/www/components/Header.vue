<template lang="html">
  <Menu mode="horizontal" theme="primary" active-name="1" @on-select="onSelect">
    <div class="lushx-logo">
      <img src="~assets/logo.png" alt="logo">
    </div>
    <a class="lushx-btn login-btn">
      登录
    </a>
    <a class="lushx-btn register-btn">
      注册
    </a>
  </Menu>
</template>


<script>
  export default {
    name: "Header",
    data() {
      return {
      }
    },
    methods: {
      onSelect(value) {
        console.log(value)
      }
    }
  }
</script>


<style lang="less" scoped>
  .lushx-logo {
    padding-top: 7px;
    padding-bottom: 7px;
    padding-left: 28px;
    display: inline-block;
    & > img {
      width: 46px;
      height: 46px;
    }
  }

  .lushx-btn {
    margin-top: 14px;
    margin-right: 10px;
    float: right;
    padding: 4px 40px;
    line-height: 20px;
    border: 1px solid #EB5428;
    border-radius: 4px;
    background: #1D1C1F;
    overflow: hidden;
    color: #EB5428;
    text-decoration: none;
    text-indent: 0;
    transition: all .2s ease-in-out;
    cursor: pointer;
    &:hover {
      background: #0B0C0C;
      text-decoration: none;
      cursor: pointer;
    }
  }

  .ivu-menu-horizontal .ivu-menu-item, .ivu-menu-horizontal .ivu-menu-submenu {
    float: right;
  }

  @media (max-width: 600px) {
    .lushx-btn {
      padding: 4px 20px;
    }
  }
</style>
