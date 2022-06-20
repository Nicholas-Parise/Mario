package Mario;

public class Level {

    public int[][] arr;

    Entity[][] entArr;

    public Level(){

        arr = new int[][] {{1,1,1,1,1,1},
                {1,0,0,0,0,1},
                {1,0,0,1,1,1},
                {1,0,0,0,0,1},
                {1,0,0,0,0,1},
                {1,1,1,1,1,1}};

        entArr = createLevel(arr);
    }

    public Entity[][] createLevel(int[][] a){

        Entity[][] temp = new Entity[a.length][a[0].length];


        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if(arr[i][j] == 1) {
                    temp[i][j] = new Entity(10 + i * 50, 10 + j * 50, 50, 50);
                }
            }
        }

        return temp;
    }


    public void levelCollider(Entity e){
    // e = player, npc, ect
    //https://stackoverflow.com/questions/2656943/2d-platformer-collision-problems-with-both-axes/2657184#2657184
     //   e.setOnGround(false);

        for (int i = 0; i < entArr.length; i++) {
            for (int j = 0; j < entArr[i].length; j++) {

                if(entArr[i][j]!= null && e.collision(entArr[i][j])){

                    int overlapX = (e.getHitBoxX()/2 +entArr[i][j].getHitBoxX()/2) - Math.abs(e.getX()-entArr[i][j].getX());
                    int overlapY = (e.getHitBoxY()/2 +entArr[i][j].getHitBoxY()/2) - Math.abs(e.getY()-entArr[i][j].getY());

                //    System.out.println(overlapX+" "+overlapY);

                  //  System.out.println(e.getX()+" "+e.getY());

                    //overlap = magnitude(A.pos - B.pos) - (A.radius + B.radius)

                    if(overlapY > 0 && overlapY < overlapX) {


                        if(e.getY()<entArr[i][j].getY()) {
                            e.setOnGround(true);
                            e.setY(e.getY() - overlapY-1);
                        }else{
                            e.setY(e.getY() + overlapY+1);
                        }
                        e.setSpeedY(0);

                    } else if(overlapX > 0 && overlapY>overlapX){

                        if(e.getX()<entArr[i][j].getX()) {
                            e.setX(e.getX()-overlapX-1);
                        }else{
                            e.setX(e.getX()+overlapX+1);
                        }

                        e.setSpeedX(0);
                    }

           //         System.out.println(e.getX()+" "+e.getY());

                }
            }
        }
    }




}
