import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import user from './modules/user'

Vue.use(Vuex)

const store = () => new Vuex.Store({
  actions,
  modules: {
    user
  }
})
  
export default store
