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
        int patrimonioEquipamento;
        while(true){
            try {
                patrimonioEquipamento = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e){
                System.out.println("Valor inválido, digite novamente: ");
            }
        }
        new Controlador().criaEquipamento(nomeEquipamento, patrimonioEquipamento);
    }
}
