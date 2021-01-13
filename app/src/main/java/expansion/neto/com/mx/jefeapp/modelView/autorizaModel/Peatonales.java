package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Peatonales implements SortedListAdapter.ViewModel {

    @SerializedName("conteos")
    @Expose
    private List<Conteo> conteos = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("detallesValidacion")
    @Expose
    private List<DatosConstruccions.DetallesValidacion> detallesValidacion = null;

    public List<DatosConstruccions.DetallesValidacion> getDetallesValidacion() {
        return detallesValidacion;
    }

    public void setDetallesValidacion(List<DatosConstruccions.DetallesValidacion> detallesValidacion) {
        this.detallesValidacion = detallesValidacion;
    }

    public List<Conteo> getConteos() {
        return conteos;
    }

    public void setConteos(List<Conteo> conteos) {
        this.conteos = conteos;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public class Conteo {

        @SerializedName("descipcion")
        @Expose
        private String descipcion;
        @SerializedName("factorId")
        @Expose
        private Integer factorId;
        @SerializedName("toleranciaMaxima")
        @Expose
        private Integer toleranciaMaxima;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;
        @SerializedName("toleeranciaMinima")
        @Expose
        private Integer toleeranciaMinima;
        @SerializedName("detalle")
        @Expose
        private List<Detalle> detalle = null;
        @SerializedName("unidadMedicion")
        @Expose
        private String unidadMedicion;
        @SerializedName("promedioPeatonal")
        @Expose
        private Integer promedioPeatonal;

        public Integer getPromedioPeatonal() {
            return promedioPeatonal;
        }

        public void setPromedioPeatonal(Integer promedioPeatonal) {
            this.promedioPeatonal = promedioPeatonal;
        }

        public String getDescipcion() {
            return descipcion;
        }

        public void setDescipcion(String descipcion) {
            this.descipcion = descipcion;
        }

        public Integer getFactorId() {
            return factorId;
        }

        public void setFactorId(Integer factorId) {
            this.factorId = factorId;
        }

        public Integer getToleranciaMaxima() {
            return toleranciaMaxima;
        }

        public void setToleranciaMaxima(Integer toleranciaMaxima) {
            this.toleranciaMaxima = toleranciaMaxima;
        }

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

        public Integer getToleeranciaMinima() {
            return toleeranciaMinima;
        }

        public void setToleeranciaMinima(Integer toleeranciaMinima) {
            this.toleeranciaMinima = toleeranciaMinima;
        }

        public List<Detalle> getDetalle() {
            return detalle;
        }

        public void setDetalle(List<Detalle> detalle) {
            this.detalle = detalle;
        }

        public String getUnidadMedicion() {
            return unidadMedicion;
        }

        public void setUnidadMedicion(String unidadMedicion) {
            this.unidadMedicion = unidadMedicion;
        }

    }


    public class Detalle {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("fecha")
        @Expose
        private String fecha;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("valor")
        @Expose
        private String valor;

        public String getNombreGenerador() {
            return nombreGenerador;
        }

        public void setNombreGenerador(String nombreGenerador) {
            this.nombreGenerador = nombreGenerador;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public Integer getGeneradorId() {
            return generadorId;
        }

        public void setGeneradorId(Integer generadorId) {
            this.generadorId = generadorId;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

    }


    public void onClickCalificar(String algo) {
       // Log.e("*****", algo);
    }


}




