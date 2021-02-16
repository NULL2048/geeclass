import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue" // Admin主键名对应的页面是admin.vue
import Welcome from "./views/admin/welcome.vue"

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
        component: Admin,
        children:[{ // 添加子路由  子路由就是父访问链接后面再拼上子路由的访问链接
            path: "welcome", // 子路由设置不能加斜杠
            component: Welcome,
        }]
    }]
})