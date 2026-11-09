import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.AnimationTimer;
import java.util.Arrays;

public class App extends Application
{

private Rectangle waterColumn;
private Text dayIndicator;
private int day = 1;
private double nitrates = 0;
private double pH = 7.00;
private Text pHIndicator; 
private int temperature = 78;
private Text tempIndicator;
private Button gbtn1, gbtn2, gbtn3;
private Button bbtn1, bbtn2, bbtn3;
private Button ybtn1, ybtn2, ybtn3;
private Group root;
private Text nIndicator;
private Text note;
private Goldfish[] allGoldfish = new Goldfish[100]; 
private int nitrateMultiplier = 0;
private int plantValue = 0;
private Rectangle[] plant1 = new Rectangle[100];
private Rectangle[] plant2 = new Rectangle[100];
private Rectangle[] plant3 = new Rectangle[100];
private int it;
private boolean isDuckweed = false;
private int goldfishValue = 0;
private int points = 0;
private Text pIndicator;
private int shrimpBreeding = 0;

   @Override
   public void start(Stage stage)
   {
      stage.setTitle("Virtual Aquarium 2.0");
      Group root = new Group();
      this.root = root;
      Scene scene = new Scene(root, 700, 700, Color.BLACK);
      stage.setScene( scene );
      stage.show();
      KeyPressHandler handler = new KeyPressHandler();
      scene.setOnKeyPressed(handler);
      tankBuilder(root);
      guiBuilder(root);
      
      Text title = new Text();
      root.getChildren().add(title);
      textEditor(title, "Virtual Aquarium 2.0", 25, 415, Color.WHITE, "Verdana", 24);
      
      Text dayIndicator = new Text();
      root.getChildren().add(dayIndicator);
      textEditor(dayIndicator, "Day 1", 25, 450, Color.WHITE, "Verdana", 18);
      this.dayIndicator = dayIndicator;
      
      this.pIndicator = new Text();
      textEditor(this.pIndicator, "Points: " + this.points, 550, 450, Color.WHITE, "Verdana", 18);
      root.getChildren().add(this.pIndicator);
      
      this.nIndicator = new Text();
      textEditor(this.nIndicator, "Nitrates: " + this.nitrates + " ppm", 25, 485, Color.WHITE, "Verdana", 18);
      root.getChildren().add(this.nIndicator);
      
      this.pHIndicator = new Text();
      textEditor(this.pHIndicator, "pH: ", 25, 520, Color.WHITE, "Verdana", 18);
      this.pHIndicator.setText("pH: " + this.pH);
      root.getChildren().add(this.pHIndicator);
      
      Text tempIndicator = new Text();
      textEditor(tempIndicator, "Temperature: ", 25, 555, Color.WHITE, "Verdana", 18);
      tempIndicator.setText("Temperature: " + this.temperature + " degF");
      this.tempIndicator = tempIndicator;
      root.getChildren().add(this.tempIndicator);
      
      this.note = new Text();
      textEditor(this.note, "Note: Press [E] for info/tips.", 25, 590, Color.WHITE, "Verdana", 18);
      root.getChildren().add(this.note);
      
   }
    
   private void tankBuilder(Group root)
   {
      Rectangle waterColumn = new Rectangle(500, 300, Color.LIGHTBLUE);
      this.waterColumn = waterColumn;
      root.getChildren().add(this.waterColumn); 
      rectanglePosition(waterColumn, 100, 0);
      Rectangle substrate[] = new Rectangle[60];
         for(int j = 0; j<3; j++){
            for(int i = 0; i<20; i++){
               Rectangle sB = new Rectangle(25, 25, Color.KHAKI);
               root.getChildren().add(sB);
               rectanglePosition(sB, (100 + (i*25)), (300 + (j*25)));
               int randomInt = (int)(Math.random() * 3) + 1;
                  switch(randomInt){
                     case 1:
                     sB.setFill(Color.PAPAYAWHIP);
                     break;
                     case 2:
                     sB.setFill(Color.LEMONCHIFFON);
                     break;
                     case 3:
                     sB.setFill(Color.KHAKI);
                     break;
                  }
               substrate[i] = sB;
            }
         }   
   }
   
   private void rectanglePosition(Rectangle r, int x, int y)
   {
      r.setX(x);
      r.setY(y);
   }
   
   private void textEditor(Text t, String message, int x, int y, Color color, String font, int size)
   {
      t.setText(message);
      t.setX(x);
      t.setY(y);
      t.setFill(color);
      t.setFont(Font.font(font, size));
   }
   
  private void guiBuilder(Group root)
   {
     ButtonPressHandler buttonHandler = new ButtonPressHandler();
     Rectangle greenBox = new Rectangle(234, 100, Color.LIGHTGREEN);
     root.getChildren().add(greenBox); 
     rectanglePosition(greenBox, 0, 615);
     
     Rectangle blueBox = new Rectangle(234, 100, Color.LIGHTBLUE);
     root.getChildren().add(blueBox); 
     rectanglePosition(blueBox, 234, 615);
     
     Rectangle yellowBox = new Rectangle(234, 100, Color.LIGHTYELLOW);
     root.getChildren().add(yellowBox); 
     rectanglePosition(yellowBox, 468, 615);
     
     Text greenT = new Text();
     root.getChildren().add(greenT);
     textEditor(greenT, "Plants", 25, 625, Color.BLACK, "Verdana", 10);
     
     Text blueT = new Text();
     root.getChildren().add(blueT);
     textEditor(blueT, "Livestock", 259, 625, Color.BLACK, "Verdana", 10);
     
     Text yellowT = new Text();
     root.getChildren().add(yellowT);
     textEditor(yellowT, "Maintenance", 493, 625, Color.BLACK, "Verdana", 10);

     HBox buttonBar = new HBox(10);
     buttonBar.setSpacing(15);     
     buttonBar.setLayoutY(650);
     buttonBar.setLayoutX(-149);
     buttonBar.setPrefWidth(1000);  
     buttonBar.setScaleX(0.75);
     buttonBar.setScaleY(0.75);   
     buttonBar.setAlignment(Pos.CENTER); 
  
     this.gbtn1 = new Button("Add Vallisneria");
     this.gbtn2 = new Button("Add Rotala");
     this.gbtn3 = new Button("Add Duckweed");

     this.bbtn1 = new Button("Add Goldfish");
     this.bbtn2 = new Button("Add Cherry Shrimp");
     this.bbtn3 = new Button("Add Guppy");

     this.ybtn1 = new Button("Water change");
     this.ybtn2 = new Button("Feed animals");
     this.ybtn3 = new Button("Add limestone");     
     
     gbtn1.setOnAction(buttonHandler);
     gbtn2.setOnAction(buttonHandler);
     gbtn3.setOnAction(buttonHandler);

     bbtn1.setOnAction(buttonHandler);
     bbtn2.setOnAction(buttonHandler);
     bbtn3.setOnAction(buttonHandler);

     ybtn1.setOnAction(buttonHandler);
     ybtn2.setOnAction(buttonHandler);
     ybtn3.setOnAction(buttonHandler);
     
     buttonBar.getChildren().addAll(gbtn1, gbtn2, gbtn3, bbtn1, bbtn2, bbtn3, ybtn1, ybtn2, ybtn3);
     root.getChildren().add(buttonBar);
  }
  
  private void setWaterColor(Color c)
  {   
     this.waterColumn.setFill(c);
  }
     
  private void setDayIndicator(String extraInfo, int dayAdder)
  {
     this.day += dayAdder;
     this.dayIndicator.setText("Day: " + this.day + extraInfo);   
  }
  
  private void setTemperature(int t)
  {
     this.temperature += t;
     this.tempIndicator.setText("Temperature: " + this.temperature + " degF");
  }
  
  private void setPH(double p)
  {
     this.pH += p;
     this.pHIndicator.setText("pH: " + String.format("%.2f", this.pH));
  }
  
  public double getPH(double zero)
  {
     this.pH += zero;
     return(this.pH);
  }

  public class KeyPressHandler implements EventHandler<KeyEvent>
  {
  
  boolean dayBoolean = true;
  
     public void animalEditor(int healthEditor, int speedEditor, Color c)
     {
        for(Goldfish gf : allGoldfish)
        {
           if(gf == null){
           break;
           }
           gf.setBody(healthEditor, speedEditor, c);
        }
     }
     
     @Override
     public void handle(KeyEvent event)
     {
        if(event.getCode()==KeyCode.E)
        {
           Stage stage2 = new Stage();
           Group root2 = new Group();
           Scene scene2 = new Scene(root2, 300, 300, Color.WHITE);
           stage2.setScene( scene2 );
           stage2.show();
           stage2.setTitle("Tips");
           
           Text info1 = new Text();
           root2.getChildren().add(info1);
           info1.setFill(Color.BLACK);
           info1.setText("Press [D] to pass time.");
           info1.setY(20);
           
           Text info2 = new Text();
           root2.getChildren().add(info2);
           info2.setFill(Color.BLACK);
           info2.setText("Decomposing matter (food/waste) will raise nitrates.");
           info2.setY(60);
           
           Text info3 = new Text();
           root2.getChildren().add(info3);
           info3.setFill(Color.BLACK);
           info3.setText("At high levels, nitrates harm livestock, especially shrimp.");
           info3.setY(100);
           
           Text info4 = new Text();
           root2.getChildren().add(info4);
           info4.setFill(Color.BLACK);
           info4.setText("Plants release CO2 at night, temporarily lowering pH.");
           info4.setY(140);
           
           Text info5 = new Text();
           root2.getChildren().add(info5);
           info5.setFill(Color.BLACK);
           info5.setText("Water changes manage nitrates.");
           info5.setY(180);
           
           Text info6 = new Text();
           root2.getChildren().add(info6);
           info6.setFill(Color.BLACK);
           info6.setText("Control the temperature using [W/S] keys.");
           info6.setY(220);
           
           Text info7 = new Text();
           root2.getChildren().add(info7);
           info7.setFill(Color.BLACK);
           info7.setText("Very acidic or basic water will harm livestock.");
           info7.setY(260);
        }
        
        if(event.getCode()==KeyCode.D)
        {  
           if(dayBoolean == true){
              setWaterColor(Color.DARKBLUE);
              setDayIndicator(" (night)", 0);
              dayBoolean = false;
                 if(getPH(0) > 6.85){
                    setPH(-0.01);
                 }
                 int chance = (int)(Math.random()*40) + 1;
                 if(chance == 25 && shrimpBreeding >= 2)
                 {
                    allGoldfish[goldfishValue] = new Cherry_Shrimp();
                    Circle body = allGoldfish[goldfishValue].getBody();
                    root.getChildren().add(body);
                    nitrateMultiplier += 1;
                    goldfishValue += 1;
                    note.setText("Note: Eggs layed by some shrimp have successfully hatched.");
                 }

           }
              else if(dayBoolean == false){
                 setWaterColor(Color.LIGHTBLUE);
                 setDayIndicator("", 1);
                 dayBoolean = true;
                 setPH(0.0085);
                 double realNitrates = ((double)nitrateMultiplier/50);
                    if(temperature>90)
                    {
                    animalEditor(-5, 0, Color.LIGHTSALMON);
                    note.setText("Note: The water is a bit too warm.");
                    }
                    if(temperature>95)
                    {
                    animalEditor(-10, 0, Color.LIGHTSALMON);
                    note.setText("Note: The water is a bit too warm.");
                    }
                    if(temperature>105)
                    {
                    animalEditor(-20, 0, Color.LIGHTSALMON);
                    note.setText("Note: The water is too warm.");
                    }
                    if(temperature>120)
                    {
                    animalEditor(-40, 0, Color.LIGHTSALMON);
                    note.setText("Note: The water is too warm.");
                    }
                    
                    if(temperature<60)
                    {
                    animalEditor(-5, 0, Color.LIGHTSALMON);
                    note.setText("Note: The water is too cold.");
                    }
                    if(temperature<50)
                    {
                    animalEditor(-10, 0, Color.LIGHTSALMON);
                    note.setText("Note: The water is too cold.");
                    }
                    if(temperature<32)
                    {
                    animalEditor(-35, 0, Color.LIGHTSALMON);
                    note.setText("Note: The water is freezing.");
                    }

                    if(realNitrates>0)
                    {
                       nitrates += realNitrates;
                       nIndicator.setText("Nitrates: " + String.format("%.2f", nitrates) + " ppm"); 
                    }
                    if(nitrates>40)
                    {
                       nIndicator.setFill(Color.RED);
                       note.setText("Note: Nitrates are worringly high. Do a water change.");
                       animalEditor(-40, 0, Color.LIGHTSALMON);
                    }
                    if(nitrates>20 && nitrates<40)
                    {
                       note.setText("Note: Nitrates are a bit high. Do a water change.");
                       animalEditor(-9, 0, Color.LIGHTSALMON);
                    }
                    if(nitrates<20)
                    {
                       animalEditor(+8, 0, Color.LIGHTSALMON);
                       points += 10;
                       pIndicator.setText("Points: " + points);
                    }
                    if(nitrates<5)
                    {
                       animalEditor(+6, 0, Color.LIGHTSALMON);
                       points += 10;
                       pIndicator.setText("Points: " + points);
                    }

                    if(nitrates<40)
                    {
                       nIndicator.setFill(Color.WHITE);
                    }
                    if(pH<6.5)
                    {
                       animalEditor(-15, 0, Color.LIGHTSALMON);
                       note.setText("Note: The water is too acidic.");
                    }
                    if(pH>7.8)
                    {
                       animalEditor(-15, 0, Color.LIGHTSALMON);
                       note.setText("Note: The water is too basic.");
                    }
                    
              }
        }
        
        if(event.getCode()==KeyCode.W)
        {
           setTemperature(1);


        }
        
        if(event.getCode()==KeyCode.S)
        {
           setTemperature(-1);
          

        }
     }
  }
  
  public class ButtonPressHandler implements EventHandler<ActionEvent>
  {     
     public void handle(ActionEvent event)
     {
        Object bp = event.getSource();
           if(bp == bbtn1)
           {
              allGoldfish[goldfishValue] = new Goldfish();
              Circle body = allGoldfish[goldfishValue].getBody();
              root.getChildren().add(body);
              nitrateMultiplier += 4;
              note.setText("Note: Goldfish added.");
              goldfishValue += 1;
           }
           
           if(bp == bbtn2)
           {
              allGoldfish[goldfishValue] = new Cherry_Shrimp();
              Circle body = allGoldfish[goldfishValue].getBody();
              root.getChildren().add(body);
              nitrateMultiplier += 1;
              note.setText("Note: Cherry Shrimp added.");
              goldfishValue += 1;
              shrimpBreeding += 1;
           }
           
           if(bp == bbtn3)
           {
              allGoldfish[goldfishValue] = new Guppy();
              Circle body = allGoldfish[goldfishValue].getBody();
              root.getChildren().add(body);
              nitrateMultiplier += 2;
              note.setText("Note: Guppy added.");
              goldfishValue += 1;
           }
           
           if(bp == ybtn1){
              note.setText("Note: 25% water change completed.");
              nitrateMultiplier -= 2;
              
              if(nitrates<40)
              {
                 nIndicator.setFill(Color.WHITE);
              }

              if(nitrates > 4)
              {
                 nitrates -= 5;
                 nIndicator.setText("Nitrates: " + String.format("%.2f", nitrates) + " ppm"); 
              }
                 else
                 {
                 nitrates = 0;
                 nIndicator.setText("Nitrates: " + nitrates + " ppm"); 
                 }
           }
           
           if(bp == ybtn2){
              note.setText("Note: Animals fed.");
              nitrateMultiplier += 5;
              nIndicator.setText("Nitrates: " + String.format("%.2f", nitrates) + " ppm"); 
              if(nitrates<5)
              {
                 points += 30;
                 pIndicator.setText("Points: " + points);
              }
           }
           
           if(bp == ybtn3){
              note.setText("Note: Limestone added.");
              pH += 0.2;
              pHIndicator.setText("pH: " + String.format("%.2f", pH)); 
           }
           
            if(bp == gbtn1){
              pH -= 0.005;
              nitrateMultiplier -= 2;
              pHIndicator.setText("pH: " + String.format("%.2f", pH));
              if(plantValue > 300)
                 {
                    plantValue = 0;
                 }
              switch(plantValue)
              {
              case 0:
                 note.setText("Note: Vallisneria added (slot 1).");
              break;
              case 150:
                 note.setText("Note: Vallisneria added (slot 2).");
              break;
              case 300:
                 note.setText("Note: Vallisneria added (slot 3).");
              break;
              }
              plantBuilder('v', plantValue);
              plantValue += 150;
            }
            
            if(bp == gbtn2){
              pH -= 0.005;
              nitrateMultiplier -= 2;
              pHIndicator.setText("pH: " + String.format("%.2f", pH));
                 if(plantValue > 300)
                 {
                    plantValue = 0;
                 }
              switch(plantValue)
              {
              case 0:
                 note.setText("Note: Rotala added (slot 1).");
              break;
              case 150:
                 note.setText("Note: Rotala added (slot 2).");
              break;
              case 300:
                 note.setText("Note: Rotala added (slot 3).");
              break;
              }
              plantBuilder('r', plantValue);
              plantValue += 150;
            }
            
            if(bp == gbtn3){
              pH -= 0.005;
              nitrateMultiplier -= 3;
              pHIndicator.setText("pH: " + String.format("%.2f", pH));
              plantBuilder('d', plantValue);
              note.setText("Note: Duckweed added.");
            }
     }
     
     private void plantBuilder(char plantType, int v)
     {
        switch(plantValue)
           {
              case 0:
                 for(Rectangle r : plant1)
                 {
                    if(r != null){
                      root.getChildren().remove(r);
                    }
                 }
              break;
              case 150:
                 for(Rectangle r : plant2)
                 {
                    if(r != null){
                      root.getChildren().remove(r);
                    }
                 }
              break;
              case 300:
                 for(Rectangle r : plant3)
                 {
                    if(r != null){
                      root.getChildren().remove(r);
                    }
                 }
              break;

           }
           
        if(plantType == 'v')
        {
           it = 0;
           plantRectangleBuilder(175+v, 275, Color.GREEN);
           plantRectangleBuilder(175+v, 250, Color.GREEN);
           plantRectangleBuilder(200+v, 225, Color.GREEN);
           plantRectangleBuilder(150+v, 225, Color.GREEN);
           plantRectangleBuilder(200+v, 200, Color.GREEN);
           plantRectangleBuilder(150+v, 200, Color.GREEN);
           plantRectangleBuilder(200+v, 175, Color.GREEN);
           plantRectangleBuilder(150+v, 175, Color.GREEN);
           plantRectangleBuilder(225+v, 150, Color.GREEN);
           plantRectangleBuilder(125+v, 150, Color.GREEN);
           plantRectangleBuilder(175+v, 150, Color.GREEN);
           plantRectangleBuilder(225+v, 125, Color.GREEN);
           plantRectangleBuilder(125+v, 125, Color.GREEN);
           plantRectangleBuilder(175+v, 125, Color.GREEN);
           plantRectangleBuilder(225+v, 100, Color.GREEN);
           plantRectangleBuilder(125+v, 100, Color.GREEN);
           plantRectangleBuilder(175+v, 100, Color.GREEN);
           plantRectangleBuilder(225+v, 75, Color.GREEN);
           plantRectangleBuilder(125+v, 75, Color.GREEN);
           plantRectangleBuilder(175+v, 75, Color.GREEN);
           System.out.println(Arrays.toString(plant2));
        }
        
        else if(plantType == 'r')
        {
           it = 0;
           plantRectangleBuilder(125+v, 275, Color.GREEN);
           plantRectangleBuilder(150+v, 250, Color.GREEN);
           plantRectangleBuilder(125+v, 225, Color.GREEN);
           plantRectangleBuilder(150+v, 200, Color.GREEN);
           plantRectangleBuilder(125+v, 175, Color.GREEN);
           plantRectangleBuilder(150+v, 150, Color.YELLOWGREEN);
           plantRectangleBuilder(125+v, 125, Color.GOLD);
           plantRectangleBuilder(150+v, 100, Color.SANDYBROWN);
           plantRectangleBuilder(125+v, 75, Color.INDIANRED);
           
           plantRectangleBuilder(200+v, 275, Color.GREEN);
           plantRectangleBuilder(225+v, 250, Color.GREEN);
           plantRectangleBuilder(200+v, 225, Color.GREEN);
           plantRectangleBuilder(225+v, 200, Color.GREEN);
           plantRectangleBuilder(200+v, 175, Color.GREEN);
           plantRectangleBuilder(225+v, 150, Color.YELLOWGREEN);
           plantRectangleBuilder(200+v, 125, Color.GOLD);
           plantRectangleBuilder(225+v, 100, Color.SANDYBROWN);
           plantRectangleBuilder(200+v, 75, Color.INDIANRED);
        }
        
        else if(plantType == 'd'){
           isDuckweed = true;
           plantRectangleBuilder(200, 0, Color.GREEN);
           plantRectangleBuilder(400, 0, Color.GREEN);
           plantRectangleBuilder(550, 0, Color.GREEN);
           plantRectangleBuilder(150, 0, Color.GREEN);
           plantRectangleBuilder(450, 0, Color.GREEN);
           isDuckweed = false;
        }
                  
     }
     
     private void plantRectangleBuilder(int x, int y, Color color)
     {
        Rectangle stem = new Rectangle(25, 25, color);
        stem.setX(x);
        stem.setY(y);
        root.getChildren().add(stem);
           if(isDuckweed == false){
           switch(plantValue)
           {
              case 0:
                 plant1[it] = stem;
                 it += 1;
              break;
              case 150:
                 plant2[it] = stem;
                 it += 1;
              break;
              case 300:
                 plant3[it] = stem;
                 it += 1;
              break;

           }
           }
     }       
     

  }
  


   
   
}