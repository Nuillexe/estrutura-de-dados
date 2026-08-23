/// Metodo masterDetail: Refatorar colocando for each e tentar encapsular
///


package Provas_unidade_1.prova2025_2;

import repository.estaticas.lista.ListaEstatica;

class Cliente{
    private int clienteCPF;
    private String name;

    public int getClienteCPF() {
        return clienteCPF;
    }

    public void setClienteCPF(int clienteCPF) {
        this.clienteCPF = clienteCPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Cliente(int clienteCPF, String name){
        this.clienteCPF=clienteCPF;
        this.name=name;
    }
}

class Order{
    private int clientCPF, value;

    public int getClientCPF() {
        return clientCPF;
    }

    public void setClientCPF(int clientCPF) {
        this.clientCPF = clientCPF;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Order(int clientCPF, int value) {\
        this.clientCPF = clientCPF;
        this.value = value;
    }
}



public class Sistema{

    ListaEstatica clientes= new ListaEstatica();
    ListaEstatica pedidos= new ListaEstatica();
    public static void main(String[] args) {

    }

    public float masterDetail(String name){
        Cliente[] arrayDeClientes= (Cliente[]) clientes.selecionarTodos();
        Order[] arrayDePedidos= (Order[]) pedidos.selecionarTodos();


        for(int c=0; c<arrayDeClientes.length; c++){
            if(arrayDeClientes[c].getName().equalsIgnoreCase(name)){
                Cliente cliente=arrayDeClientes[c];
                Order pedido;
                int somatory=0;

                for(int p=0; p<arrayDePedidos.length; p++){
                    pedido=arrayDePedidos[p];
                    if(pedido.getClientCPF()==cliente.getClienteCPF()){
                        somatory+=pedido.getValue();
                    }
                }
                return somatory;
            }
        }
        System.err.println("Não há esse cliente na lista de clientes");

        return 0;
    }


}