import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;

public class Bullet {
		Image img;
		int x,y;
		Canvas picture;
		int count1, count2;
		Bullet(Display display, Shell shell, Tank tank){
			img=new Image(display,"bullet.png");
			picture=new Canvas(shell,SWT.NONE);
			picture.addPaintListener(new PaintListener() {
				public void paintControl(final PaintEvent e) {
					if(img!=null)
						e.gc.drawImage(img, 0, 0);
				}
			});
			x=tank.x+100;
			y=tank.y+37;

		}
		void draw() {
			picture.setBounds(x, y, img.getBounds().width, img.getBounds().height);
		}
		void update(Shell shell, Enemy[] enemy,Label label1, Label label2,Label label3, Tank tank, Label labelEsc, Button btn1){
			
			if((!labelEsc.isVisible() )) {
			x=x+(int)(Math.random()*3)+1;
			
			for (int i = 0; i < enemy.length; i++) {
				if(y > enemy[i].y && y < enemy[i].y + enemy[i].picture.getBounds().height && x+20 - img.getBounds().width >= enemy[i].x ){
					
					tank.count1++;
					label1.setText("Не допущено митингов: "+tank.count1);	
				
				
				x =-2000;
				y =-2000;
				enemy[i].x =(int)(800+Math.random()*1200);
				enemy[i].y = (int)(40+Math.random()*420);
			
				
				}
				else if(x>810) {
					x =-2000;
					y =-2000;
					
				}
			}
			}
		}
		
		
	
		

}
