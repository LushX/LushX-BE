import * as types from './mutations_types'

/**
 * 保存用户信息
 * @param {Object} data
 */
export const initUser = ({ commit }, data) => {
    commit(types.INIT_USER, data)
}
