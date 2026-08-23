package Provas_unidade_1.prova2025_2;


public class Questao3 {

    class Paciente{
        String nome;

        int prioridade=0;
        int idade;

        public Paciente(int idade, String nome, int prioridade) {
            this.idade = idade;
            this.nome = nome;
            this.prioridade = prioridade;
        }
        //Considerasse os metodos basicos da fila já implementados(enfileirar, desenfileirar,front, etc)
        static long count=0;
        long priority;

        long priorityAdjustamente(int risk){
            count++;
            return risk*10000 - count;
        }
    }
}
