<template>
  <div>
    <Header></Header>
    <div class="mblog">
      <h2>{{blog.title}}</h2>
      <el-link icon="el-icon-edit" v-if="ownBlog">
        <router-link :to="{name:'BlogEdit',params:{blogId:blog.id}}">编辑</router-link>

      </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content">

      </div>
    </div>
  </div>
</template>

<script>
  import 'github-markdown-css'
  import Header from "../components/Header";


  export default {
    name: "BlogDetail",
    components: {
      Header
    },
    data() {
      return {
        blog: {
          id: '',
          title: '',
          description: '',
          content: '',
        },
        ownBlog: false
      }
    },
    created() {
      const blogId = this.$route.params.blogId
      console.log(blogId)
      if(blogId) {
        this.$axios.get("/blog/"+blogId).then(res => {
          console.log(res)
          const blog = res.data.data

          this.blog.id = blog.id
          this.blog.title = blog.title
          //显示内容之前进行markdown渲染
          var MarkdownIt = require('markdown-it');
          var  md = new MarkdownIt();
          var result = md.render(blog.content);
          this.blog.content = result
          // this.blog.content = blog.content
          //this.blog.description = blog.description
          this.ownBlog = (blog.userId === this.$store.getters.getUser.id)
        });
      }
    }
  }
</script>

<style scoped>
  .mblog {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    width: 100%;
    min-height: 700px;
  }
</style>