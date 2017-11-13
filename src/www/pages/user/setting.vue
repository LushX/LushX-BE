<template lang="html">
  <section class="setting-container">
    <div class="icon-contaienr">
      <Icon class="back" type="android-arrow-back" @click.native="backToIndex"></Icon>
    </div>
    <div class="pannel-container">
      <div class="setting-pannel">
        <Row>
          <Col :xs="24" :sm="24" :md="9" :lg="9">
            <div class="avator">
              <img src="http://image.huangxizhou.com/Wallions277908.jpg" alt="用户头像">
                <button class="setting-item setting-btn">修改头像</button> 
                <p style="text-align:left;">支持 jpg、png 格式大小 5M 以内的图片</p>                                                        
            </div>
          </Col>
          <Col :xs="24" :sm="24" :md="15" :lg="15">
            <div class="setting-des">
              <div class="input-wrapper">
                <Input class="setting-item setting-input" icon="android-person" type="text" placeholder="昵称" size="large"></Input>
                <Input class="setting-item setting-input" icon="ios-email" type="text" placeholder="邮箱" size="large"></Input>
                <Input class="setting-item setting-input" icon="android-call" type="text" placeholder="手机号" size="large"></Input>
                <Input class="setting-item setting-input" icon="unlocked" type="password" placeholder="新密码" size="large"></Input> 
                <Input class="setting-item setting-input" icon="locked" type="password" placeholder="确认新密码" size="large"></Input>   
                <button class="setting-item setting-btn">提交修改</button>                                           
              </div>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  </section>
</template>


<script>
export default {
  name: "setting",
  data() {
    return {
    }
  },
  methods: {
    backToIndex() {
      this.$router.push({
        name: 'user'
      })
    },
  }
}
</script>


<style lang="less" scoped>
  .setting-container {
    width: 100%;
    min-height: 100vh;
    background-color: #57B99C;
    .icon-contaienr {
      width: 100%;
      height: 45px;
      background-color: #57B99C;
      .back {
        position: fixed;
        top: 8px;
        left: 8px;
        color: #FFF;
        font-size: 30px;
        font-weight: 600;
        cursor: pointer;
        transition: .25s;
        &:hover {
          color: #3B475A;
        }
      }
    }
  }

  .setting-btn {
    margin-bottom: 24px;
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

  .pannel-container {
    margin-left: auto;
    margin-right: auto;
    max-width: 960px;
    min-height: 90vh;
    background-color: #FFF;
    .setting-pannel {
      text-align: center;
      .avator {
        margin-top: 50px;
        display: inline-block;
        width: 128px;
        height: 128px;
        border-radius: 50%;
        border: 4px solid #57B99C;
        & > img {
          margin-bottom: 30px;           
          width: 120px;
          height: 120px;
          border-radius: 50%; 
        }
      }
      .setting-des {
        margin-top: 50px;
        margin-bottom: 50px;
        margin-left: 20px;
        margin-right: 20px;
        min-height: 300px;
        background-color: #EDEEF1;
        text-align: center;
        .input-wrapper {
          padding-top: 24px;        
          padding-left: 24px;
          padding-right: 24px;
        }
        .setting-item {
          width: 100%;
          margin-bottom: 14px;
        }
      }
    }
  }

  @media (max-width: 445px) {
    .pannel-container {
      .setting-pannel {
        .setting-des {
          .input-wrapper {
            padding-top: 14px;                    
            padding-left: 10px;
            padding-right: 10px;
          }
        }
      }
    }
  }
</style>