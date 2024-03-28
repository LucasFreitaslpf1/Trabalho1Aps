package br.ifnmg.edu.menus;

import br.ifnmg.edu.outros.Controlador;
import br.ifnmg.edu.outros.Dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Controlador controlador = new Controlador();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dados.cargaDados();
        Controlador controlador = new Controlador();

        while (true) {
            System.out.println("Escolha uma opção: ");

            System.out.println("\n1 - Menu de Equipamentos\n" +
                    "2 - Menu de Campus e Prédios\n" +
                    "3 - Menu de Funcionários\n" +
                    "4 - Menu de Reservas\n" +
                    "5 - Menu de Salas\n" +
                    "0 - Sair"
            );
            String input = scanner.nextLine();

            int opcao;

            try {
                opcao = Integer.parseInt(input);
            } catch (Exception e){
                System.out.println("Entrada inválida\n");
                continue;
            }


            switch (opcao) {
                case 1:
                    MenuEquipamento.menuEquipamento(scanner);
                    break;
                case 2:
                    MenuCampusPredios.menuCampusPredios(scanner);
                    break;
                case 3:
                    MenuFuncionarios.menuFuncionarios(scanner);
                    break;
                case 4:
                    MenuReservas.menuReservas(scanner);
                    break;
                case 5:
                    MenuSala.menuSala(scanner);
                    break;
                case 0:
                    return;
            }

        }
    }


}