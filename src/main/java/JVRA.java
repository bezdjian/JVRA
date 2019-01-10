import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class JVRA {

    public static void main(String[] args) {
        try {
            //Configuration Object
            Configuration configuration = new Configuration();

            // Set path to the acoustic model.
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

            // Set path to the dictionary.
            configuration.setDictionaryPath("YOUR_.DIC_FILE_PATH_HERE");

            // Set path to the language model.
            configuration.setLanguageModelPath("YOUR_.LM_FILE_PATH_HERE");

            //Recognizer object, Pass the Configuration object
            LiveSpeechRecognizer recognize = null;
            recognize = new LiveSpeechRecognizer(configuration);

            //Start Recognition Process (The bool parameter clears the previous cache if true)
            recognize.startRecognition(true);

            //Creating SpeechResult object
            SpeechResult result;

            //Check if recognizer recognized the speech
            while ((result = recognize.getResult()) != null) {
                //Get the recognized speech
                String command = result.getHypothesis();
                String work = null;
                Process p;

                //Some Extra Commands from my Corpus File
                if (command.equalsIgnoreCase("open search")) {
                    work = "google-chrome http://www.google.com";
                } else if (command.equalsIgnoreCase("new tab")) {
                    work = "google-chrome \\c";
                } else if (command.equalsIgnoreCase("open mail")) {
                    work = "google-chrome gmail.com";
                } else if (command.equalsIgnoreCase("open linked in")) {
                    work = "google-chrome linkedin.com";
                } else if (command.equalsIgnoreCase("open blog")) {
                    work = "google-chrome procurity.wordpress.com";
                } else if (command.equalsIgnoreCase("open git hub")) {
                    work = "google-chrome github.com/Ex094";
                } else if (command.equalsIgnoreCase("browser")) {
                    work = "google-chrome";
                } else if (command.equalsIgnoreCase("terminal")) {
                    work = "gnome-terminal";
                } else if (command.equalsIgnoreCase("file manager")) {
                    work = "nautilus";
                }

                if (work != null) {
                    p = Runtime.getRuntime().exec(work);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
