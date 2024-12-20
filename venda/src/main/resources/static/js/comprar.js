let jogos = [];

// Carregar jogos do backend
fetch('/jogos')
    .then(response => response.json())
    .then(data => {
        jogos = data;
        preencherCarousel();
        preencherGrid();
    });

function preencherCarousel() {
    const carouselContent = document.getElementById('carousel-content');
    jogos.slice(0, 5).forEach((jogo, index) => {
        const active = index === 0 ? 'active' : '';
        carouselContent.innerHTML += `
            <div class="carousel-item ${active}">
                <img src="data:image/jpeg;base64,${jogo.imagem}" class="d-block w-100 carousel-img" alt="${jogo.nome}">
                <div class="carousel-caption">
                    <h5>${jogo.nome}</h5>
                    <p>R$ ${jogo.preco.toFixed(2)}</p>
                </div>
            </div>
        `;
    });
}

function preencherGrid() {
    const jogosContainer = document.getElementById('jogos-container');
    jogos.forEach(jogo => {
        jogosContainer.innerHTML += `
            <div class="col-md-3 mb-4">
                <div class="card jogo-card" onclick="mostrarModal(${jogo.idJogo})">
                    <img src="data:image/jpeg;base64,${jogo.imagem}" class="card-img-top" alt="${jogo.nome}">
                    <div class="card-body">
                        <h5 class="card-title">${jogo.nome}</h5>
                        <p class="card-text">R$ ${jogo.preco.toFixed(2)}</p>
                    </div>
                </div>
            </div>
        `;
    });
}

function mostrarModal(idJogo) {
    const jogo = jogos.find(j => j.idJogo === idJogo);
    const modalContent = document.getElementById('modal-content');
    modalContent.innerHTML = `
        <img src="data:image/jpeg;base64,${jogo.imagem}" class="img-fluid mb-3" alt="${jogo.nome}">
        <h5>${jogo.nome}</h5>
        <p>${jogo.descricao}</p>
        <p>R$ ${jogo.preco.toFixed(2)}</p>
    `;
    document.getElementById('adicionarCarrinho').onclick = () => adicionarCarrinho(jogo.idJogo);
    const modal = new bootstrap.Modal(document.getElementById('jogoModal'));
    modal.show();
}

function adicionarCarrinho(idJogo) {
    fetch('/carrinho/adicionar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `jogoId=${idJogo}`
    })
    .then(response => {
        if (response.ok) {
            alert('Jogo adicionado ao carrinho!');
        } else {
            alert('Erro ao adicionar o jogo. Tente novamente.');
        }
    });
}