export const dataStore = Vuex.createStore({

    // Where we declare the state of the item we want to keep track of
    state() {
        return {
            // manager that is signed in
            manager: null,

            // The request that is being made/edited
            request: null
        };
    },


    mutations: {
        // manager signs in
        signIn(state, manager) {
            state.manager = manager;
        },

        selectRequest(state, request) {
            state.request = request;
        }
    },

    // add session storage persistence
    plugins: [window.createPersistedState({ storage: window.sessionStorage })]

});
