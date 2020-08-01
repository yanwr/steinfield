import React from 'react'
import HtppRequest from './shared/httpRequest/HttpRequest';
import { Logger } from './shared/log/Logger';
import { Provider } from 'react-redux';
import { store } from './shared/redux/store';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Routes from './routes';

const Log = new Logger("App.js");

const startTime = new Date();
const UUID = new Date().getTime();
HtppRequest.interceptors.request.use( function(config) {
  const { baseURL, url, data, headers } = config;
  const finalURL = baseURL + url;
  const currentBody = data || {};
  const { userState } = store.getState();
  if(userState.user && userState.user.access_token){
    headers.Authorization = userState.user.access_token;
  }
  Log.info(`${UUID} - Do request ${finalURL} with body`, currentBody);
  return config;
}, function(error){
  const { baseURL, url, data } = error;
  const finalURL = baseURL + url;
  const currentBody = data || {};
  Log.info(`${UUID} - Fail request to ${finalURL} with body`, currentBody);
  return Promise.reject(error);
});

HtppRequest.interceptors.response.use(function(res) {
  const { data, config } = res;
  const currentData = data || {};
  const { baseURL, url } = config;
  const finalURL = baseURL + url;
  Log.info(`${UUID} - Request to ${finalURL} DONE in ${new Date() - startTime}ms with data`, currentData);
  return res;
}, function(error) {
  const { config, data } = error.response;
  const { baseURL, url } = config;
  const currentData = data || {};
  const finalURL = baseURL + url;
  Log.info(`${UUID} - Fail response to ${finalURL} with data`, currentData);
  return Promise.reject(error);
});

export default () => (
    <Provider store={store}>
      <Routes />
    </Provider>
)
