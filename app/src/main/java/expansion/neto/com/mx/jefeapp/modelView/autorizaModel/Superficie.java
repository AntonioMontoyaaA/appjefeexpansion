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
        @SerializedName("fecha_agua")
        @Expose
        private String fecha_agua;
        @SerializedName("fecha_luz")
        @Expose
        private String fecha_luz;
        @SerializedName("fecha_fente")
        @Expose
        private String fecha_fente;
        @SerializedName("imgEnt1")
        @Expose
        private String imgEnt1;
        @SerializedName("fecha_ent1")
        @Expose
        private String fecha_ent1;
        @SerializedName("imgEnt2")
        @Expose
        private String imgEnt2;
        @SerializedName("fecha_ent2")
        @Expose
        private String fecha_ent2;
        @SerializedName("imgEnt3")
        @Expose
        private String imgEnt3;
        @SerializedName("fecha_ent3")
        @Expose
        private String fecha_ent3;
        @SerializedName("imgAgua")
        @Expose
        private String imgAgua;
        @SerializedName("imgLuz")
        @Expose
        private String imgLuz;


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

        public String getImgPredial() {
            return imgPredial;
        }

        public void setImgPredial(String imgPredial) {
            this.imgPredial = imgPredial;
        }

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

        public String getFecha_agua() {
            return fecha_agua;
        }

        public void setFecha_agua(String fecha_agua) {
            this.fecha_agua = fecha_agua;
        }

        public String getFecha_luz() {
            return fecha_luz;
        }

        public void setFecha_luz(String fecha_luz) {
            this.fecha_luz = fecha_luz;
        }

        public String getFecha_fente() {
            return fecha_fente;
        }

        public void setFecha_fente(String fecha_fente) {
            this.fecha_fente = fecha_fente;
        }

        public String getImgEnt1() {
            return imgEnt1;
        }

        public void setImgEnt1(String imgEnt1) {
            this.imgEnt1 = imgEnt1;
        }

        public String getFecha_ent1() {
            return fecha_ent1;
        }

        public void setFecha_ent1(String fecha_ent1) {
            this.fecha_ent1 = fecha_ent1;
        }

        public String getImgEnt2() {
            return imgEnt2;
        }

        public void setImgEnt2(String imgEnt2) {
            this.imgEnt2 = imgEnt2;
        }

        public String getFecha_ent2() {
            return fecha_ent2;
        }

        public void setFecha_ent2(String fecha_ent2) {
            this.fecha_ent2 = fecha_ent2;
        }

        public String getImgEnt3() {
            return imgEnt3;
        }

        public void setImgEnt3(String imgEnt3) {
            this.imgEnt3 = imgEnt3;
        }

        public String getFecha_ent3() {
            return fecha_ent3;
        }

        public void setFecha_ent3(String fecha_ent3) {
            this.fecha_ent3 = fecha_ent3;
        }

        public String getImgAgua() {
            return imgAgua;
        }

        public void setImgAgua(String imgAgua) {
            this.imgAgua = imgAgua;
        }

        public String getImgLuz() {
            return imgLuz;
        }

        public void setImgLuz(String imgLuz) {
            this.imgLuz = imgLuz;
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
