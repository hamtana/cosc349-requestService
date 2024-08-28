/* global Vue, axios */
var tenantApi = '/api/tenants';
var tenantApiSignIn = ({username}) => `/api/tenants/${username}`;

const app = Vue.createApp({

    data() {
        return{
            //models map (comma seperated key/value pairs)
            tenant : new Object(),
            message: "Please enter your details"
        };


    },

    mounted() {
        alert('Mounted method called - testing');
    },

    methods: {
        tenantLogin(username) {
            axios.get(tenantApiSignIn({'username' : this.tenant.username}))
            .then(response => {
                this.tenant = response.data;
                dataStore.commit("signIn", this.tenant);
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