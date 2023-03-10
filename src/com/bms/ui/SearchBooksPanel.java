package com.bms.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.bms.db.BookDAO;

public class SearchBooksPanel extends JPanel {

	private JTextField txtTitle;
	private JLabel lblTitle;
	final String[] Books = {"Better call saul","Java"," Daredevil"," Pulp fiction","Sixth sense "," Bond ","Turbomachines","demo"};
	final String[] categories = {"Crime/Drama","Textbook","Sci-fi"," Crime","Thriller","Action","Textbook","book"};
	private JComboBox<String> cbCategory = new JComboBox<String>(categories);
	private JLabel lblCategory = null;
	private JTextField txtAuthor;
	private JLabel lblAuthor;
	private JScrollPane pane;
	private JPanel panel;
	private JTable table;
	private JButton btnSearchTitle;
	private JButton btnSearchAuthor;
	private JButton btnHome;

	Vector<String> column = new Vector<String>();
	BookDAO dao = null;

	public SearchBooksPanel(Container parent, CardLayout layout) {

		panel = this;

		txtTitle = new JTextField(8);
		lblTitle = new JLabel("Search Title");

		txtAuthor = new JTextField(8);	
		lblAuthor = new JLabel("Search Author");

		lblCategory = new JLabel();
		lblCategory.setPreferredSize(new Dimension(100, 127));
		lblCategory.setMaximumSize(new Dimension(100, 127));

		cbCategory.setSelectedIndex(-1);
		cbCategory.setPreferredSize(new Dimension(140, 22));
		cbCategory.setMaximumSize(new Dimension(140, 22));


		column.add("ISBN");
		column.add("Title");
		column.add("Category");
		column.add("NoOfBooks");
		column.add("Authorname");
		column.add("MailID");

		dao = new BookDAO();
		table = new JTable(null, column);
		pane = new JScrollPane(table);

		btnSearchTitle = new JButton("Search");
		btnSearchTitle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String strTitle = txtTitle.getText();

				if(strTitle.trim().equals(""))
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel, "Book details cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				BookDAO dao = new BookDAO();
				table.setModel(new DefaultTableModel(dao.getSearchTitleData(strTitle), column));


			}
		});
		
		cbCategory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String category = (String)cb.getSelectedItem();
		        lblCategory.setText(category);
				
			}
		});
		

/*		cbCategory.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent ie) {

				if(ie.getStateChange() == ItemEvent.SELECTED)
				{
					int index = cbCategory.getSelectedIndex();
					String category = Books[index];
					lblCategory.setText(category);
				}
			}

		});
*/
		btnSearchAuthor = new JButton("Search");
		btnSearchAuthor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String strAuthor = txtAuthor.getText();

				if(strAuthor.trim().equals(""))
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel, "Book details cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				BookDAO dao = new BookDAO();
				table.setModel(new DefaultTableModel(dao.getSearchAuthorData(strAuthor), column));
			}
		});


		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(parent,"HOME");

			}
		});

		add(lblCategory);
		add(cbCategory);
		add(lblTitle);
		add(txtTitle);
		add(btnSearchTitle);
		add(lblAuthor);
		add(txtAuthor);
		add(btnSearchAuthor);
		add(btnHome);
		add(pane);


	}
}
