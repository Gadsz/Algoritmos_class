import java.util.Scanner;

public class Teatro {

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            Gerenciamento Gerenciador = new Gerenciamento();
            int escolher; //opção

            while (true) { //bloco de código que quando verdadeiro é executado

                // Menu do terminal

                System.out.println("\nBem-vindo ao GERENCIAMENTO DO TEATRO (TM)");// menu de selecação que vai ser printado para o usuário
                System.out.println("Escolha uma das opção:"); //texto principal
                System.out.println("1. [Área Administrativa]"); 
                System.out.println("2. [Área de Cliente]");
                System.out.println("3. [Sair]");

                escolher = entrada.nextInt();

                // Ações dentro do menu

                switch (escolher) {
                    case 1:
                        Gerenciador.Menu_ADM(); //puxa para o outro menu
                        break;
                    case 2:
                        Gerenciador.MenuCliente(); //puxa para o menu usuário
                        break;
                    case 3:
                        System.out.println("Saindo do sistema..."); // texto final quando finaliza
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida: Tente novamente...");
                }
            }
        }
    }

    static class Gerenciamento { // ações que só acontecem dentro da classe Gerenciamento

        private double ValorIngressoAgora = 50.00;
        private double TotalArrecadado = 0.00;
        private double ValorIngresso = ValorIngressoAgora;
        private int[][] MapaAssentos = new int[15][10]; //mapa em matriz dos assentos podendo ser configurado
        private int AssentosLivres = 150;
        private int AssentosConfirmados = 0;
        private int IngressosEstudantes = 0;
        private int AssentosReservados = 0; // define e inicia todas as variávies com 0 para dar o valor conforme o usuario preencher

        public void Menu_ADM() { //quando selecionado lá em cima o menu adm tem essas opções

            Scanner entrada = new Scanner(System.in);
            int escolher;

            while (true) {

                // Menu dentro do adm, tudo isso é printado

                System.out.println("\nMenu ADM (TM)"); // texto do topo 
                System.out.println("1. [Visualizar assentos]");
                System.out.println("2. [Gerar relatório]");
                System.out.println("3. [Alterar valor do ingresso");
                System.out.println("4. [Voltar ao menu]");

                escolher = entrada.nextInt();

                // Ações do menu

                switch (escolher) { // switch de alternativos linkados dentro do menu adm
                    case 1:
                        MapaAssentos(); //interruptor 2
                        break;
                    case 2: //interruptor 2
                        Relatorio();
                        break;
                    case 3:
                        System.out.println("Digite o novo valor:"); //caso selecione para alterar o valor vai printar essa frase
                        ValorIngresso = entrada.nextDouble();
                        System.out.println("Valor do ingresso agora é R$:" + ValorIngresso); // viariável para edição
                        break;
                    case 4:
                        return; //retornar
                    default:
                        System.out.println("Opção inválida: Tente novamente..."); //default pra digitar algo errado
                }
            }
        }

        public void MenuCliente() {
            Scanner entrada = new Scanner(System.in); // novo scanner pra dar entrada no meu do cliente
            int escolher; //int escolher para dar opções do que fazer

            // Menu do cliente para edição

            while (true) {
                System.out.println("\nMenu CLIENTE:"); // opções em display linkadas ao clicar no modo cliente
                System.out.println("1. [Visualizar o mapa de assentos]");
                System.out.println("2. [Efetuar reserva]");
                System.out.println("3. [Cancelar reserva]");
                System.out.println("4. [Voltar ao menu principal]");
                escolher = entrada.nextInt();

                // Ações do menu cliente em switch (analogia do interruptor)

                switch (escolher) {
                    case 1:
                        MapaAssentos(); //interruptor 1
                        break;
                    case 2:
                        Reservar(); //interruptor 2
                        break;
                    case 3:
                        CancelarReserva(); //interruptor 3
                        break;
                    case 4:
                        return; //interruptor 4 (nada)
                    default:
                        System.out.println("Opção inválida: Tente novamente..."); //vai printar isso como default se der erro ou se o usuário digitar algo errado
                }
            }
        }

        public void MapaAssentos() {
            System.out.println("\nMapa dos Assentos:"); //ele puxa do mapa de assentos dando display nessas opções

            System.out.println("---------------------");
            System.out.println("-        PALCO        -"); //layout terminal
            System.out.println("---------------------");

            for (int i = 0; i < 15; i++) { //loop alinhado com o for, passa por todos as celulas da matriz (0 a 15 sendo as variaveis o 15 e 10)
                for (int j = 0; j < 10; j++) { //for na variavel int j que é o numero de assentos, quando imprimi faz a matriz 10x15

                    if (MapaAssentos[i][j] == 0) {
                        System.out.print("_ ");
                    } else if (MapaAssentos[i][j] == 1) { //quando printa x significa que já está ocupado
                        System.out.print("X ");
                    } else if (MapaAssentos[i][j] == 2) { //como se fosse algo booleano de sim ou não com o assento preenchendo ou reservado
                        System.out.print("R "); // quando printa r significa que já está reservado
                    }
                }
                System.out.println();

            }
        }

        public void Reservar() {

            Scanner entrada = new Scanner(System.in); //scanner novo para a ação de reservar
            int coluna, linha;
            String resposta;

            System.out.println("\nRealizar Reserva:"); //vai printar isso e quebrar o código

            System.out.print("[Filas entre 0 a 14: ]"); //pedindo a fila puxando da coluna
            coluna = entrada.nextInt();

            System.out.print("[Cadeiras entre 0 a 9: ]"); //pedindo a fila puxando da linha
            linha = entrada.nextInt();

            if (coluna < 0 || coluna > 14 || linha < 0 || linha > 9) { //vai validar se a entrada feita pelo usuário é válida comparando com os dados nesse contexto, se for falso
                System.out.println("Assento inválido: Tente novamente..."); // vai imprimir isso e retornar para outra opção a ser inserida
                return;
            }

            // Caso o cliente seja estudante

            if (MapaAssentos[coluna][linha] == 0) { //começa iniciando com coluna linha = 0 sinalizando que o assento esta vago (opção 1 e 2 como se fosse true e false)

                System.out.print("O cliente é estudante? (S/N): "); //vai pedir se é estudante e caso se sim vai puxar para o valor especial
                resposta = entrada.next().toUpperCase(); //depois de ler vai armazenar a alternativa

                if (resposta.equals("S")) {
                    TotalArrecadado += ValorIngresso / 2; 
                    //o valor vai ser meia dividindo o total arrecado desse ingresso em especifico sendo dividido em 2
                    IngressosEstudantes++;
                } else {
                    TotalArrecadado += ValorIngresso; // se não vai fazer igular o valor com mais igual ao valor setado no ingresso full
                }

                MapaAssentos[coluna][linha] = 2; //se atualizar para 2 significa que o assento foi reservado
                AssentosReservados++; //aumenta o numero de assuntos reservado e automaticamente diminui o numero de assentos livres puxando lá em cima
                AssentosLivres--;
                System.out.println("Reserva realizada com sucesso.");//depois do usuário fazer a reserva isso vai ser printado na tela dele
            } else { //caso contrário ele vai ser avisado que já esta reservado
                System.out.println("Este assento já está ocupado ou reservado.");
            }
        }

        public void CancelarReserva() { //puxa lá de cima o cancelarReserva
            Scanner entrada = new Scanner(System.in); //cria o scanner para ser lido posteriormente
            int coluna, linha;
            System.out.println("\nCancelar Reserva:"); //vai printar pra cancelar a reserva e quebrar a linha

            System.out.print("Filas entre 0 a 14: "); //vai pedir ao usuário selecionar a fila dando coluna = entrada puxando da reserva efetuada
            coluna = entrada.nextInt();

            System.out.print("Cadeiras entre 0 a 9: "); //vai pedir ao usuário selecionar a coluna dando linha = entrada puxando da reserva efetuada
            linha = entrada.nextInt();

            if (coluna < 0 || coluna > 14 || linha < 0 || linha > 9) { // ele vai verificar comparando com os dados que foram inseridos nesse contexto se as cadeiras são válidas e se existem

                System.out.println("Assento inválido. Tente novamente."); // caso a alternativa anterior for verdadeira (analogia do true e false) vai printar inválido e retornar embaixo pra outra ação
                return;
            }

            if (MapaAssentos[coluna][linha] == 2) { //2 significado reservado
                MapaAssentos[coluna][linha] = 0;
                AssentosReservados--; //vai aumentar os assentos revardos e automaticamente diminuir os livres
                AssentosLivres++;
                System.out.println("Reserva cancelada com sucesso."); //você pode cancelar a reserva com essa opção
            } else {
                System.out.println("Este assento não está reservado."); //printa quando tenta selecionar a reserva
            }
        }

        public void Relatorio() {
            System.out.println("\nRelatório:");
            System.out.println("Quantidade de lugares reservados: " + AssentosReservados);
            System.out.println("Quantidade de lugares confirmados: " + AssentosConfirmados);
            System.out.println("Quantidade de lugares livres: " + AssentosLivres);
            System.out.println("Quantidade de ingressos de estudantes: " + IngressosEstudantes);
            System.out.println("Valor total arrecadado: R$" + TotalArrecadado); //quando puxa o relatorio ele vai printar todas essas informações puxando as info de onde os nomes estão, como variáveis

        }

    }

}