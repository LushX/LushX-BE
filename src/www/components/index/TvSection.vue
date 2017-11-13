<template lang="html">
  <section class="type-section">
    <Row>
      <Col :xs="24" :sm="24" :md="24" :lg="24" class="title-container">
        <div class="title-line gray">
          <span class="title">电视</span>
        </div>
        <button class="refresh" @click="refresh()">
          <span>换一批</span>
          <Icon class="icon-refresh" type="android-refresh"></Icon>
        </button>
      </Col>
      <Col v-for="(item, idx) in randomTvList" :key="idx" :xs="8" :sm="6" :md="4" :lg="4" class="tv-container">
        <div>
          <div class="tv-item">
            <div class="shadow-des">
              <h3 class="shadow-des--content">{{ item.title }}</h3>
            </div>
            <img v-lazy="item.image" :alt="item.title">
          </div>
        </div>
        <div class="tv-btn">
          播放电视剧
        </div>
      </Col>
    </Row>
  </section>
</template>


<script>
  import _ from 'lodash'
  export default {
    name: "TvSection",
    props: {
      tvList: {
        type: Array
      }
    },
    data() {
      return {
        tvSmallList: _.slice(this.tvList, [0], [12])
      }
    },
    computed: {
      randomTvList() {
        return this.tvSmallList
      }
    },
    methods: {
      refresh() {
        this.tvSmallList = _.slice(_.shuffle(this.tvList), [0], [12])
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
    .gray {
      border-bottom: 4px solid #E4E7EE; 
    }
  }

  .type-section {
    margin-top: 40px;
    .tv-container {
      text-align: center;
    }
  }

  .tv-item {
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

  .tv-btn {
    margin-left: 28px;
    margin-right: 28px;
    height: 45px;
    border-radius: 0 0 5px 5px;
    background-color: #2980B9;
    color: #fff;
    text-align: center;
    line-height: 45px;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    transition: border 0.25s linear, color 0.25s linear, background-color 0.25s linear;
    &:hover {
      background-color: #2F577F;
    }
  }

  @media (min-width: 445px) and (max-width: 768px) {
    .title-container {
      padding-left: 3px;
      .refresh {
        padding-right: 3px;
      }
    }
    .tv-item {
      margin-left: 3px;
      margin-right: 3px;

    }
    .tv-btn {
      margin-left: 3px;
      margin-right: 3px;
    }
  }

  @media (max-width: 445px) {
    .title-container {
      padding-left: 3px;
      .refresh {
        padding-right: 3px;
      }
    }
    .tv-item {
      margin-left: 3px;
      margin-right: 3px;

    }
    .tv-btn {
      margin-left: 3px;
      margin-right: 3px;
    }
    .container {
      padding-top: 50px;
    }
    .tv-item {
      .tv-btn {
        height: 32px;
        line-height: 32px;
      }
    }
  }
</style>
