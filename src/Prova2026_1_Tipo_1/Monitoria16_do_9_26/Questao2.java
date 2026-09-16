package Prova2026_1_Tipo_1.Monitoria16_do_9_26;

/*
Utilizando uma Pilha Estática (PilhaEstatica.java) que implementa a interface Empilhavel.java, implemente os métodos
solicitados a seguir. Considere que os alunos estejam armazenados em: Empilhavel pilhaAlunos = new PilhaEstatica(20);
2. Implemente Aluno getAluno(int matricula), que retorna (sem remover) um aluno de pilhaAlunos, cuja matricula
corresponde ao valor informado. Caso não seja encontrada, retorne null.
 *
 */


import repository.estaticas.pilha.PilhaEstatica;

//Tipo 1
//1. Instanciar a pilha de alunos.
//2. Criar variável para armazenar o aluno encontrado.
//3. Criou pilha auxiliar.
//4. Percorrer a pilha desempilhando elementos.
//5. Armazenar temporariamente os elementos na pilha auxiliar.
//6. Comparar a matrícula corretamente.
//7. Armazenar o aluno encontrado, caso a matrícula seja igual a informada.
//8. Interromper a busca ao encontrar o aluno.
//9. Restaurar a pilha original usando a pilha auxiliar.
//10. Retornar o aluno encontrado ou null caso não exista.
public class Questao2 {
    private class Aluno{
        private int matricula;

        public int getMatricula(){
            return matricula;
        }
    }

    PilhaEstatica pilhaAlunos= new PilhaEstatica(20);
    public Aluno getAluno(int matrucula){

        PilhaEstatica pilhaAux= new PilhaEstatica(20);
        Aluno a= null;
        while(!pilhaAlunos.estaVazia()){
            a= pilhaAlunos.desempilhar();
            if(a.matricula==matricula){
                break;
            }
            pilhaAux.empilhar(a);
        }

        while(!pilhaAux.estaVazia){
            pilhaAlunos.empilhar(pilhaAux.desempilhar());
        }

        return a;
    }

}
