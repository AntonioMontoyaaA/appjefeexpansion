package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Autorizadas {

    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("autorizadas")
    @Expose
    private List<Autorizada> autorizadas = null;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public List<Autorizada> getAutorizadas() {
        return autorizadas;
    }

    public void setAutorizadas(List<Autorizada> autorizadas) {
        this.autorizadas = autorizadas;
    }


    public class Autorizada {

        @SerializedName("memoriaid")
        @Expose
        private String memoriaid;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("puntuacion")
        @Expose
        private Integer puntuacion;
        @SerializedName("fechaatorizacion")
        @Expose
        private String fechaatorizacion;
        @SerializedName("nombresitio")
        @Expose
        private String nombresitio;

        public String getMemoriaid() {
            return memoriaid;
        }

        public void setMemoriaid(String memoriaid) {
            this.memoriaid = memoriaid;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public Integer getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Integer puntuacion) {
            this.puntuacion = puntuacion;
        }

        public String getFechaatorizacion() {

            String fecha = fechaatorizacion.substring(0, 10);

            return fecha;
        }

        public void setFechaatorizacion(String fechaatorizacion) {
            this.fechaatorizacion = fechaatorizacion;
        }

        public String getNombresitio() {
            return nombresitio;
        }

        public void setNombresitio(String nombresitio) {
            this.nombresitio = nombresitio;
        }

    }
}
