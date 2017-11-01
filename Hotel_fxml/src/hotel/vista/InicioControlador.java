package hotel.vista;

import java.time.LocalDate;
import java.util.Optional;

import hotel.Miapp;
import hotel.modelo.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class InicioControlador {
	private Miapp miapp;
	@FXML
    private TableView<Reserva> tablaReservas;
	@FXML
	private TableColumn<Reserva,String> columnaLlegada;
	@FXML
	private TableColumn<Reserva,String> columnaSalida;
	@FXML
	private TableColumn<Reserva,String> columnaCliente;
	
	@FXML
	private ProgressIndicator ocupacion;
	
	
	public InicioControlador(){}
	
	@FXML
	private void initialize(){
		// Initialize the person table with the two columns.
		columnaLlegada.setCellValueFactory(cellData -> cellData.getValue().fechaLlegadaProperty());
		columnaSalida.setCellValueFactory(cellData -> cellData.getValue().fechaSalidaProperty());
		columnaCliente.setCellValueFactory(cellData-> cellData.getValue().getDniProperty());
		
		
		
		}
	
	
	
	public void setMiapp(Miapp miapp){
		this.miapp=miapp;
		tablaReservas.setItems(miapp.getDatosReserva());
		this.calcularOcupacion();
	}
	
	
	private void calcularOcupacion() {
		int ocup =0;
		LocalDate hoy=LocalDate.now();
		for(Reserva r: this.miapp.getDatosReserva()){
			if(r.getLlegada().isBefore(hoy) && r.getSalida().isAfter(hoy)){
				ocup+=1;
			}
		}
		float porcentaje=(float)ocup/(float)this.miapp.getNumeroTotalHabitaciones();
		System.out.println("Porcentaje ocupado: "+ porcentaje+"		Habitaciones ocupadas: "+ocup+"/"+this.miapp.getNumeroTotalHabitaciones());
		this.ocupacion.setProgress(porcentaje);
		
		
	}

	@FXML
	private void pulsarBotonReservas(){
		miapp.verNuevaReserva();
		this.calcularOcupacion();
	}
	
	@FXML
	private void pulsarBotonModificarReserva(){
		try{
			Reserva r=tablaReservas.getSelectionModel().getSelectedItem();
			for(int i=0;i<miapp.getDatosReserva().size();i++){
				if(r.equals(miapp.getDatosReserva().get(i))){
					miapp.modificarReserva(i);
				}
			}
			this.calcularOcupacion();
		}catch (RuntimeException e){
			System.out.println("Nada seleccionado");
		}
	}
	
	@FXML
	private void pulsarBotonEliminarReserva(){
		try{
			Reserva reserva=tablaReservas.getSelectionModel().getSelectedItem();
			for(int i=0;i<miapp.getDatosReserva().size();i++){
				if(reserva.equals(miapp.getDatosReserva().get(i))){
		
					Alert alert=new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmación");
					alert.setHeaderText("¿Seguro que quiere eliminar la reserva?");
					alert.setContentText("Llegada: "+reserva.getLlegada().toString()
										+"\nSalida: "+reserva.getSalida().toString()
										+"\nCliente: "+reserva.getNombre()+"	DNI: "+ reserva.getDni());
					ButtonType botonEliminar = new ButtonType("Eliminar");
					ButtonType botonCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
					
					DialogPane dp=alert.getDialogPane();
					dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
					alert.getButtonTypes().setAll(botonEliminar, botonCancelar);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == botonEliminar){
						miapp.getDatosReserva().remove(i);
					}
				}
				this.calcularOcupacion();
			}
		}catch (RuntimeException e){
			System.out.println("Nada seleccionado");
		}
	}
	
	
	
	
	
	
	
	
	

}
