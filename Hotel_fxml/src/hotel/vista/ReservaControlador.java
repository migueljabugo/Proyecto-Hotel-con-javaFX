package hotel.vista;

import java.time.LocalDate;
import java.util.Optional;

import hotel.Miapp;
import hotel.modelo.Reserva;
import hotel.modelo.TipoHabitacion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ReservaControlador {
	
	private Miapp miapp;
    @FXML
	private TextField dni;
    @FXML
	private TextField nombre;
    @FXML
	private TextField direccion;
    @FXML
	private TextField localidad;
    @FXML
	private TextField provincia;
    @FXML
	private DatePicker fechaLlegada;
    @FXML
	private DatePicker fechaSalida;
    @FXML
	private Spinner<Integer> numHabitaciones;
    @FXML
	private SplitMenuButton tipoHabitacion;
    @FXML
	private CheckBox fumador;
    @FXML
	private RadioButton alojamientoDesayuno;
    @FXML
	private RadioButton mediaPension;
    @FXML
	private RadioButton PensionCompleta;
    
    @FXML
    private Label labelTextofumador;
    
    private TipoHabitacion tipo;
    
    
    
    private Stage ventana;
	private boolean reservaCreada;
	private int posicionReserva;
	
	
	public void setMiapp(Miapp app){
		this.miapp=app;
	}
	
	
	public void setVentana(Stage ventana){
		this.ventana=ventana;
	}
	
	
	@FXML
	private void pulsarBotonAceptar(){
		String sNombre=nombre.getText();
		String sDni=dni.getText();
		String sDireccion=direccion.getText();
		String sLocalidad=localidad.getText();
		String sProvincia=provincia.getText();
		LocalDate llegada=fechaLlegada.getValue();
		LocalDate salida=fechaSalida.getValue();
		int numHabit=this.numHabitaciones.getValue();
		boolean bFumador=fumador.isSelected();
		
		
		
		if(sNombre.length()==0 || 
				sDni.length()==0 || 
				sDireccion.length()==0 || 
				sLocalidad.length()==0 ||
				sProvincia.length()==0){
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Existen campos vacios");
        	alert.setContentText("Rellene los campos para continuar");
        	DialogPane dp=alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        	alert.showAndWait();
			
			
		}else{
			if(this.fechaLlegada.getValue()==null || this.fechaSalida.getValue()==null){
				Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Advertencia");
				alert.setHeaderText("las fechas de llegada o salida no han sido seleccionadas");
				DialogPane dp=alert.getDialogPane();
				dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
				alert.show();
			
			}
			else{
				if(this.tipoHabitacion.getText().toString().equals("Elija uno")){
					Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Advertencia");
					alert.setHeaderText("Tipo de habitación no seleccionada");
					DialogPane dp=alert.getDialogPane();
					dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
					alert.show();
				}else{
					if(this.fechaLlegada.getValue().isBefore(LocalDate.now())||this.fechaSalida.getValue().isBefore(LocalDate.now())){
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Fechas incorrectas");
						alert.setHeaderText("Alguna de sus fechas es anterior a la fecha de solicitud");
						alert.setContentText("Tanto la fecha de llegada como la de salida deben ser posterior a la fecha actual: "+LocalDate.now().toString());
						DialogPane dp=alert.getDialogPane();
						dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
						alert.show();
					}else{
						if(this.fechaLlegada.getValue().isAfter(this.fechaSalida.getValue())){
							Alert alert=new Alert(AlertType.WARNING);
							alert.setTitle("Fechas incorrectas");
							alert.setHeaderText("La fecha de llegada debe ser anterior a la fecha de salida");
							alert.setContentText("Si su fecha de llegada es el dia "+this.fechaLlegada.getValue().toString()+", la fecha de salida por ejemplo seria "+this.fechaLlegada.getValue().plusDays(5));
							DialogPane dp=alert.getDialogPane();
							dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
							alert.show();
						}else{
							
							
							Alert alert=new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Confirmación");
							alert.setHeaderText("¿Desea guardar los cambios?");
							ButtonType botonSi = new ButtonType("Si");
							ButtonType botonNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
	
							alert.getButtonTypes().setAll(botonSi, botonNo);
							DialogPane dp=alert.getDialogPane();
							dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == botonSi){
								if(this.reservaCreada){
									this.miapp.getDatosReserva().remove(this.posicionReserva);
									Reserva reserva=new Reserva(nombre.getText(), dni.getText(), direccion.getText(), localidad.getText(), provincia.getText(), llegada, salida, tipo, numHabit, bFumador, alojamientoDesayuno.isArmed(),mediaPension.isArmed(), PensionCompleta.isArmed());
									this.miapp.getDatosReserva().add(posicionReserva, reserva);
								}
								else{
									Reserva reserva=new Reserva(nombre.getText(), dni.getText(), direccion.getText(), localidad.getText(), provincia.getText(), llegada, salida, tipo, numHabit, bFumador, alojamientoDesayuno.isArmed(),mediaPension.isArmed(), PensionCompleta.isArmed());
									this.miapp.getDatosReserva().add(reserva);
								}
								ventana.close();
							}
							
						}
					}
					
				}
				
			}
        	
		}
		
		
	}
	
	
	@FXML
	private void pulsarBotonCancelar(){
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Seguro que quiere salir?");
		ButtonType botonSalir = new ButtonType("Salir");
		ButtonType botonCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(botonSalir, botonCancelar);
		DialogPane dp=alert.getDialogPane();
		dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == botonSalir){
			ventana.close();
		}
		
		
		
	}
	
	@FXML
	private void pulsarBotonLimpiar(){
		
		dni.setText("");
		nombre.setText("");
	    direccion.setText("");
	    localidad.setText("");
	    provincia.setText("");
		fechaLlegada.setValue(null);
	    fechaSalida.setValue(null);
	    numHabitaciones.getValueFactory().setValue(1);
	    tipoHabitacion.setText("Elija uno");
	    fumador.setSelected(false);
	    alojamientoDesayuno.setSelected(false);
	    mediaPension.setSelected(false);
	    PensionCompleta.setSelected(false);
	    this.labelTextofumador.setVisible(false);
	}
	
	@FXML
	private void pulsarTipoDoble(){
		tipoHabitacion.setText("Doble");
		tipo=TipoHabitacion.DOBLE;
	}
	@FXML
	private void pulsarTipoDobleIndividual(){
		tipoHabitacion.setText("Doble de uso Individual");
		tipo=TipoHabitacion.DOBLE_INDIVIDUAL;
	}
	@FXML
	private void pulsarTipoJuniorSuite(){
		tipoHabitacion.setText("Junior Suite");
		tipo=TipoHabitacion.JUNIOR_SUITE;
	}
	@FXML
	private void pulsarTipoSuite(){
		tipoHabitacion.setText("Suite");
		tipo=TipoHabitacion.SUITE;
	}
	
	@FXML
	public void pulsarFumador(){
		if(this.labelTextofumador.isVisible()){
			this.labelTextofumador.setVisible(false);
		}else
			this.labelTextofumador.setVisible(true);
	}


	public void setCampos(Reserva r, boolean b, int i) {
		
		
		
		dni.setText(r.getDni());
		nombre.setText(r.getNombre());
	    direccion.setText(r.getDireccion());
	    localidad.setText(r.getLocalidad());
	    provincia.setText(r.getProvincia());
		fechaLlegada.setValue(r.getLlegada());
	    fechaSalida.setValue(r.getSalida());
	    numHabitaciones.getValueFactory().setValue(r.getNumHabitaciones());
	    String tipHabit=r.getTipoHabitacion().toString();
	    switch(tipHabit){
	    	case ("DOBLE_INDIVIDUAL"):
	    		tipoHabitacion.setText("Doble de uso individual");
	    		break;
	    	case ("DOBLE"):
	    		tipoHabitacion.setText("Doble");
	    		break;
	    	case "JUNIOR_SUITE":
	    		tipoHabitacion.setText("Junior suite");
	    		break;
	    	case "SUITE":
	    		tipoHabitacion.setText("Suite");
	    		break;
	    	
	    }
	    
	    
	    
	    
	    fumador.setSelected(r.getFumador());
	    alojamientoDesayuno.setSelected(r.getAlojamientoDesayuno());
	    mediaPension.setSelected(r.getMediaPension());
	    PensionCompleta.setSelected(r.getPensionCompleta());
	    this.labelTextofumador.setVisible(r.getFumador());
	    reservaCreada=true;
	    posicionReserva=i;
		
	}
	
	
	
	
	
	

}
