/* Global Vue, axios */
var managerApi = '/api/managers';

const app = Vue.createApp({

    data() {
        return {
            // models map (comma seperated key/values pairs)
            manager : new Object()
        };


    },

    mounted(){
        //for testing
    },

    methods: {
        //Comma seperated function declarations
        addManager() {
            axios.post(managerApi, this.manager)
                .then(response => {
                    window.location.href = 'view-properties.html';
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

