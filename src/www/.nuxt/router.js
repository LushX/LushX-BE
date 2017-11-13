import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const _4c627f60 = () => import('../pages/index.vue' /* webpackChunkName: "pages/index" */).then(m => m.default || m)
const _413a1a00 = () => import('../pages/user/index.vue' /* webpackChunkName: "pages/user/index" */).then(m => m.default || m)
const _7a0a1bfe = () => import('../pages/user/setting.vue' /* webpackChunkName: "pages/user/setting" */).then(m => m.default || m)



const scrollBehavior = (to, from, savedPosition) => {
      return { x: 0, y: 0 }
    }


export function createRouter () {
  return new Router({
    mode: 'history',
    base: '/',
    linkActiveClass: 'nuxt-link-active',
    linkExactActiveClass: 'nuxt-link-exact-active',
    scrollBehavior,
    routes: [
		{
			path: "/",
			component: _4c627f60,
			name: "index"
		},
		{
			path: "/user",
			component: _413a1a00,
			name: "user"
		},
		{
			path: "/user/setting",
			component: _7a0a1bfe,
			name: "user-setting"
		}
    ],
    fallback: false
  })
}
