package dao;
import model.Livro;
import repository.Enfileiravel;
import repository.estaticas.fila.FilaEstatica;

public class LivroDAOFilaEstatica implements LivroDAO {
    private Enfileiravel filaDeLivros= new FilaEstatica(100);

    @Override
    public void addLivro(Livro newLivro){
        if(newLivro==null) {
            throw new IllegalArgumentException("livro não pode ser nulo.");
        }

        filaDeLivros.enfileirar(newLivro);
    }

    @Override
    public Livro getLivroPorId(long id){
        Enfileiravel filaAuxiliar= new FilaEstatica(100);
        Livro livro;
        Livro livroRetorno=null;

        while(!filaDeLivros.estaVazia()){
            livro=(Livro) filaDeLivros.frente();
            if(livro.getId()==id){
                livroRetorno=livro;
            }

            filaAuxiliar.enfileirar(filaDeLivros.desenfileirar());
        }
        filaDeLivros=filaAuxiliar;

        return livroRetorno;
    }

    public Livro[] getAllLivros(){
        Enfileiravel filaAuxiliar= new FilaEstatica(100);
        int totalLivros=0;

        while(!filaDeLivros.estaVazia()){
            filaAuxiliar.enfileirar(filaDeLivros.desenfileirar());
            totalLivros++;

        }

        filaDeLivros=filaAuxiliar;
        filaAuxiliar.limpar();
        Livro[] arrayDeLivros= new Livro[totalLivros];

        for(int i=0; i<totalLivros; i++){
            arrayDeLivros[i]= (Livro) filaDeLivros.desenfileirar();
            filaAuxiliar.enfileirar(arrayDeLivros[i]);
        }

        filaDeLivros=filaAuxiliar;

        return arrayDeLivros;
    }

    public int getTotalLivros(){
        Enfileiravel filaAuxiliar= new FilaEstatica(100);
        int contador=0;
        while(!filaDeLivros.estaVazia()){
            filaAuxiliar.enfileirar(filaDeLivros.desenfileirar());
            contador++;
        }
        filaDeLivros=filaAuxiliar;

        return contador;
    }

    public void updateLivro(Livro newLivro){
        if(!filaDeLivros.estaVazia()){
            Enfileiravel filaAuxiliar= new FilaEstatica(100);
            Livro livroDaFrente;

            while(!filaDeLivros.estaVazia()) {
                livroDaFrente=(Livro) filaDeLivros.desenfileirar();
                if(livroDaFrente.getId()==newLivro.getId()){
                    filaAuxiliar.enfileirar(newLivro);
                }else{
                    filaAuxiliar.enfileirar(livroDaFrente);
                }
            }

            filaDeLivros=filaAuxiliar;
        }
    }

    public Livro deleteLivro(long id) {
        Livro livroRetorno = null;
        if(!filaDeLivros.estaVazia()) {
            Enfileiravel filaAux = new FilaEstatica();
            Livro livro;

            while (!filaDeLivros.estaVazia()) {
                livro = (Livro) filaDeLivros.desenfileirar();
                if (livro.getId() == id) {
                    livroRetorno = livro;

                } else {
                    filaAux.enfileirar(livro);
                }
            }

            filaDeLivros=filaAux;
        }else{
            System.err.println("A fila esta vazia");
        }
        return livroRetorno;
    }

    public Livro[] getLivrosPorAutor(String autor){
        Enfileiravel filaAuxiliar= new FilaEstatica(100);
        Enfileiravel filaRetorno= new FilaEstatica(100);
        Livro livroDaFrente;
        int quantidade=0;

        while(!filaDeLivros.estaVazia()){
            livroDaFrente=(Livro)filaDeLivros.frente();

            if(livroDaFrente.getAutor().equalsIgnoreCase(autor)){
                filaRetorno.enfileirar(livroDaFrente);
                quantidade++;
            }

            filaAuxiliar.enfileirar(filaDeLivros.desenfileirar());
        }

        filaDeLivros=filaAuxiliar;

        Livro[] arrayRetorno= new Livro[quantidade];
        for(int i=0; i<quantidade; i++){
            arrayRetorno[i]= (Livro)filaRetorno.desenfileirar();
        }

        return arrayRetorno;
    }

    public Livro getLivroMaisCaro(){
        if(filaDeLivros.estaVazia()){
            return null;
        }

        Livro maisCaro= (Livro) filaDeLivros.frente();
        Livro atual;
        Enfileiravel filaAux= new FilaEstatica(100);
        while (!filaDeLivros.estaVazia()){
            atual=(Livro) filaDeLivros.desenfileirar();
            if(atual.getPreco()>maisCaro.getPreco()){
                maisCaro=atual;
            }
            filaAux.enfileirar(atual);
        }

        filaDeLivros=filaAux;
        return  maisCaro;
    }

    public Livro[] deleteLivrosPorTitulo(String titulo) {
        if(filaDeLivros.estaVazia()){
            return new Livro[0];
        }

        Livro atual;
        Enfileiravel filaAux= new FilaEstatica(100);
        Enfileiravel filaRemovidos= new FilaEstatica(100);
        int quantidade=0;

        while(!filaDeLivros.estaVazia()){
            atual=(Livro) filaDeLivros.desenfileirar();
            if(atual.getTitulo().equalsIgnoreCase(titulo)){
                filaRemovidos.enfileirar(atual);
                quantidade++;
            }else{
                filaAux.enfileirar(atual);
            }
        }

        filaDeLivros=filaAux;
        filaAux.limpar();

        Livro[] arrayRetorno= new Livro[quantidade];

        for(int i=0; i<quantidade; i++){
            arrayRetorno[i]=(Livro) filaRemovidos.desenfileirar();
        }

        return arrayRetorno;
    }
}


