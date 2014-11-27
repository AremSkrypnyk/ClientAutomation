package musicqubed.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ars on 11/26/14.
 */
public class XMLConverter {

    private static String[] array;
    private static String fileName;

    public XMLConverter(String fileToConvert) {
        this.fileName = fileToConvert;
        try {
            readFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null)
            lines.add(line);
        reader.close();
        this.array = lines.toArray(new String[lines.size()]);
    }

    public static void convertToXml(String pathToResultFile) {
        String parameter, key, value;
        XMLBuilder xmlBuilder = new XMLBuilder("Flurry");
        xmlBuilder
                .createParentElement("Device")
                .createChildElement("name", System.getProperty("test.device"))
                .createChildElement("time_stamp", System.getProperty("test.udid"))
                .returnToParentElement();

        xmlBuilder
                .createParentElement("Events");

        for (String line : array) {
            if (!line.isEmpty()) {
                String eventName = line.substring(0, line.indexOf(","));
                xmlBuilder
                        .createParentElement("Event")
                        .createChildElement("name", eventName);

                String parameters = line.substring(line.indexOf("{") + 1);
                do {
                    if (!parameters.contains(","))
                        parameter = parameters;
                    else {
                        parameter = parameters.substring(0, parameters.indexOf(","));
                        parameters = parameters.substring(parameters.indexOf(",") + 2);
                    }
                    key = parameter.substring(0, parameter.indexOf("="));
                    value = parameter.substring(parameter.indexOf("=") + 1);
                    xmlBuilder
                            .createChildElement(key, value);

                } while (!Objects.equals(parameter, parameters));
                xmlBuilder
                        .returnToParentElement();
            }
        }
        xmlBuilder.build(pathToResultFile);
    }
}
