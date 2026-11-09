import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;

public class Guppy extends Goldfish{

protected int health = 100;
protected Circle body;
protected int size;
protected Timer timer;
protected boolean flip = false;
protected double speedChanger = 0;
protected boolean isDead = false;

   public Guppy(){
      int randomNumX = (int)(Math.random() * 450) + 120;
      int randomNumY = (int)(Math.random() * 250) + 20;
      Circle circle = new Circle(randomNumX, randomNumY, 10.0, Color.CORNFLOWERBLUE); 
      this.body = circle;
      timer = new Timer();
      timer.start();
   }
   
   @Override
   public void move(){
      // vertical movement
      double verticalJitter = (Math.random() - 0.5) * 1.5;
      int willJitter = (int)(Math.random() * 1) + 3;
         if(willJitter == 2)
         { 
            this.body.setCenterY(this.body.getCenterY() + verticalJitter);
         }
      
      // horizontal movement
      if(flip == false && isDead == false)
      {
         this.body.setCenterX(this.body.getCenterX() + (0.75 + speedChanger));
            if(this.body.getCenterX() > 575)
            {
               flip = true;
            }
      }
      else if(flip == true && isDead == false)
      {
         this.body.setCenterX(this.body.getCenterX() - (0.75 + speedChanger));
            if(this.body.getCenterX() < 125)
            {
               flip = false;
            }
      }
   }
   
   public Circle getBody(){
      return this.body;
   }
   
   public void setBody(int healthEditor, int speedEditor, Color c){
      this.health += healthEditor;
         if(this.health > 101)
         {
            this.health = 100;
         }
         if(this.health < 40)
         {
            this.body.setFill(Color.LIGHTGRAY);
            this.speedChanger = -0.5;
         }
         else if(this.health > 40)
         {
            this.body.setFill(Color.CORNFLOWERBLUE);
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
         if(now - last > frame)
         {
            move();
            last = now;
         }
      }
   }

}   

