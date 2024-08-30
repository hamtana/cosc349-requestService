"use strict";

export const navigationMenu = {

	computed: {
		signedIn() {
			return this.tenant != null;
		},
		...Vuex.mapState({
			tenant: 'tenant'
		})
	},

	template:
	`
	<nav>
		<div id="welcome" v-if="signedIn">Welcome {{tenant.firstName}}</div>
		<a href=".">Home</a>
		<a href="view-requests.html" v-if="signedIn">Browse Requests</a>
		<a href="#" v-if="signedIn" @click="signOut()">Sign Out</a>
		<a href="tenant-login.html" v-if="!signedIn">Sign In</a>
        <a href="add-tenant.html" v-if="!signedIn">Create Tenant</a>
	</nav>
	`,

	methods:{
		signOut() {
			sessionStorage.clear();
			window.location = '.';
		}
	}
};