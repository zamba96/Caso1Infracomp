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
	public Usuario(Buffer buffer, int id, int numMensajes){
		mensajes = new Mensaje[numMensajes];
		for(int i = 0;i <mensajes.length;i++ ) {
			mensajes[i] = new Mensaje();
			mensajes[i].setPregunta("Pregunta de usuario " + id + ": " + i);
			//mensajes[i].setPregunta("pregunta #" + id +i);
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
				synchronized (mensajes[i]) {
					mensajes[i].wait();
					System.out.println(mensajes[i].getRespuesta());
				}
				
			}catch (Exception e) {
				e.getStackTrace();
			}
			//mensajes[i] = buffer.atender();
		}
	}
}
