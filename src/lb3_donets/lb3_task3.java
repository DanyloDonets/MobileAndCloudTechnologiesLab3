package lb3_donets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class lb3_task3 {

    private static int numRows = 5;
    private static int numColumns = 5;
    private static int[][] matrix = new int[numRows][numColumns];
    private Text firstRowText;
    private Text secondRowText;
    private static int firstRow = -1;
    private static int secondRow = -1;
    

    public static void main(String[] args) {
        try {
            lb3_task3 window = new lb3_task3();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void open() {
        Display display = Display.getDefault();
        Shell shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("LB3 Task3 Donets 316st");

        Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 306, 127);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Додавання стовпців до таблиці з можливістю редагування
        for (int i = 0; i < numColumns; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setWidth(60);
        }

        // Додавання рядків (TableItem) та елементів матриці до таблиці
        for (int i = 0; i < numRows; i++) {
            TableItem item = new TableItem(table, SWT.NONE);
            for (int j = 0; j < numColumns; j++) {
                item.setText(j, String.valueOf(matrix[i][j]));
                
            }
        }

       


        
        Button randomBtn = new Button(shell, SWT.PUSH);
        randomBtn.setText("Random Fill");
        randomBtn.setBounds(20, 143, 296, 30);
        randomBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                randomFillMatrix(table, display);
                updateTable(table, display);
            }
        });

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
        
        firstRowText = new Text(shell, SWT.BORDER);
        firstRowText.setBounds(83, 191, 76, 21);
        
        secondRowText = new Text(shell, SWT.BORDER);
        secondRowText.setBounds(83, 218, 76, 21);
        
        Label firstRowLbl = new Label(shell, SWT.NONE);
        firstRowLbl.setBounds(10, 197, 55, 15);
        firstRowLbl.setText("1 Row");
        
        Label secondRowLbl = new Label(shell, SWT.NONE);
        secondRowLbl.setText("2 Row");
        secondRowLbl.setBounds(10, 224, 55, 15);
        
        Button changeBtn = new Button(shell, SWT.NONE);
        changeBtn.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
        		firstRow = Integer.parseInt(firstRowText.getText());
        		secondRow = Integer.parseInt(secondRowText.getText());
        		if(firstRow >=5 || firstRow < 0) {
        			MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
    				box.setText("Error");
    				box.setMessage("Check varabiles of 1Row (0>=n<=4)");

    				box.open();
        		} else if(secondRow >=5 || secondRow < 0) {
        			MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
    				box.setText("Error");
    				box.setMessage("Check varabiles of 2Row (0>=n<=4)");
    				box.open();
        		}else {
        			int buff;
        			for(int i=0;i<numRows;i++) {
        				buff = matrix[firstRow][i];
        				matrix[firstRow][i] = matrix[secondRow][i];
        				matrix[secondRow][i] = buff;
        			}
        			updateTable(table, display);
        		}
        		}catch(Exception e1) {
        			MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
    				box.setText("Error");
    				box.setMessage("Check varabiles of Rows");
    				box.open();
        		}
        		
        	}
        });
        changeBtn.setBounds(184, 202, 75, 25);
        changeBtn.setText("Change rows");
        
        
        
        
        
        
        

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

    private static void randomFillMatrix(Table table, Display display) {
    	firstRow = -1;
    	secondRow = -1;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }
        updateTable(table, display);
    }

    private static void updateTable(Table table, Display display) {
        for (int i = 0; i < numRows; i++) {
            TableItem item = table.getItem(i);
            for (int j = 0; j < numColumns; j++) {
                item.setText(j, String.valueOf(matrix[i][j]));
                if(i == firstRow || i == secondRow) {
                	item.setBackground(j, display.getSystemColor(SWT.COLOR_RED));
                }
                else {
                	item.setBackground(j, display.getSystemColor(SWT.COLOR_WHITE));
                }
            }
        }
    }
}