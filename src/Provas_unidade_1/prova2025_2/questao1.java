package Provas_unidade_1.prova2025_2;

public class questao1 {

    class sys{
        class Client{
            int clientCPF;
            String name;

            public Client(int CPFname, String name) {
                this.clientCPF = CPFname;
                this.name = name;
            }
        }

        class Order{
            int clientCPF;
            float value;

            public Order(int clientCPF, float value) {
                this.clientCPF = clientCPF;
                this.value = value;
            }
        }


        public float masterDetail(String name){
            Client client=null;
            float soma=0;
            for( client: clientes) {
                if (client.name.equalsIgnoreCase(name))
                    break;
            }

            if(client!=null){
                for(Order order:pedidos){
                    if(order.clientCPF==client.clientCPF){
                        soma+=order.value;
                    }
                }
            }

            return soma;
        }

    }
}
