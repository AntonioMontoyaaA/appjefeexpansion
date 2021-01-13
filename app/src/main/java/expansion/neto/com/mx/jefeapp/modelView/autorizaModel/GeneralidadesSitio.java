package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class GeneralidadesSitio {


    @SerializedName("generalidades")
    @Expose
    private List<Generalidade> generalidades = null;
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

    public List<Generalidade> getGeneralidades() {
        return generalidades;
    }

    public void setGeneralidades(List<Generalidade> generalidades) {
        this.generalidades = generalidades;
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

    public class Generalidade {

        @SerializedName("detalles")
        @Expose
        private List<Detalle> detalles = null;
        @SerializedName("fechadisponible")
        @Expose
        private String fechadisponible;
        @SerializedName("nombrenivel")
        @Expose
        private String nombrenivel;
        @SerializedName("facorbusquedaid")
        @Expose
        private Integer facorbusquedaid;
        @SerializedName("valor")
        @Expose
        private Integer valor;
        @SerializedName("fecharegistro")
        @Expose
        private String fecharegistro;
        @SerializedName("puntos")
        @Expose
        private Integer puntos;
        @SerializedName("nivelid")
        @Expose
        private Integer nivelid;

        public List<Detalle> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<Detalle> detalles) {
            this.detalles = detalles;
        }

        public String getFechadisponible() {
            return fechadisponible;
        }

        public void setFechadisponible(String fechadisponible) {
            this.fechadisponible = fechadisponible;
        }

        public String getNombrenivel() {
            return nombrenivel;
        }

        public void setNombrenivel(String nombrenivel) {
            this.nombrenivel = nombrenivel;
        }

        public Integer getFacorbusquedaid() {
            return facorbusquedaid;
        }

        public void setFacorbusquedaid(Integer facorbusquedaid) {
            this.facorbusquedaid = facorbusquedaid;
        }

        public Integer getValor() {
            return valor;
        }

        public void setValor(Integer valor) {
            this.valor = valor;
        }

        public String getFecharegistro() {
            return fecharegistro;
        }

        public void setFecharegistro(String fecharegistro) {
            this.fecharegistro = fecharegistro;
        }

        public Integer getPuntos() {
            return puntos;
        }

        public void setPuntos(Integer puntos) {
            this.puntos = puntos;
        }

        public Integer getNivelid() {
            return nivelid;
        }

        public void setNivelid(Integer nivelid) {
            this.nivelid = nivelid;
        }

    }

    public class Detalle {

        @SerializedName("nombredetalle")
        @Expose
        private String nombredetalle;
        @SerializedName("detalleid")
        @Expose
        private Integer detalleid;
        @SerializedName("unidadmedicion")
        @Expose
        private String unidadmedicion;
        @SerializedName("valor")
        @Expose
        private Integer valor;

        public String getNombredetalle() {
            return nombredetalle;
        }

        public void setNombredetalle(String nombredetalle) {
            this.nombredetalle = nombredetalle;
        }

        public Integer getDetalleid() {
            return detalleid;
        }

        public void setDetalleid(Integer detalleid) {
            this.detalleid = detalleid;
        }

        public String getUnidadmedicion() {
            return unidadmedicion;
        }

        public void setUnidadmedicion(String unidadmedicion) {
            this.unidadmedicion = unidadmedicion;
        }

        public Integer getValor() {
            return valor;
        }

        public void setValor(Integer valor) {
            this.valor = valor;
        }

    }

}
