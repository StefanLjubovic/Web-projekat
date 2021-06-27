<template>
  <div class="map-container">
    <div ref="map-root" class="map">
    </div>
  </div>
</template>
<script>
  import View from 'ol/View'
  import Map from 'ol/Map'
  import TileLayer from 'ol/layer/Tile'
  import OSM from 'ol/source/OSM'

  import 'ol/ol.css'

  export default {
    name: 'MapContainer',
    mounted() {
      new Map({
        target: this.$refs['map-root'],
        layers: [
          new TileLayer({
            source: new OSM() // tiles are served by OpenStreetMap
          }),
        ],
        view: new View({
            projection: 'EPSG:4326',
            zoom: 17,
            center: [19.846654, 45.254451],
          constrainResolution: true
        }),
      })
    }
  }
</script>

<style scoped>
    .map{
        /* width: 100vw;  */
        height: 100%;
        margin-top: -210px;
        padding-top:  -210px;
    }
</style>