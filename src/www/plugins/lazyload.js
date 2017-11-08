import Vue from 'vue'
import VueLazyload from 'vue-lazyload'

Vue.use(VueLazyload, {
  preLoad: 10,
  error: '//oz2yhn98k.bkt.clouddn.com/loading.gif',
  loading: '//oz2yhn98k.bkt.clouddn.com/loading.gif',
  attempt: 3,
  listenEvents: ['scroll', 'wheel', 'mousewheel', 'touchmove']
})