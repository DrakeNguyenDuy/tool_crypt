package presentations;

import java.io.IOException;

public interface IPresentation {
     void execute();
     void loadText(String type) throws IOException;
     void saveFile(String type);
}
