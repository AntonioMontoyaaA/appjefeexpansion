package expansion.neto.com.mx.jefeapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Amortizacion {

    @SerializedName("amortizacion")
    @Expose
    private List<Amortizacio> amortizacion = null;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("gracia")
    @Expose
    private List<Gracium> gracia = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;

    public List<Amortizacio> getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(List<Amortizacio> amortizacion) {
        this.amortizacion = amortizacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Gracium> getGracia() {
        return gracia;
    }

    public void setGracia(List<Gracium> gracia) {
        this.gracia = gracia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public class Amortizacio {

        @SerializedName("opcion")
        @Expose
        private String opcion;

        public String getOpcion() {
            return opcion;
        }

        public void setOpcion(String opcion) {
            this.opcion = opcion;
        }

    }

    public class Gracium {

        @SerializedName("opcion")
        @Expose
        private String opcion;

        public String getOpcion() {
            return opcion;
        }

        public void setOpcion(String opcion) {
            this.opcion = opcion;
        }

    }

}
