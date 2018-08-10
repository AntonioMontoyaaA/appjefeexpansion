package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Superficie {

    @SerializedName("puntoFac")
    @Expose
    private Integer puntoFac;
    @SerializedName("validado")
    @Expose
    private Integer validado;
    @SerializedName("editable")
    @Expose
    private Integer editable;
    @SerializedName("detallesValidacion")
    @Expose
    private List<Object> detallesValidacion = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("tip")
    @Expose
    private List<Tip> tip = null;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("niveles")
    @Expose
    private List<Nivele> niveles = null;

    public Integer getPuntoFac() {
        return puntoFac;
    }

    public void setPuntoFac(Integer puntoFac) {
        this.puntoFac = puntoFac;
    }

    public Integer getValidado() {
        return validado;
    }

    public void setValidado(Integer validado) {
        this.validado = validado;
    }

    public Integer getEditable() {
        return editable;
    }

    public void setEditable(Integer editable) {
        this.editable = editable;
    }

    public List<Object> getDetallesValidacion() {
        return detallesValidacion;
    }

    public void setDetallesValidacion(List<Object> detallesValidacion) {
        this.detallesValidacion = detallesValidacion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public List<Tip> getTip() {
        return tip;
    }

    public void setTip(List<Tip> tip) {
        this.tip = tip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Nivele> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivele> niveles) {
        this.niveles = niveles;
    }

    public class Nivele {

        @SerializedName("nivel")
        @Expose
        private Integer nivel;
        @SerializedName("nombrenivel")
        @Expose
        private String nombrenivel;
        @SerializedName("imgFrenteId")
        @Expose
        private String imgFrenteId;
        @SerializedName("fondo")
        @Expose
        private Integer fondo;
        @SerializedName("puntuacion")
        @Expose
        private Double puntuacion;
        @SerializedName("valorreal")
        @Expose
        private Integer valorreal;
        @SerializedName("imgLateral2Id")
        @Expose
        private String imgLateral2Id;
        @SerializedName("imgLateral1Id")
        @Expose
        private String imgLateral1Id;

        public Integer getNivel() {
            return nivel;
        }

        public void setNivel(Integer nivel) {
            this.nivel = nivel;
        }

        public String getNombrenivel() {
            return nombrenivel;
        }

        public void setNombrenivel(String nombrenivel) {
            this.nombrenivel = nombrenivel;
        }

        public String getImgFrenteId() {
            return imgFrenteId;
        }

        public void setImgFrenteId(String imgFrenteId) {
            this.imgFrenteId = imgFrenteId;
        }

        public Integer getFondo() {
            return fondo;
        }

        public void setFondo(Integer fondo) {
            this.fondo = fondo;
        }

        public Double getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Double puntuacion) {
            this.puntuacion = puntuacion;
        }

        public Integer getValorreal() {
            return valorreal;
        }

        public void setValorreal(Integer valorreal) {
            this.valorreal = valorreal;
        }

        public String getImgLateral2Id() {
            return imgLateral2Id;
        }

        public void setImgLateral2Id(String imgLateral2Id) {
            this.imgLateral2Id = imgLateral2Id;
        }

        public String getImgLateral1Id() {
            return imgLateral1Id;
        }

        public void setImgLateral1Id(String imgLateral1Id) {
            this.imgLateral1Id = imgLateral1Id;
        }

    }

    public class Tip {

        @SerializedName("detalle")
        @Expose
        private String detalle;

        public String getDetalle() {
            return detalle;
        }

        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }

    }

}
