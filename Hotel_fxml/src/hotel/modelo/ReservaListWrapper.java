package hotel.modelo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "reservas")
public class ReservaListWrapper {
	private List<Reserva> reservas;
	
	
	@XmlElement(name="reserva")
	public List<Reserva> getReservas(){
		return reservas;
	}
	
	public void setReservas(List<Reserva> reservas){
		this.reservas=reservas;
	}
	
	
	
}
