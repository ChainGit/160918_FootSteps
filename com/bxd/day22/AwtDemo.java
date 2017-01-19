package com.bxd.day22;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * 显示目录下的所有文件
 * 
 * @author Chain
 *
 */
public class AwtDemo {

	private Frame f1;
	private Button b1;
	private TextField tf1;
	private TextArea ta1;

	private Dialog dl1;

	public AwtDemo() {
		init();
	}

	private void init() {
		initFrame();
		initFrameListener();
		initComponent();
		initComponentListener();
		startFrame();
	}

	private void startFrame() {
		// f1.pack();
		f1.setVisible(true);
	}

	private void initComponentListener() {
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionProc();
			}
		});

		tf1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					button1ActionProc();
			}
		});

		dl1.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dl1.setVisible(false);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				dl1.setVisible(false);
			}
		});
	}

	private void button1ActionProc() {
		String strtf1 = tf1.getText();
		ta1.setText(strtf1);
		ta1.append("\n\n");// In Windows
		File f1 = new File(strtf1);
		if (!f1.exists()) {
			dl1.setVisible(true);
			return;
		}
		ta1.append(showAllFiles(f1, 0));
		ta1.append("\n");// In Windows
	}

	private void initComponent() {
		tf1 = new TextField(65);
		f1.add(tf1);
		b1 = new Button("Go");
		f1.add(b1);
		ta1 = new TextArea(25, 70);
		f1.add(ta1);

		dl1 = new Dialog(f1, "提示");
		dl1.setSize(240, 80);
		dl1.setLocation(f1.getX() + f1.getWidth() / 2 - dl1.getWidth() / 2,
				f1.getY() + f1.getHeight() / 2 - dl1.getHeight() / 2);
		dl1.add(new Label(" 您输入的不是一个有效文件名或目录!"));
	}

	private void initFrameListener() {
		f1.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void initFrame() {
		f1 = new Frame("列出子目录下的所有文件");
		// f1.setSize(400, 500);
		// f1.setLocation(100, 200);
		f1.setBounds(100, 200, 600, 500);
		f1.setLayout(new FlowLayout());
	}

	public static void main(String[] args) {
		new AwtDemo();
	}

	private String showAllFiles(File f, int lv) {
		StringBuilder sb = new StringBuilder();
		sb.append(getLevel(lv));
		sb.append(f.getName());
		if (f.isDirectory())
			sb.append("*\n");
		else
			sb.append('\n');
		File[] fs = f.listFiles();
		lv++;
		for (File fp : fs) {
			if (fp.isDirectory())
				sb.append(showAllFiles(fp, lv));
			else {
				sb.append(getLevel(lv));
				sb.append(fp.getName());
				sb.append("\n");// In Winodws
			}
		}

		return sb.toString();
	}

	public String getLevel(int lv) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lv; i++)
			sb.append(": ");
		sb.append("|-");
		return sb.toString();
	}
}
