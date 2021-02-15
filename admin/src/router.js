import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue" // Admin主键名对应的页面是admin.vue

Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{ // 这一段的意思就是不管我在地址栏输入什么地址，他都会自动到转到/login这个路径，然后/login对应的是主键Login，主键Login对应的是login.vue这个界面
        path: "*",
        redirect: "/login",
    }, {
        path: "",
        redirect: "/login",
    }, {
        path: "/login",
        component: Login
    }, {
        path: "/admin",  // 访问路径/admin，就会跳转到主键Admin对应的页面
        component: Admin
    }]
})
