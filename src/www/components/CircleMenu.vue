<template lang="html">
  <div>
    <button @click.native="openMenu" class="btn">
      <Icon type="android-more-vertical"></Icon>
    </button>
    <transition name="move">
      <div class="menu" v-show="showMenu">
        <div v-for="(item, idx) in circleMenuList" :key="idx" :class="`inner inner-${ idx + 1 }`" @click.native="goAnchor(item.id)">{{ item.value }}</div>
      </div>
    </transition>
  </div>
</template>


<script>
  export default {
    name: "CircleMenu",
    props: {
      circleMenuList: {
        type: Array
      }
    },
    data() {
      return {
        showMenu: false
      }
    },
    methods: {
      openMenu() {
        this.showMenu = !this.showMenu;
      },
      goAnchor(selector) {
        let anchor = document.querySelector(selector)
        // console.log(anchor)
        // console.log(window.pageYOffset)
        // document.documentElement.scrollTop = anchor.offsetTop - 60
        // document.body.scrollTop = anchor.offsetTop
        // document.body.scrollTop
        // let anchor = document.querySelector(selector)
        // let scrollStep = -window.scrollY / (600 / 15),
        //   let scrollInterval = setInterval(() => {
        //   if ( window.pageYOffset != selector.pageYOffset ) {
        //     document.documentElement.scrollTop = anchor.offsetTop - 65
        //   }
        //   else clearInterval(scrollInterval)
        // }, 15)
        document.documentElement.scrollTop = anchor.offsetTop - 65
      }
    }
  }
</script>


<style lang="less" scoped>
.btn {
  position: fixed;
  bottom: 100px;
  right: 6px;
  z-index: 99999;
  width: 45px;
  height: 45px;
  line-height: 45px;
  border-radius: 50%;
  border: none;
  outline: none;
  color: #fff;
  font-size: 18px;
  background: #57B99D;
  cursor: pointer;
}
.menu {
  position: fixed;
  bottom: 100px;
  right: 6px;
  width: 45px;
  height: 45px;
  border-radius: 50%;
  z-index: 99998;
  transition: all 0.5s ease-in;
  &.move-enter-active {
    .inner {
      transform: translate3d(0, 0, 0);
      transition-timing-function: cubic-bezier(0, 0.57, 0.44, 1.97);
    }
    .inner-1 {
      transition-delay: 0.1s;
    }
    .inner-2 {
      transition-delay: 0.2s;
    }
    .inner-3 {
      transition-delay: 0.3s;
    }
    .inner-4 {
      transition-delay: 0.4s;
    }
    .inner-5 {
      transition-delay: 0.5s;
    }
  }
  &.move-enter,
  &.move-leave-active {
    .inner {
      transition-timing-function: ease-in-out;
    }
    .inner-1 {
      transform: translate3d(0, 60px, 0);
      transition-delay: 0.5s;
    }
    .inner-2 {
      transform: translate3d(40px, 40px, 0);
      transition-delay: 0.4s;
    }
    .inner-3 {
      transform: translate3d(60px, 0, 0);
      transition-delay: 0.3s;
    }
    .inner-4 {
      transform: translate3d(40px, -40px, 0);
      transition-delay: 0.2s;
    }
    .inner-5 {
      transform: translate3d(0, -60px, 0);
      transition-delay: 0.1s;
    }
  }
  .inner {
    display: inline-block;
    position: absolute;
    width: 30px;
    height: 30px;
    line-height: 30px;
    border-radius: 50%;
    text-align: center;
    color: #fff;
    cursor: pointer;
    transition: all 0.4s;
  }
  .inner-1 {
    top: -45px;
    left: 10px;
    background: #7DC581;
  }
  .inner-2 {
    top: -30px;
    left: -30px;
    background: #E4C75B;
  }
  .inner-3 {
    top: 10px;
    left: -50px;
    background: #C65E4C;
  }
  .inner-4 {
    top: 46px;
    left: -29px;
    background: #6192D1;
  }
  .inner-5 {
    top: 65px;
    left: 10px;
    background: #7DC581;
  }
}
</style>