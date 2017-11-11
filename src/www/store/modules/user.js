import * as types from '../mutations_types'

// state
const state = {
  userInfo: {}
}

// mutations
const mutations = {
  // 设置user
  [types.INIT_USER] (state, data) {
    state.userInfo = data
  }
}

// 导出state, mutations
export default {
  state,
  mutations
}
