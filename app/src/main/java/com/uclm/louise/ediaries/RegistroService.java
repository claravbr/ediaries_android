package com.uclm.louise.ediaries;

import static com.uclm.louise.ediaries.utils.Constantes.DCLINICOS_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DESCOLARES_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DPERSONALES_URL;
import static com.uclm.louise.ediaries.utils.Constantes.USUARIO_URL;

import com.uclm.louise.ediaries.data.models.DClinicos;
import com.uclm.louise.ediaries.data.models.DEscolares;
import com.uclm.louise.ediaries.data.models.DPersonales;
import com.uclm.louise.ediaries.data.models.Usuario;
import com.uclm.louise.ediaries.data.requests.CreateDClinicosRequest;
import com.uclm.louise.ediaries.data.requests.CreateDEscolaresRequest;
import com.uclm.louise.ediaries.data.requests.CreateDPersonalesRequest;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistroService {

    @POST(USUARIO_URL)
    Call<Usuario> createUsuario(@Body CreateUsuarioRequest usuario);

    @POST(USUARIO_URL+DPERSONALES_URL)
    Call<DPersonales> createDPersonales(@Body CreateDPersonalesRequest dpersonales);

    @POST(USUARIO_URL+DCLINICOS_URL)
    Call<DClinicos> createDPersonales(@Body CreateDClinicosRequest dclinicos);

    @POST(USUARIO_URL+DESCOLARES_URL)
    Call<DEscolares> createDPersonales(@Body CreateDEscolaresRequest descolares);


}
