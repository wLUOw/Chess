package util;


import view.GamePanel;

public class Timer extends Thread{
    public static int time = 50;
    public GamePanel gp;

    @Override
    public void run(){
        while (true){
            int player = gp.getCurrentPlayer();
            while(time > 0) {
                time--;
                try {
                    Thread.sleep(1000);
                    gp.getHintTimeLabel().setText("时间：" + time);
                    //System.out.println("还剩" + time + "秒");
                    if (gp.getCurrentPlayer() != player){
                        gp.overMyTurn();
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gp.overMyTurn();
            time = 50;
        }

    }

    public Timer(GamePanel gp){
        this.gp = gp;
    }

}



