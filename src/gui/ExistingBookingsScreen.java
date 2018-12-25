package gui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dataStorage.DetailedBooking;
import dataStorage.Movie;
import dataStorage.MovieVector;
import dataStorage.User;

// Shows all bookings the currentUser has made on JLists
public class ExistingBookingsScreen extends JPanel{
	private static User currentUser;
	private static MainFrame mainFrame;
	
	public ExistingBookingsScreen(MainFrame mf)
	{
		mainFrame = mf; // Setting the reference to the constructor field
		setLayout(null);
		currentUser = mainFrame.controller().getCurrentUser();
		mainFrame.setTitle("Existing Bookings of " + currentUser.getName());
		
		Label label = new Label("Existing Bookings");
		label.setAlignment(Label.CENTER);
		label.setBounds(136, 10, 146, 12);
		this.add(label);
		
		int numOfPaid = 0, numOfUnpaid = 0;
		for(int i = 0; i<mainFrame.controller().getCurrentUser().getBookingVector().size(); i++){
			if(mainFrame.controller().getCurrentUser().getBookingVector().get(i).getPaymentStatus())
				++numOfPaid;
			else
				++numOfUnpaid;
		}
		
		JLabel lblPaidBookings = new JLabel("<html>" + numOfPaid + " Paid Bookings:</html>");
		lblPaidBookings.setBounds(10, 52, 82, 35);
		this.add(lblPaidBookings);
		
		JLabel lblUnpaidBookings = new JLabel("<html>" + numOfUnpaid + " Unpaid Bookings:</html>");
		lblUnpaidBookings.setBounds(10, 130, 82, 45);
		this.add(lblUnpaidBookings);
		
		JLabel lblNumOfBookings = new JLabel("Total number of bookings: " + (numOfPaid + numOfUnpaid));
		lblNumOfBookings.setBounds(10, 217, 169, 14);
		this.add(lblNumOfBookings);
		
		// Create and add ScrollPanes
		JScrollPane scrollPane_paid = new JScrollPane();
		scrollPane_paid.setBounds(102, 28, 241, 82);
		this.add(scrollPane_paid);
		JScrollPane scrollPane_unpaid = new JScrollPane();
		scrollPane_unpaid.setBounds(102, 115, 241, 82);
		this.add(scrollPane_unpaid);
		
		// Create and Add JLists
		JList list_paid = new JList();
		list_paid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_paid.setViewportView(list_paid);
		DefaultListCellRenderer r = (DefaultListCellRenderer) list_paid.getCellRenderer();
		r.setHorizontalAlignment(SwingConstants.CENTER);
		JList list_unpaid = new JList();
		list_unpaid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_unpaid.setViewportView(list_unpaid);
		r = (DefaultListCellRenderer) list_unpaid.getCellRenderer();
		r.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Populating both JList with bookings belonging to the current User
		DefaultListModel model_paid = new DefaultListModel();
		DefaultListModel model_unpaid = new DefaultListModel();
		for(int i = 0; i<mainFrame.controller().getCurrentUser().getBookingVector().size(); i++){
			if(mainFrame.controller().getCurrentUser().getBookingVector().get(i).getPaymentStatus())
				model_paid.addElement("<html>" + currentUser.getBookingVector().get(i).asJList_element());
			else
				model_unpaid.addElement("<html>" + currentUser.getBookingVector().get(i).asJList_element());
		}
		list_paid.setModel(model_paid);
		list_unpaid.setModel(model_unpaid);

		// Adding action listener to both JLists
		list_paid.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0){showBookingDetailsScreen(list_paid);}
		});
		list_unpaid.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0){showBookingDetailsScreen(list_unpaid);}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { mainFrame.showHomeScreen(); }
		});
		btnBack.setBounds(232, 213, 111, 23);
		this.add(btnBack);
	}
	private void showBookingDetailsScreen(JList l){
		for(int i = 0; i<currentUser.getBookingVector().size(); i++){
			if(l.getSelectedValue().toString().equals("<html>" + currentUser.getBookingVector().get(i).asJList_element())){
				mainFrame.controller().setCurrentBookingSelection(new DetailedBooking(currentUser.getBookingVector().get(i), null));
				break; // Break the for loop when booking is found
			}
		}
		// Call ExistingBookingDetailsScreen after setting currentBookingSelection from the JList
		mainFrame.showExistingBookingDetailsScreen();
	}
}