package com.bxd.day08;

public class Computer extends MotherBoard {

	@Override
	public void runPciDevice(PciDevice p) {
		// TODO Auto-generated method stub
		if (p != null)
			p.run();
	}

	public Computer() {
		runPciDevice(new SoundCard());
		runPciDevice(new NetCard());
	}

}

abstract class MotherBoard {
	static {
		System.out.println("MotherBoard Check");
	}

	public abstract void runPciDevice(PciDevice p);
}

interface PciDevice {
	public abstract void run();
}

class SoundCard implements PciDevice {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("SoundCard Run");
	}

}

class NetCard implements PciDevice {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("NetCard Run");
	}

}
