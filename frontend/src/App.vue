<template>
<div class="app" id="appContainer">
    <Header />
    <router-view/>
</div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";


export default {
	name: "App",
	components: {
		Header,
	},
};
</script>
<style>
    .app{
        font-family: 'Quicksand', sans-serif;   
    }
    .app input:focus{
        outline: none !important;
    }
    ::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
        color: white;
        opacity: 0.5; /* Firefox */
    }

    :-ms-input-placeholder { /* Internet Explorer 10-11 */
        color: white;
        opacity: 0.5; /* Firefox */
    }

    ::-ms-input-placeholder { /* Microsoft Edge */
        color: white;
        opacity: 0.5; /* Firefox */
    }

</style>