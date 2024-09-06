/* global Vue, axios */
var propertiesApi = '/api/properties';
var propertiesApiManager = (username) => `/api/properties/manager/${username}`;

const app = Vue.createApp({
    data() {
        return {
            properties : [],
        };
    },

    computed: {
        // Map Vuex state to get the tenant from the data store
        ...Vuex.mapState({
            manager: state => state.manager
        })
    },

    mounted() {
        // call the method to get all properties for a particular manager
        this.getPropertiesByManager();
    },

    methods: {
        // Function to get properties by manager username
        getPropertiesByManager() {
            console.log(this.manager); // Check the manager object
            if (this.manager && this.manager.username) {
                // Send GET request
                axios.get(propertiesApiManager(this.manager.username))
                    .then(response => {
                        // Store response in requests model
                        // if the response contains no properties, redirect to the add-properties page
                        if (!Array.isArray(response.data)) {
                            window.location = "add-property.html";
                            alert("You have no properties. Please add a property.");
                            return ;
                        }
                        this.properties = response.data;
                        console.log(this.properties);

                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
            } else {
                console.error("No Manager is signed in.");
            }
        },

        manageProperty(property) {
            dataStore.commit("selectProperty", property);
            window.location = "manage-property.html";
        }
    },

    // Other modules
    mixins: []
});

// other component imports go here

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import data store
import { dataStore } from './data-store.js';
app.use(dataStore);

// mount the page - this needs to be the last line in the file
app.mount("main");
