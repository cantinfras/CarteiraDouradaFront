package com.example.elton.carteiradourada.services;

import com.example.elton.carteiradourada.model.TipoMulta;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TipoMultaService {

    @GET("tiposMulta/")
    Call<List<TipoMulta>> getTiposMulta();

    @POST("tiposMulta/")
    Call<TipoMulta> addTipoMulta(@Body TipoMulta tipoMulta);

    @PUT("tiposMulta/{id}")
    Call<TipoMulta> updateTipoMulta(@Path("id") int id, @Body TipoMulta tipoMulta);

    @DELETE("tiposMulta/{id}")
    Call<TipoMulta> deleteTipoMulta(@Path("id") int id);
}