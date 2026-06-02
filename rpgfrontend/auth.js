 const AUTH_URL = 'http://localhost:8080/auth';

async function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const mensagem = document.getElementById('mensagem');

    try {
        const response = await fetch(`${AUTH_URL}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            const data = await response.json();
            localStorage.setItem('username', data.username);
            localStorage.setItem('role', data.role);
            mensagem.style.color = '#00ff00';
            mensagem.textContent = 'Login realizado! Redirecionando...';
            setTimeout(() => window.location.href = 'personagem.html', 1500);
        } else {
            mensagem.textContent = 'Usuário ou senha incorretos!';
        }
    } catch (error) {
        mensagem.textContent = 'Erro ao conectar com o servidor!';
    }
}

async function cadastrar() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const mensagem = document.getElementById('mensagem');

    try {
        const response = await fetch(`${AUTH_URL}/cadastrar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            mensagem.style.color = '#00ff00';
            mensagem.textContent = 'Cadastro realizado! Faça login.';
        } else {
            mensagem.textContent = 'Erro ao cadastrar!';
        }
    } catch (error) {
        mensagem.textContent = 'Erro ao conectar com o servidor!';
    }
}