<template>
  <div>
    <el-container>
      <el-header>
        <img class="logo" src="https://wx2.sinaimg.cn/mw690/b2ad70e5gy1ge1ma198gpj20hs0hsmxx.jpg" alt="">
      </el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
// import {request} from "../request";

export default {
    name: "Login",
    data() {
      return {
        ruleForm: {
          username: 'calf',
          password: '12345678',
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],

        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            console.log(JSON.stringify(this.ruleForm))
            console.log(this.ruleForm)
            const _this = this
            // request({
            //   url: 'login',
            //   method: 'post',
            //   headers: {
            //       'Content-Type': 'application/json'},
            //   data: this.ruleForm
            //      //JSON.stringify(this.ruleForm)//转换为字符串
            //     //  "username": this.ruleForm.username,
            //     //  "password": this.ruleForm.password,
            //
            //
            // }).then(res=>{
            //   console.log(res)
            // })
            this.$axios.post("/login",this.ruleForm).then(res => {
              const jwt = res.headers['authorization']//获取jwt
              const userInfo = res.data.data;
              console.log(res.data)
              //把数据共享出去
              _this.$store.commit('SET_TOKEN',jwt)
              _this.$store.commit('SET_USERINFO',userInfo)
              //获取
              console.log(_this.$store.getters.getUser)
              _this.$router.push("/blogs")
            }).catch(err  => {
              console.log(err)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-main {
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .logo {
    width: 3%;
    height: 70%;
    margin-top: 10px;
    border-radius: 20px;
  }
  .demo-ruleForm {
    width: 500px;
    margin: 0 auto;
  }
</style>