import axios from 'axios';
import { checkResponse } from '../helpers/session'

export const httpGet = async (url, params) => {
  try {
    const response = await axios.get(url, {params: params});
      console.log(response.statusText);
      if (response && checkResponse(response)) {
          return response.data
      } else {
          throw new Error('httpGet not ok')
      }
  } catch (err) {
      console.warn('httpGet error ', err);
      throw new Error(err)
  }
};

export const httpPostForm = async (url, params) => {
  try {
    const response = await axios.post(url, params);
      console.log(response.statusText);
      if (response && checkResponse(response)) {
          return response.data
      } else {
          throw new Error('httpPostForm not ok')
      }
  } catch (error) {
      if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
      } else if (error.request) {
          // The request was made but no response was received
          // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          console.log(error.request);
      } else {
          // Something happened in setting up the request that triggered an Error
          console.log('Error', error.message);
      }
      throw error
  }
};
