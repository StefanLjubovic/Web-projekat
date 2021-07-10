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
import XYZSource from 'ol/source/XYZ';
import { fromLonLat, toLonLat } from 'ol/proj';
import 'ol/ol.css';
import { Icon, Style } from 'ol/style';
import { Modify } from 'ol/interaction';
import { Vector as VectorLayer } from 'ol/layer';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import VectorSource from 'ol/source/Vector';
import { Attribution } from 'ol/control';
import 'ol/control';
import LayerGroup from 'ol/layer/Group';

const initLayer = new TileLayer({
	source: new OSM({
		url: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
		// attributions: [ OSM.ATTRIBUTION ],
		maxZoom: 18,
	}),
});
var iconStyle = new Style({
	image: new Icon({
		anchor: [0.5, 62],
        // scale: 0.1,
		anchorXUnits: 'fraction',
		anchorYUnits: 'pixels',
		src: require('../../assets/location-pin.png'),
	}),
});

const createPoint = (points) => {
	console.log("Points: ",points);
    var iconFeature = new Feature({
		geometry: new Point(points),
		name: 'Null Island',
		population: 4000,
		rainfall: 500,
	});
	iconFeature.setStyle(iconStyle);
	var layer = new VectorLayer({
		source: new VectorSource({
			features: [iconFeature],
		}),
	});
    return layer
}
export default {
	props: ['restaurant'],
	mounted() {
		document.getElementById('appContainer').style.overflow = 'hidden';
		document.getElementById('appContainer').style.height = '100vh';
		console.log(this.restaurant);
		const target = this.$refs['map-root'];
		const layer = createPoint([this.restaurant.location.longitude, this.restaurant.location.latitude])
		const map = new Map({
			target: target,
			layers: [initLayer, layer],
			view: new View({
				center: [this.restaurant.location.longitude, this.restaurant.location.latitude],
				zoom: 18,
			}),
		});
	},
	unmounted() {
		document.getElementById('appContainer').style.overflow = 'unset';
		document.getElementById('appContainer').style.height = 'unset';
	},
};
</script>

<style scoped>
.map {
	/* width: 100vw;  */
	height: 100%;
	margin-top: -220px;
}
</style>
