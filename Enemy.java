import org.eclipse.swt.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class Enemy {
	Image image;
	int x, y;
	Canvas picture;
	
	Enemy(Display display, Shell shell){
		image = new Image(display, "n.png");
		picture = new Canvas(shell, SWT.NONE);
		picture.addPaintListener(new PaintListener() {
			public void paintControl(final PaintEvent e) {
				if(image != null) {
					e.gc.drawImage(image, 0, 0);
				}	
			}		
		});
		x =(int)(800+Math.random()*1200);
		y=(int)(40+Math.random()*420);
		
		
		
	}
	void draw () {
		picture.setBounds(x, y, image.getBounds().width, image.getBounds().height);
	}
	void update(Shell shell, Bullet[] bullet,Tank tank, Label label2, Label labelEsc, Button btn1, Label labelEndGame ) {
		
		if((!labelEsc.isVisible() ) /*&& ( !btn1.isVisible() )*/ ) {
		bullet = new Bullet[150];
		x-=(int)(Math.random()*3)+1;
		if(x<=0) {
			tank.count2++;
			label2.setText("Митингов провели: "+tank.count2);
			if(tank.count2 >= 10) {
				labelEndGame.setVisible(true);
			}
//			baza.count1++; прикосновение отнимает жизнь стены
			x =(int)(800+Math.random()*1200);
			y = (int)(40+Math.random()*420);
		}	
		}
	}
	
}