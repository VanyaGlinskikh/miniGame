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

public class Game {
	Display display;
	Shell shell;
	Label label1, label2, label3;
	Label labelB1, labelB2, labelB3, labelR;
	Button btn1,btn2;
	Button ok;
	Button menu;
	Label labelEsc;
	Label labelSet;
	Label labelEndGame;
	List list;
	Combo combo;
	Tank tank;
	Bullet[] bullet;
	Enemy[] enemy;
	int k=0, q=0, n=0, s=0;
	int sumEnemy;
	int escCount=0;
	int startCount=0;
	boolean up,down;
	int time_bullet = 3, time_tank = 60, time_enemy;	
	BufferedReader br;
	final String name = "Baza";
	public static void main(String args[]) throws IOException  {
		new Game();
		}
	Game() throws IOException  {
		display = new Display();
		shell = new Shell(display, SWT.DIALOG_TRIM);
		shell.setBackgroundImage(new Image(display,"fon1.png"));
		shell.setBounds(100, 100, 830, 573);
		shell.setText("4-ый СРОК");
		
		exitInMainMenu();
		labelEndGame();
		labelEsc();
		labelOne();
		labelTwo();
		labelRes();
		score();
		labelTh();
		up = false;
		down = false;
		bullet = new Bullet[150];
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);//задание прозрачного фона для накладываемых картинок
		shell.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				System.out.println("asfsdfsdfs");
				if(e.keyCode == SWT.ESC ) {
					escCount++;
					if(escCount % 2 != 0) {
						labelEsc.setVisible(true);
						menu.setVisible(true);
					}
					if(escCount % 2 == 0) {
						labelEsc.setVisible(false);
						menu.setVisible(false);
					}
				}
				if(e.keyCode == SWT.ARROW_RIGHT ) {
					try {
						if(!labelEsc.isVisible() && !menu.isVisible()) {
						q++;
						if(q <= 3) {
							System.out.println("вылетает "+q+" пуля");
							drowBullet();
							
							n=0;
							if(q == 1)
								labelB3.setVisible(false);
							if(q == 2)
								labelB2.setVisible(false);
							if(q == 3) {
								labelB1.setVisible(false);
								System.out.println("перезарядка");
								labelR.setVisible(true);
								}
							}
						
						else if(q >= 3 && n >= 3) {							
							n=0;
							q = 0;
							q++;
							labelB3.setVisible(false);
							System.out.println("вылетает "+q+" пуля");
							drowBullet();
						}
						}
								
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				if(e.keyCode == SWT.ARROW_UP) {
					up = true; 
					down = false;
				}
				if(e.keyCode==SWT.ARROW_DOWN) {
					down = true; 
					up = false;
				}
				
			}
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == SWT.ARROW_UP) 
					up = false; 
				if(e.keyCode == SWT.ARROW_DOWN)  
					down = false;	
			}
		});	
		combo();
		btnStart();
		btnSet();
		shell.open();
		shell_close();
	}
	void combo() {
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(350, 300 , 100, 50);
		combo.add("1");
		combo.add("2");
		combo.add("3");
		combo.setText(combo.getItem(0));
		combo.setVisible(false);
	}
	void score() throws IOException {
		File f = new File(name);	
		if(f.exists() && !f.isDirectory()) {
			RandomAccessFile raf;
			try {
				raf = new RandomAccessFile(name,"rw");
				s = raf.readInt();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			 
			
		}
	}
	void Add() throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		RandomAccessFile raf = new RandomAccessFile(name,"rw");
		if(tank.count1 > s) {
			s=tank.count1;
			label3.setText("рекорд:"+s);
		}
		raf.writeInt(s);
		raf.close();

	}
	void Print() throws IOException{
		RandomAccessFile raf = new RandomAccessFile(name,"rw");
			int s = raf.readInt();
			System.out.print(s+" ");
		raf.close();
	}
	void shell_close() {
		while (!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	void drowTank() {
		tank = new Tank(display, shell);
		tank.draw();
		run_tank();
		
	}
	void drowEnemy() {
		enemy = new Enemy[sumEnemy];
		for(int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy(display, shell);
			
		}
		
		run_enemy();	
	}
	void drowBullet() throws IOException {
		bullet[k] = new Bullet(display, shell, tank);
		run_bullet(k);
		k++;
	}
	void btnStart() {
		btn1 = new Button(shell, SWT.NONE);
		btn1.setText("начать");
		btn1.setBounds(350, 250 , 100, 50);
		btn1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().equals("1")) {
					sumEnemy = 3;
					time_enemy = 15;
				}
				if(combo.getText().equals("2")){
					time_enemy = 10;
					sumEnemy = 4;
				}
				if(combo.getText().equals("3")) {
					time_enemy = 10;
					sumEnemy = 5;
				}
				System.out.println("выбрана сложность "+combo.getText());
				System.out.println("скорость противника "+time_enemy);
				shell.setBackgroundImage(new Image(display,"fon.jpg"));
				k++;
				if(k<2) {
					
					ResB();
					
				}
				btn1.setVisible(false);
				btn2.setVisible(false);
				label1.setVisible(true);
				label2.setVisible(true);
				label3.setVisible(true);
				labelB1.setVisible(true);
				labelB2.setVisible(true);
				labelB3.setVisible(true);
				combo.setVisible(false);
				
				
				if(k<2) {
					drowEnemy();
					drowTank();
					
				}
				
			}
		});	
		
	}
	void btnSet() {
		ok = new Button(shell, SWT.NONE);
		ok.setText("ОК");
		ok.setBounds(350, 400 , 100, 30);
		ok.setVisible(false);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				labelSet.setVisible(false);
				combo.setVisible(false);
				ok.setVisible(false);
				btn1.setVisible(true);
				btn2.setVisible(true);
			}
		});
		btn2 = new Button(shell, SWT.NONE);
		btn2.setText("настройки");
		btn2.setBounds(350, 350 , 100, 50);
		labelSet = new Label(shell, SWT.NONE);
		labelSet.setBounds(320, 250 , 160, 200);
		Color color = new Color(display, 230, 255, 255);
		labelSet.setBackground(color);
		labelSet.setFont(new Font(display, "Cambria", 11, SWT.ITALIC));
		labelSet.setText("Выберите сложность: ");
		labelSet.setVisible(false);
		btn2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				labelSet.setVisible(true);
				combo.setVisible(true);
				ok.setVisible(true);
				btn1.setVisible(false);
				btn2.setVisible(false);
				
				
			}
		});
		
		
	}
	void exitInMainMenu() {
		menu = new Button(shell, SWT.NONE);
		menu.setText("в меню");
		menu.setBounds(375, 250 , 100, 40);
		menu.setVisible(false);
		menu.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.setBackgroundImage(new Image(display,"fon1.png"));
				btn1.setVisible(true);
				btn2.setVisible(true);
				label1.setVisible(false);
				label2.setVisible(false);
				label3.setVisible(false);
				labelB1.setVisible(false);
				labelB2.setVisible(false);
				labelB3.setVisible(false);
				labelEsc.setVisible(false);
				menu.setVisible(false);
			}
		});
	}
	void labelOne() {
		Color color = new Color(display, 255, 255, 255);
		label1 = new Label(shell,SWT.NONE);
		label1.setBounds(0, 0, 180, 25);
		label1.setText("Не допущено митингов: ");	
		label1.setFont(new Font(display, "Cambria", 11, SWT.ITALIC));
		label1.setForeground(color);
		label1.setVisible(false);
		
	}
	void labelEndGame() {
		Color color = new Color(display, 255, 255, 255);
		labelEndGame = new Label(shell,SWT.NONE);
		labelEndGame.setBounds(230, 170, 360, 230);
		labelEndGame.setBackgroundImage(new Image(display, "endGame.jpg"));
		labelEndGame.setFont(new Font(display, "Cambria", 11, SWT.ITALIC));
		labelEndGame.setForeground(color);
		labelEndGame.setVisible(false);
		
	}
	void labelTwo() {
		Color color = new Color(display, 178, 34, 34);
		label2 = new Label(shell,SWT.NONE);
		label2.setBounds(200, 0, 200, 25);
		label2.setText("Митингов провели: ");
		label2.setFont(new Font(display, "Cambria", 11, SWT.ITALIC));
		label2.setForeground(color);
		label2.setVisible(false);
	}
	void labelRes() {
		Color color = new Color(display, 178, 34, 34);
		labelB1 = new Label(shell,SWT.NONE);
		labelB1.setBounds(520, 0, 77, 19);
		labelB1.setVisible(false);
		labelB2 = new Label(shell,SWT.NONE);
		labelB2.setBounds(600, 0, 77, 19);
		labelB2.setVisible(false);
		labelB3 = new Label(shell,SWT.NONE);
		labelB3.setBounds(680, 0, 77, 19);
		labelB3.setVisible(false);	
		labelB1.setBackgroundImage(new Image(display, "bullet1.png"));
		labelB2.setBackgroundImage(new Image(display, "bullet1.png"));
		labelB3.setBackgroundImage(new Image(display, "bullet1.png"));
		labelR = new Label(shell,SWT.NONE);
		labelR.setBounds(570, 0, 140, 19);
		labelR.setFont(new Font(display, "Cambria", 14, SWT.BOLD));
		labelR.setForeground(color);
		labelR.setText("ПЕРЕЗАРЯДКА");
		labelR.setVisible(false);
	}
	void labelTh() {
		Color color = new Color(display, 178, 34, 34);
		label3 = new Label(shell,SWT.NONE);
		label3.setBounds(400, 0, 100, 25);
		label3.setText("рекорд:"+s);	
		label3.setFont(new Font(display, "Cambria", 11, SWT.ITALIC));
		label3.setForeground(color);
		label3.setVisible(false);
	}
	void labelEsc() {
		Color color = new Color(display, 255, 255, 255);
		labelEsc = new Label(shell, SWT.NONE);
		labelEsc.setBounds(350, 150, 150, 150);
		labelEsc.setBackground(color);
		labelEsc.setVisible(false);
	}
	void ResB() {
		Runnable r = new Runnable() {

			public void run() {
				if(!labelEsc.isVisible()) {
					n++;
					System.out.println(n);
					
					if (n>=3) {
						if(labelB1.isVisible() == false) {
						labelR.setVisible(false);
						labelB1.setVisible(true);
						labelB2.setVisible(true);
						labelB3.setVisible(true);
						}
					}
					}
					
					
				display.timerExec(1000, this);
			}
			
		};
		display.timerExec(1000, r);
		
		
	}
	void run_bullet(int k) {
		Runnable r = new Runnable() {
			public void run() {
				
					bullet[k].update(shell, enemy, label1, label2, label3, tank, labelEsc, btn1);
					bullet[k].draw();
					display.timerExec(time_bullet, this);
				
			}
		};
		display.timerExec(time_bullet, r);
		
		
	}
	void run_tank() {
		Runnable r = new Runnable() {
			public void run() {
				tank.update(shell, up, down, labelEsc, btn1);
				display.timerExec(time_tank, this);	
			}
		};
		display.timerExec(time_tank, r);
	}
	void run_enemy() {
		Runnable r = new Runnable() {
			public void run() {
				for (int i = 0; i < enemy.length; i++) {
					enemy[i].update(shell, bullet, tank, label2, labelEsc, btn1, labelEndGame);
					enemy[i].draw();
					try {
						Add();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				display.timerExec(time_enemy, this);
			}
		};
		display.timerExec(time_enemy, r);

		
	}

}
