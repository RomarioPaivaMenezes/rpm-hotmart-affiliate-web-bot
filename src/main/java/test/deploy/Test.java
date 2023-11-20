package test.deploy;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Test {
	public static void main(String[] args) throws JSchException {
		
		
		JSch jsch = new JSch();
		String privateKey = "C:\\Users\\rmenezes\\Desktop\\Documentos Tarefas Critical\\dev-package\\wincp-putty\\private-keys\\teste";
		jsch.addIdentity(privateKey, "unity");
		
		Session jschSession = jsch.getSession("root","192.168.10.12",22);
		java.util.Properties config = new java.util.Properties(); 
	    config.put("StrictHostKeyChecking", "no");
	    jschSession.setConfig(config);
		
	    jschSession.connect();
	}
	
}
