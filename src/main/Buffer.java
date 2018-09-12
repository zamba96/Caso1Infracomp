package main;

import java.util.ArrayList;

public class Buffer {
	private int tamañoBuffer;
	private ArrayList<Mensaje> buffer;
	private int clientes;
	private Object semaforo;
	
	public Buffer(int n){
		tamañoBuffer=n;
		buffer= new ArrayList<>();
		clientes=0;
		semaforo= new Object();
	}
	
	public void enviar(Mensaje n){
		synchronized (this) {
			buffer.add(n);
			clientes++;
			semaforo.notify();
		}
	}
	public Mensaje atender(){
		synchronized(this){
			if(buffer.isEmpty()){
				try {
					semaforo.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return buffer.remove(0);
			
		}
	}
	

}
