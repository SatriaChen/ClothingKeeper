import Vue from 'vue'
import App from './App'
import store from './store'

import mainpage from'./pages/main/main.vue'
Vue.component('mainpage',mainpage)

import about from './pages/basics/about.vue'
Vue.component('about',about)


import closet from './pages/basics/closet.vue'
Vue.component('closet',closet)

import recommend from './pages/basics/recommend.vue'
Vue.component('recommend',recommend)

import moniter from './pages/basics/moniter.vue'
Vue.component('moniter',moniter)

import bottoms from'./pages/closet/bottoms.vue'
Vue.component('bottoms',bottoms)

import shoes from'./pages/closet/shoes.vue'
Vue.component('shoes',shoes)

import suit from'./pages/closet/suit.vue'
Vue.component('suit',suit)

import upper from'./pages/closet/upper.vue'
Vue.component('upper',upper)

import dayrecommend from'./pages/recommend/dayrecommend.vue'
Vue.component('dayrecommend',dayrecommend)

import travelrecommend from'./pages/recommend/travelrecommend.vue'
Vue.component('travelrecommend',travelrecommend)

import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom',cuCustom)

//使用mint-ui
//import MintUI from 'mint-ui'
//import 'mint-ui/lib/style.css'
//Vue.use(MintUI)

//引入Popup组件
//import { Popup } from 'mint-ui'
//Vue.component(Popup.name, Popup)

Vue.config.productionTip = false
Vue.prototype.$store = store
App.mpType = 'app'


const app = new Vue({
    ...App
})
app.$mount()
