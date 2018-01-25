package cosc426.assign45;

/**
 * Created by lhe on 11/25/17.
 */

public class Gamer {

    private double ballX;
    private double ballY;
    private double ballRadius;
    private double ballSpeed;

    private double bulletX;
    private double bulletY;
    private double bulletRadius;
    private double bulletSpeed;

    private double gunX;
    private double gunY;

    private double distanceThreshold;

    private boolean fired;
    private boolean hit;
    private boolean bulletDisappear;
    private boolean ballDisappear;

    private double screenWidth;
    private double screenHeight;

    public Gamer(double screenWidth, double screenHeight)
    {
        initialize();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void initialize()
    {
        fired = false;
        hit = false;
        bulletDisappear = false;
        ballDisappear = false;

        gunX = 150;
        gunY = 400 + Math.random() * 400;

        bulletX = gunX;
        bulletY = gunY;

        ballX = 1000 + Math.random() * 300;
        ballY = 100 * Math.random();
        ballRadius = 50;
        ballSpeed = 5 + 10 * Math.random();

        bulletRadius = 30;
        bulletSpeed = 70;

        distanceThreshold = 100;

    }

    public void update()
    {
        if(bulletDisappear && ballDisappear) {
            initialize();
        }

        moveBall();
        if(fired)
            moveBullet();
    }

    public void fire()
    {
        fired = true;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public double getBallRadius() {
        return ballRadius;
    }

    public double getBulletX() {
        return bulletX;
    }

    public double getBulletY() {
        return bulletY;
    }

    public double getBulletRadius() {
        return bulletRadius;
    }

    public double getGunX() {
        return gunX;
    }

    public double getGunY() {
        return gunY;
    }

    public boolean isHit() {
        return hit;
    }

    public boolean isBulletDisappear() {
        return bulletDisappear;
    }

    public boolean isBallDisappear() {
        return ballDisappear;
    }

    private void moveBall()
    {
        if(!hit)
        {
            ballY = ballY + ballSpeed;
            hit = decideHit();

            if( hit || ballY >= screenHeight) {

                ballDisappear = true;

            }

        }

    }

    private boolean decideHit()
    {
        double distance = Math.sqrt( (ballX - bulletX)*(ballX - bulletX) +
                                (ballY - bulletY)*(ballY - bulletY));

        return distance <= distanceThreshold;
    }

    private void moveBullet()
    {
        bulletX += bulletSpeed;
        if(bulletX >= screenWidth)
            bulletDisappear = true;
    }


}
