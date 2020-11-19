<template>
  <div class="m-content">
    <h3>欢迎来到calf的博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{user.username}}</div>
    </div>
    <div class="maction">
      <span><el-link href="/blogs">主页</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span><el-link type="success" href="/blog/add">发表博客</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span v-if="hasLogin"><el-link href="/login" type="primary">登录</el-link></span>
      <span v-else><el-link type="danger" @click="logout">退出</el-link></span>
    </div>
  </div>

</template>

<script>
  export default {
    name: "Header",
    data() {
      return {
         user:{
          username: '请先登录',
          src: 'https://wx2.sinaimg.cn/mw690/b2ad70e5gy1ge1ma198gpj20hs0hsmxx.jpg',
          avatar: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
         },
        hasLogin: true
      }

    },
    methods: {

      logout() {
        this.$axios.get("/logout",{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          console.log(res)
          this.$store.commit("SET_REMOVE")
          this.$router.push("/login")
        })
      }
    },
    created() {
      if (this.$store.getters.getUser.username) {
        this.user.username = this.$store.getters.getUser.username,
        this.user.avatar = this.$store.getters.getUser.avatar
        this.hasLogin = false
      }
    }
  }
</script>

<style scoped>
  .m-content {
    text-align: center;
  }
  .maction {
    margin: 10px 0;
  }
</style>