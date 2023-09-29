import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.*; 
public class Employee {

	private JFrame frame;
	private JTextField jtxtEmployeeID;
	private JTable table;
	private JTextField jtxtNINumber;
	private JTextField jtxtSurname;
	private JTextField jtxtFirstname;
	private JTextField jtxtGender;
	private JTextField jtxtDOB;
	private JTextField jtxtAge;
	private JTextField jtxtSalary;

	Connection conn = null;
	PreparedStatement pst= null;
	ResultSet rs= null;
	
	DefaultTableModel model= new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	
	public void updateTable()
	{
		conn = EmployeeData.ConnectDB();
		
		if(conn !=null)
		{
			String sql = "Select EmpID,NINumber,Firstname,Surname,Gender,DOB,Age,Salary";
		
		
		try
		{
			pst = conn.prepareStatement(sql);
			rs =pst.executeQuery();
			Object[] columnData = new Object[8];
			
			while(rs.next()) {
				columnData [0] = rs.getString("EmpID");
				columnData [1] = rs.getString("NINumber");
				columnData [2] = rs.getString("Firstname");
				columnData [3] = rs.getString("Surname");
				columnData [4] = rs.getString("Gender");
				columnData [5] = rs.getString("DOB");
				columnData [6] = rs.getString("Age");
				columnData [7] = rs.getString("Salary"); 
				
				model.addRow(columnData);
			}
		}
		 catch(Exception e)
		    {
			  JOptionPane.showMessageDialog(null, e);
	        }
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
		
		conn = EmployeeData.ConnectDB();
		Object col[] = {"EmpID","NINumber","Firstname","Surname","Gender","DOB","Age","Salary"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.setBounds(0,0,1450,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(82, 136, 134, 33);
		frame.getContentPane().add(lblNewLabel);
		
		jtxtEmployeeID = new JTextField();
		jtxtEmployeeID.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtEmployeeID.setBounds(311, 134, 280, 37);
		frame.getContentPane().add(jtxtEmployeeID);
		jtxtEmployeeID.setColumns(10);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql ="INSERT INTO employee(EmpID,NINumber,Firstname,Surname,Gender,DOB,Age,Salary)VALUES(?,?,?,?,?,?,?,?)";
						
				try
				{
					pst=conn.prepareStatement(sql);
					   pst.setString(1, jtxtEmployeeID.getText());
					   pst.setString(1, jtxtNINumber.getText());
					   pst.setString(1, jtxtFirstname.getText());
					   pst.setString(1, jtxtSurname.getText());
					   pst.setString(1, jtxtGender.getText());
					   pst.setString(1, jtxtDOB.getText());
					   pst.setString(1, jtxtAge.getText());
					   pst.setString(1, jtxtSalary.getText());
					   
					   pst.execute();
					   
					   rs.close();
					   pst.close();
				}
				catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null, "System Update Completed");
				}
				
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				          model.addRow(new Object[] {
				        		  
				        		 jtxtEmployeeID.getText(),
								 jtxtNINumber.getText(),
							     jtxtFirstname.getText(),
								 jtxtSurname.getText(),
				                 jtxtGender.getText(),
								 jtxtDOB.getText(),
								 jtxtAge.getText(),
								 jtxtSalary.getText(),
				          });
				          if (table.getSelectedRow() == -1)
				          {
				        		JOptionPane.showMessageDialog(null, "Membership Update confirmed","Employee Database System",
				        				JOptionPane.OK_OPTION);
				          }
				
			}
		});
		btnAddNew.setBounds(82, 603, 235, 49);
		frame.getContentPane().add(btnAddNew);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(728, 142, 680, 388);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"EmpID", "NINumber", "Firstname", "Surname", "Gender", "DOB", "Age", "Salary"
			}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		JLabel IblNiNumber = new JLabel("NI Number");
		IblNiNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		IblNiNumber.setBounds(82, 188, 134, 33);
		frame.getContentPane().add(IblNiNumber);
		
		jtxtNINumber = new JTextField();
		jtxtNINumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtNINumber.setColumns(10);
		jtxtNINumber.setBounds(311, 188, 280, 33);
		frame.getContentPane().add(jtxtNINumber);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFirstname.setBounds(82, 245, 134, 33);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(82, 295, 134, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		jtxtSurname = new JTextField();
		jtxtSurname.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtSurname.setColumns(10);
		jtxtSurname.setBounds(311, 293, 280, 36);
		frame.getContentPane().add(jtxtSurname);
		
		jtxtFirstname = new JTextField();
		jtxtFirstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtFirstname.setColumns(10);
		jtxtFirstname.setBounds(311, 243, 280, 36);
		frame.getContentPane().add(jtxtFirstname);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGender.setBounds(82, 349, 134, 33);
		frame.getContentPane().add(lblGender);
		
		jtxtGender = new JTextField();
		jtxtGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtGender.setColumns(10);
		jtxtGender.setBounds(311, 349, 280, 33);
		frame.getContentPane().add(jtxtGender);
		
		jtxtDOB = new JTextField();
		jtxtDOB.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtDOB.setColumns(10);
		jtxtDOB.setBounds(311, 404, 280, 33);
		frame.getContentPane().add(jtxtDOB);
		
		jtxtAge = new JTextField();
		jtxtAge.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtAge.setColumns(10);
		jtxtAge.setBounds(311, 458, 280, 33);
		frame.getContentPane().add(jtxtAge);
		
		jtxtSalary = new JTextField();
		jtxtSalary.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtSalary.setColumns(10);
		jtxtSalary.setBounds(311, 513, 280, 36);
		frame.getContentPane().add(jtxtSalary);
		
		JLabel lblNewLabel_1_2 = new JLabel("DOB");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(82, 404, 134, 33);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Age");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(82, 458, 134, 33);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Salary");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(82, 515, 134, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			   MessageFormat header= new MessageFormat("Printing in Progress");
			   MessageFormat footer= new MessageFormat("Page {0, number, integer}");
			   
			   try
			   {
				   table.print();
				   
			   }
			   catch(java.awt.print.PrinterException ev) {
				   System.err.format("No Printer found",ev.getMessage());
			   }
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrint.setBounds(380, 603, 235, 49);
		frame.getContentPane().add(btnPrint);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				jtxtEmployeeID.setText(null);
				jtxtNINumber.setText(null);
				jtxtFirstname.setText(null);
				jtxtSurname.setText(null);
				jtxtGender.setText(null);
				jtxtDOB.setText(null);
				jtxtAge.setText(null);
				jtxtSalary.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(686, 603, 235, 49);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			     frame =new JFrame("Exit");
			        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","Employee Database System",
			        		JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
			        	System.exit(0);
			        }
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(969, 603, 235, 49);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Database Management System");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(386, 55, 638, 49);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
