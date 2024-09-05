/* global Vue, axios */
var propertiesApi = '/api/properties';
var managerApi = (username) => `/api/managers/${username}`;

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            property : new Object(),
        };
    },

        computed: {
            // Map Vuex state to get the tenant from the data store
            ...Vuex.mapState({
                manager: state => state.manager
            })
        },

    mounted() {
        // semicolon separated statements
        if (this.manager) {
            this.property.manager = this.manager;
        }
    },

    methods: {
        // comma separated function declarations

        async addProperty() {
         // Make sure manager and property details are available
             if (this.manager && this.manager.username) {
                   this.property.manager = this.manager;  // Set tenant's username
             } else {
                  alert("Manager is not available");
                  return;
             }

            axios.post(propertiesApi, this.property)
                    .then(() => {
                        window.location = 'view-properties.html';
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
