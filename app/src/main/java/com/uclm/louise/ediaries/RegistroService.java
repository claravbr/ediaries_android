package com.uclm.louise.ediaries;

import static com.uclm.louise.ediaries.utils.Constantes.USUARIO_URL;

import com.uclm.louise.ediaries.data.models.Usuario;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistroService {

    @POST(USUARIO_URL)
    Call<Usuario> createUsuario(@Body CreateUsuarioRequest usuario);
}
