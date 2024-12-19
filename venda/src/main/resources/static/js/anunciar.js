function previewImage(event) {
    const reader = new FileReader();
    reader.onload = function () {
        const preview = document.getElementById('preview');
        preview.src = reader.result;
        preview.style.display = 'block';
    };
    reader.readAsDataURL(event.target.files[0]);
}

function showAnunciar() {
    document.getElementById("section-anunciar").classList.remove("d-none");
    document.getElementById("section-anunciar").classList.add("d-block");
    document.getElementById("section-listar").classList.remove("d-block");
    document.getElementById("section-listar").classList.add("d-none");
}

function showListar() {
    document.getElementById("section-listar").classList.remove("d-none");
    document.getElementById("section-listar").classList.add("d-block");
    document.getElementById("section-anunciar").classList.remove("d-block");
    document.getElementById("section-anunciar").classList.add("d-none");
}

function editarJogo(id) {
    fetch(`/editar/${id}`)
        .then(response => response.json())
        .then(jogo => {
            document.getElementById('editId').value = jogo.idJogo;
            document.getElementById('editNome').value = jogo.nome;
            document.getElementById('editDescricao').value = jogo.descricao;
            document.getElementById('editDataLancamento').value = jogo.dataLancamento;
            document.getElementById('editClassificacaoIndicativa').value = jogo.classificacaoIndicativa;
            document.getElementById('editPreco').value = jogo.preco;
        })
        .catch(error => console.error('Erro ao carregar jogo para edição:', error));
}
