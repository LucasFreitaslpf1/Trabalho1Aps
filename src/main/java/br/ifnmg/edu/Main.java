package br.ifnmg.edu;

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
                    "0 - Sair");

            Integer entrada = Integer.parseInt(scanner.nextLine());

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
                case 0:
                    return;
            }
        }
    }
}