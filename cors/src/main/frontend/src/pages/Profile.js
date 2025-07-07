import React, { useEffect, useState } from 'react';
import api, { logout } from '../api/api';
import { useNavigate } from 'react-router-dom';

function Profile() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    api.get('/user/me')
      .then(res => setUser(res.data))
      .catch(() => {
        alert('로그인이 필요합니다.');
        navigate('/login');
      });
  }, [navigate]);

  const handleLogout = async () => {
    await logout();
    navigate('/login');
  };

  if (!user) return <div>로딩중...</div>;

  return (
    <div>
      <div>이메일: {user.email}</div>
      <div>이름: {user.name}</div>
      <div>권한: {user.role}</div>
      <button onClick={handleLogout}>로그아웃</button>
    </div>
  );
}

export default Profile;