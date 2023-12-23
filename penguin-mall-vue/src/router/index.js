import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import Home from '../views/Home.vue'
Vue.use(VueRouter);
const router = new VueRouter({
    routes: [
        {
            path: '/index',
            component: Index
        },
        {
            path: '/home',
            component: Home,
            children: [
                {
                    path: '/category',
                    component: () => import('../views/product/category.vue'),
                },
            ]
        },
    ]
});
export default router;