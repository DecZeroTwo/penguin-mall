import Vue from 'vue'
import VueRouter  from 'vue-router'
import Index from '../views/Index.vue'
import Main from '../views/main.vue'
Vue.use(VueRouter);
const router = new VueRouter({
    routes:[
        {
            path: '/index',
            component: Index
        },
    ]
});
export default router;