
public class ElementBox {


	//width and height of the box to be rendered
	public static final int WIDTH = 23;
	public static final int HEIGHT = 25;
	
	//location of top left corner of box
	private int x, y;
	private int del_x, del_y;
	private boolean swapping;
	
	//value within box
	private int value;
	

	public ElementBox(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
		del_x = 0;
		del_y = 0;
		swapping = false;
	}
	public void moveX() {
		x+= del_x;
	}

	public void moveY() {
		y+= del_y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setDel_x(int del_x) {
		this.del_x = del_x;
	}

	public void setDel_y(int del_y) {
		this.del_y = del_y;
	}
	public boolean isSwapping() {
		return swapping;
	}
	public void setSwapping(boolean swapping) {
		this.swapping = swapping;
	}

}
