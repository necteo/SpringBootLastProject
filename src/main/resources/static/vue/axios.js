// React
/*
		NodeJS SpringBoot
			|				|
		 React	 React
		------------------- MSA (JWT)
*/
const api = axios.create({
	baseURL: 'http://localhost:8080',
	timeout: 50000
})