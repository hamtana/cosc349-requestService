/* Global Vue, axios */
var tenantApi = '/api/tenants';

const app = Vue.createApp({

    data() {
        return {
            // models map (comma seperated key/values pairs)
            tenant : new Object()
        };


    },

    mounted(){
        //for testing
        alert('Mounted method called - testing');
    },

    methods: {
        //Comma seperated function declarations
        addTenant() {
            axios.post('/api/tenants', this.tenant)
                .then(response => {
                    window.location.href = 'view-requests.html';
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },
        //Other modules
        mixins: []



});

// other component imports go here
import { navigationMenu } from './navigation-menu.js';
app.component('navigation-menu', navigationMenu);

import { dataStore } from './data-store.js';
app.use(dataStore);

// mount this page, needs to be at the bottom of the file
app.mount("main");

