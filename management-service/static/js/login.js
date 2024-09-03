/* global Vue, axios */
var managerApi = '/api/managers';
var managerApiSignIn = ({username}) => `/api/managers/${username}`;

const app = Vue.createApp({

    data() {
        return{
            //models map (comma seperated key/value pairs)
            manager : new Object(),
            message: "Please enter your details"
        };


    },

    mounted() {
    },

    methods: {
        managerLogin(username) {
            axios.get(managerApiSignIn({'username' : this.manager.username}))
            .then(response => {
                this.manager = response.data;
                dataStore.commit("signIn", this.manager);
                window.location = 'view-requests.html';
                this.message = "Welcome " + username;
            })
            .catch(error => {
                this.message = "You have entered invalid credentials";
            });
        }
    },

    //other modules
    mixins: []

});

///Import navigation menu component
import {navigationMenu} from './navigation-menu.js';
app.component('navmenu', navigationMenu);

//import data store
import {dataStore} from './data-store.js';
app.use(dataStore);

app.mount("main");