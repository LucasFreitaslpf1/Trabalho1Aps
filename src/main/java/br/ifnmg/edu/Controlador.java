package br.ifnmg.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    private List<Equipamento> equipamentos = new ArrayList<>();
    private List<Campus> campi = new ArrayList<>();

    private List<Funcionario> funcionarios = new ArrayList<>();

    public void criaEquipamento(String nome, Integer patrimonio) {
        Equipamento e = new Reserva().criaEquipamento();
        e.setNome(nome);
        e.setPatrimonio(patrimonio);
        equipamentos.add(e);
    }

    public void listarEquipamento() {
        System.out.println("Equipamentos: ");
        for (Equipamento e : equipamentos) {
            System.out.println(e);
        }
    }

    public void criaCampus(String nome, String rua, Integer numero, String bairro, String cidade){
        Campus c = new Reserva().criaFuncionario().criaCampus();
        c.setNome(nome);
        c.setRua(rua);
        c.setNumero(numero);
        c.setBairro(bairro);
        c.setCidade(cidade);
        campi.add(c);
    }


    public void listarCampi(){
        System.out.println("Campi: ");
        for(Campus c : campi){
            System.out.println(c);
        }
    }

    public void criaFuncionario(String nome, String cargo, String ramal){
        Funcionario f = new Reserva().criaFuncionario();
        f.setNome(nome);
        f.setRamal(ramal);
        f.setCargo(cargo);
        funcionarios.add(f);
    }

    public void listarFuncionarios(){
        System.out.println("Funcionários: ");
        for(Funcionario f : funcionarios){
            System.out.println(f);
        }
    }
}
