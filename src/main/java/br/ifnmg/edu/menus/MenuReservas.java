package br.ifnmg.edu.menus;

import br.ifnmg.edu.outros.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.ifnmg.edu.menus.MenuEquipamento.listarEquipamentos;
import static br.ifnmg.edu.menus.MenuFuncionarios.listarFuncionarios;
import static br.ifnmg.edu.menus.MenuSala.listarSalas;

public class MenuReservas {
    public static void menuReservas(Scanner scanner) {
        while (true) {
            System.out.println("\n1 - Adicionar reserva\n" +
                    "2 - Listar reservas\n" +
                    "3 - Criar reserva a partir de salas livres\n" +
                    "0 - Sair");
            String input = scanner.nextLine();
            ;
            int escolha;
            try {
                escolha = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Entrada inválida\n");
                continue;
            }
            switch (escolha) {
                case 1:
                    criarReserva(scanner);
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    criarReservaSalasLivres(scanner);
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void listarReservas() {
        List<String> reservas = new Controlador().getReservas();
        for (String reserva : reservas) {
            System.out.println(reserva);
        }
    }


    private static void criarReserva(Scanner scanner) {
        Controlador controlador = new Controlador();
        if (!controlador.temFuncionario()) {
            System.out.println("Não há funcionários cadastrados");
            return;
        }
        if (!controlador.temSala()) {
            System.out.println("Não há salas cadastradas");
            return;
        }
        System.out.println("Digite a data da alocação: (dia/mês/ano)");
        String dataReserva = scanner.nextLine();
        System.out.println("Digite hora de início da locação: (horas:minutos)");
        String horaInicioReserva = scanner.nextLine();
        System.out.println("Digite hora de fim da locação: (horas:minutos)");
        String horaFimReserva = scanner.nextLine();
        System.out.println("Digite assunto da reserva: ");
        String assuntoResera = scanner.nextLine();

        System.out.println("Deseja adicionar equipamentos?");
        System.out.println("1 - Sim\n0 - Não");
        int opcao;
        while (true) {
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Valor inválido, digite novamente: ");
            }
        }
        ArrayList<Integer> equipamentos = new ArrayList<>();
        while (opcao > 0) {
            System.out.println("Escolha um equipamento: (-1 para sair)");
            listarEquipamentos();
            while (true) {
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Valor inválido, digite novamente: ");
                }
            }
            if (opcao >= 0) equipamentos.add(opcao);
        }

        System.out.println("Escolha uma sala: ");
        listarSalas();
        int numSalaReserva;
        while (true) {
            try {
                numSalaReserva = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Valor inválido, digite novamente: ");
            }
        }

        System.out.println("Escolha um funcionário: ");
        listarFuncionarios();
        int numFuncionario;
        while (true) {
            try {
                numFuncionario = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Valor inválido, digite novamente: ");
            }
        }

        controlador.criaReserva(dataReserva, horaInicioReserva, horaFimReserva, assuntoResera,
                equipamentos, numSalaReserva, numFuncionario);

    }

    private static void criarReservaSalasLivres(Scanner scanner) {
        Controlador controlador = new Controlador();

        if (!controlador.temFuncionario()) {
            System.out.println("Não há funcionários cadastrados");
            return;
        }
        if (!controlador.temSala()) {
            System.out.println("Não há salas cadastradas");
            return;
        }

        if (controlador.temSala()) {
            System.out.println("Digite a data (dia/mês/ano):");
            String dataSalaLivre = scanner.nextLine();
            System.out.println("Digite a hora de inicio (horas:minutos) : ");
            String horaInicioSalaLivre = scanner.nextLine();
            System.out.println("Digite a hora de término (horas:minutos): ");
            String horaFimSalaLivre = scanner.nextLine();
            List<String> salas = controlador.getSalasLivres(dataSalaLivre, horaInicioSalaLivre, horaFimSalaLivre);
            if (!salas.isEmpty()) {
                for (String sala : salas) {
                    System.out.println(sala);
                }
                Integer numSalaLivre = Integer.parseInt(scanner.nextLine());
                criarReservaSalaLivre(scanner,
                        controlador.salaLivreToSala(numSalaLivre,dataSalaLivre,horaInicioSalaLivre,horaFimSalaLivre),
                        dataSalaLivre, horaInicioSalaLivre, horaFimSalaLivre);
            } else {
                System.out.println("Não há salas livres.");
            }
        }
    }

    private static void criarReservaSalaLivre(Scanner scanner, Integer sala, String dataReserva, String horaInicio, String horaFim) {
        Controlador controlador = new Controlador();

        if (!controlador.temFuncionario()) {
            System.out.println("Não há funcionários cadastrados");
            return;
        }
        if (!controlador.temSala()) {
            System.out.println("Não há salas cadastradas");
            return;
        }

        System.out.println("Digite assunto da reserva: ");
        String assuntoResera = scanner.nextLine();

        System.out.println("Deseja adicionar equipamentos?");
        System.out.println("1 - Sim\n0 - Não");
        int opcao = Integer.parseInt(scanner.nextLine());
        ArrayList<Integer> equipamentos = new ArrayList<>();
        while (opcao > 0) {
            System.out.println("Escolha um equipamento: (-1 para sair)");
            listarEquipamentos();
            opcao = Integer.parseInt(scanner.nextLine());
            if (opcao >= 0) equipamentos.add(opcao);
        }
        int numSalaReserva;
        if (sala == null) {
            System.out.println("Escolha uma sala: ");
            listarSalas();
            numSalaReserva = Integer.parseInt(scanner.nextLine());
        } else {
            numSalaReserva = sala;
        }
        System.out.println("Escolha um funcionário: ");
        listarFuncionarios();
        int numFuncionario;
        while (true) {
            try {
                numFuncionario = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Valor inválido, digite novamente: ");
            }
        }

        controlador.criaReserva(dataReserva, horaInicio, horaFim,
                assuntoResera, equipamentos, numSalaReserva, numFuncionario);

    }

}
