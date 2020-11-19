import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    userInfo: JSON.parse(sessionStorage.getItem("userInfo"))
  },
  mutations: {
    //set
    SET_TOKEN: (state, token) => {
      state.token = token;
      localStorage.setItem("token",token)
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo;
      //一次会话,但是sessionStorage中不能存对象，只能存字符串，所有进行json转化
      sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },
    SET_REMOVE: (state) => {
      state.token = '';
      state.userInfo = {};
      localStorage.setItem("token",'')
      //一次会话,但是sessionStorage中不能存对象，只能存字符串，所有进行json转化
      sessionStorage.setItem("userInfo",JSON.stringify(''))
    },
  },
  getters: {
    //get

    getUser : (state) => {
      return state.userInfo;
    }
  },
  actions: {

  },
  modules: {
  },

})
