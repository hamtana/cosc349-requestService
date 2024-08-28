/* global Vue, axios */
var requestsApi = '/api/requests';
var requestApiTenant = ({username}) => `/api/requests/tenant/${username}`;

const app = Vue.createApp({
    data() {
        return {
            requests: []
        };
    },

    computed: {
        // Map Vuex state to get the tenant from the data store
        ...Vuex.mapState({
            tenant: state => state.tenant
        })
    },

    mounted() {
        // Call the method to get requests by tenant
        this.getRequestByTenant();
    },

    methods: {
        // Function to get requests by tenant username
        getRequestByTenant() {
            if (this.tenant && this.tenant.username) {
                // Send GET request
                axios.get(requestApiTenant({ 'username': this.tenant.username }))
                    .then(response => {
                        // Store response in requests model
                        this.requests = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
            } else {
                console.error("No tenant is signed in.");
            }
        },

        updateRequest(request) {
            dataStore.commit("selectRequest", request);
            window.location = "update-request.html";
        }
    },

    // Other modules
    mixins: [NumberFormatter]
});

// other component imports go here

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import data store
import { dataStore } from './data-store.js';
app.use(dataStore);

import { NumberFormatter } from './number-formatter.js';

// mount the page - this needs to be the last line in the file
app.mount("main");
