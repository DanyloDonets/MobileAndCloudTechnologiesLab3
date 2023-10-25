package lb3_donets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class lb3 {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			lb3 window = new lb3();
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
		
		Label lblLb = new Label(shell, SWT.NONE);
		lblLb.setBounds(170, 37, 55, 15);
		lblLb.setText("LB3");
		
		Button task1Btn = new Button(shell, SWT.NONE);
		task1Btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lb3_task1 task1Window = new lb3_task1();
				shell.dispose();
				task1Window.open();
				
            }
		});
		task1Btn.setBounds(146, 69, 75, 25);
		task1Btn.setText("Task1");
		
		
		Button task2Btn = new Button(shell, SWT.NONE);
		task2Btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lb3_task2 task2Window = new lb3_task2();
				shell.dispose();
				task2Window.open();
			}
		});
		task2Btn.setBounds(146, 114, 75, 25);
		task2Btn.setText("Task2");
		
		Button task3Btn = new Button(shell, SWT.NONE);
		task3Btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lb3_task3 task3Window = new lb3_task3();
				shell.dispose();
				task3Window.open();
			}
		});
		task3Btn.setBounds(146, 159, 75, 25);
		task3Btn.setText("Task3");
		
		Label developedByLabel = new Label(shell, SWT.NONE);
		developedByLabel.setBounds(224, 204, 199, 15);
		developedByLabel.setText("Developed by Donets Danylo 316st");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
