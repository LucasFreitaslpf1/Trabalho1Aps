package br.ifnmg.edu;

import java.time.LocalTime;
import java.util.Date;

public class Reserva {

    private Date dataAlocacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String assunto;

    public Equipamento criaEquipamento() {

        return new Equipamento();
    }

    public Funcionario criaFuncionario(){
        return new Funcionario();
    }
}
