package com.project.gymSystem.exceptions;

public class UsuarioFoundException extends RuntimeException{

    public UsuarioFoundException(){super("CPF/Email/Telefone já cadastrado");}
}
