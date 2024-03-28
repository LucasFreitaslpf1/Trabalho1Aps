package br.ifnmg.edu.menus;

import br.ifnmg.edu.outros.Controlador;

import java.util.List;
import java.util.Scanner;

public class MenuFuncionarios {
    public static void menuFuncionarios(Scanner scanner) {
        while(true)
        {
            System.out.println("\n1 - Adicionar funcionário\n" +
                    "2 - Listar funcionários\n" +
                    "0 - Sair");
            String input = scanner.nextLine();;
            int escolha;
            try {
                escolha = Integer.parseInt(input);
            } catch (Exception e){
                System.out.println("Entrada inválida\n");
                continue;
            }
            switch (escolha){
                case 1:
                    criarFuncionario(scanner);
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void criarFuncionario(Scanner scanner) {
        System.out.println("Digite o nome do funcionário: ");
        String nomeFuncionario = scanner.nextLine();
        System.out.println("Digite o cargo do funcionário: ");
        String cargoFuncionario = scanner.nextLine();
        System.out.println("Digite o ramal do funcionário: ");
        String ramalFuncionario = scanner.nextLine();
        new Controlador().criaFuncionario(nomeFuncionario, cargoFuncionario, ramalFuncionario);
    }

    protected static void listarFuncionarios() {
        List<String> funcionarios = new Controlador().getFuncionarios();
        System.out.println("Funcionários: ");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println(i + " - " + funcionarios.get(i));
        }
    }
}
