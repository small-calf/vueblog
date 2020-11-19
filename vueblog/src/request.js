import axios from 'axios'
import Element from 'element-ui'
import router from './router'
import store from './store'

axios.defaults.baseURL = "http://localhost:8081"

//前置拦截
  axios.interceptors.request.use(config => {
    console.log(config)
    return config
  })

  //后置拦截
  axios.interceptors.response.use(response => {
    let res = response.data
    if(res.code === 200) {
      console.log(response.data)
      return response
    }else {
      Element.Message.error('错了哦，这是一条错误消息');
      return Promise.reject(res.msg)//拒绝访问
    }
  }, error => {
    console.log(error.response)
        if (error.response.status === 401) {
            store.commit("SET_REMOVE")
            router.push("/login")
        }
      Element.Message.error(error.response.data.msg);
      return Promise.reject(error)//拒绝访问
      }
  )


//自己封装
// export function request (config) {
//   //1，创建axios实例
//   const instance =axios.create({
//     baseURL: 'http://localhost:8081',
//     timeout: 5000
//   })
//   axios.defaults.baseURL = "http://localhost:8081"
//
//   //请求拦截的作用
//   //前置拦截
//   instance.interceptors.request.use(config => {
//     console.log(config)
//     return config
//   })
//
//   //后置拦截
//   instance.interceptors.response.use(response => {
//     console.log(response.data)
//     return response
//   })
//
//   // instance(config,rule).then(res => {
//   //    console.log(res)
//   //  }).catch(err => {
//   //   console.log(rule)
//   //    console.log(err)
//   //  })
//   var params = new URLSearchParams();
//   params.append("username",'markerhub')
//   params.append("password",'111111')
//   //方法一
//   //  instance(config).then(res => {
//   //   console.log(res)
//   //   success("res")
//   // }).catch(err => {
//   //   console.log(err)
//   // })
//   //方法二
//   //return instance(config);
// }

// {
//   url: '/login',
//       method: 'post',
//     headers: {
//   'Content-Type': 'application/json'
// },
//   data: {
//     "username":'markerhub',
//         "password":'111111',
//   }
//
// }