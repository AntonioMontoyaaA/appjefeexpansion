package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatosConstruccion {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("construccion")
    @Expose
    private List<Construccion> construccion = null;

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

    public List<Construccion> getConstruccion() {
        return construccion;
    }

    public void setConstruccion(List<Construccion> construccion) {
        this.construccion = construccion;
    }

    public class Detalle {

        @SerializedName("nombredetalle")
        @Expose
        private String nombredetalle;
        @SerializedName("detalleid")
        @Expose
        private Integer detalleid;

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

    }

    public class Construccion {

        @SerializedName("nombrenivel")
        @Expose
        private String nombrenivel;
        @SerializedName("usuario")
        @Expose
        private String usuario;
        @SerializedName("puntos")
        @Expose
        private Integer puntos;
        @SerializedName("nivelid")
        @Expose
        private Integer nivelid;
        @SerializedName("detalles")
        @Expose
        private List<Detalle> detalles = null;
        @SerializedName("facorbusquedaid")
        @Expose
        private Integer facorbusquedaid;
        @SerializedName("fecharegistro")
        @Expose
        private String fecharegistro;

        public String getNombrenivel() {
            return nombrenivel;
        }

        public void setNombrenivel(String nombrenivel) {
            this.nombrenivel = nombrenivel;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
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

        public List<Detalle> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<Detalle> detalles) {
            this.detalles = detalles;
        }

        public Integer getFacorbusquedaid() {
            return facorbusquedaid;
        }

        public void setFacorbusquedaid(Integer facorbusquedaid) {
            this.facorbusquedaid = facorbusquedaid;
        }

        public String getFecharegistro() {
            return fecharegistro;
        }

        public void setFecharegistro(String fecharegistro) {
            this.fecharegistro = fecharegistro;
        }

    }

}
