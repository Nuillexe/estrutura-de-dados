package Provas_unidade_1.prova2025_1;

import java.time.LocalDateTime;


/*
* C-CREATE
* R=READ
* U=UPDATE
* D=DELETE
* */


public interface ControleDeReserva {

    void addReserva(Object Reserva);//Adiciona nova reserva

    Object checarReserva(String nomeDoPassageiro);//Checar se um passageiro tem uma reserva, através do nome do passageiro

    Object[] listarReservas(); // Retornar todas as reservas

    void atualiazarReserva(Object novaReserva); //Atualizar uma reserva existente a partir do nome do passageiro cadastrado em novaReserva

    Object deletarReserva(String nomeDoPassageiro);//Deletar reserva a partir do nome do passageiro

    boolean estaCheia();//Checa se o numero de rserva chegou ao limite

    boolean estaVazia();//Checa se não há nenhuma reserva registrada

    Object[] getReservaPorData(LocalDateTime data);//Seleciona reservas por data

    Object[] getReservaPorOrigem(String origem);//Seleciona reservas por origem

    Object[] getReservaPorDestino(String destino);//Seleciona reservas por destino

    Object[] getReservaPorDataEHorario(LocalDateTime data, LocalDateTime horario); //Seleciona reservas por data e horario


}
