package hotel.modelo;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import hotel.util.LocalDateAdapter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

//@XmlRootElement(name="reserva")
@XmlType(propOrder = {"nombre","dni","direccion","localidad","provincia","llegada","salida", "tipoHabitacion","numHabitaciones", "fumador","alojamientoDesayuno","mediaPension","pensionCompleta"})
public class Reserva {
	@FXML
	private ObjectProperty<LocalDate> llegada;
	@FXML
	private ObjectProperty<LocalDate> salida;
	@FXML
	private ObjectProperty<TipoHabitacion> tipoHabitacion;
	@FXML
	private IntegerProperty numHabitaciones;
	@FXML
	private BooleanProperty fumador; 
	@FXML
	private BooleanProperty alojamientoDesayuno;
	@FXML
	private BooleanProperty mediaPension;
	@FXML
	private BooleanProperty pensionCompleta;
	
	private StringProperty nombre;
	private StringProperty dni;
	private StringProperty direccion;
	private StringProperty localidad;
	private StringProperty provincia;
	
	
	public Reserva(){
		this(null,null,null,null,null,null, null,null, 0, false, false, false, false);
	}
	public Reserva(String nombre, String dni, String direccion, String localidad, String provincia, 
			LocalDate llegada, LocalDate salida, TipoHabitacion tipo, int numHabit, 
			boolean fumador,boolean alojamientoDesayuno, boolean mediaPension, boolean pensionCompleta){
		this.nombre=new SimpleStringProperty(nombre);
		this.dni=new SimpleStringProperty(dni);
		this.direccion= new SimpleStringProperty(direccion);
		this.localidad=new SimpleStringProperty(localidad);
		this.provincia=new SimpleStringProperty(provincia);
		this.llegada=new SimpleObjectProperty<LocalDate>(llegada);
		this.salida=new SimpleObjectProperty<LocalDate>(salida);
		this.tipoHabitacion=new SimpleObjectProperty<TipoHabitacion>(tipo);
		this.numHabitaciones=new SimpleIntegerProperty(numHabit);
		this.fumador=new SimpleBooleanProperty(fumador);
		this.alojamientoDesayuno=new SimpleBooleanProperty(alojamientoDesayuno);
		this.mediaPension=new SimpleBooleanProperty(mediaPension);
		this.pensionCompleta=new SimpleBooleanProperty(pensionCompleta);
	}
	
	
	/*
	public ObjectProperty<TipoHabitacion> getTipoHabitacion() {
		return tipoHabitacion;
	}
*/



	public void setTipoHabitacion(ObjectProperty<TipoHabitacion> tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}



	
	public BooleanProperty getPropertyFumador() {
		return fumador;
	}
	
	@XmlElement(name = "fumador")
	public boolean getFumador(){
		return this.fumador.get();
	}




	public void setFumador(BooleanProperty fumador) {
		this.fumador = fumador;
	}




	public void setLlegada(ObjectProperty<LocalDate> llegada) {
		this.llegada = llegada;
	}




	public void setSalida(ObjectProperty<LocalDate> salida) {
		this.salida = salida;
	}




	public void setNumHabitaciones(IntegerProperty numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}



	public boolean isFumador() {
		return fumador.get();
	}




	public void setFumador(boolean fumador) {
		this.fumador.set(fumador);
	}



	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@XmlElement(name = "llegada")
	public LocalDate getLlegada() {
		return llegada.get();
	}


	public void setLlegada(LocalDate llegada) {
		this.llegada.set(llegada);
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@XmlElement(name = "salida")
	public LocalDate getSalida() {
		return salida.get();
	}


	public void setSalida(LocalDate salida) {
		this.salida.set(salida);
	}

	@XmlElement(name = "tipoHabitacion")
	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion.get();
	}


	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion.set(tipoHabitacion);
	}

	@XmlElement(name = "numHabitaciones")
	public int getNumHabitaciones() {
		return numHabitaciones.get();
	}


	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones.set(numHabitaciones);
	}


	
	public StringProperty fechaLlegadaProperty(){
		StringProperty s=new SimpleStringProperty(llegada.get().getDayOfMonth()+"-"+llegada.get().getMonthValue()+"-"+llegada.get().getYear());
		return s;
	}
	public StringProperty fechaSalidaProperty(){
		StringProperty s=new SimpleStringProperty(salida.get().getDayOfMonth()+"-"+salida.get().getMonthValue()+"-"+salida.get().getYear());
		return s;
	}
	



	
	public BooleanProperty getAlojamientoDesayunoProperty() {
		return alojamientoDesayuno;
	}

	@XmlElement(name = "alojamientoDesayuno")
	public boolean getAlojamientoDesayuno(){
		return this.alojamientoDesayuno.get();
	}



	public void setAlojamientoDesayuno(BooleanProperty alojamientoDesayuno) {
		this.alojamientoDesayuno = alojamientoDesayuno;
	}



	
	public BooleanProperty getMediaPensionProperty() {
		return mediaPension;
	}

	@XmlElement(name = "mediaPension")
	public boolean getMediaPension() {
		return mediaPension.get();
	}


	public void setMediaPension(BooleanProperty mediaPension) {
		this.mediaPension = mediaPension;
	}



	
	public BooleanProperty getPensionCompletaProperty() {
		return pensionCompleta;
	}

	@XmlElement(name = "pensionCompleta")
	public boolean getPensionCompleta(){
		return this.pensionCompleta.get();
	}


	public void setPensionCompleta(BooleanProperty pensionCompleta) {
		this.pensionCompleta = pensionCompleta;
	}
	
	@XmlElement(name = "nombre")
	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	@XmlElement(name = "dni")
	public String getDni() {
		return dni.get();
	}

	public void setDni(String dni) {
		this.dni.set(dni);
	}
	@XmlElement(name = "direccion")
	public String getDireccion() {
		return direccion.get();
	}

	public void setDireccion(String direccion) {
		this.direccion.set(direccion);
	}
	@XmlElement(name = "localidad")
	public String getLocalidad() {
		return localidad.get();
	}

	public void setLocalidad(String localidad) {
		this.localidad.set(localidad);
	}
	@XmlElement(name = "provincia")
	public String getProvincia() {
		return provincia.get();
	}

	public void setProvincia(String provincia) {
		this.provincia.set(provincia);
	}
	
	public StringProperty getDniProperty(){
		return this.dni;
	}

}
