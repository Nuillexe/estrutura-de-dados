package Prova2026_1_Tipo_1.Monitoria16_do_9_26;


import java.time.LocalDate;

/*
Utilizando uma Fila Estática Circular (FilaEstatica.java) que implementa a interface
Enfileiravel.java, implemente os métodos solicitados a seguir. Considere que os pacientes
estejam armazenados em: Enfileiravel filaPacientes = new FilaEstatica(20);
* Implemente Paciente[] getPacientesPorFaixaEtaria(LocalDate dataInicial, LocalDate
dataFinal), que retorna um array contendo todos os pacientes de filaPacientes, cuja
dataNascimento esteja entre as datas informadas. Caso não exista correspondência, o método deve
retornar um array vazio.
*
* */

public class questaFilaProvaTipo3 {

    private class Paciente{
        private LocalDate idade;

        public LocalDate  getIdade(){
            return idade;
        }
    }

    Enfileiravel filaPacientes = new FilaEstatica(20);

    Paciente[] getPacientesPorFaixaEtaria(LocalDate dataInicial, LocalDate dataFinal){

        FilaEstatica filaAux= new FilaEstatica(20);
        Paciente[] filaDeRetorno=new Paciente[20];
        int quantidade=0;

        while(!filaPacientes.estaVazia()){

            Paciente p= filaPacientes.desenfileirar();

            if( p.getIdade().isAfter(dataInicial) && p.getIdade().isBefore(dataFinal) ){//checa se esta na faixa
                filaDeRetorno.enfileirar(p);
                quantidade++;
            }

            filaAux.enfileirar(p);
        }

        filaPacientes=filaAux;

        Paciente[] arrayDeRetorno= new Paciente[quantidade];

        for(int i=0; i<quantidade; i++){
            arrayDeRetorno[i]= filaDeRetorno.desenfileirar();
        }


        /* tbm pode realocar os itens dessa forma
        *   int i=0;
        while (!filaDeRetorno.estaVazia()){
            arrayDeRetorno[i]=filaDeRetorno.desenfileirar();
            i++;
        }
        * */

        return arrayDeRetorno;

    }
}
