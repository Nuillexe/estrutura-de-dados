package dao;
import repository.Listavel;
import repository.estaticas.lista.ListaEstatica;
import model.Livro;

public class LivroDAOListaEstatica implements LivroDAO {
    private Listavel listaDeLivros= new ListaEstatica(100);

    public void addLivro(Livro livro){
        if( livro==null){
            throw new IllegalArgumentException(("livro não pode ser nulo");
        }
        listaDeLivros.anexar(livro);
    }

    public Livro getLivroPorId(long id){
        Livro livroRetorno= null;
        Livro atual;
        for(int i=0; i<listaDeLivros.tamanho(); i++){
            atual=(Livro) listaDeLivros.selecionar(i);
            if(atual.getId()==id){
                livroRetorno=atual;
                break;
            }
        }
        return livroRetorno;
    }


    public Livro[] getAllLivros(){
        int tamanho=listaDeLivros.tamanho();
        Livro[] arrayDeRetorno= new Livro[tamanho];

        for(int i=0; i<tamanho; i++){
            arrayDeRetorno[i]= (Livro)listaDeLivros.selecionar(i);
        }

        return arrayDeRetorno;
    }


    public int getTotalLivros(){
        return listaDeLivros.tamanho();
    }

    public void updateLivro(Livro newLivro){
        if(listaDeLivros.estaVazia()){
           return;
        }

        Livro atual;
        for(int i=0; i<listaDeLivros.tamanho(); i++){
            atual=(Livro)listaDeLivros.selecionar(i);
            if(atual.getId()==newLivro.getId()){
                listaDeLivros.atualizar(atual,i);
                break;
            }
        }
    }

    @Override
    public Livro deleteLivro(long id) {
        Livro livroAux,livroRetorno =null;

        for(int i=0; i<listaDeLivros.tamanho(); i++){
            livroAux=(Livro) listaDeLivros.selecionar(i);
            if(livroAux.getId()==id){
                livroRetorno= (Livro) listaDeLivros.apagar(i);
                break;
            }
        }
       return livroRetorno;
    }

    public Livro[] deleteLivrosPorTitulo(String titulo){
        Listavel listaRemovidos= new ListaEstatica(100);
        Livro selecionado;
        for(int i=0; i<listaDeLivros.tamanho(); i++){
            selecionado=(Livro) listaDeLivros.selecionar(i);

            if(selecionado.getTitulo().equalsIgnoreCase(titulo)){
                listaRemovidos.anexar(listaDeLivros.apagar(i));
                i--;
            }
        }

        Livro[] livrosRemovidos= new Livro[listaRemovidos.tamanho()];
        for(int i=0; i<listaRemovidos.tamanho(); i++){
            livrosRemovidos[i]=(Livro)listaRemovidos.selecionar(i);
        }

        return livrosRemovidos;
    }

    public Livro getLivroMaisCaro(){
        if(listaDeLivros.estaVazia()){
            return null;
        }

        Livro livroMaisCaro=(Livro)listaDeLivros.selecionar(0);
        Livro atual;
        for(int i = 1; i < listaDeLivros.tamanho(); i++){
            atual=(Livro) listaDeLivros.selecionar(i);
            if(livroMaisCaro.getPreco() < atual.getPreco()){
                livroMaisCaro=atual;
            }
        }

        return livroMaisCaro;
    }

}
