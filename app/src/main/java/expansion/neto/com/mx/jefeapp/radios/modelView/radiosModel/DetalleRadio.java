
package expansion.neto.com.mx.jefeapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetalleRadio {

    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("poblacion")
    @Expose
    private List<Poblacion> poblacion = null;
    @SerializedName("fechaAsignado")
    @Expose
    private String fechaAsignado;
    @SerializedName("radioId")
    @Expose
    private String radioId;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("estatusRadioId")
    @Expose
    private Integer estatusRadioId;
    @SerializedName("generadores")
    @Expose
    private List<Generadore> generadores = null;
    @SerializedName("tipoEstrategia")
    @Expose
    private String tipoEstrategia;
    @SerializedName("ubicacion")
    @Expose
    private List<Ubicacion> ubicacion = null;
    @SerializedName("estatusRadio")
    @Expose
    private String estatusRadio;
    @SerializedName("nombreRadio")
    @Expose
    private String nombreRadio;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public List<Poblacion> getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(List<Poblacion> poblacion) {
        this.poblacion = poblacion;
    }

    public String getFechaAsignado() {
        return fechaAsignado;
    }

    public void setFechaAsignado(String fechaAsignado) {
        this.fechaAsignado = fechaAsignado;
    }

    public String getRadioId() {
        return radioId;
    }

    public void setRadioId(String radioId) {
        this.radioId = radioId;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Integer getEstatusRadioId() {
        return estatusRadioId;
    }

    public void setEstatusRadioId(Integer estatusRadioId) {
        this.estatusRadioId = estatusRadioId;
    }

    public List<Generadore> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(List<Generadore> generadores) {
        this.generadores = generadores;
    }

    public String getTipoEstrategia() {
        return tipoEstrategia;
    }

    public void setTipoEstrategia(String tipoEstrategia) {
        this.tipoEstrategia = tipoEstrategia;
    }

    public List<Ubicacion> getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(List<Ubicacion> ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstatusRadio() {
        return estatusRadio;
    }

    public void setEstatusRadio(String estatusRadio) {
        this.estatusRadio = estatusRadio;
    }

    public String getNombreRadio() {
        return nombreRadio;
    }

    public void setNombreRadio(String nombreRadio) {
        this.nombreRadio = nombreRadio;
    }

}
