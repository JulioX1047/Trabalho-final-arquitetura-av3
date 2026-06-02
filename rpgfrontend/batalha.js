const COMBAT_URL = 'http://localhost:8080/combate';

let batalhaId = null;
let hpMaxJogador = parseInt(localStorage.getItem('hpPersonagem')) || 100;
let hpMaxInimigo = 100;

async function iniciarBatalha() {
    const personagemId = localStorage.getItem('personagemId') || 1;
    const hpPersonagem = localStorage.getItem('hpPersonagem') || 100;
    const tipoMonstro = document.getElementById('tipoMonstro').value;

    batalhaId = null;
    hpMaxJogador = parseInt(hpPersonagem);
    document.getElementById('log').innerHTML = '<p>Log de batalha:</p>';
    document.getElementById('resultado').style.display = 'none';
    document.getElementById('botoes-ataque').style.display = 'block';
    document.getElementById('acoes').style.display = 'none';

    try {
        const response = await fetch(
            `${COMBAT_URL}/iniciar?personagemId=${personagemId}&hpPersonagem=${hpPersonagem}&tipoMonstro=${tipoMonstro}`,
            { method: 'POST' }
        );

        if (response.ok) {
            const data = await response.json();
            batalhaId = data.id;
            hpMaxInimigo = data.hpInimigo;
            document.getElementById('acoes').style.display = 'block';
            atualizarHp(data.hpPersonagem, data.hpInimigo);
            adicionarLog(`Batalha iniciada contra ${data.nomeInimigo}!`);
        }
    } catch (error) {
        alert('Erro ao conectar com o servidor!');
    }
}

async function atacar(tipoAtaque) {
    if (!batalhaId) return;
    const forca = localStorage.getItem('forca') || 15;

    try {
        const response = await fetch(
            `${COMBAT_URL}/atacar?forca=${forca}&defesaInimigo=5`,
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ batalhaId, tipoAtaque })
            }
        );

        if (response.ok) {
            const data = await response.json();

            const danoInimigo = Math.max(1, Math.floor(Math.random() * 15) + 5);
            const novoHpJogador = Math.max(0, data.hpPersonagemAtual - danoInimigo);

            atualizarHp(novoHpJogador, data.hpInimigoAtual);
            adicionarLog(`Ataque ${tipoAtaque} causou ${data.danoGivado} de dano!`);
            adicionarLog(`Inimigo contra-atacou causando ${danoInimigo} de dano!`);

            if (data.statusBatalha === 'VITORIA') {
                adicionarLog('VITORIA! Voce venceu a batalha!');
                mostrarResultado('vitoria', 'VITORIA! Voce venceu!');
            } else if (novoHpJogador <= 0) {
                adicionarLog('DERROTA! Voce foi derrotado...');
                mostrarResultado('derrota', 'DERROTA! Voce foi derrotado...');
            }
        }
    } catch (error) {
        alert('Erro ao conectar com o servidor!');
    }
}

async function fugir() {
    if (!batalhaId) return;
    try {
        const response = await fetch(`${COMBAT_URL}/fugir/${batalhaId}`, { method: 'POST' });
        if (response.ok) {
            adicionarLog('Voce fugiu da batalha!');
            mostrarResultado('fuga', 'Voce fugiu da batalha!');
        }
    } catch (error) {
        alert('Erro ao conectar com o servidor!');
    }
}

function atualizarHp(hpJogador, hpInimigo) {
    document.getElementById('hp-jogador').textContent = `HP: ${hpJogador}`;
    document.getElementById('hp-inimigo').textContent = `HP: ${hpInimigo}`;
    document.getElementById('hp-bar-jogador').style.width = `${(hpJogador / hpMaxJogador) * 100}%`;
    document.getElementById('hp-bar-inimigo').style.width = `${(hpInimigo / hpMaxInimigo) * 100}%`;
}

function adicionarLog(mensagem) {
    const log = document.getElementById('log');
    log.innerHTML += `<div class="log-item">${mensagem}</div>`;
    log.scrollTop = log.scrollHeight;
}

function mostrarResultado(tipo, texto) {
    const resultado = document.getElementById('resultado');
    resultado.className = tipo;
    resultado.style.display = 'block';
    document.getElementById('resultado-texto').textContent = texto;
    document.getElementById('botoes-ataque').style.display = 'none';
    batalhaId = null;
}

function novaBatalha() {
    document.getElementById('resultado').style.display = 'none';
    document.getElementById('botoes-ataque').style.display = 'block';
    document.getElementById('acoes').style.display = 'none';
    document.getElementById('log').innerHTML = '<p>Log de batalha:</p>';
}