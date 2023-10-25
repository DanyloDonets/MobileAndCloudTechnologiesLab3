package lb3_donets;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class lb3_task1 {
	private Text xText;
	private Text yText;
	private Text zText;
	private double a;
	private double b;
	private double x;
	private double y;
	private double z;
	
	
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			lb3_task1 window = new lb3_task1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("LB3 Task1 Donets 316st ");
		
		Label task1Lbl = new Label(shell, SWT.NONE);
		task1Lbl.setBounds(167, 31, 55, 15);
		task1Lbl.setText("Task1");
		
		Label xLbl = new Label(shell, SWT.NONE);
		xLbl.setBounds(10, 73, 23, 15);
		xLbl.setText("X =");
		
		Label yLbl = new Label(shell, SWT.NONE);
		yLbl.setText("Y =");
		yLbl.setBounds(131, 76, 23, 15);
		
		xText = new Text(shell, SWT.BORDER);
		xText.setBounds(36, 73, 76, 21);
		
		yText = new Text(shell, SWT.BORDER);
		yText.setBounds(156, 73, 76, 21);
		
		Label zLbl = new Label(shell, SWT.NONE);
		zLbl.setBounds(252, 76, 23, 15);
		zLbl.setText("Z =");
		
		zText = new Text(shell, SWT.BORDER);
		zText.setBounds(286, 73, 76, 21);
		
		
		
		
		Label aLbl = new Label(shell, SWT.NONE);
		aLbl.setBounds(204, 172, 48, 15);
		aLbl.setText("= 0");
		
		Label bLbl = new Label(shell, SWT.NONE);
		bLbl.setBounds(141, 236, 55, 15);
		bLbl.setText("= 0");
		
		Label aImageLbl = new Label(shell, SWT.NONE);
		aImageLbl.setImage(SWTResourceManager.getImage("D:\\ХАІ\\Мобільні та хмарні технології\\практична 1\\lb3_donets\\image\\task1_a.jpg"));
		aImageLbl.setBounds(10, 158, 188, 49);
		
		Label bImageLbl = new Label(shell, SWT.NONE);
		bImageLbl.setImage(SWTResourceManager.getImage("D:\\ХАІ\\Мобільні та хмарні технології\\практична 1\\lb3_donets\\image\\task1_b.jpg"));
		bImageLbl.setBounds(10, 213, 125, 48);
		
		Button btnCount = new Button(shell, SWT.NONE);
		btnCount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			try {
				x = Double.parseDouble(xText.getText());
				y = Double.parseDouble(yText.getText());
				z = Double.parseDouble(zText.getText());
				aLbl.setText("= "+countA());
				bLbl.setText("= "+countB());
			
			}catch(Exception e1) {
				MessageBox box = new MessageBox(shell, SWT.OK);
				box.setText("Error");
				box.setMessage("Check varabiles");

				box.open();
			}
				
			}
		});
		btnCount.setBounds(156, 117, 75, 25);
		btnCount.setText("Count");
		
		
		Button resetBtn = new Button(shell, SWT.NONE);
		resetBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				xText.setText("");
				yText.setText("");
				zText.setText("");
				aLbl.setText("= 0");
				bLbl.setText("= 0");
			}
		});
		resetBtn.setBounds(322, 192, 75, 25);
		resetBtn.setText("Reset");
		
		Button backBtn = new Button(shell, SWT.NONE);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lb3 mainWindow = new lb3();
				shell.dispose();
				mainWindow.open();
			}
		});
		backBtn.setBounds(338, 20, 75, 25);
		backBtn.setText("->");
		
		 shell.addShellListener(new org.eclipse.swt.events.ShellAdapter() {
	            @Override
	            public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
	                
	            	shell.dispose();
	                lb3 lb3Window = new lb3();
	                lb3Window.open();
	            }
	        });
		 
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
				}
			}
		}
	private double countA() {
		double a= (1+y)*((x+y/(Math.pow(x, 2)+4))/(Math.exp(-x-2)+1/(Math.pow(x, 2)+4)));
		return a;
		
	}
	private double countB() {
		double b =(1+Math.cos(y-2))/(Math.pow(x, 4)/2+Math.pow(Math.sin(z), 2));
		return b;
		
	}
	
	
}