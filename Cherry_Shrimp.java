import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;

public class Cherry_Shrimp extends Goldfish{

protected int health = 50;
protected Circle body;
protected int size;
protected Timer timer;
protected boolean flip = false;
protected double speedChanger = 0;
protected boolean isDead = false;

   public Cherry_Shrimp(){
      int randomNumX = (int)(Math.random() * 450) + 120;
      int randomNumY = (int)(Math.random() * 250) + 20;
      Circle circle = new Circle(randomNumX, 300, 10.0, Color.FIREBRICK); 
      this.body = circle;
      timer = new Timer();
      timer.start();
   }
   
   @Override
   public void move(){
      // vertical movement 
      double verticalJitter = (Math.random() * 6);
      int willJitter = (int)(Math.random() * 100);
         if(willJitter == 50)
         { 
          //  this.body.setCenterY(this.body.getCenterY() + verticalJitter);
         }
      
      // horizontal movement
      if(flip == false && isDead == false)
      {
         this.body.setCenterX(this.body.getCenterX() + (0.1 + speedChanger));
            if(this.body.getCenterX() > 575)
            {
               flip = true;
            }
      }
      else if(flip == true && isDead == false)
      {
         this.body.setCenterX(this.body.getCenterX() - (0.1 + speedChanger));
            if(this.body.getCenterX() < 125)
            {
               flip = false;
            }
      }
   }
   
   public Circle getBody(){
      return this.body;
   }
   
   @Override
   public void setBody(int healthEditor, int speedEditor, Color c){
      this.health += healthEditor;
         if(this.health > 51)
         {
            this.health = 50;
         }
         if(this.health < 40)
         {
            this.body.setFill(Color.CORAL);
            this.speedChanger = -0.05;
         }
         else if(this.health > 40)
         {
            this.body.setFill(Color.FIREBRICK);
            this.speedChanger = 0;
         }
        if(this.health < 0)
        {
           this.health = -999999;
           this.isDead = true;
        }
   }

   class Timer extends AnimationTimer
   {
      long last = 0;
      final long frame = 16000000;
      
      @Override
      public void handle(long now)
      {
         if(now - last > frame);
            move();
            last = now;
      }
   }

}   

