import axios from 'axios';

const HtppRequest = axios.create({
    baseURL: 'http://localhost:8080'
});

export default HtppRequest;