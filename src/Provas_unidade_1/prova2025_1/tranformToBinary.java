package Provas_unidade_1.prova2025_1;
import repository.Empilhavel;
import repository.estaticas.pilha.PilhaEstatica;

public class tranformToBinary {

    String decToBin(String data){
        int number= Integer.parseInt(data);
        Empilhavel pilhaDeValores= new PilhaEstatica(100);
        int result;

        if (number == 0) {
            return "0";
        }
        while(!(number==0)){
            result=number%2;
            number=number/2;
            pilhaDeValores.empilhar(result);
        }

        String numeroBinario="";

        while(!pilhaDeValores.estaVazia()){
            numeroBinario +=pilhaDeValores.desempilhar();

        }

        return numeroBinario;
    }
}
