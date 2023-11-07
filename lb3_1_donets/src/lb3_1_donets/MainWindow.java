package lb3_1_donets;

import java.io.Console;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

public class MainWindow {
	private Text rowsText;
	private Text columnText;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	private static int numRows = 0;
    private static int numColumns = 0;
    private static int[][] matrix;
    private static int min;
    private static int max;
    private static int minX;
    private static int minY;
    private static int maxX;
    private static int maxY;
	
	
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
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
        shell.setText("LB3+ Donets 316st");
        
        rowsText = new Text(shell, SWT.BORDER);
		rowsText.setBounds(45, 10, 76, 21);
		
		columnText = new Text(shell, SWT.BORDER);
		columnText.setBounds(223, 10, 76, 21);


        Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 39, 306, 176);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        Button btnGenerateTable = new Button(shell, SWT.NONE);
		btnGenerateTable.addSelectionListener(new SelectionAdapter() {
			
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
				numColumns = Integer.parseInt(columnText.getText());
				numRows = Integer.parseInt(rowsText.getText());
				}
				catch(Exception e1) {
					MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
    				box.setText("Error");
    				box.setMessage("Varabiles must be positive numbers");
    				box.open();
				}
				if(numColumns <= 0 || numRows <= 0) {
					MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
    				box.setText("Error");
    				box.setMessage("Rows/Clolumns >0");
    				box.open();
				}else {
				matrix = null;
				matrix = new int[numRows][numColumns];
				table.removeAll();
				
				
				
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
			}
			}
		});
		btnGenerateTable.setBounds(331, 10, 93, 25);
		btnGenerateTable.setText("generate table");
		
        
        
        
        Label minLbl = new Label(shell, SWT.NONE);
        minLbl.setBounds(369, 125, 55, 15);
        minLbl.setText("Min:");
        
        Label maxLbl = new Label(shell, SWT.NONE);
        maxLbl.setBounds(369, 146, 55, 15);
        maxLbl.setText("Max: ");
        
        Button countBtn = new Button(shell, SWT.NONE);
        countBtn.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
        		
        		count(table, display, maxLbl,minLbl);
        		 
        	
        	}catch(Exception e1) {
				MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
				box.setText("Error");
				box.setMessage("Generete table");
				box.open();
			}}
        });
        countBtn.setBounds(349, 79, 75, 25);
        countBtn.setText("Count");
        
       
        
        Button changeBtn = new Button(shell, SWT.NONE);
        changeBtn.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
        		matrix[maxX][maxY] = min;
        		table.getItem(maxX).setText(maxY, String.valueOf(matrix[maxX][maxY]));
        		matrix[minX][minY] = max;
        		table.getItem(minX).setText(minY, String.valueOf(matrix[minX][minY]));
        		count(table, display, maxLbl,minLbl);
        	}catch(Exception e1) {
				MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
				box.setText("Error");
				box.setMessage("Generete table");
				box.open();
			}
        	}
        });
        changeBtn.setBounds(349, 190, 75, 25);
        changeBtn.setText("Change");
        
        
        
        Button randomBtn = new Button(shell, SWT.PUSH);
        randomBtn.setText("Random Fill");
        randomBtn.setBounds(10, 221, 296, 30);
        
        Label lblRow = new Label(shell, SWT.NONE);
        lblRow.setBounds(10, 16, 32, 15);
        lblRow.setText("Row:");
        
        Label lblColumn = new Label(shell, SWT.NONE);
        lblColumn.setBounds(161, 16, 55, 15);
        lblColumn.setText("Column:");
        randomBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                randomFillMatrix(table, display);
                updateTable(table, display);
                maxLbl.setText("Max: ");
                minLbl.setText("Min: ");
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
	
	
	private static void count(Table table, Display display,Label maxLbl, Label minLbl) {
		int num=0;
		max = matrix[0][0];
		min = matrix[numRows/2][numColumns/2];
		minX = 0;
		minY = 0;
		maxX = 0;
		maxY = 0;
		
		for (int i = 0; i < numRows; i++) {
			 
			 TableItem item = table.getItem(i);
            for (int j = 0; j < numColumns; j++) {
                if(j>=i && j<=numColumns/2) {
                	item.setBackground(j, display.getSystemColor(SWT.COLOR_RED));
                	if(matrix[i][j]>max) {
                		max = matrix[i][j];
                		maxX = i;
                		maxY = j;
                	}
                }
                else if(j>= numColumns/2 && j<=numColumns/2+num && i>=numRows/2) {
                	item.setBackground(j, display.getSystemColor(SWT.COLOR_GREEN));
                	
                	if(matrix[i][j]<min) {
                		min = matrix[i][j];
                		minX = i;
                		minY = j;
                	}
                }
                
                
            }
            if(i>=numRows/2) {
            	num++;
            }
        }
		table.getItem(maxX).setBackground(maxY, display.getSystemColor(SWT.COLOR_YELLOW)); 
		 maxLbl.setText("Max: "+max);
		 table.getItem(minX).setBackground(minY, display.getSystemColor(SWT.COLOR_BLUE)); 
		 minLbl.setText("Min: "+min);
	}
	
	
	private static void randomFillMatrix(Table table, Display display) {
    	
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
                item.setBackground(j, display.getSystemColor(SWT.COLOR_WHITE)); 
            }
        }
        
    }
	
}
