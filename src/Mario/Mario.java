package Mario;

import java.util.concurrent.TimeUnit;

public class Mario {

    public Entity player;
    public KeyBoard key;
    public Level level;

public Mario(){


    float last_time = System.nanoTime();
    float time = 0;
    int delta_time = 0;

    player = new Entity(100,100,10,20);
    Level level = new Level();
    Render r = new Render(this);


  //  player.setX((int)(Math.random()*300)+1);
   //  player.setY((int)(Math.random()*500)+1);


//    int[][] temp = level.arr;

    for (int i = 0; i <level.arr.length ; i++) {
        for (int j = 0; j <level.arr[i].length ; j++) {
            System.out.print(level.arr[i][j]);
        }
        System.out.println("");
    }




    boolean temp = true;

    while(true){
        // changes delta time
        time = System.nanoTime();
        delta_time = (int) ((time - last_time) / 1000000);
        last_time = time;

    //  System.out.println(delta_time+" "+player.getSpeedX()+" "+player.getX()+" "+player.getY());



           // player.moveRight();
           // System.out.println("right");


           // player.moveleft();
         //   System.out.println("left");

        if(temp){
            temp = false;
            player.moveRight();
       //     player.jump();
        }


        //player.setX((int)(Math.random()*300)+1);
       // player.setY((int)(Math.random()*500)+1);

       player.update(delta_time);

        try {
            r.Update();
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (Exception e) {
        }

    }





}



    public static void main(String[] args) {
        Mario m = new Mario();
    }
}
