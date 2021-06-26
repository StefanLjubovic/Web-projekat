<template>
  <div ref="map-root" class="map">
  </div>
</template>
<script>
  import View from 'ol/View'
  import Map from 'ol/Map'
  import TileLayer from 'ol/layer/Tile'
  import OSM from 'ol/source/OSM'

  // importing the OpenLayers stylesheet is required for having
  // good looking buttons!
  import 'ol/ol.css'

  export default {
    name: 'MapContainer',
    components: {},
    props: {},
    mounted() {
      // this is where we create the OpenLayers map
      new Map({
        // the map will be created using the 'map-root' ref
        target: this.$refs['map-root'],
        layers: [
          // adding a background tiled layer
          new TileLayer({
            source: new OSM() // tiles are served by OpenStreetMap
          }),
        ],
        // martke

        // the map view will initially show the whole world
        view: new View({
            projection: 'EPSG:4326',
            zoom: 17,
            center: [19.846654, 45.254451],
        //   constrainResolution: true
        }),
      })
    },
  }
</script>

<style scoped>
    .map{
        width: 100%; 
        height: calc(100% - 147px);
        margin-top: -210px;
    }
</style>