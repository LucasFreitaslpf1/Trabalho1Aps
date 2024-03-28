package br.ifnmg.edu.menus;

import br.ifnmg.edu.outros.Controlador;

import java.util.List;
import java.util.Scanner;

public class MenuEquipamento
{
    public static void menuEquipamento(Scanner scanner) {
        while(true)
        {
            System.out.println("1 - Adicionar equipamento\n" +
                    "2 - Listar equipamento\n" +
                    "0 - Sair");
            int escolha = Integer.parseInt(scanner.nextLine());
            switch (escolha){
                case 1:
                    adicionarEquipamento(scanner);
                    break;
                case 2:
                    listarEquipamentos();
                    break;
                case 0:
                    return;
            }
        }
    }

    protected static void listarEquipamentos() {
        List<String> equipamentos = new Controlador().getEquipamentos();
        System.out.println("Equipamentos:");
        for (int i = 0; i < equipamentos.size(); i++) {
            System.out.println(i + " - " + equipamentos.get(i));
        }
    }

    private static void adicionarEquipamento(Scanner scanner) {
        System.out.println("Insira nome do equipamento: ");
        String nomeEquipamento = scanner.nextLine();
        System.out.println("Insira patrimonio do equipamento: ");
        Integer patrimonioEquipamento = Integer.parseInt(scanner.nextLine());
        new Controlador().criaEquipamento(nomeEquipamento, patrimonioEquipamento);
    }
}
