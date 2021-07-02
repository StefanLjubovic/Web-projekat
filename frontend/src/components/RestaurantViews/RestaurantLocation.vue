<template>
	<div class="map-container">
		<div ref="map-root" class="map"></div>
	</div>
</template>
<script>
import View from 'ol/View';
import Map from 'ol/Map';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';

import 'ol/ol.css';

export default {
	props: ['restaurant'],
	mounted() {
		document.getElementById('appContainer').style.overflow = 'hidden';
		document.getElementById('appContainer').style.height = '100vh';
		const map = new Map({
			target: this.$refs['map-root'],
			layers: [
				new TileLayer({
					source: new OSM(), // tiles are served by OpenStreetMap
				}),
			],
			view: new View({
				projection: 'EPSG:4326',
				zoom: 17,
				center: [this.restaurant.location.longitude, this.restaurant.location.latitude],
				// constrainResolution: true
			}),
		});
	},
	unmounted() {
		console.log("Unmounging locations...");
		document.getElementById('appContainer').style.overflow = 'unset';
		document.getElementById('appContainer').style.height = 'unset';
	},
};
</script>

<style scoped>
.map {
	/* width: 100vw;  */
	height: 100%;
	margin-top: -188px;
}
</style>
