import org.eclipse.swt.SWT;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
public class Tank {
	Image image;
	int x, y;
	Canvas picture;
	int count1, count2;
	Tank(Display display, Shell shell){
		image = new Image(display, "tank.png");
		picture = new Canvas(shell, SWT.NONE);
		picture.addPaintListener(new PaintListener() {
			public void paintControl(final PaintEvent e) {
				if(image != null) {
					e.gc.drawImage(image, 0, 0);
				}
				
			}
			
		});
		x = 10;
		y = 200;
		count1 = 0;
		count2 = 0;
	}
	void draw () {
		picture.setBounds(x, y, image.getBounds().width, image.getBounds().height);
	}
	void update(Shell shell, boolean up, boolean down, Label labelEsc, Button btn1) {
		
		if( (!labelEsc.isVisible() )) {
		if(up) {
			y -= 5;
		}
		if(down) {
			y += 5;
		}
		if(y < 25) {
			y = 25;
		}
		if(y > shell.getBounds().height-120 ) {
			y = shell.getBounds().height-120;
		}
		if(up||down)
			draw();
	}
	}
	
}