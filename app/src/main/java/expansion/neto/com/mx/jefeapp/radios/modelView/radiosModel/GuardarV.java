package expansion.neto.com.mx.jefeapp.radios.modelView.radiosModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuardarV {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

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
}
