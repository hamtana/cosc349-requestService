"use strict";

export const navigationMenu = {

	computed: {
		signedIn() {
			return this.manager != null;
		},
		...Vuex.mapState({
			manager: 'manager'
		})
	},

	template:
	`
	<nav>
		<a href=".">Home</a>
		<a href="view-properties.html" v-if="signedIn">Browse Properties</a>
		<a href="#" v-if="signedIn" @click="signOut()">Sign Out</a>
		<a href="manager-login.html" v-if="!signedIn">Sign In</a>
        <a href="add-manager.html" v-if="!signedIn">Create Account</a>
        <div class="welcome" v-if="signedIn">Welcome {{manager.firstName}}</div>
	</nav>
	`,

	methods:{
		signOut() {
			sessionStorage.clear();
			window.location = '.';
		}
	}
};