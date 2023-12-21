import axios from 'axios'

const $request = axios.create({
    timeout: 5000
});

$request.interceptors.request.use(
    config => {
        config.headers['Content-Type'] = 'application/json;charset=utf-8';
        config.headers['token'] = localStorage.getItem('token');
        return config;
    }, error => {
        return Promise.reject(error);
    }
);

export default $request;