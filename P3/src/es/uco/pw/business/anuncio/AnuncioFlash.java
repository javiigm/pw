package es.uco.pw.business.anuncio;

import java.text.ParseException;
import java.time.LocalDate;

import es.uco.pw.to.Estado;

/**
 * A concrete announcement that can only be seen at a specific time.
 * @author Carlos Ayuso Sanchez
 * @author Javier Gil Moya
 * */

public class AnuncioFlash extends Anuncio {
	
	private String fecha_ini;
	private String fecha_fin;
	/**
	 * setter fecha inicio de publicacion
	 * @param fecha_ini
	 */
	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	/**
	 * getter fecha inicio de publicacion
	 * @return fecha_ini
	 */
	public String getFecha_ini() {
		return fecha_ini;
	}
	/**
	 * setter fecha final de publicacion
	 * @param fecha_fin
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	/**
	 * getter fecha final de publicacion
	 * @return fecha_fin
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}
	/**
	 * compara las fechas para determinar el estado de un anuncio
	 * @param a
	 * @param fecha_ini
	 * @param fecha_fin
	 * @throws ParseException
	 */
	public void CompararFechas_ini(Anuncio a,String fecha_ini,String fecha_fin) throws ParseException {
        LocalDate dt_1 = LocalDate.parse(fecha_ini); 
        LocalDate dt_2 = LocalDate.parse(fecha_fin); 
        LocalDate date = LocalDate.now();
        
        if (date.isBefore(dt_1)) {  
            a.setEstado(Estado.en_espera);
        } 
        else if (date.isAfter(dt_2)) {  
        	a.setEstado(Estado.archivado);
        } 
        else if (date.isEqual(dt_1) || date.isBefore(dt_2)) {  
        	a.setEstado(Estado.publicado);
        }
	}
}