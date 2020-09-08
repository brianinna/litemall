import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/user/list',
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
export function updateAddrees(data) {
  return request({
    url: '/address/update',
    method: 'post',
    data
  })
}

export function addCredit(data) {
  return request({
    url: '/user/addCredit',
    method: 'post',
    data
  })
}

export function updateUserType(data) {
  return request({
    url: '/user/userType',
    method: 'post',
    data
  })
}
export function getCredit(query) {
  return request({
    url: '/user/credit',
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

