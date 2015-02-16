package client;

public class ForEnemy extends Thread {

	private Client cli;

	public ForEnemy(Client cli){
		this.cli = cli;
	}
	
	    public void run() {
	    	while(true){
	        System.out.println("Hello from a thread!");
	        try {
	      
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}
	    }

	   

	

}
