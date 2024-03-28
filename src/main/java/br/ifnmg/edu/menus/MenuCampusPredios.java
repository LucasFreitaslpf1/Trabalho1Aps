package br.ifnmg.edu.menus;

import br.ifnmg.edu.outros.Controlador;

import java.util.List;
import java.util.Scanner;

public class MenuCampusPredios {
    public static void menuCampusPredios(Scanner scanner) {
        while(true)
        {
            System.out.println("\n1 - Adicionar campus\n" +
                    "2 - Listar campus\n" +
                    "3 - Criar prédio\n" +
                    "4 - Listar prédios\n" +
                    "0 - Sair");
            int escolha = Integer.parseInt(scanner.nextLine());
            switch (escolha){
                case 1:
                    adicionarCampus(scanner);
                    break;
                case 2:
                    listarCampus();
                    break;
                case 3:
                    criarPredio(scanner);
                    break;
                case 4:
                    listarPredios();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void adicionarCampus(Scanner scanner) {
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
        new Controlador().criaCampus(nomeCampus, ruaCampus, numeroCampus, bairroCampus, cidadeCampus);
    }

    private static void listarCampus() {
        List<String> campi = new Controlador().listarCampi();
        for (int i = 0; i < campi.size(); i++) {
            System.out.println(i + " - " + campi.get(i));
        }
    }

    private static void criarPredio(Scanner scanner) {
        Controlador controlador = new Controlador();
        if (controlador.temCampus()) {
            System.out.println("Digite o nome do prédio: ");
            String nomePredio = scanner.nextLine();
            System.out.println("Selecione um campus");
            listarCampus();
            Integer campusPredio = Integer.parseInt(scanner.nextLine());
            controlador.criaPredio(nomePredio, campusPredio);
        } else {
            System.out.println("Não há campi cadastrados");
        }
    }

    protected static void listarPredios() {
        List<String> predios = new Controlador().getPredios();
        System.out.println("Predios");
        for (int i = 0; i < predios.size(); i++) {
            System.out.println(i + " - " + predios.get(i));
        }
    }
}
