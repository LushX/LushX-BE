<template>
  <section class="container">
    <section v-for="title in sectionTitle" :key="title" class="type-section">
      <Row>
        <Col
          :xs="24"
          :sm="24"
          :md="24"
          :lg="24"
          class="title"
        >
          <div
            class="title-line"
            :class="[
              { green: title === 'movies' },
              { yellow: title === 'tv'},
              { red: title === 'lives'},
              { blue: title === 'animation'}
            ]">
            {{ title | sectionTitle }}
          </div>
        </Col>
        <Col
          v-for="(item, idx) in vedioList[title]"
          :key="idx"
          :xs="12"
          :sm="6"
          :md="6"
          :lg="6"
          class="vedio-container"
        >
        <div>
          <div class="vedio-item">
            <div class="shadow-des">
              <h3 class="shadow-des--content">{{ item.title }}</h3>
              <p class="shadow-des--content">{{ item.other }}</p>
            </div>
            <img :src="item.image" alt="视频预览图">
          </div>
        </div>
        <div class="vedio-btn">
          播放视频
        </div>
        </Col>
      </Row>
    </section>
  </section>
</template>

<script>
  import ajax from '../server/axios.config'
  import url from '../server/url'
  import '../filters'
  export default {
    name: "Auth",
    data() {
      return {
        sectionTitle: [],
        vedioList: {}
      }
    },
    mounted () {
      ajax.get({
        url: url.INDEX_VEDIO
      }).then((data) => {
        this.sectionTitle = Object.keys(data)
        this.vedioList = data
        console.log(this.vedioList)
      })
    }
  }
</script>

<style style lang="less" scoped>
  .container {
    padding-top: 80px;
    padding-bottom: 100px;
    min-height: 100vh;
  }

  .title {
    padding-left: 28px;
    font-weight: 500;
    font-size: 32px;
    letter-spacing: 1px;
    .title-line {
      display: inline-block;
      padding-top: 10px;
      padding-bottom: 10px;
      width: 160px;
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
    .vedio-container {
      text-align: center;
    }
  }

  .vedio-item {
    position: relative;
    margin-top: 40px;
    margin-left: 28px;
    margin-right: 28px;
    border-radius: 5px 5px 0 0;
    background-color: #EFEFF2;
    overflow: hidden;
    & > img {
      padding-top: 12px;
      padding-left: 12px;
      padding-right: 12px;
      padding-bottom: 12px;
      width: 100%;
      border-radius: 5px;
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
      & > p {
        font-size: 16px;
      }
      &:hover {
        opacity: .5;
      }
      &:hover + img {
        transform: scale(1.04);
      }
    }
  }

  .vedio-btn {
    margin-left: 28px;
    margin-right: 28px;
    height: 45px;
    border-radius: 0 0 5px 5px;
    background-color: #57B99D;
    color: #fff;
    text-align: center;
    line-height: 45px;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    transition: border 0.25s linear, color 0.25s linear, background-color 0.25s linear;
    &:hover {
      background-color: #48c9b0;
    }
  }

  @media (max-width: 445px) {
    .container {
      padding-top: 50px;
    }
    .vedio-item {
      .vedio-btn {
        height: 32px;
        line-height: 32px;
      }
    }
  }
</style>
