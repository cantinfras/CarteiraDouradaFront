package com.example.elton.carteiradourada.services;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "https://carteira-dourada.herokuapp.com/";

    public static TipoMultaService getTipoMultaService(){
        return RetrofitCliente.getCliente(API_URL).create(TipoMultaService.class);
    }
}
