package Core;

import java.awt.Canvas;//draw everything to this canvas
import java.awt.Dimension;

import javax.swing.JFrame;//them put this canvas to the frame

public class Display  {
	

	private JFrame frame;//need a frame to hold the canvas
	private Canvas canvas;// need canvass to display stuffs
	
	private String title;
	private int width, height;
	
	public Display(String name, int width, int height) {
		
		this.title = name;
		this.width = width;
		this.height = height;
		
		createDisplay();
		
	}
	
	private void createDisplay() {
		
		//////////////////////////////////////Frame/////////////////////////////////////////////////////////////////////////////
		frame = new JFrame(title);
		frame.setSize(width, height);//when you pass stuffs inside the constructor then the width and height here will be given value
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);//this will make sure the window you launch gonna appear at teh center of the screen instead of the left side of the screen
		frame.setVisible(true);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////Canvas/////////////////////////////////////////////////////////////////////////////
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));//make sure the canvas will always stay at the same height we set it to be
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////////add frame to canvas////////////////////////////////////
		frame.add(canvas);
		frame.pack();//pack makes sure all the canvas will stay inside the frame, make sure it fits perfectly
		////////////////////////////////////////////////////////////////////////////////////////////
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	

}
