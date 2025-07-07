import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // 백엔드 주소
  headers: { 'Content-Type': 'application/json' }
});

// JWT 토큰 자동 주입
api.interceptors.request.use(config => {
  const token = localStorage.getItem('accessToken');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

export default api;

// 엔드포인트별 함수 예시
export async function login(email, password) {
  const res = await api.post('/auth/login', { email, password });
  localStorage.setItem('accessToken', res.data.accessToken);
  localStorage.setItem('refreshToken', res.data.refreshToken);
  localStorage.setItem('email', res.data.loginId);
  return res.data;
}

export async function registerUser(email, password, name) {
  const res = await api.post('/auth/register/user', { email, password, name });
  return res.data;
}

export async function logout() {
  await api.post('/auth/logout');
  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  localStorage.removeItem('email');
}