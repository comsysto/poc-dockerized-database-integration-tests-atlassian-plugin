package ut.com.comsysto.poc.ao.service.helper;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CmdLineTestHelper {

  public static CmdLineExecutionResult executeCommand(String command) {
    //
    // EXECUTE DOCKER COMMAND AND RETURN RESULT
    //
    ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    PumpStreamHandler psh = new PumpStreamHandler(stdout);
    CommandLine cmdLine = CommandLine.parse(command);
    DefaultExecutor executor = new DefaultExecutor();
    executor.setStreamHandler(psh);
    CmdLineExecutionResult res = new CmdLineExecutionResult();
    try {
      res.exitCode = executor.execute(cmdLine);
      res.stdout = stdout.toString();
    } catch (ExecuteException e) {
      res.exitCode = e.getExitValue();
      res.stdout = stdout.toString();
      res.exception = e.getMessage();
    } catch (IOException e) {
      res.exitCode = -1;
      res.stdout = stdout.toString();
      res.exception = e.getMessage();
    }
    return res;
  }
}
