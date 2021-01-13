package expansion.neto.com.mx.jefeapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatosConstruccion {


    @SerializedName("mdId")
    @Expose
    private String mdId;
    @SerializedName("usuarioId")
    @Expose
    private String usuarioId;
    @SerializedName("factorId")
    @Expose
    private String factorId;
    @SerializedName("numtelefono")
    @Expose
    private String numtelefono;
    @SerializedName("versionapp")
    @Expose
    private String versionapp;
    @SerializedName("niveles")
    @Expose
    private List<Nivele> niveles = null;

    public DatosConstruccion(String mdId, String usuarioId, String factorId, String numtelefono, String versionapp, List<Nivele> niveles) {
        this.mdId = mdId;
        this.usuarioId = usuarioId;
        this.factorId = factorId;
        this.numtelefono = numtelefono;
        this.versionapp = versionapp;
        this.niveles = niveles;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFactorId() {
        return factorId;
    }

    public void setFactorId(String factorId) {
        this.factorId = factorId;
    }

    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
    }

    public String getVersionapp() {
        return versionapp;
    }

    public void setVersionapp(String versionapp) {
        this.versionapp = versionapp;
    }

    public List<Nivele> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivele> niveles) {
        this.niveles = niveles;
    }


    public static class Nivele {

        @SerializedName("NIVELID")
        @Expose
        private Integer nivelId;
        @SerializedName("VALOR")
        @Expose
        private String valor;
        @SerializedName("DETALLES")
        @Expose
        private List<Detalle> detalles = null;

        public Nivele(Integer nivelId, String valor, List<Detalle> detalles) {
            this.nivelId = nivelId;
            this.valor = valor;
            this.detalles = detalles;
        }

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

        public List<Detalle> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<Detalle> detalles) {
            this.detalles = detalles;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }
    }

    public static class Detalle {

        @SerializedName("DETALLEID")
        @Expose
        private Integer detalleId;
        @SerializedName("COMENTARIO")
        @Expose
        private String comentario;

        public Detalle(Integer detalleId, String comentario) {
            this.comentario = comentario;
            this.detalleId = detalleId;
        }

        public Integer getDetalleId() {
            return detalleId;
        }

        public void setDetalleId(Integer detalleId) {
            this.detalleId = detalleId;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }
    }

}
