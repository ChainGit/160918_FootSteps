package com.bxd.day22;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtTest2 {

	private Frame f;
	private Button b1;
	private TextField tf1;

	public AwtTest2() {
		init();
	}

	private void init() {
		initFrame();
		initFrameListener();
		initComponent();
		initComponentListener();
		startFrame();
	}

	private void initFrame() {
		f = new Frame("my awt");
		f.setSize(400, 300);
		f.setLocation(100, 200);
		// f.setBounds(100, 200, 400, 300);
		f.setLayout(new FlowLayout());
	}

	private void initFrameListener() {
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("window closing");
				System.exit(0);
			}
		});
	}

	private void initComponent() {
		tf1 = new TextField(20);
		f.add(tf1);

		b1 = new Button("close");
		f.add(b1);
	}

	private void initComponentListener() {
		initTextField1Listener();
		initButton1Listener();
	}

	private void initTextField1Listener() {
		tf1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key < KeyEvent.VK_0 || key > KeyEvent.VK_9) {
					e.consume();
				}
			}
		});
	}

	private void initButton1Listener() {
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button action");
			}
		});

		b1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("key pressed " + e.getKeyChar());
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
					System.exit(0);
				}
			}
		});

		b1.addMouseListener(new MouseAdapter() {

			private int count = 0;

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("mouse enter " + (++count));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println("mouse double click");
				} else
					System.out.println("mouse single click");
			}
		});
	}

	private void startFrame() {
		f.pack();
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new AwtTest2();
	}
}
