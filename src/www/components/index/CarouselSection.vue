<template lang="html">
  <Carousel
    v-model="start"
    :autoplay="option.autoplay"
    :autoplay-speed="option.autoplaySpeed"
    :radius-dot="option.radiusDot"
    :dots="option.dots"
    :trigger="option.trigger"
    :arrow="option.arrow"
    class="carousel-container"
    >
    <CarouselItem class="carousel-item" v-for="(item, idx) in carouselList" :key="idx">
      <div class="img-container" :style="`background-image:url(${item.image})`"></div>
    </CarouselItem>
  </Carousel>
</template>


<script>
export default {
  name: "CarouselSection",
  props: {
    carouselList: {
      type: Array
    }
  },
  data() {
    return {
      start: 0,
      option: {
        autoplay: true,
        autoplaySpeed: 3000,
        radiusDot: false,
        dots: 'outside',
        trigger: 'hover',
        arrow: 'never'
      }
    }
  }
}
</script>


<style lang="less" scoped>
  .carousel-container {
    margin-top: -21px;
    width: 100%;
    .img-container {
      width: 100%;
      height: 23.7rem;
      background-size: cover;
    }
  }

  .carousel-item {
    position: relative;
    .shadow-title {
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
      height: 28%;
      text-align: center;
      background-color: #222;
      opacity: .7;
    }
  }

  @media (min-width: 1200px) and (max-width: 1440px) {
    .carousel-container {
      .img-container {
        height: 26.7rem;
      }
    }
  }

  @media (min-width: 1000px) and (max-width: 1200px) {
    .carousel-container {
      .img-container {
        height: 24.7rem;
      }
    }
  }

  @media (min-width: 820px) and (max-width: 1000px) {
    .carousel-container {
      .img-container {
        height: 20.3rem;
      }
    }
  }

  @media (min-width: 625px) and (max-width: 820px) {
    .carousel-container {
      .img-container {
        height: 14.9rem;
      }
    }
  }

  @media (min-width: 440px) and (max-width: 625px) {
    .carousel-container {
      .img-container {
        height: 10.9rem;
      }
    }
  }

  @media (min-width: 360px) and (max-width: 440px) {
    .carousel-container {
      .img-container {
        height: 8.9rem;
      }
    }
  }

  @media (max-width: 360px) {
    .carousel-container {
      .img-container {
        height: 7.9rem;
      }
    }
  }
</style>