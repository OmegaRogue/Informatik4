public class Timer extends Thread
{
	GrafikPanel gpanel = null;
    int msec;
    
    public Timer( GrafikPanel gp, int millisekunden)
    {
        msec = millisekunden;
        gpanel = gp;
    }

    public void run() {
        while (true){
           gpanel.repaint();
            
            try {
                Thread.sleep(msec);
            } catch (Exception e) {
                // Exception ignorieren
            }
        }
    }
}
