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
	private Mensaje[] mensajes;
	
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
		mensajes = new Mensaje[1];
		for(int i = 0;i <mensajes.length;i++ ) {
			mensajes[i].setPregunta("pregunta #" + id +i);
		}
		this.buffer = buffer;
		this.id = id;
	}
	
	@Override
	public void run() {
		for(int i = 0;i<mensajes.length; i++) {
			while( !buffer.enviar(mensajes[i])) {
				yield();
			}
			try {
				mensajes[i].wait();
			}catch (Exception e) {
				e.getStackTrace();
			}
			mensajes[i] = buffer.atender();
		}
	}
}
