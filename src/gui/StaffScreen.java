package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.BookingController;
import dataStorage.DetailedBooking;
import javax.swing.JLabel;
import javax.swing.JTextField;

// Home screen for staff after logging in. Allows staff to find and select a booking to edit
public class StaffScreen extends JPanel{
	private static MainFrame mainFrame;
	private static JLabel lblJListSummary;
	private static JList list;
	private static DefaultListModel model = new DefaultListModel();
	private Vector<DetailedBooking> bookings;
	
	public StaffScreen(MainFrame mf) {
		this.setSize(400, 300);
		mainFrame = mf;
		setLayout(null);
		mainFrame.setTitle("Staff: " + mainFrame.controller().getCurrentUser().getName());
		if(!mainFrame.controller().getCurrentUser().getAccessLevel()) { // If staff screen called without staffAccess
			JOptionPane.showMessageDialog(null, "Invalid Staff credentials!", "ERROR", JOptionPane.ERROR_MESSAGE);
			mainFrame.controller().resetCurrentUser(); // Reset the login credentials stored in the system
			mainFrame.showWelcomeScreen(); // Brings user back to the Welcome Screen
		}

		lblJListSummary = new JLabel("All Bookings in system");
		lblJListSummary.setHorizontalAlignment(SwingConstants.CENTER);
		lblJListSummary.setBounds(109, 17, 245, 14);
		this.add(lblJListSummary);

		
		// Create ScrollPane
		JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setBounds(129, 42, 245, 150);
		this.add(scrollPane);
		// Create and Add JList to scrollPane
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		DefaultListCellRenderer r = (DefaultListCellRenderer) list.getCellRenderer();
		r.setHorizontalAlignment(SwingConstants.CENTER);
		updateView("all"); // Default JList population to populate with all bookings
		list.addListSelectionListener(new ListSelectionActionListener()); // Adding action listener to JList
		
		JButton btnBookingsInSystem = new JButton("<html>"+BookingController.totalBookings()+" Bookings in System"+"</html>");
		btnBookingsInSystem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){updateView("all");}
		});
		btnBookingsInSystem.setBounds(10, 11, 109, 40);
		btnBookingsInSystem.setHorizontalAlignment(SwingConstants.CENTER); // Test if this works
		this.add(btnBookingsInSystem);

		JButton btnPaidBookings = new JButton("<html>"+BookingController.totalPaidBookings()+" Paid Bookings"+"</html>");
		btnPaidBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){updateView("paid");}
		});
		btnPaidBookings.setBounds(10, 61, 109, 40);
		this.add(btnPaidBookings);

		JButton btnUnpaidBookings = new JButton("<html>"+BookingController.totalUnpaidBookings()+" Unpaid Bookings"+"</html>");
		btnUnpaidBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){updateView("unpaid");}
		});
		btnUnpaidBookings.setBounds(10, 112, 109, 40);
		this.add(btnUnpaidBookings);

		JTextField txtQuery = new JTextField();
		txtQuery.setBounds(10, 175, 86, 20);
		this.add(txtQuery);

		JButton btnSearchBookings = new JButton("<html>Search Bookings</html>");
		btnSearchBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(txtQuery.getText().isEmpty())
					return; // Break out of function is query is empty
				else if(txtQuery.getText().substring(0, 1).equals("id"))
					if(txtQuery.getText().substring(2, txtQuery.getText().length()-1).matches("\\d+")) // If id is a valid number
						updateView("id", Integer.parseInt(txtQuery.getText().substring(2, txtQuery.getText().length()-1)));
			}
		});
		btnSearchBookings.setBounds(10, 208, 89, 40);
		this.add(btnSearchBookings);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.showLogoutScreen();}
		});
		btnLogout.setBounds(204, 203, 89, 23);
		this.add(btnLogout);
	}
	
	private void updateView(String type, int... args) {
		switch(type) {
		case "all":
			lblJListSummary.setText("All Bookings in system");
			bookings = BookingController.getAllBookings();
			break;
		case "paid":
			lblJListSummary.setText("Paid Bookings in system");
			bookings = BookingController.getPaidBookings();
			break;
		case "unpaid":
			lblJListSummary.setText("Unpaid Bookings in system");
			bookings = BookingController.getUnpaidBookings();
			break;
		default: // Custom stuff from query box
			lblJListSummary.setText("Search results");
			bookings = new Vector<DetailedBooking>() ;
			bookings.clear();
			bookings.add(new DetailedBooking(BookingController.getBookingWithID(args[0]), BookingController.getUserWithBookingID(args[0])));
		}
		
		model = new DefaultListModel();
		list.removeAll(); // Resets the JList
		// Populate the JList
		for(int i = 0; i<bookings.size(); i++)
			model.addElement(bookings.get(i).asJList_element());
		list.setModel(model);
	}
	private class ListSelectionActionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			for(int i = 0; i<bookings.size(); i++)
				if(list.getSelectedValue().toString().equals(bookings.get(i).asJList_element())){
					mainFrame.controller().setCurrentBookingSelection(bookings.get(i));
					break; // Break out of the 'for' loop when booking is found
				}
			mainFrame.showStaffEditBookingScreen(); // Change screen to display booking selected from JList
		}
	}
}