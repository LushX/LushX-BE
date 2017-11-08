<template lang="html">
  <section class="type-section">
    <Row>
      <Col :xs="24" :sm="24" :md="24" :lg="24" class="title-container">
        <div class="title-line yellow">
          <span class="title">直播</span>
        </div>
        <button class="refresh" @click="refresh()">
          <span>换一批</span>
          <Icon class="icon-refresh" type="android-refresh"></Icon>
        </button>
      </Col>
      <Col v-for="(item, idx) in randomLiveList" :key="idx" :xs="12" :sm="8" :md="6" :lg="6" class="live-container">
        <div>
          <div class="live-item">
            <div class="shadow-des">
              <button class="live-btn">进入直播间</button>
            </div>
            <img v-lazy="item.image" :alt="item.title">
          </div>
          <h3>{{ item.title }}</h3>
        </div>
      </Col>
    </Row>
  </section>
</template>


<script>
  import _ from 'lodash'
  export default {
    name: "LiveSection",
    props: {
      liveList: {
        type: Array
      }
    },
    data() {
      return {
        liveSmallList: _.slice(this.liveList, [0], [12])
      }
    },
    computed: {
      randomLiveList() {
        return this.liveSmallList
      }
    },
    methods: {
      refresh() {
        this.liveSmallList = _.slice(_.shuffle(this.liveList), [0], [12])
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
    .live-container {
      text-align: center;
    }
  }

  .live-item {
    position: relative;
    margin-top: 40px;
    margin-left: 28px;
    margin-right: 28px;
    border-radius: 5px;
    background-color: #EFEFF2;
    overflow: hidden;
    & > img {
      padding-top: 6px;
      padding-left: 6px;
      padding-right: 6px;
      padding-bottom: 1px;
      width: 100%;
      height: 10rem;
      cursor: pointer;
      transition: .25s;
    }
    .shadow-des {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: #479B85;
      text-align: center;
      z-index: 2;
      opacity: 0;
      cursor: pointer;
      transition: .25s;
      &:hover {
        opacity: .8;
      }
      &:hover + img {
        transform: scale(1.09);
      }
    }
  }

  .live-btn {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding-left: 15px;
    padding-right: 15px;
    height: 34px;
    outline: none;
    background-color: #8E44AD;
    color: #fff;
    border: 0;
    vertical-align: middle;
    text-align: center;
    font-size: 1em;
    font-weight: 500;
    cursor: pointer;
    transition: border 0.25s linear, color 0.25s linear, background-color 0.25s linear;
    &:hover {
      background-color: #2C3E50;
    }
  }

  @media (min-width: 460px) and (max-width: 560px) {
    .live-item {
      & > img {
        height: 7rem;
      }
    }
  }

  @media (min-width: 400px) and (max-width: 460px) {
    .live-btn {
      display: none;
    }

    .live-item {
      & > img {
        height: 5.5rem;
      }
    }
  }

  @media (min-width: 375px) and (max-width: 400px) {
      .live-btn {
        display: none;
      }

    .live-item {
      & > img {
        height: 4.8rem;
      }
    }
  }

  @media (max-width: 375px) {
    .live-btn {
      display: none;
    }
    .live-item {
      & > img {
        height: 3.8rem;
      }
    }
  }
</style>
