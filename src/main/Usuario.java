package main;

public class Usuario extends Thread{

	//
	/* crea el mensaje 
	 * wait a mensaje 
	 * yield si no se acepta
	 *  
	 */
	/**
	 * Mensaje que crea el usuario para enviar
	 */
	private Mensaje mensaje;
	
	/**
	 * Buffer al que envia el mensaje
	 */
	private Buffer buffer;
	
	/**
	 * Id del usuario 
	 */
	private int id;
	
	/**
	 * Constructor del cliente
	 * Crea el mensaje y le da valor a la pregunta.
	 * @param buffer Buffer que entra por parametro
	 * @param id del usuario
	 */
	public Usuario(Buffer buffer, int id){
		mensaje = new Mensaje();
		mensaje.setPregunta("pregunta #" + id);
		this.buffer = buffer;
		this.id = id;
	}
	
	@Override
	public void run() {
		while( !buffer.enviar(mensaje)) {
			yield();
		}
		try {
			mensaje.wait();
		}catch (Exception e) {
			e.getStackTrace();
		}
		mensaje = buffer.atender();
	}
}
