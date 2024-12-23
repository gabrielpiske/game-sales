let jogos = [];

// Carregar jogos do backend
fetch('/jogos')
    .then(response => response.json())
    .then(data => {
        jogos = data;
        console.log(jogos);  // Verifique se os dados estão corretos no console
    });

// Mostrar o modal com as informações do jogo
function mostrarModal(idJogo) {
    // Encontrar o jogo no array de jogos
    const jogo = jogos.find(j => j.idJogo === idJogo);

    if (jogo) {
        // Preencher o conteúdo do modal com os dados do jogo
        const modalContent = document.getElementById('modal-content');
        modalContent.innerHTML = `
            <img src="data:image/jpeg;base64,${jogo.imagem}" class="img-fluid mb-3" alt="${jogo.nome}">
            <h5>${jogo.nome}</h5>
            <p>${jogo.descricao}</p>
            <p>R$ ${jogo.preco.toFixed(2)}</p>
        `;

        // Atualizar a ação do botão de "Adicionar ao Carrinho"
        document.getElementById('adicionarCarrinho').onclick = () => {
            adicionarCarrinho(jogo.idJogo);
        };

        // Inicializar o modal e exibi-lo
        const modal = new bootstrap.Modal(document.getElementById('jogoModal'));
        modal.show();
    }
}

// Função para adicionar o jogo ao carrinho
function adicionarCarrinho(idJogo) {
    fetch('/carrinho/adicionar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `jogoId=${idJogo}`
    })
    .then(response => {
        if (response.ok) {
            alert('Jogo adicionado ao carrinho!');
            atualizarQuantidadeCarrinho();

            const modalElement = document.getElementById('jogoModal');
            const modal = bootstrap.Modal.getInstance(modalElement);
            modal.hide();  // Fecha o modal
        } else {
            alert('Erro ao adicionar o jogo. Tente novamente.');
        }
    });
}

// Função para atualizar a quantidade de itens no carrinho
function atualizarQuantidadeCarrinho() {
    fetch('/carrinho/quantidade')
        .then(response => response.json())
        .then(data => {
            document.getElementById('cartCount').innerText = data.quantidade;
        });
}
