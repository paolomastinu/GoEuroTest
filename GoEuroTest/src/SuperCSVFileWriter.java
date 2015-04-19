import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


public class SuperCSVFileWriter {
	
	public static void writeCsvFile(String fileName, ArrayList<CSVBeanData> CSVBeansData) {
	
		ICsvBeanWriter beanWriter = null;
	    try {
	            beanWriter = new CsvBeanWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE);
	            
	            final String[] header = new String[] { "_id", "name", "type", "latitude", "longitude"};
	            final CellProcessor[] processors = getProcessors();
	            
	            // write the header
	            beanWriter.writeHeader(header);
	            
	            // write the beans
	            for( CSVBeanData CSVbd : CSVBeansData ) {
	                beanWriter.write(CSVbd, header);
	            }
	            
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
            if( beanWriter != null ) {
                    try {
						beanWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
            }
	    }
    
	}
	
	private static CellProcessor[] getProcessors() {
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	                new UniqueHashCode(), // _id (must be unique)
	                new Optional(), // name
	                new Optional(), // type
	                new Optional(), // latitude
	                new Optional(), // longitude
	        };
	        
	        return processors;
	}

}
