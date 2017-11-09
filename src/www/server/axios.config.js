import Vue from 'vue'
import axios from 'axios'
import { Message } from 'iview'
axios.defaults.timeout = 1000000; //响应时间
axios.defaults.headers.post['Content-Type'] = 'application/json' //通信格式
axios.defaults.baseURL = 'http://139.224.135.245:8080/lushx' //配置接口地址
// axios.defaults.baseURL = '' //配置接口地址

export default {
  // POST请求
  post({...obj}) {
    return new Promise((resolve,reject) => {
      axios.post(obj.url, obj.data).then((data) => {
        if(data.data.status === 0){
          resolve(data.data.data)
        } else {
          Message.error(data.data.msg)
        }
      }).catch((data) => {
        reject(data)
      })
    })
  },

  // GET请求
  get({...obj}) {
    return new Promise((resolve,reject) => {
      axios.get(obj.url, obj.data).then((data) => {
        if(data.data.status === 0){
          resolve(data.data.data)
        } else {
          Message.error(data.data.msg)
        }
      }).catch((data) => {
        reject(data)
      })
    })
  }
}
