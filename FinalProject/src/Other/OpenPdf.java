package Other;

import java.io.File;
import java.io.IOException;

import model.InventoryUtils;

public class OpenPdf {
	public static void openById(String id) {
		if((new File(InventoryUtils.buillpath+id+".pdf")).exists()) {
			try {
				Process p = Runtime
						.getRuntime()
						.exec("rundll32 url.dll,FileProtocolHandler "+InventoryUtils.buillpath+""+id+".pdf" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
