package Mario;

public class Entity {

// This class is the base for every character, enemy, block

    int x;  //x&y is the center of the entity
    int y;
    int hitX; // hitbox centered around x and y.  x/2 = center & y/2 = center
    int hitY;

    double xSpeed;
    double ySpeed;
    double direction; // in radians
    double magnitude;

    double coef; // ground coefficent of friction

    boolean onGround;

    int time;

    //    +
    // - -|- +
    //    -

    public Entity(int xpos,int ypos,int xbox,int ybox) {

        time = 0;

        x = xpos;
        y = ypos;
        hitX = xbox;
        hitY = ybox;

        coef = 0.1;

        ySpeed = 0;
        xSpeed = 0;
        direction = 0;
        magnitude = 0;
        onGround = true;
    }

    public Entity(int xbox,int ybox){
        this(0,0,xbox,ybox);
    }

    public int getX() {return x;}

    public int getY() {
        return y;
    }

    public int getHitBoxX() {return hitX;}

    public int getHitBoxY() {
        return hitY;
    }

    public double getSpeedX() {
        return xSpeed;
    }

    public double getSpeedY() {
        return ySpeed;
    }

    public double getDirection() {
        return direction;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setX(int xx){
        x = xx; }

    public void setY(int yy){
        y = yy;}

    public void setSpeedX(double xx){
        xSpeed = xx;
    }

    public void setSpeedY(double yy){
        ySpeed = yy;
    }

    public void CalculateDirection() {

        // 1 | 0
        //---|---
        // 2 | 3
        if (xSpeed >= 0 && ySpeed < 0) {
            direction = Math.abs(Math.atan(ySpeed / xSpeed)) + (3 / 2) * Math.PI;   //3
        } else if (xSpeed < 0 && ySpeed <= 0) {
            direction = Math.abs(Math.atan(ySpeed / xSpeed)) + Math.PI;             //2
        } else if (xSpeed < 0 && ySpeed >= 0) {
            direction = Math.abs(Math.atan(ySpeed / xSpeed)) + (Math.PI / 2);       //1
        } else {
            direction = Math.abs(Math.atan(ySpeed / xSpeed)) + 0;                   //0
        }
    }

    public void CalculateMagnitude() {
        magnitude = Math.sqrt(xSpeed * xSpeed + ySpeed + ySpeed);
    }

    public void Calculate() {
        CalculateMagnitude();
        CalculateDirection();
    }


    public void jump(){
        setSpeedY(10);
        onGround = false;
    }

    public void gravity(){

        if(!onGround) {
            setSpeedY(getSpeedY() - 0.1);
        }else{
            setSpeedY(0);
        }
    }

    public void friction(){
        //coef 0.1
        if(getSpeedX()>0){
            setSpeedX(getSpeedX()-coef);
        }else if(getSpeedX()<0){
            setSpeedX(getSpeedX()+coef);
        }
    }

    public void update(int delta){

        if(time>10) {
            time = 0;
            gravity();
            friction();
            setX(getX() + (int) (getSpeedX()));
            setY(getY() - (int) (getSpeedY()));
        }else{
            time +=delta;
        }


    }



    public void moveRight(){
        setSpeedX(5.0);
    }

    public void moveleft(){
        setSpeedX(-5.0);
    }


    public boolean collision(Entity e){
// https://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other

        int entityX1 = getX()-getHitBoxX()/2;
        int entityX2 = getX()+getHitBoxX()/2;
        int eX1 = e.getX()-e.getHitBoxX()/2;
        int eX2 = e.getX()+e.getHitBoxX()/2;

        int entityY1 = getY()+getHitBoxY()/2;
        int entityY2 = getY()-getHitBoxY()/2;
        int eY1 = e.getY()+e.getHitBoxY()/2;
        int eY2 = e.getY()-e.getHitBoxY()/2;

        if (entityX1 < eX2 && entityX2 > eX1 && entityY1 > eY2 && entityY2 < eY1){
            return true;
        }
        return false;
    }




}
