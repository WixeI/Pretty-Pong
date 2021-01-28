import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Handler {


	public static ArrayList<String> input = new ArrayList<String>();
	public static boolean press = false;

	public Handler(Scene cena){

		cena.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {

                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code) ){
                        input.add( code );

                        press = true;
                        
                    }

                }
            });

        cena.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                	String code = e.getCode().toString();

                	press = false;

                    input.remove( code );

                }
            });


	}

}
