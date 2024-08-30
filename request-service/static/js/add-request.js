/* global Vue, axios */
var requestApi = '/api/requests';

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            request: new Object()
        };
    },

    mounted() {
        // semicolon separated statements


    },

    methods: {
        // comma separated function declarations
        addRequest() {
            axios.post(customersApi, this.request)
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
