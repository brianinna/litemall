<!--
  描述：拖放地图组件，默认尺寸是 500 * 300

  接收属性参数：
    lat: 纬度
    lng: 经度

  自定义事件：
    drag: 拖放完成事件

  示例：
    <mapDrag @drag="dragMap" lat="22.574405" lng="114.095388"></mapDrag>
-->
<template>
  <div class="m-map">
    <div class="search" v-if="placeSearch">
      <input type="text" placeholder="请输入关键字" v-model="searchKey" />
      <button type="button" @click="handleSearch">搜索</button>
      <div id="js-result" v-show="searchKey" class="result"></div>
    </div>
    <div id="js-container" class="map">正在加载数据 ...</div>
  </div>
</template>

<script>
import remoteLoad from '@/utils/remoteLoad'
export default {
  props: ['lat', 'lng'],
  data() {
    return {
      searchKey: '',
      placeSearch: null,
      dragStatus: false,
      AMapUI: null,
      AMap: null
    }
  },
  watch: {
    searchKey() {
      if (this.searchKey === '') {
        this.placeSearch.clear()
      }
    }
  },
  methods: {
    // 搜索
    handleSearch() {
      if (this.searchKey) {
        this.placeSearch.search(this.searchKey)
      }
    },
    // 实例化地图
    initMap() {




      // 加载PositionPicker，loadUI的路径参数为模块名中 'ui/' 之后的部分
      let AMapUI = this.AMapUI = window.AMapUI
      let AMap = this.AMap = window.AMap



      AMapUI.loadUI(['misc/PositionPicker'], PositionPicker => {
        let mapConfig = {
          zoom: 16,
          cityName: '郑州'
        }

        console.log('create map this latlng ', this.lat, this.lng)

        if (this.lat && this.lng) {
          mapConfig.center = [this.lng, this.lat]
        }
        let map = new AMap.Map('js-container', mapConfig)
        // 加载地图搜索插件
        AMap.service('AMap.PlaceSearch', () => {
          this.placeSearch = new AMap.PlaceSearch({
            pageSize: 5,
            pageIndex: 1,
            citylimit: true,
            city: '郑州',
            map: map,
            panel: 'js-result'
          })
        })
        // 启用工具条
        AMap.plugin(['AMap.ToolBar'], function () {
          map.addControl(new AMap.ToolBar({
            position: 'RB'
          }))
        })
        AMap.plugin('AMap.Geolocation', function () {
          var geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：5s
            buttonPosition: 'RB',    //定位按钮的停靠位置
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,   //定位成功后是否自动调整地图视野到定位点

          });
          map.addControl(geolocation);
          geolocation.getCurrentPosition(function (status, result) {
            if (status == 'complete') {
              console.log('position is ', result.position)
            /*   this.lat = result.position.substring(0, result.position.indexOf(","))
              this.lng = result.position.substring(result.position.indexOf(",") + 1, result.position.length)
              console.log('this latlng ', this.lat, this.lng)
             */  var position = new AMap.LngLat(result.position.lat, result.position.lng);  // 标准写法
              // 简写 var position = [116, 39]; 
              map.setCenter(position);
            } else {
              alert(JSON.stringify(result));
              console.log(JSON.stringify(result))
              console.log('position error')

            }
          });
        });
        // 创建地图拖拽
        let positionPicker = new PositionPicker({
          mode: 'dragMap', // 设定为拖拽地图模式，可选'dragMap'、'dragMarker'，默认为'dragMap'
          map: map // 依赖地图对象
        })
        // 拖拽完成发送自定义 drag 事件
        positionPicker.on('success', positionResult => {
          // 过滤掉初始化地图后的第一次默认拖放
          if (!this.dragStatus) {
            this.dragStatus = true
          } else {
            this.$emit('drag', positionResult)
          }
        })
        // 启动拖放
        positionPicker.start()
      })
    }
  },
  async created() {
    // 已载入高德地图API，则直接初始化地图
    if (window.AMap && window.AMapUI) {
      this.initMap()
      // 未载入高德地图API，则先载入API再初始化
    } else {
      await remoteLoad(`https://webapi.amap.com/maps?v=1.4.15&key=0628686f4620797ee6a4f17a0c21fdc7`, false)
      await remoteLoad('https://webapi.amap.com/ui/1.0/main.js', false)
      this.initMap()
    }
  }
}
</script>

<style lang="css">
.m-map {
  min-width: 500px;
  min-height: 300px;
  position: relative;
}
.m-map .map {
  width: 100%;
  height: 500px;
}
.m-map .search {
  position: absolute;
  top: 10px;
  left: 10px;
  width: 285px;
  z-index: 1;
}
.m-map .search input {
  width: 180px;
  border: 1px solid #ccc;
  line-height: 20px;
  padding: 5px;
  outline: none;
}
.m-map .search button {
  line-height: 26px;
  background: #fff;
  border: 1px solid #ccc;
  width: 50px;
  text-align: center;
}
.m-map .result {
  max-height: 300px;
  overflow: auto;
  margin-top: 10px;
}
</style>
