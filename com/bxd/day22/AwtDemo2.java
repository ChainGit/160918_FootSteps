package com.bxd.day22;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 简单的显示文件内容
 * 
 * @author Chain
 *
 */
public class AwtDemo2 {

	private Frame f1;
	private MenuBar mb1;
	private Menu m1;
	private MenuItem mitNew, mitOpen, mitSave, mitClose;
	private FileDialog fdlOpen, fdlSave;
	private TextArea ta1;

	public AwtDemo2() {
		init();
	}

	private void init() {
		f1 = new Frame("显示文件内容");
		f1.setBounds(100, 100, 500, 600);
		f1.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		mb1 = new MenuBar();

		m1 = new Menu("操作");
		mb1.add(m1);

		mitNew = new MenuItem("新建");
		mitNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ta1 != null)
					ta1.setText("");
			}
		});

		fdlOpen = new FileDialog(f1, "打开文件", FileDialog.LOAD);

		mitOpen = new MenuItem("打开");
		mitOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fdlOpen.setVisible(true);
				try {
					procReadFile();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		fdlSave = new FileDialog(f1, "保存到文件", FileDialog.SAVE);

		mitSave = new MenuItem("保存");
		mitSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fdlSave.setVisible(true);
				try {
					procSaveFile();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		mitClose = new MenuItem("关闭");
		mitClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		m1.add(mitNew);
		m1.add(mitOpen);
		m1.add(mitSave);
		m1.add(mitClose);

		f1.setMenuBar(mb1);

		ta1 = new TextArea();
		ta1.setBounds(0, 0, f1.getWidth(), f1.getHeight());
		f1.add(ta1);

		f1.setVisible(true);
	}

	public static void main(String[] args) {
		new AwtDemo2();
	}

	private void procReadFile() throws Exception {
		String filepath = fdlOpen.getDirectory();
		String filename = fdlOpen.getFile();
		if (filename == null || filepath == null)
			return;
		BufferedReader bufr = new BufferedReader(new FileReader(new File(filepath, filename)));
		String buf = null;
		ta1.setText("");
		while ((buf = bufr.readLine()) != null) {
			ta1.append(buf);
			ta1.append("\n");
		}
		bufr.close();
	}

	private void procSaveFile() throws Exception {
		String filepath = fdlSave.getDirectory();
		String filename = fdlSave.getFile();
		if (filepath == null | filename == null)
			return;
		BufferedWriter bufw = new BufferedWriter(new FileWriter(new File(filepath, filename)));
		String buf = null;
		if ((buf = ta1.getText()) != null) {
			bufw.write(buf);
		}
		bufw.flush();
		bufw.close();
	}
}
