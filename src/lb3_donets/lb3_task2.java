package lb3_donets;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

public class lb3_task2 {

    private static int numRows = 5;
    private static int numColumns = 5;
    private static int[][] matrix = new int[numRows][numColumns];

    public static void main(String[] args) {
        try {
            lb3_task2 window = new lb3_task2();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        Display display = Display.getDefault();
        Shell shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("LB3 Task2 Donets 316st");

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
                if(i<=j && i<numColumns-j) {
                	item.setBackground(j, display.getSystemColor(SWT.COLOR_RED));
                
                }
            }
        }

        final TableEditor editor = new TableEditor(table);
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;

        table.addListener(SWT.MouseDown, e -> {
            Rectangle clientArea = table.getClientArea();
            Point pt = new Point(e.x, e.y);
            int index = table.getTopIndex();
            while (index < table.getItemCount()) {
                boolean visible = false;
                final TableItem item = table.getItem(index);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Rectangle rect = item.getBounds(i);
                    if (rect.contains(pt)) {
                        final int row = index;
                        final int column = i;
                        final Text text = new Text(table, SWT.NONE);
                        Listener textListener = new Listener() {
                            public void handleEvent(final Event e) {
                                switch (e.type) {
                                    case SWT.FocusOut:
                                        String textValue = text.getText().trim();
                                        if (!textValue.isEmpty()) {
                                            try {
                                                int newText = Integer.parseInt(textValue);
                                                item.setText(column, Integer.toString(newText));
                                                matrix[row][column] = newText;
                                            } catch (NumberFormatException ex) {
                                            	MessageBox box = new MessageBox(shell, SWT.OK);
                                				box.setText("Error");
                                				box.setMessage("Check varabiles");
                                				box.open();
                                            }
                                        } else {
                                            // Text is empty, show a MessageBox
                                            MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                                            box.setText("Empty Value");
                                            box.setMessage("Please enter a value before editing.");
                                            box.open();
                                        }
                                        text.dispose();
                                        break;
                                    case SWT.Traverse:
                                        switch (e.detail) {
                                            case SWT.TRAVERSE_RETURN:
                                                // Handle the return key press if needed
                                                // You can add specific logic here
                                            case SWT.TRAVERSE_ESCAPE:
                                                text.dispose();
                                                e.doit = false;
                                                break;
                                        }
                                        break;
                                }
                            }
                        };
                        text.addListener(SWT.FocusOut, textListener);
                        text.addListener(SWT.Traverse, textListener);
                        editor.setEditor(text, item, i);
                        text.setText(item.getText(i));
                        text.selectAll();
                        text.setFocus();
                        return;
                    }
                    if (!visible && rect.intersects(clientArea)) {
                        visible = true;
                    }
                }
                if (!visible)
                    return;
                index++;
            }
        });


        
        Button randomBtn = new Button(shell, SWT.PUSH);
        randomBtn.setText("Random Fill");
        randomBtn.setBounds(20, 143, 296, 30);
        randomBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                randomFillMatrix(table);
                updateTable(table);
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
        
        Label biggestLbl = new Label(shell, SWT.NONE);
        biggestLbl.setTouchEnabled(true);
        biggestLbl.setBounds(296, 208, 55, 15);
        biggestLbl.setText("0");
        
        
        Button biggestBtn = new Button(shell, SWT.NONE);
        biggestBtn.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		int theBiggest = 0;
        		for (int i = 0; i < numRows; i++) {
        			for (int j = 0; j < numColumns; j++) {
        				if(i<=j && i<numColumns-j) {
        					if(theBiggest < matrix[i][j]) {
        						theBiggest = matrix[i][j];
        					}
        				}
        			}
        		}
        		biggestLbl.setText(Integer.toString(theBiggest));
        		
        	}
        });
        biggestBtn.setBounds(20, 203, 115, 25);
        biggestBtn.setText("The  biggest value");
        
        Label tipLbl = new Label(shell, SWT.NONE);
        tipLbl.setBounds(141, 208, 149, 30);
        tipLbl.setText("The biggest colored value is");
        
        

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

    private static void randomFillMatrix(Table table) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }
        updateTable(table);
    }

    private static void updateTable(Table table) {
        for (int i = 0; i < numRows; i++) {
            TableItem item = table.getItem(i);
            for (int j = 0; j < numColumns; j++) {
                item.setText(j, String.valueOf(matrix[i][j]));
            }
        }
    }
}
