package repository.arvores;

public class ArvoreBinariaDePesquisa {
    private NoTriplo raiz;

    public ArvoreBinariaDePesquisa(){
        raiz=null;
    }

    public NoTriplo getRaiz(){
        return raiz;
    }

    public void inserir(int elemento){
        NoTriplo novo= new NoTriplo(elemento);

        if(raiz==null){
            raiz=novo;
            return;
        }

        NoTriplo atual=raiz;
        NoTriplo pai=null;

        while(atual!=null){
            pai=atual;

            if(elemento<atual.getElemento()){
                atual=atual.getEsquerda();
            }else{
                atual=atual.getDireita();
            }
        }
        novo.setPai(pai);

        if(elemento< pai.getElemento()){
            pai.setEsquerda(novo);
        }else{
            pai.setDireita(novo);
        }
    }

    public NoTriplo pesquisar(int elemento){
        NoTriplo atual=raiz;

        while(atual!= null){
            if(elemento == atual.getElemento()){
                return atual;
            }

            if(elemento< atual.getElemento()){
                atual=atual.getEsquerda();
            }else{
                atual=atual.getDireita();
            }
        }

        return null;
    }

    public boolean contem(int elemento){
        return pesquisar(elemento)!=null;
    }

    public NoTriplo menor(){
        if(raiz==null)
            return null;

        NoTriplo atual= raiz;

        while(atual.getEsquerda()!= null){
            atual= atual.getEsquerda();
        }

        return atual;
    }

    public NoTriplo maior(){
        if(raiz==null)
            return null;

        NoTriplo atual= raiz;

        while (atual.getDireita()!=null){

            atual= atual.getDireita();
        }

        return atual;
    }


    public void preOrdem(NoTriplo no){//vai partir desse nó, ou melhor, do rais
        if(no!= null){
            System.out.println(no.getElemento()+ " ");

            preOrdem(no.getEsquerda());
            preOrdem(no.getDireita());
        }
    }

    public void emOrdem(NoTriplo no){
        if(no != null){
            emOrdem(no.getEsquerda());
            System.out.println(no.getEsquerda()+" ");
            emOrdem(no.getDireita());
        }
    }

    public void posOrdem(NoTriplo no){
        if(no!= null){
            posOrdem(no.getEsquerda());
            posOrdem(no.getDireita());

            System.out.println(no.getElemento()+" ");
        }
    }

    public int quantidadeNos(NoTriplo no){
        if( no==null)
            return 0;

        return 1+ quantidadeNos(no.getEsquerda()) + quantidadeNos(no.getDireita());
    }

    public int quantidadeFolhas(NoTriplo no){
        if(no==null)
            return 0;

        if(no.getEsquerda()==null && no.getDireita()==null){
            return 1;
        }

        return quantidadeFolhas(no.getEsquerda()) + quantidadeFolhas(no.getDireita());
    }



}
