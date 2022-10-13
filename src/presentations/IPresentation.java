package presentations;

import java.io.IOException;

public interface IPresentation {
     void execute();
     void loadText() throws IOException;
     void saveFile();
}
