package br.ifnmg.edu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Controlador controlador = new Controlador();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("Escolha uma opção: ");

            System.out.println("1 - Adicionar equipamento\n" +
                    "2 - Listar equipamento\n" +
                    "3 - Adicionar Campus\n" +
                    "4 - Listar Campus\n" +
                    "5 - Criar Funcionario\n" +
                    "6 - Listar Funcionários\n" +
                    "7 - Criar prédio\n" +
                    "8 - Listar prédios\n" +
                    "9 - Criar sala\n"+
                    "10 - Listar salas\n" +
                    "11 - Fazer reserva\n" +
                    "12 - Listar reservas\n" +
                    "0 - Sair"
            );
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                continue;
            }

            int entrada = Integer.parseInt(input);

            switch (entrada) {
                case 1:
                    System.out.println("Insira nome do equipamento: ");
                    String nomeEquipamento = scanner.nextLine();
                    System.out.println("Insira patrimonio do equipamento: ");
                    Integer patrimonioEquipamento = Integer.parseInt(scanner.nextLine());
                    controlador.criaEquipamento(nomeEquipamento, patrimonioEquipamento);
                    break;
                case 2:
                    controlador.listarEquipamento();
                    break;
                case 3:
                    System.out.println("Insira o nome do Campus: ");
                    String nomeCampus = scanner.nextLine();
                    System.out.println("Digite a Rua do Campus: ");
                    String ruaCampus = scanner.nextLine();
                    System.out.println("Digite o Bairro do Campus: ");
                    String bairroCampus = scanner.nextLine();
                    System.out.println("Digite o numero do Campus: ");
                    Integer numeroCampus = Integer.parseInt(scanner.nextLine());
                    System.out.println("Digite a cidade do Campus: ");
                    String cidadeCampus = scanner.nextLine();
                    controlador.criaCampus(nomeCampus, ruaCampus, numeroCampus, bairroCampus, cidadeCampus);
                    break;
                case 4:
                    controlador.listarCampi();
                    break;
                case 5:
                    System.out.println("Digite o nome do funcionário: ");
                    String nomeFuncionario = scanner.nextLine();
                    System.out.println("Digite o cargo do funcionário: ");
                    String cargoFuncionario = scanner.nextLine();
                    System.out.println("Digite o ramal do funcionário: ");
                    String ramalFuncionario = scanner.nextLine();
                    controlador.criaFuncionario(nomeFuncionario, cargoFuncionario, ramalFuncionario);
                    break;
                case 6:
                    controlador.listarFuncionarios();
                    break;
                case 7:
                    if(controlador.temCampus()){
                        System.out.println("Digite o nome do prédio: ");
                        String nomePredio = scanner.nextLine();
                        System.out.println("Selecione um campus");
                        controlador.listarCampi();
                        Integer campusPredio = Integer.parseInt(scanner.nextLine());
                        controlador.criaPredio(nomePredio,campusPredio);
                    } else{
                        System.out.println("Não há campi cadastrados");
                    }
                    break;
                case 8:
                    controlador.listarPredios();
                    break;
                case 9:
                    if(controlador.temPredio()){
                        System.out.println("Digite o numero da sala: ");
                        Integer numeroSala = Integer.parseInt(scanner.nextLine());
                        System.out.println("Digite a quantidade de lugares: ");
                        Integer lugaresSala = Integer.parseInt(scanner.nextLine());
                        System.out.println("Selecione um prédio");
                        controlador.listarPredios();
                        Integer predioSala = Integer.parseInt(scanner.nextLine());
                        controlador.criarSala(numeroSala,lugaresSala,predioSala);
                    } else{
                        System.out.println("Não há prédios cadastrados");
                    }
                    break;
                case 10:
                    controlador.listaSalas();
                    break;
                case 11:
                    if(controlador.temSala()){
                        System.out.println("Digite a data da alocação: (dia/mês/ano)");
                        String dataReserva = scanner.nextLine();
                        System.out.println("Digite hora de início da locação: (horas:minutos)");
                        String horaInicioReserva = scanner.nextLine();
                        System.out.println("Digite hora de fim da locação: (horas:minutos)");
                        String horaFimReserva = scanner.nextLine();
                        System.out.println("Digite assunto da reserva: ");
                        String assuntoResera = scanner.nextLine();

                        System.out.println("Deseja adicionar equipamentos?");
                        System.out.println("1 - Sim\n2 - Não");
                        int opcao = Integer.parseInt(scanner.nextLine());
                        ArrayList<Integer> equipamentos = new ArrayList<>();
                        while(opcao >= 0){
                            System.out.println("Escolha um equipamento: (-1 para sair)");
                            controlador.listarEquipamento();
                            opcao = Integer.parseInt(scanner.nextLine());
                            if(opcao >= 0) equipamentos.add(opcao);
                        }

                        System.out.println("Escolha uma sala: ");
                        controlador.listaSalas();
                        Integer numSalaReserva = Integer.parseInt(scanner.nextLine());
                        controlador.criaReserva(dataReserva,horaInicioReserva,horaFimReserva,assuntoResera,equipamentos,numSalaReserva);
                    } else{
                        System.out.println("Não há salas cadastradas");
                    }
                    break;
                case 0:
                    return;
            }

        }
    }
}