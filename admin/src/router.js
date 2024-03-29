import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue" // Admin主键名对应的页面是admin.vue
import Welcome from "./views/admin/welcome.vue"
import Course from "./views/admin/course.vue"
import Chapter from "./views/admin/chapter.vue"
import Section from "./views/admin/section.vue"
import Category from "./views/admin/category.vue"
import Teacher from "./views/admin/teacher.vue"
import Content from "./views/admin/content.vue"
import File from "./views/admin/file.vue"
import User from "./views/admin/user.vue"
import Resource from "./views/admin/resource.vue"
import Role from "./views/admin/role.vue"
import Member from "./views/admin/member.vue"
import Sms from "./views/admin/sms.vue"



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
        path: "/",  // 访问路径/admin，就会跳转到主键Admin对应的页面
        name: "admin",
        component: Admin,
        meta: {
            loginRequire: true
        },
        children:[{ // 添加子路由  子路由就是父访问链接后面再拼上子路由的访问链接
            path: "welcome", // 子路由设置不能加斜杠
            name: "welcome",
            component: Welcome,
        }, {
            path: "business/course", // 子路由设置不能加斜杠
            name: "business/course",
            component: Course,
        },{
            path: "business/chapter", // 子路由设置不能加斜杠
            name: "business/chapter",
            component: Chapter,
        },{
            path: "business/section", // 子路由设置不能加斜杠
            name: "business/section",
            component: Section,
        },{
            path: "business/category", // 子路由设置不能加斜杠
            name: "business/category",
            component: Category,
        },{
            path: "business/content", // 子路由设置不能加斜杠
            name: "business/content",
            component: Content,
        },{
            path: "business/teacher", // 子路由设置不能加斜杠
            name: "business/teacher",
            component: Teacher,
        }, {
            path: "business/member",
            name: "business/member",
            component: Member,
        }, {
            path: "business/sms",
            name: "business/sms",
            component: Sms,
        },{
            path: "file/file", // 子路由设置不能加斜杠
            name: "file/file",
            component: File,
        }, {
            path: "system/user",
            name: "system/user",
            component: User,
        }, {
            path: "system/resource",
            name: "system/resource",
            component: Resource,
        }, {
            path: "system/role",
            name: "system/role",
            component: Role,
        }]
    }]
})
