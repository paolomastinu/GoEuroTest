import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWriter {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "_id,name,type,latitude,longitude";

    public static void writeCsvFile(String fileName, ArrayList<CSVBeanData> CSVBeansData) {

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write CSVBeanData list to the CSV file

            for (CSVBeanData CSVBeanDataObject : CSVBeansData) {

                fileWriter.append(String.valueOf(CSVBeanDataObject.get_id()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(CSVBeanDataObject.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(CSVBeanDataObject.getType());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(CSVBeanDataObject.getLatitude()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(CSVBeanDataObject.getLongitude()));
                fileWriter.append(NEW_LINE_SEPARATOR);

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
