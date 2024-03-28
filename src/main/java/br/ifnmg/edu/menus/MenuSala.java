package br.ifnmg.edu.menus;

import br.ifnmg.edu.outros.Controlador;

import java.util.List;
import java.util.Scanner;

import static br.ifnmg.edu.menus.MenuCampusPredios.listarPredios;

public class MenuSala {
    public static void menuSala(Scanner scanner) {
        while(true)
        {
            System.out.println("\n1 - Adicionar sala\n" +
                    "2 - Listar salas\n" +
                    "3 - Ver salas ocupadas\n"+
                    "4 - Listagem de salas por dia, semana ou mês\n" +
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
                    criarSala(scanner);
                    break;
                case 2:
                    listarSalas();
                    break;
                case 3:
                    verSalasOcupadas(scanner);
                    break;
                case 4:
                    verSalasTempo(scanner);
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void criarSala(Scanner scanner) {
        Controlador controlador = new Controlador();
        if (controlador.temPredio()) {
            System.out.println("Digite o numero da sala: ");
            int numeroSala;
            while(true){
                try {
                    numeroSala = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (Exception e){
                    System.out.println("Valor inválido, digite novamente: ");
                }
            }
            System.out.println("Digite a quantidade de lugares: ");
            int lugaresSala;
            while(true){
                try {
                    lugaresSala = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (Exception e){
                    System.out.println("Valor inválido, digite novamente: ");
                }
            }
            System.out.println("Selecione um prédio");
            listarPredios();
            int predioSala;
            while(true){
                try {
                    predioSala = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (Exception e){
                    System.out.println("Valor inválido, digite novamente: ");
                }
            }
            controlador.criarSala(numeroSala, lugaresSala, predioSala);
        } else {
            System.out.println("Não há prédios cadastrados");
        }
    }

    protected static void listarSalas() {
        List<String> salas = new Controlador().getSalas();
        System.out.println("Salas:");
        for (int i = 0; i < salas.size(); i++) {
            System.out.println(i + " - " + salas.get(i));
        }
    }

    private static void verSalasOcupadas(Scanner scanner) {
        System.out.println("Digite o início do intervalo (dia/mês/ano):");
        String dataInicio = scanner.nextLine();
        System.out.println("Digite o fim do intervalo (dia/mês/ano):");
        String dataFim = scanner.nextLine();
        for(String sala : new Controlador().getSalasOcupadasPeriodo(dataInicio, dataFim)){
            System.out.println(sala);
        }
    }

    private static void verSalasTempo(Scanner scanner) {
        System.out.println("Escolha um tipo de listagem: ");
        System.out.println("1 - Por dia");
        System.out.println("2 - Por mês");
        System.out.println("3 - Por semana");
        int escolhaListagem;
        while(true){
            try {
                escolhaListagem = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e){
                System.out.println("Valor inválido, digite novamente: ");
            }
        }
        for(String s : new Controlador().getSalasOrdemCronologica(escolhaListagem)){
            System.out.println(s);
        }
    }
}
