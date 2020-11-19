import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import Blogs from "../views/Blogs";
import BlogEdit from "../views/BlogEdit";
import BlogDetail from "../views/BlogDetail";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: Blogs
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {//详情页面
    path: '/blogs',
    name: 'Blogs',
    component: Blogs
  },

  {//添加页面
    path: '/blog/add',
    name: 'BlogAdd',
    component: BlogEdit
  },
  {//编辑页面
    path: '/blog/:blogId/edit',//:blogId表示要传的参数
    name: 'BlogEdit',
    component: BlogEdit
  },
  {//查询某一页面
    path: '/blog/:blogId',//:blogId表示要传的参数
    name: 'BlogDetail',
    component: BlogDetail
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
