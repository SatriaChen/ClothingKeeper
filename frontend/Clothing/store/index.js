import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state={
	user_address:{
		province:'',
		city:'',
		district:''				
	},
	clothesResponse: null,
	travelRecommend:null,
	centigrade:null,
	gaomao:''
	
}

const mutations={
	commit_address(state,data){
		state.user_address=data;
	},
	get_clothesResponse(state, data){
		state.clothesResponse = data;
	},
	
	get_travelRecommend(state, data){
		state.travelRecommend = data;
	},
	get_centigrade(state, data) {
		state.centigrade = data;
	},
	get_ganmao(state, data) {
		state.gaomao = data;
	}
}
export default new Vuex.Store({
	state:state,
	mutations:mutations
})