<template>
  <div>
    <section v-if="movieList[0]" class="container">
      <carousel-section :carouselList="carouselList"></carousel-section>
      <movie-section id="movie" :movieList="movieList"></movie-section>
      <live-section id="live" :liveList="liveList"></live-section>
      <animation-section id="animation" :animationList="animationList"></animation-section>
      <cam-section id="cam" :camList="camList"></cam-section>
      <tv-section id="tv" :tvList="tvList"></tv-section>
    </section>
    <circle-menu :circleMenuList="circleMenuList"></circle-menu>
    <lushx-skeleton v-if="!movieList[0]"></lushx-skeleton>
  </div>
</template>

<script>
  import LushxSkeleton from '~/components/Skeleton'
  import CarouselSection from '~/components/index/CarouselSection'  
  import MovieSection from '~/components/index/MovieSection'
  import LiveSection from '~/components/index/LiveSection'
  import AnimationSection from '~/components/index/AnimationSection'
  import CamSection from '~/components/index/CamSection'
  import TvSection from '~/components/index/TvSection'
  import CircleMenu from '~/components/CircleMenu'
  import ajax from '~/server/axios.config'
  import url from '~/server/url'
  import '~/filters/index'
  export default {
    name: "Auth",
    data() {
      return {
        movieList: [],
        liveList: [],
        animationList: [],
        camList: [],
        tvList: [],
        carouselList: [],
        circleMenuList: [
          {
            id: '#movie',
            value: '电影'
          }, {
            id: '#live',
            value: '直播'
          }, {
            id: '#animation',
            value: '动漫'
          }, {
            id: '#cam',
            value: '综艺'
          }, {
            id: '#tv',
            value: '电视'
          }
        ]
      }
    },
    components: {
      LushxSkeleton,
      CarouselSection,
      MovieSection,
      LiveSection,
      AnimationSection,
      CamSection,
      TvSection,
      CircleMenu
    },
    mounted () {
      ajax.get({
        url: url.INDEX_VEDIO
      }).then((data) => {
        this.carouselList = data.carousel
        this.movieList = data.movies
        this.liveList = data.cams
        this.animationList = data.animation
        this.camList = data.lives
        this.tvList = data.tv
      })
    }
  }
</script>

<style style lang="less" scoped>
  .container {
    margin-left: auto;
    margin-right: auto;
    padding-top: 80px;
    padding-bottom: 100px;
    max-width: 1440px;
    min-height: 100vh;
  }
</style>
