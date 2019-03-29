package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Superficie {

    @SerializedName("puntoFac")
    @Expose
    private Double puntoFac;
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

    public Double getPuntoFac() {
        return puntoFac;
    }

    public void setPuntoFac(Double puntoFac) {
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
        private Double fondo;
        @SerializedName("puntuacion")
        @Expose
        private Double puntuacion;
        @SerializedName("valorreal")
        @Expose
        private Double valorreal;
        @SerializedName("imgLateral2Id")
        @Expose
        private String imgLateral2Id;
        @SerializedName("imgLateral1Id")
        @Expose
        private String imgLateral1Id;
        @SerializedName("imgPredial")
        @Expose
        private String imgPredial;

        @SerializedName("fecha_lat1")
        @Expose
        private String fecha_lat1;
        @SerializedName("fecha_lat2")
        @Expose
        private String fecha_lat2;
        @SerializedName("fecha_pred")
        @Expose
        private String fecha_pred;
        @SerializedName("fecha_fente")
        @Expose
        private String fecha_fente;
        @SerializedName("imgEntorno1Id")
        @Expose
        private String imgEntorno1Id;
        @SerializedName("fecha_entorno1")
        @Expose
        private String fecha_entorno1;
        @SerializedName("imgEntorno2Id")
        @Expose
        private String imgEntorno2Id;
        @SerializedName("fecha_entorno2")
        @Expose
        private String fecha_entorno2;
        @SerializedName("imgEntorno3Id")
        @Expose
        private String imgEntorno3Id;
        @SerializedName("fecha_entorno3")
        @Expose
        private String fecha_entorno3;
        @SerializedName("imgReciboAgua")
        @Expose
        private String imgReciboAgua;
        @SerializedName("imgReciboLuz")
        @Expose
        private String imgReciboLuz;


        public String getFecha_lat1() {
            return fecha_lat1;
        }

        public void setFecha_lat1(String fecha_lat1) {
            this.fecha_lat1 = fecha_lat1;
        }

        public String getFecha_lat2() {
            return fecha_lat2;
        }

        public void setFecha_lat2(String fecha_lat2) {
            this.fecha_lat2 = fecha_lat2;
        }

        public String getFecha_pred() {
            return fecha_pred;
        }

        public void setFecha_pred(String fecha_pred) {
            this.fecha_pred = fecha_pred;
        }

        public String getFecha_fente() {
            return fecha_fente;
        }

        public void setFecha_fente(String fecha_fente) {
            this.fecha_fente = fecha_fente;
        }

        public String getImgPredial() {
            return imgPredial;
        }

        public void setImgPredial(String imgPredial) {
            this.imgPredial = imgPredial;
        }

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

        public Double getFondo() {
            return fondo;
        }

        public void setFondo(Double fondo) {
            this.fondo = fondo;
        }

        public Double getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Double puntuacion) {
            this.puntuacion = puntuacion;
        }

        public Double getValorreal() {
            return valorreal;
        }

        public void setValorreal(Double valorreal) {
            this.valorreal = valorreal;
        }

        public String getImgLateral2Id() { return imgLateral2Id; }

        public void setImgLateral2Id(String imgLateral2Id) {
            this.imgLateral2Id = imgLateral2Id;
        }

        public String getImgLateral1Id() {
            return imgLateral1Id;
        }

        public void setImgLateral1Id(String imgLateral1Id) {
            this.imgLateral1Id = imgLateral1Id;
        }

        public String getImgEntorno1Id() { return imgEntorno1Id; }

        public void setImgEntorno1Id(String imgEntorno1Id) {
            this.imgEntorno1Id = imgEntorno1Id;
        }

        public String getFecha_entorno1() { return fecha_entorno1; }

        public void setFecha_entorno1(String fecha_entorno1) {
            this.fecha_entorno1 = fecha_entorno1;
        }

        public String getImgEntorno2Id() { return imgEntorno2Id; }

        public void setImgEntorno2Id(String imgEntorno1Id) {
            this.imgEntorno2Id = imgEntorno2Id;
        }

        public String getFecha_entorno2() { return fecha_entorno2; }

        public void setFecha_entorno2(String fecha_entorno2) {
            this.fecha_entorno2 = fecha_entorno2;
        }

        public String getImgEntorno3Id() { return imgEntorno3Id; }

        public void setImgEntorno3Id(String imgEntorno3Id) {
            this.imgEntorno3Id = imgEntorno3Id;
        }

        public String getFecha_entorno3() { return fecha_entorno3; }

        public void setFecha_entorno3(String fecha_entorno3) {
            this.fecha_entorno3 = fecha_entorno3;
        }

        public String getImgReciboAgua() { return imgReciboAgua; }

        public void setImgReciboAgua(String imgReciboAgua) {
            this.imgReciboAgua = imgReciboAgua;
        }

        public String getImgReciboLuz() { return imgReciboLuz; }

        public void setImgReciboLuz(String imgReciboLuz) {
            this.imgReciboLuz = imgReciboLuz;
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
