/* global Vue, axios */
var requestApi = '/api/requests';

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            request: new Object()
        };
    },

        computed: {
            // Map Vuex state to get the tenant from the data store
            ...Vuex.mapState({
                tenant: state => state.tenant
            })
        },

    mounted() {
        // semicolon separated statements
        if (this.tenant) {
            this.request.tenant = this.tenant;
        }


    },

    methods: {
        // comma separated function declarations
        addRequest() {
        // Ensure that urgent and completed are always boolean values
            this.request.urgent = !!this.request.urgent;
            this.request.completed = !!this.request.completed;

            axios.post(requestApi, this.request, this.tenant)
                    .then(() => {
                        window.location = 'view-requests.html';
                    })
                    .catch(error => {
                        alert(error.response.data.message);
                    });
        }

    },

    // other modules
    mixins:[]

});

// other component imports go here



// import navigation  menu component
import { navigationMenu } from './navigation-menu.js';
app.component('navmenu', navigationMenu);

import { dataStore } from './data-store.js';
app.use(dataStore);


// mount the page - this needs to be the last line in the file
app.mount("main");
