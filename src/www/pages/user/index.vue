<template lang="html">
  <section class="user-container">
    <div class="bg-container" style="background-image:url('http://image.huangxizhou.com/Wallions277908.jpg')">
      <div><Icon class="back" type="android-arrow-back" @click.native="backToIndex"></Icon></div>
      <div class="user-des">
        <div class="user-head">
          <img src="http://image.huangxizhou.com/Wallions277908.jpg" alt="用户头像">
        </div>
        <h1>Trevor</h1>
        <button class="setting-btn">设置</button>        
      </div>
    </div>
    <div class="collection-container"></div>
  </section>
</template>


<script>
  export default {
    name: "index",
    data() {
      return {
      }
    },
    methods: {
      backToIndex() {
        this.$router.push({
          name: 'index'
        })
      }
    }
  }
</script>


<style lang="less" scoped>
  .user-container {
    width: 100%;
    min-height: 100vh;
  }

  .bg-container {
    position: relative;
    width: 100%;
    height: 400px;
    background-size: cover;
    text-align: center;
    background-position-x: 50%;
    .back {
      position: absolute;
      top: 8px;
      left: 8px;
      color: #FFF;
      font-size: 30px;
      font-weight: 600;
      cursor: pointer;
      transition: .25s;
      &:hover {
        color: #1abc9c;        
      }
    }
    .user-des {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      text-align: center;
      .user-head {
        display: inline-block;
        width: 128px;
        height: 128px;
        border: 4px solid #FFF;
        border-radius: 50%;
        & > img {
          width: 120px;
          height:120px;
          border-radius: 50%;
        }
      }
      & > h1 {
        color: #FFF;
      }
      .setting-btn {
        margin-top: 26px;
        width: 180px;
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
  }

  .collection-container {
    margin-left: auto;
    margin-right: auto;
    width: 840px;
    min-height: 300px;
    background: #222;
  }
</style>