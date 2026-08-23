package dao;

import model.Livro;
import repository.Listavel;
import repository.estaticas.lista.ListaEstatica;

import java.time.LocalDate;

public class LivroDAOListaEstatica implements LivroDAO {

    private Listavel listaDeLivros;

    public LivroDAOListaEstatica() {
        this(100);
    }

    public LivroDAOListaEstatica(int capacidade) {
        this.listaDeLivros = new ListaEstatica(capacidade);
    }

    // =========================================================================
    // OPERAÇÕES BÁSICAS CRUD
    // =========================================================================

    @Override
    public void addLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        listaDeLivros.anexar(livro);
    }

    @Override
    public Livro getLivroPorId(long id) {
        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (atual.getId() == id) {
                return atual;
            }
        }
        return null;
    }

    @Override
    public Livro[] getAllLivros() {
        int tamanho = listaDeLivros.tamanho();
        Livro[] arrayDeRetorno = new Livro[tamanho];

        for (int i = 0; i < tamanho; i++) {
            arrayDeRetorno[i] = (Livro) listaDeLivros.selecionar(i);
        }

        return arrayDeRetorno;
    }

    @Override
    public void updateLivro(Livro newLivro) {
        if (newLivro == null || listaDeLivros.estaVazia()) {
            return;
        }

        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (atual.getId() == newLivro.getId()) {
                listaDeLivros.atualizar(newLivro, i);
                break;
            }
        }
    }

    @Override
    public Livro deleteLivro(long id) {
        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro livroAux = (Livro) listaDeLivros.selecionar(i);
            if (livroAux.getId() == id) {
                return (Livro) listaDeLivros.apagar(i);
            }
        }
        return null;
    }

    // =========================================================================
    // OPERAÇÕES DE CONSULTA ESPECÍFICAS
    // =========================================================================

    @Override
    public Livro[] getLivrosPorAutor(String autor) {
        Listavel resultados = new ListaEstatica(100);

        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (autor != null && atual.getAutor().equalsIgnoreCase(autor)) {
                resultados.anexar(atual);
            }
        }

        Livro[] arrayRetorno = new Livro[resultados.tamanho()];
        for (int i = 0; i < resultados.tamanho(); i++) {
            arrayRetorno[i] = (Livro) resultados.selecionar(i);
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao) {
        Listavel resultados = new ListaEstatica(100);

        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (dataPublicacao != null && dataPublicacao.equals(atual.getDataPublicacao())) {
                resultados.anexar(atual);
            }
        }

        Livro[] arrayRetorno = new Livro[resultados.tamanho()];
        for (int i = 0; i < resultados.tamanho(); i++) {
            arrayRetorno[i] = (Livro) resultados.selecionar(i);
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] getLivrosPorTitulo(String titulo) {
        Listavel resultados = new ListaEstatica(100);

        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (titulo != null && atual.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.anexar(atual);
            }
        }

        Livro[] arrayRetorno = new Livro[resultados.tamanho()];
        for (int i = 0; i < resultados.tamanho(); i++) {
            arrayRetorno[i] = (Livro) resultados.selecionar(i);
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] deleteLivrosPorTitulo(String titulo) {
        Listavel listaRemovidos = new ListaEstatica(100);

        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro selecionado = (Livro) listaDeLivros.selecionar(i);

            if (titulo != null && selecionado.getTitulo().equalsIgnoreCase(titulo)) {
                listaRemovidos.anexar(listaDeLivros.apagar(i));
                i--; // Decrementa o índice pois a lista encolheu após a remoção
            }
        }

        Livro[] livrosRemovidos = new Livro[listaRemovidos.tamanho()];
        for (int i = 0; i < listaRemovidos.tamanho(); i++) {
            livrosRemovidos[i] = (Livro) listaRemovidos.selecionar(i);
        }

        return livrosRemovidos;
    }

    @Override
    public Livro getLivroPorIsbn(String isbn) {
        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (isbn != null && isbn.equalsIgnoreCase(atual.getIsbn())) {
                return atual;
            }
        }
        return null;
    }

    @Override
    public Livro[] getLivrosPorPrecoRange(double minPreco, double maxPreco) {
        Listavel resultados = new ListaEstatica(100);

        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (atual.getPreco() >= minPreco && atual.getPreco() <= maxPreco) {
                resultados.anexar(atual);
            }
        }

        Livro[] arrayRetorno = new Livro[resultados.tamanho()];
        for (int i = 0; i < resultados.tamanho(); i++) {
            arrayRetorno[i] = (Livro) resultados.selecionar(i);
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] getLivrosPorDataRange(LocalDate minDate, LocalDate maxDate) {
        Listavel resultados = new ListaEstatica(100);

        for (int i = 0; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            LocalDate data = atual.getDataPublicacao();

            boolean noIntervalo = (data != null) &&
                    (!data.isBefore(minDate)) &&
                    (!data.isAfter(maxDate));

            if (noIntervalo) {
                resultados.anexar(atual);
            }
        }

        Livro[] arrayRetorno = new Livro[resultados.tamanho()];
        for (int i = 0; i < resultados.tamanho(); i++) {
            arrayRetorno[i] = (Livro) resultados.selecionar(i);
        }

        return arrayRetorno;
    }

    // =========================================================================
    // OPERAÇÕES DE ANÁLISE E ESTATÍSTICAS
    // =========================================================================

    @Override
    public Livro getLivroMaisCaro() {
        if (listaDeLivros.estaVazia()) {
            return null;
        }

        Livro livroMaisCaro = (Livro) listaDeLivros.selecionar(0);
        for (int i = 1; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (atual.getPreco() > livroMaisCaro.getPreco()) {
                livroMaisCaro = atual;
            }
        }

        return livroMaisCaro;
    }

    @Override
    public Livro getLivroMaisBarato() {
        if (listaDeLivros.estaVazia()) {
            return null;
        }

        Livro livroMaisBarato = (Livro) listaDeLivros.selecionar(0);
        for (int i = 1; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (atual.getPreco() < livroMaisBarato.getPreco()) {
                livroMaisBarato = atual;
            }
        }

        return livroMaisBarato;
    }

    @Override
    public Livro getLivroMaisNovo() {
        if (listaDeLivros.estaVazia()) {
            return null;
        }

        Livro maisNovo = (Livro) listaDeLivros.selecionar(0);
        for (int i = 1; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (atual.getDataPublicacao() != null && maisNovo.getDataPublicacao() != null) {
                if (atual.getDataPublicacao().isAfter(maisNovo.getDataPublicacao())) {
                    maisNovo = atual;
                }
            }
        }

        return maisNovo;
    }

    @Override
    public Livro getLivroMaisAntigo() {
        if (listaDeLivros.estaVazia()) {
            return null;
        }

        Livro maisAntigo = (Livro) listaDeLivros.selecionar(0);
        for (int i = 1; i < listaDeLivros.tamanho(); i++) {
            Livro atual = (Livro) listaDeLivros.selecionar(i);
            if (atual.getDataPublicacao() != null && maisAntigo.getDataPublicacao() != null) {
                if (atual.getDataPublicacao().isBefore(maisAntigo.getDataPublicacao())) {
                    maisAntigo = atual;
                }
            }
        }

        return maisAntigo;
    }

    // =========================================================================
    // OPERAÇÕES DE RELATÓRIO E ESTATÍSTICAS
    // =========================================================================

    @Override
    public String printLivros() {
        return listaDeLivros.imprimir();
    }

    @Override
    public int getTotalLivros() {
        return listaDeLivros.tamanho();
    }

    @Override
    public double getPrecoMedio() {
        if (listaDeLivros.estaVazia()) {
            return 0.0;
        }

        double somaPrecos = 0.0;
        int total = listaDeLivros.tamanho();

        for (int i = 0; i < total; i++) {
            Livro livro = (Livro) listaDeLivros.selecionar(i);
            somaPrecos += livro.getPreco();
        }

        return somaPrecos / total;
    }

    // =========================================================================
    // OPERAÇÕES DE GERENCIAMENTO E BACKUP
    // =========================================================================

    @Override
    public boolean isLivroDisponivel(long id) {
        return getLivroPorId(id) != null;
    }

    @Override
    public void clearAllLivros() {
        listaDeLivros.limpar();
    }
}