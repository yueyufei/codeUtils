package yyf.common.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHProxy {

	public static void sshInnerDatabase(String sshUser,String sshPwd,String sshHost,int sshPort,int rport, String rhost, int localPort) {
	
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(sshUser, sshHost, sshPort);
			session.setPassword(sshPwd);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println(session.getServerVersion());
			int assinged_port = session.setPortForwardingL(localPort, rhost, rport);

			System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		JSch jsch = new JSch();
		Session session = jsch.getSession("root", "192.168.72.147", 22);
		session.setPassword("root");
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		System.out.println(session.getServerVersion());
		int assinged_port = session.setPortForwardingL(3306, "192.168.72.147", 3306);
		System.out.println(assinged_port);
	}
}
