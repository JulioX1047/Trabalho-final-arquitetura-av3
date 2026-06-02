const CHAR_URL = 'http://localhost:8080/personagens';

async function criarPersonagem() {
    const nome = document.getElementById('nome').value;
    const classe = document.getElementById('classe').value;
    const usuarioId = document.getElementById('usuarioId').value;
    const mensagem = document.getElementById('mensagem');

    try {
        const response = await fetch(CHAR_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome, classe, usuarioId: parseInt(usuarioId) })
        });

        if (response.ok) {
            const data = await response.json();
            mensagem.textContent = `Personagem ${data.nome} criado com sucesso!`;
            listarPersonagens(usuarioId);
        } else {
            mensagem.style.color = '#e94560';
            mensagem.textContent = 'Erro ao criar personagem!';
        }
    } catch (error) {
        mensagem.style.color = '#e94560';
        mensagem.textContent = 'Erro ao conectar com o servidor!';
    }
}

async function listarPersonagens(usuarioId = 1) {
    try {
        const response = await fetch(`${CHAR_URL}/usuario/${usuarioId}`);
        const personagens = await response.json();
        const lista = document.getElementById('lista-personagens');

        if (personagens.length === 0) {
            lista.innerHTML = '<p>Nenhum personagem encontrado.</p>';
            return;
        }

        lista.innerHTML = personagens.map(p => `
            <div class="personagem-card">
                <strong>${p.nome}</strong> — ${p.classe} | Nível ${p.nivel}
                <div class="stats">
                    <span class="stat">❤️ HP: ${p.hp}</span>
                    <span class="stat">💙 MP: ${p.mp}</span>
                    <span class="stat">⚔️ Força: ${p.forca}</span>
                    <span class="stat">🛡️ Defesa: ${p.defesa}</span>
                </div>
                <div style="margin-top: 10px;">
                    <button class="btn-batalha" onclick="irParaBatalha(${p.id}, ${p.hp}, ${p.forca})">
                        ⚔️ Batalhar
                    </button>
                    <button style="background: #27ae60; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; margin-left: 5px;" onclick="usarPocao(${p.id})">
                        🧪 Usar Poção
                    </button>
                </div>
            </div>
        `).join('');
    } catch (error) {
        console.error('Erro ao listar personagens:', error);
    }
}

function irParaBatalha(personagemId, hp, forca) {
    localStorage.setItem('personagemId', personagemId);
    localStorage.setItem('hpPersonagem', hp);
    localStorage.setItem('forca', forca);
    window.location.href = 'batalha.html';
}

// NOVA FUNÇÃO PARA A POÇÃO ADICIONADA AQUI
async function usarPocao(id) {
    try {
        const response = await fetch(`${CHAR_URL}/${id}/usar-pocao`, { 
            method: 'POST' 
        });
        
        if (response.ok) {
            alert('Poção usada! +30 HP recuperado.');
            listarPersonagens(); // Atualiza a lista automaticamente na tela para mostrar o novo HP
        } else {
            alert('Erro ao usar a poção!');
        }
    } catch (error) {
        alert('Erro ao conectar com o servidor!');
    }
}

// Carrega personagens ao abrir a página
listarPersonagens();