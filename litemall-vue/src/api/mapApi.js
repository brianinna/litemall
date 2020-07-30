import request from '@/utils/request'

const locationUrl = '/ws/location/v1/ip'; //首页数据接口
export function getLocation(query) {
  return request({
    url: locationUrl,
    method: 'get',
    params: query
  })
}
