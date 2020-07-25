import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/promoter/list',
    method: 'get',
    params: query
  })
}

export function listAddress(query) {
  return request({
    url: '/address/list',
    method: 'get',
    params: query
  })
}

export function listCollect(query) {
  return request({
    url: '/collect/list',
    method: 'get',
    params: query
  })
}

export function listFeedback(query) {
  return request({
    url: '/feedback/list',
    method: 'get',
    params: query
  })
}

export function listFootprint(query) {
  return request({
    url: '/footprint/list',
    method: 'get',
    params: query
  })
}

export function listHistory(query) {
  return request({
    url: '/history/list',
    method: 'get',
    params: query
  })
}

export function updatePromoter(data) {
  return request({
    url: '/promoter/update',
    method: 'post',
    data
  })
}

export function createPromoter(data) {
  return request({
    url: '/promoter/create',
    method: 'post',
    data
  })
}

