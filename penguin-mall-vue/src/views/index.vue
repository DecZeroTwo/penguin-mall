<template>
    <div class="auth-container">
        <el-tabs v-model="activeTab" type="card">
            <el-tab-pane label="登录" name="login">
                <el-card class="auth-card">
                    <h3 class="auth-title">用户登录</h3>
                    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-width="0px">
                        <el-form-item prop="username">
                            <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
                        </el-form-item>
                        <el-form-item prop="password">
                            <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
                        </el-form-item>
                        <el-form-item prop="captcha">
                            <el-input v-model="loginForm.captcha" placeholder="验证码"></el-input>
                            <img class="captcha-image" :src="captchaImage" @click="refreshCaptcha" alt="验证码">
                        </el-form-item>
                        <el-form-item>
                            <div class="center-button">
                                <el-button type="primary" @click="login">登录</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-tab-pane>
            <el-tab-pane label="注册" name="registry">
                <el-card class="auth-card">
                    <h3 class="auth-title">用户注册</h3>
                    <el-form ref="registryForm" :model="registryForm" :rules="registryRules" label-width="0px">
                        <el-form-item prop="username">
                            <el-input v-model="registryForm.username" placeholder="用户名"></el-input>
                        </el-form-item>
                        <el-form-item prop="password">
                            <el-input type="password" v-model="registryForm.password" placeholder="密码"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-input v-model="registryForm.email" placeholder="邮箱"></el-input>
                            <el-button type="success" @click="sendEmailCaptcha">发送验证码</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-input v-model="registryForm.emailCaptcha" placeholder="邮箱验证码"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <div class="center-button">
                                <el-button type="primary" @click="registry">注册</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
import $request from '../utils/request'
export default {
    name: "index",
    data() {
        return {
            activeTab: 'login',
            loginForm: {
                username: '',
                password: '',
                captcha: '',
            },
            captchaImage: '',
            registryForm: {
                username: '',
                password: '',
                email: '',
                emailCaptcha: '',
                userRole: 1,
            },
            loginRules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ]
            },
            registryRules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
            }
        };
    },
    methods: {
        login() {
            let _this = this;
            _this.$refs.loginForm.validate(valid => {
                if (valid) {
                    $request.get('http://localhost:33333/penguinmall-ums/api/user/login', {
                        params: {
                            username: _this.loginForm.username,
                            password: _this.loginForm.password,
                            captcha: _this.loginForm.captcha,
                        },
                        headers: {
                            vCodeId: localStorage.getItem('vCodeId')
                        }
                    })
                        .then(function (response) {
                            if (response.data.code == 2000) {
                                localStorage.setItem('token', response.headers.get('token'));
                                if (response.data.result == 1) {
                                    console.log(1);
                                } else if (response.data.result == 2) {
                                    console.log(2);
                                }
                            } else {
                                _this.failMsg(response.data.result)
                            }
                        })
                        .catch(e => {
                            console.log(e)
                        });
                }
            });
        },
        sendEmailCaptcha() {
            let _this = this
            $request.get('http://localhost:33333/penguinmall-ums/api/user/emailCaptcha', {
                params: {
                    email: _this.registryForm.email
                },
            })
                .then(function (response) {
                    if (response.data.code == 2000) {
                        localStorage.setItem('emailCaptchaId', response.headers.get('emailCaptchaId'));
                        _this.successMsg("请在邮箱中查看验证码")
                    } else {
                        _this.failMsg("邮箱错误")
                    }
                })
                .catch(e => {
                    console.log(e)
                });
        },
        registry() {
            let _this = this;

            _this.$refs.registryForm.validate(valid => {
                if (valid) {
                    // 处理注册逻辑
                    $request.post('http://localhost:33333/penguinmall-ums/api/user/registry',
                        _this.registryForm,
                        {
                            headers: {
                                'emailCaptchaId': localStorage.getItem('emailCaptchaId')
                            }
                        }
                    )
                        .then(function (response) {
                            if (response.data.code == 2000) {
                                _this.successMsg("请等待邮箱通知是否注册成功")
                            } else {
                                _this.failMsg("注册失败")
                            }
                        })
                        .catch(e => {
                            console.log(e)
                        });
                }
            });
        },
        refreshCaptcha() {
            let _this = this;
            // 刷新验证码图片
            $request.get('http://localhost:33333/penguinmall-ums/api/user/captcha')
                .then(function (response) {
                    _this.captchaImage = response.data.result;
                    localStorage.setItem('vCodeId', response.headers.get('vCodeId'))
                })
                .catch(e => {
                    console.log(e)
                });
        },
        successMsg(msg) {
            this.$message({
                message: msg,
                type: 'success'
            });
        },
        failMsg(msg) {
            this.$message({
                showClose: true,
                message: msg,
                type: 'error'
            });
        },
    },
    created() {
        this.refreshCaptcha();
    }
}
</script>

<style>
.auth-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.auth-card {
    width: 400px;
}

.auth-title {
    text-align: center;
    font-size: 20px;
    margin-bottom: 20px;
}

.center-button {
    display: flex;
    justify-content: center;
}
</style>