package gui;
import dataStorage.Movie;
import dataStorage.MovieVector;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants; 

// This screens shows all avail movies
public class AvailMoviesScreen extends JPanel {
	private static MainFrame mainFrame;
	
	public AvailMoviesScreen(MainFrame mf) {
		mainFrame = mf; // Setting the reference to the constructor field
		setLayout(null);  // Used for the event handler, need this to detect user selections
		mainFrame.setTitle("Available Movies");
		mainFrame.controller().setCurrentMovieSelection(null); // Reset current movie selection
		
		Label label = new Label("Available Movies");
		label.setAlignment(Label.CENTER);
		label.setBounds(66, 16, 252, 16);
		this.add(label);
		
		// Create ScrollPane
		JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setBounds(66, 38, 252, 165);
		this.add(scrollPane);
		
		// Create and Add JList to scrollPane
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		DefaultListCellRenderer r = (DefaultListCellRenderer) list.getCellRenderer();
		r.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Populating the JList
		DefaultListModel model = new DefaultListModel();
		for(int i = MovieVector.size() - 1; i > -1 ; i--)
			model.addElement(MovieVector.get(i).getTitle());
		list.setModel(model);
		
		// Adding action listener to JList
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Movie movie = MovieVector.getMovieWithTitle(list.getSelectedValue().toString());
				mainFrame.controller().setCurrentMovieSelection(movie); // Set the selected movie
				mainFrame.showMovieDetailsScreen(); // Call MovieDetailsScreen after setting current movie from the JList
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { showPreviousScreen(); }
		});
		btnBack.setBounds(150, 213, 89, 23);
		this.add(btnBack);
		
	}
	private void showPreviousScreen(){
		mainFrame.controller().setCurrentMovieSelection(null); // Reset movie selection when user goes back
		if(mainFrame.controller().getCurrentUser() == null)
			mainFrame.showWelcomeScreen();
		else
			mainFrame.showHomeScreen();
	}
}