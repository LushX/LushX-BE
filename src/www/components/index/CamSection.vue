<template lang="html">
  <section class="type-section">
    <Row>
      <Col :xs="24" :sm="24" :md="24" :lg="24" class="title-container">
        <div class="title-line blue">
          <span class="title">综艺</span>
        </div>
        <button class="refresh" @click="refresh()">
          <span>换一批</span>
          <Icon class="icon-refresh" type="android-refresh"></Icon>
        </button>
      </Col>
      <Col v-for="(item, idx) in randomCamList" :key="idx" :xs="12" :sm="6" :md="4" :lg="4" class="cam-container">
        <div>
          <div class="cam-item">
            <div class="shadow-des">
              <h3 class="shadow-des--content">{{ item.title }}</h3>
            </div>
            <img v-lazy="item.image" :alt="item.title">
          </div>
        </div>
        <div class="cam-btn">
          播放综艺
        </div>
      </Col>
    </Row>
  </section>
</template>


<script>
  import _ from 'lodash'
  export default {
    name: "CamSection",
    props: {
      camList: {
        type: Array
      }
    },
    data() {
      return {
        camSmallList: _.slice(this.camList, [0], [12])
      }
    },
    computed: {
      randomCamList() {
        return this.camSmallList
      }
    },
    methods: {
      refresh() {
        this.camSmallList = _.slice(_.shuffle(this.camList), [0], [12])
      }
    }
  }
</script>


<style lang="less" scoped>
  .title-container {
    padding-left: 28px;
    .title-line {
      display: inline-block;
      padding-top: 10px;
      padding-bottom: 10px;
      width: 160px;
      .title {
        font-weight: 500;
        font-size: 32px;
        letter-spacing: 1px;
        cursor: pointer;
      }
    }
    .refresh {
      padding-right: 28px;
      float: right;
      border: 0;
      background: transparent;
      color: #BEC2C7;
      outline: none;
      cursor: pointer;
      .icon-refresh {
        float: right;
        line-height: 72px;
        font-weight: 500;
        font-size: 32px;
      }
      & > span {
        float: right;
        padding-left: 6px;
        line-height: 72px;
        font-weight: 500;
        font-size: 22px;
      }
    }
    .red {
      border-bottom: 4px solid #D55745;
    }
    .blue {
      border-bottom: 4px solid #5195D6;
    }
    .yellow {
      border-bottom: 4px solid #EAC644;
    }
    .green {
      border-bottom: 4px solid #64C87A;
    }
  }

  .type-section {
    margin-top: 40px;
    .cam-container {
      text-align: center;
    }
  }

  .cam-item {
    position: relative;
    margin-top: 40px;
    margin-left: 28px;
    margin-right: 28px;
    min-height: 100px;
    border-radius: 5px 5px 0 0;
    background-color: #EFEFF2;
    overflow: hidden;
    & > img {
      padding-top: 6px;
      padding-left: 6px;
      padding-right: 6px;
      padding-bottom: 1px;
      width: 100%;
      cursor: pointer;
      transition: .25s;
    }
    .shadow-des {
      padding-bottom: 65px;
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: #222;
      z-index: 2;
      opacity: 0;
      cursor: pointer;
      transition: .25s;
      .shadow-des--content {
        padding-top: 20px;
        padding-left: 14px;
        padding-right: 14px;
        text-align: left;
        color: #fff;
        opacity: 1;
      }
      & > h3 {
        font-size: 18px;
        font-weight: 500;
      }
      &:hover {
        opacity: .5;
      }
      &:hover + img {
        transform: scale(1.04);
      }
    }
  }

  .cam-btn {
    margin-left: 28px;
    margin-right: 28px;
    height: 45px;
    border-radius: 0 0 5px 5px;
    background-color: #F39C12;
    color: #fff;
    text-align: center;
    line-height: 45px;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    transition: border 0.25s linear, color 0.25s linear, background-color 0.25s linear;
    &:hover {
      background-color: #D35400;
    }
  }

  @media (max-width: 445px) {
    .container {
      padding-top: 50px;
    }
    .cam-item {
      .cam-btn {
        height: 32px;
        line-height: 32px;
      }
    }
  }
</style>
