package com.uclm.louise.ediaries;

import static com.uclm.louise.ediaries.utils.Constantes.ACTIVIDADES_FAVORITAS_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DCLINICOS_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DESCOLARES_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DIARIO_EMOCIONES_URL;
import static com.uclm.louise.ediaries.utils.Constantes.DONE;
import static com.uclm.louise.ediaries.utils.Constantes.DPERSONALES_URL;
import static com.uclm.louise.ediaries.utils.Constantes.GET_TAREAS;
import static com.uclm.louise.ediaries.utils.Constantes.LOGIN_URL;
import static com.uclm.louise.ediaries.utils.Constantes.NEW;
import static com.uclm.louise.ediaries.utils.Constantes.REGISTER_URL;
import static com.uclm.louise.ediaries.utils.Constantes.TAREA_DIARIA_URL;
import static com.uclm.louise.ediaries.utils.Constantes.UPDATE;
import static com.uclm.louise.ediaries.utils.Constantes.USUARIO_URL;

import com.uclm.louise.ediaries.data.models.DClinicos;
import com.uclm.louise.ediaries.data.models.DEscolares;
import com.uclm.louise.ediaries.data.models.DPersonales;
import com.uclm.louise.ediaries.data.models.DiarioEmociones;
import com.uclm.louise.ediaries.data.models.TareaDiaria;
import com.uclm.louise.ediaries.data.requests.CreateActividadesFavoritasRequest;
import com.uclm.louise.ediaries.data.requests.CreateDClinicosRequest;
import com.uclm.louise.ediaries.data.requests.CreateDEscolaresRequest;
import com.uclm.louise.ediaries.data.requests.CreateDPersonalesRequest;
import com.uclm.louise.ediaries.data.requests.CreateDiarioEmocionesRequest;
import com.uclm.louise.ediaries.data.requests.CreateTareaDiariaRequest;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;
import com.uclm.louise.ediaries.data.requests.LoginRequest;
import com.uclm.louise.ediaries.data.requests.UpdateTareaDiariaRequest;
import com.uclm.louise.ediaries.data.responses.CreateUsuarioResult;
import com.uclm.louise.ediaries.data.responses.LoginResponse;
import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST(LOGIN_URL)
    Call<LoginResponse> login(@Body LoginRequest request);

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


    @GET(TAREA_DIARIA_URL+GET_TAREAS+"{childId}")
    Call<List<SearchTareaDiariaResult>> fetchTareasDiarias(@Header ("Authorization") String token, @Path("childId") Integer childId);
    @POST(TAREA_DIARIA_URL+NEW)
    Call<TareaDiaria> createTareaDiaria(@Header ("Authorization") String token, @Body CreateTareaDiariaRequest tareaDiaria);
    @PUT(TAREA_DIARIA_URL+UPDATE+"/"+"{tareaId}")
    Call<TareaDiaria> updateTareaDiaria(@Header ("Authorization") String token, @Body UpdateTareaDiariaRequest tareaDiaria, @Path("tareaId") Integer tareaId);
    @PUT(TAREA_DIARIA_URL+"{tareaId}"+"/"+DONE)
    Call<Response<Void>> setTareaDiariaTerminada(@Header ("Authorization") String token, @Path("tareaId") Integer tareaId);
    @DELETE(TAREA_DIARIA_URL+"{tareaId}")
    Call<Response<Void>> deleteTareaDiaria(@Header ("Authorization") String token, @Path("tareaId") Integer tareaId);

    @POST(DIARIO_EMOCIONES_URL+NEW)
    Call<DiarioEmociones> createDiarioEmociones(@Header ("Authorization") String token, @Body CreateDiarioEmocionesRequest diarioEmociones);
}
