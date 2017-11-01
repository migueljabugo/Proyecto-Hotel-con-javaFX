package hotel;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import hotel.modelo.Reserva;
import hotel.modelo.ReservaListWrapper;
import hotel.modelo.TipoHabitacion;
import hotel.vista.InicioControlador;
import hotel.vista.ReservaControlador;
import hotel.vista.RootLayoutControlador;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Miapp extends Application {

	 private Stage primaryStage;
	 private BorderPane rootLayout;
	 private ObservableList<Reserva> datosReserva= FXCollections.observableArrayList();
	private int numeroTotalHabitaciones;
	
	 public BorderPane getRootLayout() {
		return rootLayout;
	}




	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}




	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}




	public Miapp(){
		 this.numeroTotalHabitaciones=60;
//		 insertarReservas(2011,2011,50);
//		 insertarReservas(2012,2012,50);
//		 insertarReservas(2013,2013,50);
//		 insertarReservas(2014,2014,100);
		 insertarReservas(2015,2015,200);
		 insertarReservas(2016,2016,200);
		 insertarReservas(2017,2017,200);
//		 insertarReservas(2018,2018,100);
//		 insertarReservas(2019,2019,50);
//		 insertarReservas(2020,2020,50);
//		 insertarReservas(2021,2021,50);
		 
	}
	

	 
	 
	 private void insertarReservas(int anno, int anno2, int cantidad) {
		 for(int i=0;i<cantidad;i++){
			 int mesEntrada, diaEntrada, mesSalida, diaSalida;
			 mesEntrada=0;
			 diaEntrada=0;
			 mesSalida=0;
			 diaSalida=0;
			 TipoHabitacion tipo=TipoHabitacion.DOBLE;
			 do{
				 mesEntrada= (int) (Math.random()*+100.);
			 }while(mesEntrada<1 || mesEntrada>12 );
			 do{
				 mesSalida= (int) (Math.random()*+100.);
			 }while(mesSalida>12 || mesSalida<1 || mesEntrada>mesSalida);
			 do{
				 diaEntrada= (int) (Math.random()*+100.);
			 }while(diaEntrada>28 || diaEntrada<1);
			 do{
				 diaSalida= (int) (Math.random()*+100.);
			 }while(diaSalida>12 || diaSalida<1);
			 int valor=(int) (Math.random()*10.);
			 if (valor==1 || valor==2 || valor==3 || valor==4){
				 tipo=TipoHabitacion.DOBLE;
			 }
			 if (valor==5 || valor==6 || valor==7){
				 tipo=TipoHabitacion.DOBLE_INDIVIDUAL;
			 }
			 if (valor==8||valor==10){
				 tipo=TipoHabitacion.JUNIOR_SUITE;
			 }
			 if (valor==9){
				 tipo=TipoHabitacion.SUITE;
			 }
			 valor=(int) (Math.random()*100000000.);
			 String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKE";
			    int modulo= valor % 23;
			    char letra = juegoCaracteres.charAt(modulo);
			/* System.out.println("Mes llegada: "+mesEntrada+
					 "\nDia llegada: "+diaEntrada+
					 "\nMes salida: "+mesSalida+
					 "\nDia salida: "+diaSalida+
					 "\nTipoHabitacion: "+tipo);*/
			 datosReserva.add(new Reserva("Ramon",valor+""+letra,"ñlakdjf","Sevilla","Sevilla",LocalDate.of(anno, mesEntrada, diaEntrada),LocalDate.of(anno2, mesSalida,diaSalida),tipo,1,false,false,true,false));
		
		 }
		 
		
	}




	public ObservableList<Reserva> getDatosReserva() {
	        return datosReserva;
	    }
	
	 public static void main(String[] args) {
	        launch(args);
	    }
	 
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("RENAISSANCE");
        //this.primaryStage.setMinHeight(600);
       // this.primaryStage.setMinWidth(800);
        this.primaryStage.getIcons().add(new Image("file:resources/imagenes/icono.png"));
        initRootLayout();
        cargarInicio();
		
	}
	
	private void cargarInicio() {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Miapp.class.getResource("vista/inicio.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            this.getRootLayout().setCenter(personOverview);
            InicioControlador controlador=loader.getController();
            controlador.setMiapp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	public void initRootLayout() {
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Miapp.class
                .getResource("vista/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        RootLayoutControlador controller = loader.getController();
        controller.setMiapp(this);

        primaryStage.show();
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file =getRutaFicheroReservas();
	    if (file != null) {
	        cargarDatosReservasDeFichero(file);
	    }
	
	
	
	
	
	
	}
	
	
	
	public Stage getPrimaryStage() {
        return primaryStage;
    }




	public void verNuevaReserva() {
		try {
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(Miapp.class.getResource("vista/Reserva.fxml"));
		 AnchorPane page = (AnchorPane) loader.load();
         Stage dialogStage = new Stage();
         dialogStage.setTitle("Nueva Reserva");
         dialogStage.setResizable(false);
         dialogStage.initModality(Modality.WINDOW_MODAL);
         dialogStage.initOwner(primaryStage);
         Scene scene = new Scene(page);
         dialogStage.setScene(scene);
         
         ReservaControlador controlador=loader.getController();
         controlador.setMiapp(this);
         controlador.setVentana(dialogStage);
         dialogStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	public void modificarReserva(int i) {
		try {
			FXMLLoader loader=new FXMLLoader();
			
			loader.setLocation(Miapp.class.getResource("vista/Reserva.fxml"));
			 AnchorPane page = (AnchorPane) loader.load();
	         Stage dialogStage = new Stage();
	         dialogStage.setTitle("Modificar Reserva");
	         dialogStage.setResizable(false);
	         dialogStage.initModality(Modality.WINDOW_MODAL);
	         dialogStage.initOwner(primaryStage);
	         Scene scene = new Scene(page);
	         dialogStage.setScene(scene);
	         
	         ReservaControlador controlador=loader.getController();
	         
	         
	         Reserva r=this.datosReserva.get(i);
	         controlador.setCampos(r, true, i);
	         controlador.setMiapp(this);
	         controlador.setVentana(dialogStage);


	         dialogStage.show();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}


	public int getNumeroTotalHabitaciones() {
		return numeroTotalHabitaciones;
	}


	public void setNumeroTotalHabitaciones(int numeroTotalHabitaciones) {
		this.numeroTotalHabitaciones = numeroTotalHabitaciones;
	}
	
	
	
	
	
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getRutaFicheroReservas() {
	    Preferences preferencias = Preferences.userNodeForPackage(Miapp.class);
	    String rutaFichero = preferencias.get("filePath", null);
	    if (rutaFichero != null) {
	        return new File(rutaFichero);
	    } else {
	        return null;
	    }
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setRutaFicheroReservas(File file) {
	    Preferences preferencias = Preferences.userNodeForPackage(Miapp.class);
	    if (file != null) {
	        preferencias.put("filePath", file.getPath());

	        // Update the stage title.
	        primaryStage.setTitle("Hotel - " + file.getName());
	    } else {
	        preferencias.remove("filePath");

	        // Update the stage title.
	        primaryStage.setTitle("AddressApp");
	    }
	}
	
	
	
	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void cargarDatosReservasDeFichero(File file) {
	    try {
	        JAXBContext context = JAXBContext.newInstance(ReservaListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        ReservaListWrapper wrapper = (ReservaListWrapper) um.unmarshal(file);

	        this.datosReserva.clear();
	        datosReserva.addAll(wrapper.getReservas());

	        // Save the file path to the registry.
	        this.setRutaFicheroReservas(file);

	    } catch (Exception e) { // catches ANY exception
	    	Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No se pudo cargar los datos del archivo:\n"+file.getPath());
			DialogPane dp=alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
			alert.show();
			e.printStackTrace();
	    }
	}
	
	
	
	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void guardarDatosReservaFichero (File file) {
	    try {
	        JAXBContext context = JAXBContext.newInstance(ReservaListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        ReservaListWrapper wrapper = new ReservaListWrapper();
	        wrapper.setReservas(datosReserva);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        this.setRutaFicheroReservas(file);
	    } catch (Exception e) { // catches ANY exception
	    	Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No se pudo guardar los datos en el archivo:\n"+file.getPath());
			DialogPane dp=alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
			alert.show();
	    }
	}
	
	
	

}
