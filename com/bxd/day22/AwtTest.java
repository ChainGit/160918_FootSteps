package com.bxd.day22;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * �������̣�
 * 1.Frame����
 * 2.Frame����Ļ�������
 * 3.���岼�ֺ����
 * 4.ʹ��add������ӵ�Frame��
 * 5.�ô�����ʾ
 * 
 * �¼��������ƣ�
 * �¼�Դ�������¼������������¼�����
 * ����Աֻ���д�¼�������
 * 
 */
public class AwtTest {

	public static void main(String[] args) {
		Frame f = new Frame("my awt");
		f.setSize(400, 300);
		f.setLocation(100, 200);

		f.setLayout(new FlowLayout());

		Button b1 = new Button("Button");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("button action: " + e.toString());
			}

		});
		f.add(b1);

		f.addWindowListener(new MyWindowAdapter());

		f.setVisible(true);
	}
}

class MyWindowAdapter extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("closing: " + e.toString());
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("closed");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("activated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("deactivated");
	}
}

class MyWindowListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
