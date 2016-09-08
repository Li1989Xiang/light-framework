package up.light.io;

import java.io.IOException;
import java.io.OutputStream;

public interface IWritableResource extends IResource {
	boolean isWritable();
	OutputStream getOutputStream() throws IOException;
}
