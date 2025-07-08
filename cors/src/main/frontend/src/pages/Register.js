import React, { useState } from 'react';
import { registerUser } from '../api/api';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const navigate = useNavigate();

  const handleRegister = async e => {
    e.preventDefault();
    try {
      await registerUser(email, password, name);
      alert('회원가입 성공! 로그인 해주세요.');
      navigate('/login');
    } catch {
      alert('회원가입 실패');
    }
  };

  return (
    <form onSubmit={handleRegister}>
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="이메일" />
      <input value={name} onChange={e => setName(e.target.value)} placeholder="이름" />
      <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="비밀번호" />
      <button type="submit">회원가입</button>
    </form>
  );
}

export default Register;