package com.uclm.louise.ediaries;

import static com.uclm.louise.ediaries.utils.Constantes.ACTIVIDADES_FAVORITAS_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DCLINICOS_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DESCOLARES_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DPERSONALES_URL;
import static com.uclm.louise.ediaries.utils.Constantes.GET_CHILD;
import static com.uclm.louise.ediaries.utils.Constantes.REGISTER_URL;
import static com.uclm.louise.ediaries.utils.Constantes.USUARIO_URL;

import com.uclm.louise.ediaries.data.models.Child;
import com.uclm.louise.ediaries.data.models.DClinicos;
import com.uclm.louise.ediaries.data.models.DEscolares;
import com.uclm.louise.ediaries.data.models.DPersonales;
import com.uclm.louise.ediaries.data.requests.CreateActividadesFavoritasRequest;
import com.uclm.louise.ediaries.data.requests.CreateDClinicosRequest;
import com.uclm.louise.ediaries.data.requests.CreateDEscolaresRequest;
import com.uclm.louise.ediaries.data.requests.CreateDPersonalesRequest;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;
import com.uclm.louise.ediaries.data.responses.CreateUsuarioResult;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RegistroService {

    @POST(USUARIO_URL+REGISTER_URL)
    Call<CreateUsuarioResult> createUsuario(@Body CreateUsuarioRequest usuario);

    @POST(USUARIO_URL+DPERSONALES_URL)
    Call<DPersonales> createDPersonales(@Body CreateDPersonalesRequest dpersonales);

    @POST(USUARIO_URL+DCLINICOS_URL)
    Call<DClinicos> createDClinicos(@Body CreateDClinicosRequest dclinicos);

    @POST(USUARIO_URL+DESCOLARES_URL)
    Call<DEscolares> createDEscolares(@Body CreateDEscolaresRequest descolares);

    @POST(USUARIO_URL+ACTIVIDADES_FAVORITAS_URL)
    Call<Response<Void>> createActividadesFavoritas(@Body CreateActividadesFavoritasRequest actividadesFavoritas);

}
