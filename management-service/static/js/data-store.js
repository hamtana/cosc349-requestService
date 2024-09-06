export const dataStore = Vuex.createStore({

    // Where we declare the state of the item we want to keep track of
    state() {
        return {
            // manager that is signed in
            manager: null,

            // The request that is being made/edited
            property: null,

        };
    },


    mutations: {
        // manager signs in
        signIn(state, manager) {
            state.manager = manager;
        },

        selectProperty(state, property) {
            state.property = property;
        }
    },

    // add session storage persistence
    plugins: [window.createPersistedState({ storage: window.sessionStorage })]

});
