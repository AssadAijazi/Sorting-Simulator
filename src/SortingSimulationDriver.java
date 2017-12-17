import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortingSimulationDriver {

	public static SortingSimulation bubbleSort, selectionSort, insertionSort;
	public static volatile boolean running1, running2, running3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Sorting Simulation");
		frame.setSize(1450, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4, 1));

		ArrayList<Integer> random = new ArrayList<Integer>();
		for(int i = 0; i < 50; i++) {
			random.add(i + 1);
		}

		Collections.shuffle(random);
		bubbleSort = new SortingSimulation(random, "Bubble Sort");
		selectionSort = new SortingSimulation(random, "Selection Sort");
		insertionSort = new SortingSimulation(random, "Insertion Sort");
		JPanel bottom = new JPanel();
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(running1 || running2 || running3)) {
					ArrayList<Integer> random = new ArrayList<Integer>();
					for(int i = 0; i < 50; i++) {
						random.add(i + 1);
					}
					Collections.shuffle(random);
					bubbleSort.reset(random);
					selectionSort.reset(random);
					insertionSort.reset(random);
					new Thread(new Runnable() {
						@Override
						public void run() {
							running1 = true;
							bubbleSort.bubbleSort();
							running1 = false;
						}
					}).start();

					new Thread(new Runnable() {
						@Override
						public void run() {
							running2 = true;
							selectionSort.selectionSort();
							running2 = false;
						}
					}).start();

					new Thread(new Runnable() {
						@Override
						public void run() {
							running3 = true;
							insertionSort.insertionSort();
							running3 = false;
						}
					}).start();
				}
			}
			
		});
		mainPanel.add(bubbleSort);
		mainPanel.add(selectionSort);
		mainPanel.add(insertionSort);
		bottom.add(start);
		mainPanel.add(bottom);

		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
