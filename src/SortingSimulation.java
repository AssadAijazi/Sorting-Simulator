import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


public class SortingSimulation extends JPanel {
	public ElementBox[] elements;
	private static final int SPEED = 1000000;
	private static final double FACTOR = 0.5;
	private String title;
	public SortingSimulation(ArrayList<Integer> random, String title) {

		elements = new ElementBox[50];
		this.title = title;
		for (int i = 0; i < 50; i++) {
			elements[i] = new ElementBox(6 + 27 * i, 50, random.get(i));
		}
		
	}
	
	public void reset(ArrayList<Integer> random) {
		for(int i = 0; i < elements.length; i++) {
			elements[i].setValue(random.get(i));
		}
		repaint();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void swapElements(int a, int b) {
		
		elements[a].setSwapping(true);
		elements[b].setSwapping(true);
		int targetB = elements[a].getX();
		int targetA = elements[b].getX();
		if ( a < b) { 
			elements[a].setDel_x(1);
			elements[b].setDel_x(-1);
			elements[a].setDel_y(1);
			elements[b].setDel_y(-1);
		} else if ( a > b){
			elements[b].setDel_x(1);
			elements[a].setDel_x(-1);
			elements[b].setDel_y(1);
			elements[a].setDel_y(-1);
		} else {
			return;
		}
		
		for(int i = 0; i < 40; i++) {
			elements[a].moveY();
			elements[b].moveY();
			repaint();
			long start = System.nanoTime();
			while(System.nanoTime() - start < SPEED / FACTOR);
		}
		while(elements[a].getX() != targetA || elements[b].getX() != targetB) {
			if(elements[a].getX() != targetA)
				elements[a].moveX();
			if(elements[b].getX() != targetB)
				elements[b].moveX();
			repaint();
			long start = System.nanoTime();
			while(System.nanoTime() - start < SPEED / FACTOR);
		}
		
		if ( a < b) { 
			elements[b].setDel_y(1);
			elements[a].setDel_y(-1);
		} else if ( a > b){
			elements[a].setDel_y(1);
			elements[b].setDel_y(-1);
		} else {
			return;
		}
		for(int i = 0; i < 40; i++) {
			elements[a].moveY();
			elements[b].moveY();
			repaint();
			long start = System.nanoTime();
			while(System.nanoTime() - start < SPEED / FACTOR);
		}
		elements[a].setSwapping(false);
		elements[b].setSwapping(false);
		for(ElementBox element: elements) {
			element.setSwapping(false);
		}
		repaint();
		
		ElementBox temp;
		temp = elements[a];
		elements[a] = elements[b];
		elements[b] = temp;
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString(title, 630, 20);
		for(ElementBox element: elements) {
			if(element.isSwapping()) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.ORANGE);
			}
			g.fillRect(element.getX(), element.getY(), ElementBox.WIDTH, ElementBox.HEIGHT);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Monospaced", Font.BOLD, 15));
			g.drawString("" + element.getValue(), element.getX() + 2, element.getY() + 15);
		}
	}
	
	public void bubbleSort() {
		for (int i = elements.length - 1; i > 0; i--) {
			for(int j = 0; j < i ; j++) {
				if(elements[j].getValue() > elements[j + 1].getValue()) {
					swapElements(j, j + 1);
				}
			}
		}
		
		repaint();
	}
	
	public void selectionSort() {
		int min;
		for(int i = 0; i < elements.length; i++) {
			min = i;
			for(int j = i + 1; j < elements.length; j++) {
				if(elements[j].getValue() < elements[min].getValue()) {
					min = j;
				} 
			}
			swapElements(min, i);
			for(ElementBox element: elements) {
				element.setSwapping(false);
			}
		repaint();
		}
		
		for(ElementBox element: elements) {
			element.setSwapping(false);
		}
		repaint();
	}
	
	public void insertionSort() {
		for(int i = 1; i < elements.length; i++) {
			int j = i;
			while(j >= 1 && elements[j - 1].getValue() > elements[j].getValue()) {
				swapElements(j - 1, j);
				j--;
			}
		}
	}

		
}
