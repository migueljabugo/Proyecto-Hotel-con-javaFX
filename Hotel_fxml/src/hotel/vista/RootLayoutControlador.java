package hotel.vista;

import java.io.File;
import java.io.IOException;

import hotel.Miapp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class RootLayoutControlador {

	private Miapp miapp;
	
	public void setMiapp(Miapp miapp) {
		this.miapp=miapp;
	}
	
	@FXML
	private void pulsarEstadistica(){
		//Modificar menu
		try {
            
			
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Miapp.class.getResource("vista/Estadistica.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
           

            // Set person overview into the center of root layout.
            this.miapp.getRootLayout().setCenter(personOverview);
            EstadisticaControlador controlador=loader.getController();
            controlador.setMiapp(this.miapp);

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	@FXML
	public void verOcupacionPorMes(){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Miapp.class.getResource("vista/OcupacionPorMes.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            this.miapp.getRootLayout().setCenter(personOverview);
            OcupacionPorMesControlador controlador=loader.getController();
            controlador.setMiapp(this.miapp);
            controlador.grafica();
            controlador.calcularProgressIndicators();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	@FXML
	public void cargarGestion(){
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Miapp.class.getResource("vista/inicio.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            miapp.getRootLayout().setCenter(personOverview);
            InicioControlador controlador=loader.getController();
            controlador.setMiapp(this.miapp);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void cargarHabitaciones(){
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Miapp.class.getResource("vista/habitaciones.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            miapp.getRootLayout().setCenter(personOverview);
            
            
            HabitacionesControlador controlador=loader.getController();
            controlador.setMiapp(this.miapp);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
	
	
	
	@FXML
    private void pulsarNuevo() {
		miapp.getDatosReserva();
		miapp.setRutaFicheroReservas(null);
    }
	
	
	@FXML
    private void pulsarAbrir() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(miapp.getPrimaryStage());

        if (file != null) {
            miapp.cargarDatosReservasDeFichero(file);
        }
    }
	
	
	 @FXML
    private void pulsarGuardar() {
        File File = miapp.getRutaFicheroReservas();
        if (File != null) {
            miapp.guardarDatosReservaFichero(File);
        } else {
            pulsarGuardarComo();
        }
    }
	 
	 
	 @FXML
    private void pulsarGuardarComo() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(miapp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            miapp.guardarDatosReservaFichero(file);
        }
    }
	
	 @FXML
    private void pulsarDetalles() {
		 Alert alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("Detalles");
			alert.setHeaderText("Autor:\n	Miguel Angel González Hernández\n"
					+ "		2º DAM - 2016/2017\n"
					+ "		I.E.S. Julio Verne"
					+"\n\n\nVersion 1.0");
			alert.setContentText("");
			DialogPane dp=alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
			alert.show();
    }
	 
	 @FXML
	 private void pulsarAyuda(){
		 File file = new File("resources/Ayuda Hotel.chm");
		 try
		 {
			 Runtime.getRuntime().exec("HH.EXE ms-its:" + file.getAbsolutePath() );
		 } catch (IOException e1)
		 {
			 e1.printStackTrace();
		 }
	 }
	 
	 @FXML
    private void pulsarSalir(){
        System.exit(0);
    }
	 
	

}
