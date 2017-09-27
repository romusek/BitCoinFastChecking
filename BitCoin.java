import java.util.*;
import java.io.*;
import java.net.*;

import javafx.application.*;
import java.text.*;
import java.util.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.stage.*;
import javafx.geometry.*;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
//import javafx.animation.Animation;

public class BitCoin extends Application{

	public static void main(String[] args) {
		//getCurrentVal();
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception{


    	HBox root = new HBox(); 

    	String buttonText = getCurrentVal();
    	BitStore.post(toInteger(buttonText));
	    Button button = new Button(buttonText);
	    button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override public void handle(ActionEvent e) {
    			String val = getCurrentVal();
        		button.setText(val);
        		BitStore.post(toInteger(val));
    		}
		});
    	root.getChildren().addAll(button);

    	Scene scene = new Scene(root);
    	scene.getStylesheets().add("Style.css");

    	stage.setScene(scene);
    	stage.setTitle("BitCoin");
    	stage.getIcons().add(new Image("http://bitcoin.pl/images/price-tag.png"));
    	stage.sizeToScene();
    	stage.setResizable(false);
    	stage.setAlwaysOnTop(true);
    	stage.show();
  	}

	private static String getCurrentVal(){
		String source = getUrlSource("http://bitcoin.pl/kurs");
		int pocz = 0;
		String szuk =  "alt=Kurs bitcoina\" />";
		while (!source.substring(pocz, pocz + szuk.length()).equals(szuk) && pocz < source.length()){
			pocz++;
		}
		pocz += szuk.length();
		int koniec = pocz + 1;
		while (source.charAt(koniec)!=' '){
			koniec++;
		}
		String result = source.substring(pocz, koniec);
		return result;
		//log(source);
	}

	private static String getUrlSource(String url){
        StringBuilder a = new StringBuilder();
        try{
        	URL yahoo = new URL(url);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(), "UTF-8"));
            String inputLine;
            a = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                a.append(inputLine);
            in.close();
        }catch(IOException e){
        	log(e.toString());
        }
        return a.toString();
   	}

   	private static Integer toInteger(String text){
   		int result = 0;
   		for (int i=0; i<text.length(); i++){
   			char tmp = text.charAt(i);
   			result = 10 * result + (tmp - 48);
   		}
   		return result;
   	}

    public static void log(String msg){
    	System.out.println(msg);
    }
}
