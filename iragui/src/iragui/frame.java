package iragui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class frame {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtroll;
	private JTextField txtyear;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame window = new frame();
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
	public frame() {
		initialize();
		connect();
		table_load();
	}
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	private JTable table;
	public  void connect()
	{ try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root",*password*);
		
		
		 
		
	}
	catch (ClassNotFoundException ex) 
    {
      ex.printStackTrace();
    }
    catch (SQLException ex) 
    {
    	   ex.printStackTrace();
    }
	
}


	/**
	 * Initialize the contents of the frame.
	 */
	public void table_load()
    {
    	try 
    	{
	    stmt = con.prepareStatement("select * from student");
	    rs = stmt.executeQuery();
	  
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	} 
    	catch (SQLException e) 
    	 {
    		e.printStackTrace();
	  } 
    }
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FORM");
		lblNewLabel.setBounds(181, 11, 77, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Register", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 38, 248, 125);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 27, 56, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblRollNo = new JLabel("ROLL NO.");
		lblRollNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRollNo.setBounds(10, 58, 67, 21);
		panel.add(lblRollNo);
		
		JLabel lblYear = new JLabel("YEAR");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblYear.setBounds(10, 90, 56, 21);
		panel.add(lblYear);
		
		txtname = new JTextField();
		txtname.setBounds(76, 28, 120, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtroll = new JTextField();
		txtroll.setColumns(10);
		txtroll.setBounds(76, 59, 120, 20);
		panel.add(txtroll);
		
		txtyear = new JTextField();
		txtyear.setColumns(10);
		txtyear.setBounds(76, 91, 120, 20);
		panel.add(txtyear);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name,roll_no,year;
				name = txtname.getText();
				roll_no = txtroll.getText();
				year= txtyear.getText();
							
				 try {
					stmt = con.prepareStatement("insert into student(name,roll_no,year)values(?,?,?)");
					stmt.setString(1, name);
					stmt.setString(2, roll_no);
					stmt.setString(3, year);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added successfully");
					table_load();
						           
					txtname.setText("");
					txtroll.setText("");
					txtyear.setText("");
					txtname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			
			
			}
		});
		btnNewButton.setBounds(10, 164, 77, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT\r\n");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(97, 164, 77, 37);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtname.setText("");
				txtroll.setText("");
				txtyear.setText("");
				txtname.requestFocus();
			}
		});
		btnClear.setBounds(181, 164, 77, 37);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 207, 248, 43);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("SID");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblName.setBounds(10, 14, 41, 16);
		panel_1.add(lblName);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				 try {
			          
			            String id = txtid.getText();

			                stmt = con.prepareStatement("select name,roll_no,year from student where id = ?");
			                stmt.setString(1, id);
			                ResultSet rs = stmt.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String name = rs.getString(1);
			                String roll_no = rs.getString(2);
			                String year = rs.getString(3);
			                
			                txtname.setText(name);
			                txtroll.setText(roll_no);
			                txtyear.setText(year);
			                
			                
			            }   
			            else
			            {
			            	txtname.setText("");
			            	txtroll.setText("");
			                txtyear.setText("");
			                 
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
				
			
			}
		});
		txtid.setColumns(10);
		txtid.setBounds(78, 13, 115, 18);
		panel_1.add(txtid);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
String name,roll_no,year,id;
				
				
				name = txtname.getText();
				roll_no = txtroll.getText();
				year = txtyear.getText();
				id  = txtid.getText();
				
				 try {
						stmt = con.prepareStatement("update student set name= ?,roll_no=?,year=? where id =?");
						stmt.setString(1, name);
			            stmt.setString(2, roll_no);
			            stmt.setString(3, year);
			            stmt.setString(4, id);
			            stmt.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			           
			            txtname.setText("");
			            txtroll.setText("");
			            txtyear.setText("");
			            txtname.requestFocus();
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnUpdate.setBounds(269, 213, 77, 37);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                String id;
			id  = txtid.getText();
			
			 try {
stmt = con.prepareStatement("delete from student where id =?");
			
stmt.setString(1, id);
stmt.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Deleted");
		            table_load();
		           
		            txtname.setText("");
		            txtroll.setText("");
		            txtyear.setText("");
		            txtname.requestFocus();
				}

	            catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnDelete.setBounds(356, 213, 77, 37);
		frame.getContentPane().add(btnDelete);
		
		table = new JTable();
		table.setBounds(268, 49, 156, 152);
		frame.getContentPane().add(table);
	}
}
